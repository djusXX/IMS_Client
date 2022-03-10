package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.databinding.LoginFragmentBinding;
import com.example.ims_mobile_client.utils.AppConstants;
import com.example.ims_mobile_client.utils.SavedData;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

import org.pjsip.pjsua2.pjsip_status_code;

public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment";
    private LoginFragmentBinding binding = null;

    private final BroadcastEventReceiver receiver = new BroadcastEventReceiver() {
        @Override
        public void onRegistration(String accountID, int registrationStateCode) {
            super.onRegistration(accountID, registrationStateCode);
            if (accountID.isEmpty() && 400 == registrationStateCode) {
                logInCurrentUser();
            } else if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                BuddyListFragment buddyListFragment = new BuddyListFragment(accountID);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
//                        .addToBackStack("Registered")
//                        .setReorderingAllowed(true)
                        .replace(R.id.main_fragment_container, buddyListFragment, BuddyListFragment.TAG)
                        .commit();
            } else {
                Toast.makeText(getActivity(), "error: " + registrationStateCode, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        receiver.register(requireActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        receiver.unregister(requireActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        loadLastUser();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.loginButton.setOnClickListener(v -> { checkRegistrationStatus(); });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }



    private void checkRegistrationStatus() {
        String usrSipUri = SavedData.getInstance(requireContext()).getString(AppConstants.USER_SIP_URI);
        if (usrSipUri != null && !usrSipUri.isEmpty()) {
            SipServiceCommand.getRegistrationStatus(requireContext().getApplicationContext(), usrSipUri);
            return;
        }
        logInCurrentUser();
    }

    public void logInCurrentUser() {
        SipAccountData accData = new SipAccountData();
        accData.setUsername(binding.username.getText().toString());
        accData.setPassword(binding.password.getText().toString());
        accData.setRealm(binding.realm.getText().toString());
        String pcscf = binding.pcscf.getText().toString();
        String host = pcscf.substring(0,pcscf.indexOf(":"));
        String portStr = pcscf.substring(pcscf.indexOf(":") + 1);
        int port = Integer.parseInt(portStr);
        accData.setHost(host);
        accData.setPort(port);

        SipServiceCommand.setAccount(requireContext().getApplicationContext(), accData);
        setLastUser(accData.getIdUri());
    }

    private void setLastUser(String sipUri) {
        SavedData appPrefs = SavedData.getInstance(getActivity());
        appPrefs.setString(AppConstants.USER_DISPLAY_NAME, binding.displayName.getText().toString());
        appPrefs.setString(AppConstants.USER_NAME, binding.username.getText().toString());
        appPrefs.setString(AppConstants.USER_REALM, binding.realm.getText().toString());
        appPrefs.setString(AppConstants.USER_PCSCF, binding.pcscf.getText().toString());
        appPrefs.setString(AppConstants.USER_SIP_URI, sipUri);
    }

    private void loadLastUser() {
        SavedData appPrefs = SavedData.getInstance(getActivity());
        binding.displayName.setText(appPrefs.getString(AppConstants.USER_DISPLAY_NAME));
        binding.username.setText(appPrefs.getString(AppConstants.USER_NAME));
        binding.realm.setText(appPrefs.getString(AppConstants.USER_REALM));
        binding.pcscf.setText(appPrefs.getString(AppConstants.USER_PCSCF));

        // use below only for debugging
        if(binding.displayName.getText().toString().isEmpty()) {
            binding.displayName.setText("ALICE");
            binding.username.setText("alice");
            binding.realm.setText("open-ims.test");
            binding.pcscf.setText("10.0.0.9:4060");
        }
        binding.password.setText("alice");
    }

}

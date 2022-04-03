package com.example.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.login.AppConstants;
import com.example.login.databinding.LoginFragmentBinding;
import com.example.login.viewmodel.SavedData;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;


public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();
    private LoginFragmentBinding binding = null;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
        logInCurrentUser(-1);
    }

    public void logInCurrentUser(int registrationStateCode) {
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

        boolean isAKAAuth = registrationStateCode == 401;
        SipServiceCommand.setAccount(requireContext().getApplicationContext(), accData, isAKAAuth);
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
package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ims_mobile_client.databinding.LoginFragmentBinding;
import com.example.ims_mobile_client.utils.AppConstants;
import com.example.ims_mobile_client.utils.AppPreferencesHelper;

import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipServiceCommand;

public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment";

    private LoginFragmentBinding binding;

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

        binding.loginButton.setOnClickListener(v -> { logInCurrentUser(); });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void logInCurrentUser() {
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

        SipServiceCommand.getRegistrationStatus(requireActivity().getApplicationContext(), accData.getIdUri());

        setLastUser(accData.getIdUri());
    }

    private void setLastUser(String sipUri) {
        AppPreferencesHelper appPrefs = AppPreferencesHelper.getInstance(getActivity());
        appPrefs.setString(AppConstants.USER_DISPLAY_NAME, binding.displayName.getText().toString());
        appPrefs.setString(AppConstants.USER_NAME, binding.username.getText().toString());
        appPrefs.setString(AppConstants.USER_REALM, binding.realm.getText().toString());
        appPrefs.setString(AppConstants.USER_PCSCF, binding.pcscf.getText().toString());
        appPrefs.setString(AppConstants.USER_SIP_URI, sipUri);
    }

    private void loadLastUser() {
        AppPreferencesHelper appPrefs = AppPreferencesHelper.getInstance(getActivity());
        binding.displayName.setText(appPrefs.getString(AppConstants.USER_DISPLAY_NAME));
        binding.username.setText(appPrefs.getString(AppConstants.USER_NAME));
        binding.realm.setText(appPrefs.getString(AppConstants.USER_REALM));
        binding.pcscf.setText(appPrefs.getString(AppConstants.USER_PCSCF));
    }

}

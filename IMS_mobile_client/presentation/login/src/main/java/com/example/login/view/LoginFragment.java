package com.example.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.login.databinding.LoginFragmentBinding;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();
    private LoginFragmentBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAlice();
        binding.loginButton.setOnClickListener(v -> { checkRegistrationStatus(); });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    private void checkRegistrationStatus() {
//        if (localUser != null) {
//            SipServiceCommand.getRegistrationStatus(requireActivity(), localUser.getSipUri());
//            return;
//        }
        logInCurrentUser(-1);
    }

    public void logInCurrentUser(int registrationStateCode) {
//        LocalUser localUser = createFromBinding();

//        SipAccountData accData = new SipAccountData();
//        accData.setUsername(localUser.userName);
//        accData.setPassword(binding.password.getText().toString());
//        accData.setRealm(localUser.realm);
//        accData.setHost(localUser.pcscfGetHost());
//        accData.setPort(localUser.pcscfGetPort());

        boolean isAKAAuth = registrationStateCode == 401;
//        SipServiceCommand.setAccount(requireActivity(), accData, isAKAAuth);
    }

//    private LocalUser createFromBinding() {
//        return new LocalUser(
//                binding.displayName.getText().toString(),
//                binding.username.getText().toString(),
//                binding.realm.getText().toString(),
//                binding.pcscf.getText().toString());
//    }

    private void setAlice() {
        binding.displayName.setText("ALICE");
        binding.username.setText("alice");
        binding.realm.setText("open-ims.test");
        binding.pcscf.setText("10.0.0.9:4060");
        binding.password.setText("alice");
    }

}

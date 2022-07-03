package ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.LocalUserViewModel;
import ims_mobile_client.ui.databinding.LoginFragmentBinding;

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
        binding.loginButton.setOnClickListener(v -> { logInCurrentUser(); });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public void logInCurrentUser() {
        LocalUserViewModel localUserViewModel = new ViewModelProvider(requireActivity())
                .get(LocalUserViewModel.class);

        UserView userView = createFromBinding();
        localUserViewModel.logInUserView(userView);
    }

    private UserView createFromBinding() {
        return new UserView(0,
                binding.username.getText().toString(),
                binding.password.getText().toString(),
                binding.displayName.getText().toString(),
                binding.realm.getText().toString(),
                binding.pcscf.getText().toString(),
                0);
    }

    private void setAlice() {
        binding.displayName.setText("ALICE");
        binding.username.setText("alice");
        binding.realm.setText("open-ims.test");
        binding.pcscf.setText("10.0.0.9:4060");
        binding.password.setText("alice");
    }

}

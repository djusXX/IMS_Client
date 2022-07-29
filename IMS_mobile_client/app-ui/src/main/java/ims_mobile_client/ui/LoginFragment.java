package ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;


import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.domain.models.UserLoggedStatus;
import ims_mobile_client.presentation.models.UserCredentials;
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.databinding.LoginFragmentBinding;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();
    private LoginFragmentBinding binding = null;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (userViewModel.getUserCredentials() != null) {
            UserCredentials uc = userViewModel.getUserCredentials();
            setUserCredentials(uc.getName(), uc.getDisplayName(), uc.getPassword(), uc.getRealm(), uc.getPcscf());
        } else {
            setUserCredentials("alice", "ALICE", "alice", "open-ims.test", "10.0.0.9:4060");
        }

        binding.loginButton.setOnClickListener(v -> { logInCurrentUser(); });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    public void logInCurrentUser() {
        userViewModel.getUserLoggedStatus().observe(requireActivity(), userLoggedStatus -> {
            if (userLoggedStatus == UserLoggedStatus.LOGGED_IN) {
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainFragment);
                return;
            }
            if (userLoggedStatus == UserLoggedStatus.TRYING) {
                Toast.makeText(requireActivity(), "Trying to log user", Toast.LENGTH_SHORT).show();
            }
            if (userLoggedStatus == UserLoggedStatus.UNKNOWN) {
                Toast.makeText(requireActivity(), "Unknown error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setUserCredentials(String name, String displayName, String password, String realm, String pcscf) {
        binding.username.setText(name);
        binding.displayName.setText(displayName);
        binding.password.setText(password);
        binding.realm.setText(realm);
        binding.pcscf.setText(pcscf);
    }

}

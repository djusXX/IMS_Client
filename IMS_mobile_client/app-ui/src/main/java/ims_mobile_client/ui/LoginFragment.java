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
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.databinding.LoginFragmentBinding;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();
    private LoginFragmentBinding binding = null;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUserRegistrationStatus().observe(getViewLifecycleOwner(), status -> {
            if (status == UserLoggedStatus.LOGGED_IN) {
                NavHostFragment.findNavController(this).popBackStack();
            }
            else if (status == UserLoggedStatus.TRYING) {
                Toast.makeText(requireActivity(), "Trying to log user", Toast.LENGTH_SHORT).show();
            }
            else if (status == UserLoggedStatus.UNKNOWN) {
                Toast.makeText(requireActivity(), "Unknown error occurred", Toast.LENGTH_SHORT).show();
            }
            else if (status == UserLoggedStatus.LOGGED_OUT) {
                Toast.makeText(requireActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getUserCredentials().observe(getViewLifecycleOwner(), userCredentials -> {
            if (userCredentials != null) {
                setUserCredentials(userCredentials.getName(), userCredentials.getDisplayName(),
                        userCredentials.getPassword(), userCredentials.getRealm(), userCredentials.getPcscf());
            }
        });

        // only for debugging
        setUserCredentials("alice", "ALICE", "alice", "open-ims.test", "10.0.0.9:4060");
        binding.loginButton.setOnClickListener(v -> { logInCurrentUser(); });
    }

    public void logInCurrentUser() {
        String name = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String displayName = binding.displayName.getText().toString();
        String realm = binding.realm.getText().toString();
        String pcscf = binding.pcscf.getText().toString();

        viewModel.registerUser(name, password, displayName, realm, pcscf);
    }

    private void setUserCredentials(String name, String displayName, String password, String realm, String pcscf) {
        binding.username.setText(name);
        binding.displayName.setText(displayName);
        binding.password.setText(password);
        binding.realm.setText(realm);
        binding.pcscf.setText(pcscf);
    }

}

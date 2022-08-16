package ims_mobile_client.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.databinding.BlankFragmentBinding;

@AndroidEntryPoint
public class BlankFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        viewModel.getLoggedUserUri().observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_home_to_mainFragment);
            } else {
                NavHostFragment.findNavController(this).navigate(R.id.loginFragment);
            }
        });
    }
}

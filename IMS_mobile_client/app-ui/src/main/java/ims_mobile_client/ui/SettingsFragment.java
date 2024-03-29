package ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.databinding.SettingsFragmentBinding;


@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private SettingsFragmentBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        binding.userSipUri.setText(viewModel.getUserCredentials().getValue().getSipUri());

        viewModel.getUserPresence().observe(requireActivity(), this::setCurrentPresence);
        viewModel.subscribePresence();

        binding.updateButton.setOnClickListener(v -> {
            String type = binding.statusType.getSelectedItem().toString();
            String text = binding.statusText.getText().toString();

            viewModel.updatePresence(type, text);
        });
    }

    private void setCurrentPresence(PresenceStatus status) {
        String title = "Current Presence status:";
        String type = "\ntype: ";
        String text = "\ntext: ";

        String all = title + type + status.getPresenceStatusType().toString()
                    + text + status.getPresenceStatusText();
        binding.currentPresenceState.setText(all);
    }

}
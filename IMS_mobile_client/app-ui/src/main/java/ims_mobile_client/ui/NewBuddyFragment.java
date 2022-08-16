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
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.BuddyListViewModel;
import ims_mobile_client.ui.databinding.NewBuddyFragmentBinding;

@AndroidEntryPoint
public class NewBuddyFragment extends Fragment {

    public static final String TAG = NewBuddyFragment.class.getName();

    private NewBuddyFragmentBinding binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_buddy_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BuddyListViewModel viewModel = new ViewModelProvider(requireActivity()).get(BuddyListViewModel.class);

        binding.newBuddyAdd.setOnClickListener(v -> {
            String displayName = binding.newBuddyDisplayName.getText().toString();
            String buddySipUri = binding.newBuddySipUri.getText().toString();
            viewModel.addBuddyToList(displayName, buddySipUri);
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

}

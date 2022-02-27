package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.databinding.BuddyListFragmentBinding;
import com.example.ims_mobile_client.view_models.BuddyViewModel;

public class BuddyListFragment extends Fragment {

    public static final String TAG = "BuddyListFragment";

    private BuddyAdapter buddyAdapter;
    private BuddyListFragmentBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.buddy_list_fragment, container, false);
        buddyAdapter = new BuddyAdapter();
        binding.buddyListRecyclerView.setAdapter(buddyAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final BuddyViewModel buddyViewModel = new ViewModelProvider(requireActivity()).get(BuddyViewModel.class);

        buddyViewModel.getAllBuddies().observe(getViewLifecycleOwner(), buddies-> {
            if (buddies != null) {
                buddyAdapter.setBuddyList(buddies);
            }
            binding.executePendingBindings();
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        buddyAdapter = null;
        super.onDestroyView();
    }
}

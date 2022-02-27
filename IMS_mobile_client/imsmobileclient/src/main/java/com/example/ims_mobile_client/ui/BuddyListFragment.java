package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.databinding.BuddyListFragmentBinding;

public class BuddyListFragment extends Fragment {

    public static final String TAG = "BuddyListFragment";

    private BuddyAdapter buddyAdapter;

//    private BuddyListFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.buddy_list_fragment, container, false);
//
////        buddyAdapter = new BuddyAdapter(buddyClickCallback)
//
//        return binding.getRoot();
        return null;
    }
}

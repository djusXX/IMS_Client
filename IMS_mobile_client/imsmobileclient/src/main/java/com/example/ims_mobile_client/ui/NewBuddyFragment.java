package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.databinding.NewBuddyFragmentBinding;
import com.example.ims_mobile_client.view_models.BuddyViewModel;

import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

public class NewBuddyFragment extends Fragment {

    private NewBuddyFragmentBinding binding = null;
    private static String usrSipUri;

    public NewBuddyFragment(String usrSipUri) {
        NewBuddyFragment.usrSipUri = usrSipUri;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_buddy_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final BuddyViewModel buddyViewModel = new ViewModelProvider(requireActivity()).get(BuddyViewModel.class);

        binding.newChatAdd.setOnClickListener(v -> {
            SipBuddyData buddyData = new SipBuddyData();
            buddyData.setSipUri(binding.newChatSipUri.getText().toString());
            buddyData.setDisplayName(binding.newChatDisplayName.getText().toString());
            SipServiceCommand.addBuddy(requireActivity().getApplicationContext(), usrSipUri, buddyData);
            buddyViewModel.addBuddy(new BuddyEntity(usrSipUri, buddyData.getBuddyUri(), buddyData.getDisplayName()));
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        usrSipUri = null;
        super.onDestroyView();
    }


}

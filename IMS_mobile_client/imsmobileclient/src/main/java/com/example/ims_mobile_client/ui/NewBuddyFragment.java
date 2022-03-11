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

    public static final String TAG = NewBuddyFragment.class.getName();

    private NewBuddyFragmentBinding binding = null;
    private static String usrSipUri;

    public NewBuddyFragment(String usrSipUri) {
        NewBuddyFragment.usrSipUri = usrSipUri;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.debug(TAG, "inside method onCreateView()===================================================================================");
        Logger.debug(TAG, "binding(" + binding + ")===================================================================================");
        Logger.debug(TAG, "usrSipUri(" + usrSipUri + ")===================================================================================");
        binding = DataBindingUtil.inflate(inflater, R.layout.new_buddy_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Logger.debug(TAG, "inside method onViewCreated()===================================================================================");
        Logger.debug(TAG, "binding(" + binding + ")===================================================================================");
        Logger.debug(TAG, "usrSipUri(" + usrSipUri + ")===================================================================================");
        super.onViewCreated(view, savedInstanceState);

        binding.newChatAdd.setOnClickListener(v -> {
            SipBuddyData buddyData = new SipBuddyData();
            buddyData.setSipUri(binding.newChatSipUri.getText().toString());
            buddyData.setDisplayName(binding.newChatDisplayName.getText().toString());
            SipServiceCommand.addBuddy(requireActivity().getApplicationContext(), NewBuddyFragment.usrSipUri, buddyData);
            requireActivity().getSupportFragmentManager().beginTransaction().remove(NewBuddyFragment.this).commit();
        });
    }

    @Override
    public void onDestroyView() {
        Logger.debug(TAG, "inside method onDestroyView()===================================================================================");
        Logger.debug(TAG, "binding(" + binding + ")===================================================================================");
        Logger.debug(TAG, "usrSipUri(" + usrSipUri + ")===================================================================================");
        binding = null;
        usrSipUri = null;
        super.onDestroyView();
    }


}

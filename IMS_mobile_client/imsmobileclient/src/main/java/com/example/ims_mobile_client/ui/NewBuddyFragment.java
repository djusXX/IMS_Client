package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.databinding.NewBuddyFragmentBinding;

import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

public class NewBuddyFragment extends Fragment {

    private NewBuddyFragmentBinding binding;
    private String loggedUserID;

    public NewBuddyFragment(String idUri) {
        loggedUserID = idUri;
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

        binding.newChatAdd.setOnClickListener(v -> {
            SipBuddyData buddyData = new SipBuddyData();
            buddyData.setSipUri(binding.newChatSipUri.getText().toString());
            buddyData.setDisplayName(binding.newChatDisplayName.getText().toString());
            SipServiceCommand.addBuddy(requireActivity().getApplicationContext(), loggedUserID, buddyData);

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container,
                                new BuddyFragment(binding.getNewBuddy()), null).commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }


}

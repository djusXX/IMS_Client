package com.example.ims_mobile_client.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.databinding.BuddyListFragmentBinding;
import com.example.ims_mobile_client.view_models.BuddyViewModel;

import net.gotev.sipservice.Logger;
import net.gotev.sipservice.SipAccountData;
import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

import java.util.ArrayList;
import java.util.List;

public class BuddyListFragment extends Fragment {

    public static final String TAG = "BuddyListFragment";

    private static BuddyAdapter buddyAdapter = null;
    private BuddyListFragmentBinding binding = null;
    private static String usrSipUri;

    public BuddyListFragment(String usrSipUri) {
        BuddyListFragment.usrSipUri = usrSipUri;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.buddy_list_fragment, container, false);
        buddyAdapter = new BuddyAdapter(buddyClickCallback);
        binding.buddyListRecyclerView.setAdapter(buddyAdapter);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final BuddyViewModel buddyViewModel = new ViewModelProvider(this).get(BuddyViewModel.class);

        buddyViewModel.getBuddiesFor(usrSipUri).observe(getViewLifecycleOwner(), buddies-> {
            if (buddies != null) {
                updateSubscription(buddies);
                buddyAdapter.setBuddyList(buddies);
            }
            binding.executePendingBindings();
        });
    }

    private void updateSubscription(List<BuddyEntity> buddyEntities) {
        ArrayList<SipBuddyData> buddyDataList = new ArrayList<SipBuddyData>();
        buddyEntities.forEach(buddyEntity -> {
            SipBuddyData buddyData = new  SipBuddyData();
            buddyData.setDisplayName(buddyEntity.buddy_display_name);
            buddyData.setSipUri(buddyEntity.buddy_sip_uri);
            buddyDataList.add(buddyData);
        });
        if (usrSipUri != null && buddyDataList.size() > 0) {
            SipServiceCommand.updateBuddyList(requireActivity().getApplicationContext(), usrSipUri, buddyDataList);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.chats_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (R.id.contacts_menu_search == id) {
//            // TODO: implement
//            return true;
//        }
        if (R.id.chats_menu_new_chat == id) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.main_fragment_container,
                                new NewBuddyFragment(usrSipUri), null).commit();
            }
            return true;
        }
        if (R.id.chats_menu_settings == id) {
            // TODO: implement
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        buddyAdapter = null;
        usrSipUri = null;
        super.onDestroyView();
    }

    private final BuddyClickCallback buddyClickCallback = buddyEntity -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("Conversation")
                    .replace(R.id.main_fragment_container,
                            new ConversationFragment(usrSipUri, buddyEntity), null).commit();
        }
    };

}

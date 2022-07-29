package ims_mobile_client.ui.conversations;

import android.os.Bundle;
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

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.BuddyListFragmentBinding;
import ims_mobile_client.ui.models.Buddy;

@AndroidEntryPoint
public class BuddyListFragment extends Fragment {

    public static final String TAG = BuddyListFragment.class.getName();

    private BuddyAdapter buddyAdapter = null;
    private BuddyListFragmentBinding binding = null;
    private static String usrSipUri;
    private static int mainContainer;

    public BuddyListFragment(String usrSipUri, int mainContainer) {
        BuddyListFragment.usrSipUri = usrSipUri;
        BuddyListFragment.mainContainer = mainContainer;
    }

    public BuddyListFragment() {
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
//        final BuddyViewModel buddyViewModel = new ViewModelProvider(requireActivity()).get(BuddyViewModel.class);

//        buddyViewModel.getBuddiesFor(usrSipUri).observe(getViewLifecycleOwner(), buddies-> {
//            if (buddies != null) {
//                updateSubscription(buddies);
//                buddyAdapter.setBuddyList(buddies);
//            }
//            binding.executePendingBindings();
//        });
    }

    private void updateSubscription(final List<Buddy> buddyEntities) {
//        ArrayList<SipBuddyData> buddyDataList = new ArrayList<SipBuddyData>();
//        buddyEntities.forEach(buddyEntity -> {
//            SipBuddyData buddyData = new SipBuddyData();
//            buddyData.setDisplayName(buddyEntity.buddy_display_name);
//            buddyData.setSipUri(buddyEntity.buddy_sip_uri);
//            buddyDataList.add(buddyData);
//        });
//        if (usrSipUri != null && buddyDataList.size() > 0) {
//            SipServiceCommand.updateBuddyList(requireActivity().getApplicationContext(), usrSipUri, buddyDataList);
//        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.chats_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
//        if (R.id.contacts_menu_search == id) {
//            // TODO: implement
//            return true;
//        }
        if (R.id.chats_menu_new_chat == id) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                NewBuddyFragment newBuddyFragment =  new NewBuddyFragment(usrSipUri, mainContainer);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("NewBuddy")
                        .setReorderingAllowed(true)
                        .replace(mainContainer,
                                newBuddyFragment, NewBuddyFragment.TAG).commit();
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
        super.onDestroyView();
    }

    private final BuddyClickCallback buddyClickCallback = buddyEntity -> {
//        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//            ConversationFragment conversationFragment = new ConversationFragment(usrSipUri, buddyEntity.buddy_sip_uri);
//            requireActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .addToBackStack("Conversation")
//                    .setReorderingAllowed(true)
//                    .replace(mainContainer,
//                            conversationFragment, ConversationFragment.TAG).commit();
//        }
    };

    public void updateBuddyState(String ownerSipUri, String contactUri, String presStatus, String presText) {
        buddyAdapter.updateBuddyState(ownerSipUri, contactUri, presStatus, presText);
    }
}

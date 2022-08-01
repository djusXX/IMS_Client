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
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.BuddyListViewModel;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.BuddyListFragmentBinding;
import ims_mobile_client.ui.models.Buddy;

@AndroidEntryPoint
public class BuddyListFragment extends Fragment {

    public static final String TAG = BuddyListFragment.class.getName();

    private BuddyAdapter buddyAdapter = null;
    private BuddyListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BuddyListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.buddy_list_fragment, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(BuddyListViewModel.class);

        buddyAdapter = new BuddyAdapter(buddyClickCallback);
//        BuddyListAdapter buddyListAdapter = new BuddyListAdapter();
        binding.buddyListRecyclerView.setAdapter(buddyAdapter);

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.chats_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (R.id.chats_menu_new_chat == id) {
                    navigateToNewBuddyFragment();
                    return true;
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        return binding.getRoot();
    }

    private void navigateToNewBuddyFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_newBuddyFragment);
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

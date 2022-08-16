package ims_mobile_client.ui.main;

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

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.presentation.viewModels.BuddyListViewModel;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.BuddyListFragmentBinding;

@AndroidEntryPoint
public class BuddyListFragment extends Fragment {

    public static final String TAG = BuddyListFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BuddyListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.buddy_list_fragment, container, false);
        BuddyListViewModel viewModel = new ViewModelProvider(requireActivity()).get(BuddyListViewModel.class);

        BuddyListAdapter buddyListAdapter = new BuddyListAdapter();
        binding.buddyListRecyclerView.setAdapter(buddyListAdapter);
        viewModel.getBuddyList().observe(getViewLifecycleOwner(), buddyListAdapter::submitList);

        requireActivity().addMenuProvider(new MenuProvider() {
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

}

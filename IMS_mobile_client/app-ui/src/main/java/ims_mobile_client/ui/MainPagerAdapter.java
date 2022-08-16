package ims_mobile_client.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import ims_mobile_client.ui.buddylist.BuddyListFragment;

public class MainPagerAdapter extends FragmentStateAdapter {
    public MainPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new SettingsFragment() : new BuddyListFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

package ims_mobile_client.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import javax.inject.Inject;

import ims_mobile_client.ui.buddylist.BuddyListFragment;

public class MainPagerAdapter extends FragmentStateAdapter {
//    SettingsFragment settings;
//    BuddyListFragment chats;
    @Inject
    public MainPagerAdapter(Fragment fragment) {
        super(fragment);
//        settings = new SettingsFragment();
//        chats = new BuddyListFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new SettingsFragment() : new BuddyListFragment();
//        return position == 0 ? settings : chats;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

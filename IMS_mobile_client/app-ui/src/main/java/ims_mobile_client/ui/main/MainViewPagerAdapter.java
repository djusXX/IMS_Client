package ims_mobile_client.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (0 == position) {
            return new SettingsFragment();
        }
        // 1 == position
        return new BuddyListFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}

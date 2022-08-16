package ims_mobile_client.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.ui.MainActivity;
import ims_mobile_client.ui.databinding.MainFragmentBinding;


@AndroidEntryPoint
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainFragmentBinding binding = MainFragmentBinding.inflate(inflater, container, false);
        TabLayout tabLayout = binding.tabs;
        ViewPager2 viewPager = binding.viewPager;

        viewPager.setAdapter(new MainViewPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            String text = "";
            if (position == 0) text = "SETTINGS";
            if (position == 1) text = "CHATS";
            tab.setText(text);
        }).attach();

        ((MainActivity) requireActivity()).setSupportActionBar(binding.toolbar);

        return binding.getRoot();
    }
}

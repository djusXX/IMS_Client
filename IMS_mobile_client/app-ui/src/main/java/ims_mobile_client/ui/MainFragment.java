package ims_mobile_client.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;
import ims_mobile_client.domain.models.UserLoggedStatus;
import ims_mobile_client.presentation.viewModels.UserViewModel;
import ims_mobile_client.ui.databinding.MainFragmentBinding;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getUserRegistrationStatus().observe(getViewLifecycleOwner(), userLoggedStatus -> {
            if (userLoggedStatus != UserLoggedStatus.LOGGED_IN) {
                navigateToLoginFragment();
            }
        });

        MainFragmentBinding binding = MainFragmentBinding.inflate(inflater, container, false);
        TabLayout tabLayout = binding.tabLayout;
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(new MainPagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, (tab, pos) -> {
            String title = (pos == 0) ? "SETTINGS" : "CHATS";
            tab.setText(title);
        }).attach();

        return binding.getRoot();
    }

    private void navigateToLoginFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.loginFragment);
    }
}

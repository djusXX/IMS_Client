package com.example.conversations.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.example.conversations.R;
import com.example.conversations.databinding.NewBuddyFragmentBinding;

import net.gotev.sipservice.SipBuddyData;
import net.gotev.sipservice.SipServiceCommand;

public class NewBuddyFragment extends Fragment {

    public static final String TAG = NewBuddyFragment.class.getName();

    private NewBuddyFragmentBinding binding = null;
    private static String usrSipUri;
    private static int mainContainer;

    public NewBuddyFragment(String usrSipUri, int mainContainer) {
        NewBuddyFragment.usrSipUri = usrSipUri;
        NewBuddyFragment.mainContainer = mainContainer;
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
            SipServiceCommand.addBuddy(requireActivity().getApplicationContext(), NewBuddyFragment.usrSipUri, buddyData);
            requireActivity().getSupportFragmentManager().popBackStack();

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ConversationFragment conversationFragment = new ConversationFragment(usrSipUri, buddyData.getBuddyUri());
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("Conversation")
                        .setReorderingAllowed(true)
                        .replace(mainContainer,
                                conversationFragment, ConversationFragment.TAG).commit();
//                ((MainActivity) requireActivity()).loadConversationFragment(usrSipUri, buddyData.getBuddyUri());
            }
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }


}

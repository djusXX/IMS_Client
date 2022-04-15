package com.example.conversations.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.conversations.R;
import com.example.conversations.databinding.ConversationFragmentBinding;
import com.example.conversations.viewmodel.MessageViewModel;
import com.example.repository.data.entities.MessageEntity;

import net.gotev.sipservice.SipServiceCommand;

import java.util.Date;

public class ConversationFragment extends Fragment {

    public static final String TAG = ConversationFragment.class.getName();

    private ConversationFragmentBinding binding;
    private static MessageAdapter messageAdapter = null;
    private static String usrSipUri;
    private static String buddySipUri;

    public ConversationFragment(String usrSipUri, String buddySipUri) {
        ConversationFragment.usrSipUri = usrSipUri;
        ConversationFragment.buddySipUri = buddySipUri;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.conversation_fragment, container, false);
        messageAdapter = new MessageAdapter(usrSipUri);
        binding.conversationRecyclerViewer.setAdapter(messageAdapter);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final MessageViewModel messageViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);

        messageViewModel.getMessagesFor(usrSipUri, buddySipUri).observe(getViewLifecycleOwner(), messageEntities -> {
            if(messageEntities != null) {
                messageAdapter.setMessages(messageEntities);
            }
            binding.executePendingBindings();
        });

        binding.sendButton.setOnClickListener(v -> {
            Date date = new Date();
            MessageEntity messageEntity = new MessageEntity(usrSipUri, buddySipUri, date.toString(), binding.messageInput.getText().toString());
            messageViewModel.addMessage(messageEntity);
            SipServiceCommand.sendMessage(requireActivity().getApplicationContext(), usrSipUri, buddySipUri, messageEntity.content);
            binding.messageInput.setText("");
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.conversation_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.option_call_audio == id) {
            try {
                SipServiceCommand.makeCall(requireActivity().getApplicationContext(), usrSipUri, buddySipUri);
            } catch (Exception exc) {
                Toast.makeText(getActivity(), "Error occurred while making Voice call:" + exc, Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (R.id.option_call_video == id) {
            try {
                SipServiceCommand.makeCall(requireActivity().getApplicationContext(), usrSipUri, buddySipUri, true, false);
            } catch (Exception exc) {
                Toast.makeText(getActivity(), "Error occurred while making Video call:" + exc, Toast.LENGTH_LONG).show();
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}

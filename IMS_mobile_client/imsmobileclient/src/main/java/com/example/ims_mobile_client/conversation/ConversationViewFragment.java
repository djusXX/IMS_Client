package com.example.ims_mobile_client.conversation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.common.MessageType;

import java.util.ArrayList;

public class ConversationViewFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected ConversationAdapter conversationAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Message> messages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversation_view_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.conversation_recycler_viewer);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        conversationAdapter = new ConversationAdapter(messages);
        recyclerView.setAdapter(conversationAdapter);

        return rootView;
    }

    private void initData() {
        messages = new ArrayList<> ();
        messages.add(new Message("message_text_1", MessageType.IN));
        messages.add(new Message("message_text_2", MessageType.OUT));
        messages.add(new Message("message_text_3", MessageType.IN));
        messages.add(new Message("message_text_4", MessageType.OUT));
        messages.add(new Message("message_text_5", MessageType.OUT));
        messages.add(new Message("message_text_6", MessageType.IN));
        messages.add(new Message("message_text_7", MessageType.IN));
    }
}

package com.example.ims_mobile_client.conversation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.common.MessageType;

import net.gotev.sipservice.BroadcastEventReceiver;
import net.gotev.sipservice.SipServiceCommand;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;

public class ConversationActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    protected ConversationAdapter conversationAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Message> messages;
    protected String accountID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountID = getIntent().getStringExtra(PARAM_ACCOUNT_ID);
        initData();


        setContentView(R.layout.activity_conversation);
        recyclerView = findViewById(R.id.conversation_recycler_viewer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        conversationAdapter = new ConversationAdapter(ConversationActivity.this, messages);
        recyclerView.setAdapter(conversationAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.conversation_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.option_call_audio == id) {
            // TODO: implement
            return true;
        }
        if (R.id.option_call_video == id) {
            // TODO: implement
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public BroadcastEventReceiver eventReceiver = new BroadcastEventReceiver() {

    };

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

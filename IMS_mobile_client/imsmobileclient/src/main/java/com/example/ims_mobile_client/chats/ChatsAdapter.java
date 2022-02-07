package com.example.ims_mobile_client.chats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.conversation.ConversationActivity;

import net.gotev.sipservice.SipBuddy;
import net.gotev.sipservice.SipBuddyData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CONTACT_URI;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {
    Context context;
    String accountID;
    String displayName;
    ArrayList<SipBuddyData> sipBuddyList = new ArrayList<>();


    // class to hold the layout for one row (single contact) of contact list 
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        ImageView statusImg;
        TextView name;
        TextView sipUri;
        TextView statusText;

        public ViewHolder(View v) {
            super(v);
            avatar = v.findViewById(R.id.contact_avatar);
            statusImg = v.findViewById(R.id.contact_status_image);
            name = v.findViewById(R.id.contact_name);
            sipUri = v.findViewById(R.id.contact_sip_uri);
            statusText = v.findViewById(R.id.contact_status_text);
        }

    }
    
    public ChatsAdapter(Context context, String accountID, String displayName, ArrayList<SipBuddyData> sipBuddyList) {
        this.context = context;
        this.accountID = accountID;
        this.displayName = displayName;
        this.sipBuddyList = sipBuddyList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view from core layout and data
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_chat, parent, false);
        v.setOnClickListener(view -> {
            Intent intent = new Intent(context, ConversationActivity.class);
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
            String contactUri = ((TextView) v.findViewById(R.id.contact_sip_uri)).getText().toString();
            intent.putExtra(PARAM_CONTACT_URI, contactUri);
            context.startActivity(intent);
        });
        return new ViewHolder(v);
    }

    @Override   // Fill holder with data
    public void onBindViewHolder(ViewHolder holder, int position) {
        SipBuddyData contact = sipBuddyList.get(position);
        holder.name.setText(contact.getDisplayName());
        holder.sipUri.setText(contact.getSipUri());
    }

    @Override
    public int getItemCount() {
        return sipBuddyList.size();
    }

    public boolean addBuddy(SipBuddyData buddyData) {
        if (sipBuddyList.contains(buddyData))
            return false;

        sipBuddyList.add(buddyData);
        return true;
    }
}

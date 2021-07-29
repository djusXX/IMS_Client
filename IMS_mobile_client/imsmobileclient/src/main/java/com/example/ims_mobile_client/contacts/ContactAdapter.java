package com.example.ims_mobile_client.contacts;

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

import net.gotev.sipservice.SipContact;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.gotev.sipservice.SipServiceConstants.PARAM_ACCOUNT_ID;
import static net.gotev.sipservice.SipServiceConstants.PARAM_CONTACT_URI;
import static net.gotev.sipservice.SipServiceConstants.PARAM_DISPLAY_NAME;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    String accountID;
    String displayName;
    ArrayList<String> sipContactList = new ArrayList<>();


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
    
    public ContactAdapter(Context context, String accountID, String displayName, ArrayList<String> sipContacts) {
        this.context = context;
        this.accountID = accountID;
        this.displayName = displayName;
        this.sipContactList = sipContacts;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view from core layout and data
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact, parent, false);
        v.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ConversationActivity.class);
            intent.putExtra(PARAM_ACCOUNT_ID, accountID);
            intent.putExtra(PARAM_DISPLAY_NAME, displayName);
            intent.putExtra(PARAM_CONTACT_URI, sipContactList.get(viewType));
            context.startActivity(intent);
        });
        return new ViewHolder(v);
    }

    @Override   // Fill holder with data
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = sipContactList.get(position);
        holder.sipUri.setText(name);
    }

    @Override
    public int getItemCount() {
        return sipContactList.size();
    }
}

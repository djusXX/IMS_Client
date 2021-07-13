package com.example.ims_mobile_client.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<Contact> contactList;


    // class to hold the layout for one row (single contact) of contact list 
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        ImageView statusImg;
        TextView name;
        TextView sipUri;
        TextView statusText;

        public ViewHolder(View v) {
            super(v);
            avatar = (ImageView) v.findViewById(R.id.contact_avatar);
            statusImg = (ImageView) v.findViewById(R.id.contact_status_image);
            name = (TextView) v.findViewById(R.id.contact_name);
            sipUri = (TextView) v.findViewById(R.id.contact_sip_uri);
            statusText = (TextView) v.findViewById(R.id.contact_status_text);
        }

    }
    
    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contactList = contacts;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view from core layout and data
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact, parent, false);
        v.setOnClickListener(view -> {
            // TODO: open history with current contact
        });
        return new ViewHolder(v);
    }

    @Override   // Fill holder with data
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = contactList.get(position).getName();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}

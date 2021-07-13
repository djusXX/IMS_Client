package com.example.ims_mobile_client.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.contacts.Contact;
import com.example.ims_mobile_client.contacts.ContactAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContactsViewFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected ContactAdapter contactAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected ArrayList<Contact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(contactAdapter);

        return rootView;
    }

    private void initData() {
        contacts = new ArrayList<> ();
        contacts.add(new Contact("contact_1"));
        contacts.add(new Contact("contact_2"));
        contacts.add(new Contact("contact_3"));
        contacts.add(new Contact("contact_4"));
        contacts.add(new Contact("contact_5"));
        contacts.add(new Contact("contact_6"));
        contacts.add(new Contact("contact_7"));
    }
}

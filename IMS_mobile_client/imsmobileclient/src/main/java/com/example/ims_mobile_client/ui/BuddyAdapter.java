package com.example.ims_mobile_client.ui;

import android.text.TextUtils;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.data.entities.BuddyEntity;
import com.example.ims_mobile_client.databinding.SingleBuddyBinding;

import net.gotev.sipservice.SipBuddyData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BuddyAdapter extends RecyclerView.Adapter<BuddyAdapter.BuddyViewHolder> {

    List<? extends BuddyEntity> buddyList;

//    @Nullable
//    private final MessagesListAdapter messagesListAdapter;


    public BuddyAdapter() {
    }

    public void setBuddyList(final List<? extends BuddyEntity> newBuddyList) {
        if (buddyList == null) {
            buddyList = newBuddyList;
            notifyItemRangeChanged(0, newBuddyList.size());
            return;
        }

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return buddyList.size();
            }

            @Override
            public int getNewListSize() {
                return newBuddyList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return buddyList.get(oldItemPosition).id == newBuddyList.get(newItemPosition).id;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                BuddyEntity oldBuddy = buddyList.get(oldItemPosition);
                BuddyEntity newBuddy = newBuddyList.get(newItemPosition);

                return oldBuddy.id == newBuddy.id
                        && TextUtils.equals(oldBuddy.buddy_display_name, newBuddy.buddy_display_name)
                        && TextUtils.equals(oldBuddy.buddy_sip_uri, newBuddy.buddy_sip_uri)
                        && TextUtils.equals(oldBuddy.user_sip_uri, newBuddy.user_sip_uri);
            }
        });

        buddyList = newBuddyList;
        result.dispatchUpdatesTo(this);
    }

    static class BuddyViewHolder extends RecyclerView.ViewHolder {

        final SingleBuddyBinding binding;

        public BuddyViewHolder(SingleBuddyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NotNull
    @Override
    public BuddyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil
//                .inflate(LayoutInflater.from(parent.getContext()), R.layout.single_buddy, parent,false);

        return null;
    }

    @Override   // Fill holder with data
    public void onBindViewHolder(BuddyViewHolder holder, int position) {
//        SipBuddyData contact = sipBuddyList.get(position);
//        holder.name.setText(contact.getDisplayName());
//        holder.sipUri.setText(contact.getSipUri());
    }

    @Override
    public int getItemCount() {
//        return sipBuddyList.size();
        return 0;
    }

    public boolean addBuddy(SipBuddyData buddyData) {
//        if (sipBuddyList.contains(buddyData))
//            return false;
//
//        sipBuddyList.add(buddyData);
        return true;
    }
}

package ims_mobile_client.ui.conversations;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import ims_mobile_client.R;
import ims_mobile_client.databinding.SingleBuddyFragmentBinding;
import ims_mobile_client.ui.models.Buddy;

public class BuddyAdapter extends RecyclerView.Adapter<BuddyAdapter.BuddyViewHolder> {

    private static List<? extends Buddy> buddyList;

    @Nullable
    private final BuddyClickCallback buddyClickCallback;


    public BuddyAdapter(@Nullable BuddyClickCallback buddyClickCallback) {
        this.buddyClickCallback = buddyClickCallback;
        setHasStableIds(true);
    }

    public void setBuddyList(final List<Buddy> newBuddyList) {
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
                return buddyList.get(oldItemPosition).getSipUri() == newBuddyList.get(newItemPosition).getSipUri();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Buddy oldBuddy = buddyList.get(oldItemPosition);
                Buddy newBuddy = newBuddyList.get(newItemPosition);

                return TextUtils.equals(oldBuddy.getSipUri(), newBuddy.getSipUri())
                        && TextUtils.equals(oldBuddy.getDisplayName(), newBuddy.getDisplayName())
                        && TextUtils.equals(oldBuddy.getStatusType(), newBuddy.getStatusType())
                        && TextUtils.equals(oldBuddy.getStatusText(), newBuddy.getStatusText());
            }
        });

        buddyList = newBuddyList;
        result.dispatchUpdatesTo(this);
    }

    public void updateBuddyState(String ownerSipUri, String contactUri, String presStatus, String presText) {
        if (buddyList != null) {
            buddyList.get(0).setStatusType(presStatus);
            buddyList.get(0).setStatusText(presText);
        }
    }

    static class BuddyViewHolder extends RecyclerView.ViewHolder {

        final SingleBuddyFragmentBinding binding;

        public BuddyViewHolder(SingleBuddyFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NotNull
    @Override
    public BuddyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleBuddyFragmentBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.single_buddy_fragment, parent,false);
        binding.setCallback(buddyClickCallback);
        return new BuddyViewHolder(binding);
    }

    @Override   // Fill holder with data
    public void onBindViewHolder(BuddyViewHolder holder, int position) {
        holder.binding.setBuddy(buddyList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return buddyList == null ? 0 : buddyList.size();
    }

    @Override
    public long getItemId(int position) {
        return buddyList.get(position).id;
    }
}

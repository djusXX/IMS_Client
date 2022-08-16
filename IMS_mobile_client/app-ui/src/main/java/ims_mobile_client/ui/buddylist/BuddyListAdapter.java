package ims_mobile_client.ui.buddylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ims_mobile_client.presentation.models.BuddyInfo;
import ims_mobile_client.ui.R;
import ims_mobile_client.ui.databinding.SingleBuddyFragmentBinding;

public class BuddyListAdapter extends ListAdapter<BuddyInfo, BuddyListAdapter.BuddyViewHolder> {

    public BuddyListAdapter() {
        super(BuddyListAdapter.diffCallback);
    }

    static class BuddyViewHolder extends RecyclerView.ViewHolder {
        final SingleBuddyFragmentBinding binding;

        public BuddyViewHolder(SingleBuddyFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public BuddyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleBuddyFragmentBinding binding = SingleBuddyFragmentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.setCallback(v -> {
            Bundle data = new Bundle();
            data.putString("buddySipUri", binding.getBuddy().getBuddySipUri());
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_conversationFragment, data);
        });
        return new BuddyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BuddyViewHolder holder, int position) {
        holder.binding.setBuddy(getItem(position));
        holder.binding.executePendingBindings();
    }

    public static final DiffUtil.ItemCallback<BuddyInfo> diffCallback = new DiffUtil.ItemCallback<BuddyInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull BuddyInfo oldItem, @NonNull BuddyInfo newItem) {
            return oldItem.getBuddySipUri().equals(newItem.getBuddySipUri());
        }

        @Override
        public boolean areContentsTheSame(@NonNull BuddyInfo oldItem, @NonNull BuddyInfo newItem) {
            return oldItem.getBuddySipUri().equals(newItem.getBuddySipUri())
                    && oldItem.getBuddyDisplayName().equals(newItem.getBuddyDisplayName())
                    && oldItem.getPresenceStatus().getPresenceStatusType().equals(newItem.getPresenceStatus().getPresenceStatusType())
                    && oldItem.getPresenceStatus().getPresenceStatusText().equals(newItem.getPresenceStatus().getPresenceStatusText());
        }
    };
}

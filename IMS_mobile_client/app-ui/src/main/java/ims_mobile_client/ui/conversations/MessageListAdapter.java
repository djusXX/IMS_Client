package ims_mobile_client.ui.conversations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ims_mobile_client.presentation.models.MessageInfo;
import ims_mobile_client.ui.databinding.MessageFragmentBinding;


public class MessageListAdapter extends ListAdapter<MessageInfo, RecyclerView.ViewHolder> {

    protected MessageListAdapter() {
        super(MessageListAdapter.diffCallback);
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        final MessageFragmentBinding binding;

        public MessageViewHolder(MessageFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageFragmentBinding binding = MessageFragmentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageInfo messageInfo = getItem(position);
        ((MessageViewHolder) holder).binding.setMessageInfo(messageInfo);
    }

    private static final DiffUtil.ItemCallback<MessageInfo> diffCallback = new DiffUtil.ItemCallback<MessageInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull MessageInfo oldItem, @NonNull MessageInfo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageInfo oldItem, @NonNull MessageInfo newItem) {
            return oldItem.getId() == newItem.getId()
                    && oldItem.getSipUriFrom().equals(newItem.getSipUriFrom())
                    && oldItem.getSipUriTo().equals(newItem.getSipUriTo())
                    && oldItem.getTime() == newItem.getTime()
                    && oldItem.getContent().equals(newItem.getContent());
        }
    };
}

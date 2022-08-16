package ims_mobile_client.ui.conversations;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.ui.databinding.MessageFragmentBinding;


public class MessageListAdapter extends ListAdapter<Message, MessageListAdapter.MessageViewHolder> {

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
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageFragmentBinding binding = MessageFragmentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message Message = getItem(position);
        holder.binding.setMessage(Message);
    }

    private static final DiffUtil.ItemCallback<Message> diffCallback = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.getId() == newItem.getId()
                    && oldItem.getSipUriFrom().equals(newItem.getSipUriFrom())
                    && oldItem.getSipUriTo().equals(newItem.getSipUriTo())
                    && oldItem.getTime() == newItem.getTime()
                    && oldItem.getContent().equals(newItem.getContent());
        }
    };
}

package ims_mobile_client.ui.conversations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.ui.databinding.MessageFragmentBinding;


public class MessageListAdapter extends ListAdapter<Message, MessageListAdapter.MessageViewHolder> {
    private String buddySipUri;

    protected MessageListAdapter(String buddySipUri) {
        super(MessageListAdapter.diffCallback);
        this.buddySipUri = buddySipUri;
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
        Message message = getItem(position);
        if (buddySipUri.equals(message.getSipUriFrom())) {
            holder.binding.messageInContentView.setVisibility(View.VISIBLE);
            holder.binding.messageOutContentView.setVisibility(View.GONE);
        } else {
            holder.binding.messageInContentView.setVisibility(View.GONE);
            holder.binding.messageOutContentView.setVisibility(View.VISIBLE);
        }
        holder.binding.setMessage(message);
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

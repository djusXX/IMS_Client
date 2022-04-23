package ims_mobile_client.ui.conversations;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ims_mobile_client.R;
import ims_mobile_client.databinding.MessageFragmentBinding;
import ims_mobile_client.ui.models.Message;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static List<? extends Message> messageList;
    private final String usrSipUri;

    public MessageAdapter(String usrSipUri) {
        setHasStableIds(true);
        this.usrSipUri = usrSipUri;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageFragmentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.message_fragment, parent, false);
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.binding.setIsIncomingMessage(message.isIncoming());
        holder.binding.setMessage(message);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }


    public void setMessages(final List<Message> newMessageList) {
        if (messageList == null) {
            messageList = newMessageList;
            notifyItemRangeChanged(0, newMessageList.size());
            return;
        }

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return messageList.size();
            }

            @Override
            public int getNewListSize() {
                return newMessageList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return messageList.get(oldItemPosition).equals(newMessageList.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Message oldMessage = messageList.get(oldItemPosition);
                Message newMessage = newMessageList.get(newItemPosition);
                return oldMessage.isIncoming() == newMessage.isIncoming()
                        && TextUtils.equals(oldMessage.getTimestamp(), newMessage.getTimestamp())
                        && TextUtils.equals(oldMessage.getContent(), newMessage.getContent());
            }

        });

        messageList = newMessageList;
        result.dispatchUpdatesTo(this);
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        final MessageFragmentBinding binding;

        public MessageViewHolder(MessageFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

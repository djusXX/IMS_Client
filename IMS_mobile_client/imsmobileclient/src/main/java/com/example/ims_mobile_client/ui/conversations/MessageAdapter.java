package com.example.ims_mobile_client.ui.conversations;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.data.entities.MessageEntity;
import com.example.ims_mobile_client.databinding.MessageFragmentBinding;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static List<? extends MessageEntity> messageList;
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
        MessageEntity messageEntity = messageList.get(position);
        holder.binding.setIsIncomingMessage(messageEntity.sip_uri_TO.equals(usrSipUri));
        holder.binding.setMessage(messageEntity);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }

    @Override
    public long getItemId(int position) {
        return messageList.get(position).id;
    }

    public void setMessages(final List<? extends MessageEntity> newMessageList) {
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
                return messageList.get(oldItemPosition).id == newMessageList.get(newItemPosition).id;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                MessageEntity oldMessage = messageList.get(oldItemPosition);
                MessageEntity newMessage = newMessageList.get(newItemPosition);
                return oldMessage.id == newMessage.id
                        && TextUtils.equals(oldMessage.sip_uri_FROM, newMessage.sip_uri_FROM)
                        && TextUtils.equals(oldMessage.sip_uri_TO, newMessage.sip_uri_TO)
                        && TextUtils.equals(oldMessage.content, newMessage.content)
                        && TextUtils.equals(oldMessage.timestamp, newMessage.timestamp);
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

package com.example.ims_mobile_client.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ims_mobile_client.R;
import com.example.ims_mobile_client.utils.MessageType;

import java.util.ArrayList;


public class ConversationAdapter extends RecyclerView.Adapter {
    public static final int VIEW_TYPE_MESSAGE_OUT = 1;
    public static final int VIEW_TYPE_MESSAGE_IN = 2;

    Context context;
    ArrayList<Message> messageList;

    public ConversationAdapter(Context context, ArrayList<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        MessageType type = messageList.get(position).type;

        if(type == MessageType.OUT) {
            return VIEW_TYPE_MESSAGE_OUT;
        }
        if(type == MessageType.IN) {
            return VIEW_TYPE_MESSAGE_IN;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if(viewType == VIEW_TYPE_MESSAGE_OUT) {
            v = LayoutInflater.from(context).inflate(R.layout.message_out_view, parent, false);
            return new MessageOutHolder(v);
        }

        if(viewType == VIEW_TYPE_MESSAGE_IN) {
            v = LayoutInflater.from(context).inflate(R.layout.message_in_view, parent, false);
            return new MessageInHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message msg = messageList.get(position);

        if(holder.getItemViewType() == VIEW_TYPE_MESSAGE_OUT) {
            ((MessageOutHolder) holder).fillWithMessage(msg);
            return;
        }

        if(holder.getItemViewType() == VIEW_TYPE_MESSAGE_IN) {
            ((MessageInHolder) holder).fillWithMessage(msg);
            return;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private class MessageOutHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView text;

        public MessageOutHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.message_out_timestamp);
            text = (TextView) v.findViewById(R.id.message_out_content_text);
        }

        private void fillWithMessage(Message msg) {
            time.setText(msg.getTimeStamp());
            text.setText(msg.text);
        }
    }

    private class MessageInHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView text;

        public MessageInHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.message_in_timestamp);
            text = (TextView) v.findViewById(R.id.message_in_content_text);
        }

        private void fillWithMessage(Message msg) {
            time.setText(msg.getTimeStamp());
            text.setText(msg.text);
        }
    }
}

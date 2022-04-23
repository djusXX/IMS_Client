package ims_mobile_client.data.mappers;

import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.domain.entities.Message;

public class MessageMapper implements Mapper<MessageEntity, Message> {
    @Override
    public Message mapTo(MessageEntity me) {
        return new Message(me.getId(), me.getSipUriFrom(), me.getSipUriTo(), me.getTime(), me.getContent());
    }

    @Override
    public MessageEntity mapFrom(Message m) {
        return new MessageEntity(m.getId(), m.getSipUriFrom(), m.getSipUriTo(), m.getTime(), m.getContent());
    }
}

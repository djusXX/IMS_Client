package ims_mobile_client.data.mappers;

import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.domain.entities.Message;

public class MessageMapper implements Mapper<MessageEntity, Message> {
    @Override
    public Message mapToDomain(MessageEntity me) {
        return new Message(me.getId(), me.getSipUriFrom(), me.getSipUriTo(), me.getTime(), me.getContent());
    }

    @Override
    public MessageEntity mapFromDomain(Message m) {
        return new MessageEntity(m.getId(), m.getSipUriFrom(), m.getSipUriTo(), m.getTime(), m.getContent());
    }
}

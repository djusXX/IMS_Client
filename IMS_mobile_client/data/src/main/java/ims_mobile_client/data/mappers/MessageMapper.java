package ims_mobile_client.data.mappers;

import javax.inject.Inject;

import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.domain.models.Message;

public class MessageMapper implements Mapper<MessageEntity, Message> {

    @Inject
    public MessageMapper() {
        super();
    }
    @Override
    public Message mapToDomain(MessageEntity me) {
        return new Message(me.getId(), me.getSipUriFrom(), me.getSipUriTo(), type, me.getTime(), me.getContent());
    }

    @Override
    public MessageEntity mapFromDomain(Message m) {
        return new MessageEntity(m.getId(), m.getSipUriFrom(), m.getSipUriTo(), m.getTime(), m.getContent());
    }
}

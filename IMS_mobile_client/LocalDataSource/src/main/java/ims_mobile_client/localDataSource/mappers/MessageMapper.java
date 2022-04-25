package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.entities.MessageEntity;
import ims_mobile_client.localDataSource.entities.LocalMessage;

public class MessageMapper implements Mapper<LocalMessage, MessageEntity> {
    @Override
    public MessageEntity mapTo(LocalMessage lm) {
        return new MessageEntity(lm.getId(), lm.getSipUriFrom(), lm.getSipUriTo(),
                lm.getTime(),lm.getContent());
    }

    @Override
    public LocalMessage mapFrom(MessageEntity me) {
        return new LocalMessage(me.getId(), me.getSipUriFrom(), me.getSipUriTo(),
                me.getTime(),me.getContent());
    }
}

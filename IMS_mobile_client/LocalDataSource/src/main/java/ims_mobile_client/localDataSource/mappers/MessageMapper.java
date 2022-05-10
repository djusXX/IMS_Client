package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.localDataSource.models.LocalMessage;

public class MessageMapper implements Mapper<LocalMessage, MessageEntity> {

    public MessageMapper() {
        super();
    }

    @Override
    public MessageEntity mapToEntity(LocalMessage lm) {
        return new MessageEntity(lm.getId(), lm.getSipUriFrom(), lm.getSipUriTo(),
                lm.getTime(),lm.getContent());
    }

    @Override
    public LocalMessage mapFromEntity(MessageEntity me) {
        return new LocalMessage(me.getId(), me.getSipUriFrom(), me.getSipUriTo(),
                me.getTime(),me.getContent());
    }
}

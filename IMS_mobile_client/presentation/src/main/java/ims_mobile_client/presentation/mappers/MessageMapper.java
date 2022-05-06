package ims_mobile_client.presentation.mappers;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.presentation.models.MessageView;

public class MessageMapper implements Mapper<MessageView, Message> {
    @Override
    public MessageView mapToView(Message m) {
        return new MessageView(m.getId(), m.getSipUriFrom(), m.getSipUriTo(),
                m.getTime(), m.getContent());
    }
}

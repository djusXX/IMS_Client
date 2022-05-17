package ims_mobile_client.data.mappers;

import javax.inject.Inject;

public class MapperProvider {
    private final BuddyMapper buddyMapper;
    private final CallMapper callMapper;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    @Inject
    public MapperProvider(BuddyMapper buddyMapper, CallMapper callMapper, MessageMapper messageMapper, UserMapper userMapper) {
        this.buddyMapper = buddyMapper;
        this.callMapper = callMapper;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }


    public BuddyMapper forBuddy() {
        return buddyMapper;
    }

    public CallMapper forCall() {
        return callMapper;
    }

    public MessageMapper forMessage() {
        return messageMapper;
    }

    public UserMapper forUser() {
        return userMapper;
    }
}

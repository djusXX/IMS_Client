package ims_mobile_client.localDataSource.mappers;

public class MapperProvider {
    private final BuddyMapper buddyMapper;
    private final CallMapper callMapper;
    private final MessageMapper messageMapper;
    private final UserMapper userMapper;

    public MapperProvider(BuddyMapper buddyMapper, CallMapper callMapper, MessageMapper messageMapper, UserMapper userMapper) {
        this.buddyMapper = buddyMapper;
        this.callMapper = callMapper;
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
    }


    public BuddyMapper getBuddyMapper() {
        return buddyMapper;
    }

    public CallMapper getCallMapper() {
        return callMapper;
    }

    public MessageMapper getMessageMapper() {
        return messageMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }
}

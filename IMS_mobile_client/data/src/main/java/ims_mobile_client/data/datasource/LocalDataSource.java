package ims_mobile_client.data.datasource;



import java.util.List;


public interface LocalDataSource {

    UserEntity getUser(String usrSpiUri);
    UserEntity getLastUser();
    void addUser(UserEntity userEntity);

    List<BuddyEntity> getAllBuddies();
    List<BuddyEntity> getBuddiesFor(String userSipUri);
    BuddyEntity getBuddy(String usrSipUri, String buddySipUri);
    void addBuddy(BuddyEntity buddyEntity);

    List<MessageEntity> getAllMessages();
    List<MessageEntity> getMessagesFor(String userSipUri);
    List<MessageEntity> getMessagesFor(String usrSipUri, String buddySipUri);
    void addMessage(MessageEntity message);

    List<CallEntity> getAllCalls();
    List<CallEntity> getCallsFor(String userSipUri);
    List<CallEntity> getCallsFor(String usrSipUri, String buddySipUri);
    void saveCall(CallEntity call);

}

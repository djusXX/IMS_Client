package ims_mobile_client.data;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.dataStores.DataStoreFactory;
import ims_mobile_client.data.mappers.MapperProvider;
import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.data.sip.SIPManager;
import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.models.UserRegistrationStatus;
import ims_mobile_client.domain.repository.IMSRepository;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class IMSRepositoryImpl implements IMSRepository {

    private final DataStoreFactory dataStore;
    private final MapperProvider mapper;
    private final SIPManager sipManager;

    private String userSipUri;

    @Inject
    public IMSRepositoryImpl(DataStoreFactory dataStore, MapperProvider mapper, SIPManager sipManager) {
        this.dataStore = dataStore;
        this.mapper = mapper;
        this.sipManager = sipManager;
    }

    @Override
    public Flowable<User> getLastUser() {
        return dataStore.getDefault().getLastUser()
                .map(mapper.forUser()::mapToDomain);
    }

    @Override
    public Flowable<User> getUser(String userSipUri) {
        return dataStore.getDefault().getUser(userSipUri)
                .map(mapper.forUser()::mapToDomain);
    }

    @Override
    public Completable saveUser(User user) {
        return dataStore.getDefault().addUser(mapper.forUser().mapFromDomain(user));
    }

    @Override
    public Flowable<List<Buddy>> getBuddyList() {
        return dataStore.getDefault().getBuddiesFor(userSipUri)
                .concatMap(Flowable::fromIterable)
                .map(mapper.forBuddy()::mapToDomain)
                .toList()
                .toFlowable();
    }

    @Override
    public Flowable<Buddy> getBuddy(String userSipUri, String buddySipUri) {
        return dataStore.getDefault().getBuddy(userSipUri, buddySipUri)
                .map(mapper.forBuddy()::mapToDomain);
    }

    @Override
    public Completable saveBuddy(String buddySipUri, String buddyDisplayName) {
        BuddyEntity buddyEntity = new BuddyEntity(1, userSipUri, buddySipUri, buddyDisplayName);
        return dataStore.getDefault().addBuddy(buddyEntity);
    }

    @Override
    public Flowable<List<Message>> getMessagesFor(String buddySipUri) {
        return dataStore.getDefault().getMessagesFor(userSipUri, buddySipUri)
                .concatMap(Flowable::fromIterable)
                .map(mapper.forMessage()::mapToDomain)
                .toList()
                .toFlowable();
    }

    @Override
    public Completable saveMessage(Message message) {
        return dataStore.getDefault().addMessage(mapper.forMessage().mapFromDomain(message));
    }

    @Override
    public Flowable<List<Call>> getCallsFor(String usrSipUri, String buddySipUri) {
        return dataStore.getDefault().getCallsFor(usrSipUri, buddySipUri)
                .concatMap(Flowable::fromIterable)
                .map(mapper.forCall()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Completable saveCall(Call call) {
        return dataStore.getDefault().addCall(mapper.forCall().mapFromDomain(call));
    }

    @Override
    public Flowable<UserRegistrationStatus> getRegistrationState() {
        return sipManager.getRegistrationState();
    }

    @Override
    public Flowable<PresenceStatus> getUserPresenceState() {
        return sipManager.getUserPresenceState();
    }

    @Override
    public Flowable<Message> getIncomingMessage(String usrSipUri) {
        return sipManager.getIncomingMessageForUser(usrSipUri);
    }

    @Override
    public Flowable<Call> getCurrentCall() {
        return sipManager.getCurrentCall(userSipUri);
    }

    @Override
    public Flowable<String> getLoggedUserSipUri() {
        return sipManager.getLoggedUserSipUri();
    }

    @Override
    public Completable registerUser(User u) {
        userSipUri = u.getSipUri();
        return sipManager.registerUser(u);
    }

    @Override
    public Completable updateUserPresence(PresenceStatus presenceStatus) {
        return sipManager.updateUserPresence(presenceStatus);
    }

    @Override
    public Completable addNewBuddy(String buddySipUri, String buddyDisplayName) {
        saveBuddy(buddySipUri, buddyDisplayName);
        return sipManager.addNewBuddy(buddySipUri, buddyDisplayName);
    }

    @Override
    public Completable sendMessage(String buddySipUri, String content) {
        return null;
    }

    @Override
    public Completable makeCall(String buddySipUri, boolean isVideo) {
        return null;
    }
}

package ims_mobile_client.data;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.dataStores.DataStoreFactory;
import ims_mobile_client.data.mappers.MapperProvider;
import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.repository.IMSRepository;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class IMSRepositoryImpl implements IMSRepository {

    private final DataStoreFactory dataStore;
    private final MapperProvider mapper;

    @Inject
    public IMSRepositoryImpl(DataStoreFactory dataStore, MapperProvider mapper) {
        this.dataStore = dataStore;
        this.mapper = mapper;
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
    public Completable addUser(User user) {
        return dataStore.getDefault().addUser(mapper.forUser().mapFromDomain(user));
    }

    @Override
    public Flowable<List<Buddy>> getBuddiesFor(String userSipUri) {
        return dataStore.getDefault().getBuddiesFor(userSipUri)
                .concatMap(Flowable::fromIterable)
                .map(mapper.forBuddy()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Flowable<Buddy> getBuddy(String userSipUri, String buddySipUri) {
        return dataStore.getDefault().getBuddy(userSipUri, buddySipUri)
                .map(mapper.forBuddy()::mapToDomain);
    }

    @Override
    public Completable addBuddy(Buddy buddy) {
        return dataStore.getDefault().addBuddy(mapper.forBuddy().mapFromDomain(buddy));
    }

    @Override
    public Flowable<List<Message>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return dataStore.getDefault().getMessagesFor(usrSipUri, buddySipUri)
                .concatMap(Flowable::fromIterable)
                .map(mapper.forMessage()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Completable addMessage(Message message) {
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
    public Completable addCall(Call call) {
        return dataStore.getDefault().addCall(mapper.forCall().mapFromDomain(call));
    }
}

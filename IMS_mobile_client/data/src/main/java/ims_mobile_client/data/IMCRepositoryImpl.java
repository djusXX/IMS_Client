package ims_mobile_client.data;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.mappers.MapperFactory;
import ims_mobile_client.data.mappers.UserMapper;
import ims_mobile_client.data.stores.DataStoreFactory;
import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.entities.User;
import ims_mobile_client.domain.repository.IMCRepository;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class IMCRepositoryImpl implements IMCRepository {

    private final DataStoreFactory dataStores;
    private final MapperFactory mappers;

    @Inject
    public IMCRepositoryImpl(DataStoreFactory dataStores, MapperFactory mappers) {
        this.dataStores = dataStores;
        this.mappers = mappers;
    }


    @Override
    public Flowable<User> getLastUser() {
        return dataStores.getDefault().getLastUser()
                .map(mappers.getUserMapper()::mapToDomain);
    }

    @Override
    public Flowable<User> getUser(String userSipUri) {
        return dataStores.getDefault().getUser(userSipUri)
                .map(mappers.getUserMapper()::mapToDomain);
    }

    @Override
    public Completable addUser(User user) {
        return dataStores.getDefault().addUser(mappers.getUserMapper().mapFromDomain(user));
    }

    @Override
    public Flowable<List<Buddy>> getBuddiesFor(String userSipUri) {
        return dataStores.getDefault().getBuddiesFor(userSipUri)
                .concatMap(Flowable::fromIterable)
                .map(mappers.getBuddyMapper()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Flowable<Buddy> getBuddy(String userSipUri, String buddySipUri) {
        return dataStores.getDefault().getBuddy(userSipUri, buddySipUri)
                .map(mappers.getBuddyMapper()::mapToDomain);
    }

    @Override
    public Completable addBuddy(Buddy buddy) {
        return dataStores.getDefault().addBuddy(mappers.getBuddyMapper().mapFromDomain(buddy));
    }

    @Override
    public Flowable<List<Message>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return dataStores.getDefault().getMessagesFor(usrSipUri, buddySipUri)
                .concatMap(Flowable::fromIterable)
                .map(mappers.getMessageMapper()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Completable addMessage(Message message) {
        return dataStores.getDefault().addMessage(mappers.getMessageMapper().mapFromDomain(message));
    }

    @Override
    public Flowable<List<Call>> getCallsFor(String usrSipUri, String buddySipUri) {
        return dataStores.getDefault().getCallsFor(usrSipUri, buddySipUri)
                .concatMap(Flowable::fromIterable)
                .map(mappers.getCallMapper()::mapToDomain)
                .toList().toFlowable();
    }

    @Override
    public Completable addCall(Call call) {
        return dataStores.getDefault().addCall(mappers.getCallMapper().mapFromDomain(call));
    }
}

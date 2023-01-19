package ims_mobile_client.localStorage;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.data.dataSources.LocalDataSource;
import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.data.models.MessageEntity;
import ims_mobile_client.data.models.UserEntity;
import ims_mobile_client.localStorage.db.LocalDatabase;
import ims_mobile_client.localStorage.mappers.MapperProvider;
import ims_mobile_client.localStorage.models.LocalBuddy;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalDataSourceImpl implements LocalDataSource {
    public static final String TAG = LocalDataSourceImpl.class.getName();

    private final LocalDatabase db;
    private final MapperProvider mappers;

    @Inject
    public LocalDataSourceImpl(LocalDatabase db, MapperProvider mappers) {
        this.db = db;
        this.mappers = mappers;
    }


    @Override
    public Flowable<UserEntity> getUser(String usrSipUri) {
        return Flowable.defer(() -> Flowable.just(db.userDao().getUser(usrSipUri)))
                .map(mappers.getUserMapper()::mapToEntity);
    }

    @Override
    public Flowable<UserEntity> getLastUser() {
        return Flowable.defer(() -> Flowable.just(db.userDao().getLastLoggedUser()))
                .map(mappers.getUserMapper()::mapToEntity);
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
//        return db.userDao().insert(mappers.getUserMapper().mapFromEntity(userEntity));
        return Completable.defer(() -> {
            db.userDao().insert(mappers.getUserMapper().mapFromEntity(userEntity));
            return Completable.complete();
        });
    }

    @Override
    public Flowable<List<BuddyEntity>> getBuddiesFor(String userSipUri) {
        return Flowable.defer(() -> Flowable.just(db.buddyDao().getBuddiesFor(userSipUri)))
                .concatMap(Flowable::fromIterable)
                .map(mappers.getBuddyMapper()::mapToEntity)
                .toList()
                .toFlowable();
    }

    @Override
    public Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri) {
        return Flowable.defer(() -> Flowable.just(db.buddyDao().getBuddy(usrSipUri, buddySipUri)))
                .map(mappers.getBuddyMapper()::mapToEntity);
    }

    @Override
    public Completable addBuddy(BuddyEntity buddyEntity) {
        LocalBuddy localBuddy = mappers.getBuddyMapper().mapFromEntity(buddyEntity);
        Log.d(TAG, "Inserting buddy to table by dao. " + localBuddy.getId() + "|" + localBuddy.getBuddySipUri());
        return db.buddyDao().insert(localBuddy);

//        return Completable.defer(() -> {
//            LocalBuddy localBuddy = mappers.getBuddyMapper().mapFromEntity(buddyEntity);
//            db.buddyDao().insert(localBuddy);
//            return Completable.complete();
//        });
    }

    @Override
    public Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return Flowable.defer(() -> Flowable.just(db.messageDao().getMessagesFor(usrSipUri, buddySipUri)))
                .concatMap(Flowable::fromIterable)
                .map(mappers.getMessageMapper()::mapToEntity)
                .toList().toFlowable();
    }

    @Override
    public Completable addMessage(MessageEntity message) {
        return Completable.defer(() -> {
            db.messageDao().insert(mappers.getMessageMapper().mapFromEntity(message));
            return Completable.complete();
        });
    }

    @Override
    public Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri) {
        return Flowable.defer(() -> Flowable.just(db.callDao().getCallsFor(usrSipUri, buddySipUri)))
                .concatMap(Flowable::fromIterable)
                .map(mappers.getCallMapper()::mapToEntity)
                .toList().toFlowable();
    }

    @Override
    public Completable addCall(CallEntity call) {
        return Completable.defer(() -> {
            db.callDao().insert(mappers.getCallMapper().mapFromEntity(call));
            return Completable.complete();
        });
    }
}

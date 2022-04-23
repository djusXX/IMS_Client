package ims_mobile_client.data;

import java.util.List;

import ims_mobile_client.data.repository.dataLocal;
import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.entities.User;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public class ImsMobileClientRepositoryImpl implements ImsMobileClientRepository {

    private final dataLocal dataSource;

    public ImsMobileClientRepositoryImpl(dataLocal dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Flowable<User> getLastUser() {
        return null;
    }

    @Override
    public Completable addUser(User user) {
        return null;
    }

    @Override
    public Flowable<List<Buddy>> getBuddiesFor(String userSipUri) {
        return null;
    }

    @Override
    public Completable addBuddy(Buddy buddy) {
        return null;
    }

    @Override
    public Flowable<List<Message>> getMessagesFor(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable addMessage(Message message) {
        return null;
    }

    @Override
    public Flowable<List<Call>> getCallsFor(String usrSipUri, String buddySipUri) {
        return null;
    }

    @Override
    public Completable addCall(Call call) {
        return null;
    }
}

package ims_mobile_client.domain.service;

import java.util.List;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.models.User;
import io.reactivex.Flowable;

public interface IMSService {

    Flowable<List<User>> getUsers();
    Flowable<List<Call>> getCalls();
    Flowable<List<Buddy>> getBuddies();
    Flowable<List<Message>> getMessages();

    Flowable<User> getUserData(int id);
    Flowable<Call> getCallData(int id);
    Flowable<Buddy> getBuddyData(int id);
    Flowable<Message> getMessageData(int id);

    Flowable<User> setUser(User user);
    Flowable<Call> setCall(Call call);
    Flowable<Buddy> setBuddy(Buddy buddy);
    Flowable<Message> setMessageData(Message message);
}

package ims_mobile_client.data.mappers;

import javax.inject.Inject;

import ims_mobile_client.data.models.UserEntity;
import ims_mobile_client.domain.models.User;

public class UserMapper implements Mapper<UserEntity, User> {

    @Inject
    public UserMapper() {
        super();
    }

    @Override
    public User mapToDomain(UserEntity ue) {
        return new User(ue.getName(), ue.getPassword(), ue.getDisplayName(),
                ue.getRealm(), ue.getPcscf());
    }

    @Override
    public UserEntity mapFromDomain(User u) {
        return new UserEntity(u.getId(), u.getPassword(), u.getName(),
                u.getDisplayName(), u.getRealm(),
                u.getPcscf(), -1);
    }
}

package ims_mobile_client.data.mappers;

import ims_mobile_client.data.models.UserEntity;
import ims_mobile_client.domain.entities.User;

public class UserMapper implements Mapper<UserEntity, User> {
    @Override
    public User mapToDomain(UserEntity ue) {
        return new User(ue.getId(), ue.getName(),
                ue.getDisplayName(), ue.getRealm(),
                ue.getPcscf(), ue.getLastLogged());
    }

    @Override
    public UserEntity mapFromDomain(User u) {
        return new UserEntity(u.getId(), u.getName(),
                u.getDisplayName(), u.getRealm(),
                u.getPcscf(), u.getLastLogged());
    }
}

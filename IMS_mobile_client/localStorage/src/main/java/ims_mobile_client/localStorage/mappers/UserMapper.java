package ims_mobile_client.localStorage.mappers;

import javax.inject.Inject;

import ims_mobile_client.data.models.UserEntity;
import ims_mobile_client.localStorage.models.LocalUser;

public class UserMapper implements Mapper<LocalUser, UserEntity> {

    @Inject
    public UserMapper() {
        super();
    }

    @Override
    public UserEntity mapToEntity(LocalUser lu) {
        return new UserEntity(lu.getId(), lu.getPassword(), lu.getDisplayName(), lu.getUserName(),
                lu.getRealm(), lu.getPcscf(), lu.getLastLogged());
    }

    @Override
    public LocalUser mapFromEntity(UserEntity ue) {
        return new LocalUser(ue.getId(), ue.getDisplayName(), ue.getName(),
                ue.getPassword(), ue.getRealm(), ue.getPcscf(), ue.getLastLogged());
    }
}

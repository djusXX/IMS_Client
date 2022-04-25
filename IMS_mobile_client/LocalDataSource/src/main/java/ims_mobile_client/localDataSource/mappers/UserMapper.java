package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.entities.UserEntity;
import ims_mobile_client.localDataSource.entities.LocalUser;

public class UserMapper implements Mapper<LocalUser, UserEntity> {
    @Override
    public UserEntity mapTo(LocalUser lu) {
        return new UserEntity(lu.getId(), lu.getDisplayName(), lu.getUserName(),
                lu.getRealm(), lu.getPcscf(), lu.getLastLogged());
    }

    @Override
    public LocalUser mapFrom(UserEntity ue) {
        return new LocalUser(ue.getId(), ue.getDisplayName(), ue.getName(),
                ue.getRealm(), ue.getPcscf(), ue.getLastLogged());
    }
}

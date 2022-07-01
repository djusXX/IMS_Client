package ims_mobile_client.presentation.mappers;

import javax.inject.Inject;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.presentation.models.UserView;

public class UserMapper implements Mapper<UserView, User> {

    @Inject
    public UserMapper() {}

    @Override
    public UserView mapToView(User u) {
        return new UserView(u.getId(), u.getName(), u.getPassword(), u.getDisplayName(), u.getRealm(),
                u.getPcscf(),0);
    }
}

package ims_mobile_client.presentation.mappers;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.presentation.models.UserView;

public class UserMapper implements Mapper<UserView, User> {
    @Override
    public UserView mapToView(User u) {
        return new UserView(u.getId(), u.getName(), u.getDisplayName(), u.getRealm(),
                u.getPcscf(), u.getLastLogged(), u.getRegStatus(), u.getUserStatusType(),
                u.getUserStatusActivity(), u.getUserStatusText(), u.getNote(), u.getRpidId());
    }
}
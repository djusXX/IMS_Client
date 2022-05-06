package ims_mobile_client.presentation.mappers;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.presentation.models.BuddyView;

public class BuddyMapper implements Mapper<BuddyView, Buddy> {
    @Override
    public BuddyView mapToView(Buddy d) {
        return new BuddyView(d.getId(), d.getUsrSipUri(), d.getBuddySipUri(),
                d.getBuddyDisplayName(), d.getBuddyStatusType(), d.getBuddyStatusActivity(),
                d.getBuddyStatusText(), d.getNote(), d.getRpidId());
    }
}

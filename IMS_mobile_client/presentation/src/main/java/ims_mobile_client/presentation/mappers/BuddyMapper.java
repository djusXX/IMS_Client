package ims_mobile_client.presentation.mappers;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.presentation.models.BuddyView;

public class BuddyMapper implements Mapper<BuddyView, Buddy> {

    @Inject
    public BuddyMapper() {}

    @Override
    public BuddyView mapToView(Buddy d) {
        return new BuddyView(d.getUsrSipUri(), d.getBuddySipUri(),
                d.getBuddyDisplayName());
    }
}

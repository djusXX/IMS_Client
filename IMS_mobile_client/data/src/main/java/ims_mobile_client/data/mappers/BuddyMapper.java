package ims_mobile_client.data.mappers;

import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.domain.entities.Buddy;

public class BuddyMapper implements Mapper<BuddyEntity, Buddy> {
    @Override
    public Buddy mapToDomain(BuddyEntity be) {
        return new Buddy(be.getId(), be.getUserSipUri(), be.getBuddySipUri(), be.getBuddyDisplayName());
    }

    @Override
    public BuddyEntity mapFromDomain(Buddy b) {
        return new BuddyEntity(b.getId(), b.getUsrSipUri(), b.getBuddySipUri(), b.getBuddyDisplayName());
    }
}

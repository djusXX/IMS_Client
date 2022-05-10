package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.models.BuddyEntity;
import ims_mobile_client.localDataSource.models.LocalBuddy;

public class BuddyMapper implements Mapper<LocalBuddy, BuddyEntity>{

    public BuddyMapper() {
        super();
    }

    @Override
    public BuddyEntity mapToEntity(LocalBuddy lb) {
        return new BuddyEntity(lb.getId(), lb.getUserSipUri(),
                lb.getBuddySipUri(), lb.getBuddyDisplayName());
    }

    @Override
    public LocalBuddy mapFromEntity(BuddyEntity be) {
        return new LocalBuddy(be.getId(), be.getUserSipUri(),
                be.getBuddySipUri(), be.getBuddyDisplayName());
    }
}

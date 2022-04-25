package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.entities.BuddyEntity;
import ims_mobile_client.localDataSource.entities.LocalBuddy;

public class BuddyMapper implements Mapper<LocalBuddy, BuddyEntity>{
    @Override
    public BuddyEntity mapTo(LocalBuddy lb) {
        return new BuddyEntity(lb.getId(), lb.getUserSipUri(),
                lb.getBuddySipUri(), lb.getBuddyDisplayName());
    }

    @Override
    public LocalBuddy mapFrom(BuddyEntity be) {
        return new LocalBuddy(be.getId(), be.getUserSipUri(),
                be.getBuddySipUri(), be.getBuddyDisplayName());
    }
}

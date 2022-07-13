package ims_mobile_client.data.mappers;

import javax.inject.Inject;

import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.domain.models.Call;

public class CallMapper implements Mapper<CallEntity, Call> {

    @Inject
    public CallMapper() {
        super();
    }

    @Override
    public Call mapToDomain(CallEntity ce) {
        return new Call(ce.getId(), ce.getSipUriFROM(),
                ce.getSipUriTO(), ce.isVideo());
    }

    @Override
    public CallEntity mapFromDomain(Call c) {
        return new CallEntity(c.getId(), c.getSipUriFrom(),
                c.getSipUriTo(), c.isVideoCall(),
                c.getBeginTime(), c.getEndTime());
    }
}

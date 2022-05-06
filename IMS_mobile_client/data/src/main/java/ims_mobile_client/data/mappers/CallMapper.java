package ims_mobile_client.data.mappers;

import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.domain.entities.Call;

public class CallMapper implements Mapper<CallEntity, Call> {
    @Override
    public Call mapToDomain(CallEntity ce) {
        return new Call(ce.getId(), ce.getSipUriFROM(),
                ce.getSipUriTO(), ce.isVideo(),
                ce.getBeginTime(), ce.getEndTime());
    }

    @Override
    public CallEntity mapFromDomain(Call c) {
        return new CallEntity(c.getId(), c.getSipUriFrom(),
                c.getSipUriTo(), c.isVideo(),
                c.getBeginTime(), c.getEndTime());
    }
}

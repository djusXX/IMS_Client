package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.entities.CallEntity;
import ims_mobile_client.localDataSource.entities.LocalCall;

public class CallMapper implements Mapper<LocalCall, CallEntity> {
    @Override
    public CallEntity mapTo(LocalCall lc) {
        return new CallEntity(lc.getId(), lc.getSipUriFROM(), lc.getSipUriTO(),
                lc.isVideo(), lc.getBeginTime(), lc.getEndTime());
    }

    @Override
    public LocalCall mapFrom(CallEntity ce) {
        return new LocalCall(ce.getId(), ce.getSipUriFROM(), ce.getSipUriTO(),
                ce.isVideo(), ce.getBeginTime(), ce.getEndTime());
    }
}

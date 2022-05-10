package ims_mobile_client.localDataSource.mappers;

import ims_mobile_client.data.models.CallEntity;
import ims_mobile_client.localDataSource.models.LocalCall;

public class CallMapper implements Mapper<LocalCall, CallEntity> {

    public CallMapper() {
        super();
    }

    @Override
    public CallEntity mapToEntity(LocalCall lc) {
        return new CallEntity(lc.getId(), lc.getSipUriFROM(), lc.getSipUriTO(),
                lc.isVideo(), lc.getBeginTime(), lc.getEndTime());
    }

    @Override
    public LocalCall mapFromEntity(CallEntity ce) {
        return new LocalCall(ce.getId(), ce.getSipUriFROM(), ce.getSipUriTO(),
                ce.isVideo(), ce.getBeginTime(), ce.getEndTime());
    }
}

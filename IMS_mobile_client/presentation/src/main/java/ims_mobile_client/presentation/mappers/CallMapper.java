package ims_mobile_client.presentation.mappers;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.presentation.models.CallView;

public class CallMapper implements Mapper<CallView, Call> {

    @Inject
    public CallMapper() {}

    @Override
    public CallView mapToView(Call c) {
        return new CallView(c.getId(), c.getSipUriFrom(), c.getSipUriTo(), c.isVideo(),
                c.getBeginTime(), c.getEndTime(), c.isActive(), c.getStateType(),
                c.getStateText(), c.getStatusCode(), c.getStatusText());
    }
}

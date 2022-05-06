package ims_mobile_client.presentation.mappers;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.presentation.models.CallView;

public class CallMapper implements Mapper<CallView, Call> {
    @Override
    public CallView mapToView(Call c) {
        return new CallView(c.getId(), c.getSipUriFrom(), c.getSipUriTo(), c.isVideo(),
                c.getBeginTime(), c.getEndTime(), c.isActive(), c.getStateType(),
                c.getStateText(), c.getStatusCode(), c.getStatusText());
    }
}

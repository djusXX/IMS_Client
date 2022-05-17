package ims_mobile_client.presentation.mappers;

public interface Mapper<View, Domain> {

    View mapToView(Domain d);
}

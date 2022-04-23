package ims_mobile_client.data.mappers;

public interface Mapper<Data, Domain> {
    Domain mapTo(Data data);
    Data mapFrom(Domain domain);
}

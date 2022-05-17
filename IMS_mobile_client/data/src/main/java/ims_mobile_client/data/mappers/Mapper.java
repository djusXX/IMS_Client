package ims_mobile_client.data.mappers;

public interface Mapper<Data, Domain> {
    Domain mapToDomain(Data data);
    Data mapFromDomain(Domain domain);
}

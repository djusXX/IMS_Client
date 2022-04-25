package ims_mobile_client.localDataSource.mappers;

public interface Mapper<LocalData, Data> {
    Data mapTo(LocalData data);
    LocalData mapFrom(Data domain);
}

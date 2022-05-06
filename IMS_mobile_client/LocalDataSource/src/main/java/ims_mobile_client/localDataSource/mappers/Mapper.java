package ims_mobile_client.localDataSource.mappers;

public interface Mapper<LocalData, Entity> {
    Entity mapToEntity(LocalData data);
    LocalData mapFromEntity(Entity domain);
}

package ims_mobile_client.localStorage.mappers;

public interface Mapper<LocalData, Entity> {
    Entity mapToEntity(LocalData data);
    LocalData mapFromEntity(Entity domain);
}

package ims_mobile_client.data.source;

import javax.inject.Inject;

import ims_mobile_client.data.repository.DefaultDataStore;

public class DefaultDataStoreFactory {
    private final LocalDataStore localDataStore;


    @Inject
    public DefaultDataStoreFactory(LocalDataStore localDataStore) {
        this.localDataStore = localDataStore;
    }

    public DefaultDataStore getDefaultDataStore() {
        /**
         * For now only one data store, but it is possible to add more data stores, eg. remote data store.
         * */
        return getLocalDataStore();
    }

    public LocalDataStore getLocalDataStore() {
        return localDataStore;
    }
}

package ims_mobile_client.data.stores;

import javax.inject.Inject;

public class DataStoreFactory {
    private final DataStoreLocal dataStoreLocal;

    @Inject
    public DataStoreFactory(DataStoreLocal dataStoreLocal) {
        this.dataStoreLocal = dataStoreLocal;
    }

    public DataStore getDefault() {
        return getLocal();
    }

    public DataStoreLocal getLocal() {
        return dataStoreLocal;
    }


//    // For now, there is only one type of data store, but it is possible to add more.
//    // eg. remote data store.
//    public IMCRemoteDataStore getRemote() { return null; }
}

package ims_mobile_client.data.dataStores;


import javax.annotation.Nullable;
import javax.inject.Inject;

public class DataStoreFactory {
    private final DataStoreLocal dataStoreLocal;
    private final DataStoreRemote dataStoreRemote;

    @Inject
    public DataStoreFactory(DataStoreLocal dataStoreLocal, @Nullable DataStoreRemote dataStoreRemote) {
        this.dataStoreLocal = dataStoreLocal;
        this.dataStoreRemote = dataStoreRemote;
    }

    public DataStore getDefault() {
        return getLocal();
    }

    public DataStore getLocal() {
        return dataStoreLocal;
    }

    public DataStore getRemote() {
        return dataStoreRemote;
    }

}

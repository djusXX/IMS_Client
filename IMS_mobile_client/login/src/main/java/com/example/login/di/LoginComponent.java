package com.example.login.di;


import android.content.Context;

import com.example.ims_mobile_client.di.LoginModuleDependencies;
import com.example.login.view.LoginFragment;

import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = LoginModuleDependencies.class)
public interface LoginComponent {

    void inject(LoginFragment loginFragment);

    @Component.Builder
    interface Builder {
        Builder context(@BindsInstance Context context);
        Builder appDependencies(LoginModuleDependencies loginModuleDependencies);
        LoginComponent build();
    }

}

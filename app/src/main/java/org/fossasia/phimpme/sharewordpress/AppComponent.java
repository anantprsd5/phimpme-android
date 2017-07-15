package org.fossasia.phimpme.sharewordpress;

import org.fossasia.phimpme.MyApplication;
import org.wordpress.android.fluxc.module.AppContextModule;
import org.wordpress.android.fluxc.module.ReleaseBaseModule;
import org.wordpress.android.fluxc.module.ReleaseNetworkModule;
import org.wordpress.android.fluxc.module.ReleaseOkHttpClientModule;
import org.wordpress.android.fluxc.module.ReleaseStoreModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anant on 15/7/17.
 */

@Singleton
@Component(modules = {
        AppContextModule.class,
        AppSecretsModule.class,
        ReleaseOkHttpClientModule.class,
        ReleaseBaseModule.class,
        ReleaseNetworkModule.class,
        ReleaseStoreModule.class
})
public interface AppComponent {
    void inject(MyApplication object);
    void inject(WordpressLoginActivity object);

}

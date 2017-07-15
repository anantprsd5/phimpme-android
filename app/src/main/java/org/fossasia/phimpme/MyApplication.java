package org.fossasia.phimpme;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.yarolegovich.wellsql.WellSql;

import org.fossasia.phimpme.leafpic.data.Album;
import org.fossasia.phimpme.leafpic.data.HandlingAlbums;
import org.fossasia.phimpme.sharewordpress.AppComponent;
import org.fossasia.phimpme.sharewordpress.DaggerAppComponent;
import org.wordpress.android.fluxc.module.AppContextModule;
import org.wordpress.android.fluxc.persistence.WellSqlConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by dnld on 28/04/16.
 */
public class MyApplication extends Application {

    private HandlingAlbums albums = null;
    public static Context applicationContext;

    protected AppComponent mComponent;

    public Album getAlbum() {
        return albums.dispAlbums.size() > 0 ? albums.getCurrentAlbum() : Album.getEmptyAlbum();
    }

    @Override
    public void onCreate() {

        albums = new HandlingAlbums(getApplicationContext());
        applicationContext = getApplicationContext();

        MultiDex.install(this);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.twitter_CONSUMER_KEY), getString(R.string.twitter_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        /**
         * Realm initialization
         */
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("phimpme.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        super.onCreate();

        /**
         * Stetho initialization
         */
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        super.onCreate();
        initDaggerComponent();
        component().inject(this);
        WellSql.init(new WellSqlConfig(getApplicationContext()));
    }

    public AppComponent component() {
        return mComponent;
    }

    protected void initDaggerComponent() {
        mComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(getApplicationContext()))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public HandlingAlbums getAlbums() {
        return albums;
    }

    public void setAlbums(HandlingAlbums albums) {
        this.albums = albums;
    }

    public void updateAlbums() {
        albums.loadAlbums(getApplicationContext());
    }
}
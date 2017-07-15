package org.fossasia.phimpme.sharewordpress;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fossasia.phimpme.MyApplication;
import org.fossasia.phimpme.R;
import org.fossasia.phimpme.base.ThemedActivity;
import org.fossasia.phimpme.leafpic.util.ThemeHelper;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AuthenticationActionBuilder;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.util.AppLog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordpressLoginActivity extends ThemedActivity{

    ThemeHelper themeHelper;
    @BindView(R.id.email)
    EditText user_name;
    @BindView(R.id.password)
    EditText user_password;
    Button login_button;
    View parent;
    Toolbar toolbar;

    protected @Inject
    Dispatcher mDispatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).component().inject(this);
        setContentView(R.layout.activity_wordpress_login);
        themeHelper = new ThemeHelper(this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sharewordpress);
        toolbar.setBackgroundColor(themeHelper.getPrimaryColor());
        parent = findViewById(R.id.wordpress_login);
        setSupportActionBar(toolbar);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(AccountStore.OnAccountChanged event) {
        if (event.isError()) {
            AppLog.e(AppLog.T.API, "onAccountChanged has error: " + event.error.type + " - " + event.error.message);
            return;
        }

        AppLog.i(AppLog.T.NUX, "onAccountChanged: " + event.toString());

        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

        // Success


        // Finish activity if sites have been fetched
        }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAuthenticationChanged(AccountStore.OnAuthenticationChanged event) {
        Toast.makeText(getApplicationContext(),"ASfsf",Toast.LENGTH_SHORT).show();
        if (event.isError()) {
            AppLog.e(AppLog.T.API, "onAuthenticationChanged has error: " + event.error.type + " - " + event.error.message);

            return;
        }

        AppLog.i(AppLog.T.NUX, "onAuthenticationChanged: " + event.toString());
    }

    public void signIn(){
        String mUsername = user_name.getText().toString().trim().toLowerCase();
        String mPassword = user_password.getText().toString().trim();
        AccountStore.AuthenticatePayload payload = new AccountStore.AuthenticatePayload(mUsername, mPassword);
        mDispatcher.dispatch(AuthenticationActionBuilder.newAuthenticateAction(payload));
    }

    @Override
    public void onStart() {
        super.onStart();
        mDispatcher.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mDispatcher.unregister(this);
    }

}
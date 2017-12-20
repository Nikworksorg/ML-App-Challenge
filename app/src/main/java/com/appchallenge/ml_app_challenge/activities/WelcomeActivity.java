package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.presenters.WelcomePresenter;
import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class WelcomeActivity extends BaseActivity implements WelcomeMvpView {

    private WelcomePresenter mWelcomePresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Timber.i("Presented Welcome Activity");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(R.string.welcome_activity_title));

        DataManager dataManager = DataManager.getInstance(this);

        mWelcomePresenter = new WelcomePresenter(this, dataManager);
        mWelcomePresenter.onAttach(this);
        mWelcomePresenter.computeNextActivity();
    }

    @Override
    public void openAccountActivity() {
        Intent intent = AccountActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.open_button) void onOpenButtonClick() {
        openAccountActivity();
    }



}
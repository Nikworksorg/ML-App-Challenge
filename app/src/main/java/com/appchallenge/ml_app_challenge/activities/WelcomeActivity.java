package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.presenters.LaunchPresenter;
import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class WelcomeActivity extends BaseActivity implements WelcomeMvpView {

    LaunchPresenter mLaunchPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Timber.i("Presented Launch Activity");

        //DataManager dataManager = ((MvpApp) getApplication()).getDataManager();

        //mAccountPresenter = new AccountPresenter(dataManager); //Bind Data here

        mLaunchPresenter = new LaunchPresenter();
        mLaunchPresenter.onAttach(this);
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
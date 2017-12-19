package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.presenters.AccountPresenter;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AccountActivity extends BaseActivity implements AccountMvpView {

    AccountPresenter mAccountPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        Timber.i("Presented Account Activity");
        mAccountPresenter = new AccountPresenter();
        mAccountPresenter.onAttach(this);
    }
}

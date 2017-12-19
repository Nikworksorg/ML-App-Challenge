package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.presenters.AccountTransactionPresenter;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

import butterknife.ButterKnife;
import timber.log.Timber;

public class AccountTransactionActivity extends BaseActivity implements AccountTransactionMvpView {

    AccountTransactionPresenter mAccountTransactionPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AccountTransactionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_transaction);
        ButterKnife.bind(this);
        Timber.i("Presented Account Transaction Activity");

        mAccountTransactionPresenter.onAttach(this);
        mAccountTransactionPresenter = new AccountTransactionPresenter();
    }
}

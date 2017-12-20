package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.DataManager;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(R.string.account_activity_title));

        DataManager dataManager = DataManager.getInstance(this);


        mAccountPresenter = new AccountPresenter();
        mAccountPresenter.onAttach(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quit, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                quitApplication();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void quitApplication() {
        finish();
    }

    @Override
    public void openAccountTransaction() {
        Intent intent = AccountTransactionActivity.getStartIntent(this);
        startActivity(intent);
    }
}

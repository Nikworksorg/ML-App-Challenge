package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.adapters.TransactionAdapter;
import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.models.TransactionRenderModel;
import com.appchallenge.ml_app_challenge.presenters.AccountTransactionPresenter;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AccountTransactionActivity extends AppCompatActivity implements AccountTransactionMvpView {

    AccountTransactionPresenter mAccountTransactionPresenter;

    @BindView(R.id.list_view)
    ListView mListView;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AccountModel accountModel = (AccountModel) getIntent().getSerializableExtra("account");

        DataManager dataManager = DataManager.getInstance(this);

        mAccountTransactionPresenter = new AccountTransactionPresenter(this, dataManager);
        mAccountTransactionPresenter.onAttach(this);
        if(accountModel != null) {
            mAccountTransactionPresenter.computeAccount(accountModel);
        }
        else{
            mAccountTransactionPresenter.computeAllTransactions();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void renderTitle(String title) {
        if(title != null) {
            setTitle(title);
        }
    }

    @Override
    public void renderTransactionList(ArrayList<TransactionRenderModel> transactionRenderModels) {
        if(transactionRenderModels != null) {
            mListView.setAdapter(new TransactionAdapter(this, transactionRenderModels));
        }
    }
}

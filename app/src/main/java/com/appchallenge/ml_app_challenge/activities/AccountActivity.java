package com.appchallenge.ml_app_challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.presenters.AccountPresenter;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class AccountActivity extends AppCompatActivity implements AccountMvpView {

    AccountPresenter mAccountPresenter;

    @BindView(R.id.chequing_account_title)
    TextView mChequingAccountTitle;

    @BindView(R.id.chequing_account_number)
    TextView mChequingAccountNumber;

    @BindView(R.id.chequing_account_balance)
    TextView mChequingAccountBalance;

    @BindView(R.id.savings_account_title)
    TextView mSavingsAccountTitle;

    @BindView(R.id.savings_account_number)
    TextView mSavingsAccountNumber;

    @BindView(R.id.savings_account_balance)
    TextView mSavingsAccountBalance;

    @BindView(R.id.tfsa_account_title)
    TextView mTfsaAccountTitle;

    @BindView(R.id.tfsa_account_number)
    TextView mTfsaAccountNumber;

    @BindView(R.id.tfsa_account_balance)
    TextView mTfsaAccountBalance;

    @BindView(R.id.total)
    TextView mTotal;




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


        mAccountPresenter = new AccountPresenter(this, dataManager);
        mAccountPresenter.onAttach(this);
        mAccountPresenter.render();
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
    public void onBackPressed() {
        Timber.i("Back button pressed");
    }

    @Override
    public void openAccountTransaction(AccountModel accountModel) {
        Intent intent = AccountTransactionActivity.getStartIntent(this);
        intent.putExtra("account", accountModel);
        startActivity(intent);
    }

    @Override
    public void renderChequingAccount(AccountModel accountModel) {
        if(accountModel != null) {
            mChequingAccountTitle.setText(accountModel.getmDisplayName());
            mChequingAccountNumber.setText(accountModel.getmAccountNumber());
            mChequingAccountBalance.setText(String.format("$%.2f", accountModel.getmBalance()));
        }
    }

    @Override
    public void renderSavingsAccount(AccountModel accountModel) {
        if(accountModel != null) {
            mSavingsAccountTitle.setText(accountModel.getmDisplayName());
            mSavingsAccountNumber.setText(accountModel.getmAccountNumber());
            mSavingsAccountBalance.setText(String.format("$%.2f", accountModel.getmBalance()));
        }
    }

    @Override
    public void renderTfsaAccount(AccountModel accountModel) {
        if(accountModel != null) {
            mTfsaAccountTitle.setText(accountModel.getmDisplayName());
            mTfsaAccountNumber.setText(accountModel.getmAccountNumber());
            mTfsaAccountBalance.setText(String.format("$%.2f", accountModel.getmBalance()));
        }
    }

    @Override
    public void renderTotal(String total) {
        if(total != null) {
            mTotal.setText(total);
        }
    }

    @OnClick(R.id.chequing_account_cell)
    public void onChequingCellClicked(View Sender){
        mAccountPresenter.computeTransactionActivity(AccountPresenter.CHEQUING_ACCOUNT_ID);
    }

    @OnClick(R.id.savings_account_cell)
    public void onSavingsCellClicked(View Sender){
        mAccountPresenter.computeTransactionActivity(AccountPresenter.SAVINGS_ACCOUNT_ID);
    }

    @OnClick(R.id.tfsa_account_cell)
    public void onTfsaCellClicked(View Sender){
        mAccountPresenter.computeTransactionActivity(AccountPresenter.TFSA_ACCOUNT_ID);
    }

    @OnClick(R.id.all_transactions_cell)
    public void onAllTransactionsCellClicked(View Sender){
        mAccountPresenter.computeTransactionActivity(AccountPresenter.ALL_TRANSACTION_ID);
    }
}

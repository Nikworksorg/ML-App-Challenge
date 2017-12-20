package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;
import android.content.Intent;

import com.appchallenge.ml_app_challenge.activities.AccountActivity;
import com.appchallenge.ml_app_challenge.models.Account;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountPresenter <V extends AccountMvpView> extends BasePresenter<V> implements AccountMvpPresenter<V> {
    public static final Integer CHEQUING_ACCOUNT_ID = 10;
    public static final Integer SAVINGS_ACCOUNT_ID = 12;
    public static final Integer TFSA_ACCOUNT_ID = 19;
    public static final Integer ALL_TRANSACTION_ID = -1;
    private Double total = 0.0;

    public AccountPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void setShowWelcome() {
        getmDataManager().saveShowWelcome(getmContext(), true);
    }

    @Override
    public void render() {
        ArrayList<Account>accounts = getmDataManager().getmAccountList();
        if(accounts != null){
            for(Account account: accounts) {
                if(account.getmId().equals(CHEQUING_ACCOUNT_ID)){
                    getMvpView().renderChequingAccount(account);
                }else if(account.getmId().equals(SAVINGS_ACCOUNT_ID)) {
                    getMvpView().renderSavingsAccount(account);
                }else if(account.getmId().equals(TFSA_ACCOUNT_ID)){
                    getMvpView().renderTfsaAccount(account);
                }
                total += account.getmBalance();
            }
            getMvpView().renderTotal(String.format("$%.2f", total));
        }
    }

    @Override
    public void computeTransactionActivity(Integer accountId) {
        ArrayList<Account>accounts = getmDataManager().getmAccountList();
        Account passingAccount = null;
        if(accounts != null){
            for(Account account: accounts) {
                if(account.getmId().equals(accountId)){
                    passingAccount = account;
                    break;
                }
            }
            getMvpView().openAccountTransaction(passingAccount);
        }


    }
}

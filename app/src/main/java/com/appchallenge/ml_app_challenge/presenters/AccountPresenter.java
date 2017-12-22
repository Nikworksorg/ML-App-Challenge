package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountPresenter <V extends AccountMvpView> extends BasePresenter<V> implements AccountMvpPresenter<V> {
    private Double total = 0.0;

    public AccountPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void setShowWelcome() {
        getmDataManager().saveShowWelcome(getmContext(), true);
    }

    @Override
    public void computeTotal() {
        ArrayList<AccountModel> accountModels = getmDataManager().getmAccountModelList();
        if(accountModels != null){
            for(AccountModel accountModel : accountModels) {
                if(accountModel.getmId().equals(AccountModel.CHEQUING_ACCOUNT_ID)){
                    getMvpView().renderChequingAccount(accountModel);
                }else if(accountModel.getmId().equals(AccountModel.SAVINGS_ACCOUNT_ID)) {
                    getMvpView().renderSavingsAccount(accountModel);
                }else if(accountModel.getmId().equals(AccountModel.TFSA_ACCOUNT_ID)){
                    getMvpView().renderTfsaAccount(accountModel);
                }
                total += accountModel.getmBalance();
            }
            getMvpView().renderTotal(String.format("$%.2f", total));
        }
    }

    @Override
    public void computeTransactionActivity(Integer accountId) {
        ArrayList<AccountModel> accountModels = getmDataManager().getmAccountModelList();
        AccountModel passingAccountModel = null;
        if(accountModels != null){
            for(AccountModel accountModel : accountModels) {
                if(accountModel.getmId().equals(accountId)){
                    passingAccountModel = accountModel;
                    break;
                }
            }
            getMvpView().openAccountTransaction(passingAccountModel);
        }
    }
}

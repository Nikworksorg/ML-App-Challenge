package com.appchallenge.ml_app_challenge.views;

import com.appchallenge.ml_app_challenge.models.AccountModel;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountMvpView extends MvpView{
    public void quitApplication();
    public void openAccountTransaction(AccountModel accountModel);
    public void renderChequingAccount(AccountModel accountModel);
    public void renderSavingsAccount(AccountModel accountModel);
    public void renderTfsaAccount(AccountModel accountModel);
    public void renderTotal(String total);
}

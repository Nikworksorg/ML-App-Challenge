package com.appchallenge.ml_app_challenge.views;

import com.appchallenge.ml_app_challenge.models.Account;

import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountMvpView extends MvpView{
    public void quitApplication();
    public void openAccountTransaction(Account account);
    public void renderChequingAccount(Account account);
    public void renderSavingsAccount(Account account);
    public void renderTfsaAccount(Account account);
    public void renderTotal(String total);
}

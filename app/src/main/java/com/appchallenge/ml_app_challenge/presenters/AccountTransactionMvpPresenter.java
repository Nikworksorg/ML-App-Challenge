package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountTransactionMvpPresenter<V extends AccountTransactionMvpView> extends MvpPresenter<V> {
    void computeAccount(AccountModel accountModel);
    void computeAllTransactions();
}

package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountTransactionPresenter <V extends AccountTransactionMvpView> extends BasePresenter<V> implements AccountTransactionMvpPresenter<V> {
    public AccountTransactionPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }
}

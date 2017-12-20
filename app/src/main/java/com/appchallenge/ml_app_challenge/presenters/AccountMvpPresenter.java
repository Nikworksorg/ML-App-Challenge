package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.models.Account;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountMvpPresenter<V extends AccountMvpView> extends MvpPresenter<V> {
    public void setShowWelcome();
    public void render();
    public void computeTransactionActivity(Integer accountId);
}

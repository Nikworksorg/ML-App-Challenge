package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.AccountMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountPresenter <V extends AccountMvpView> extends BasePresenter<V> implements AccountMvpPresenter<V> {
    public AccountPresenter(/*DataManager dataManager*/) {
    }
}

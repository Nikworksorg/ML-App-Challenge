package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.AccountMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountMvpPresenter<V extends AccountMvpView> extends MvpPresenter<V> {
    public void setShowWelcome();
}

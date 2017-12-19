package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface LaunchMvpPresenter<V extends WelcomeMvpView> extends MvpPresenter<V> {
    void decideNextActivity();
}

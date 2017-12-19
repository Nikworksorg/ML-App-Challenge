package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class LaunchPresenter <V extends WelcomeMvpView> extends BasePresenter<V> implements LaunchMvpPresenter<V> {

    public LaunchPresenter(/*DataManager dataManager*/) {
    }

    @Override
    public void decideNextActivity() {

    }
}

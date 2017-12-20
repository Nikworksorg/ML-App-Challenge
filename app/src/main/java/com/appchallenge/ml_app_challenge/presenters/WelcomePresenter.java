package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class WelcomePresenter<V extends WelcomeMvpView> extends BasePresenter<V> implements WelcomeMvpPresenter<V> {

    public WelcomePresenter(/*DataManager dataManager*/) {
    }

    @Override
    public void decideNextActivity() {

    }
}

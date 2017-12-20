package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class WelcomePresenter<V extends WelcomeMvpView> extends BasePresenter<V> implements WelcomeMvpPresenter<V> {

    public WelcomePresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void computeNextActivity() {
        if(!getmDataManager().loadShowWelcome(getmContext())){
            getMvpView().openAccountActivity();
        }
    }

    @Override
    public void unsetShowWelcome() {
        getmDataManager().saveShowWelcome(getmContext(), false);
    }
}

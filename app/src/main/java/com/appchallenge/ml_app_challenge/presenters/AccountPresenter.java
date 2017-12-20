package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountPresenter <V extends AccountMvpView> extends BasePresenter<V> implements AccountMvpPresenter<V> {
    public AccountPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void setShowWelcome() {
        getDataManager().saveShowWelcome(getmContext(), true);
    }
}

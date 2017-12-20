package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.MvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    private V mMvpView;
    private DataManager mDataManager;
    private Context mContext;


    public BasePresenter(Context context, DataManager dataManager){
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public Context getmContext() {
        return mContext;
    }
}

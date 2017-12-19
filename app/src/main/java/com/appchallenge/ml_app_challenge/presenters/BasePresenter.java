package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.MvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    private V mMvpView;

    //DataManager mDataManager;


    public BasePresenter(/*DataManager dataManager*/){
        //mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

//    public DataManager getDataManager() {
//        return mDataManager;
//    }
}

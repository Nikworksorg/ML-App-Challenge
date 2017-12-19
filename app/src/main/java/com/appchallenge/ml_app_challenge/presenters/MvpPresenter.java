package com.appchallenge.ml_app_challenge.presenters;

import com.appchallenge.ml_app_challenge.views.MvpView;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface MvpPresenter <V extends MvpView>{
    void onAttach(V mvpView);
}

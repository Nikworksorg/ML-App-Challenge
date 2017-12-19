package com.appchallenge.ml_app_challenge.application;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class TransactionApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

    }
}
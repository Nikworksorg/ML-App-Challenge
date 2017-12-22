package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;
import com.appchallenge.ml_app_challenge.views.WelcomeMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import timber.log.Timber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by nikhilthiruvengadam on 12/21/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class WelcomePresenterTest {
    @Mock
    private WelcomeMvpView mockWelcomeMvpView;

    private DataManager dataManager;

    private Context context;

    private WelcomePresenter welcomePresenter;

    @Before
    public void setUp(){
        context = InstrumentationRegistry.getTargetContext();
        dataManager = DataManager.getInstance(context);
        welcomePresenter = new WelcomePresenter(context, dataManager);
        welcomePresenter.onAttach(mockWelcomeMvpView);
    }

    @Test
    public void transitionToToAccountScreen() throws Exception {
        dataManager.saveShowWelcome(context, false);
        welcomePresenter.computeNextActivity();
        verify(mockWelcomeMvpView).openAccountActivity();
    }
}
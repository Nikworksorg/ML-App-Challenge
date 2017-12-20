package com.appchallenge.ml_app_challenge.models;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import com.appchallenge.ml_app_challenge.activities.WelcomeActivity;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */
public class DataManagerTest {


    @Mock
    Context context;

    DataManager dataManager;

    @Test
    public void getInstance() throws Exception {
        dataManager = DataManager.getInstance(context);
        assertNull(dataManager);
    }
}
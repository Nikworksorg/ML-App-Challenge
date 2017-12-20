package com.appchallenge.ml_app_challenge.views;

import com.appchallenge.ml_app_challenge.models.TransactionRenderModel;

import java.util.ArrayList;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public interface AccountTransactionMvpView extends MvpView {
    public void renderTitle(String title);
    public void renderTransactionList(ArrayList<TransactionRenderModel> transactionRenderModels);
}

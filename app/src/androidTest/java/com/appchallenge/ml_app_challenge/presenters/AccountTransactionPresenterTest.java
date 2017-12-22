package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.models.TransactionEventModel;
import com.appchallenge.ml_app_challenge.models.TransactionRenderModel;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;

/**
 * Created by nikhilthiruvengadam on 12/21/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountTransactionPresenterTest {
    @Mock
    private AccountTransactionMvpView mockAccountTransactionMvpView;

    private AccountTransactionPresenter accountTransactionPresenter;

    private DataManager dataManager;

    private Context context;

    private AccountModel chequingAccountModel;
    private AccountModel savingsAccountModel;
    private AccountModel tfsaAccountModel;


    private final Integer CHEQUING_ACCOUNT_ID = 10;
    private final Integer SAVINGS_ACCOUNT_ID = 12;
    private final Integer TFSA_ACCOUNT_ID = 19;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getContext();
        dataManager = DataManager.getInstance(context);
        populateAccountModels();
        accountTransactionPresenter = new AccountTransactionPresenter(context, dataManager);
        accountTransactionPresenter.onAttach(mockAccountTransactionMvpView);

    }

    private void populateAccountModels(){
        ArrayList<AccountModel> accountModels = dataManager.getmAccountModelList();

        for (AccountModel accountModel: accountModels) {
            if(accountModel.getmId() == CHEQUING_ACCOUNT_ID){
                chequingAccountModel = accountModel;
            }
            else if(accountModel.getmId() == SAVINGS_ACCOUNT_ID){
                savingsAccountModel = accountModel;
            }
            else if(accountModel.getmId() == TFSA_ACCOUNT_ID){
                tfsaAccountModel = accountModel;
            }
        }
    }

    @Test
    public void renderChequingAccountTitle() throws Exception {
        accountTransactionPresenter.computeAccount(chequingAccountModel);
        verify(mockAccountTransactionMvpView).renderTitle(chequingAccountModel);
    }

    @Test
    public void renderSavingsAccountTitle() throws Exception {
        accountTransactionPresenter.computeAccount(savingsAccountModel);
        verify(mockAccountTransactionMvpView).renderTitle(savingsAccountModel);
    }

    @Test
    public void renderTfsaAccountTitle() throws Exception {
        accountTransactionPresenter.computeAccount(tfsaAccountModel);
        verify(mockAccountTransactionMvpView).renderTitle(tfsaAccountModel);
    }
}


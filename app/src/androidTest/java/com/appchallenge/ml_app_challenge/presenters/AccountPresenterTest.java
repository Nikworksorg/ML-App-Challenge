package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.views.AccountMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;


import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by nikhilthiruvengadam on 12/21/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountPresenterTest {
    @Mock
    private AccountMvpView mockAccountMvpView;

    private AccountPresenter accountPresenter;

    private DataManager dataManager;

    private Context context;

    private AccountModel chequingAccountModel;
    private AccountModel savingsAccountModel;
    private AccountModel tfsaAccountModel;

    private final Integer CHEQUING_ACCOUNT_ID = 10;
    private final Integer SAVINGS_ACCOUNT_ID = 12;
    private final Integer TFSA_ACCOUNT_ID = 19;
    private final Integer ALL_TRANSACTION_ID = -1;

    String total;

    @Before
    public void setUp() throws  Exception{
        context = InstrumentationRegistry.getContext();
        dataManager = DataManager.getInstance(context);
        populateAccountModels();
        accountPresenter = new AccountPresenter(context, dataManager);
        accountPresenter.onAttach(mockAccountMvpView);
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
        total = String.format("$%.2f", chequingAccountModel.getmBalance() +
                                        savingsAccountModel.getmBalance() +
                                        tfsaAccountModel.getmBalance());
    }

     @Test
    public void computeTotal() throws Exception {
         accountPresenter.computeTotal();
         verify(mockAccountMvpView).renderChequingAccount(chequingAccountModel);
         verify(mockAccountMvpView).renderSavingsAccount(savingsAccountModel);
         verify(mockAccountMvpView).renderTfsaAccount(tfsaAccountModel);
         verify(mockAccountMvpView).renderTotal(total);
    }

    @Test
    public void computeChequingTransactionActivity() throws Exception {
        accountPresenter.computeTransactionActivity(CHEQUING_ACCOUNT_ID);
        verify(mockAccountMvpView).openAccountTransaction(chequingAccountModel);
    }

    @Test
    public void computeSavingsTransactionActivity() throws Exception {
        accountPresenter.computeTransactionActivity(SAVINGS_ACCOUNT_ID);
        verify(mockAccountMvpView).openAccountTransaction(savingsAccountModel);
    }

    @Test
    public void computeTfsaTransactionActivity() throws Exception {
        accountPresenter.computeTransactionActivity(TFSA_ACCOUNT_ID);
        verify(mockAccountMvpView).openAccountTransaction(tfsaAccountModel);
    }

    @Test
    public void computeAllTransactionActivity() throws Exception {
        accountPresenter.computeTransactionActivity(ALL_TRANSACTION_ID);
        verify(mockAccountMvpView).openAccountTransaction(null);
    }
}
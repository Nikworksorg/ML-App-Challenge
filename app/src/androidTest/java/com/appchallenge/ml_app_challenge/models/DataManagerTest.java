package com.appchallenge.ml_app_challenge.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import com.google.gson.reflect.TypeToken;

import net.javacrumbs.jsonunit.JsonAssert;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;


import timber.log.Timber;

import static org.junit.Assert.*;

/**
 * Created by nikhilthiruvengadam on 12/21/17.
 */
public class DataManagerTest{
    private DataManager dataManager;

    private Context context;

    @Before
    public void setUp() throws  Exception{
        context = InstrumentationRegistry.getContext();
        dataManager = DataManager.getInstance(context);
    }

   @Test
   public void checkForNotNullOnDataManagerObjects() throws  Exception{
       assertNotNull(dataManager.getmAccountModelList());
       assertNotNull(dataManager.getmCheckingAccountTransactions());
       assertNotNull(dataManager.getmSavingsAccountTransactions());
       assertNotNull(dataManager.getmTfsaAccountTransactions());
       assertNotNull(dataManager.getmOverallTransactions());
   }

   @Test
   public void fetchList(){
       ArrayList<AccountModel> accountModelList = dataManager.fetchList(context, "listOfAccounts.json",
                                                     new TypeToken<ArrayList<AccountModel>>(){}.getType());
       assertNotNull(accountModelList);
       assertTrue(accountModelList.size() > 0);
       assertNotNull(accountModelList.get(0));
       assertNotNull(accountModelList.get(0).getmId());
       assertNotNull(accountModelList.get(0).getmDisplayName());
       assertNotNull(accountModelList.get(0).getmAccountNumber());
       assertNotNull(accountModelList.get(0).getmBalance());


       ArrayList<TransactionEventModel> checkingAccountTransactions = dataManager.fetchList(context, "chequingAccount.json",
               new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
       assertNotNull(checkingAccountTransactions);
       assertTrue(checkingAccountTransactions.size() > 0);
       assertNotNull(checkingAccountTransactions.get(0).getmDate());
       assertNotNull(checkingAccountTransactions.get(0));
       assertTrue(checkingAccountTransactions.get(0).getmTransactionActivities().size() > 0);
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmBalance());
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmDate());
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmDepositAmount());
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmDescription());
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmId());
       assertNotNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmTransactionUuid());
       assertNull(checkingAccountTransactions.get(0).getmTransactionActivities().get(0).getmWithDrawableAmount());


       ArrayList<TransactionEventModel> savingsAccountTransactions = dataManager.fetchList(context, "savingsAccount.json",
               new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
       assertNotNull(savingsAccountTransactions);
       assertTrue(savingsAccountTransactions.size() > 0);
       assertNotNull(savingsAccountTransactions.get(0).getmDate());
       assertNotNull(savingsAccountTransactions.get(0));
       assertTrue(savingsAccountTransactions.get(0).getmTransactionActivities().size() > 0);
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmBalance());
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmDate());
       assertNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmDepositAmount());
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmDescription());
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmId());
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmTransactionUuid());
       assertNotNull(savingsAccountTransactions.get(0).getmTransactionActivities().get(0).getmWithDrawableAmount());

       ArrayList<TransactionEventModel> tfsaAccountTransactions = dataManager.fetchList(context, "TfsaAccount.json",
               new TypeToken<ArrayList<TransactionEventModel>>(){}.getType());
       assertNotNull(tfsaAccountTransactions);
       assertTrue(tfsaAccountTransactions.size() > 0);
       assertNotNull(tfsaAccountTransactions.get(0).getmDate());
       assertNotNull(tfsaAccountTransactions.get(0));
       assertTrue(tfsaAccountTransactions.get(0).getmTransactionActivities().size() > 0);
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmBalance());
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmDate());
       assertNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmDepositAmount());
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmDescription());
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmId());
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmTransactionUuid());
       assertNotNull(tfsaAccountTransactions.get(0).getmTransactionActivities().get(0).getmWithDrawableAmount());

       ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>> allTransactions = dataManager.fetchList(context, "accountTransactions.json",
               new TypeToken<ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>>>(){}.getType());
       assertNotNull(allTransactions);
       assertTrue(allTransactions.size() > 0);
       assertNotNull(allTransactions.get(0));
       assertNotNull(allTransactions.get(0).get(10));
       assertTrue(allTransactions.get(0).get(10).size() > 0);
       assertNotNull(allTransactions.get(0).get(10).get(0).getmDate());
       assertNotNull(allTransactions.get(0).get(10).get(0));
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities());
       assertTrue(allTransactions.get(0).get(10).get(0).getmTransactionActivities().size() > 0);
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0));
       assertNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmWithDrawableAmount());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmId());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmDescription());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmDepositAmount());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmBalance());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmTransactionUuid());
       assertNotNull(allTransactions.get(0).get(10).get(0).getmTransactionActivities().get(0).getmDate());
   }

   @Test
   public void loadJSONFromAsset() {
       assertNull(dataManager.loadJSONFromAsset(context, "")); //Empty file Name
       assertNull(dataManager.loadJSONFromAsset(context, "test.json")); //Incorrect file name
       assertNotNull(dataManager.loadJSONFromAsset(context, "listOfAccounts.json"));
       assertNotNull(dataManager.loadJSONFromAsset(context, "chequingAccount.json"));
       assertNotNull(dataManager.loadJSONFromAsset(context, "savingsAccount.json"));
       assertNotNull(dataManager.loadJSONFromAsset(context, "TfsaAccount.json"));
       assertNotNull(dataManager.loadJSONFromAsset(context, "accountTransactions.json"));
   }


   @After
   public void tearDown() throws Exception{
        dataManager = null;
        context = null;
    }

}
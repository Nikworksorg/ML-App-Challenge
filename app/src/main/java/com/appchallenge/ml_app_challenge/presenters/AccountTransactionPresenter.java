package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.models.TransactionActivityModel;
import com.appchallenge.ml_app_challenge.models.TransactionEventModel;
import com.appchallenge.ml_app_challenge.models.TransactionRenderModel;
import com.appchallenge.ml_app_challenge.utils.DateUtils;
import com.appchallenge.ml_app_challenge.views.AccountTransactionMvpView;

import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountTransactionPresenter<V extends AccountTransactionMvpView> extends BasePresenter<V> implements AccountTransactionMvpPresenter<V> {
    public AccountTransactionPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void computeAccount(AccountModel accountModel) {
        try {
            if (accountModel != null) {
                ArrayList<TransactionEventModel> accountTransactions = null;
                if (accountModel.getmId().equals(AccountModel.CHEQUING_ACCOUNT_ID)) {
                    accountTransactions = getmDataManager().getmCheckingAccountTransactions();
                } else if (accountModel.getmId().equals(AccountModel.SAVINGS_ACCOUNT_ID)) {
                    accountTransactions = getmDataManager().getmSavingsAccountTransactions();
                } else if (accountModel.getmId().equals(AccountModel.TFSA_ACCOUNT_ID)) {
                    accountTransactions = getmDataManager().getmTfsaAccountTransactions();
                }
                getMvpView().renderTitle(accountModel);
                ArrayList<TransactionRenderModel> transactionRenderModels = new ArrayList<>();
                transactionRenderModels = populateAccountRenderModel(accountTransactions,
                        transactionRenderModels);
                getMvpView().renderTransactionList(populateAccountRenderModel(accountTransactions,
                        transactionRenderModels));
            }
        } catch (Exception ex) {
            Timber.e(ex.toString());
        }
    }


    @Override
    public void computeAllTransactions() {
        ArrayList<TransactionRenderModel> transactionRenderModels = new ArrayList<>();
        ArrayList<HashMap<Integer, ArrayList<TransactionEventModel>>> allTransactions = getmDataManager().getmOverallTransactions();
        for (HashMap<Integer, ArrayList<TransactionEventModel>> transaction : allTransactions) {
            for (Integer key :
                    transaction.keySet()) {
                TransactionRenderModel transactionRenderModel = new TransactionRenderModel();
                transactionRenderModel.setmRowType(TransactionRenderModel.RowType.ACCOUNT_NAME_LIST_ITEM.ordinal());
                transactionRenderModel.setmAccountId(key);
                transactionRenderModels.add(transactionRenderModel);
                transactionRenderModels = populateAccountRenderModel(transaction.get(key), transactionRenderModels);
            }
        }
        getMvpView().renderTransactionList(transactionRenderModels);
    }

    public ArrayList<TransactionRenderModel> populateAccountRenderModel(ArrayList<TransactionEventModel> accountTransactions
            , ArrayList<TransactionRenderModel> transactionRenderModels) {
        for (TransactionEventModel transactionEventModel : accountTransactions) {
            if (transactionEventModel.getmDate() != null && DateUtils.isValidDate(transactionEventModel.getmDate())) {
                TransactionRenderModel transactionRenderModel = new TransactionRenderModel();
                transactionRenderModel.setmDate(DateUtils.formatDate(transactionEventModel.getmDate()));
                transactionRenderModel.setmRowType(TransactionRenderModel.RowType.DATE_LIST_ITEM.ordinal());
                transactionRenderModels.add(transactionRenderModel);
                ArrayList<TransactionActivityModel> transactionActivities = transactionEventModel.getmTransactionActivities();
                for (TransactionActivityModel transactionActivityModel : transactionActivities) {
                    if (transactionActivityModel.getmTransactionUuid() != null ||
                            transactionActivityModel.getmWithDrawableAmount() != null ||
                            transactionActivityModel.getmWithDrawableAmount() != null ||
                            transactionActivityModel.getmDepositAmount() != null ||
                            transactionActivityModel.getmDescription() != null) {
                        TransactionRenderModel transactionRenderModel2 = new TransactionRenderModel();
                        if (transactionActivityModel.getmTransactionUuid() != null) {
                            transactionRenderModel2.setmUuid(transactionActivityModel.getmTransactionUuid());
                        }

                        if (transactionActivityModel.getmWithDrawableAmount() != null) {
                            transactionRenderModel2.setmAmount(transactionActivityModel.getmWithDrawableAmount());
                            transactionRenderModel2.setDeposit(false);
                        } else if (transactionActivityModel.getmDepositAmount() != null) {
                            transactionRenderModel2.setmAmount(transactionActivityModel.getmDepositAmount());
                            transactionRenderModel2.setDeposit(true);
                        }

                        if (transactionActivityModel.getmBalance() != null) {
                            transactionRenderModel2.setmBalance(transactionActivityModel.getmBalance());
                        }

                        if (transactionActivityModel.getmDescription() != null) {
                            transactionRenderModel2.setmDescription(transactionActivityModel.getmDescription());
                        }
                        transactionRenderModel2.setmRowType(TransactionRenderModel.RowType.ACCOUNT_LIST_ITEM.ordinal());
                        if (transactionActivities.indexOf(transactionActivityModel) == transactionActivities.size() - 1) {
                            transactionRenderModel2.setSeparatorHidden(true);
                        }

                        transactionRenderModels.add(transactionRenderModel2);
                    }
                }
            }
        }
        return transactionRenderModels;
    }
}

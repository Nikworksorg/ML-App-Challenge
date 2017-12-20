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

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountTransactionPresenter<V extends AccountTransactionMvpView> extends BasePresenter<V> implements AccountTransactionMvpPresenter<V> {
    public AccountTransactionPresenter(Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void computeAccount(AccountModel accountModel) {
        String title = "";
        if (accountModel != null) {
            ArrayList<TransactionEventModel> accountTransactions = null;
            if (accountModel.getmId().equals(AccountPresenter.CHEQUING_ACCOUNT_ID)) {
                title = getmContext().getString(R.string.chequing_transactions);
                accountTransactions = getmDataManager().getmCheckingAccountTransactions();
            } else if (accountModel.getmId().equals(AccountPresenter.SAVINGS_ACCOUNT_ID)) {
                title = getmContext().getString(R.string.savings_transactions);
                accountTransactions = getmDataManager().getmSavingsAccountTransactions();
            } else if (accountModel.getmId().equals(AccountPresenter.TFSA_ACCOUNT_ID)) {
                title = getmContext().getString(R.string.tfsa_transactions);
                accountTransactions = getmDataManager().getmTfsaAccountTransactions();

            }
            getMvpView().renderTitle(title);
            ArrayList<TransactionRenderModel> transactionRenderModels = new ArrayList<>();
            getMvpView().renderTransactionList(populateAccountRenderModel(accountTransactions,
                    transactionRenderModels));
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
                if (key == AccountPresenter.CHEQUING_ACCOUNT_ID) {
                    transactionRenderModel.setmAccountName(getmContext().getString(R.string.chequing_transactions));
                } else if (key == AccountPresenter.SAVINGS_ACCOUNT_ID) {
                    transactionRenderModel.setmAccountName(getmContext().getString(R.string.savings_transactions));
                } else if (key == AccountPresenter.TFSA_ACCOUNT_ID) {
                    transactionRenderModel.setmAccountName(getmContext().getString(R.string.tfsa_transactions));
                }
                transactionRenderModels.add(transactionRenderModel);
                transactionRenderModels = populateAccountRenderModel(transaction.get(key),transactionRenderModels);
            }
        }
        getMvpView().renderTransactionList(transactionRenderModels);
    }

    private ArrayList<TransactionRenderModel> populateAccountRenderModel(ArrayList<TransactionEventModel> accountTransactions, ArrayList<TransactionRenderModel> transactionRenderModels){
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
                            transactionRenderModel2.setmUuid(String.format("%s: %d", getmContext().getString(R.string.uuid),
                                    transactionActivityModel.getmTransactionUuid()));
                        }

                        if (transactionActivityModel.getmWithDrawableAmount() != null) {
                            transactionRenderModel2.setmAmount(String.format("%s: $%s", getmContext().getString(R.string.withdrawal),
                                    transactionActivityModel.getmWithDrawableAmount()));
                        } else if (transactionActivityModel.getmDepositAmount() != null) {
                            transactionRenderModel2.setmAmount(String.format("%s: $%s", getmContext().getString(R.string.deposit),
                                    transactionActivityModel.getmDepositAmount()));
                        }

                        if (transactionActivityModel.getmBalance() != null) {
                            transactionRenderModel2.setmBalance(String.format("%s: $%s", getmContext().getString(R.string.balance),
                                    transactionActivityModel.getmBalance()));
                        }

                        if (transactionActivityModel.getmDescription() != null) {
                            transactionRenderModel2.setmDescription(String.format("%s: %s", getmContext().getString(R.string.description),
                                    transactionActivityModel.getmDescription()));
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

package com.appchallenge.ml_app_challenge.presenters;

import android.content.Context;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.Account;
import com.appchallenge.ml_app_challenge.models.DataManager;
import com.appchallenge.ml_app_challenge.models.TransactionActivity;
import com.appchallenge.ml_app_challenge.models.TransactionEvent;
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
    public void computeAccount(Account account) {
        String title = "";
        if (account != null) {
            ArrayList<TransactionEvent> accountTransactions = null;
            if (account.getmId().equals(AccountPresenter.CHEQUING_ACCOUNT_ID)) {
                title = getmContext().getString(R.string.chequing_transactions);
                accountTransactions = getmDataManager().getmCheckingAccountTransactions();
            } else if (account.getmId().equals(AccountPresenter.SAVINGS_ACCOUNT_ID)) {
                title = getmContext().getString(R.string.savings_transactions);
                accountTransactions = getmDataManager().getmSavingsAccountTransactions();
            } else if (account.getmId().equals(AccountPresenter.TFSA_ACCOUNT_ID)) {
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
        ArrayList<HashMap<Integer, ArrayList<TransactionEvent>>> allTransactions = getmDataManager().getmOverallTransactions();
        for (HashMap<Integer, ArrayList<TransactionEvent>> transaction : allTransactions) {
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

    private ArrayList<TransactionRenderModel> populateAccountRenderModel(ArrayList<TransactionEvent> accountTransactions, ArrayList<TransactionRenderModel> transactionRenderModels){
        for (TransactionEvent transactionEvent : accountTransactions) {
            if (transactionEvent.getmDate() != null && DateUtils.isValidDate(transactionEvent.getmDate())) {
                TransactionRenderModel transactionRenderModel = new TransactionRenderModel();
                transactionRenderModel.setmDate(DateUtils.formatDate(transactionEvent.getmDate()));
                transactionRenderModel.setmRowType(TransactionRenderModel.RowType.DATE_LIST_ITEM.ordinal());
                transactionRenderModels.add(transactionRenderModel);
                ArrayList<TransactionActivity> transactionActivities = transactionEvent.getmTransactionActivities();
                for (TransactionActivity transactionActivity : transactionActivities) {
                    if (transactionActivity.getmTransactionUuid() != null ||
                            transactionActivity.getmWithDrawableAmount() != null ||
                            transactionActivity.getmWithDrawableAmount() != null ||
                            transactionActivity.getmDepositAmount() != null ||
                            transactionActivity.getmDescription() != null) {
                        TransactionRenderModel transactionRenderModel2 = new TransactionRenderModel();
                        if (transactionActivity.getmTransactionUuid() != null) {
                            transactionRenderModel2.setmUuid(String.format("%s: %d", getmContext().getString(R.string.uuid),
                                    transactionActivity.getmTransactionUuid()));
                        }

                        if (transactionActivity.getmWithDrawableAmount() != null) {
                            transactionRenderModel2.setmAmount(String.format("%s: $%s", getmContext().getString(R.string.withdrawal),
                                    transactionActivity.getmWithDrawableAmount()));
                        } else if (transactionActivity.getmDepositAmount() != null) {
                            transactionRenderModel2.setmAmount(String.format("%s: $%s", getmContext().getString(R.string.deposit),
                                    transactionActivity.getmDepositAmount()));
                        }

                        if (transactionActivity.getmBalance() != null) {
                            transactionRenderModel2.setmBalance(String.format("%s: $%s", getmContext().getString(R.string.balance),
                                    transactionActivity.getmBalance()));
                        }

                        if (transactionActivity.getmDescription() != null) {
                            transactionRenderModel2.setmDescription(String.format("%s: %s", getmContext().getString(R.string.description),
                                    transactionActivity.getmDescription()));
                        }
                        transactionRenderModel2.setmRowType(TransactionRenderModel.RowType.ACCOUNT_LIST_ITEM.ordinal());
                        if (transactionActivities.indexOf(transactionActivity) == transactionActivities.size() - 1) {
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

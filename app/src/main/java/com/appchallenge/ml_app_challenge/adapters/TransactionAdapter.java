package com.appchallenge.ml_app_challenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appchallenge.ml_app_challenge.R;
import com.appchallenge.ml_app_challenge.models.AccountModel;
import com.appchallenge.ml_app_challenge.models.TransactionRenderModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikhilthiruvengadam on 12/20/17.
 */

public class TransactionAdapter extends BaseAdapter {

    private TransactionAdapter.AccountNameViewHolder mAccountNameViewHolder;
    private TransactionAdapter.DateViewHolder mDateViewHolder;
    private TransactionAdapter.AccountViewHolder mAccountViewHolder;
    private Context mContext;
    private ArrayList<TransactionRenderModel> mTransactionRenderModels;

    public TransactionAdapter(Context context, ArrayList<TransactionRenderModel> transactionRenderModels) {
        mContext = context;
        mTransactionRenderModels = transactionRenderModels;
    }

    static class AccountNameViewHolder {
        @BindView(R.id.account_name)
        TextView mAccountName;


        public AccountNameViewHolder(View view){
            ButterKnife.bind(this, view);
        }

    }

    static class DateViewHolder {
        @BindView(R.id.date)
        TextView mDate;

        public DateViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    static class AccountViewHolder {
        @BindView(R.id.uuid)
        TextView mUuid;

        @BindView(R.id.balance)
        TextView mBalance;

        @BindView(R.id.amount)
        TextView mAmount;

        @BindView(R.id.description)
        TextView mDescription;


        @BindView(R.id.separator)
        View mSeparator;

        public AccountViewHolder(View view){
            ButterKnife.bind(this, view);
        }

    }

    @Override
    public int getCount() {
        return mTransactionRenderModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mTransactionRenderModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return TransactionRenderModel.RowType.MAX_RESOURCE_TYPE.ordinal();
    }

    @Override
    public int getItemViewType(int position) {
        TransactionRenderModel transactionRenderModel = mTransactionRenderModels.get(position);
        if (transactionRenderModel.getmRowType()== TransactionRenderModel.RowType.ACCOUNT_NAME_LIST_ITEM.ordinal()) {
            return TransactionRenderModel.RowType.ACCOUNT_NAME_LIST_ITEM.ordinal();
        }else if (transactionRenderModel.getmRowType()== TransactionRenderModel.RowType.DATE_LIST_ITEM.ordinal()) {
            return TransactionRenderModel.RowType.DATE_LIST_ITEM.ordinal();
        }else if (transactionRenderModel.getmRowType()== TransactionRenderModel.RowType.ACCOUNT_LIST_ITEM.ordinal()) {
            return TransactionRenderModel.RowType.ACCOUNT_LIST_ITEM.ordinal();
        }
        return -1;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final TransactionRenderModel transactionRenderModel = mTransactionRenderModels.get(position);

        if (getItemViewType(position) == TransactionRenderModel.RowType.ACCOUNT_NAME_LIST_ITEM.ordinal()) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_transaction_account_name_list_item, null);
                mAccountNameViewHolder = new AccountNameViewHolder(convertView);
                convertView.setTag(mAccountNameViewHolder);
            } else {
                mAccountNameViewHolder = (AccountNameViewHolder) convertView.getTag();
            }
            if(transactionRenderModel.getmAccountId() == AccountModel.CHEQUING_ACCOUNT_ID){
                mAccountNameViewHolder.mAccountName.setText(String.format("%s", mContext.getString(R.string.chequing_transactions)));
            }else if(transactionRenderModel.getmAccountId() == AccountModel.SAVINGS_ACCOUNT_ID){
                mAccountNameViewHolder.mAccountName.setText(String.format("%s", mContext.getString(R.string.savings_transactions)));
            }else if(transactionRenderModel.getmAccountId() == AccountModel.TFSA_ACCOUNT_ID) {
            mAccountNameViewHolder.mAccountName.setText(String.format("%s", mContext.getString(R.string.tfsa_transactions)));
            }
        } else if (getItemViewType(position) == TransactionRenderModel.RowType.DATE_LIST_ITEM.ordinal()) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_transaction_date_list_item, null);
                mDateViewHolder = new DateViewHolder(convertView);
                convertView.setTag(mDateViewHolder);
            } else {
                mDateViewHolder = (DateViewHolder) convertView.getTag();
            }
            mDateViewHolder.mDate.setText(transactionRenderModel.getmDate()); //Have To Format Date
        }else if (getItemViewType(position) == TransactionRenderModel.RowType.ACCOUNT_LIST_ITEM.ordinal()) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_transaction_account_list_item, null);
                mAccountViewHolder = new AccountViewHolder(convertView);
                convertView.setTag(mAccountViewHolder);
            } else {
                mAccountViewHolder = (AccountViewHolder) convertView.getTag();
            }
            mAccountViewHolder.mUuid.setText(String.format("%s: %s", mContext.getString(R.string.uuid),  transactionRenderModel.getmUuid()));
            mAccountViewHolder.mAmount.setText(String.format("%s: %s",
                    ((transactionRenderModel.getDeposit())?mContext.getString(R.string.deposit):mContext.getString(R.string.withdrawal))
                    , transactionRenderModel.getmAmount()));
            mAccountViewHolder.mBalance.setText(String.format("%s: %s",mContext.getString(R.string.balance), transactionRenderModel.getmBalance()));
            mAccountViewHolder.mDescription.setText(String.format("%s: %s",mContext.getString(R.string.description), transactionRenderModel.getmDescription()));
            mAccountViewHolder.mSeparator.setVisibility((transactionRenderModel.getSeparatorHidden())?View.GONE:View.VISIBLE);
        }
        return convertView;
    }
}

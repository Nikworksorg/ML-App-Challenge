package com.appchallenge.ml_app_challenge.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appchallenge.ml_app_challenge.R;
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
    private Activity mActivity;
    private ArrayList<TransactionRenderModel> mTransactionRenderModels;

    public TransactionAdapter(Activity activity, ArrayList<TransactionRenderModel> transactionRenderModels) {
        mActivity = activity;
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
                mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final TransactionRenderModel transactionRenderModel = mTransactionRenderModels.get(position);

        if (getItemViewType(position) == TransactionRenderModel.RowType.ACCOUNT_NAME_LIST_ITEM.ordinal()) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_transaction_account_name_list_item, null);
                mAccountNameViewHolder = new AccountNameViewHolder(convertView);
                convertView.setTag(mAccountNameViewHolder);
            } else {
                mAccountNameViewHolder = (AccountNameViewHolder) convertView.getTag();
            }
            mAccountNameViewHolder.mAccountName.setText(transactionRenderModel.getmAccountName());
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
            mAccountViewHolder.mUuid.setText(transactionRenderModel.getmUuid());
            mAccountViewHolder.mAmount.setText(transactionRenderModel.getmAmount());
            mAccountViewHolder.mBalance.setText(transactionRenderModel.getmBalance());
            mAccountViewHolder.mDescription.setText(transactionRenderModel.getmDescription());
            mAccountViewHolder.mSeparator.setVisibility((transactionRenderModel.getSeparatorHidden())?View.GONE:View.VISIBLE);
        }
        return convertView;
    }
}

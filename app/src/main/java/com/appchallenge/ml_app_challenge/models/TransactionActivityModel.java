package com.appchallenge.ml_app_challenge.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class TransactionActivityModel implements Serializable {
    @SerializedName("id")
    private Integer mId;

    @SerializedName("date")
    private String mDate;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("withdrawal_amount")
    private String mWithDrawableAmount;

    @SerializedName("deposit_amount")
    private String mDepositAmount;

    @SerializedName("balance")
    private String mBalance;

    @SerializedName("transaction_uid")
    private Long  mTransactionUuid;

    public Integer getmId() {
        return mId;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmWithDrawableAmount() {
        return mWithDrawableAmount;
    }

    public String getmBalance() {
        return mBalance;
    }

    public Long getmTransactionUuid() {
        return mTransactionUuid;
    }

    public String getmDepositAmount() {
        return mDepositAmount;
    }

    public boolean equals(TransactionActivityModel transactionActivityModel){
        if(transactionActivityModel == null) return false;
        return (this.getmId()).equals(transactionActivityModel.getmId());
    }
}

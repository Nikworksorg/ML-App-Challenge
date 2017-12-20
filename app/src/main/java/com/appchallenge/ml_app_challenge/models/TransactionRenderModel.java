package com.appchallenge.ml_app_challenge.models;

import java.io.Serializable;

/**
 * Created by nikhilthiruvengadam on 12/20/17.
 */

public class TransactionRenderModel implements Serializable{

    public enum RowType {
        ACCOUNT_NAME_LIST_ITEM,
        DATE_LIST_ITEM,
        ACCOUNT_LIST_ITEM,
        MAX_RESOURCE_TYPE
    };


    private String mAccountName;
    private String mDate;
    private String mUuid;
    private String mAmount;
    private String mBalance;
    private String mDescription ;
    private Integer mRowType = -1;

    public String getmAccountName() {
        return mAccountName;
    }

    public void setmAccountName(String mAccountName) {
        this.mAccountName = mAccountName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmUuid() {
        return mUuid;
    }

    public void setmUuid(String mUuid) {
        this.mUuid = mUuid;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmBalance() {
        return mBalance;
    }

    public void setmBalance(String mBalance) {
        this.mBalance = mBalance;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Integer getmRowType() {
        return mRowType;
    }

    public void setmRowType(Integer mRowType) {
        this.mRowType = mRowType;
    }
}

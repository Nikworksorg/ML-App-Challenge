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


    private Integer mAccountId;
    private String mDate;
    private Long mUuid;
    private String mAmount;
    private String mBalance;
    private String mDescription ;
    private Integer mRowType = -1;
    private Boolean isSeparatorHidden = false;
    private Boolean isDeposit = false;

    public Integer getmAccountId() {
        return mAccountId;
    }

    public void setmAccountId(Integer mAccountId) {
        this.mAccountId = mAccountId;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public Long getmUuid() {
        return mUuid;
    }

    public void setmUuid(Long mUuid) {
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

    public Boolean getSeparatorHidden() {
        return isSeparatorHidden;
    }

    public void setSeparatorHidden(Boolean separatorHidden) {
        isSeparatorHidden = separatorHidden;
    }

    public Boolean getDeposit() {
        return isDeposit;
    }

    public void setDeposit(Boolean deposit) {
        isDeposit = deposit;
    }

    public boolean equals(TransactionRenderModel transactionRenderModel){
        if(transactionRenderModel == null) return false;
        return (this.getmUuid()).equals(transactionRenderModel.getmUuid());
    }
}

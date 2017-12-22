package com.appchallenge.ml_app_challenge.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountModel implements Serializable {
    public static final Integer CHEQUING_ACCOUNT_ID = 10;
    public static final Integer SAVINGS_ACCOUNT_ID = 12;
    public static final Integer TFSA_ACCOUNT_ID = 19;
    public static final Integer ALL_TRANSACTION_ID = -1;

    @SerializedName("id")
    private Integer mId;

    @SerializedName("display_name")
    private String mDisplayName;

    @SerializedName("account_number")
    private String mAccountNumber;

    @SerializedName("balance")
    private Double mBalance;

    public Integer getmId() {
        return mId;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }

    public String getmAccountNumber() {
        return mAccountNumber;
    }

    public Double getmBalance() {
        return mBalance;
    }

    public boolean equals(AccountModel accountModel){
        if(accountModel == null) return false;
        return (this.getmId()).equals(accountModel.getmId());
    }
}

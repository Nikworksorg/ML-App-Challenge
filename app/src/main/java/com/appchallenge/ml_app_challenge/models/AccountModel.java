package com.appchallenge.ml_app_challenge.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nikhilthiruvengadam on 12/19/17.
 */

public class AccountModel implements Serializable {
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
}

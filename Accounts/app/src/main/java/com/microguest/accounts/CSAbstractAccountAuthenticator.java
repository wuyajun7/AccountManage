package com.microguest.accounts;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wuyajun on 15/8/9.
 */
public class CSAbstractAccountAuthenticator extends AbstractAccountAuthenticator {
    private String TAG = "SleepyAccountAuthenticator";
    private Context mContext;

    public CSAbstractAccountAuthenticator(Context context) {
        super(context);
        mContext = context;
    }

    /* 添加账户 */
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType,
                             String authTokenType,
                             String[] requiredFeatures,
                             Bundle options) throws NetworkErrorException {

        Log.d(TAG, accountType + " - " + authTokenType);

        Bundle ret = new Bundle();

        Intent intent = new Intent(mContext, CSAccountAddActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        ret.putParcelable(AccountManager.KEY_INTENT, intent);
        return ret;
    }


    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {
        Log.d(TAG, ".confirmCredentials");
        return null;
    }


    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        Log.d(TAG, ".editProperties");
        return null;
    }


    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) throws NetworkErrorException {
        Log.d(TAG, ".getAuthToken");
        return null;
    }


    @Override
    public String getAuthTokenLabel(String authTokenType) {
        Log.d(TAG, ".getAuthTokenLabel");
        return null;
    }


    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        Log.d(TAG, ".hasFeatures");
        return null;
    }


    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) {
        Log.d(TAG, ".updateCredentials");
        return null;
    }

}
package com.microguest.accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by wuyajun on 15/8/9.
 */
public class CSAccountsService extends Service {
    private CSAbstractAccountAuthenticator _saa;

    @Override
    public IBinder onBind(Intent intent) {
        IBinder ret = null;
        if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getSleepyAuthenticator().getIBinder();
        return ret;
    }

    private CSAbstractAccountAuthenticator getSleepyAuthenticator() {
        if (_saa == null)
            _saa = new CSAbstractAccountAuthenticator(this);
        return _saa;
    }
}

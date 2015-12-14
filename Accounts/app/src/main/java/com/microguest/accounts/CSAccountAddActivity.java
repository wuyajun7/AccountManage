package com.microguest.accounts;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by wuyajun on 15/8/9.
 * 添加账号界面
 */
public class CSAccountAddActivity extends AccountAuthenticatorActivity {

    private Activity self = null;

    private Button mDone = null;
    private EditText mUsername = null;
    private EditText mPassword = null;
    private EditText mTtoken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        self = this;

        mUsername = (EditText) findViewById(R.id.new_account_username);
        mPassword = (EditText) findViewById(R.id.new_account_password);
        mTtoken = (EditText) findViewById(R.id.new_account_token);

        mDone = (Button) findViewById(R.id.new_account_done);

        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String username = mUsername.getText().toString();
                String passoword = mPassword.getText().toString();
                String token = mTtoken.getText().toString();

                //Account--指定账户名和账户类型
                Account account = new Account(username, getString(R.string.ACCOUNT_TYPE));
                //取得AccountManager
                AccountManager am = AccountManager.get(self);

                //服务器数据-T0KEN
                Bundle userdata = new Bundle();
                userdata.putString(AccountManager.KEY_ACCOUNT_NAME, username);
                userdata.putString(AccountManager.KEY_ACCOUNT_TYPE, getString(R.string.ACCOUNT_TYPE));
                userdata.putString(AccountManager.KEY_AUTHTOKEN, token);

                //添加一个账户
                if (am.addAccountExplicitly(account, passoword, userdata)) {
                    setAccountAuthenticatorResult(userdata);
                    finish();
                } else {
                    Toast.makeText(self, "账号添加失败", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}

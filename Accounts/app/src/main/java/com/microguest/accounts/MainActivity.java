package com.microguest.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.microguest.accounts.adapters.AccountAdapter;
import com.microguest.accounts.domain.AccountsBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyajun on 15/8/9.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Context mContext = null;
    private AccountManager mAccountManager = null;

    private Button mAdd_account = null;
    private Button mDel_account = null;
    private EditText mDel_Account_ET_Name = null;
    private ListView mAccount_list = null;

    private AccountAdapter mAccountAdapter;
    private AccountsBean mAccountsBean = null;
    private List<AccountsBean> mBeanList = new ArrayList<>();

    private Account mDelAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initSysMethed();

        setUpViews();
        setUpListener();
        getData();
        setDataToViews();
    }

    private void initSysMethed() {
        /* 取得AccountManager对象 */
        mAccountManager = AccountManager.get(mContext);
    }

    protected void setUpViews() {
        mAdd_account = (Button) findViewById(R.id.add_account);
        mDel_account = (Button) findViewById(R.id.del_account);
        mDel_Account_ET_Name = (EditText) findViewById(R.id.account);

        mAccount_list = (ListView) findViewById(R.id.account_list);

        mAccountAdapter = new AccountAdapter(mContext, mBeanList, R.layout.view_account_list_item);
        mAccount_list.setAdapter(mAccountAdapter);
    }

    protected void setUpListener() {
        mAdd_account.setOnClickListener(this);
        mDel_account.setOnClickListener(this);
        mAccount_list.setOnItemClickListener(new OnItemClickListener());
    }

    class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AccountsBean accountsBean = mAccountAdapter.getItem(position);
            if (accountsBean != null) {
                mDelAccount = accountsBean.getUserAccount();
                mDel_Account_ET_Name.setText(accountsBean.userName);
            }
        }
    }

    protected void getData() {
        mBeanList.clear();
        /* 显示出所有账户 - 得到指定类型的账户 */
        Account[] accounts = mAccountManager.getAccountsByType(getString(R.string.ACCOUNT_TYPE));
        for (Account account : accounts) {
            /* 获取 Token */
            String token = mAccountManager.getUserData(account, AccountManager.KEY_AUTHTOKEN);

            mAccountsBean = new AccountsBean();
            mAccountsBean.setUserId("");
            mAccountsBean.setUserName(account.name);
            mAccountsBean.setUserPwd("");
            mAccountsBean.setUserToken(token);
            mAccountsBean.setUserAccount(account);

            mBeanList.add(mAccountsBean);

        }
    }

    protected void setDataToViews() {
        mAccountAdapter.notifyDataSetChanged();
    }

    protected void refreshListData() {
        getData();
        setDataToViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_account:

                mAccountManager.addAccount(getString(R.string.ACCOUNT_TYPE), null, null, null, MainActivity.this, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> amfuture) {
                        try {
                            Log.d(TAG, amfuture.getResult().toString());
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                        refreshListData();
                    }
                }, null);

                break;
            case R.id.del_account:

                if (mDelAccount == null) {
                    Toast.makeText(mContext, "请选择账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAccountManager.removeAccount(mDelAccount, new AccountManagerCallback<Boolean>() {
                    public void run(AccountManagerFuture<Boolean> future) {
                        try {
                            if (future.getResult() == true) {
                                Toast.makeText(mContext, "删除账号成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "删除账号失败", Toast.LENGTH_SHORT).show();
                            }

                            mDelAccount = null;
                            mDel_Account_ET_Name.setText("");

                            refreshListData();

                        } catch (OperationCanceledException e) {
                        } catch (IOException e) {
                        } catch (AuthenticatorException e) {
                        }
                    }
                }, null);

                break;
        }
    }
}
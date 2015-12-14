package com.microguest.accounts.adapters;

import android.content.Context;
import android.widget.TextView;

import com.microguest.accounts.R;
import com.microguest.accounts.adapters.wnadapter.WNBaseAdapter;
import com.microguest.accounts.adapters.wnadapter.WNViewHolder;
import com.microguest.accounts.domain.AccountsBean;

import java.util.List;

/**
 * Created by wuyajun on 15/8/9.
 */
public class AccountAdapter extends WNBaseAdapter<AccountsBean> {

    private Context mContext;

    public AccountAdapter(Context context, List<?> datas, int itemId) {
        super(context, datas, itemId);
        this.mContext = context;
    }

    @Override
    public void convertView(WNViewHolder holder, AccountsBean accountsBean, int position) {

        TextView userName = holder.getView(R.id.user_name);
        TextView userToken = holder.getView(R.id.user_token);

        userName.setText(String.format(mContext.getString(R.string.username_format),
                accountsBean.userName));
        userToken.setText(String.format(mContext.getString(R.string.usertoken_format),
                accountsBean.userToken));
    }
}

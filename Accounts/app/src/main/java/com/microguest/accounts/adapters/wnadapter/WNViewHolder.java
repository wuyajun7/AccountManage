package com.microguest.accounts.adapters.wnadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wuyajun on 15/6/19.
 * 万能适配器 - ViewHolder
 */
public class WNViewHolder {

    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public WNViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mViews = new SparseArray<View>();
        mPosition = position;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static WNViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new WNViewHolder(context, parent, layoutId, position);
        } else {
            WNViewHolder holder = (WNViewHolder) convertView.getTag();
            return holder;
        }
    }

    public int getPosition() {
        return mPosition;
    }

    /**
     * 通过 viewId 获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 返回布局
     *
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 链式设置TextView的值ֵ
     *
     * @param viewId
     * @param text
     * @return
     */
    public WNViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 链式设置ImageView的值 ֵ
     *
     * @param viewId
     * @param resId
     * @return
     */
    public WNViewHolder setImageResource(int viewId, int resId) {
        ImageView tv = getView(viewId);
        tv.setImageResource(resId);
        return this;
    }

}

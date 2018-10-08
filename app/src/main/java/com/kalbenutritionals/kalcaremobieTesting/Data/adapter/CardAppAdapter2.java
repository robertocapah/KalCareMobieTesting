package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;

import java.util.List;

public class CardAppAdapter2 extends BaseAdapter {
    private Context context;
    private List<String> mAppList;
    int color;

    public CardAppAdapter2(Context context, List<String> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public String getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_app2, null);
            new ViewHolder(convertView);
        }
        String item = getItem(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_name.setText(item);
        holder.cardView.setCardBackgroundColor(color);
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        CardView cardView;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            tv_name = (TextView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.tv_name);
            cardView = (CardView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.cdv_list);

            iv_icon.setVisibility(View.GONE);
            view.setTag(this);
        }
    }
}

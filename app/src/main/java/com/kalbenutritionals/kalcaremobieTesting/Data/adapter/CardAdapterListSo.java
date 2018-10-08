package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMLIstSo;

import java.util.List;

/**
 * Created by Robert on 26/03/2018.
 */

public class CardAdapterListSo extends BaseAdapter {
    private Context context;
    private List<VMLIstSo> mAppList;
    int color;

    public CardAdapterListSo(Context context, List<VMLIstSo> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public VMLIstSo getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_sohome, null);
            new ViewHolder(convertView);
        }
        VMLIstSo item = getItem(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tv_name.setText(item.getTxtCustomerName());
        holder.cardView.setCardBackgroundColor(color);
        holder.noSo.setText(item.getTxtNoSo());
        holder.status.setText(item.getTxtStatus());
        if (item.getTxtStatus().equals("APPROVED")){
            holder.status.setTextColor(Color.GREEN);
        }else{
            holder.status.setTextColor(Color.GRAY);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name, noSo, status;
        CardView cardView;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            cardView = (CardView) view.findViewById(R.id.cdv_list);
            noSo = (TextView) view.findViewById(R.id.tvNoSo);
            status = (TextView) view.findViewById(R.id.tvStatus);

            iv_icon.setVisibility(View.GONE);
            view.setTag(this);
        }
    }
}

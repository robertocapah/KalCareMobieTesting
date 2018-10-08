package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItems;

import java.util.List;

/**
 * Created by Robert on 26/02/2018.
 */

public class CardAdapterSearchResult extends BaseAdapter {
    private Context context;
    private List<VMItems> mAppList;
    int color;
    public CardAdapterSearchResult(Context context, List<VMItems> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public VMItems getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_product_search, null);
            new CardAdapterSearchResult.ViewHolder(convertView);
        }
        VMItems item = getItem(position);
        CardAdapterSearchResult.ViewHolder holder = (CardAdapterSearchResult.ViewHolder) convertView.getTag();
        holder.tvItemName.setText(String.valueOf(item.getItemName()));
        holder.tvitemPrice.setText(String.valueOf(item.getPrice()));
        holder.tvItemGroupName.setText(item.getItemCode());
        holder.tvDesc.setText(item.getDesc());
        holder.cardView.setCardBackgroundColor(color);
        return convertView;
    }

    class ViewHolder {
        TextView tvHeaderProductName,tvDesc, tvItemName, tvItemGroupName, tvitemPrice;
        CardView cardView;
        ImageView iv_icon;
        public ViewHolder(View view) {
            tvHeaderProductName = (TextView) view.findViewById(R.id.tvHeaderProductName);
//            tvItemCode = (TextView) view.findViewById(R.id.tvItemCode);
            tvDesc = (TextView) view.findViewById(R.id.tv_desc_search);
            tvItemGroupName = (TextView) view.findViewById(R.id.tv_groupname_search);
            tvItemName = (TextView) view.findViewById(R.id.tv_name_search);
            tvitemPrice = (TextView) view.findViewById(R.id.tv_price_search);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_list_product_search);
            iv_icon.setVisibility(View.GONE);
            tvitemPrice.setVisibility(View.GONE);
            tvDesc.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
}

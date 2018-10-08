package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMOutlets;

import java.util.List;

/**
 * Created by Robert on 09/03/2018.
 */

public class CardAdapterOutet extends BaseAdapter {
    private Context context;
    private List<VMOutlets> mAppList;
    int color;
    public CardAdapterOutet(Context context, List<VMOutlets> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public VMOutlets getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_outlet, null);
            new CardAdapterOutet.ViewHolder(convertView);
        }
        VMOutlets item = getItem(position);
        CardAdapterOutet.ViewHolder holder = (CardAdapterOutet.ViewHolder) convertView.getTag();
        holder.tvOutletName.setText(String.valueOf(item.getTxtNamaInstitusi()));
        holder.tvOutletAlamat.setText(String.valueOf(item.getTxtAlamat()));
        holder.tvOutletCode.setText(item.getTxtSumberDataID());
        holder.cardView.setCardBackgroundColor(color);
        return convertView;
    }

    class ViewHolder {
        TextView tvOutletName,tvOutletCode, tvOutletAlamat;
        CardView cardView;
        ImageView iv_icon;
        public ViewHolder(View view) {
            tvOutletName = (TextView) view.findViewById(R.id.tvOutletName);
//            tvItemCode = (TextView) view.findViewById(R.id.tvItemCode);
            tvOutletCode = (TextView) view.findViewById(R.id.tvOutletCode);
            tvOutletAlamat = (TextView) view.findViewById(R.id.tvOutletAlamat);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_outlet_list);
            iv_icon.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
}

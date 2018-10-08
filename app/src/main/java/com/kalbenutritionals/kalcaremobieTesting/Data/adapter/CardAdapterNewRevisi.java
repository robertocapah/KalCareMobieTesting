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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Robert on 31/07/2018.
 */

public class CardAdapterNewRevisi extends BaseAdapter {
    private Context context;
    private List<VMItems> mAppList;
    int color;
    public CardAdapterNewRevisi(Context context, List<VMItems> mAppList, int color){
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
            convertView = View.inflate(context, R.layout.card_list_app_revisi, null);
            new CardAdapterNewRevisi.ViewHolder(convertView);
        }
        VMItems item = getItem(position);
        CardAdapterNewRevisi.ViewHolder holder = (CardAdapterNewRevisi.ViewHolder) convertView.getTag();
        holder.tvItemName.setText(item.getItemName());
        holder.tvHeaderProductName.setText(item.getItemBrand()+" - "+item.getTxtItemPacketID());
        holder.tvQty.setText(String.valueOf(item.getQty()));

        String itemPrice = new DecimalFormat("##.##").format(item.getPrice());
        String itemDiscount = new DecimalFormat("##.##").format(item.getDiscount());
        String totalPrice = new DecimalFormat("##.##").format(item.getTotalPrice());
        String taxAmount = new DecimalFormat("##.##").format(item.getTaxAmount());
        String netPrice = new DecimalFormat("##.##").format(item.getNetPrice());

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMaximumFractionDigits(2);
        formatRupiah.setMinimumFractionDigits(2);
        DecimalFormat twoDForm = new DecimalFormat("#");
        Double a = Double.valueOf(twoDForm.format(item.getNetPrice()));
        holder.tvNetPrice.setText(formatRupiah.format(a));
        double basePoint = Double.parseDouble(item.getBasePoint());
        int intBasePoint = (int)basePoint;
        double bonusPoint = Double.parseDouble(item.getBonusPoint());
        int intBonusPoint = (int) bonusPoint;
        holder.tvBasePoint.setText(String.valueOf(intBasePoint));
        holder.tvBonusPoint.setText(String.valueOf(intBonusPoint));
        holder.cardView.setCardBackgroundColor(color);
        return convertView;
    }

    class ViewHolder {
        TextView tvHeaderProductName,tvItemName, tvQty, tvNetPrice, tvBasePoint, tvBonusPoint;
        CardView cardView;
        ImageView iv_icon;
        public ViewHolder(View view) {
            tvHeaderProductName = (TextView) view.findViewById(R.id.tvHeaderProductName);
            tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            tvQty = (TextView) view.findViewById(R.id.tvQty);
            tvNetPrice = (TextView) view.findViewById(R.id.tvNetPrice);
            tvBasePoint = (TextView) view.findViewById(R.id.tvBasePoint);
            tvBonusPoint = (TextView) view.findViewById(R.id.tvBonusPoint);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_list);
            iv_icon.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
}

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
 * Created by Dewi Oktaviani on 1/17/2018.
 */

public class CardAppAdapter extends BaseAdapter {
    private Context context;
    private List<VMItems> mAppList;
    int color;
    public CardAppAdapter(Context context, List<VMItems> mAppList, int color){
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
            convertView = View.inflate(context, R.layout.card_list_app, null);
            new ViewHolder(convertView);
        }
        VMItems item = getItem(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tvItemName.setText(item.getItemName());
        holder.tvHeaderProductName.setText(item.getItemBrand()+" - "+item.getTxtItemPacketID());
        holder.tvItemCode.setText(item.getItemCode());
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
        holder.tvitemPrice.setText(formatRupiah.format((double) item.getPrice()));
        holder.tvDiscount.setText(formatRupiah.format((double)item.getDiscount()));
        holder.tvTotalPrice.setText(formatRupiah.format((double)item.getTotalPrice()));
        holder.tvTaxAmount.setText(formatRupiah.format((double)item.getTaxAmount()));
        holder.tvNetPrice.setText(formatRupiah.format(a));

        /*holder.tvitemPrice.setText(itemPrice);
        holder.tvDiscount.setText(itemDiscount);
        holder.tvTotalPrice.setText(totalPrice);
        holder.tvTaxAmount.setText(taxAmount);
        holder.tvNetPrice.setText(netPrice);
        */
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
        TextView tvHeaderProductName,tvItemCode, tvItemName, tvQty, tvitemPrice, tvDiscount, tvTotalPrice, tvTaxAmount, tvNetPrice, tvBasePoint, tvBonusPoint;
        CardView cardView;
        ImageView iv_icon;
        public ViewHolder(View view) {
            tvHeaderProductName = (TextView) view.findViewById(R.id.tvHeaderProductName);
            tvItemCode = (TextView) view.findViewById(R.id.tvItemCode);
            tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            tvQty = (TextView) view.findViewById(R.id.tvQty);
            tvitemPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvDiscount = (TextView) view.findViewById(R.id.tvDiscount);
            tvTotalPrice = (TextView) view.findViewById(R.id.tvTotalPrice);
            tvTaxAmount = (TextView) view.findViewById(R.id.tvTax);
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

package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItemsPreview;

import java.util.List;

/**
 * Created by Robert on 22/03/2018.
 */

public class CardAppAdapterPreview extends BaseAdapter {
    private Context context;
    private List<VMItemsPreview> mAppList;
    int color;
    public CardAppAdapterPreview(Context context, List<VMItemsPreview> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public VMItemsPreview getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_preview, null);
            new CardAppAdapterPreview.ViewHolder(convertView);
        }
        VMItemsPreview item = getItem(position);
        CardAppAdapterPreview.ViewHolder holder = (CardAppAdapterPreview.ViewHolder) convertView.getTag();

        double dblQty = Double.parseDouble(item.getQty());
        int intQty = (int) dblQty;
        holder.tvintQTY.setText(String.valueOf(intQty));
        holder.tvtxtProductCode.setText(item.getProductCode());
        holder.tvtxtProductDesc.setText(item.getProductName());


        if (item.getTxtIntFlag().equals("0")){
            holder.cardView.setCardBackgroundColor(color);
        }else{
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        }
        if (!item.isBitAvailable()){
            holder.tvStatus.setText("Not available");
            holder.tvStatus.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvtxtProductDesc.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvtxtProductCode.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvintQTY.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvintQtyAvailable.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvtxtPartnerName.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvtxtPartnerAddress.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tvtxtPartnerPhone.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv1.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv2.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv3.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv4.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv5.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv6.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv7.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.tv8.setTextColor(ContextCompat.getColor(context,R.color.red));
//            holder.cardView.setCardBackgroundColor(Color.RED);
        }else{
            holder.tvStatus.setText("Available");
        }

        holder.tvintQtyAvailable.setText(String.valueOf(item.getQtyAvailable()));
        holder.tvtxtPartnerName.setText(String.valueOf(item.getPartnerName()));
        holder.tvtxtPartnerAddress.setText(String.valueOf(item.getPartnerAddress()));
        holder.tvtxtPartnerPhone.setText(item.getPartnerPhone());

        return convertView;
    }

    class ViewHolder {
        TextView tvintQTY,tvtxtProductCode, tvdecAmount, tvStatus, tvintQtyAvailable, tvtxtPartnerAddress, tvtxtPartnerID, tvtxtPartnerName, tvtxtPartnerPhone, tvtxtProductDesc, tvintPriority;
        CardView cardView;
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
        ImageView iv_icon;
        public ViewHolder(View view) {
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            tv5 = (TextView) view.findViewById(R.id.tv5);
            tv6 = (TextView) view.findViewById(R.id.tv6);
            tv7 = (TextView) view.findViewById(R.id.tv7);
            tv8 = (TextView) view.findViewById(R.id.tv8);
            tvintQTY = (TextView) view.findViewById(R.id.tvQty);
            tvtxtProductCode = (TextView) view.findViewById(R.id.tvItemCode);
            tvtxtProductDesc = (TextView) view.findViewById(R.id.tvItemName);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            tvintQtyAvailable = (TextView) view.findViewById(R.id.tvQtyAvailable);
            tvtxtPartnerName = (TextView) view.findViewById(R.id.tvPartnerName);
            tvtxtPartnerAddress = (TextView) view.findViewById(R.id.tvPartnerAddress);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_list);
            tvtxtPartnerPhone = (TextView) view.findViewById(R.id.tvPartnerPhone);
            iv_icon.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
}

package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItems;

import java.util.List;

/**
 * Created by Robert on 26/03/2018.
 */

public class RVParentAdapter extends RecyclerView.Adapter<RVParentAdapter.MyViewHolder> {
    private Context context;
    private List<VMItems> mAppList;
    int color;
    private ItemClickListener mClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHeaderProductName,tvItemCode, tvItemName, tvQty, tvitemPrice, tvDiscount, tvTotalPrice, tvTaxAmount, tvNetPrice, tvBasePoint, tvBonusPoint;
        CardView cardView;
        ImageView iv_icon;
        private ItemClickListener mClickListener;
        public MyViewHolder(View view) {
            super(view);
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
        }
    }
    public RVParentAdapter(Context context, List<VMItems> mAppList, int color) {
        this.mAppList = mAppList;
        this.color = color;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_app, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VMItems item = mAppList.get(position);
        holder.tvItemName.setText(item.getItemName());
        holder.tvHeaderProductName.setText(item.getItemName());
        holder.tvItemCode.setText(item.getItemCode());
        holder.tvQty.setText(String.valueOf(item.getQty()));
        holder.tvitemPrice.setText(String.valueOf(item.getPrice()));
        holder.tvDiscount.setText(String.valueOf(item.getDiscount()));
        holder.tvTotalPrice.setText(String.valueOf(item.getTotalPrice()));
        holder.tvTaxAmount.setText(String.valueOf(item.getTaxAmount()));
        holder.tvNetPrice.setText(String.valueOf(item.getNetPrice()));
        holder.tvBasePoint.setText(item.getBasePoint());
        holder.tvBonusPoint.setText(item.getBonusPoint());
        holder.cardView.setCardBackgroundColor(color);
    }


    @Override
    public int getItemCount() {
        return mAppList.size();
    }
    // convenience method for getting data at click position
    VMItems getItem(int id) {
        return mAppList.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

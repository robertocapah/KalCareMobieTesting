package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItemsPreview;

import java.util.List;

/**
 * Created by Robert on 26/03/2018.
 */

public class RVPreviewAdapter extends RecyclerView.Adapter<RVPreviewAdapter.MyViewHolder> {
    private Context context;
    private List<VMItemsPreview> mAppList;
    int color;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvintQTY,tvtxtProductCode, tvdecAmount, tvStatus, tvintQtyAvailable, tvtxtPartnerAddress, tvtxtPartnerID, tvtxtPartnerName, tvtxtPartnerPhone, tvtxtProductDesc, tvintPriority;
        CardView cardView;
        ImageView iv_icon;
        public MyViewHolder(View view) {
            super(view);
            tvintQTY = (TextView) view.findViewById(R.id.tvQty);
            tvtxtProductCode = (TextView) view.findViewById(R.id.tvItemCode);
            tvtxtProductDesc = (TextView) view.findViewById(R.id.tvItemName);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
            tvintQtyAvailable = (TextView) view.findViewById(R.id.tvQtyAvailable);
            tvtxtPartnerName = (TextView) view.findViewById(R.id.tvPartnerName);
            tvtxtPartnerAddress = (TextView) view.findViewById(R.id.tvPartnerAddress);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_list);
            iv_icon.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
    public RVPreviewAdapter(Context context, List<VMItemsPreview> mAppList, int color) {
        this.mAppList = mAppList;
        this.color = color;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_preview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RVPreviewAdapter.MyViewHolder holder, int position) {
        VMItemsPreview item = mAppList.get(position);
        holder.tvintQTY.setText(item.getQty());
        holder.tvtxtProductCode.setText(item.getProductCode());
        holder.tvtxtProductDesc.setText(item.getProductName());
        if (item.getTxtIntFlag().equals("0")){
            holder.tvStatus.setText("Not available");
            holder.cardView.setCardBackgroundColor(color);
        }else{
            holder.tvStatus.setText("Available");
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        }

        holder.tvintQtyAvailable.setText(String.valueOf(item.getQtyAvailable()));
        holder.tvtxtPartnerName.setText(String.valueOf(item.getPartnerName()));
        holder.tvtxtPartnerAddress.setText(String.valueOf(item.getPartnerAddress()));

    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}

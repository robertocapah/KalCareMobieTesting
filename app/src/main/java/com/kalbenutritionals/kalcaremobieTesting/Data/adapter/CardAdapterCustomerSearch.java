package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMCustomers;

import java.util.List;

/**
 * Created by Robert on 28/02/2018.
 */

public class CardAdapterCustomerSearch extends BaseAdapter {
    private Context context;
    private List<VMCustomers> mAppList;
    int color;
    public CardAdapterCustomerSearch(Context context, List<VMCustomers> mAppList, int color){
        this.context = context;
        this.mAppList = mAppList;
        this.color = color;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public VMCustomers getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.card_list_customer_search, null);
            new CardAdapterCustomerSearch.ViewHolder(convertView);
        }
        VMCustomers item = getItem(position);
        CardAdapterCustomerSearch.ViewHolder holder = (CardAdapterCustomerSearch.ViewHolder) convertView.getTag();
        holder.txtContactID.setText(String.valueOf(item.getTxtContactID()));
        holder.txtAlamat.setText(String.valueOf(item.getTxtAlamat()));
        holder.txtHeaderCustomerName.setText(item.getTxtNama());
        holder.txtNama.setText(item.getTxtNama());
        holder.txtKabKot.setText(item.getTxtKabKot());
        holder.txtProv.setText(String.valueOf(item.getTxtProv()));
        holder.txtjenisAlm.setText(String.valueOf(item.getTxtjenisAlm()));
        holder.txtListMed.setText(item.getTxtListMed());
        holder.txtKec.setText(item.getTxtKec());
        holder.txtKel.setText(String.valueOf(item.getTxtKel()));
        holder.txtNHDSiteID.setText(String.valueOf(item.getTxtNHDSiteID()));
        holder.txtProvID.setText(item.getTxtProvID());
        holder.txtKABKotID.setText(item.getTxtKABKotID());
        holder.txtKodePos.setText(String.valueOf(item.getTxtKodePos()));
        holder.cardView.setCardBackgroundColor(color);
        return convertView;
    }

    class ViewHolder {
        TextView txtContactID;
        TextView txtAlamat;
        TextView txtNama;
        TextView txtKabKot;
        TextView txtProv;
        TextView txtjenisAlm;
        TextView txtListMed;
        TextView txtKec;
        TextView txtKel;
        TextView txtNHDSiteID;
        TextView txtProvID;
        TextView txtKABKotID;
        TextView txtKecID;
        TextView txtKodePos;
        TextView txtHeaderCustomerName;
        CardView cardView;
        ImageView iv_icon;
        public ViewHolder(View view) {
            txtHeaderCustomerName = (TextView) view.findViewById(R.id.tvHeaderCustomerName);
            txtContactID = (TextView) view.findViewById(R.id.tvContactIDCst);
//            tvItemCode = (TextView) view.findViewById(R.id.tvItemCode);
            txtAlamat = (TextView) view.findViewById(R.id.tvAlamatCst);
            txtNama = (TextView) view.findViewById(R.id.tvNamaCst);
            txtKabKot = (TextView) view.findViewById(R.id.tvKabKotCst);
            txtProv = (TextView) view.findViewById(R.id.tvProvinsiCst);
            txtjenisAlm = (TextView) view.findViewById(R.id.tvJenisAlmCst);
            txtListMed = (TextView) view.findViewById(R.id.tvListmediaCst);
            txtKec = (TextView) view.findViewById(R.id.tvKecamatanCst);
            txtKel = (TextView) view.findViewById(R.id.tvKelurahanCst);
            txtNHDSiteID = (TextView) view.findViewById(R.id.tvNHDSiteIDCst);
            txtProvID = (TextView) view.findViewById(R.id.tvProvIDCst);
            txtKABKotID = (TextView) view.findViewById(R.id.tvKabKotIDCst);
//            txtKecID = (TextView) view.findViewById(R.id.tvkec);
            txtKodePos = (TextView) view.findViewById(R.id.tvKodePosCst);
            iv_icon = (ImageView) view.findViewById(com.kalbe.mobiledevknlibs.R.id.iv_icon);
            cardView = (CardView) view.findViewById(R.id.cdv_list_customer_search);
            iv_icon.setVisibility(View.GONE);

            view.setTag(this);
        }
    }
}

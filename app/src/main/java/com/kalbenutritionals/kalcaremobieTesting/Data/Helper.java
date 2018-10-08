package com.kalbenutritionals.kalcaremobieTesting.Data;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.Common.clsDraft;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsProductDraft;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsProductDraftRepo;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Robert on 21/02/2018.
 */

public class Helper {
    public static void setTableLayout(Context context, TableLayout tableLayout, String[] colTextHeader, ArrayList<String[]> colData) {
        tableLayout.removeAllViews();

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);

        TableRow tr = new TableRow(context);

        TableLayout tl = new TableLayout(context);

        for (String text : colTextHeader) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            tv.setTextSize(14);
            tv.setPadding(10, 10, 10, 10);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#4CAF50"));
            tv.setTextColor(Color.WHITE);
            tr.addView(tv,params);
        }
        tl.addView(tr);
        for(String[] data : colData){
            tr = new TableRow(context);
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=0;
            int topMargin=0;
            int rightMargin=0;
            int bottomMargin=0;
            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            tr.setLayoutParams(tableRowParams);
            for (String dt : data){
                TextView tv = new TextView(context);
                tv.setTextSize(12);
                tv.setWidth(100);
                tv.setPadding(10, 10, 10, 10);
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextColor(Color.BLACK);
                tv.setText(dt);
                tr.addView(tv,params);
            }
            tl.addView(tr, tableRowParams);
        }

        tableLayout.addView(tl);
    }
    public String GenerateGuid() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }
    public Date ConvertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }
    public JSONArray writeJSONSaveData(Context context, clsDraft draft, List<VMItems> contentLibs){
        boolean saveDraftResult = false;
//        new clsProductDraftRepo(context).clearAllData();
        JSONArray items = new JSONArray();
        for (VMItems item : contentLibs) {
            String guiid = new Helper().GenerateGuid();
            JSONObject jsnItem = new JSONObject();
            clsProductDraft product = new clsProductDraft();
            product.setTxtDraftGUI(item.getGuiid());
            product.setTxtProductDraftGUI(guiid);
            product.setTxtItemName(item.getItemName());
            product.setTxtItemCode(item.getItemCode());
            product.setDblItemDiscount(item.getDiscount());
            product.setDblPrice(item.getPrice());
            product.setDblNetPrice(item.getNetPrice());
            product.setIntQty(item.getQty());
            product.setDblItemTax(item.getTaxAmount());
            product.setDblTotalPrice(item.getTotalPrice());
            String strDecBasePoint = item.getBasePoint();
            double decPrice = item.getPrice();
            double decBasePoint = 0;
            if (!strDecBasePoint.equals("") || !strDecBasePoint.equals("null")){
                decBasePoint = Double.parseDouble(item.getBasePoint());
            }
            double decDiscount = item.getDiscount();
            double intTotalBonus = 0;
            int intCampagn = item.getIntCampagn();
            double decTax = item.getDectax();
            String strBonusPoint = item.getBonusPoint();
            if (!strBonusPoint.equals("") || !strBonusPoint.equals("null")){
                intTotalBonus = Double.parseDouble(item.getBonusPoint());
            }

            int qty = item.getQty();
            try {
                jsnItem.put("decQty",qty);
                jsnItem.put("ProductCode",item.getItemCode());
                jsnItem.put("decPrice",String.valueOf(decPrice));
                jsnItem.put("decBasePoint",decBasePoint);
                jsnItem.put("intTotBonusPoin",intTotalBonus);
                jsnItem.put("decDiscount",decDiscount);
                jsnItem.put("bitPromo","1");
                jsnItem.put("intCampaignID",intCampagn);
                jsnItem.put("decTax",decTax);
                jsnItem.put("txtProductCategory",item.getProductCategory());
                jsnItem.put("txtDataId",item.getGuiid());
                jsnItem.put("ProductCode",item.getItemCode());
                jsnItem.put("ProductName",item.getItemName());
                jsnItem.put("txtProductCategory",item.getProductCategory());
                jsnItem.put("txtBrand",item.getItemBrand());
                jsnItem.put("txtGroupProduct",item.getTxtGroupProduct());
                jsnItem.put("txtProductBarcode",item.getBarcode());
                jsnItem.put("txtGroupItem",item.getItemGroup());
                jsnItem.put("txtItemPacketID",item.getTxtItemPacketID());
                jsnItem.put("intItemID",item.getItemId());
                jsnItem.put("intBitPaket",item.getIntPaket());
                if (item.getIntPaket()==1){
                    jsnItem.put("decHJPOriginal",item.getDecHJPOriginal());
                }
                jsnItem.put("txtKN",item.getTxtKN());
                jsnItem.put("txtGroupKN",item.getTxtGroupKN());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            items.put(jsnItem);
//            new clsProductDraftRepo(context).createOrUpdate(product);
            saveDraftResult = true;
            //07/05/2018 kurang retrieve data item
        }
        return items;
    }
    public List<Date> getDatesBetweenUsingJava7(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }
    public int getDatesHolidayBetweenUsingJava7(Calendar calendar, Calendar endCalendar, List<Calendar> dateHoliday) {
//        List<Date> datesInRange = new ArrayList<>();
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startDate);
        calendar.add(Calendar.DATE,1);
//        Calendar endCalendar = new GregorianCalendar();
//        endCalendar.setTime(endDate);
        int a = 0;
        List<Date> dateResult = new ArrayList<>();
//        endCalendar.add(Calendar.DATE, 1);
        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
//            datesInRange.add(result);
            boolean weekend  = false;
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//                calendar.add(Calendar.DATE, 1);
                a++;
                endCalendar.add(Calendar.DATE, 1);
                weekend = true;
            }
            for (Calendar cal : dateHoliday){
                Calendar cal2 = new GregorianCalendar();
                cal2.setTime(result);
                if (isSameDay(cal,cal2) && !weekend){
//                    calendar.add();
                    a++;
                    endCalendar.add(Calendar.DATE, 1);
                }
            }
            calendar.add(Calendar.DATE, 1);
        }


        return a;
    }
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    public String formatRupiah(double number){
        String money = "";
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMaximumFractionDigits(2);
        formatRupiah.setMinimumFractionDigits(2);
        money = formatRupiah.format((double)number);
        return money;
    }
    public static void textWatcher(EditText et, final Activity activity){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                activity.onUserInteraction();
            }
        });
    }
    public JSONArray writeJSONSaveDataFinal(Context context, clsDraft draft, List<VMItems> contentLibs){
        boolean saveDraftResult = false;
        new clsProductDraftRepo(context).clearAllData();
        JSONArray items = new JSONArray();
        for (VMItems item : contentLibs) {
            String guiid = new Helper().GenerateGuid();
            JSONObject jsnItem = new JSONObject();
            clsProductDraft product = new clsProductDraft();
            product.setTxtDraftGUI(draft.getGuiId());
            product.setTxtProductDraftGUI(guiid);
            product.setTxtItemName(item.getItemName());
            product.setTxtItemCode(item.getItemCode());
            product.setDblItemDiscount(item.getDiscount());
            product.setDblPrice(item.getPrice());
            product.setDblNetPrice(item.getNetPrice());
            product.setIntQty(item.getQty());
            product.setDblItemTax(item.getTaxAmount());
            product.setDblTotalPrice(item.getTotalPrice());
            String strDecBasePoint = item.getBasePoint();
            double decPrice = item.getPrice();
            double decBasePoint = 0;
            if (!strDecBasePoint.equals("") || !strDecBasePoint.equals("null")){
                decBasePoint = Double.parseDouble(item.getBasePoint());
            }
            double decDiscount = item.getDiscount();
            double intTotalBonus = 0;
            String strBonusPoint = item.getBonusPoint();
            if (!strBonusPoint.equals("") || !strBonusPoint.equals("null")){
                intTotalBonus = Double.parseDouble(item.getBonusPoint());
            }

            int qty = item.getQty();
            try {
                jsnItem.put("decQty",qty);
                jsnItem.put("ProductCode",item.getItemCode());
                jsnItem.put("decPrice",decPrice);
                jsnItem.put("decBasePoint",decBasePoint);
                jsnItem.put("intTotBonusPoin",intTotalBonus);
                jsnItem.put("decDiscount",decDiscount);
                jsnItem.put("bitPromo","1");
                jsnItem.put("intCampaignID","3");
                jsnItem.put("txtProductCategory",item.getProductCategory());
                jsnItem.put("txtDataId",draft.getGuiId());
                jsnItem.put("ProductCode",item.getItemCode());
                jsnItem.put("ProductName",item.getItemName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            items.put(jsnItem);
//            new clsProductDraftRepo(context).createOrUpdate(product);
            saveDraftResult = true;
        }
        return items;
    }

}

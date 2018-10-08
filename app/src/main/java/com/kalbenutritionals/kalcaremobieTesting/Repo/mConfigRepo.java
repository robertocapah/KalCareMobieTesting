package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.kalbenutritionals.kalcaremobieTesting.Common.mConfigData;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roberto on 11/21/2017.
 */

public class mConfigRepo {
    DatabaseHelper helper;
    //pc Roberto
    public String strDomain = "192.168.137.42/WebAPIKalcare";
    //pc AAn
//    public String strDomain = "10.171.14.19:8018";
    //Testing Internet
//    public String strDomain = "appgwdev.kalbenutritionals.com/api/kalcaremobile";
    //LIVE
//    public String strDomain = "appgw.kalbenutritionals.com/api/kalcare";
    //production pakai https jika testing http
    public String API = "http://"+strDomain+"/NHD/";
    public String APIToken = "http://"+strDomain+"/token";
//
    //prod
//    public String API = "https://"+strDomain+"/NHD/";
//    public String APIToken = "https://"+strDomain+"/token";

    public mConfigRepo(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    public mConfigData findById(int value) throws SQLException {
        mConfigData item = null;
        try{
            item = helper.getmConfigDao().queryForId(value);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public List<?> findAll() throws SQLException {
        List<mConfigData> items = null;
        try{
            items = helper.getmConfigDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public void InsertDefaultmConfig() throws SQLException {
        mConfigData data1 = new mConfigData();
        data1.setIntId(1);
        data1.setTxtName("android:versionCode");
        data1.setTxtValue("5");
        data1.setTxtDefaultValue("5");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);

        data1 = new mConfigData();
        data1.setIntId(3);
        data1.setTxtName("Domain Kalbe");
        data1.setTxtValue("ONEKALBE.LOCAL");
        data1.setTxtDefaultValue("ONEKALBE.LOCAL");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);

        data1 = new mConfigData();
        data1.setIntId(4);
        data1.setTxtName("Application Name");
        data1.setTxtValue("Kal Care");
        data1.setTxtDefaultValue("3VyizZ7haX2KCvR0wl64YwulEteHqsq5FLncJSL+pBM=");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);

        data1 = new mConfigData();
        data1.setIntId(5);
        data1.setTxtName("UserId");
        data1.setTxtValue("rian.andrivani");
        data1.setTxtDefaultValue("rian.andrivani");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);


        data1 = new mConfigData();
        data1.setIntId(6);
        data1.setTxtName("Text Footer");
        data1.setTxtValue("Copyright &copy; KN IT 2018");
        data1.setTxtDefaultValue("Copyright &copy; KN IT 2018");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);

        data1 = new mConfigData();
        data1.setIntId(7);
        data1.setTxtName("Grant_type");
        data1.setTxtValue("password");
        data1.setTxtDefaultValue("password");
        data1.setIntEditAdmin("1");
        helper.getmConfigDao().createOrUpdate(data1);
    }
}

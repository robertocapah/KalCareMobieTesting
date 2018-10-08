package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rian Andrivani on 11/22/2017.
 */

public class clsUserLoginRepo implements crud {
    private DatabaseHelper helper;
    public clsUserLoginRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) {
        int index = -1;
        clsUserLogin object = (clsUserLogin) item;
        try {
            index = helper.getLoginDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }
    public clsUserLogin getDataLogin(Context context){
        clsUserLoginRepo repo = new clsUserLoginRepo(context);
        clsUserLogin dataLogin =null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String now = dateFormat.format(cal.getTime()).toString();
//        if(repo.CheckLoginNow()){
        List<clsUserLogin> listData= null;
        try {
            listData = helper.getLoginDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (clsUserLogin data : listData){
            /*if (data.getDtLogin().equals(now)){
                dataLogin = data;
            }*/
            if (data != null){
                dataLogin = data;
            }

        }

//        }
        return dataLogin;
    }
    public clsUserLogin getDataLoginToday(Context context){
        clsUserLoginRepo repo = new clsUserLoginRepo(context);
        clsUserLogin dataLogin =null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String now = dateFormat.format(cal.getTime()).toString();

//        if(repo.CheckLoginNow()){
        List<clsUserLogin> listData= null;
        try {
            listData = helper.getLoginDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (clsUserLogin data : listData){
            Date dtLogin = data.getDtLogin();
            String txtDtlogin = dateFormat.format(dtLogin);
            if (txtDtlogin.equals(now)){
                dataLogin = data;
            }else{
                dataLogin = null;
            }
            /*if (data != null){
                dataLogin = data;
            }*/

        }

//        }
        return dataLogin;
    }
    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsUserLogin object = (clsUserLogin) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getLoginDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
//            index = 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;
        clsUserLogin object = (clsUserLogin) item;
        try {
            index = helper.getLoginDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;
        clsUserLogin object = (clsUserLogin) item;
        try {
            index = helper.getLoginDao().delete(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsUserLogin item = null;
        try{
            item = helper.getLoginDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() throws SQLException {
        List<clsUserLogin> items = null;
        try{
            items = helper.getLoginDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

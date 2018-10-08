package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsCustomerData;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert on 26/03/2018.
 */

public class clsCustomerDataRepo implements crud {
    private DatabaseHelper helper;
    public clsCustomerDataRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;
        clsCustomerData object = (clsCustomerData) item;
        try {
            index = helper.getcustomerDataDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsCustomerData object = (clsCustomerData) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getcustomerDataDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
//            index = 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) {
        return 0;
    }

    @Override
    public int delete(Object item) {
        return 0;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsCustomerData item = null;
        try{
            item = helper.getcustomerDataDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<clsCustomerData> findAll() throws SQLException {
        List<clsCustomerData> items = null;
        try{
            items = helper.getcustomerDataDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public clsCustomerData findOne() throws SQLException {
        List<clsCustomerData> items = null;
        clsCustomerData item = new clsCustomerData();
        try{
            items = helper.getcustomerDataDao().queryForAll();
            for (clsCustomerData data : items){
                item = data;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }
}

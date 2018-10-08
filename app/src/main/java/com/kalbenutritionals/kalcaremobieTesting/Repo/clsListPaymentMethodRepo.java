package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListPaymentMethod;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert on 13/04/2018.
 */

public class clsListPaymentMethodRepo implements crud {

    private DatabaseHelper helper;
    public clsListPaymentMethodRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        clsListPaymentMethod object = (clsListPaymentMethod) item;
        try {
            index = helper.getclsListPaymentMethodDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        clsListPaymentMethod object = (clsListPaymentMethod) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getclsListPaymentMethodDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
//            index = 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Object item) throws SQLException {
        return 0;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsListPaymentMethod item = null;
        try{
            item = helper.getclsListPaymentMethodDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<clsListPaymentMethod> findAll() throws SQLException {
        List<clsListPaymentMethod> items = null;
        try{
            items = helper.getclsListPaymentMethodDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

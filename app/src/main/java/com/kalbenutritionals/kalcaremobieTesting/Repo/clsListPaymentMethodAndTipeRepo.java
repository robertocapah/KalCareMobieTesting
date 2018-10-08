package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListPaymentMethodAndTipe;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 20/04/2018.
 */

public class clsListPaymentMethodAndTipeRepo implements crud{
    private DatabaseHelper helper;
    public clsListPaymentMethodAndTipeRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        clsListPaymentMethodAndTipe object = (clsListPaymentMethodAndTipe) item;
        try {
            index = helper.getClsListPaymentMethodAndTipeDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        clsListPaymentMethodAndTipe object = (clsListPaymentMethodAndTipe) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getClsListPaymentMethodAndTipeDao().createOrUpdate(object);
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
        return null;
    }
    public List<clsListPaymentMethodAndTipe> findById2(String txtTypeId) throws SQLException {
        clsListPaymentMethodAndTipe item = new clsListPaymentMethodAndTipe();
        List<clsListPaymentMethodAndTipe> listData = new ArrayList<>();
        int intId = Integer.parseInt(txtTypeId);
        QueryBuilder<clsListPaymentMethodAndTipe, Integer> queryBuilder = null;
        try {
            queryBuilder = helper.getClsListPaymentMethodAndTipeDao().queryBuilder();
            queryBuilder.where().eq(item.Property_txtPaymentMethodId, txtTypeId);
            listData = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }
    @Override
    public List<?> findAll() throws SQLException {
        List<clsListPaymentMethodAndTipe> items = null;
        try{
            items = helper.getClsListPaymentMethodAndTipeDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsDraft;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert on 02/03/2018.
 */

public class clsDraftRepo implements crud{
    private DatabaseHelper helper;
    public clsDraftRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) {
        int index = -1;
        clsDraft object = (clsDraft) item;
        try {
            index = helper.getDraftDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }
    public void clearAllData(){
        helper.clearDataDraft();
    }

    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsDraft object = (clsDraft) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getDraftDao().createOrUpdate(object);
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
        clsDraft object = (clsDraft) item;
        try {
            index = helper.getDraftDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;
        clsDraft object = (clsDraft) item;
        try {
            index = helper.getDraftDao().delete(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    public clsDraft findByBitActive() throws SQLException {
        List<clsDraft> items = null;
        clsDraft itemPicked = null;
        try{
            //            QueryBuilder<clsAbsenData, Integer> queryBuilder = helper.getUserAbsenDao().queryBuilder();
//            queryBuilder.where().eq(dataAbsen.Property_dtCheckout, null).or().eq(dataAbsen.Property_dtCheckout, "");
//            listData = queryBuilder.query();

            QueryBuilder<clsDraft, Integer> qb = helper.getDraftDao().queryBuilder();
            Where where = qb.where();
            where.eq(clsDraft.txtPropertyIntStatus, 0);
            items = qb.query();
            if (items != null && items.size() > 0) {
                for (clsDraft item : items) {
                    itemPicked = item;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemPicked;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsDraft item = null;
        try{
            item = helper.getDraftDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() throws SQLException {
        List<clsDraft> items = null;
        try{
            items = helper.getDraftDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

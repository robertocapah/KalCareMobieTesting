package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsProductDraft;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert on 05/03/2018.
 */

public class clsProductDraftRepo implements crud{
    private DatabaseHelper helper;
    public clsProductDraftRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) {
        int index = -1;
        clsProductDraft object = (clsProductDraft) item;
        try {
            index = helper.getProductDraftDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsProductDraft object = (clsProductDraft) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getProductDraftDao().createOrUpdate(object);
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
        clsProductDraft object = (clsProductDraft) item;
        try {
            index = helper.getProductDraftDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;
        clsProductDraft object = (clsProductDraft) item;
        try {
            index = helper.getProductDraftDao().delete(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }
    public void clearAllData(){
        helper.clearDataProductDraft();
    }

    public clsProductDraft findByBitActive() throws SQLException {
        List<clsProductDraft> items = null;
        clsProductDraft itemPicked = null;
        try{
            //            QueryBuilder<clsAbsenData, Integer> queryBuilder = helper.getUserAbsenDao().queryBuilder();
//            queryBuilder.where().eq(dataAbsen.Property_dtCheckout, null).or().eq(dataAbsen.Property_dtCheckout, "");
//            listData = queryBuilder.query();

            QueryBuilder<clsProductDraft, Integer> qb = helper.getProductDraftDao().queryBuilder();
            Where where = qb.where();
//            where.eq(clsProductDraft.txtPropertyIntStatus, 1);
            items = qb.query();
            if (items != null && items.size() > 0) {
                for (clsProductDraft item : items) {
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
        clsProductDraft item = null;
        try{
            item = helper.getProductDraftDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<clsProductDraft> findAll() throws SQLException {
        List<clsProductDraft> items = null;
        try{
            items = helper.getProductDraftDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

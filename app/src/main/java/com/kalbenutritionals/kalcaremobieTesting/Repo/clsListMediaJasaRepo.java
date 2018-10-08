package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListMediaJasa;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 13/04/2018.
 */

public class clsListMediaJasaRepo implements crud {
    private DatabaseHelper helper;
    public clsListMediaJasaRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        clsListMediaJasa object = (clsListMediaJasa) item;
        try {
            index = helper.getclsListMediaJasaDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        clsListMediaJasa object = (clsListMediaJasa) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getclsListMediaJasaDao().createOrUpdate(object);
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
        clsListMediaJasa item = null;
        try{
            item = helper.getclsListMediaJasaDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }
    public List<clsListMediaJasa> findById2(String id) throws SQLException {
        clsListMediaJasa item = new clsListMediaJasa();
        List<clsListMediaJasa> listData = new ArrayList<>();
        QueryBuilder<clsListMediaJasa, Integer> queryBuilder = null;
        try {
            queryBuilder = helper.getclsListMediaJasaDao().queryBuilder();
            queryBuilder.where().eq(item.Property_StrTypeId, id);
            listData = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }

    @Override
    public List<clsListMediaJasa> findAll() throws SQLException {
        List<clsListMediaJasa> items = null;
        try{
            items = helper.getclsListMediaJasaDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

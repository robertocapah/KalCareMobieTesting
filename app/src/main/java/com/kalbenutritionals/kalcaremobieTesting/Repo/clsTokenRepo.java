package com.kalbenutritionals.kalcaremobieTesting.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsToken;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 1/29/2018.
 */

public class clsTokenRepo implements crud {
    private DatabaseHelper helper;
    public clsTokenRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {
        int index = -1;
        clsToken object = (clsToken) item;
        try {
            index = helper.getTokenDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsToken object = (clsToken) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getTokenDao().createOrUpdate(object);
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
        clsToken item = null;
        try{
            item = helper.getTokenDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<clsToken> findAll() throws SQLException {
        List<clsToken> items = null;
        try{
            items = helper.getTokenDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}

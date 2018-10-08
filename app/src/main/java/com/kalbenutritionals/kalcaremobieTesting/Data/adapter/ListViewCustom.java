package com.kalbenutritionals.kalcaremobieTesting.Data.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kalbe.mobiledevknlibs.ListView.AppAdapter;
import com.kalbe.mobiledevknlibs.ListView.AppAdapterNotif;
import com.kalbe.mobiledevknlibs.ListView.CardAppAdapter;
import com.kalbe.mobiledevknlibs.ListView.clsRowItem;
import com.kalbe.mobiledevknlibs.ListView.clsSwipeList;
import com.kalbe.mobiledevknlibs.library.PullToRefreshSwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dewi Oktaviani on 1/17/2018.
 */

public class ListViewCustom {
    public static AppAdapterNotif setListA(Context context, List<clsRowItem> items) {
        final AppAdapterNotif mAdapter;

        List<clsRowItem> mAppList = new ArrayList<clsRowItem>();

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                clsRowItem getswipeList = items.get(i);
                mAppList.add(i, getswipeList);
            }
        }
        mAdapter = new AppAdapterNotif(context, items);

        return mAdapter;
    }

    public static com.kalbe.mobiledevknlibs.ListView.CardAppAdapter setCardList(Context _ctx, final List<clsSwipeList> swipeList, int color, int format) {
        final com.kalbe.mobiledevknlibs.ListView.CardAppAdapter mAdapter;

        List<String> mAppList = new ArrayList<String>();

        for (int i = 0; i < swipeList.size(); i++) {
            clsSwipeList getswipeList = swipeList.get(i);
            switch (format){
                case 0:
                    mAppList.add(getswipeList.get_txtTitle() );
                    break;
                case 1:
                    mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription() );
                    break;
                case 2:
                    mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription() + "\n" + getswipeList.get_txtDescription2());
                    break;
                case 3:
                    mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription() + "\n" + getswipeList.get_txtDescription2() + "\n" + getswipeList.get_txtDescription3());
                    break;
                default:
                    mAppList.add(getswipeList.get_txtTitle());
            }

        }

        mAdapter = new CardAppAdapter(_ctx, mAppList,color);

        return mAdapter;

    }


    public static AppAdapter setList(Context _ctx, final List<clsSwipeList> swipeList) {
        final AppAdapter mAdapter;

        List<String> mAppList = new ArrayList<String>();

        for (int i = 0; i < swipeList.size(); i++) {
            clsSwipeList getswipeList = swipeList.get(i);
            mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription() );
        }

        mAdapter = new AppAdapter(_ctx, mAppList);

        return mAdapter;

    }

    public static AppAdapter setList2(Context _ctx, final List<clsSwipeList> swipeList) {
        final AppAdapter mAdapter;
        PullToRefreshSwipeMenuListView mListView;
        Handler mHandler;

        List<String> mAppList = new ArrayList<String>();

        for (int i = 0; i < swipeList.size(); i++) {
            clsSwipeList getswipeList = swipeList.get(i);
            mAppList.add(getswipeList.get_txtTitle() + "\n" + getswipeList.get_txtDescription() + "\n" + getswipeList.get_txtDescription2() + "\n" + getswipeList.get_txtDescription3());
        }

        mAdapter = new AppAdapter(_ctx, mAppList);

        return mAdapter;

    }

    public static boolean setListViewHeightBasedOnItems(Activity activity, ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        int screenSize = activity.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        String toastMsg;

        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();

            switch(screenSize) {
                case Configuration.SCREENLAYOUT_SIZE_LARGE:
                    toastMsg = "Large screen";
                    break;
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                    toastMsg = "Normal screen";
                    break;
                case Configuration.SCREENLAYOUT_SIZE_SMALL:
                    toastMsg = "Small screen";
                    break;
                default:
                    toastMsg = "Screen size is neither large, normal or small";
            }


            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
    public static boolean setListViewHeightBasedOnItems2(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}

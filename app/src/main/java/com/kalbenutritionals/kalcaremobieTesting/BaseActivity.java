package com.kalbenutritionals.kalcaremobieTesting;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;

import java.util.Timer;

/**
 * Created by Robert on 11/07/2018.
 */

public class BaseActivity extends AppCompatActivity {
    public static Timer timer;
    int intSession = 15;
    public static Handler myHandler = new Handler();
    private int TIME_TO_WAIT = 150000;
    Handler mHandler = new Handler(); Context context = this;
    boolean boolHandler = false;
    boolean boolLogOut = false;
    ToneGenerator toneGen1;
    ToneGenerator toneGen2;
    boolean boolTesting  = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(LoginActivity.Preference_Session, MODE_PRIVATE);
        String restoredText = prefs.getString("time", "15");
        if (restoredText != null) {
            String txtTime = prefs.getString("time", "15");
            if (!txtTime.equals("")) {
                int intTime = Integer.parseInt(txtTime);
                if (!boolTesting){
                    intSession = intTime * 60 * 1000;
                }else{
                    intSession = 15 * 60 * 1000;
                }

            }
        }
        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        toneGen2 = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        TIME_TO_WAIT = intSession;
        startUserSession();

    }

    void cancelTimer() {
        /*if (mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
            boolHandler = true;
        }*/
        if (timer != null) {
            timer.cancel();
        }
    }


    public void startUserSession() {
        if (!boolLogOut){
            restart();
        }
//        runHandler();
        /*timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logout();
            }
        }, intSession);*/
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        restart();
        super.onPause();
    }

    public void logout() {
        boolLogOut = true;
//        toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT,200);
        stop();
//        cancelTimer();
/*        if (isMyServiceRunning(MyNotification.class)) {
            stopService(new Intent(getApplicationContext(), MyNotification.class));
        }*/
//        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        /*FragmentInformation homeFragment = new FragmentInformation();
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, homeFragment, "FragmentInformation");
        fragmentTransactionHome.commit();
        */

        Intent intent = new Intent(context, SplashActivity.class);
        if (intent != null) {
            DatabaseHelper helper = DatabaseManager.getInstance().getHelper();
            helper.clearDataAfterLogout();
            startActivity(intent);
            finishAffinity();
        }
    }
    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            // your code here
            Log.d("Runnable","loop");
            if (!boolLogOut){
                logout();
            }
            /*if (!isFinishing()){
                logout();
            }*/
//            stop();
//            logout();
        }
    };



    public void start() {
        myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
    }

    public void stop() {
        myHandler.removeCallbacks(myRunnable);
    }

    public void restart() {
        myHandler.removeCallbacks(myRunnable);
        myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
//        Toast.makeText(getApplicationContext(),"Restarting session",Toast.LENGTH_SHORT).show();

//        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
    }
    @Override
    public void onUserInteraction() {
        startUserSession();
        super.onUserInteraction();
    }

    public void startTimer() {
        startUserSession();
        /*SharedPreferences prefs = getSharedPreferences(LoginActivity.Preference_Session, MODE_PRIVATE);
        String restoredText = prefs.getString("time", "15");
        if (restoredText != null) {
            String txtTime = prefs.getString("time", "15");
            if (!txtTime.equals("")){
                int intTime = Integer.parseInt(txtTime);
                intSession = intTime *60 * 1000;
            }
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },intSession);*/
    }
}

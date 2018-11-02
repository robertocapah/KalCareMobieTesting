package com.kalbenutritionals.kalcaremobieTesting;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.kalbe.mobiledevknlibs.CopyDB.CopyDB;
import com.kalbenutritionals.kalcaremobieTesting.BL.clsActivity;
import com.kalbenutritionals.kalcaremobieTesting.BL.clsMainBL;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsPhotoProfile;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobieTesting.Common.mMenuData;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseHelper;
import com.kalbenutritionals.kalcaremobieTesting.Data.DatabaseManager;
import com.kalbenutritionals.kalcaremobieTesting.Data.clsHardCode;
import com.kalbenutritionals.kalcaremobieTesting.Fragment.FragmentAddNewCustomer;
import com.kalbenutritionals.kalcaremobieTesting.Fragment.FragmentInformation;
import com.kalbenutritionals.kalcaremobieTesting.Fragment.FragmentProfile;
import com.kalbenutritionals.kalcaremobieTesting.Fragment.FragmentSalesOrder;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsPhotoProfilRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsUserLoginRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.mConfigRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.mMenuRepo;
import com.kalbenutritionals.kalcaremobieTesting.services.MyNotification;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Roberto on 11/22/2017.
 */

public class MainMenu extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult> {
    private Toolbar toolbar;
    boolean booltime = false;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    DatabaseHelper helper = DatabaseManager.getInstance().getHelper();
    clsUserLogin dataLogin = null;
    List<mMenuData> dataMenu = null;
    AlertDialog dialog1;
    List<clsPhotoProfile> dataImageProfile = null;
    clsUserLoginRepo loginRepo;
    mMenuRepo menuRepo;
    clsPhotoProfilRepo repoUserImageProfile;
    PackageInfo pInfo = null;
    int selectedId;
    Boolean isSubMenu = false;
    String[] listMenu;
    String[] linkMenu;
    private static final int CAMERA_REQUEST_PROFILE = 120;
    private static final String IMAGE_DIRECTORY_NAME = "Image Personal";
    final int SELECT_FILE_PROFILE = 6;
    private static Bitmap photoProfile, mybitmapImageProfile;
    private static byte[] phtProfile;
    final int PIC_CROP_PROFILE = 5;
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();

    private TextView tvUsername, tvEmail, tvDomain;
    CircleImageView ivProfile;
    private Uri uriImage, selectedImage;

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest locationRequest;
    int REQUEST_CHECK_SETTINGS = 100;
//    private BroadcastReceiver mReceiver = null;
    public static MainMenu instance;
//    public Timer timer;

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        int count = getFragmentManager().getBackStackEntryCount();

        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentByTag("Fragment_AddOrder");

        if (currentFragment != null) {
            FragmentSalesOrder SOFragment = new FragmentSalesOrder();
            FragmentTransaction fragmentTransactionSO = getSupportFragmentManager().beginTransaction();
            fragmentTransactionSO.replace(R.id.frame, SOFragment, "FragmentSalesOrder");
            fragmentTransactionSO.commit();
            selectedId = 99;
        } else {
            builder.setTitle("Exit");
            builder.setMessage("Are you sure to exit?");

            builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                    finishAffinity();
                    boolean service = new clsMainBL().isMyServiceRunning(MainMenu.this, MyNotification.class);
                    if (service) {
                        Intent i = new Intent(getApplicationContext(), MyNotification.class);
                        i.putExtra("serviceName", "updateNotif");
                        stopService(i);
                    }
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            android.app.AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        selectedId = 0;
        instance = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }
        // initialize receiver
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        mReceiver = new ScreenReceiver();
//        registerReceiver(mReceiver, filter);
        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        boolean service = new clsMainBL().isMyServiceRunning(this, MyNotification.class);
        if (!service) {
            Intent i = new Intent(getApplicationContext(), MyNotification.class);
            i.putExtra("serviceName", "updateNotif");
            startService(i);
        }
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
//        setSupportActionBar(toolbar);

//        addProductAndOrder();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);
        ivProfile = (CircleImageView) vwHeader.findViewById(R.id.profile_image);
        tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        tvDomain = (TextView) vwHeader.findViewById(R.id.domain);

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(AppIndex.API).build();
        mGoogleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        phtProfile = null;
        FragmentInformation homeFragment = new FragmentInformation();
        Bundle bundle = new Bundle();
//        bundle.putString("keyMainMenu", extra);
//        homeFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, homeFragment, "FragmentInformation");
        fragmentTransactionHome.commit();
        if (photoProfile != null) {
            ivProfile.setImageBitmap(photoProfile);
            photoProfile.compress(Bitmap.CompressFormat.PNG, 100, output);
            phtProfile = output.toByteArray();
        }

        try {
            repoUserImageProfile = new clsPhotoProfilRepo(getApplicationContext());
            dataImageProfile = (List<clsPhotoProfile>) repoUserImageProfile.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dataImageProfile.size() > 0) {
            viewImageProfile();
        }

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageProfile();
            }
        });

        try {
            loginRepo = new clsUserLoginRepo(getApplicationContext());
            dataLogin = loginRepo.getDataLogin(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvUsername.setText(new clsActivity().greetings() + dataLogin.getTxtTeleName().toString());
        tvEmail.setText(dataLogin.getTxtNamaInstitusi());
        String txtDomain = new mConfigRepo(getApplicationContext()).strDomain;
        tvDomain.setText(txtDomain);
        if (!boolTesting){
            tvDomain.setVisibility(View.GONE);
        }

        /*String linkAPI = new mConfigRepo(getApplicationContext()).API_menu;
        try {
            URL u = new URL(linkAPI);
            linkAPI = u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/
     /*   Menu header = navigationView.getMenu();
        SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        try {
            subMenuVersion.add(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT").setIcon(R.mipmap.ic_android).setEnabled(false);
            subMenuVersion.add(linkAPI).setIcon(R.mipmap.ic_link).setEnabled(false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/

        // get menu from db SQLite
        int menuActive = 0;
        menuActive = R.id.groupMenuDinamis;
        try {
            menuRepo = new mMenuRepo(getApplicationContext());
            dataMenu = (List<mMenuData>) menuRepo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*linkMenu = new String[dataMenu.size()];
        listMenu = new String[dataMenu.size()];

        for (int i = 0; i < dataMenu.size(); i++) {
            int resId = getResources().getIdentifier(String.valueOf(dataMenu.get(i).txtDescription.toLowerCase()), "drawable", MainMenu.this.getPackageName());
            Drawable icon = MainMenu.this.getResources().getDrawable(resId);

            header.add(menuActive, i, 1, dataMenu.get(i).getTxtMenuName()).setIcon(icon).setCheckable(true);

            linkMenu[i] = dataMenu.get(i).getTxtLink();
            listMenu[i] = dataMenu.get(i).getTxtMenuName();
        }*/
        int menit15 = 15 * 60 * 1000;
        int menit1 = 60 * 1000;
        int detik30 = 30 * 1000;
        int detik10 = 10 * 1000;
//        int intSession = 15 *60 * 1000;

        startTimer();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.logout:
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainMenu.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure ?");

                        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog dialog2 = new ProgressDialog(MainMenu.this, ProgressDialog.STYLE_SPINNER);
                                dialog2.setIndeterminate(true);
                                dialog2.setMessage("Logging out...");
                                dialog2.setCancelable(false);
                                dialog2.show();

                                new Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                // On complete call either onLoginSuccess or onLoginFailed
                                                logout();
                                                // onLoginFailed();
                                                dialog2.dismiss();
                                            }
                                        }, 3000);
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        android.app.AlertDialog alert = builder.create();
                        alert.show();
                        return true;

                    case R.id.home:
                        toolbar.setTitle("Home");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentInformation homeFragment = new FragmentInformation();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, homeFragment, "FragmentInformation");
                        fragmentTransactionHome.commit();
//                        timer.start();
                        startTimer();
                        selectedId = 99;

                        return true;
                    case R.id.copydb:
                        String dbName = new DatabaseHelper(getApplicationContext()).getDatabaseName().toString();

                        CopyDB.copyFile(getApplicationContext(), dbName);


                        return true;


                    case R.id.profile:
                        toolbar.setTitle("Profile");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentProfile profileFragment = new FragmentProfile();
                        FragmentTransaction fragmentTransactionProfile = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionProfile.replace(R.id.frame, profileFragment, "FragmentProfile");
                        fragmentTransactionProfile.commit();
                        selectedId = 99;
                        return true;

                    case R.id.SO:
                        toolbar.setTitle("Source Order");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentSalesOrder SOFragment = new FragmentSalesOrder();
                        FragmentTransaction fragmentTransactionSO = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionSO.replace(R.id.frame, SOFragment, "FragmentSalesOrder");
                        fragmentTransactionSO.commit();
                        selectedId = 99;
                        return true;

                    case R.id.addNew:
                        toolbar.setTitle("Add New Customer");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentAddNewCustomer fragmentAddNewCustomer = new FragmentAddNewCustomer();
                        FragmentTransaction fragmentTransactionAddNewCustomer = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionAddNewCustomer.replace(R.id.frame, fragmentAddNewCustomer, "FragmentAddNewCustomer");
                        fragmentTransactionAddNewCustomer.commit();
                        selectedId = 99;
                        return true;

                    default:
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                        try {
                            Class<?> fragmentClass = Class.forName(linkMenu[menuItem.getItemId()]);
                            try {
                                toolbar.setTitle(menuItem.getTitle().toString());
                                toolbar.setSubtitle(null);

                                fragment = (Fragment) fragmentClass.newInstance();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame, fragment);
                                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                                fragmentTransaction.commit();
                                selectedId = menuItem.getItemId();
                                isSubMenu = false;

                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onPause() {
        // when the screen is about to turn off
        /*startUserSession();
        unregisterReceiver(mReceiver);
        if (ScreenReceiver.wasScreenOn) {
            // this is the case when onPause() is called by the system due to a screen state change
            Log.e("MYAPP", "SCREEN TURNED OFF");
            startTimer();
        } else {
            // this is when onPause() is called when the screen state has not changed
        }*/
        super.onPause();
    }

    /*@Override
    public void onUserInteraction() {
        startUserSession(getApplicationContext());
        super.onUserInteraction();
    }
    void cancelTimer(){
        if (timer != null){
            timer.cancel();
        }
    }
    public void startUserSession(Context context){
        cancelTimer();
        SharedPreferences prefs = context.getSharedPreferences(LoginActivity.Preference_Session, MODE_PRIVATE);
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
                logout();
            }
        },intSession);
    }

    public void startTimer(){
        *//*SharedPreferences prefs = getSharedPreferences(LoginActivity.Preference_Session, MODE_PRIVATE);
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
        },intSession);*//*
    }*/

    // put image from camera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_PROFILE) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    String uri = uriImage.getPath().toString();

                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    performCropProfile();

//                    previewCaptureImage2(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == 0) {
                Toast.makeText(getApplicationContext(), "User cancel take image", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    photoProfile = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PIC_CROP_PROFILE) {
            if (resultCode == -1) {
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");

                previewCaptureImageProfile(thePic);
            } else if (resultCode == 0) {
                Toast.makeText(getApplicationContext(), "User cancel take image", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == SELECT_FILE_PROFILE) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    selectedImage = data.getData();
                    String uri = selectedImage.getPath().toString();
                    bitmap = BitmapFactory.decodeFile(uri, bitmapOptions);

                    performCropGalleryProfile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                try {
                    fragment.onActivityResult(requestCode, resultCode, data);
                } catch (Exception ex) {

                }
            }
        }
    }
    /*public void logout() {
//        timer.cancel();
        if (isMyServiceRunning(MyNotification.class)) {
            stopService(new Intent(getApplicationContext(), MyNotification.class));
        }
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        DatabaseHelper helper = DatabaseManager.getInstance().getHelper();
        helper.clearDataAfterLogout();
        startActivity(intent);
        finish();

    }*/

    private void selectImageProfile() {
        final CharSequence[] items = {"Ambil Foto", "Pilih dari Galeri",
                "Batal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getApplicationContext());
                if (items[item].equals("Ambil Foto")) {
                    if (result)
                        captureImageProfile();
                } else if (items[item].equals("Pilih dari Galeri")) {
                    if (result)
                        galleryIntentProfile();
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    protected void viewImageProfile() {
        try {
            repoUserImageProfile = new clsPhotoProfilRepo(getApplicationContext());
            dataImageProfile = (List<clsPhotoProfile>) repoUserImageProfile.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/KalbeCare/tempdata/Foto_Profil");
        folder.mkdir();

        for (clsPhotoProfile imgDt : dataImageProfile) {
            final byte[] imgFile = imgDt.getTxtImg();
            if (imgFile != null) {
                mybitmapImageProfile = BitmapFactory.decodeByteArray(imgFile, 0, imgFile.length);
                Bitmap bitmap = Bitmap.createScaledBitmap(mybitmapImageProfile, 150, 150, true);
                ivProfile.setImageBitmap(bitmap);
            }
        }
    }

    // preview image profile
    private void previewCaptureImageProfile(Bitmap photo) {
        try {
            Bitmap bitmap = new clsActivity().resizeImageForBlob(photo);
            ivProfile.setVisibility(View.VISIBLE);
            output = null;
            try {
                output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, output);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Bitmap photo_view = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
            phtProfile = output.toByteArray();
            ivProfile.setImageBitmap(photo_view);

            saveImageProfile();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void saveImageProfile() {
        try {
            repoUserImageProfile = new clsPhotoProfilRepo(getApplicationContext());
            dataImageProfile = (List<clsPhotoProfile>) repoUserImageProfile.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clsPhotoProfile data = new clsPhotoProfile();
        data.setTxtGuiId("1");
        data.setTxtDescription("Profile");
        data.setTxtImg(phtProfile);

        repoUserImageProfile.createOrUpdate(data);
        Toast.makeText(getApplicationContext(), "Image Profile Saved", Toast.LENGTH_SHORT).show();
    }

    protected void captureImageProfile() {
        uriImage = getOutputMediaFileUri();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_PROFILE);
    }
 
    private void galleryIntentProfile() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, SELECT_FILE_PROFILE);//one can be replaced with any action code
    }

    private void performCropProfile() {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(uriImage, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP_PROFILE);
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void performCropGalleryProfile() {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(selectedImage, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP_PROFILE);
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private File getOutputMediaFile() {
        // External sdcard location

        File mediaStorageDir = new File(new clsHardCode().txtFolderData + File.separator);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "tmp_act" + ".png");
        return mediaFile;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        builder.build()
                );

        result.setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        /*startUserSession();
        if (!ScreenReceiver.wasScreenOn) {
            // this is when onResume() is called due to a screen state change
            Log.e("MYAPP", "SCREEN TURNED ON");
//            MainMenu.timer.start();
            startTimer();
        } else {
            // this is when onResume() is called when the screen state has not changed
        }*/
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        int a = 20;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cancelTimer();
        if (isMyServiceRunning(MyNotification.class)) {
            stopService(new Intent(getApplicationContext(), MyNotification.class));
        }
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:

                // NO need to show the dialog;

                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                //  Location settings are not satisfied. Show the user a dialog

                try {
                    // Show the dialog by calling startResolutionForResult(), and check the result
                    // in onActivityResult().

                    status.startResolutionForResult(MainMenu.this, REQUEST_CHECK_SETTINGS);

                } catch (IntentSender.SendIntentException e) {

                    //failed to show
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Location settings are unavailable so not possible to show any dialog now
                break;
        }
    }

    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context) {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }
}

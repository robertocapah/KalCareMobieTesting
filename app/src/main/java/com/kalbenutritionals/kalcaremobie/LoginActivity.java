package com.kalbenutritionals.kalcaremobie;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbenutritionals.kalcaremobie.Common.clsCustomerData;
import com.kalbenutritionals.kalcaremobie.Common.clsToken;
import com.kalbenutritionals.kalcaremobie.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobie.Common.mConfigData;
import com.kalbenutritionals.kalcaremobie.Data.Helper;
import com.kalbenutritionals.kalcaremobie.Data.VolleyResponseListener;
import com.kalbenutritionals.kalcaremobie.Data.VolleyUtils;
import com.kalbenutritionals.kalcaremobie.Data.clsHardCode;
import com.kalbenutritionals.kalcaremobie.Repo.clsCustomerDataRepo;
import com.kalbenutritionals.kalcaremobie.Repo.clsTokenRepo;
import com.kalbenutritionals.kalcaremobie.Repo.clsUserLoginRepo;
import com.kalbenutritionals.kalcaremobie.Repo.mConfigRepo;
import com.kalbenutritionals.kalcaremobie.Repo.mMenuRepo;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Rian Andrivani on 11/22/2017.
 */

public class LoginActivity extends Activity {
    private static final int REQUEST_READ_PHONE_STATE = 0;
    String txtUsername, txtPassword, imeiNumber, deviceName, access_token;
    String clientId = "";
    @BindView(R.id.btnConnect)
    Button btnConnect;
    @BindView(R.id.llDisconnected)
    LinearLayout llDisconnected;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.ivBannerLogin)
    ImageView ivBannerLogin;
    @BindView(R.id.editTextUsername)
    EditText editTextUsername;
    @BindView(R.id.viewFlipper2)
    View viewFlipper2;
    @BindView(R.id.editTextPass)
    EditText editTextPass;
    @BindView(R.id.buttonLogin)
    Button buttonLogin;
    @BindView(R.id.buttonExit)
    Button buttonExit;
    @BindView(R.id.buttonRefreshApp)
    Button buttonRefreshApp;
    @BindView(R.id.txtVersionLogin)
    TextView txtVersionLogin;
    private int intSet = 1;
    int intProcesscancel = 0;
    List<clsToken> dataToken;
    clsUserLoginRepo loginRepo;
    clsTokenRepo tokenRepo;
    mMenuRepo menuRepo;
    private PackageInfo pInfo = null;
    boolean boolTestingLogin = false;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure to exit?");

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        ivBannerLogin.setAdjustViewBounds(true);
        ivBannerLogin.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        spnRole = (Spinner) findViewById(R.id.spnRole);
//        final CircularProgressView progressView = (CircularProgressView) findViewById(R.id.progress_view);
        txtVersionLogin = (TextView) findViewById(R.id.txtVersionLogin);
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        txtVersionLogin.setText(pInfo.versionName);
        try {
            loginRepo = new clsUserLoginRepo(getApplicationContext());
            tokenRepo = new clsTokenRepo(getApplicationContext());
            dataToken = (List<clsToken>) tokenRepo.findAll();
            if (dataToken.size() == 0) {
                requestToken();
            } else {
                llContent.setVisibility(View.VISIBLE);
                llDisconnected.setVisibility(View.GONE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Spinner Drop down elements
        final List<String> roleName = new ArrayList<String>();
        roleName.add("Select One");

        editTextUsername.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    intProcesscancel = 0;
                    roleName.add("Example - Role 1");

                    return true;
                }
                return false;
            }
        });

        // Creating adapter for spinnerTelp
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roleName);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Initializing an ArrayAdapter with initial text like select one
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, roleName) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // attaching data adapter to spinner
       /* spnRole.setAdapter(spinnerArrayAdapter);

        spnRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // put code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // put code here
            }
        });*/

        editTextPass.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editTextPass) {
            public boolean onDrawableClick() {
                if (intSet == 1) {
                    editTextPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    intSet = 0;
                } else {
                    editTextPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    intSet = 1;
                }

                return true;
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressView.setVisibility(View.VISIBLE);
//                progressView.startAnimation();

                if (editTextUsername.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (editTextPass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    popupSubmit();
                }
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                builder.setTitle("Exit");
                builder.setMessage("Are you sure to exit?");

                builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void popupSubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are You sure?");

        builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                login();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    // sesuaikan username dan password dengan data di server
    private void login() {
        txtUsername = editTextUsername.getText().toString();
        txtPassword = editTextPass.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        final String now = dateFormat.format(cal.getTime()).toString();
        final String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        String strLinkAPI = new clsHardCode().linkLogin;
        final JSONObject resJson = new JSONObject();

        try {
            tokenRepo = new clsTokenRepo(getApplicationContext());
            dataToken = (List<clsToken>) tokenRepo.findAll();
            resJson.put("txtUserName", txtUsername);
            resJson.put("txtPass", txtPassword);
//            resJson.put("txtRefreshToken", token);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final String mRequestBody = resJson.toString();
        access_token = dataToken.get(0).getTxtUserToken();
        if (boolTestingLogin) {
            //testing
            clsUserLogin data = new clsUserLogin();
            String gui = new Helper().GenerateGuid();
            data.setTxtGuiId(gui);
            data.setIdUser("hello");
            data.setGrpUser("hello");
            data.setTxtSumberDataID("");
            data.setNmUser("");
            data.setTxtTeleName("");
            data.setTxtNamaInstitusi("");
            data.setDtLogin(new Date());

            loginRepo.createOrUpdate(data);

            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
            finish();
            startActivity(intent);

            //testing closed
        } else {
            makeJsonObjectRequestkf(LoginActivity.this, strLinkAPI, mRequestBody, access_token, "Mohon Tunggu Beberapa Saat...", new VolleyResponseListener() {
                @Override
                public void onError(String response) {
                    ToastCustom.showToasty(getApplicationContext(), response, 2);
                }

                @Override
                public void onResponse(String response, Boolean status, String strErrorMsg) {
                    if (response != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("intResult");
                            String warn = jsonObject.getString("txtMessage");


                            if (result == 0) {
                                ToastCustom.showToasty(getApplicationContext(), " Invalid Username or Password", 2);
                            } else {
                                if (!jsonObject.getString("ListData").equals("null")) {
                                    JSONArray jsn = jsonObject.getJSONArray("ListData");

                                    JSONObject jsnObject = jsn.getJSONObject(0);
                                    JSONObject dtInfo = jsnObject.getJSONObject("dtInfo");

                                    JSONObject dtclsCustomerData = jsnObject.getJSONObject("dtclsCustomerData");
                                    String txtKontakIDDefault = dtclsCustomerData.getString("txtKontakID");
                                    String txtNama = dtclsCustomerData.getString("txtNama");
                                    String txtAlamat = dtclsCustomerData.getString("txtAlamat");
                                    String txtNamaKabKota = dtclsCustomerData.getString("txtNamaKabKota");
                                    String txtNamaPropinsi = dtclsCustomerData.getString("txtNamaPropinsi");
                                    String JenisAlamat = dtclsCustomerData.getString("JenisAlamat");
                                    String txtListMedia = dtclsCustomerData.getString("txtListMedia");
                                    String txtNamaKecamatan = dtclsCustomerData.getString("txtNamaKecamatan");
                                    String txtNamaKelurahan = dtclsCustomerData.getString("txtNamaKelurahan");
                                    String txtNHDSiteID = dtclsCustomerData.getString("txtNHDSiteID");
                                    String txtPropinsiID = dtclsCustomerData.getString("txtPropinsiID");
                                    String txtKabKotaID = dtclsCustomerData.getString("txtKabKotaID");
                                    String txtKecamatan = dtclsCustomerData.getString("txtKecamatan");
                                    String txtKodePos = dtclsCustomerData.getString("txtKodePos");

                                    clsCustomerData dataDefault = new clsCustomerData();
                                    dataDefault.setId(1);
                                    dataDefault.setTxtKontakID(txtKontakIDDefault);
                                    dataDefault.setTxtNama(txtNama);
                                    dataDefault.setJenisAlamat(JenisAlamat);
                                    dataDefault.setTxtAlamat(txtAlamat);
                                    dataDefault.setTxtNamaKabKota(txtNamaKabKota);
                                    dataDefault.setTxtNamaPropinsi(txtNamaPropinsi);
                                    dataDefault.setTxtListMedia(txtListMedia);
                                    dataDefault.setTxtNamaKecamatan(txtNamaKecamatan);
                                    dataDefault.setTxtNamaKelurahan(txtNamaKelurahan);
                                    dataDefault.setTxtNHDSiteID(txtNHDSiteID);
                                    dataDefault.setTxtPropinsiID(txtPropinsiID);
                                    dataDefault.setTxtKabKotaID(txtKabKotaID);
                                    dataDefault.setTxtKecamatan(txtKecamatan);
                                    dataDefault.setTxtKodePos(txtKodePos);
                                    new clsCustomerDataRepo(getApplicationContext()).create(dataDefault);

                                    String dtDate = jsnObject.getString("dtDate");
                                    int intScheduleDevilery = jsnObject.getInt("intScheduleDevilery");

                                    String gui = new Helper().GenerateGuid();
                                    String id_user = dtInfo.getString("id_user");
                                    String grp_user = dtInfo.getString("grp_user");
                                    String txtSumberDataID = dtInfo.getString("txtSumberDataID");
                                    String nm_user = dtInfo.getString("nm_user");
                                    String teleName = dtInfo.getString("TeleName");
                                    String txtCodeId = dtInfo.getString("txtCodeId");
                                    String txtKontakID = dtInfo.getString("txtKontakID");
                                    String txtNamaInstitusi = dtInfo.getString("txtNamaInstitusi");
                                    String txtTeleID = dtInfo.getString("TeleID");
                                    String txtTeleID_CRM = dtInfo.getString("TeleID_CRM");
                                    String txtPhoneNo = dtInfo.getString("txtPhoneNo");
                                    String txtSubinventory = dtInfo.getString("txtSubinventory");

                                    Date datelogin = new Helper().ConvertToDate(dtDate);

                                    clsUserLogin data = new clsUserLogin();
                                    data.setTxtGuiId(gui);
                                    data.setTxtTeleID(txtTeleID);
                                    data.setTxtTeleID_CRM(txtTeleID_CRM);
                                    data.setTxtPhoneNo(txtPhoneNo);
                                    data.setTxtSubinventory(txtSubinventory);
                                    data.setTxtGuiId("1");
                                    data.setIdUser(id_user);
                                    data.setTxtKontakID(txtKontakID);
                                    data.setGrpUser(grp_user);
                                    data.setTxtSumberDataID(txtSumberDataID);
                                    data.setNmUser(nm_user);
                                    data.setTxtCodeId(txtCodeId);
                                    data.setTxtTeleName(teleName);
                                    data.setTxtNamaInstitusi(txtNamaInstitusi);
                                    data.setIntSchedel(intScheduleDevilery);
                                    data.setDtLogin(datelogin);

                                    loginRepo.createOrUpdate(data);
                                    Log.d("Data info", "Login Success");
//                            listMenu();

                                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                                    finish();
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            /*volleyLogin(strLinkAPI, mRequestBody, "Please Wait....", new VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String response, Boolean status, String strErrorMsg) {
                    if (response != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsn = jsonObject.getJSONObject("ListData");
                            String warn = jsn.getString("txtMessage");
                            String result = jsn.getString("intResult");

                            if (result.equals("1")){
                                clsUserLogin data = new clsUserLogin();
                                data.setTxtGuiId("1");
                                data.setTxtUsername(txtUsername);
                                data.setTxtPassword(txtPassword);
                                data.setDtLogin(now);
                                data.setTxtImei(imeiNumber);
                                data.setTxtDeviceName(deviceName);

                                loginRepo.createOrUpdate(data);
                                Log.d("Data info", "Login Success");
//                            listMenu();

                                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                                finish();
                                startActivity(intent);

                            } else {
                                ToastCustom.showToasty(getApplicationContext(),"Username or Password invalid",2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });*/
        }


    }

    private void requestToken() {
        String username = "";
        String strLinkAPI = new clsHardCode().linkToken;
        String clientIdh = "";
        mConfigRepo configRepo = new mConfigRepo(getApplicationContext());
        try {
            mConfigData configDataClient = (mConfigData) configRepo.findById(4);
            mConfigData configDataUser = (mConfigData) configRepo.findById(5);
            username = configDataUser.getTxtDefaultValue().toString();
            clientIdh = configDataClient.getTxtDefaultValue().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new VolleyUtils().makeJsonObjectRequestToken(this, strLinkAPI, username, "", clientIdh, "Requesting Token, Please Wait", new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                llContent.setVisibility(View.GONE);
                llDisconnected.setVisibility(View.VISIBLE);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    try {
                        String accessToken = "";
                        String refreshToken = "";
                        JSONObject jsonObject = new JSONObject(response);
                        accessToken = jsonObject.getString("access_token");
                        refreshToken = jsonObject.getString("refresh_token");
                        String dtIssued = jsonObject.getString(".issued");

                        clsToken data = new clsToken();
                        data.setIntId("1");
                        data.setDtIssuedToken(dtIssued);
                        data.setTxtUserToken(accessToken);
                        data.setTxtRefreshToken(refreshToken);

                        tokenRepo.createOrUpdate(data);
                        Log.d("Data info", "get access_token & refresh_token, Success");
                        if (!editTextUsername.getText().toString().equals("") && !editTextPass.getText().toString().equals("")) {
                            login();
                        }
                        llContent.setVisibility(View.VISIBLE);
                        llDisconnected.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Ready For Login", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void makeJsonObjectRequestkf(final Activity activity, String strLinkAPI, final String mRequestBody, final String tokenVolley, String progressBarType, final VolleyResponseListener listener) {
        ProgressDialog Dialog = new ProgressDialog(activity);
        Dialog = ProgressDialog.show(activity, "",
                progressBarType, false);
        final ProgressDialog finalDialog = Dialog;
        final ProgressDialog finalDialog1 = Dialog;

        StringRequest req = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
//                    new clsActivity().showCustomToast(activity.getApplicationContext(), "401", false);
                    finalDialog1.dismiss();
                    if (error.getMessage() != null) {
                        listener.onError(error.getMessage());
                    }
                    requestToken();
                } else {
                    popup();
                    finalDialog1.dismiss();
                }
            }

            public void popup() {
                final SweetAlertDialog dialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("Oops...");
                dialog.setContentText("Mohon check kembali koneksi internet anda");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        activity.startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK)); // turn on internet with wifi
//                        activity.startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)); // turn on internet with mobile data
                        dialog.dismiss();
                        sweetAlertDialog.dismiss();

                        final SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE);
                        pDialog.setTitleText("Refresh Data ?");
                        pDialog.setContentText("");
                        pDialog.setConfirmText("Refresh");
                        pDialog.setCancelable(false);
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                            }
                        });
                        pDialog.show();
                    }
                });
                dialog.show();
            }
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                finalDialog1.dismiss();
//                listener.onError(error.getMessage());
//            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("txtParam", mRequestBody);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                String creds = String.format("%s:%s","test","test");
//                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
//                params.put("Authorization", auth);
//                return params;
                String credentials = "test" + ":" + "test";
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + tokenVolley);
                return headers;
            }
        };
        req.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(req);
    }


    private void volleyLogin(String strLinkAPI, final String mRequestBody, String progressBarType, final VolleyResponseListener listener) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final String[] body = new String[1];
        final String[] message = new String[1];
        final ProgressDialog Dialog = new ProgressDialog(LoginActivity.this);
        Dialog.setMessage(progressBarType);
        Dialog.setCancelable(false);
        Dialog.show();

        final ProgressDialog finalDialog = Dialog;
        final ProgressDialog finalDialog1 = Dialog;

        mConfigRepo configRepo = new mConfigRepo(getApplicationContext());
        try {
            mConfigData configDataClient = (mConfigData) configRepo.findById(4);
            clientId = configDataClient.getTxtDefaultValue().toString();
            dataToken = (List<clsToken>) tokenRepo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String strLinkAPI = new clsHardCode().linkToken;
                final String refresh_token = dataToken.get(0).txtRefreshToken;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                    try {
                        // body for value error response
                        body[0] = new String(error.networkResponse.data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(body[0]);
                        message[0] = jsonObject.getString("Message");
                        //Toast.makeText(context, "Error 401, " + message[0], Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    requestToken();
                    finalDialog1.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Error 500, Server Error", Toast.LENGTH_SHORT).show();
                    finalDialog1.dismiss();
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                try {
                    tokenRepo = new clsTokenRepo(getApplicationContext());
                    dataToken = (List<clsToken>) tokenRepo.findAll();
                    access_token = dataToken.get(0).getTxtUserToken();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + access_token);

                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("txtParam", mRequestBody);
                return params;
            }
        };
        request.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    @OnClick(R.id.btnConnect)
    public void onbtnConnectClicked() {
        requestToken();
    }
}

package com.kalbenutritionals.kalcaremobieTesting.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsToken;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobieTesting.Data.VolleyResponseListener;
import com.kalbenutritionals.kalcaremobieTesting.Data.VolleyUtils;
import com.kalbenutritionals.kalcaremobieTesting.Data.clsHardCode;
import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsTokenRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Robert on 25/05/2018.
 */

public class FragmentAddNewCustomer extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    Context context;
    clsUserLogin dataLogin = null;
    Unbinder unbinder;
    @BindView(R.id.input_firstname)
    EditText inputFirstname;
    @BindView(R.id.input_lastName)
    EditText inputLastName;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_noHp)
    EditText inputNoHp;
    @BindView(R.id.input_nama_kartu)
    EditText inputNamaKartu;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.spnPropinsiNew)
    Spinner spnPropinsiNew;
    @BindView(R.id.spnKabKotNew)
    Spinner spnKabKotNew;
    @BindView(R.id.spnKecamatanNew)
    Spinner spnKecamatanNew;
    @BindView(R.id.spnKeLurahanNew)
    Spinner spnKeLurahanNew;
    @BindView(R.id.linearLayoutTop)
    LinearLayout linearLayoutTop;
    @BindView(R.id.btn_signup)
    AppCompatButton btnSignup;
    final static String STRSPINNEROPT = "--Choose one--"; // buat default spinner
    List<String> categoriesProv = new ArrayList<String>();
    List<String> categoriesPostCode = new ArrayList<String>();
    List<String> categoriesKabKot = new ArrayList<String>();
    List<String> categoriesKecamatan = new ArrayList<String>();
    List<String> categoriesKelurahan = new ArrayList<String>();
    @BindView(R.id.spnKodePosNew)
    Spinner spnKodePosNew;
    @BindView(R.id.textInputFirstName)
    TextInputLayout textInputFirstName;
    @BindView(R.id.textInputLastName)
    TextInputLayout textInputLastName;
    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;
    @BindView(R.id.textInputNoHp)
    TextInputLayout textInputNoHp;
    @BindView(R.id.textInputNamaKartu)
    TextInputLayout textInputNamaKartu;
    @BindView(R.id.textInputAlamat)
    TextInputLayout textInputAlamat;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    int lengthChar;

    private HashMap<String, String> HMProvinceID = new HashMap<>();
    private HashMap<String, String> HMKabKotID = new HashMap<>();
    private HashMap<String, String> HMKecID = new HashMap<>();
    private HashMap<String, String> HMKel = new HashMap<>();
    private HashMap<String, String> HMKodePos = new HashMap<>();
    private HashMap<String, String> HMPosPerKel = new HashMap<>();
    String access_token;
    String txtChar = "20";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        addDefaultSpinnerAlamat();
        context = getActivity().getApplicationContext();
        try {
            List<clsToken> dataToken = new clsTokenRepo(context).findAll();
            access_token = dataToken.get(0).txtUserToken.toString();
        } catch (Exception e) {
            ToastCustom.showToasty(context, "Token Empty", 2);
        }
        JSONObject resJson = new JSONObject();
        try {
            resJson.put("txtPropinsiID", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getConfiguration();
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        String strLinkProvince = new clsHardCode().linkGetListPropinsi;
//getDataProvinsi();
        final String mRequestBody = resJson.toString();
        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                ToastCustom.showToasty(context, response, 2);
                btnSignup.setClickable(false);
                btnSignup.setEnabled(false);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    btnSignup.setClickable(true);
                    JSONObject jsonObject = null;
                    if (swipeContainer.isRefreshing()){
                        swipeContainer.setRefreshing(false);
                    }
                    try {
                        jsonObject = new JSONObject(response);
                        int result = jsonObject.getInt("intResult");
                        String warn = jsonObject.getString("txtMessage");
                        if (result == 1) {

                            if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");

                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String ltxKeterangan = object.getString("ltxKeterangan");
                                    String txtNamaPropinsi = object.getString("txtNamaPropinsi");
                                    String txtPropinsiID = object.getString("txtPropinsiID");
                                    String txtNegaraID = object.getString("txtNegaraID");
                                    categoriesProv.add(txtNamaPropinsi);
                                    HMProvinceID.put(txtNamaPropinsi, txtPropinsiID);
                                }
                                SpinnerCustom.setAdapterSpinner(spnPropinsiNew, context, R.layout.custom_spinner, categoriesProv);
                                SpinnerCustom.selectedItemByText(context, spnPropinsiNew, categoriesProv, STRSPINNEROPT);
                            }

                        }
                    } catch (JSONException e) {

                    }

                }
            }
        });
        spnPropinsiNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String provinceName = categoriesProv.get(position).toString();
                String ProvinceID = HMProvinceID.get(provinceName);
                String strLinkSpnProvince = new clsHardCode().linkGetListKabupatenKota;

                if (!ProvinceID.equals("0")) {
                    final JSONObject resJson = new JSONObject();
                    try {
                        resJson.put("txtPropinsiID", ProvinceID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final String mRequestBody = resJson.toString();
                    new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkSpnProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                        @Override
                        public void onError(String response) {
                            ToastCustom.showToasty(context, response, 2);
                        }

                        @Override
                        public void onResponse(String response, Boolean status, String strErrorMsg) {
                            if (response != null) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);
                                    int result = jsonObject.getInt("intResult");
                                    String warn = jsonObject.getString("txtMessage");
                                    if (result == 1) {
                                        if (!jsonObject.getString("ListData").equals("null")) {
                                            JSONArray jsn = jsonObject.getJSONArray("ListData");
                                            categoriesKabKot = new ArrayList<String>();
                                            categoriesKabKot.add(STRSPINNEROPT);
                                            HMKabKotID.put(STRSPINNEROPT, "0");
                                            for (int n = 0; n < jsn.length(); n++) {
                                                JSONObject object = jsn.getJSONObject(n);
                                                String ltxKeterangan = object.getString("ltxKeterangan");
                                                String txtNamaKabKot = object.getString("txtNamaKabKota");
                                                String txtKabKotID = object.getString("txtKabKotaID");
                                                String txtPropinsiID = object.getString("txtPropinsiID");
                                                categoriesKabKot.add(txtNamaKabKot);
                                                HMKabKotID.put(txtNamaKabKot, txtKabKotID);
                                            }
                                            SpinnerCustom.setAdapterSpinner(spnKabKotNew, context, R.layout.custom_spinner, categoriesKabKot);
                                        }
                                    } else {
                                        ToastCustom.showToasty(context, warn, 2);

                                    }
                                } catch (JSONException ex) {
                                    String x = ex.getMessage();
                                }
                            }
                        }
                    });
                } else {
                    SpinnerCustom.setAdapterSpinner(spnKabKotNew, context, R.layout.custom_spinner, categoriesKabKot);
                    SpinnerCustom.selectedItemByText(context, spnKabKotNew, categoriesKabKot, STRSPINNEROPT);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnKabKotNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (categoriesKabKot.size() > 0) {
                    String kabKotName = categoriesKabKot.get(position).toString();
                    String kabKotID = HMKabKotID.get(kabKotName);
                    if (!kabKotID.equals("0")) {
                        final JSONObject resJson = new JSONObject();
                        try {
                            resJson.put("txtKabupateKotaID", kabKotID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String strLinkSpnKabKot = new clsHardCode().linkGetListKecamatan;
                        final String mRequestBody = resJson.toString();
                        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkSpnKabKot, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                            @Override
                            public void onError(String response) {
                                ToastCustom.showToasty(context, response, 2);
                            }

                            @Override
                            public void onResponse(String response, Boolean status, String strErrorMsg) {
                                if (response != null) {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int result = jsonObject.getInt("intResult");
                                        String warn = jsonObject.getString("txtMessage");
                                        if (result == 1) {
                                            if (!jsonObject.getString("ListData").equals("null")) {
                                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                categoriesKecamatan = new ArrayList<String>();
                                                categoriesKecamatan.add(STRSPINNEROPT);
                                                HMKecID.put(STRSPINNEROPT, "0");
                                                for (int n = 0; n < jsn.length(); n++) {
                                                    JSONObject object = jsn.getJSONObject(n);
                                                    String txtKecamatanID = object.getString("txtKecamatanID");
                                                    String txtNamaKecamatan = object.getString("txtNamaKecamatan");
                                                    String ltxKeterangan = object.getString("ltxKeterangan");
                                                    String txtKabKotaID = object.getString("txtKabKotaID");
                                                    categoriesKecamatan.add(txtNamaKecamatan);
                                                    HMKecID.put(txtNamaKecamatan, txtKecamatanID);
                                                }
                                                SpinnerCustom.setAdapterSpinner(spnKecamatanNew, context, R.layout.custom_spinner, categoriesKecamatan);
                                            }
                                        } else {
                                            ToastCustom.showToasty(context, warn, 2);

                                        }
                                    } catch (JSONException ex) {
                                        String x = ex.getMessage();
                                    }
                                }
                            }
                        });
                    } else {
                        SpinnerCustom.setAdapterSpinner(spnKecamatanNew, context, R.layout.custom_spinner, categoriesKecamatan);
                        SpinnerCustom.selectedItemByText(context, spnKecamatanNew, categoriesKecamatan, STRSPINNEROPT);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnKecamatanNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (categoriesKecamatan.size() > 0) {
                    String KecName = categoriesKecamatan.get(position).toString();
                    String kecID = HMKecID.get(KecName);
                    if (!kecID.equals("0")) {
                        final JSONObject resJson = new JSONObject();
                        try {
                            resJson.put("txtKecamatanID", kecID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String strLinkSpnKel = new clsHardCode().linkGetListKelurahan;
                        final String mRequestBody = resJson.toString();
                        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkSpnKel, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                            @Override
                            public void onError(String response) {
                                ToastCustom.showToasty(context, response, 2);
                            }

                            @Override
                            public void onResponse(String response, Boolean status, String strErrorMsg) {
                                if (response != null) {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int result = jsonObject.getInt("intResult");
                                        String warn = jsonObject.getString("txtMessage");
                                        if (result == 1) {
                                            if (!jsonObject.getString("ListData").equals("null")) {
                                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                categoriesKelurahan = new ArrayList<>();
                                                categoriesKelurahan.add(STRSPINNEROPT);
                                                categoriesPostCode = new ArrayList<>();
                                                categoriesPostCode.add(STRSPINNEROPT);
                                                for (int n = 0; n < jsn.length(); n++) {
                                                    JSONObject object = jsn.getJSONObject(n);
                                                    String txtNamaKelurahan = object.getString("txtNamaKelurahan");
                                                    String txtKodePosID = object.getString("txtKodePosID");
                                                    categoriesKelurahan.add(txtNamaKelurahan);
                                                    HMKel.put(txtNamaKelurahan, txtNamaKelurahan);
                                                    categoriesPostCode.add(txtKodePosID + "-" + txtNamaKelurahan);
                                                    HMKodePos.put(txtKodePosID + "-" + txtNamaKelurahan, txtKodePosID);
                                                    HMPosPerKel.put(txtNamaKelurahan,txtKodePosID + "-" + txtNamaKelurahan);

                                                }
                                                SpinnerCustom.setAdapterSpinner(spnKeLurahanNew, context, R.layout.custom_spinner, categoriesKelurahan);
                                                SpinnerCustom.setAdapterSpinner(spnKodePosNew, context, R.layout.custom_spinner, categoriesPostCode);

                                            }
                                        } else {
                                            ToastCustom.showToasty(context, warn, 2);

                                        }
                                    } catch (JSONException ex) {
                                        String x = ex.getMessage();
                                    }
                                }
                            }
                        });
                    } else {
                        SpinnerCustom.setAdapterSpinner(spnKeLurahanNew, context, R.layout.custom_spinner, categoriesKelurahan);
                        SpinnerCustom.setAdapterSpinner(spnKodePosNew, context, R.layout.custom_spinner, categoriesPostCode);
                        SpinnerCustom.selectedItemByText(context, spnKecamatanNew, categoriesKelurahan, STRSPINNEROPT);
                        SpinnerCustom.selectedItemByText(context, spnKodePosNew, categoriesPostCode, STRSPINNEROPT);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnKeLurahanNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kelName = categoriesKelurahan.get(position).toString();
                String kelID = HMKel.get(kelName);
                String pos = HMPosPerKel.get(kelName);
                SpinnerCustom.selectedItemByText(context, spnKodePosNew, categoriesPostCode, pos);
                spnKodePosNew.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        inputFirstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputFirstName);
            }
        });

        inputFirstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lengthChar = Integer.parseInt(txtChar);
                String nama = s.toString();
                String lastName = inputLastName.getText().toString();
                if (nama.length()>lengthChar){
                    inputNamaKartu.setText("");
                    setErrorMessage(context,textInputNamaKartu,inputNamaKartu,"nama tidak boleh lebih dari "+lengthChar+" karakter");
                }else{
                    inputNamaKartu.setText(nama + " " + lastName);
                    removeErrorMessage(textInputNamaKartu);
                }

            }
        });
        inputLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lengthChar = Integer.parseInt(txtChar);
                String namaDepan = inputFirstname.getText().toString();
                String namaBelakang = s.toString();
                if (namaDepan.length()<lengthChar){
                    if (namaDepan.length()+namaBelakang.length()<=lengthChar){
                        inputNamaKartu.setText(namaDepan + " " + namaBelakang);
                    }
                }
            }
        });
        inputNamaKartu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lengthChar = Integer.parseInt(txtChar);
                String namaKartu = s.toString();
                if (namaKartu.length()>lengthChar){
                    setErrorMessage(context,textInputNamaKartu,inputNamaKartu,"Nama kartu tidak boleh lebih dari "+lengthChar+" karakter");
                }else{
                    removeErrorMessage(textInputNamaKartu);
                }
            }
        });
        inputFirstname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputFirstName);
            }
        });
        inputLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputLastName);
            }
        });
        inputLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputLastName);
            }
        });
        inputEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputEmail);
            }
        });
        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputEmail);
            }
        });
        inputNamaKartu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputNamaKartu);
            }
        });
        /*inputNamaKartu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputNamaKartu);
                String namaDepan = inputFirstname.getText().toString();
                String namaBelakang = inputLastName.getText().toString();
                if(hasFocus)
                {
                    inputNamaKartu.setText(namaDepan+" "+namaBelakang);
                }
            }
        });*/
        inputNoHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputNoHp);
            }
        });
        inputNoHp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputNoHp);
            }
        });
        inputAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeErrorMessage(textInputAlamat);
            }
        });
        inputAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                removeErrorMessage(textInputAlamat);
            }
        });
        return view;
    }

    private String getConfiguration(){

        JSONObject resJson = new JSONObject();
        try {
            resJson.put("congfig", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        String strLinkGetConfig = new clsHardCode().linkGetConfig;
//getDataProvinsi();
        final String mRequestBody = resJson.toString();
        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkGetConfig, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                ToastCustom.showToasty(context, response, 2);
                btnSignup.setClickable(false);
                btnSignup.setEnabled(false);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    btnSignup.setClickable(true);
                    JSONObject jsonObject = null;
                    if (swipeContainer.isRefreshing()){
                        swipeContainer.setRefreshing(false);
                    }
                    try {
                        jsonObject = new JSONObject(response);
                        int result = jsonObject.getInt("intResult");
                        String warn = jsonObject.getString("txtMessage");
                        if (result == 1) {

                            if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                if (jsn.length()>0){
                                    JSONObject obj = jsn.getJSONObject(0);
                                    txtChar = obj.getString("txtConfigValue");
                                    lengthChar = Integer.parseInt(txtChar);
                                    InputFilter[] fArray = new InputFilter[1];
                                    fArray[0] = new InputFilter.LengthFilter(lengthChar+1);
                                    inputNamaKartu.setFilters(fArray);
                                }
                            }

                        }
                    } catch (JSONException e) {

                    }

                }
            }
        });
        return "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().onUserInteraction();
    }

    private void addDefaultSpinnerAlamat() {
        categoriesProv = new ArrayList<>();
        categoriesKabKot = new ArrayList<>();
        categoriesKecamatan = new ArrayList<>();
        categoriesPostCode = new ArrayList<>();
        categoriesKelurahan = new ArrayList<>();
        categoriesPostCode = new ArrayList<>();

        categoriesProv.add(STRSPINNEROPT);
        categoriesKabKot.add(STRSPINNEROPT);
        categoriesKecamatan.add(STRSPINNEROPT);
        categoriesKelurahan.add(STRSPINNEROPT);
        categoriesPostCode.add(STRSPINNEROPT);
        HMProvinceID.put(STRSPINNEROPT, "0");
        HMKabKotID.put(STRSPINNEROPT, "0");
        HMKecID.put(STRSPINNEROPT, "0");
        HMKel.put(STRSPINNEROPT, "0");
        HMKodePos.put(STRSPINNEROPT, "0");
    }

    @OnClick(R.id.btn_signup)
    public void onViewClicked() {
        boolean validasiData = true;
        String message = "";
        String namaDepan = inputFirstname.getText().toString();
        String namaBelakang = inputLastName.getText().toString();
        String noHP = inputNoHp.getText().toString();
        String alamat = inputAddress.getText().toString();
        String email = inputEmail.getText().toString();
        String namaKartu = inputNamaKartu.getText().toString();
        String namaPropinsi = spnPropinsiNew.getSelectedItem().toString();
        String kodePropinsi = HMProvinceID.get(namaPropinsi);
        String namaKabupatenKota = spnKabKotNew.getSelectedItem().toString();
        String kodeKabKot = HMKabKotID.get(namaKabupatenKota);
        String namaKecamatan = spnKecamatanNew.getSelectedItem().toString();
        String kodeKec = HMKecID.get(namaKecamatan);
        String namaKeluarahan = spnKeLurahanNew.getSelectedItem().toString();
        String kodeKelurahan = HMKel.get(namaKeluarahan);
        String kodePosKel = spnKodePosNew.getSelectedItem().toString();
        String kodePos = HMKodePos.get(kodePosKel);

        if (namaDepan.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputFirstName, inputFirstname, "Nama depan wajib di isi");
        } else {
            int digit = namaDepan.length();
            if (digit < 2) {
                setErrorMessage(context, textInputFirstName, inputFirstname, "Nama depan minimal 2 karakter");
                validasiData = false;
            } else if (namaDepan.contains("..")) {
                setErrorMessage(context, textInputFirstName, inputFirstname, "Nama depan tidak valid");
                validasiData = false;
            } else if (namaDepan.substring(0, 1).equals(".")) {
                setErrorMessage(context, textInputFirstName, inputFirstname, "Nama depan tidak boleh diawali titik");
            } else {
                removeErrorMessage(textInputFirstName);
            }
        }

        if (namaBelakang.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputLastName, inputLastName, "Nama belakang wajib di isi");
        } else {
            int digit = namaBelakang.length();
            if (digit < 2) {
                setErrorMessage(context, textInputLastName, inputLastName, "Nama belakang minimal 2 karakter");
                validasiData = false;
            } else if (namaBelakang.contains("..")) {
                setErrorMessage(context, textInputLastName, inputLastName, "Nama belakang tidak valid");
                validasiData = false;
            } else if (namaBelakang.substring(0, 1).equals(".")) {
                setErrorMessage(context, textInputLastName, inputLastName, "Nama belakang tidak boleh diawali titik");
            } else {

                removeErrorMessage(textInputLastName);
            }
        }


        if (email.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputEmail, inputEmail, "Email tidak valid");
        } else {
            boolean emailval = isValidEmail(email);
            if (!emailval) {
                setErrorMessage(context, textInputEmail, inputEmail, "Email tidak valid");
                validasiData = false;
            } else {
                removeErrorMessage(textInputEmail);
            }
        }
        if (kodeKabKot.equals("0") || kodePropinsi.equals("0") || kodeKec.equals("0") || kodeKelurahan.equals("0") || kodePos.equals("0")) {
            validasiData = false;
            ToastCustom.showToasty(context, "Isi detail Alamat", 4);
        }

        if (namaKartu.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputNamaKartu, inputNamaKartu, "Nama kartu wajib di isi");
        } else {
            removeErrorMessage(textInputNamaKartu);
        }
        lengthChar = Integer.parseInt(txtChar);
        if(namaKartu.length()>lengthChar){
            validasiData = false;
            setErrorMessage(context, textInputNamaKartu, inputNamaKartu, "Nama kartu tidak boleh lebih dari "+lengthChar+" karakter");
        }
        if (noHP.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputNoHp, inputNoHp, "No HP wajib di isi");
        } else {
            boolean vl = validCellPhone(noHP);
            if (!vl) {
                setErrorMessage(context, textInputNoHp, inputNoHp, "Format No HP salah");
                validasiData = false;
            } else {
                String nope = noHP.substring(0, 3);
                if (noHP.length() < 8) {
                    setErrorMessage(context, textInputNoHp, inputNoHp, "No HP minimal 8 angka");
                    validasiData = false;
                } else if (nope.contains("00")) {
                    setErrorMessage(context, textInputNoHp, inputNoHp, "Format No HP salah");
                    validasiData = false;
                } else if (!nope.substring(0, 1).equals("0")) {
                    setErrorMessage(context, textInputNoHp, inputNoHp, "No Hp harus diawali angka 0");
                    validasiData = false;
                } else {
                    removeErrorMessage(textInputNoHp);
                }

            }

        }
        Pattern regex = Pattern.compile("[$&+:;=\\\\?@#|/'<>^*()%!-]");
        if (alamat.replace(" ", "").equals("")) {
            validasiData = false;
            setErrorMessage(context, textInputAlamat, inputAddress, "Alamat wajib di isi");
        } else if (alamat.replace(" ", "").length() < 10) {
            validasiData = false;
            setErrorMessage(context, textInputAlamat, inputAddress, "Alamat wajib minimal 10 karakter non spasi");
        } else if (regex.matcher(alamat.substring(0, 9).replace(" ", "")).find()) {
            validasiData = false;
            setErrorMessage(context, textInputAlamat, inputAddress, "Alamat tidak valid, check penggunaan spesial karakter");
        } else if (alamat.contains(",,")){
            validasiData = false;
            setErrorMessage(context, textInputAlamat, inputAddress, "Alamat tidak valid, check penggunaan spesial karakter (koma)");
        }else if (alamat.contains("..")){
            validasiData = false;
            setErrorMessage(context, textInputAlamat, inputAddress, "Alamat tidak valid, check penggunaan spesial karakter (titik)");
        }else {
            removeErrorMessage(textInputAlamat);
        }

        if (validasiData) {
            final String strLinkAPI = new clsHardCode().linksaveNewCustomer;
            final JSONObject resJson = new JSONObject();
            try {
                resJson.put("txtNamaDepan", namaDepan.replaceFirst("\\s++$", ""));
                resJson.put("txtNamaBelakang", namaBelakang.replaceFirst("\\s++$", ""));
                resJson.put("txtEmail", email);
                resJson.put("txtNoHp", noHP);
                resJson.put("txtNamaKartuMemberShip", namaKartu);
                resJson.put("txtKodeProvinsi", kodePropinsi);
                resJson.put("txtKodeKabKot", kodeKabKot);
                resJson.put("txtKodeKec", kodeKec);
                resJson.put("txtKodeKelurahan", kodeKelurahan);
                resJson.put("txtKodePos", kodePos);
                resJson.put("txtAlamat", alamat);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final String mRequestBody = resJson.toString();
            new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPI, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                @Override
                public void onError(String response) {
                    ToastCustom.showToasty(context, response, 2);
                }

                @Override
                public void onResponse(String response, Boolean status, String strErrorMsg) {
                    if (response != null) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("intResult");
                            String warn = jsonObject.getString("txtMessage");
                            if (result == 1) {
                                inputFirstname.setText("");
                                inputLastName.setText("");
                                inputAddress.setText("");
                                inputEmail.setText("");
                                inputNamaKartu.setText("");
                                inputNoHp.setText("");
                                SpinnerCustom.selectedItemByText(context, spnPropinsiNew, categoriesProv, STRSPINNEROPT);
                                SpinnerCustom.selectedItemByText(context, spnKecamatanNew, categoriesKecamatan, STRSPINNEROPT);
                                SpinnerCustom.selectedItemByText(context, spnKabKotNew, categoriesKabKot, STRSPINNEROPT);
                                SpinnerCustom.selectedItemByText(context, spnKeLurahanNew, categoriesKelurahan, STRSPINNEROPT);
                                SpinnerCustom.selectedItemByText(context, spnKodePosNew, categoriesPostCode, STRSPINNEROPT);
                                ToastCustom.showToasty(context, "Pendaftaran Berhasil dengan kontak ID " + warn, 1);
                            /*if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String txtDiscount = object.getString("decDiscount");
                                }
                            }*/
                            } else {
                                ToastCustom.showToasty(context, warn, 2);
                            }
                        } catch (JSONException ex) {
                            String x = ex.getMessage();
                        }
                    }
                }
            });
        } else {
            ToastCustom.showToasty(context, "Data tidak valid, silahkan perbaiki", 2);
        }

    }

    public boolean validCellPhone(String number) {
        return Patterns.PHONE.matcher(number).matches();
    }

    public void setErrorMessage(Context context, TextInputLayout textInputLayout, EditText editText, String message) {
        textInputLayout.setError(message);
        textInputLayout.setErrorEnabled(true);
//        editText.setFocusable(true);
//        editText.setBackground(context.getResources().getDrawable(R.drawable.bg_edtext));
        editText.getBackground().clearColorFilter();
    }

    public void removeErrorMessage(TextInputLayout textInputLayout) {
        if (textInputLayout != null) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    public void onRefresh() {
        // Reload current fragment
        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("FragmentAddNewCustomer");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }
}

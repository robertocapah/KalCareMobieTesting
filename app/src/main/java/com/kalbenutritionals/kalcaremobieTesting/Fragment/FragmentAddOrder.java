package com.kalbenutritionals.kalcaremobieTesting.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.kalbe.mobiledevknlibs.DeviceInformation.DeviceInformation;
import com.kalbe.mobiledevknlibs.DeviceInformation.ModelDevice;
import com.kalbe.mobiledevknlibs.Spinner.SpinnerCustom;
import com.kalbe.mobiledevknlibs.Toast.ToastCustom;
import com.kalbe.mobiledevknlibs.library.pulltorefresh.interfaces.IXListViewListener;
import com.kalbe.mobiledevknlibs.library.toasty.Toasty;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsCustomerData;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsDraft;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListMediaJasa;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListPaymentMethod;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsListPaymentMethodAndTipe;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsProductDraft;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsToken;
import com.kalbenutritionals.kalcaremobieTesting.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog.CustomDatePicker;
import com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog.clsDatePicker;
import com.kalbenutritionals.kalcaremobieTesting.Data.Helper;
import com.kalbenutritionals.kalcaremobieTesting.Data.VolleyResponseListener;
import com.kalbenutritionals.kalcaremobieTesting.Data.VolleyUtils;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAdapterCustomerSearch;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAdapterNewRevisi;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAdapterOutet;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAdapterSearchResult;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAppAdapter;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAppAdapter2;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.CardAppAdapterPreview;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.ListViewCustom;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.RVParentAdapter;
import com.kalbenutritionals.kalcaremobieTesting.Data.adapter.RVPreviewAdapter;
import com.kalbenutritionals.kalcaremobieTesting.Data.clsHardCode;
import com.kalbenutritionals.kalcaremobieTesting.DrawableClickListener;
import com.kalbenutritionals.kalcaremobieTesting.LoginActivity;
import com.kalbenutritionals.kalcaremobieTesting.MainMenu;
import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsCustomerDataRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsDraftRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsListMediaJasaRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsListPaymentMethodAndTipeRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsListPaymentMethodRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsProductDraftRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsTokenRepo;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsUserLoginRepo;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMCustomers;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItems;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMItemsPreview;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMLIstSo;
import com.kalbenutritionals.kalcaremobieTesting.ViewModel.VMOutlets;

import net.idik.lib.slimadapter.SlimAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jim.h.common.android.lib.zxing.config.ZXingLibConfig;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import jim.h.common.android.lib.zxing.integrator.IntentResult;

import static android.content.Context.MODE_PRIVATE;
import static com.kalbenutritionals.kalcaremobieTesting.Data.AlertDialog.clsDatePicker.formatSimpleDate;

/**
 * Created by Robert on 19/02/2018.
 */

public class FragmentAddOrder extends Fragment implements IXListViewListener, RVParentAdapter.ItemClickListener {
    View v;
    @BindView(R.id.etNoSo)
    EditText etNoSo;
    @BindView(R.id.etSoStatus)
    EditText etSoStatus;
    @BindView(R.id.input_layout_no_status)
    TextInputLayout inputLayoutNoStatus;
    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.input_layout_date)
    TextInputLayout inputLayoutDate;
    @BindView(R.id.etAgentName)
    EditText etAgentName;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.etOrderLocation)
    EditText etOrderLocation;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etRemarks)
    EditText etRemarks;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.linearLayoutTop)
    LinearLayout linearLayoutTop;
    @BindView(R.id.imageScanner)
    ImageView imageScanner;
    @BindView(R.id.ivReset)
    ImageView ivReset;
    @BindView(R.id.etItemCode)
    EditText etItemCode;
    @BindView(R.id.etItemName)
    EditText etItemName;
    @BindView(R.id.btnSearchItem)
    Button btnSearchItem;
    @BindView(R.id.etQty)
    EditText etQty;
    @BindView(R.id.lvContentItemSearch)
    ListView lvContentItemSearch;
    @BindView(R.id.btnSaveDraft)
    Button btnSaveDraft;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.btnPreview)
    Button btnPreview;
    @BindView(R.id.linearLayoutBottom)
    LinearLayout linearLayoutBottom;
    Unbinder unbinder;
    Unbinder unbinder2;
    @BindView(R.id.tvSoStatus)
    TextView tvSoStatus;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView10_2)
    TextView textView102;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.llimgviewScanner)
    LinearLayout llimgviewScanner;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.tvItemPrice)
    TextView tvItemPrice;
    @BindView(R.id.etSOSource)
    EditText etSOSource;
    @BindView(R.id.etDeliverySchedule)
    EditText etDeliverySchedule;
    @BindView(R.id.input_layout_DeliverySchedule)
    TextInputLayout inputLayoutDeliverySchedule;
    @BindView(R.id.input_layout_telp2)
    TextInputLayout inputLayoutTelp2;
    @BindView(R.id.lvItemAdd)
    ListView lvItemAdd;
    @BindView(R.id.cbWalkInCustomer)
    CheckBox cbWalkInCustomer;
    private RVParentAdapter mAdapterItemHeader;
    private RVPreviewAdapter mAdapterItemPreview;
    List<VMItems> itemsadd = new ArrayList<VMItems>();
    List<VMItems> contentLibs = new ArrayList<VMItems>();
    List<VMItemsPreview> contentLibsPreviewItem = new ArrayList<VMItemsPreview>();
    @BindView(R.id.cbWalkIn)
    CheckBox cbPassBy;
    @BindView(R.id.cbAttach)
    CheckBox cbAttach;
    @BindView(R.id.spnProvinceAddOrder)
    Spinner spnProvinceAddOrder;
    @BindView(R.id.spnKabKotAddOrder)
    Spinner spnKabKotAddOrder;
    @BindView(R.id.spnKecamatanAddOrder)
    Spinner spnKecamatanAddOrder;
    @BindView(R.id.lnCustomerDetail)
    LinearLayout lnCustomerDetail;
    @BindView(R.id.tvContactIDHeader)
    TextView tvContactIDHeader;
    @BindView(R.id.tvMemberNoHeader)
    TextView tvMemberNoHeader;
    @BindView(R.id.tvCustomerNameHeader)
    TextView tvCustomerNameHeader;
    @BindView(R.id.spnPostCodeAddOrder)
    Spinner spnPostCodeAddOrder;
    List<String> categoriesProv = new ArrayList<String>();
    List<String> categoriesPostCode = new ArrayList<String>();
    List<String> categoriesKabKot = new ArrayList<String>();
    List<String> categoriesKecamatan = new ArrayList<String>();
    List<String> categoriesKelurahan = new ArrayList<String>();

    List<String> paymentMethodList = new ArrayList<String>();
    List<String> sourceMediaPaymentList = new ArrayList<String>();
    List<String> paymentMethodAndTipe = new ArrayList<String>();
    List<String> listItem = new ArrayList<String>();

    int intCampagnGlobal = 0;
    double decTaxGlobal = 10;
    String txtBrandGlobal = "";
    String txtGroupProductGlobal = "";
    String txtGroupItemGlobal = "";
    String txtDescriptionGlobal = "";
    String txtBarcodeGlobal = "";
    String txtKNGlobal = "";
    String txtGroupKNGlobal = "";
    String txtProductGlobal = "";
    String txtUOMGlobal = "";
    int intPaketGlobal = 0;
    double decWeightGlobal = 0;
    double poinKN1Global = 0;
    double poinKN2Global = 0;
    double poinKN3Global = 0;
    double poinKN4Global = 0;
    double poinOtherGlobal = 0;
    int intPassBy = 1;
    int intWalkinCustomer = 0;
    int intSession = 15;
    private HashMap<String, String> HMPaymentmethodList = new HashMap<String, String>();
    private HashMap<String, String> HMsourceMediaPaymentList = new HashMap<String, String>();
    private HashMap<String, String> HMpaymentMethodAndTipe = new HashMap<String, String>();
    private HashMap<String, Integer> HMCardNumber = new HashMap<String, Integer>();
    private HashMap<String, Integer> HMTraceNumber = new HashMap<String, Integer>();

    boolean boolPaymentFill = false;

    EditText etCardNumber;
    EditText etBcaTraceNumber, etPayment;
    TextView tvCustomerPrev;
    @BindView(R.id.spnKelurahanAddOrder)
    Spinner spnKelurahanAddOrder;
    @BindView(R.id.tvPhoneNumb)
    TextView tvPhoneNumb;
    @BindView(R.id.rv_parent)
    RecyclerView rvParent;
    //    @BindView(R.id.tvPostCodeHeader)
//    EditText tvPostCodeHeader;
    private ZXingLibConfig zxingLibConfig;
    Context context;
    List<clsToken> dataToken;
    final static String SPNMEMBERID = "Member ID";
    final static String SPNCHOOSEONE = "Choose One";
    final static String SPNPHONE = "Phone Number";
    private HashMap<String, String> HMItemCode = new HashMap<String, String>();
    private HashMap<String, String> HMItemName = new HashMap<String, String>();
    private HashMap<String, String> HMItemPrice = new HashMap<String, String>();
    private HashMap<String, String> HMItemDesc = new HashMap<String, String>();
    private HashMap<String, String> HMtxtProductCategory = new HashMap<String, String>();

    private HashMap<String, String> HMProvinceID = new HashMap<>();
    private HashMap<String, String> HMKabKotID = new HashMap<>();
    private HashMap<String, String> HMKecID = new HashMap<>();
    private HashMap<String, String> HMKel = new HashMap<>();
    private HashMap<String, String> HMKodePos = new HashMap<>();
    private HashMap<String, String> HMGetKodePosFromKeluarahan = new HashMap<>();
    private HashMap<String, String> HMOutletCode = new HashMap<>();
    private HashMap<String, String> HMOrderLocationId = new HashMap<>();

    //final
    double dblTaxBasedAmountFinal = 0;
    double dbldecTaxAmountFinal = 0;
    double dbldecTotalFinal = 0;
    double dbldecTotDiscountFinal = 0;
    double dbldecTotalPriceFinal = 0;
    double dbldecdecRoundedFinal = 0;
    double dbldecPaymentFinal = 0;
    double dbldecNetPriceFinal = 0;
    double dbldecTotalBonusPoin = 0;
    double dbldecTotalPoin = 0;

    boolean boolPaymentFilled = false;
    boolean keyDel = false;

    String txtPaymentMethodID = "";
    String txtMediaJasaID = "";
    String txtMediaJasaPaymentID = "";
    String txtCardNumber = "";
    String txtBCATraceNo = "";
    String txtMediaJasaName = "";
    String txtPaymentMethodDesc = "";
    String txtCustomerFinal = "";
    String txtCustomerNameFinal = "";
    String txtPickUpLocationNameFinal = "";
    String txtmediajasapaymentFinal = "";

    String txtPropinsiIDFinal = "";
    String txtPropinsiNameFinal = "";
    String txtKabupatenKotaIDFinal = "";
    String txtKabupatenKotaNameFinal = "";
    String txtKecamatanIDFinal = "";
    String txtKecamatanNameFinal = "";
    String txtKelurahanIDFinal = "";
    String txtKelurahanNameFinal = "";
    String txtKodePosFinal = "";
    String txtDeliveryAddressFinal = "";
    String txtKontakIDFinal = "";

    String txtNoSoFinal = "";

    String bitSO = "";

    int intBitActiveFinal = 0;

    String access_token;
    String strPaymentMethod = "";
    List<String> contentLibs2 = new ArrayList<>();
    Boolean boolCustomerSet = true;
    clsUserLogin data;
    clsDraft draftData;
    clsDraft draft = null;
    ModelDevice deviceInfo = null;
    clsUserLogin dataLogin = null;
    boolean boolAttachCustomer = false;
    boolean boolRestore = false;
    boolean boolListPaketOrProduct = true;
    private SlimAdapter slimAdapter;
    //    LoadToast lt = null;
    final static String STRSPINNEROPT = "--Choose one--"; // buat default spinner
    final static String STRKALCARE = "KALCARE"; // buat default spinner

    String provinceCustomer = "";
    String kabKotCustomer = "";
    String kecamatanCustomer = "";
    String kelurahanCustomer = "";
    String postCodeCustomer = "";
    ListView lvSearchResult;

    String paymentMethodId = "";
    String sourceMediaPaymentId = "";
    String paymentId = "";

    AlertDialog alertDSearchitem;
    AlertDialog alertDProductSearch;
    AlertDialog alertDPreview;


    String mRequestBodyCheckout = "";
    String strLinkAPICheckout = "";
    //pop up search item
    EditText etQtySearch;
    EditText etSearchOnPopUp;
    EditText etDiscount;
    EditText etPrice;
    EditText etBonusPoint;
    EditText etBasedPoint;
    Timer timer;
    boolean boolDraft = false;
    private static int frm;
    private static int year, month, day;

    List<VMItems> contentSearchResult = new ArrayList<VMItems>();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_order, container, false);
        context = getActivity().getApplicationContext();
        unbinder = ButterKnife.bind(this, v);
        addDefaultSpinnerAlamat();

        try {
            List<clsToken> dataToken = new clsTokenRepo(context).findAll();

            access_token = dataToken.get(0).txtUserToken.toString();
        } catch (Exception e) {
            ToastCustom.showToasty(context, "Token Empty", 2);
        }
        //pengganti session
        Helper.textWatcher(etRemarks, getActivity());
//        new Helper().textWatcher(etAgentName);
//        new Helper().textWatcher(etBcaTraceNumber);
//        new Helper().textWatcher(etCardNumber);
//        new Helper().textWatcher(etSearchOnPopUp);
//        new Helper().textWatcher(etQtySearch);


//        lt = new LoadToast(context);
        deviceInfo = new ModelDevice();
        deviceInfo = DeviceInformation.getDeviceInformation();
        contentLibs = new ArrayList<VMItems>();
        lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, new ArrayList<VMItems>(), Color.WHITE));
        rvParent.setAdapter(new RVParentAdapter(context, new ArrayList<VMItems>(), Color.WHITE));
        dataLogin = new clsUserLogin();
        dataLogin = new clsUserLoginRepo(context).getDataLogin(context);

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd MMM yyyy");
        String delNow = sdf3.format(Calendar.getInstance().getTime());
        etDeliverySchedule.setText(delNow);

        final Bundle arguments = getArguments();
        String noSO = "";
        if (arguments != null) {
            bitSO = arguments.getString("bitSO");
            noSO = arguments.getString("noSO");
        }
        final SimpleDateFormat sdfi = new SimpleDateFormat("yyyy-MM-dd");
        final Date dtLoginData = dataLogin.getDtLogin();
        String currentDateandTime = sdfi.format(dtLoginData);
        final List<VMLIstSo> contentLibs = new ArrayList<>();
        try {
            draft = new clsDraftRepo(context).findByBitActive();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (draft != null) {
            boolDraft = true;
            if (!draft.getTxtSOStatus().equals("")) {
                etSoStatus.setText(draft.getTxtSOStatus());
            }
            if (!draft.getTxtNoSO().equals("")) {
                etNoSo.setText(draft.getTxtNoSO());
            }
            if (!draft.isBoolPassBy()) {
                getDataProvinsi();
                String txtPropinsiID = draft.getTxtProvinceID();
                String txtPropinsiName = draft.getTxtProvince();
                String txtKabupatenKotaID = draft.getTxtKabKotID();
                String txtKabupatenKotaName = draft.getTxtKabKot();
                String txtKecamatanID = draft.getTxtKecamatanID();
                String txtKecamatanName = draft.getTxtKecamatan();
                String txtKelurahanID = draft.getTxtKelurahan();
                String txtKelurahanName = draft.getTxtKelurahan();
                String txtAddress = draft.getTxtAddress();
                String txtKontakId = draft.getTxtContactID();
                String txtMemberId = draft.getTxtMemberID();
                String txtCustomerName = draft.getTxtCustomerName();
                String txtPhoneNum = draft.getTxtPhoneNumber();

                tvMemberNoHeader.setText(txtMemberId);
                tvCustomerNameHeader.setText(txtCustomerName);
                tvContactIDHeader.setText(txtKontakId);
                tvPhoneNumb.setText(txtPhoneNum);
                etAddress.setText(txtAddress);
                cbPassBy.setChecked(false);
                String postCode = draft.getTxtPostCode() + "-" + draft.getTxtKelurahan();

                categoriesPostCode.add(postCode);
                HMKodePos.put(postCode, draft.getTxtPostCode());
                SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, postCode);

                categoriesProv.add(draft.getTxtProvince());
                HMProvinceID.put(txtPropinsiName, txtPropinsiID);
                SpinnerCustom.setAdapterSpinner(spnProvinceAddOrder, context, R.layout.custom_spinner, categoriesProv);
                SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, txtPropinsiName);

                categoriesKabKot.add(txtKabupatenKotaName);
                HMKabKotID.put(txtKabupatenKotaName, txtKabupatenKotaID);
                SpinnerCustom.setAdapterSpinner(spnKabKotAddOrder, context, R.layout.custom_spinner, categoriesKabKot);
                SpinnerCustom.selectedItemByText(context, spnKabKotAddOrder, categoriesKabKot, txtKabupatenKotaName);

                categoriesKecamatan.add(txtKecamatanName);
                HMKecID.put(txtKecamatanName, txtKecamatanID);
                SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);
                SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, txtKecamatanName);

                categoriesKelurahan.add(txtKelurahanName);
                HMKel.put(txtKelurahanName, txtKelurahanID);
                SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
                SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, txtKelurahanName);
                if (draft.isBoolWalkinCustomer()) {
                    cbPassBy.setChecked(false);
                    cbAttach.setChecked(false);
                    cbAttach.setVisibility(View.VISIBLE);
                    cbWalkInCustomer.setChecked(true);
                } else if (draft.isBoolPassBy()) {
                    cbPassBy.setChecked(true);
                    cbAttach.setChecked(false);
                    cbWalkInCustomer.setChecked(false);
                    cbAttach.setVisibility(View.VISIBLE);
                } else {
                    cbPassBy.setChecked(false);
                    cbAttach.setChecked(true);
                    cbWalkInCustomer.setChecked(false);
                    cbAttach.setVisibility(View.VISIBLE);
                }
                lnCustomerDetail.setVisibility(View.VISIBLE);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            if (draft.getDtDeliverySche() != null) {
                String txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                etDeliverySchedule.setText(txtDeliverSche);
            }
            if (draft.isBoolAttachCustomer()) {
                getDataProvinsi();

                lnCustomerDetail.setVisibility(View.VISIBLE);
                cbAttach.setChecked(true);
                tvContactIDHeader.setText(draft.getTxtContactID());
                tvMemberNoHeader.setText(draft.getTxtMemberID());
                tvCustomerNameHeader.setText(draft.getTxtCustomerName());
                tvPhoneNumb.setText(draft.getTxtPhoneNumber());
                etAddress.setText(draft.getTxtAddress());
//                List<String> categoriesProv = new ArrayList<String>();
//                categoriesProv.add(draft.getTxtProvince());
                /*if (categoriesProv.size() == 0) {
                    categoriesProv.add(draft.getTxtProvince());
                }
                SpinnerCustom.setAdapterSpinner(spnProvinceAddOrder, context, R.layout.custom_spinner, categoriesProv);
                SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, draft.getTxtProvince());
                SpinnerCustom.selectedItemByText(context, spnKabKotAddOrder, categoriesKabKot, draft.getTxtKabKot());
//                List<String> categoriesKabKot = new ArrayList<String>();

//                List<String> categoriesKecamatan = new ArrayList<String>();
                if (categoriesKecamatan.size() == 0) {
                    categoriesKecamatan.add(draft.getTxtKecamatan());
                }
                SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);

                if(categoriesKelurahan.size()==0){
                    categoriesKelurahan.add(draft.getTxtKelurahan());
                }
                SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
//                List<String> categoriesPostCode = new ArrayList<String>();
                if (categoriesPostCode.size() == 0) {
                    categoriesPostCode.add(draft.getTxtPostCode());
                }
                SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                */
            }
            etRemarks.setText(draft.getTxtRemarks());
        }
        spnProvinceAddOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String provinceName = categoriesProv.get(position).toString();
                String ProvinceID = HMProvinceID.get(provinceName);
                final JSONObject resJson = new JSONObject();
                try {
                    resJson.put("txtPropinsiID", ProvinceID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String strLinkSpnProvince = new clsHardCode().linkGetListKabupatenKota;

                final String mRequestBody = resJson.toString();
//                lt.setText("Retrieving data Kabupaten Kota...");
//                lt.show();

                new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkSpnProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                    @Override
                    public void onError(String response) {
                        ToastCustom.showToasty(context, response, 2);
//                        lt.hide();
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
                                        spnKabKotAddOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//                        lt.setText("Retrieving data Kecamatan...");
                                                        new VolleyUtils().makeJsonObjectRequestWithTokenWithoutProgressD(getActivity(), strLinkSpnKabKot, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                                                            @Override
                                                            public void onError(String response) {
                                                                ToastCustom.showToasty(context, response, 2);
//                                lt.hide();
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
                                                                                SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);
                                                                                spnKecamatanAddOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                                    @Override
                                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                        spinnerKecamatan(position);
                                                                                    }

                                                                                    @Override
                                                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                                                    }
                                                                                });
                                                                                if (!boolAttachCustomer) {

                                                                                    categoriesKelurahan = new ArrayList<>();
                                                                                    categoriesKelurahan.add(STRSPINNEROPT);
                                                                                    SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
                                                                                    SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, STRSPINNEROPT);

                                                                                    categoriesPostCode = new ArrayList<>();
                                                                                    categoriesPostCode.add(STRSPINNEROPT);
                                                                                    SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                                                                                    SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);
                                                                                } else {
//                                                                                    boolAttachCustomer = false;
                                                                                    SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);
                                                                                    SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, kecamatanCustomer);
                                                                                }

                                                                                if (draft != null && draft.isBoolPassBy() == false && !boolAttachCustomer && bitSO.equals("1")) {
                                                                                    SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, draft.getTxtKecamatan());
                                                                                } else {
//                                                                                    SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, STRSPINNEROPT);
//                                                                                    categoriesKelurahan.add(STRSPINNEROPT);
//                                                                                    SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKelurahan, STRSPINNEROPT);
//                                                                                    categoriesPostCode.add(STRSPINNEROPT);
//                                                                                    SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);
                                                                                }

                                                                            }
                                                                        }
                                                                    } catch (JSONException e) {

                                                                    }

                                                                }
                                                            }
                                                        });
                                                    } else {

//                                                        addDefaultSpinnerAlamat();
                                                        SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);
                                                        SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, STRSPINNEROPT);

                                                        SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
                                                        SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, STRSPINNEROPT);

//                                                        categoriesPostCode.add(STRSPINNEROPT);
//                                                        HMKodePos.put(STRSPINNEROPT, "0");
                                                        SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                                                        SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);
                                                    }
                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        if (!boolAttachCustomer) {
                                            SpinnerCustom.setAdapterSpinner(spnKabKotAddOrder, context, R.layout.custom_spinner, categoriesKabKot);
//                                            categoriesKecamatan.add(STRSPINNEROPT);
//                                            HMKecID.put(STRSPINNEROPT, "0");
                                            SpinnerCustom.setAdapterSpinner(spnKecamatanAddOrder, context, R.layout.custom_spinner, categoriesKecamatan);
                                            SpinnerCustom.selectedItemByText(context, spnKecamatanAddOrder, categoriesKecamatan, STRSPINNEROPT);

//                                            categoriesKelurahan.add(STRSPINNEROPT);
//                                            HMKel.put(STRSPINNEROPT, "0");
                                            SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
                                            SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, STRSPINNEROPT);

//                                            categoriesPostCode.add(STRSPINNEROPT);
//                                            HMKodePos.put(STRSPINNEROPT, "0");
                                            SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                                            SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);

                                        } else {
//                                            boolAttachCustomer = false;
                                            SpinnerCustom.setAdapterSpinner(spnKabKotAddOrder, context, R.layout.custom_spinner, categoriesKabKot);
                                            SpinnerCustom.selectedItemByText(context, spnKabKotAddOrder, categoriesKabKot, kabKotCustomer);

                                        }


                                        if (draft != null && draft.getTxtKabKot() != null && draft.isBoolPassBy() == false && !boolAttachCustomer && bitSO.equals("1")) {
                                            SpinnerCustom.selectedItemByText(context, spnKabKotAddOrder, categoriesKabKot, draft.getTxtKabKot());
                                        } else {
//                                            SpinnerCustom.selectedItemByText(context, spnKabKotAddOrder, categoriesKabKot, STRSPINNEROPT);
                                        }

                                    }
                                }
                            } catch (JSONException e) {

                            }

                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            /*spnKelurahanAddOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(categoriesKelurahan.size()>0){
                        String kelName = categoriesKelurahan.get(position).toString();
                        String kelID = HMKel.get(kelName);
                        if(!kelID.equals("0")){
                            final JSONObject resJson = new JSONObject();
                            try {
                                resJson.put("txtKecamatanID", kelID);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String strLinkKel = new clsHardCode().linkGetListKelurahan;

                            final String mRequestBody = resJson.toString();
//                        lt.setText("Retrieving data Kode Pos...");
                            new VolleyUtils().makeJsonObjectRequestWithTokenWithoutProgressD(getActivity(), strLinkKel, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
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
                                                    for (int n = 0; n < jsn.length(); n++) {
                                                        JSONObject object = jsn.getJSONObject(n);
                                                        String ltxKeterangan = object.getString("ltxKeterangan");
                                                        String txtNamaKabKot = object.getString("txtNamaKabKota");
                                                        String txtKabKotID = object.getString("txtKabKotaID");
                                                        String txtPropinsiID = object.getString("txtPropinsiID");
                                                        categoriesKabKot.add(txtNamaKabKot);
                                                        HMKabKotID.put(txtNamaKabKot, txtKabKotID);
                                                    }

                                                    SpinnerCustom.setAdapterSpinner(spnKabKotAddOrder, context, R.layout.custom_spinner, categoriesKabKot);
                                                }
                                            }
                                        } catch (JSONException e) {

                                        }

                                    }
                                }
                            });
                        }else{

                        }

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/


        draftData = new clsDraft();
        Helper.textWatcher(etItemCode, getActivity());

        etItemName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etItemCode != null) etItemCode.setText("");

            }
        });
        etItemCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etItemName != null) etItemName.setText("");
            }
        });
        data = new clsUserLoginRepo(context).getDataLogin(context);
        if (data != null) {
            etAgentName.setText(data.getTxtTeleName());
            etSOSource.setText(data.getTxtNamaInstitusi());
            etOrderLocation.setText(data.getTxtNamaInstitusi());
            HMOutletCode.put(data.getTxtNamaInstitusi(), data.getTxtSumberDataID());
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String date = sdf.format(dtLoginData);
            etDate.setText(date);
        }
        etOrderLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().onUserInteraction();
                final String strLinkAPI = new clsHardCode().linkGetDataOutletKALCARE;
                final JSONObject resJson = new JSONObject();
                try {
                    resJson.put("txtOutlet", "");
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
                                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                                    AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                                    final View promptViewOutlet = layoutInflater.inflate(R.layout.popup_outlet_option, null);
                                    final ListView lvOutletOption = (ListView) promptViewOutlet.findViewById(R.id.lvOutletOption);
                                    if (!jsonObject.getString("ListData").equals("null")) {
                                        JSONArray jsn = jsonObject.getJSONArray("ListData");
                                        final List<VMOutlets> contentOutletsResult = new ArrayList<VMOutlets>();
                                        VMOutlets item = new VMOutlets();
                                        for (int n = 0; n < jsn.length(); n++) {
                                            JSONObject object = jsn.getJSONObject(n);
                                            String txtSumberDataID = object.getString("txtSumberDataID");
                                            String lttxtTipeSumberDataID = object.getString("lttxtTipeSumberDataID");
                                            String txtPropinsiID = object.getString("txtPropinsiID");
                                            String txtKabKotaID = object.getString("txtKabKotaID");
                                            String txtKodePosID = object.getString("txtKodePosID");
                                            String txtNamaInstitusi = object.getString("txtNamaInstitusi");
                                            String txtAlamat = object.getString("txtAlamat");
                                            String txtCabangID = object.getString("txtCabangID");
                                            String txtSumberDataIDAlias = object.getString("txtSumberDataIDAlias");
                                            String txtNamaInstitusiAlias = object.getString("txtNamaInstitusiAlias");
                                            item = new VMOutlets();
                                            item.setTxtNamaInstitusi(txtNamaInstitusi);
                                            item.setTxtSumberDataID(txtSumberDataID);
                                            item.setTxtAlamat(txtAlamat);
                                            contentOutletsResult.add(item);
                                            HMOutletCode.put(txtNamaInstitusi, txtSumberDataID);
                                        }
                                        lvOutletOption.setAdapter(new CardAdapterOutet(context, contentOutletsResult, Color.WHITE));
                                        setListViewHeightBasedOnItems(lvOutletOption);
                                        lvOutletOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                            }
                                        });
                                        AlertDialog.Builder alertDialogBuilderOutlet = new AlertDialog.Builder(getActivity());
                                        alertDialogBuilderAttch.setView(promptViewOutlet);
//        alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                                        alertDialogBuilderAttch
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                })
                                                .setCancelable(false)
                                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                        cbAttach.setChecked(false);
                                                    }
                                                });
                                        final AlertDialog alertD = alertDialogBuilderAttch.create();
                                        alertD.show();
                                        alertD.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
//                                                getActivity().onUserInteraction();
                                                getActivity().onUserInteraction();
                                                if (lvOutletOption.getCheckedItemPosition() > -1) {
                                                    int position = lvOutletOption.getCheckedItemPosition();
                                                    String txtOutlet = contentOutletsResult.get(position).getTxtNamaInstitusi();
                                                    etOrderLocation.setText(txtOutlet);
                                                    alertD.dismiss();
                                                } else {
                                                    ToastCustom.showToasty(context, "Please pick the outlet first", 3);
                                                }
                                            }
                                        });
                                    }
                                }
                            } catch (JSONException ex) {

                            }
                        }
                    }
                });
            }
        });

        lvContentItemSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastCustom.showToasty(context, "Item picked", 3);
                String itemName = contentLibs2.get(position).toString();
                String itemPrice = HMItemPrice.get(itemName);
                String itemCode = HMItemCode.get(itemName);
                etItemCode.setText(itemCode);
                etItemName.setText(itemName);
                tvItemPrice.setText(itemPrice);
                etQty.setFocusable(true);
            }
        });
        cbPassBy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Debug.waitForDebugger();
//                getActivity().onUserInteraction();
                getActivity().onUserInteraction();
                if (boolDraft) {
//                    boolean boolDraftWalkin = draft.isBoolWalkinCustomer();
                    boolean boolPassBy = draft.isBoolPassBy();
//                    boolean boolDeliverySch = draft.isBoolAttachCustomer();
                    if (boolPassBy) {
                        new clsProductDraftRepo(context).clearAllData();
                    }
                }
                if (isChecked) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                    String date = sdf.format(dtLoginData);
                    etDeliverySchedule.setText(date);
                    etDeliverySchedule.setClickable(false);
                    etDeliverySchedule.setEnabled(false);
//                    etDeliverySchedule.setTextColor(R.drawable.edittext_disabled_style);


                    cbWalkInCustomer.setChecked(false);
                    intPassBy = 1;
                    intWalkinCustomer = 0;
//                    cbAttach.setChecked(true);
                    cbAttach.setChecked(false);
                    cbAttach.setVisibility(View.VISIBLE);
                    lnCustomerDetail.setVisibility(View.GONE);
                } else {
                    if (!cbWalkInCustomer.isChecked()) {
                        cbAttach.setChecked(true);
                        cbAttach.setVisibility(View.VISIBLE);
                    }
                    cbWalkInCustomer.setEnabled(true);
                    intPassBy = 0;

                    /*if (!cbAttach.isChecked()) {
                        etAddress.setVisibility(View.GONE);
                        lnCustomerDetail.setVisibility(View.GONE);
                    } else {
                        etAddress.setVisibility(View.VISIBLE);
                        lnCustomerDetail.setVisibility(View.VISIBLE);
                    }*/
                }
            }
        });
        cbWalkInCustomer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                getActivity().onUserInteraction();
                getActivity().onUserInteraction();
                if (!boolRestore) {
                    if (isChecked) {
                        if (boolDraft) {
                            boolean boolDraftWalkin = draft.isBoolWalkinCustomer();
//                            boolean boolPassBy = draft.isBoolPassBy();
//                    boolean boolDeliverySch = draft.isBoolAttachCustomer();
                            if (boolDraftWalkin) {
                                new clsProductDraftRepo(context).clearAllData();
                            }
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                        String date = sdf.format(dtLoginData);
                        etDeliverySchedule.setText(date);
                        etDeliverySchedule.setClickable(false);
                        etDeliverySchedule.setEnabled(false);
//                        etDeliverySchedule.setTextColor(R.drawable.edittext_disabled_style);

                        intWalkinCustomer = 1;
                        cbAttach.setChecked(false);
                        cbPassBy.setChecked(false);
                        cbAttach.setVisibility(View.VISIBLE);
                        intPassBy = 0;
                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                        AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                        final View promptView = layoutInflater.inflate(R.layout.popup_customer, null);
//                    unbinder = ButterKnife.bind(this, promptView);
                        Button btnSearchCustomer = (Button) promptView.findViewById(R.id.btnSearchCustomer);
                        final Spinner spnOpsiSearchCustomer = (Spinner) promptView.findViewById(R.id.spnJenisCustomer);
                        final EditText etSearchCustomer = (EditText) promptView.findViewById(R.id.etSearchCustomer);
                        final EditText etContactID = (EditText) promptView.findViewById(R.id.etContactID);
                        final EditText etPhoneNumb = (EditText) promptView.findViewById(R.id.etPhoneNumb);
                        final EditText etMemberNo = (EditText) promptView.findViewById(R.id.etMemberNo);
                        final TextView tvMemberNo = (TextView) promptView.findViewById(R.id.tvMemberNo);
                        final EditText etCustomerName = (EditText) promptView.findViewById(R.id.etCustomerName);
                        Helper.textWatcher(etCustomerName, getActivity());
                        Helper.textWatcher(etMemberNo, getActivity());
                        Helper.textWatcher(etPhoneNumb, getActivity());
                        Helper.textWatcher(etSearchCustomer, getActivity());
//                    final EditText spnPostCodeCustomer = (EditText) promptView.findViewById(R.id.etPostCodeCustomer);
                        final Spinner spnPostCodeCustomer = (Spinner) promptView.findViewById(R.id.spnPostCodeCustomer);
                        final Spinner spnProvinceCustomer = (Spinner) promptView.findViewById(R.id.spnProvinceCustomer);
                        final Spinner spnKabKotCustomer = (Spinner) promptView.findViewById(R.id.spnKabKotCustomer);
                        final Spinner spnKecamatanCustomer = (Spinner) promptView.findViewById(R.id.spnKecamatanCustomer);
                        final Spinner spnKelurahanCustomer = (Spinner) promptView.findViewById(R.id.spnKelurahanCustomer);

                        alertDialogBuilderAttch.setView(promptView);
//                    alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                        alertDialogBuilderAttch
                                .setCancelable(false)
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        cbAttach.setChecked(false);
                                        cbPassBy.setChecked(true);
                                        cbWalkInCustomer.setEnabled(true);
                                    }
                                });
                        final AlertDialog alertDHeader = alertDialogBuilderAttch.create();
                        alertDHeader.show();
                        final String strLinkAPI = new clsHardCode().linkSearchCustomer;
                        final JSONObject resJson = new JSONObject();
                        btnSearchCustomer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getActivity().onUserInteraction();
                                final String keyword = etSearchCustomer.getText().toString();
                                if (keyword.equals("")) {
                                    ToastCustom.showToasty(context, "Keyword Empty", 4);
                                } else {
                                    if (spnOpsiSearchCustomer.getSelectedItem().toString().equals(SPNMEMBERID)) {
                                        try {
                                            resJson.put("txtPhone", "");
                                            resJson.put("txtMember", keyword);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            resJson.put("txtPhone", keyword);
                                            resJson.put("txtMember", "");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
                                                        if (!jsonObject.getString("ListData").equals("null")) {
                                                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                                                            final View promptView = layoutInflater.inflate(R.layout.popup_customer_search, null);

                                                            final ListView lvCustomerSearch = (ListView) promptView.findViewById(R.id.lvCustSearchResult);
                                                            final List<VMCustomers> contentSearchResult = new ArrayList<VMCustomers>();
                                                            VMCustomers item = new VMCustomers();

                                                            JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                            for (int n = 0; n < jsn.length(); n++) {
                                                                JSONObject object = jsn.getJSONObject(n);
                                                                String txtContactID = object.getString("txtKontakID");
                                                                String txtAlamat = object.getString("txtAlamat");
                                                                String txtNama = object.getString("txtNama");
                                                                String txtKabKot = object.getString("txtNamaKabKota");
                                                                String txtProv = object.getString("txtNamaPropinsi");
                                                                String txtjenisAlm = object.getString("JenisAlamat");
                                                                String txtListMed = object.getString("txtListMedia");
                                                                String txtKec = object.getString("txtNamaKecamatan");
                                                                String txtKel = object.getString("txtNamaKelurahan");
                                                                String txtNHDSiteID = object.getString("txtNHDSiteID");
                                                                String txtProvID = object.getString("txtPropinsiID");
                                                                String txtKABKotID = object.getString("txtKabKotaID");
                                                                String txtKodePos = object.getString("txtKodePos");
                                                                String txtKacamatan = object.getString("txtKecamatan");

                                                                item = new VMCustomers();
                                                                item.setTxtContactID(txtContactID);
                                                                item.setTxtAlamat(txtAlamat);
                                                                item.setTxtNama(txtNama);
                                                                item.setTxtKabKot(txtKabKot);
                                                                item.setTxtProv(txtProv);
                                                                item.setTxtjenisAlm(txtjenisAlm);
                                                                item.setTxtListMed(txtListMed);
                                                                item.setTxtKec(txtKec);
                                                                item.setTxtKel(txtKel);
                                                                item.setTxtNHDSiteID(txtNHDSiteID);
                                                                item.setTxtProvID(txtProvID);
                                                                item.setTxtKABKotID(txtKABKotID);
                                                                item.setTxtKodePos(txtKodePos);
                                                                item.setTxtKecID(txtKacamatan);

                                                                contentSearchResult.add(item);
                                                            }
                                                            lvCustomerSearch.setAdapter(new CardAdapterCustomerSearch(context, contentSearchResult, Color.WHITE));
                                                            final VMItems itemSelected = null;
                                                            lvCustomerSearch.setSelection(0);
                                                            AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                                                            alertDialogBuilderAttch.setView(promptView);
//        alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                                                            /*alertDialogBuilderAttch
                                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                        }
                                                                    })
                                                                    .setCancelable(false)
                                                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {
                                                                            dialog.cancel();
                                                                        }
                                                                    });*/
                                                            final AlertDialog alertDChild = alertDialogBuilderAttch.create();
                                                            lvCustomerSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                @Override
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                    view.setSelected(true);
                                                                    getActivity().onUserInteraction();
                                                                    String outlet = dataLogin.getTxtNamaInstitusi();
                                                                    String contactID = "";
                                                                    String province = STRKALCARE;
                                                                    String kabkot = STRKALCARE;
                                                                    String kecamatan = STRKALCARE;
                                                                    String kelurahan = outlet;
                                                                    String postCode = outlet;
                                                                    String name = "";
                                                                    String address = outlet;
                                                                    String provinceID = "";
                                                                    String kabKotID = "";
                                                                    String kecID = "";
                                                                    String kelID = "";
                                                                    String phoneNumb = "";

                                                                    if (lvCustomerSearch.getCheckedItemPosition() < 0) {
                                                                        ToastCustom.showToasty(context, "Pick customer first", 3);
                                                                    } else {
//                                                                        int position = lvCustomerSearch.getCheckedItemPosition();
                                                                        contactID = contentSearchResult.get(position).getTxtContactID();
//                                                                            province = contentSearchResult.get(position).getTxtProv();
//                                                                            kabkot = contentSearchResult.get(position).getTxtKabKot();
                                                                        phoneNumb = contentSearchResult.get(position).getTxtListMed();
//                                                                            kecamatan = contentSearchResult.get(position).getTxtKec();
//                                                                            postCode = contentSearchResult.get(position).getTxtKodePos();
//                                                                            address = contentSearchResult.get(position).getTxtAlamat();
                                                                        name = contentSearchResult.get(position).getTxtNama();
                                                                        provinceID = province;
                                                                        kabKotID = kabkot;
                                                                        kecID = kecamatan;
                                                                        kelID = outlet;
                                                                        kelurahan = outlet;
                                                                        if (!spnOpsiSearchCustomer.getSelectedItem().toString().equals(SPNMEMBERID)) {
                                                                            tvMemberNo.setText("Phone Number");
                                                                        }
                                                                        etMemberNo.setText(keyword);
                                                                        etContactID.setText(contactID);
                                                                        etPhoneNumb.setText(phoneNumb);
//                                                                        spnPostCodeCustomer.setText(postCode);
                                                                        etCustomerName.setText(name);
                                                                        etAddress.setText(address);
                                                                        List<String> categoriesPostCode = new ArrayList<String>();
                                                                        String kodeposKelurahan = postCode + "-" + kelurahan;
                                                                        categoriesPostCode.add(kodeposKelurahan);
                                                                        HMKodePos.put(kodeposKelurahan, postCode);


                                                                        SpinnerCustom.setAdapterSpinner(spnPostCodeCustomer, context, R.layout.custom_spinner, categoriesPostCode);
//                                                                            final List<String> categoriesProv = new ArrayList<String>();
                                                                        categoriesProv.add(province);
                                                                        HMProvinceID.put(province, provinceID);
                                                                        SpinnerCustom.setAdapterSpinner(spnProvinceCustomer, context, R.layout.custom_spinner, categoriesProv);

                                                                        List<String> categoriesKabKot = new ArrayList<String>();
                                                                        categoriesKabKot.add(kabkot);
                                                                        HMKabKotID.put(kabkot, kabKotID);
                                                                        SpinnerCustom.setAdapterSpinner(spnKabKotCustomer, context, R.layout.custom_spinner, categoriesKabKot);

                                                                        List<String> categoriesKecamatan = new ArrayList<String>();
                                                                        categoriesKecamatan.add(kecamatan);
                                                                        HMKecID.put(kecamatan, kecID);
                                                                        SpinnerCustom.setAdapterSpinner(spnKecamatanCustomer, context, R.layout.custom_spinner, categoriesKecamatan);

                                                                        List<String> categoriesKelurahan = new ArrayList<String>();
                                                                        categoriesKelurahan.add(kelurahan);
                                                                        HMKel.put(kelurahan, kelurahan);
                                                                        SpinnerCustom.setAdapterSpinner(spnKelurahanCustomer, context, R.layout.custom_spinner, categoriesKelurahan);

                                                                        if (contactID.equals("") && province.equals("") && kabkot.equals("") && phoneNumb.equals("") && kecamatan.equals("") && kelurahan.equals("") && postCode.equals("")) {
                                                                            ToastCustom.showToasty(context, "Data Invalid", 2);
                                                                        } else {
                                                                            alertDChild.dismiss();
                                                                        }
                                                                        getActivity().onUserInteraction();
                                                                        try {
                                                                            resJson.put("txtPropinsiID", "");
                                                                        } catch (JSONException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        String strLinkProvince = new clsHardCode().linkGetListPropinsi;
//getDataProvinsi();
                                                                        final String mRequestBody = resJson.toString();
                                                                        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
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

                                                                                                for (int n = 0; n < jsn.length(); n++) {
                                                                                                    JSONObject object = jsn.getJSONObject(n);
                                                                                                    String ltxKeterangan = object.getString("ltxKeterangan");
                                                                                                    String txtNamaPropinsi = object.getString("txtNamaPropinsi");
                                                                                                    String txtPropinsiID = object.getString("txtPropinsiID");
                                                                                                    String txtNegaraID = object.getString("txtNegaraID");
                                                                                                    categoriesProv.add(txtNamaPropinsi);
                                                                                                    HMProvinceID.put(txtNamaPropinsi, txtPropinsiID);
                                                                                                }
                                                                                            }
                                                                                            if (etContactID.getText().toString().equals("")) {
                                                                                                ToastCustom.showToasty(context, "Customer Empty", 3);
                                                                                            } else {
                                                                                                tvContactIDHeader.setText(etContactID.getText().toString());
                                                                                                tvMemberNoHeader.setText(etMemberNo.getText().toString());
                                                                                                tvCustomerNameHeader.setText(etCustomerName.getText().toString());
                                                                                                tvPhoneNumb.setText(etPhoneNumb.getText().toString());
                                                                                                String outlet = dataLogin.getTxtNamaInstitusi();

                                                                                                provinceCustomer = STRKALCARE;
                                                                                                kabKotCustomer = STRKALCARE;
                                                                                                kecamatanCustomer = STRKALCARE;
                                                                                                kelurahanCustomer = outlet;
                                                                                                postCodeCustomer = outlet + "-" + outlet;
                                                                                                int a = intWalkinCustomer;
//                                                        if(intWalkinCustomer != 1)
                                                                                                SpinnerCustom.setAdapterSpinner(spnProvinceAddOrder, context, R.layout.custom_spinner, categoriesProv);
                                                                                                SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, provinceCustomer);
                                                                                                if (intWalkinCustomer == 1) {
                                                                                                    spnProvinceAddOrder.setEnabled(false);
                                                                                                    spnKabKotAddOrder.setEnabled(false);
                                                                                                    spnKecamatanAddOrder.setEnabled(false);
                                                                                                    spnKelurahanAddOrder.setEnabled(false);
                                                                                                    spnPostCodeAddOrder.setEnabled(false);
                                                                                                } else {
                                                                                                    spnProvinceAddOrder.setEnabled(true);
                                                                                                    spnKabKotAddOrder.setEnabled(true);
                                                                                                    spnKecamatanAddOrder.setEnabled(true);
                                                                                                    spnKelurahanAddOrder.setEnabled(true);
                                                                                                    spnPostCodeAddOrder.setEnabled(true);
                                                                                                }

                                                                                                if (provinceCustomer.equals("") || kabKotCustomer.equals("") || kecamatanCustomer.equals("") || kelurahanCustomer.equals("") || postCodeCustomer.equals("")) {
                                                                                                    ToastCustom.showToasty(context, "Invalid Data Customer", 3);
                                                                                                } else {
                                                                                                    alertDHeader.dismiss();
                                                                                                    alertDChild.dismiss();
                                                                                                    lnCustomerDetail.setVisibility(View.VISIBLE);
                                                                                                    boolAttachCustomer = true;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } catch (JSONException e) {

                                                                                    }

                                                                                }
                                                                            }
                                                                        });


                                                                    }

                                                                }
                                                            });

                                                            if (jsn.length() == 0) {
                                                                ToastCustom.showToasty(context, "Customer Not Found", 3);
                                                            } else {
                                                                alertDChild.show();
                                                                /*alertDChild.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {


                                                                    }
                                                                });*/
                                                            }

                                                        }
                                                    } else {
                                                        ToastCustom.showToasty(context, warn, 2);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                                String a = "";
                                            }
                                        }
                                    });
                                }


                            }
                        });
                        List<String> categories = new ArrayList<String>();
//                    categories.add("Phone Number");
                        categories.add(SPNMEMBERID);
                        categories.add(SPNPHONE);
                        SpinnerCustom.setAdapterSpinner(spnOpsiSearchCustomer, context, R.layout.custom_spinner, categories);


                        alertDHeader.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });
                    } else {
                        intWalkinCustomer = 0;
                        if (!cbAttach.isChecked()) {
                            cbPassBy.setChecked(true);
                        }

                    }
                }

            }
        });
        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getActivity().onUserInteraction();
                if (!boolRestore) {
                    if (isChecked) {
                        if (boolDraft) {
//                    boolean boolDraftWalkin = draft.isBoolWalkinCustomer();
//                            boolean boolPassBy = draft.isBoolPassBy();
                            boolean boolDeliverySch = draft.isBoolAttachCustomer();
                            if (boolDeliverySch) {
                                new clsProductDraftRepo(context).clearAllData();
                            }
                        }
                        etDeliverySchedule.setClickable(true);
                        etDeliverySchedule.setEnabled(true);
                        cbPassBy.setChecked(false);
                        cbWalkInCustomer.setChecked(false);
                        intPassBy = 0;
                        intWalkinCustomer = 0;
                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                        AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                        final View promptViewCustSearchHeader = layoutInflater.inflate(R.layout.popup_customer, null);
                        /*final AlertDialog alertDCustSearchHeader = alertDialogBuilderAttch.create();
                        alertDCustSearchHeader.show();*/
//                    unbinder = ButterKnife.bind(this, promptView);
                        Button btnSearchCustomer = (Button) promptViewCustSearchHeader.findViewById(R.id.btnSearchCustomer);
                        final Spinner spnOpsiSearchCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnJenisCustomer);
                        final EditText etSearchCustomer = (EditText) promptViewCustSearchHeader.findViewById(R.id.etSearchCustomer);
                        Helper.textWatcher(etSearchCustomer, getActivity());
                        final EditText etContactID = (EditText) promptViewCustSearchHeader.findViewById(R.id.etContactID);
                        final EditText etPhoneNumb = (EditText) promptViewCustSearchHeader.findViewById(R.id.etPhoneNumb);
                        final EditText etMemberNo = (EditText) promptViewCustSearchHeader.findViewById(R.id.etMemberNo);
                        final TextView tvMemberNo = (TextView) promptViewCustSearchHeader.findViewById(R.id.tvMemberNo);
                        final EditText etCustomerName = (EditText) promptViewCustSearchHeader.findViewById(R.id.etCustomerName);
//                    final EditText spnPostCodeCustomer = (EditText) promptView.findViewById(R.id.etPostCodeCustomer);
                        final Spinner spnPostCodeCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnPostCodeCustomer);
                        final Spinner spnProvinceCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnProvinceCustomer);
                        final Spinner spnKabKotCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnKabKotCustomer);
                        final Spinner spnKecamatanCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnKecamatanCustomer);
                        final Spinner spnKelurahanCustomer = (Spinner) promptViewCustSearchHeader.findViewById(R.id.spnKelurahanCustomer);
                        final String strLinkAPI = new clsHardCode().linkSearchCustomer;
                        final JSONObject resJson = new JSONObject();
                        alertDialogBuilderAttch.setView(promptViewCustSearchHeader);
//                    alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                        alertDialogBuilderAttch
                                .setCancelable(false)
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        cbAttach.setChecked(false);
                                        cbPassBy.setChecked(true);
                                    }
                                });
                        final AlertDialog alertDCustSearchHeader = alertDialogBuilderAttch.create();

                        alertDCustSearchHeader.show();
                        /*alertDCustSearchHeader.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });*/
                        btnSearchCustomer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getActivity().onUserInteraction();
                                final String keyword = etSearchCustomer.getText().toString();
                                if (keyword.equals("")) {
                                    ToastCustom.showToasty(context, "Keyword Empty", 4);
                                } else {
                                    if (spnOpsiSearchCustomer.getSelectedItem().toString().equals(SPNMEMBERID)) {
                                        try {
                                            resJson.put("txtPhone", "");
                                            resJson.put("txtMember", keyword);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            resJson.put("txtPhone", keyword);
                                            resJson.put("txtMember", "");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
                                                        if (!jsonObject.getString("ListData").equals("null")) {
                                                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                                                            final View promptViewCustSearchCh = layoutInflater.inflate(R.layout.popup_customer_search, null);

                                                            final ListView lvCustomerSearch = (ListView) promptViewCustSearchCh.findViewById(R.id.lvCustSearchResult);
                                                            final List<VMCustomers> contentSearchResult = new ArrayList<VMCustomers>();
                                                            VMCustomers item = new VMCustomers();

                                                            JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                            for (int n = 0; n < jsn.length(); n++) {
                                                                JSONObject object = jsn.getJSONObject(n);
                                                                String txtContactID = object.getString("txtKontakID");
                                                                String txtAlamat = object.getString("txtAlamat");
                                                                String txtNama = object.getString("txtNama");
                                                                String txtKabKot = object.getString("txtNamaKabKota");
                                                                String txtProv = object.getString("txtNamaPropinsi");
                                                                String txtjenisAlm = object.getString("JenisAlamat");
                                                                String txtListMed = object.getString("txtListMedia");
                                                                String txtKec = object.getString("txtNamaKecamatan");
                                                                String txtKel = object.getString("txtNamaKelurahan");
                                                                String txtNHDSiteID = object.getString("txtNHDSiteID");
                                                                String txtProvID = object.getString("txtPropinsiID");
                                                                String txtKABKotID = object.getString("txtKabKotaID");
                                                                String txtKodePos = object.getString("txtKodePos");
                                                                String txtKacamatan = object.getString("txtKecamatan");

                                                                item = new VMCustomers();
                                                                item.setTxtContactID(txtContactID);
                                                                item.setTxtAlamat(txtAlamat);
                                                                item.setTxtNama(txtNama);
                                                                item.setTxtKabKot(txtKabKot);
                                                                item.setTxtProv(txtProv);
                                                                item.setTxtjenisAlm(txtjenisAlm);
                                                                item.setTxtListMed(txtListMed);
                                                                item.setTxtKec(txtKec);
                                                                item.setTxtKel(txtKel);
                                                                item.setTxtNHDSiteID(txtNHDSiteID);
                                                                item.setTxtProvID(txtProvID);
                                                                item.setTxtKABKotID(txtKABKotID);
                                                                item.setTxtKodePos(txtKodePos);
                                                                item.setTxtKecID(txtKacamatan);

                                                                contentSearchResult.add(item);
                                                            }
                                                            lvCustomerSearch.setAdapter(new CardAdapterCustomerSearch(context, contentSearchResult, Color.WHITE));
                                                            final VMItems itemSelected = null;
                                                            lvCustomerSearch.setSelection(0);
                                                            AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                                                            alertDialogBuilderAttch.setView(promptViewCustSearchCh);
//        alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                                                           /* alertDialogBuilderAttch
                                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                        }
                                                                    })
                                                                    .setCancelable(false)
                                                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {
                                                                            dialog.cancel();
                                                                        }
                                                                    });*/
                                                            final AlertDialog alertDCustSearch = alertDialogBuilderAttch.create();
                                                            lvCustomerSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                @Override
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                    view.setSelected(true);
                                                                    getActivity().onUserInteraction();
                                                                    String contactID = "";
                                                                    String province = "";
                                                                    String kabkot = "";
                                                                    String kecamatan = "";
                                                                    String kelurahan = "";
                                                                    String postCode = "";
                                                                    String name = "";
                                                                    String address = "";
                                                                    String provinceID = "";
                                                                    String kabKotID = "";
                                                                    String kecID = "";
                                                                    String kelID = "";
                                                                    String phoneNumb = "";

                                                                    if (lvCustomerSearch.getCheckedItemPosition() < 0) {
                                                                        ToastCustom.showToasty(context, "Pick customer first", 3);
                                                                    } else {
//                                                                        int position = lvCustomerSearch.getCheckedItemPosition();
                                                                        contactID = contentSearchResult.get(position).getTxtContactID();
                                                                        province = contentSearchResult.get(position).getTxtProv();
                                                                        kabkot = contentSearchResult.get(position).getTxtKabKot();
                                                                        phoneNumb = contentSearchResult.get(position).getTxtListMed();
                                                                        kecamatan = contentSearchResult.get(position).getTxtKec();
                                                                        postCode = contentSearchResult.get(position).getTxtKodePos();
                                                                        address = contentSearchResult.get(position).getTxtAlamat();
                                                                        name = contentSearchResult.get(position).getTxtNama();
                                                                        provinceID = contentSearchResult.get(position).getTxtProvID();
                                                                        kabKotID = contentSearchResult.get(position).getTxtProvID();
                                                                        kecID = contentSearchResult.get(position).getTxtKecID();
                                                                        kelID = contentSearchResult.get(position).getTxtKel();
                                                                        kelurahan = contentSearchResult.get(position).getTxtKel();
                                                                        if (!spnOpsiSearchCustomer.getSelectedItem().toString().equals(SPNMEMBERID)) {
                                                                            tvMemberNo.setText("Phone Number");
                                                                        }
                                                                        etMemberNo.setText(keyword);
                                                                        etContactID.setText(contactID);
                                                                        etPhoneNumb.setText(phoneNumb);
//                                                                        spnPostCodeCustomer.setText(postCode);
                                                                        etCustomerName.setText(name);
                                                                        etAddress.setText(address);
                                                                        List<String> categoriesPostCode = new ArrayList<String>();
                                                                        String kodeposKelurahan = postCode + "-" + kelurahan;
                                                                        categoriesPostCode.add(kodeposKelurahan);
                                                                        HMKodePos.put(kodeposKelurahan, postCode);

                                                                        provinceCustomer = province;
                                                                        kabKotCustomer = kabkot;
                                                                        kecamatanCustomer = kecamatan;
                                                                        kelurahanCustomer = kelurahan;
                                                                        postCodeCustomer = postCode;
                                                                        SpinnerCustom.setAdapterSpinner(spnPostCodeCustomer, context, R.layout.custom_spinner, categoriesPostCode);
                                                                            /*List<String> categoriesProv = new ArrayList<String>();
                                                                            categoriesProv.add(province);
                                                                            HMProvinceID.put(province, provinceID);
                                                                            SpinnerCustom.setAdapterSpinner(spnProvinceCustomer, context, R.layout.custom_spinner, categoriesProv);

                                                                            List<String> categoriesKabKot = new ArrayList<String>();
                                                                            categoriesKabKot.add(kabkot);
                                                                            HMKabKotID.put(kabkot, kabKotID);
                                                                            SpinnerCustom.setAdapterSpinner(spnKabKotCustomer, context, R.layout.custom_spinner, categoriesKabKot);

                                                                            List<String> categoriesKecamatan = new ArrayList<String>();
                                                                            categoriesKecamatan.add(kecamatan);
                                                                            HMKecID.put(kecamatan, kecID);
                                                                            SpinnerCustom.setAdapterSpinner(spnKecamatanCustomer, context, R.layout.custom_spinner, categoriesKecamatan);

                                                                            List<String> categoriesKelurahan = new ArrayList<String>();
                                                                            categoriesKelurahan.add(kelurahan);
                                                                            HMKel.put(kelurahan, kelurahan);
                                                                            SpinnerCustom.setAdapterSpinner(spnKelurahanCustomer, context, R.layout.custom_spinner, categoriesKelurahan);
*/
                                                                        if (contactID.equals("") && province.equals("") && kabkot.equals("") && phoneNumb.equals("") && kecamatan.equals("") && kelurahan.equals("") && postCode.equals("")) {
                                                                            ToastCustom.showToasty(context, "Data Invalid", 2);
                                                                        } else {
                                                                            alertDCustSearch.dismiss();
                                                                            getActivity().onUserInteraction();
                                                                            try {
                                                                                resJson.put("txtPropinsiID", "");
                                                                            } catch (JSONException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            String strLinkProvince = new clsHardCode().linkGetListPropinsi;
//getDataProvinsi();
                                                                            final String mRequestBody = resJson.toString();
                                                                            new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
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

                                                                                                    for (int n = 0; n < jsn.length(); n++) {
                                                                                                        JSONObject object = jsn.getJSONObject(n);
                                                                                                        String ltxKeterangan = object.getString("ltxKeterangan");
                                                                                                        String txtNamaPropinsi = object.getString("txtNamaPropinsi");
                                                                                                        String txtPropinsiID = object.getString("txtPropinsiID");
                                                                                                        String txtNegaraID = object.getString("txtNegaraID");
                                                                                                        categoriesProv.add(txtNamaPropinsi);
                                                                                                        HMProvinceID.put(txtNamaPropinsi, txtPropinsiID);
                                                                                                    }
                                                                                                }
                                                                                                if (etContactID.getText().toString().equals("")) {
                                                                                                    ToastCustom.showToasty(context, "Customer Empty", 3);
                                                                                                } else {
                                                                                                    tvContactIDHeader.setText(etContactID.getText().toString());
                                                                                                    tvMemberNoHeader.setText(etMemberNo.getText().toString());
                                                                                                    tvCustomerNameHeader.setText(etCustomerName.getText().toString());
                                                                                                    tvPhoneNumb.setText(etPhoneNumb.getText().toString());

//                                                                                                        provinceCustomer = spnProvinceCustomer.getSelectedItem().toString();
//                                                                                                        kabKotCustomer = spnKabKotCustomer.getSelectedItem().toString();
//                                                                                                        kecamatanCustomer = spnKecamatanCustomer.getSelectedItem().toString();
//                                                                                                        kelurahanCustomer = spnKelurahanCustomer.getSelectedItem().toString();
//                                                                                                        postCodeCustomer = spnPostCodeCustomer.getSelectedItem().toString();


                                                                                                    SpinnerCustom.setAdapterSpinner(spnProvinceAddOrder, context, R.layout.custom_spinner, categoriesProv);
                                                                                                    SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, provinceCustomer);
                                                                                                    if (intWalkinCustomer == 1) {
                                                                                                        spnProvinceAddOrder.setEnabled(false);
                                                                                                        spnKabKotAddOrder.setEnabled(false);
                                                                                                        spnKecamatanAddOrder.setEnabled(false);
                                                                                                        spnKelurahanAddOrder.setEnabled(false);
                                                                                                        spnPostCodeAddOrder.setEnabled(false);
                                                                                                    } else {
                                                                                                        spnProvinceAddOrder.setEnabled(true);
                                                                                                        spnKabKotAddOrder.setEnabled(true);
                                                                                                        spnKecamatanAddOrder.setEnabled(true);
                                                                                                        spnKelurahanAddOrder.setEnabled(true);
                                                                                                        spnPostCodeAddOrder.setEnabled(true);
                                                                                                    }
                                                                                                    if (provinceCustomer.equals("") || kabKotCustomer.equals("") || kecamatanCustomer.equals("") || kelurahanCustomer.equals("") || postCodeCustomer.equals("")) {
                                                                                                        ToastCustom.showToasty(context, "Invalid Data Customer", 3);
                                                                                                    } else {
                                                                                                        alertDCustSearch.dismiss();
                                                                                                        alertDCustSearchHeader.dismiss();
                                                                                                        lnCustomerDetail.setVisibility(View.VISIBLE);
                                                                                                        boolAttachCustomer = true;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } catch (JSONException e) {

                                                                                        }

                                                                                    }
                                                                                }
                                                                            });
                                                                        }


                                                                    }

                                                                }
                                                            });

                                                            /*alertDCustSearch.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {


                                                                }
                                                            });*/
                                                            if (jsn.length() == 0) {
                                                                ToastCustom.showToasty(context, "Customer Not Found", 3);
                                                            } else {
                                                                alertDCustSearch.show();

                                                            }

                                                        }
                                                    } else {
                                                        ToastCustom.showToasty(context, warn, 2);
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                                String a = "";
                                            }
                                        }
                                    });
                                }


                            }
                        });
                        List<String> categories = new ArrayList<String>();
//                    categories.add("Phone Number");
                        categories.add(SPNMEMBERID);
                        categories.add(SPNPHONE);
                        SpinnerCustom.setAdapterSpinner(spnOpsiSearchCustomer, context, R.layout.custom_spinner, categories);


                    } else {
                        if (intWalkinCustomer != 1) {
                            intPassBy = 1;
                            lnCustomerDetail.setVisibility(View.GONE);
                            tvContactIDHeader.setText("");
                            tvMemberNoHeader.setText("");
                            tvCustomerNameHeader.setText("");
                            tvPhoneNumb.setText("");
                            categoriesPostCode.add("");
                            cbPassBy.setChecked(true);
                        }

                    }
                } else {
                    boolRestore = false;
                }

            }
        });


        if (cbPassBy.isChecked()) {
            cbAttach.setChecked(false);
            lnCustomerDetail.setVisibility(View.GONE);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String date = sdf.format(dtLoginData);
            etDeliverySchedule.setText(date);
            etDeliverySchedule.setClickable(false);
            etDeliverySchedule.setEnabled(false);
//            etDeliverySchedule.setTextColor(R.drawable.edittext_disabled_style);
        } else {
            etDeliverySchedule.setClickable(true);
            etDeliverySchedule.setEnabled(true);
        }
        return v;
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void startSessionPopUp() {
        cancelTimer();
        SharedPreferences prefs = context.getSharedPreferences(LoginActivity.Preference_Session, MODE_PRIVATE);
        String restoredText = prefs.getString("time", "15");
        if (restoredText != null) {
            String txtTime = prefs.getString("time", "15");
            if (!txtTime.equals("")) {
                int intTime = Integer.parseInt(txtTime);
                intSession = (intTime * 60 * 1000) - 20000;
            }
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (alertDProductSearch != null) {
                    alertDProductSearch.dismiss();
                }
                if (alertDSearchitem != null) {
                    alertDSearchitem.dismiss();
                }
            }
        }, intSession);
    }

    private void getDataProvinsi() {
        final JSONObject resJson = new JSONObject();
        try {
            resJson.put("txtPropinsiID", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strLinkProvince = new clsHardCode().linkGetListPropinsi;

        final String mRequestBody = resJson.toString();
        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkProvince, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
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

                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String ltxKeterangan = object.getString("ltxKeterangan");
                                    String txtNamaPropinsi = object.getString("txtNamaPropinsi");
                                    String txtPropinsiID = object.getString("txtPropinsiID");
                                    String txtNegaraID = object.getString("txtNegaraID");
                                    categoriesProv.add(txtNamaPropinsi);
                                    HMProvinceID.put(txtNamaPropinsi, txtPropinsiID);

                                }
                                SpinnerCustom.setAdapterSpinner(spnProvinceAddOrder, context, R.layout.custom_spinner, categoriesProv);
                                SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, draft.getTxtProvince());
                            }
                        }
                    } catch (JSONException e) {

                    }

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult.getContents() == null && scanResult.getFormatName() == null) {
                ToastCustom.showToasty(context, "Barcode Scanner Canceled", 3);
                return;
            } else {
                ToastCustom.showToasty(context, "Barcode Scanner Done", 1);
                String barcode = scanResult.getContents().toString();
                String strLinkAPI = new clsHardCode().linkSearch;
                final JSONObject resJson = new JSONObject();
                try {
                    resJson.put("txtProductBarcode", barcode);
                    resJson.put("txtProductCode", "");
                    resJson.put("txtProductDesc", "");
//            resJson.put("txtRefreshToken", token);
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
                            getActivity().onUserInteraction();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("intResult");
                                String warn = jsonObject.getString("txtMessage");
                                if (result == 1) {
                                    if (!jsonObject.getString("ListData").equals("null")) {

                                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                                        final View promptView = layoutInflater.inflate(R.layout.popup_product_search_barcode, null);
                                        /*final ListView lvSearchResult = (ListView) promptView.findViewById(R.id.lvItemSearchResult);
                                        final EditText etQtySearch = (EditText) promptView.findViewById(R.id.etQty);
                                        final EditText etDiscount = (EditText) promptView.findViewById(R.id.etDiscount);
                                        final EditText etPrice = (EditText) promptView.findViewById(R.id.etPrice);
                                        final EditText etBonusPoint = (EditText) promptView.findViewById(R.id.etBonusPoint);
                                        final EditText etBasedPoint = (EditText) promptView.findViewById(R.id.etBasePoint);*/

                                        lvSearchResult = (ListView) promptView.findViewById(R.id.lvItemSearchResult);
                                        etQtySearch = (EditText) promptView.findViewById(R.id.etQty);
                                        Helper.textWatcher(etQtySearch, getActivity());
//                                        etSearchOnPopUp = (EditText) promptView.findViewById(R.id.etItemName);
                                        etDiscount = (EditText) promptView.findViewById(R.id.etDiscount);
                                        etPrice = (EditText) promptView.findViewById(R.id.etPrice);
                                        etBonusPoint = (EditText) promptView.findViewById(R.id.etBonusPoint);
                                        etBasedPoint = (EditText) promptView.findViewById(R.id.etBasePoint);
                                        final List<VMItems> contentSearchResult = new ArrayList<VMItems>();
                                        VMItems item = new VMItems(promptView);

                                        JSONArray jsn = jsonObject.getJSONArray("ListData");
                                        for (int n = 0; n < jsn.length(); n++) {
                                            /*JSONObject object = jsn.getJSONObject(n);
                                            String txtProductCategory = object.getString("txtProductCategory");
                                            String txtBrand = object.getString("txtBrand");
                                            String txtGroupProduct = object.getString("txtGroupProduct");
                                            String txtProductBarcode = object.getString("txtProductBarcode");
                                            String txtProductCode = object.getString("txtProductCode");
                                            String txtProductDesc = object.getString("txtProductDesc");

                                            item = new VMItems(promptView);
                                            item.setGuiid(new Helper().GenerateGuid());
                                            item.setItemName(txtProductDesc);
//                                            item.setPrice(80000);
                                            item.setItemGroup(txtGroupProduct);
                                            item.setBarcode(txtProductBarcode);
                                            item.setItemCode(txtProductCode);
                                            item.setDesc(txtProductDesc);
                                            item.setItemBrand(txtBrand);

                                            contentSearchResult.add(item);*/
                                            JSONObject object = jsn.getJSONObject(n);
                                            String txtProductCategory = object.getString("txtProductCategory");
                                            String txtBrand = object.getString("txtBrand");
                                            String txtGroupProduct = object.getString("txtGroupProduct");
                                            String txtProductBarcode = object.getString("txtProductBarcode");
                                            String txtProductCode = object.getString("txtProductCode");
                                            HMtxtProductCategory.put(txtProductCode, txtProductCategory);
                                            String txtProductDesc = object.getString("txtProductDesc");
//                                    int intCampagn = object.getInt("intCampaign");
//                                    double dbltax = object.getDouble("decTax");


                                            item = new VMItems(promptView);
                                            item.setItemName(txtProductDesc);
                                            item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                                            item.setItemGroup(txtGroupProduct);
                                            item.setBarcode(txtProductBarcode);
                                            item.setItemCode(txtProductCode);
                                            item.setDesc(txtProductDesc);
                                            item.setItemBrand(txtBrand);
//                                    item.setIntCampagn(intCampagn);


                                            contentSearchResult.add(item);

                                        }
                                        lvSearchResult.setAdapter(new CardAdapterSearchResult(context, contentSearchResult, Color.WHITE));
                                        final VMItems itemSelected = null;
                                        lvSearchResult.setSelection(0);
                                        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                getDetailItem(contentSearchResult, position);
                                            }
                                        });
                                        AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(getActivity());
                                        alertDialogBuilderAttch.setView(promptView);
//        alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                                        alertDialogBuilderAttch
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                })
                                                .setCancelable(false)
                                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
//                                                        cbAttach.setChecked(false);
//                                                        lvSearchResult.setSelected(false);
                                                    }
                                                });
                                        final AlertDialog alertDPopUpSearchBarcode = alertDialogBuilderAttch.create();
                                        if (lvSearchResult.getCount() == 0) {
                                            ToastCustom.showToasty(context, "Barcode Item Not Found", 2);
                                        } else {
                                            getDetailItem(contentSearchResult, 0);
                                            alertDPopUpSearchBarcode.show();
                                            alertDPopUpSearchBarcode.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    getActivity().onUserInteraction();
                                                    String stQtySearch = etQtySearch.getText().toString();
                                                    if (!stQtySearch.equals("") && Integer.parseInt(stQtySearch) < 1) {
                                                        ToastCustom.showToasty(context, "Invalid Qty", 2);
                                                    } else {
//                                                        if (lvSearchResult.getCheckedItemPosition() > -1) {
                                                        if (etQtySearch.getText().toString().equals("")) {
                                                            ToastCustom.showToasty(context, "Quantity Empty", 3);
                                                            etQtySearch.setBackgroundResource(R.drawable.bg_edtext_error_border);
                                                        } else {

//                                                                Object checkedItem = lvSearchResult.getAdapter().getItem(lvSearchResult.getCheckedItemPosition());
//                                                                VMItems itemResult = new VMItems(promptView);
                                                            int positionResult = lvSearchResult.getCheckedItemPosition();
                                                            String itemNameAdd = contentSearchResult.get(0).getItemName();
                                                            String itemCodeAdd = contentSearchResult.get(0).getItemCode();
                                                            String itemBrand = contentSearchResult.get(0).getItemBrand();
                                                            String itemDesc = contentSearchResult.get(0).getDesc();
                                                            double itemTax = decTaxGlobal;
                                                            int intCampagn = intCampagnGlobal;
                                                            int intbitPaket = contentSearchResult.get(0).getIntPaket();
                                                            ;
                                                            int intBitCampaignPaket = contentSearchResult.get(0).getIntCampagn();
                                                            ;
                                                            String txtPrice = etPrice.getText().toString();
                                                            double itemPriceSearch = 0;
                                                            if (!txtPrice.equals("0")) {
                                                                itemPriceSearch = Double.parseDouble(txtPrice);
                                                            }
                                                            String txtDisc = etDiscount.getText().toString();
                                                            double itemDiscount = 0;
                                                            if (!txtDisc.equals("0")) {
                                                                itemDiscount = Double.parseDouble(txtDisc);
                                                            }
                                                            String itemQtyAdd = etQtySearch.getText().toString();

                                                            int itemQty = Integer.parseInt(itemQtyAdd);

                                                            Double itemTotalPrice = (itemPriceSearch * itemQty) - itemDiscount;


//                                                Double itemTaxAmount = itemTotalPrice * 0.1;
                                                            Double itemTaxAmount = itemTotalPrice * (itemTax / 100);

//                                                            Double itemNetPrice = itemTotalPrice + itemTaxAmount;
                                                            Double b = itemTotalPrice + itemTaxAmount;
                                                            ;
                                                            DecimalFormat twoDForm = new DecimalFormat("#");
                                                            Double a = Double.valueOf(twoDForm.format(b));
                                                            Double itemNetPrice = a;
                                                            String itemBasePoint1 = etBasedPoint.getText().toString();
                                                            int intItemBasePoint = Integer.parseInt(itemBasePoint1);
                                                            String itemBonusPoint1 = etBonusPoint.getText().toString();
                                                            double intBonusPoint = Double.parseDouble(itemBonusPoint1);

                                                            int totalBasePoinProduct = itemQty * intItemBasePoint;
                                                            double totalBonusPointProduct = itemQty * intBonusPoint;

                                                            VMItems item = new VMItems(promptView);

                                                            if (intPaketGlobal == 1) {
                                                                String intQty = String.valueOf(itemQtyAdd);
                                                                String txtKontakID = dataLogin.getTxtKontakID();
                                                                String txtNoLangganan = "";
                                                                String intPeriodeLangganan = "0";
                                                                final String strLinkAPIPaketItem = new clsHardCode().linkGetPrice;
                                                                final JSONObject resJsonPaketItem = new JSONObject();
                                                                try {
                                                                    resJsonPaketItem.put("txtGroupUser", dataLogin.getGrpUser());
                                                                    resJsonPaketItem.put("txtItemCode", itemCodeAdd);
                                                                    resJsonPaketItem.put("intQty", intQty.toString());
                                                                    resJsonPaketItem.put("txtKontakID", txtKontakID);
                                                                    resJsonPaketItem.put("txtNoLangganan", txtNoLangganan);
                                                                    resJsonPaketItem.put("intPeriodeLangganan", intPeriodeLangganan);
                                                                    resJsonPaketItem.put("txtCabang", "KALCARE");
                                                                    resJsonPaketItem.put("intQty", itemQty);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                final String mRequestBodyPaketItem = resJsonPaketItem.toString();
                                                                new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPIPaketItem, mRequestBodyPaketItem, access_token, "Please Wait...", new VolleyResponseListener() {
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
                                                                                        for (int n = 0; n < jsn.length(); n++) {
//                                                                                            JSONObject object = jsn.getJSONObject(n);
//                                                                                            String txtDiscount = object.getString("decDiscount");
                                                                                            JSONArray arrListOfPoint = jsn.getJSONObject(n).getJSONArray("ListOfPoint");
                                                                                            JSONObject objHeader = jsn.getJSONObject(n).getJSONObject("dtclsProductKHDData");
                                                                                            int intQtyPaket = objHeader.getInt("intQty");
                                                                                            for (int m = 0; m < arrListOfPoint.length(); m++) {
                                                                                                JSONObject obj = arrListOfPoint.getJSONObject(m);
                                                                                                double decCalcTotalPrice = obj.getDouble("decCalcTotalPrice");
                                                                                                double decCalcTaxAmount = obj.getDouble("decCalcTaxAmount");
                                                                                                double decCalcNetPrice = obj.getDouble("decCalcNetPrice");
                                                                                                double decCalcTotalBasePoint = obj.getDouble("decCalcTotalBasePoint");
                                                                                                double decCalcTotal = obj.getDouble("decCalcTotal");
                                                                                                String txtNewId = obj.getString("txtNewId");
                                                                                                String txtNoSO = obj.getString("txtNoSO");
                                                                                                String txtProductCode = obj.getString("txtProductCode");
                                                                                                String txtProductName = obj.getString("txtProductName");
                                                                                                String txtBrand = obj.getString("txtBrand");
                                                                                                String txtGroupProduct = obj.getString("txtGroupProduct");
                                                                                                String txtProductBarcode = obj.getString("txtProductBarcode");
                                                                                                String txtGroupItem = obj.getString("txtGroupItem");
                                                                                                String txtItemPacketID = obj.getString("txtItemPacketID");
                                                                                                String txtProductCategory = obj.getString("txtProductCategory");
                                                                                                int intItemID = obj.getInt("intItemID");
                                                                                                int intQty = obj.getInt("intQty");
                                                                                                double decPrice = obj.getInt("decPrice");
                                                                                                double decDiscount = obj.getInt("decDiscount");
                                                                                                double decBasePoint = obj.getInt("decBasePoint");
                                                                                                String decTotalBasePoint = obj.getString("decTotalBasePoint");
                                                                                                double decBonusPoint = obj.getInt("decBonusPoint");
                                                                                                int intBitPaket = obj.getInt("intBitPaket");
                                                                                                int bitActive = obj.getInt("bitActive");
                                                                                                String txtKN = obj.getString("txtKN");
                                                                                                String txtGroupKN = obj.getString("txtGroupKN");
                                                                                                VMItems item = new VMItems(promptView);
                                                                                                item.setItemName(txtProductName);
                                                                                                item.setGuiid(new Helper().GenerateGuid());
                                                                                                item.setItemCode(txtProductCode);
                                                                                                item.setPrice(decPrice);
                                                                                                item.setTxtItemPacketID(txtItemPacketID);
                                                                                                item.setItemBrand(txtBrand);
                                                                                                item.setItemGroup(txtGroupItem);
                                                                                                item.setTxtGroupProduct(txtGroupProduct);
                                                                                                item.setProductCategory(txtProductCategory);
                                                                                                item.setBasePoint(String.valueOf(decBasePoint));
                                                                                                item.setQty(intQty);
                                                                                                item.setBarcode(txtProductBarcode);
                                                                                                item.setDiscount(decDiscount);
                                                                                                item.setTotalPrice(decCalcTotalPrice);
                                                                                                item.setDecCalcTotalPrice(decCalcTotalPrice);
                                                                                                item.setTaxAmount(decCalcTaxAmount);
                                                                                                item.setDecCalcTaxAmount(decCalcTaxAmount);
                                                                                                item.setNetPrice(decCalcNetPrice);
                                                                                                item.setDesc(txtItemPacketID);
                                                                                                item.setDecCalcNetPrice(decCalcNetPrice);
                                                                                                item.setBonusPoint(String.valueOf(decBonusPoint));
                                                                                                item.setIntPaket(intBitPaket);
                                                                                                item.setQtyPaket(intQtyPaket);
                                                                                                item.setDectax(decTaxGlobal);
                                                                                                item.setTxtGroupKN(txtGroupKN);
                                                                                                item.setTxtKN(txtKN);
//                                                                                                item.setIntCampagn(intCampagn);

                                                                                                boolean booladded = addItem(item);
                                                                                                if (booladded) {
                                                                                                    alertDPopUpSearchBarcode.dismiss();
                                                                                                } else {
                                                                                                    ToastCustom.showToasty(context, "error system", 2);
                                                                                                }
                                                                                            }
                                                                                        }
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
                                                                item.setItemName(itemNameAdd);
                                                                item.setDesc(itemNameAdd);
                                                                item.setGuiid(new Helper().GenerateGuid());
                                                                item.setItemCode(itemCodeAdd);
                                                                item.setPrice(itemPriceSearch);
                                                                item.setItemBrand(itemBrand);
                                                                item.setProductCategory(HMtxtProductCategory.get(itemCodeAdd));
                                                                item.setBasePoint(Integer.toString(totalBasePoinProduct));
                                                                item.setQty(itemQty);
                                                                item.setTxtItemPacketID(itemNameAdd);
                                                                item.setDiscount(itemDiscount);
                                                                item.setTotalPrice(itemTotalPrice);
                                                                item.setTxtItemPacketID(itemDesc);
                                                                item.setTaxAmount(itemTaxAmount);
                                                                item.setNetPrice(itemNetPrice);
                                                                item.setBonusPoint(String.valueOf(totalBonusPointProduct));
                                                                item.setDectax(decTaxGlobal);
                                                                item.setTxtGroupKN(txtGroupKNGlobal);
                                                                item.setBarcode(txtBarcodeGlobal);
                                                                item.setTxtGroupKN(txtGroupKNGlobal);
                                                                item.setTxtGroupProduct(txtGroupProductGlobal);
                                                                item.setItemGroup(txtGroupItemGlobal);
                                                                item.setTxtKN(txtKNGlobal);
                                                                if (intbitPaket == 1) {
                                                                    item.setIntCampagn(intBitCampaignPaket);
                                                                } else {
                                                                    item.setIntCampagn(intCampagn);
                                                                }
                                                                boolean boolMatch = addItem(item);
                                                            }
                                                            lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, contentLibs, Color.WHITE));
//                                                            alertDi.dismiss();
                                                            alertDPopUpSearchBarcode.dismiss();
                                                        }

                                                        /*} else {
                                                            ToastCustom.showToasty(context, "Pick the item first", 4);
                                                        }*/
                                                    }
                                                }
                                            });
                                        }


                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String a = "";
                        }
                    }
                });

            }
            final String result = scanResult.getContents();
            Log.d("Data info", "Scanning Success result = " + result);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnNext)
    public void onBtnNextClicked() {
        getPeriodeActive();
        getActivity().onUserInteraction();
    }

    @OnClick(R.id.imageScanner)
    public void onImageScannerClicked() {
        IntentIntegrator.initiateScan(getActivity(), zxingLibConfig);
        getActivity().onUserInteraction();
    }

    @OnClick(R.id.btnSaveDraft)
    public void onBtnSaveDraftClicked() {
        saveBtnSaved();
    }

    public void saveBtnSaved() {
        getActivity().onUserInteraction();
        boolean saveDraftResult = false;
        try {
            final clsDraft draft = new clsDraftRepo(context).findByBitActive();
            if (draft != null) {
                final String strLinkAPI = new clsHardCode().linkSaveDataSalesOrder;
                final JSONObject resJson = new JSONObject();
                JSONArray jsonProduct = new Helper().writeJSONSaveData(context, draft, contentLibs);
                try {
                    resJson.put("txtNewId", draft.getGuiId());
                    String strNO = draft.getTxtNoSO();
                    if (strNO.equals("Generated by system")) {
                        resJson.put("txtNoSo", "");
                    } else {
                        resJson.put("txtNoSo", draft.getTxtNoSO());
                    }


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateandTime = sdf.format(new Date());
                    resJson.put("dtDate", currentDateandTime);
                    String txtDeliverSche = "";
                    String deliverBy = "";
                    if (draft.isBoolAttachCustomer()) {
                        deliverBy = "1";
                    } else {
                        deliverBy = "0";
                    }
                    if (draft.getDtDeliverySche() != null) {
                        txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                    }

//                    resJson.put("txtAgentName", draft.getTxtAgentName());
//                    resJson.put("txtPickUpLocationName", "");
                    resJson.put("txtAgentName", dataLogin.getNmUser());
                    resJson.put("txtSourceOrder", data.getTxtSumberDataID());
                    resJson.put("txtSourceOrderName", draft.getTxtSoSource());

                    String walkin = "";
                    if (draft.isBoolPassBy()) {
                        walkin = "1";
                        clsCustomerData dataDefault = new clsCustomerData();
                        try {
                            dataDefault = new clsCustomerDataRepo(context).findOne();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        resJson.put("dtDelivery", txtDeliverSche);
                        resJson.put("intWalkIn", walkin);
                        resJson.put("intDeliveryBy", "0");
                        resJson.put("txtDeliveryBy", dataLogin.getTxtNamaInstitusi());

                        resJson.put("txtPickUpLocation", HMOutletCode.get(draft.getTxtOrderLocation()));
                        resJson.put("txtPickUpLocationName", draft.getTxtOrderLocation());

                       /*
                        resJson.put("txtCustomer", dataDefault.getTxtKontakID());
                        resJson.put("txtCustomerName", dataDefault.getTxtNama());
                        resJson.put("txtKelurahanID", dataDefault.getTxtNamaKelurahan());
                        resJson.put("txtKelurahanName", dataDefault.getTxtNamaKelurahan());
                        resJson.put("txtPropinsiID", dataDefault.getTxtPropinsiID());
                        resJson.put("txtPropinsiName", dataDefault.getTxtNamaPropinsi());
                        resJson.put("txtKabKotaID", dataDefault.getTxtKabKotaID());
                        resJson.put("txtKabupatenKotaName", dataDefault.getTxtNamaKabKota());
                        resJson.put("txtKecamatanID", dataDefault.getTxtKecamatan());
                        resJson.put("txtKecamatanName", dataDefault.getTxtKecamatan());
                        resJson.put("txtKodePos", dataDefault.getTxtKodePos());
                        resJson.put("txtDelivery", dataDefault.getTxtAlamat());*/

                        resJson.put("txtCustomer", null);
                        resJson.put("txtCustomerName", null);
                        resJson.put("txtKelurahanID", null);
                        resJson.put("txtKelurahanName", null);
                        resJson.put("txtPropinsiID", null);
                        resJson.put("txtPropinsiName", null);
                        resJson.put("txtKabKotaID", null);
                        resJson.put("txtKabupatenKotaName", null);
                        resJson.put("txtKecamatanID", null);
                        resJson.put("txtKecamatanName", null);
                        resJson.put("txtKodePos", null);
                        resJson.put("txtDelivery", null);

                        resJson.put("txtRemarks", draft.getTxtRemarks());
                        resJson.put("txtDeviceId", deviceInfo.getModel());
                        resJson.put("txtUser", dataLogin.getNmUser());
                        resJson.put("txtStatusDoc", "0");
                        resJson.put("Detail", jsonProduct);
                    } else {
                        walkin = "0";
                        resJson.put("dtDelivery", txtDeliverSche);
                        resJson.put("intWalkIn", walkin);
                        resJson.put("intDeliveryBy", "1");
                        resJson.put("txtDeliveryBy", dataLogin.getTxtNamaInstitusi());

                        resJson.put("txtPickUpLocation", HMOutletCode.get(draft.getTxtOrderLocation()));
                        resJson.put("txtPickUpLocationName", draft.getTxtOrderLocation());

                        resJson.put("txtKelurahanID", draft.getTxtKelurahanID());
                        resJson.put("txtKelurahanName", draft.getTxtKelurahan());

                        resJson.put("txtCustomer", draft.getTxtContactID());
                        resJson.put("txtCustomerName", draft.getTxtCustomerName());
                        resJson.put("txtPickUpLocation", draft.getTxtProvince());
                        resJson.put("txtPropinsiID", String.valueOf(draft.getTxtProvinceID()));
                        resJson.put("txtPropinsiName", draft.getTxtProvince());
                        resJson.put("txtKabKotaID", String.valueOf(draft.getTxtKabKotID()));
                        resJson.put("txtKabupatenKotaName", draft.getTxtKabKot());
                        resJson.put("txtKecamatanID", String.valueOf(draft.getTxtKecamatanID()));
                        resJson.put("txtKecamatanName", draft.getTxtKecamatan());
                        resJson.put("txtKodePos", draft.getTxtPostCode());
                        resJson.put("txtDelivery", draft.getTxtAddress());
                        resJson.put("txtRemarks", draft.getTxtRemarks());
                        resJson.put("txtDeviceId", deviceInfo.getModel());
                        resJson.put("txtUser", dataLogin.getNmUser());
                        resJson.put("txtStatusDoc", "0");
                        resJson.put("Detail", jsonProduct);
                    }

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
                            getActivity().onUserInteraction();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("intResult");
                                String warn = jsonObject.getString("txtMessage");
                                if (result == 1) {
                                    if (!jsonObject.getString("ListData").equals("null")) {
                                        JSONArray jsn = jsonObject.getJSONArray("ListData");
                                        for (int n = 0; n < jsn.length(); n++) {
                                            JSONObject object = jsn.getJSONObject(n);
                                            String txtNoSO = object.getString("txtNoSO");
                                            String intStatus = object.getString("intStatus");
                                            String txtStatus = object.getString("txtStatus");
                                            draft.setTxtNoSO(txtNoSO);
                                            draft.setIntStatus(0);
                                            draft.setTxtSOStatus(txtStatus);
                                            int resultan = new clsDraftRepo(context).update(draft);
                                            if (resultan > -1) {
                                                Log.d("Update", "Berhasil");
                                                ToastCustom.showToasty(context, "Save done", 1);
                                            } else {
                                                ToastCustom.showToasty(context, "failed to save", 2);
                                            }
                                        }
                                    }
                                } else {
                                    ToastCustom.showToasty(context, warn, 2);

                                }
                            } catch (JSONException ex) {
                                String x = ex.getMessage();
                            }

                            String a = "";
                        }
                    }
                });
            }
        } catch (SQLException e) {
            saveDraftResult = false;
            e.printStackTrace();
            ToastCustom.showToasty(context, "Failed" + e.getMessage(), 2);
        }
        if (saveDraftResult) {
            ToastCustom.showToasty(context, "Berhasil menyimpan draft", 1);
        }
    }

    ;

    @OnClick(R.id.btnAdd)
    public void onBtnAddClicked() {


    }

    private void spinnerKecamatan(int position) {
        if (categoriesKecamatan.size() > 0) {
            String KecName = categoriesKecamatan.get(position).toString();
            String kecID = HMKecID.get(KecName);
            if (!kecID.equals("0")) {
                final JSONObject resJson = new JSONObject();
                try {
                    resJson.put("txtKecamatanID", KecName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String strLinkSpnKel = new clsHardCode().linkGetListKelurahan;

                final String mRequestBody = resJson.toString();
//                        lt.setText("Retrieving data Kelurahan...");
                //volley untuk ambil kelurahan dan kode pos
                new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkSpnKel, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
                    @Override
                    public void onError(String response) {
                        ToastCustom.showToasty(context, response, 2);
//                                lt.hide();
                    }

                    @Override
                    public void onResponse(String response, Boolean status, String strErrorMsg) {
                        if (response != null) {
                            getActivity().onUserInteraction();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("intResult");
                                String warn = jsonObject.getString("txtMessage");
                                if (result == 1) {

                                    if (!jsonObject.getString("ListData").equals("null")) {
                                        JSONArray jsn = jsonObject.getJSONArray("ListData");
//                                        categoriesKelurahan = new ArrayList<String>();
//                                        categoriesKelurahan.add(STRSPINNEROPT);
//                                        HMKel.put(STRSPINNEROPT, "0");

//                                        categoriesPostCode = new ArrayList<String>();
//                                        categoriesPostCode.add(STRSPINNEROPT);
//                                        HMKodePos.put(STRSPINNEROPT, "0");
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
                                            HMGetKodePosFromKeluarahan.put(txtNamaKelurahan, txtKodePosID + "-" + txtNamaKelurahan);
                                        }
                                        SpinnerCustom.setAdapterSpinner(spnKelurahanAddOrder, context, R.layout.custom_spinner, categoriesKelurahan);
                                        SpinnerCustom.setAdapterSpinner(spnPostCodeAddOrder, context, R.layout.custom_spinner, categoriesPostCode);
                                        spnKelurahanAddOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                String kelurahan = spnKelurahanAddOrder.getSelectedItem().toString();
                                                if (HMGetKodePosFromKeluarahan != null) {
                                                    if (HMGetKodePosFromKeluarahan.get(kelurahan) != null) {
                                                        String kodepos = HMGetKodePosFromKeluarahan.get(kelurahan).toString();
                                                        SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, kodepos);
                                                        spnPostCodeAddOrder.setEnabled(false);
                                                    }
                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        if (!boolAttachCustomer) {
                                            if (draft != null) {
                                                SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, draft.getTxtKelurahan());
                                                SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, draft.getTxtPostCode() + "-" + draft.getTxtKelurahan());
                                                spnPostCodeAddOrder.setEnabled(false);
                                            } else {
                                                SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, STRSPINNEROPT);
                                                SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);
                                            }
                                        } else {
                                            SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, kelurahanCustomer);
                                            SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, postCodeCustomer);
                                            spnPostCodeAddOrder.setEnabled(false);
                                            boolAttachCustomer = false;
                                        }


//                                                lt.success();
                                    }
                                }
                            } catch (JSONException e) {

                            }

                        }
                    }
                });
            } else {
                SpinnerCustom.selectedItemByText(context, spnKelurahanAddOrder, categoriesKelurahan, STRSPINNEROPT);
                SpinnerCustom.selectedItemByText(context, spnPostCodeAddOrder, categoriesPostCode, STRSPINNEROPT);
            }
        }
    }

    private void reset() {
        etItemName.setText("");
        etItemCode.setText("");
        tvItemPrice.setText("");
        etQty.setText("");

        contentLibs2 = new ArrayList<>();
        lvContentItemSearch.setAdapter(new CardAppAdapter2(context, contentLibs2, Color.WHITE));
        ListViewCustom.setListViewHeightBasedOnItems(getActivity(), lvContentItemSearch);
    }

    @OnClick(R.id.btnBack)
    public void onBtnBackClicked() {
        getActivity().onUserInteraction();
        linearLayoutTop.setVisibility(View.VISIBLE);
        linearLayoutBottom.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnPreview)
    public void onBtnPreviewClicked() {
        getActivity().onUserInteraction();
        boolean valid = true;
        if (contentLibs.size() > 0) {
            valid = true;
        } else {
            valid = false;
        }
        if (valid) {

            strLinkAPICheckout = new clsHardCode().linkCekValidasiDispatchPartner;
            final JSONObject resJson = new JSONObject();
            try {
                draft = new clsDraftRepo(context).findByBitActive();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONArray jsonProduct = new Helper().writeJSONSaveData(context, draft, contentLibs);
            boolean boolSavedDispatch = true;
            try {
                String strNO = draft.getTxtNoSO();

                if (strNO.equals("Generated by system")) {
                    boolSavedDispatch = false;
                    resJson.put("txtOrderNo", "");
                } else {
                    resJson.put("txtOrderNo", draft.getTxtNoSO());
                }
                String txtKontakID = dataLogin.getTxtKontakID();
                String txtTeleId = dataLogin.getTxtTeleID();
                resJson.put("txtTeleId", txtTeleId);

                String soDate = etDate.getText().toString();
                String dateSO = "";
                if (!soDate.equals("")) {
                    DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                    DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");
//                    Calendar cal = Calendar.getInstance();
//                    String now = dateFormat.format(cal.getTime()).toString();
                    Date newDate = null;
                    try {
                        newDate = dateFormat.parse(soDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newDate = dataLogin.getDtLogin();
                    dateSO = dateFormatStr.format(newDate);
                }
                resJson.put("dtKirim", dateSO);

                if (draft.isBoolPassBy() || draft.isBoolWalkinCustomer()) {
                    clsCustomerData dataDefault = new clsCustomerData();
                    try {
                        dataDefault = new clsCustomerDataRepo(context).findOne();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    resJson.put("txtNamaPropinsi", "");
                    resJson.put("txtNamaKabKota", "");
                    resJson.put("txtNamaKecamatan", "");
                    resJson.put("txtNamaKelurahan", "");
                    resJson.put("txtRemarkSO", draft.getTxtRemarks());
                    resJson.put("txtPropinsiID", "");
                    resJson.put("txtKabKotaID", "");
                    resJson.put("txtKecamatanID", "");
                    resJson.put("txtKodePos", "");

                    resJson.put("txtKontakID", txtKontakID);
                    resJson.put("txtCustName", "");
                    resJson.put("txtCustPhone", "");
                    resJson.put("txtAlamatKirim", "");

                } else {
                    resJson.put("txtNamaPropinsi", draft.getTxtProvince());
                    resJson.put("txtNamaKabKota", draft.getTxtKabKot());
                    resJson.put("txtNamaKecamatan", draft.getTxtKecamatan());
                    resJson.put("txtNamaKelurahan", draft.getTxtKelurahan());
                    resJson.put("txtRemarkSO", draft.getTxtRemarks());
                    resJson.put("txtPropinsiID", draft.getTxtProvinceID());
                    resJson.put("txtKabKotaID", draft.getTxtKabKotID());
                    resJson.put("txtKecamatanID", draft.getTxtKecamatanID());
                    resJson.put("txtKodePos", draft.getTxtPostCode());

                    resJson.put("txtKontakID", txtKontakID);
                    resJson.put("txtCustName", draft.getTxtCustomerName());
                    resJson.put("txtCustPhone", draft.getTxtPhoneNumber());
                    resJson.put("txtAlamatKirim", draft.getTxtAddress());
                }


                resJson.put("intNilaiPembulatan", 0);
                resJson.put("txtPaymentMethodID", "");
                resJson.put("txtMediaJasaID", "");
                resJson.put("txtMediaJasaPaymentID", "");
                resJson.put("txtCardNumber", "");
                resJson.put("txtBCATraceNo", "");
                resJson.put("txtUserID", dataLogin.getIdUser());
                resJson.put("txtTeleID", dataLogin.getTxtTeleID());
                resJson.put("txtCodeID", dataLogin.getTxtCodeId());
                resJson.put("Detail", jsonProduct);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mRequestBodyCheckout = resJson.toString();
            saveDataPreview();

        } else {
            ToastCustom.showToasty(context, "List empty !", 4);
        }
    }
    //save()
    public void saveDataPreview() {
        getActivity().onUserInteraction();
        boolean saveDraftResult = false;
        try {
            final clsDraft draft = new clsDraftRepo(context).findByBitActive();
            if (draft != null) {
                final String strLinkAPIi = new clsHardCode().linkSaveDataSalesOrder;
                final JSONObject resJsoni = new JSONObject();
                JSONArray jsonProducti = new Helper().writeJSONSaveData(context, draft, contentLibs);
                try {
                    resJsoni.put("txtNewId", draft.getGuiId());
                    String strNO = draft.getTxtNoSO();
                    if (strNO.equals("Generated by system")) {
                        resJsoni.put("txtNoSo", "");
                    } else {
                        resJsoni.put("txtNoSo", draft.getTxtNoSO());
                    }
                    String txtTeleId = dataLogin.getTxtTeleID();
                    resJsoni.put("txtTeleId", txtTeleId);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateandTime = sdf.format(new Date());
                    resJsoni.put("dtDate", currentDateandTime);
                    String txtDeliverSche = "";
                    String deliverBy = "";
                    String walkinCustomer = "";
                    String passBy = "";
                    if (draft.isBoolAttachCustomer()) {
                        deliverBy = "1";
                        walkinCustomer = "0";
                    } else if (draft.isBoolWalkinCustomer()) {
                        deliverBy = "0";
                        walkinCustomer = "1";
                    } else {
                        deliverBy = "0";
                        walkinCustomer = "0";
                    }
                    if (draft.getDtDeliverySche() != null) {
                        txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                    }
                    resJsoni.put("txtPickUpLocationName", draft.getTxtOrderLocation());
                    resJsoni.put("txtPickUpLocation", HMOutletCode.get(draft.getTxtOrderLocation()));
                    resJsoni.put("txtAgentName", dataLogin.getNmUser());
                    resJsoni.put("txtSourceOrder", data.getTxtSumberDataID());
                    resJsoni.put("txtSourceOrderName", draft.getTxtSoSource());


                    if (draft.isBoolPassBy()) {
                        passBy = "1";
                        clsCustomerData dataDefault = new clsCustomerData();
                        try {
                            dataDefault = new clsCustomerDataRepo(context).findOne();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        resJsoni.put("dtDelivery", txtDeliverSche);
                        resJsoni.put("intPassBy", passBy);
                        resJsoni.put("intDeliveryBy", deliverBy);
                        resJsoni.put("intWalkinCustomer", walkinCustomer);

                        resJsoni.put("txtDeliveryBy", dataLogin.getTxtNamaInstitusi());

                        resJsoni.put("txtCustomer", "");
                        resJsoni.put("txtCustomerName", "");

                        resJsoni.put("txtKelurahanID", "");
                        resJsoni.put("txtKelurahanName", "");

                        resJsoni.put("txtPropinsiID", "");
                        resJsoni.put("txtPropinsiName", "");
                        resJsoni.put("txtKabKotaID", "");
                        resJsoni.put("txtKabupatenKotaName", "");
                        resJsoni.put("txtKecamatanID", "");
                        resJsoni.put("txtKecamatanName", "");
                        resJsoni.put("txtKodePos", "");
                        resJsoni.put("txtDelivery", "");


                        resJsoni.put("txtRemarks", draft.getTxtRemarks());
                        resJsoni.put("txtDeviceId", deviceInfo.getModel());
                        resJsoni.put("txtUser", dataLogin.getIdUser());
                        resJsoni.put("txtAgentName", dataLogin.getNmUser());
                        resJsoni.put("txtStatusDoc", "0");
                        resJsoni.put("Detail", jsonProducti);
                    } else if (draft.isBoolWalkinCustomer()) {
                        passBy = "0";
                        resJsoni.put("dtDelivery", txtDeliverSche);
                        resJsoni.put("intPassBy", passBy);
                        resJsoni.put("intDeliveryBy", deliverBy);
                        resJsoni.put("intWalkinCustomer", walkinCustomer);

                        resJsoni.put("txtDeliveryBy", dataLogin.getTxtNamaInstitusi());

                        resJsoni.put("txtCustomer", draft.getTxtContactID());
                        resJsoni.put("txtCustomerName", draft.getTxtCustomerName());

                        resJsoni.put("txtKelurahanID", "");
                        resJsoni.put("txtKelurahanName", "");

                        resJsoni.put("txtPropinsiID", "");
                        resJsoni.put("txtPropinsiName", "");
                        resJsoni.put("txtKabKotaID", "");
                        resJsoni.put("txtKabupatenKotaName", "");
                        resJsoni.put("txtKecamatanID", "");
                        resJsoni.put("txtKecamatanName", "");
                        resJsoni.put("txtKodePos", "");
                        resJsoni.put("txtDelivery", draft.getTxtAddress());


                        resJsoni.put("txtRemarks", draft.getTxtRemarks());
                        resJsoni.put("txtDeviceId", deviceInfo.getModel());
                        resJsoni.put("txtUser", dataLogin.getIdUser());
                        resJsoni.put("txtAgentName", dataLogin.getNmUser());
                        resJsoni.put("txtStatusDoc", "0");
                        resJsoni.put("Detail", jsonProducti);
                    } else {
                        passBy = "0";
                        resJsoni.put("dtDelivery", txtDeliverSche);
                        resJsoni.put("intPassBy", passBy);
                        resJsoni.put("intWalkinCustomer", walkinCustomer);
                        resJsoni.put("intDeliveryBy", deliverBy);
                        resJsoni.put("txtDeliveryBy", dataLogin.getTxtNamaInstitusi());

                        resJsoni.put("txtKelurahanID", draft.getTxtKelurahanID());
                        resJsoni.put("txtKelurahanName", draft.getTxtKelurahan());

                        resJsoni.put("txtCustomer", draft.getTxtContactID());
                        resJsoni.put("txtCustomerName", draft.getTxtCustomerName());
                        resJsoni.put("txtPropinsiID", String.valueOf(draft.getTxtProvinceID()));
                        resJsoni.put("txtPropinsiName", draft.getTxtProvince());
                        resJsoni.put("txtKabKotaID", String.valueOf(draft.getTxtKabKotID()));
                        resJsoni.put("txtKabupatenKotaName", draft.getTxtKabKot());
                        resJsoni.put("txtKecamatanID", String.valueOf(draft.getTxtKecamatanID()));
                        resJsoni.put("txtKecamatanName", draft.getTxtKecamatan());
                        resJsoni.put("txtKodePos", draft.getTxtPostCode());
                        resJsoni.put("txtDelivery", draft.getTxtAddress());
                        resJsoni.put("txtRemarks", draft.getTxtRemarks());
                        resJsoni.put("txtDeviceId", deviceInfo.getModel());
                        resJsoni.put("txtUser", dataLogin.getIdUser());
                        resJsoni.put("txtStatusDoc", "0");
                        resJsoni.put("Detail", jsonProducti);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String mRequestBodyi = resJsoni.toString();
                new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPIi, mRequestBodyi, access_token, "Please Wait...", new VolleyResponseListener() {
                    @Override
                    public void onError(String response) {
                        ToastCustom.showToasty(context, response, 2);
                    }

                    @Override
                    public void onResponse(String response, Boolean status, String strErrorMsg) {
                        if (response != null) {
                            getActivity().onUserInteraction();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                int result = jsonObject.getInt("intResult");
                                String warn = jsonObject.getString("txtMessage");
                                if (result == 1) {
                                    if (!jsonObject.getString("ListData").equals("null")) {
                                        JSONArray jsn = jsonObject.getJSONArray("ListData");
                                        JSONObject jsnObjStatusSO = jsn.getJSONObject(0).getJSONObject("StatusSO");
                                        JSONObject jsnObjPaymentSO = jsn.getJSONObject(0).getJSONObject("PaymentSO");
                                        JSONObject jsnObjHeaderSO = jsn.getJSONObject(0).getJSONObject("HeaderSO");
                                        JSONArray jsnDetailSO = jsn.getJSONObject(0).getJSONArray("DetailSO");

                                        //StatusSO
                                        String txtNoSO = jsnObjStatusSO.getString("txtNoSO");
                                        String intStatus = jsnObjStatusSO.getString("intStatus");
                                        String txtStatus = jsnObjStatusSO.getString("txtStatus");

                                        //PaymentSO
                                        dblTaxBasedAmountFinal = jsnObjPaymentSO.getDouble("decAmount");
                                        dbldecTaxAmountFinal = jsnObjPaymentSO.getDouble("decTaxBaseAmount");
                                        dbldecTotalFinal = jsnObjPaymentSO.getDouble("decTotal");
                                        dbldecTotDiscountFinal = jsnObjPaymentSO.getDouble("decTotDiscount");
                                        dbldecTotalPriceFinal = jsnObjPaymentSO.getDouble("decTotalPrice");
                                        dbldecdecRoundedFinal = jsnObjPaymentSO.getDouble("decRounded");
                                        intBitActiveFinal = jsnObjPaymentSO.getInt("bitActive");
                                        dbldecNetPriceFinal = jsnObjPaymentSO.getInt("decNetPrice");
                                        dbldecPaymentFinal = jsnObjPaymentSO.getInt("decPayment");
                                        dbldecTotalBonusPoin = jsnObjPaymentSO.getInt("decTotalBonusPoin");
                                        dbldecTotalPoin = jsnObjPaymentSO.getInt("decTotalPoin");

                                        //HeaderSO
                                        String txtPropinsiID = jsnObjHeaderSO.getString("txtPropinsiID");
                                        String txtPropinsiName = jsnObjHeaderSO.getString("txtPropinsiName");
                                        String txtKabupatenKotaID = jsnObjHeaderSO.getString("txtKabupatenKotaID");
                                        String txtKabupatenKotaName = jsnObjHeaderSO.getString("txtKabupatenKotaName");
                                        String txtKecamatanID = jsnObjHeaderSO.getString("txtKecamatanID");
                                        String txtKecamatanName = jsnObjHeaderSO.getString("txtKecamatanName");
                                        String txtKelurahanID = jsnObjHeaderSO.getString("txtKelurahanID");
                                        String txtKelurahanName = jsnObjHeaderSO.getString("txtKelurahanName");
                                        String txtKodePos = jsnObjHeaderSO.getString("txtKodePos");
                                        String txtCustomer = jsnObjHeaderSO.getString("txtCustomer");
                                        String txtCustomerName = jsnObjHeaderSO.getString("txtCustomerName");
                                        String txtPickUpLocationName = jsnObjHeaderSO.getString("txtPickUpLocationName");
                                        String txtDelivery = jsnObjHeaderSO.getString("txtDelivery");

                                        //Detail buat ambil poin per KN
                                        double poinKN1 = 0;
                                        double poinKN2 = 0;
                                        double poinKN3 = 0;
                                        double poinKN4 = 0;
                                        double poinOther = 0;
                                        for (int n = 0; n < jsnDetailSO.length(); n++) {
                                            JSONObject object = jsnDetailSO.getJSONObject(n);
                                            String txtKN = object.getString("txtKN");
                                            String txtGroupKN = object.getString("txtGroupKN");
                                            double decBasePoint = object.getDouble("decBasePoint");
                                            double decBonusPoint = object.getDouble("decBonusPoint");
                                            int qty = object.getInt("intQty");
                                            double totalBasedPoint = decBasePoint;
                                            switch (txtKN) {
                                                case "KN1":
                                                    poinKN1 = poinKN1 + totalBasedPoint;
                                                    break;
                                                case "KN2":
                                                    poinKN2 = poinKN2 + totalBasedPoint;
                                                    break;
                                                case "KN3":
                                                    poinKN3 = poinKN3 + totalBasedPoint;
                                                    break;
                                                case "KN4":
                                                    poinKN4 = poinKN4 + totalBasedPoint;
                                                    break;
                                                default:
                                                    poinOther = poinOther + totalBasedPoint;
                                            }

                                        }
                                        poinKN1Global = poinKN1;
                                        poinKN2Global = poinKN2;
                                        poinKN3Global = poinKN3;
                                        poinKN4Global = poinKN4;
                                        poinOtherGlobal = poinOther;

                                        txtCustomerFinal = txtCustomer;
                                        txtCustomerNameFinal = txtCustomerName;
                                        txtPickUpLocationNameFinal = txtPickUpLocationName;
                                        txtPropinsiIDFinal = txtPropinsiID;
                                        txtPropinsiNameFinal = txtPropinsiName;
                                        txtKabupatenKotaIDFinal = txtKabupatenKotaID;
                                        txtKabupatenKotaNameFinal = txtKabupatenKotaName;
                                        txtKecamatanIDFinal = txtKecamatanID;
                                        txtKecamatanNameFinal = txtKecamatanName;
                                        txtKelurahanIDFinal = txtKelurahanID;
                                        txtKelurahanNameFinal = txtKelurahanName;
                                        txtKodePosFinal = txtKodePos;
                                        txtNoSoFinal = txtNoSO;
                                        txtDeliveryAddressFinal = txtDelivery;
                                        txtKontakIDFinal = txtCustomer;


                                        resJsoni.put("txtCustomer", txtCustomer);
                                        resJsoni.put("txtCustomerName", txtCustomerName);

                                        resJsoni.put("txtKelurahanID", txtKelurahanID);
                                        resJsoni.put("txtKelurahanName", txtKelurahanName);

                                        resJsoni.put("txtPropinsiID", txtPropinsiID);
                                        resJsoni.put("txtPropinsiName", txtPropinsiName);
                                        resJsoni.put("txtKabKotaID", txtKabupatenKotaID);
                                        resJsoni.put("txtKabupatenKotaName", txtKabupatenKotaName);
                                        resJsoni.put("txtKecamatanID", txtKecamatanID);
                                        resJsoni.put("txtKecamatanName", txtKecamatanName);
                                        resJsoni.put("txtKodePos", txtKodePos);
                                        resJsoni.put("txtDelivery", txtDelivery);

                                        for (int n = 0; n < jsn.length(); n++) {
                                            JSONObject object = jsn.getJSONObject(n);
//                                            String txtNoSO = object.getString("txtNoSO");
//                                            String intStatus = object.getString("intStatus");
//                                            String txtStatus = object.getString("txtStatus");
                                            draft.setTxtNoSO(txtNoSO);
                                            draft.setIntStatus(0);
                                            draft.setTxtSOStatus(txtStatus);
                                            int resultan = new clsDraftRepo(context).createOrUpdate(draft);
                                            if (resultan > -1) {
                                                Log.d("Update", "Berhasil");
                                                ToastCustom.showToasty(context, "Save done", 1);
//                                                btnPreview.performClick();
                                                resJsoni.put("txtNoSo", txtNoSO);
                                                String mRequestBodyiCheckout = resJsoni.toString();
                                                dispatchPartnerPreview(strLinkAPICheckout, mRequestBodyiCheckout);
                                            } else {
                                                ToastCustom.showToasty(context, "failed to save", 2);
                                            }
                                        }
                                    }
                                } else {
                                    ToastCustom.showToasty(context, warn, 2);

                                }
                            } catch (JSONException ex) {
                                String x = ex.getMessage();
                            }

                            String a = "";
                        }
                    }
                });
                /*new clsProductDraftRepo(context).clearAllData();
                for (VMItems item : contentLibs) {
                    String guiid = new Helper().GenerateGuid();
                    clsProductDraft product = new clsProductDraft();
                    product.setTxtDraftGUI(draft.getGuiId());
                    product.setTxtProductDraftGUI(guiid);
                    product.setTxtItemName(item.getItemName());
                    product.setTxtItemCode(item.getItemCode());
                    product.setDblItemDiscount(item.getDiscount());
                    product.setDblPrice(item.getPrice());
                    product.setDblNetPrice(item.getNetPrice());
                    product.setIntQty(item.getQty());
                    product.setDblItemTax(item.getTaxAmount());
                    product.setDblTotalPrice(item.getTotalPrice());
                    new clsProductDraftRepo(context).createOrUpdate(product);
                    saveDraftResult = true;
                }*/
            }
        } catch (SQLException e) {
            saveDraftResult = false;
            e.printStackTrace();
            ToastCustom.showToasty(context, "Failed" + e.getMessage(), 2);
        }
        if (saveDraftResult) {
            ToastCustom.showToasty(context, "Berhasil menyimpan draft", 1);

        }
    }

    public void dispatchPartnerPreview(String strLinkAPI, String mRequestBody) {
        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPI, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                ToastCustom.showToasty(context, response, 2);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    getActivity().onUserInteraction();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        int result = jsonObject.getInt("intResult");
                        String warn = jsonObject.getString("txtMessage");
                        if (result == 1) {
                            if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                contentLibsPreviewItem = new ArrayList<>();
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String txtQTY = object.getString("intQTY");
                                    String decAmount = object.getString("decAmount");
                                    boolean bitAvailable = object.getBoolean("bitAvailable");
                                    String intQtyAvailable = object.getString("intQtyAvailable");
                                    String txtPartnerAddress = object.getString("txtPartnerAddress");
                                    String txtPartnerID = object.getString("txtPartnerID");
                                    String txtPartnerName = object.getString("txtPartnerName");
                                    String txtPartnerPhone = object.getString("txtPartnerPhone");
                                    String txtProductCode = object.getString("txtProductCode");
                                    String txtProductDesc = object.getString("txtProductDesc");
                                    String intPriority = object.getString("intPriority");
                                    String intFlag = object.getString("intFlag");
                                    VMItemsPreview item = new VMItemsPreview();
                                    item.setPartnerAddress(txtPartnerAddress);
                                    item.setPartnerName(txtPartnerName);
                                    item.setProductCode(txtProductCode);
                                    item.setProductName(txtProductDesc);
                                    item.setBitAvailable(bitAvailable);
                                    item.setPartnerPhone(txtPartnerPhone);
                                    item.setQtyAvailable(intQtyAvailable);
                                    item.setQty(txtQTY);
                                    item.setTxtIntFlag(intFlag);

                                    contentLibsPreviewItem.add(item);
                                }
                                LayoutInflater layoutInflater = LayoutInflater.from(context);
                                final View promptView = layoutInflater.inflate(R.layout.popup_preview, null);
                                //            final View promptViewCustomer = layoutInflater.inflate(R.layout.popup_preview, null);
                                ListView lvPreview = (ListView) promptView.findViewById(R.id.lvItemPrev);
                                Button btnCancelPrev = (Button) promptView.findViewById(R.id.btnCancelPrev);
//                                        Button btnCheckoutPrev = (Button) promptView.findViewById(R.id.btnCheckoutPrev);
                                RecyclerView rvPreview = (RecyclerView) promptView.findViewById(R.id.rv_preview);

                                TextView tvSOStatusPrev = (TextView) promptView.findViewById(R.id.tvSOStatusPrev);
                                TextView tvtvSoNumberPrev = (TextView) promptView.findViewById(R.id.tvSoNumberPrev);
                                TextView tvSoDatePrev = (TextView) promptView.findViewById(R.id.tvSoDatePrev);
                                TextView tvSOSourcePrev = (TextView) promptView.findViewById(R.id.tvSOSourcePrev);
                                TextView tvAgentNamePrev = (TextView) promptView.findViewById(R.id.tvAgentNamePrev);
                                TextView tvOrderLocationPrev = (TextView) promptView.findViewById(R.id.tvOrderLocationPrev);
                                TextView tvDeliveryByPrev = (TextView) promptView.findViewById(R.id.tvDeliveryByPrev);
                                TextView tvDeliverySchedulePrev = (TextView) promptView.findViewById(R.id.tvDeliverySchedulePrev);
                                TextView tvRemarksPreview = (TextView) promptView.findViewById(R.id.tvRemarksPreview);
                                final TextView tvPaymentMethod = (TextView) promptView.findViewById(R.id.tvPaymentMethod);

                                final TextView etCustomerNamePrev = (TextView) promptView.findViewById(R.id.etCustomerNamePrev);
                                final TextView etContactIDPrev = (TextView) promptView.findViewById(R.id.etContactIDPrev);
                                final TextView etMemberNoPrev = (TextView) promptView.findViewById(R.id.etMemberNoPrev);
                                final TextView tvPostCodeCustomerPrev = (TextView) promptView.findViewById(R.id.tvPostCodeCustomerPrev);
                                final TextView tvAddressCustomerPrev = (TextView) promptView.findViewById(R.id.tvAddressCustomerPrev);
                                final TextView tvProvinceCustomerPrev = (TextView) promptView.findViewById(R.id.tvProvinceCustomerPrev);
                                final TextView tvCityCustomerPrev = (TextView) promptView.findViewById(R.id.tvCityCustomerPrev);
                                final TextView tvRegionCustomerPrev = (TextView) promptView.findViewById(R.id.tvRegionCustomerPrev);

                                final TextView tvTaxBaseAmount3 = (TextView) promptView.findViewById(R.id.tvTaxBaseAmount3);
                                final TextView tvTaxAmount3 = (TextView) promptView.findViewById(R.id.tvTaxAmount3);
                                final TextView tvTotal = (TextView) promptView.findViewById(R.id.tvTotal);
                                final TextView tvDiscount3 = (TextView) promptView.findViewById(R.id.tvDiscount3);
                                final TextView tvTotalPrice3 = (TextView) promptView.findViewById(R.id.tvTotalPrice3);
                                final TextView tvRounded3 = (TextView) promptView.findViewById(R.id.tvRounded3);
                                final TextView tvPaymentEnd3 = (TextView) promptView.findViewById(R.id.tvPaymentEnd3);

                                Locale localeID = new Locale("in", "ID");
                                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                                formatRupiah.setMaximumFractionDigits(2);
                                formatRupiah.setMinimumFractionDigits(2);
//                                detailHarga.setText(formatRupiah.format((double)hargarumah));

//                                tvTaxBaseAmount3.setText((new DecimalFormat("##.##").format(dblTaxBasedAmountFinal)));
//                                tvTotalPrice3.setText(new DecimalFormat("##.##").format(dbldecTotalPriceFinal));
//                                tvDiscount3.setText(new DecimalFormat("##.##").format(dbldecTotDiscountFinal));
//                                tvRounded3.setText(new DecimalFormat("##.##").format(dbldecdecRoundedFinal));

                                tvTaxBaseAmount3.setText(new Helper().formatRupiah(dblTaxBasedAmountFinal));
                                tvTaxAmount3.setText(new Helper().formatRupiah(dbldecTaxAmountFinal));
                                tvTotal.setText(new Helper().formatRupiah(dbldecTotalFinal));
                                tvTotalPrice3.setText(new Helper().formatRupiah(dbldecTotalPriceFinal));
                                tvDiscount3.setText(new Helper().formatRupiah(dbldecTotDiscountFinal));
                                tvRounded3.setText(new Helper().formatRupiah(dbldecdecRoundedFinal));
                                tvPaymentEnd3.setText(new Helper().formatRupiah(dbldecPaymentFinal));


                                final TextView tvKN1Poin3 = (TextView) promptView.findViewById(R.id.tvKN1Poin3);
                                final TextView tvKN2Poin3 = (TextView) promptView.findViewById(R.id.tvKN2Poin3);
                                final TextView tvKN3poin3 = (TextView) promptView.findViewById(R.id.tvKN3poin3);
                                final TextView tvKN4poin3 = (TextView) promptView.findViewById(R.id.tvKN4poin3);
                                final TextView tvKNOtherPoin3 = (TextView) promptView.findViewById(R.id.tvKNOtherPoin3);
                                final TextView tvBonusPoin3 = (TextView) promptView.findViewById(R.id.tvBonusPoin3);
                                final TextView tvTotalPoin3 = (TextView) promptView.findViewById(R.id.tvTotalPoin3);

                                tvKN1Poin3.setText(String.valueOf(poinKN1Global));
                                tvKN2Poin3.setText(String.valueOf(poinKN2Global));
                                tvKN3poin3.setText(String.valueOf(poinKN3Global));
                                tvKN4poin3.setText(String.valueOf(poinKN4Global));
                                tvKNOtherPoin3.setText(String.valueOf(poinOtherGlobal));
                                tvBonusPoin3.setText(String.valueOf(dbldecTotalBonusPoin));
                                tvTotalPoin3.setText(String.valueOf(dbldecTotalPoin));

                                final Button btnSummarySO = (Button) promptView.findViewById(R.id.btnSummarySO);
                                TextView tvHideCustPrev = (TextView) promptView.findViewById(R.id.tvHideCustPrev);
                                LinearLayout lnContactIdPrev = (LinearLayout) promptView.findViewById(R.id.lnContactIdPrev);
                                LinearLayout lnMemberIdPrev = (LinearLayout) promptView.findViewById(R.id.lnMemberIdPrev);

                                final LinearLayout lnAttacedCust = (LinearLayout) promptView.findViewById(R.id.lnAttacedCust);

                                final LinearLayout linearLayoutPaymentDetail = (LinearLayout) promptView.findViewById(R.id.linearLayoutPaymentDetail);
                                final EditText etSourceMediaPayment = (EditText) promptView.findViewById(R.id.etSourceMediaPayment);
                                etCardNumber = (EditText) promptView.findViewById(R.id.etCardNumber);
                                Helper.textWatcher(etCardNumber, getActivity());
                                etBcaTraceNumber = (EditText) promptView.findViewById(R.id.etBcaTraceNumber);
                                Helper.textWatcher(etBcaTraceNumber, getActivity());
                                etPayment = (EditText) promptView.findViewById(R.id.etPayment);

                                //pelem

                                tvCustomerPrev = (TextView) promptView.findViewById(R.id.tvCustomerPrev);


                                String noSO = txtNoSoFinal;
                                String soStatus = etSoStatus.getText().toString();
                                String soDate = etDate.getText().toString();
                                String soSource = etSOSource.getText().toString();
                                String deliverySche = etDeliverySchedule.getText().toString();
                                String agentName = etAgentName.getText().toString();
                                String orderLocation = etOrderLocation.getText().toString();
                                boolean deliveryPassBy = cbPassBy.isChecked();
                                boolean attchCust = cbAttach.isChecked();
                                boolean walkin = cbWalkInCustomer.isChecked();
                                String remarks = etRemarks.getText().toString();

                                tvSOSourcePrev.setText(soSource);
                                tvSoDatePrev.setText(soDate);
                                tvDeliverySchedulePrev.setText(deliverySche);
                                tvAgentNamePrev.setText(agentName);
                                tvOrderLocationPrev.setText(orderLocation);
                                tvtvSoNumberPrev.setText(noSO);
                                btnSummarySO.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getActivity().onUserInteraction();
                                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                                        View promptViewSummarySo = layoutInflater.inflate(R.layout.popup_summary_so, null);
                                        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getActivity());
                                        alertDialogBuilder2.setView(promptViewSummarySo);
                                        alertDialogBuilder2.setTitle("Summary Sales Order");
                                        alertDialogBuilder2
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {

                                                    }
                                                });
                                        final AlertDialog alertDSummarySO = alertDialogBuilder2.create();
                                        alertDSummarySO.show();
                                    }
                                });
                                tvPaymentMethod.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getActivity().onUserInteraction();
                                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                                        View promptView = layoutInflater.inflate(R.layout.popup_payment_method, null);
                                        final RadioGroup rg = promptView.findViewById(R.id.radioGroup);
                                        final RadioButton rbCash = (RadioButton) promptView.findViewById(R.id.radio_cash);
                                        final RadioButton rbDebt = (RadioButton) promptView.findViewById(R.id.radio_debit);
                                        final Spinner spnPaymentMethod = (Spinner) promptView.findViewById(R.id.spnPaymentMethod);
                                        final Spinner spnPaymentMethodAndTipe = (Spinner) promptView.findViewById(R.id.spnSourceMediaPayment);
                                        final Spinner spnSourceMediaPaymentList = (Spinner) promptView.findViewById(R.id.spnPayment);

                                        final EditText etCardNumber2 = (EditText) promptView.findViewById(R.id.etCardNumber2);
                                        final EditText etBcaTraceNumber2 = (EditText) promptView.findViewById(R.id.etBcaTraceNumber2);
                                        final String txtPaymentMth1 = tvPaymentMethod.getText().toString();
                                        final String txtSourceMediaPayment1 = etSourceMediaPayment.getText().toString();
                                        final String txtCardNumber1 = etCardNumber.getText().toString();
                                        final String txtBcaTraceNumber1 = etBcaTraceNumber.getText().toString();
                                        final String txtPayment1 = etPayment.getText().toString();

                                        if (!txtPaymentMth1.equals("*Add payment method")) {
                                            boolPaymentFilled = true;
                                        } else {
                                            boolPaymentFilled = false;
                                        }

                                        if (boolPaymentFilled) {
                                            etCardNumber2.setText(txtCardNumber1);
                                            etBcaTraceNumber2.setText(txtBcaTraceNumber1);
                                            etBcaTraceNumber2.setEnabled(true);
                                            etBcaTraceNumber2.setFocusable(true);
                                            etBcaTraceNumber2.setFocusableInTouchMode(true);
                                            etBcaTraceNumber2.setClickable(true);
                                            etCardNumber2.setEnabled(true);
                                            etCardNumber2.setFocusable(true);
                                            etCardNumber2.setFocusableInTouchMode(true);
                                            etCardNumber2.setClickable(true);
                                        }
//                                        List<clsListPaymentMethod> mediajasas = new ArrayList<>();
//                                        clsListMediaJasa mediaJasaDefault = new clsListMediaJasa();
                                        /*mediaJasaDefault.setTypeid("000");
                                        mediaJasaDefault.setDescType("Choose one");
                                        mediajasas.add(mediaJasaDefault);*/
                                        List<clsListPaymentMethod> paymentMethods = new ArrayList<>();
                                        try {
                                            paymentMethods = new clsListPaymentMethodRepo(context).findAll();
                                            paymentMethodList = new ArrayList<>();
                                            paymentMethodList.add(SPNCHOOSEONE);
                                            HMPaymentmethodList.put(SPNCHOOSEONE, "999");
                                            for (clsListPaymentMethod i : paymentMethods) {
                                                paymentMethodList.add(i.getNama());
                                                HMPaymentmethodList.put(i.getNama(), i.getKode());
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        if (paymentMethods.size() > 0) {
                                            SpinnerCustom.setAdapterSpinner(spnPaymentMethod, context, R.layout.custom_spinner, paymentMethodList);
                                            if (boolPaymentFilled) {
                                                SpinnerCustom.selectedItemByText(context, spnPaymentMethod, paymentMethodList, txtPaymentMth1);
                                            }
//                                            SpinnerCustom.selectedItemByText(context, spnProvinceAddOrder, categoriesProv, txtPropinsiName);
                                        }
                                        etCardNumber2.setFocusable(false);
                                        etCardNumber2.setEnabled(false);
                                        etBcaTraceNumber2.setFocusable(false);
                                        etBcaTraceNumber2.setEnabled(false);

                                        sourceMediaPaymentList.add(SPNCHOOSEONE);
                                        paymentMethodAndTipe.add(SPNCHOOSEONE);
                                        //boole
                                        spnPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                getActivity().onUserInteraction();
                                                String txtPaymentMethod = paymentMethodList.get(position);
                                                String idPaymentMethod = HMPaymentmethodList.get(txtPaymentMethod);
                                                if (!boolPaymentFilled) {
                                                    etBcaTraceNumber2.setText("");
                                                    etCardNumber2.setText("");
                                                }

                                                if (!idPaymentMethod.equals("999")) {
                                                    try {
                                                        if (idPaymentMethod.equals("000")) {
                                                            etCardNumber2.setFocusable(false);
                                                            etCardNumber2.setEnabled(false);
                                                            etBcaTraceNumber2.setFocusable(false);
                                                            etBcaTraceNumber2.setEnabled(false);
                                                        }
                                                        List<clsListPaymentMethodAndTipe> listPaymentMethodAndTipeSelected = new clsListPaymentMethodAndTipeRepo(context).findById2(idPaymentMethod);
                                                        paymentMethodAndTipe = new ArrayList<>();
                                                        paymentMethodAndTipe.add(SPNCHOOSEONE);
                                                        HMpaymentMethodAndTipe.put(SPNCHOOSEONE, "999");
                                                        for (clsListPaymentMethodAndTipe i : listPaymentMethodAndTipeSelected) {
                                                            paymentMethodAndTipe.add(i.getDesc_type());
                                                            HMpaymentMethodAndTipe.put(i.getDesc_type(), i.getTypeid());
                                                            HMCardNumber.put(i.getDesc_type(), i.getIntFillCardNumber());
                                                            HMTraceNumber.put(i.getDesc_type(), i.getIntFillTraceNumber());
                                                        }
                                                        if (paymentMethodAndTipe.size() > 0) {
                                                            SpinnerCustom.setAdapterSpinner(spnPaymentMethodAndTipe, context, R.layout.custom_spinner, paymentMethodAndTipe);
                                                            if (boolPaymentFilled) {
                                                                SpinnerCustom.selectedItemByText(context, spnPaymentMethodAndTipe, paymentMethodAndTipe, txtSourceMediaPayment1);
                                                            } else {
                                                                if (idPaymentMethod.equals("000")) {
                                                                    SpinnerCustom.selectedItemByText(context, spnPaymentMethodAndTipe, paymentMethodAndTipe, "Cash - Cash");
                                                                } else {
                                                                    SpinnerCustom.selectedItemByText(context, spnPaymentMethodAndTipe, paymentMethodAndTipe, SPNCHOOSEONE);
                                                                }

                                                                etBcaTraceNumber2.setText("");
                                                                etCardNumber2.setText("");
                                                            }
                                                        }

                                                    } catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                        spnPaymentMethodAndTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                getActivity().onUserInteraction();
                                                String txtPaymentMethodAndTipe = paymentMethodAndTipe.get(position);
                                                String idTxtSourceMedia = HMpaymentMethodAndTipe.get(txtPaymentMethodAndTipe);
                                                if (!boolPaymentFilled) {
                                                    etBcaTraceNumber2.setText("");
                                                    etCardNumber2.setText("");
                                                }
                                                if (!idTxtSourceMedia.equals("999")) {
                                                    int cardNumber = HMCardNumber.get(txtPaymentMethodAndTipe);
                                                    int traceNumber = HMTraceNumber.get(txtPaymentMethodAndTipe);
                                                    if (cardNumber == 1) {
                                                        etCardNumber2.setEnabled(true);
                                                        etCardNumber2.setFocusable(true);
                                                        etCardNumber2.setFocusableInTouchMode(true);
                                                        etCardNumber2.setClickable(true);
                                                    } else {
                                                        etCardNumber2.setEnabled(false);
                                                        etCardNumber2.setFocusable(false);
                                                        etCardNumber2.setFocusableInTouchMode(false);
                                                        etCardNumber2.setClickable(false);
                                                        etCardNumber2.setText("");
                                                    }
                                                    if (traceNumber == 1) {
                                                        etBcaTraceNumber2.setEnabled(true);
                                                        etBcaTraceNumber2.setFocusable(true);
                                                        etBcaTraceNumber2.setFocusableInTouchMode(true);
                                                        etBcaTraceNumber2.setClickable(true);
                                                    } else {
                                                        etBcaTraceNumber2.setEnabled(false);
                                                        etBcaTraceNumber2.setFocusable(false);
                                                        etBcaTraceNumber2.setFocusableInTouchMode(false);
                                                        etBcaTraceNumber2.setClickable(false);
                                                        etBcaTraceNumber2.setText("");
                                                    }

                                                    List<clsListMediaJasa> listMediaJasasSelected = new ArrayList<>();
                                                    if (!idTxtSourceMedia.equals("999")) {
                                                        try {
                                                            listMediaJasasSelected = new clsListMediaJasaRepo(context).findById2(idTxtSourceMedia);
                                                            List<clsListMediaJasa> mediaJasas = new ArrayList<>();
                                                            mediaJasas = new clsListMediaJasaRepo(context).findAll();
                                                            sourceMediaPaymentList = new ArrayList<>();
                                                            sourceMediaPaymentList.add(SPNCHOOSEONE);
                                                            HMsourceMediaPaymentList.put(SPNCHOOSEONE, "999");
                                                            for (clsListMediaJasa i : listMediaJasasSelected) {
                                                                sourceMediaPaymentList.add(i.getTxtmediajasapayment());
                                                                HMsourceMediaPaymentList.put(i.getTxtmediajasapayment(), i.getTxtmediajasapaymentid());
                                                            }
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }
                                                        if (listMediaJasasSelected.size() > 0) {
                                                            SpinnerCustom.setAdapterSpinner(spnSourceMediaPaymentList, context, R.layout.custom_spinner, sourceMediaPaymentList);
                                                            if (boolPaymentFilled) {
                                                                SpinnerCustom.selectedItemByText(context, spnSourceMediaPaymentList, sourceMediaPaymentList, txtPayment1);
                                                            }
                                                            if (idTxtSourceMedia.equals("000")) {
                                                                SpinnerCustom.selectedItemByText(context, spnSourceMediaPaymentList, sourceMediaPaymentList, "Cash");
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    sourceMediaPaymentList = new ArrayList<>();
                                                    sourceMediaPaymentList.add(SPNCHOOSEONE);
                                                    HMsourceMediaPaymentList.put(SPNCHOOSEONE, "999");
                                                    SpinnerCustom.setAdapterSpinner(spnSourceMediaPaymentList, context, R.layout.custom_spinner, sourceMediaPaymentList);
                                                }
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                        spnSourceMediaPaymentList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                if (!boolPaymentFilled) {
                                                    etBcaTraceNumber2.setText("");
                                                    etCardNumber2.setText("");
                                                }
                                                boolPaymentFilled = false;
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                        alertDialogBuilder.setView(promptView);
                                        alertDialogBuilder.setTitle("Payment Method");
                                        alertDialogBuilder
                                                .setCancelable(false)
                                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                        final AlertDialog alertD = alertDialogBuilder.create();
                                        alertD.show();
                                        alertD.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                getActivity().onUserInteraction();
                                                String paymentMethod = spnPaymentMethod.getSelectedItem().toString();
                                                String idPaymentMethod = HMPaymentmethodList.get(paymentMethod);
                                                String payment = "";
                                                String sourceMediaPayment = "";
                                                boolean boolPaymentValid = true;
                                                if (!idPaymentMethod.equals("999")) {
                                                    txtPaymentMethodID = idPaymentMethod;
                                                    boolPaymentValid = true;
                                                } else {
                                                    boolPaymentValid = false;
                                                }
                                                if (spnPaymentMethodAndTipe.getSelectedItem() != null && boolPaymentValid) {
                                                    sourceMediaPayment = spnPaymentMethodAndTipe.getSelectedItem().toString();
                                                    String idSourceMediaPayment = HMpaymentMethodAndTipe.get(sourceMediaPayment);
                                                    if (!idSourceMediaPayment.equals("999")) {
                                                        txtMediaJasaID = idSourceMediaPayment;
                                                        txtMediaJasaName = sourceMediaPayment;
                                                        txtmediajasapaymentFinal = txtMediaJasaName;
                                                        boolPaymentValid = true;
                                                    } else {
                                                        boolPaymentValid = false;
                                                    }

                                                } else {
                                                    boolPaymentValid = false;
                                                }
                                                if (spnSourceMediaPaymentList.getSelectedItem() != null && boolPaymentValid) {
                                                    payment = spnSourceMediaPaymentList.getSelectedItem().toString();
                                                    String paymentId = HMsourceMediaPaymentList.get(payment);
                                                    if (!paymentId.equals("999")) {
                                                        txtMediaJasaPaymentID = paymentId;


                                                    } else {
                                                        boolPaymentValid = false;
                                                    }

                                                }
                                                if (!idPaymentMethod.equals("000")) {
                                                    String a = etCardNumber2.getText().toString();
                                                    String b = etBcaTraceNumber2.getText().toString();
                                                    if (a.equals("") || b.equals("")) {
                                                        boolPaymentValid = false;
                                                    }
                                                }
//                                                String paymentMethod = spnPaymentMethod.getSelectedItem().toString();
//                                                String idPaymentMethod = HMPaymentmethodList.get(paymentMethod);
                                                if (boolPaymentValid) {
                                                    alertD.dismiss();

                                                    int cardNumb = HMCardNumber.get(sourceMediaPayment);
                                                    int traceBca = HMTraceNumber.get(sourceMediaPayment);
                                                    if (cardNumb == 1) {
                                                        String cn = etCardNumber2.getText().toString();
                                                        etCardNumber.setText(cn);
                                                        cn = cn.replace(" ", "");
                                                        cn = cn.replace("-", "");
                                                        txtCardNumber = cn;
                                                    } else {
                                                        etCardNumber.setText("0");
                                                        txtCardNumber = "0";
                                                    }
                                                    if (traceBca == 1) {
                                                        String bcaTN = etBcaTraceNumber2.getText().toString();
                                                        etBcaTraceNumber.setText(bcaTN);
                                                        bcaTN = bcaTN.replace(" ", "");
                                                        bcaTN = bcaTN.replace("-", "");
                                                        txtBCATraceNo = bcaTN;
                                                        if (bcaTN.equals("")) {

                                                        }
                                                    } else {
                                                        etBcaTraceNumber.setText("0");
                                                        txtBCATraceNo = "0";
                                                    }
                                                    tvPaymentMethod.setText(paymentMethod);
                                                    etPayment.setText(payment);
                                                    etSourceMediaPayment.setText(sourceMediaPayment);
                                                    linearLayoutPaymentDetail.setVisibility(View.VISIBLE);
                                                    linearLayoutPaymentDetail.setBackgroundResource(R.drawable.bg_rounded_select);
                                                } else {
                                                    ToastCustom.showToasty(context, "Invalid data payment", 3);
                                                }
                                                /* int selectedId = rg.getCheckedRadioButtonId();
                                                if (rbCash.isChecked()) {
                                                    tvPaymentMethod.setText("Cash");
                                                    alertD.dismiss();
                                                } else if (rbDebt.isChecked()) {
                                                    tvPaymentMethod.setText("Debit");
                                                    alertD.dismiss();
                                                } else {
                                                    ToastCustom.showToasty(context, "Please select payment method", 3);
                                                }*/
                                            }
                                        });
                                    }
                                });

                                if (walkin) {
                                    tvDeliveryByPrev.setText("Walk-In");
                                } else if (attchCust) {
                                    tvDeliveryByPrev.setText("Delivery Order");
                                } else {
                                    tvDeliveryByPrev.setText("Pass By");
                                }

                                tvRemarksPreview.setText(remarks);

                                if (attchCust) {

                                    etCustomerNamePrev.setText(tvCustomerNameHeader.getText().toString());
                                    etContactIDPrev.setText(tvContactIDHeader.getText().toString());
                                    etMemberNoPrev.setText(tvMemberNoHeader.getText().toString());
                                    tvProvinceCustomerPrev.setText(spnProvinceAddOrder.getSelectedItem().toString());
                                    tvCityCustomerPrev.setText(spnKabKotAddOrder.getSelectedItem().toString());
                                    tvRegionCustomerPrev.setText(spnKecamatanAddOrder.getSelectedItem().toString());
                                    tvPostCodeCustomerPrev.setText(spnPostCodeAddOrder.getSelectedItem().toString());
                                    tvAddressCustomerPrev.setText(etAddress.getText().toString());
                                    lnAttacedCust.setVisibility(View.VISIBLE);
                                    tvCustomerPrev.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getActivity().onUserInteraction();
                                            if (lnAttacedCust.getVisibility() == View.VISIBLE) {
                                                lnAttacedCust.setVisibility(View.GONE);
                                                tvCustomerPrev.setText("Show Contact Detail");
                                            } else {
                                                lnAttacedCust.setVisibility(View.VISIBLE);
                                                tvCustomerPrev.setText("Hide Contact Detail");
                                            }
                                            etCustomerNamePrev.setText(tvCustomerNameHeader.getText().toString());
                                            etContactIDPrev.setText(tvContactIDHeader.getText().toString());
                                            etMemberNoPrev.setText(tvMemberNoHeader.getText().toString());
                                            tvProvinceCustomerPrev.setText(spnProvinceAddOrder.getSelectedItem().toString());
                                            tvCityCustomerPrev.setText(spnKabKotAddOrder.getSelectedItem().toString());
                                            tvRegionCustomerPrev.setText(spnKecamatanAddOrder.getSelectedItem().toString());
                                            tvPostCodeCustomerPrev.setText(spnPostCodeAddOrder.getSelectedItem().toString());
                                            tvAddressCustomerPrev.setText(etAddress.getText().toString());
                                        }
                                    });

                                    tvHideCustPrev.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getActivity().onUserInteraction();
                                            lnAttacedCust.setVisibility(View.GONE);
                                        }
                                    });
                                } else {
                                    etCustomerNamePrev.setText(txtCustomerNameFinal);
//                                    etContactIDPrev.setVisibility(View.GONE);
//                                    lnContactIdPrev.setVisibility(View.GONE);
                                    etMemberNoPrev.setVisibility(View.GONE);
                                    lnMemberIdPrev.setVisibility(View.GONE);
                                    tvProvinceCustomerPrev.setText(txtPropinsiNameFinal);
                                    tvCityCustomerPrev.setText(txtKabupatenKotaNameFinal);
                                    tvRegionCustomerPrev.setText(txtKecamatanNameFinal);
                                    tvPostCodeCustomerPrev.setText(txtKodePosFinal);
                                    tvAddressCustomerPrev.setText(txtDeliveryAddressFinal);
                                    lnAttacedCust.setVisibility(View.VISIBLE);
                                    tvCustomerPrev.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getActivity().onUserInteraction();
                                            if (lnAttacedCust.getVisibility() == View.VISIBLE) {
                                                lnAttacedCust.setVisibility(View.GONE);
                                                tvCustomerPrev.setText("Show Contact Detail");
                                            } else {
                                                lnAttacedCust.setVisibility(View.VISIBLE);
                                                tvCustomerPrev.setText("Hide Contact Detail");
                                            }
                                            etCustomerNamePrev.setText(txtCustomerNameFinal);
                                            etContactIDPrev.setText(txtKontakIDFinal);
                                            etMemberNoPrev.setText("");
                                            tvProvinceCustomerPrev.setText(txtPropinsiNameFinal);
                                            tvCityCustomerPrev.setText(txtKabupatenKotaNameFinal);
                                            tvRegionCustomerPrev.setText(txtKecamatanNameFinal);
                                            tvPostCodeCustomerPrev.setText(txtKodePosFinal);
                                            tvAddressCustomerPrev.setText(txtDeliveryAddressFinal);
                                        }
                                    });

                                    tvHideCustPrev.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getActivity().onUserInteraction();
                                            lnAttacedCust.setVisibility(View.GONE);
                                        }
                                    });
                                    /*tvCustomerPrev.setText("KalCare Outlet");
                                    tvCustomerPrev.setClickable(false);*/
                                }
                                tvCustomerPrev.setClickable(true);
                                lnAttacedCust.setVisibility(View.GONE);


                                lvPreview.setAdapter(new CardAppAdapterPreview(context, contentLibsPreviewItem, Color.WHITE));
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                                rvPreview.setLayoutManager(mLayoutManager);
                                rvPreview.setItemAnimator(new DefaultItemAnimator());
//                                mAdapterItemPreview = new RVPreviewAdapter(context, contentLibsPreviewItem, Color.WHITE);
//                                rvPreview.setAdapter(mAdapterItemPreview);
//                                setListViewHeightBasedOnItems(lvPreview);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        if (cbPassBy.isChecked()) {
                                            dialog.cancel();

                                        } else {
                                            if (boolCustomerSet) {
                                                ToastCustom.showToasty(context, "Checking out", 3);
                                            } else {
                                                ToastCustom.showToasty(context, "Attach customer first", 4);
                                            }

                                        }

                                    }
                                });
                                alertDPreview = alertDialogBuilder.create();
                                alertDPreview.show();
                                alertDPreview.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getActivity().onUserInteraction();
                                        final String strLinkAPIFinalCheckout = new clsHardCode().linkCheckoutSalesOrder;
                                        final JSONObject resJsonFinalCheckout = new JSONObject();
                                        boolean boolSaved = true;
                                        try {
                                            draft = new clsDraftRepo(context).findByBitActive();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        if (draft.getTxtNoSO().equals("Generated by system")) {
                                            boolSaved = false;
                                        }
                                        JSONArray jsonProductCheckout = new Helper().writeJSONSaveData(context, draft, contentLibs);
                                        try {
//                                                resJsonFinalCheckout.put("txtNewId", draft.getGuiId());
                                            String strNO = draft.getTxtNoSO();
                                            if (strNO.equals("Generated by system")) {
                                                resJsonFinalCheckout.put("txtOrderNo", "");
                                            } else {
                                                resJsonFinalCheckout.put("txtOrderNo", draft.getTxtNoSO());
                                            }
                                            clsCustomerData dataDefault = new clsCustomerData();
                                            try {
                                                dataDefault = new clsCustomerDataRepo(context).findOne();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            resJsonFinalCheckout.put("txtLogisticID", "");
                                            resJsonFinalCheckout.put("txtLogType", "");
                                            resJsonFinalCheckout.put("intNilaiPembulatan", dbldecdecRoundedFinal);
                                            resJsonFinalCheckout.put("txtPaymentMethodID", txtPaymentMethodID);
                                            resJsonFinalCheckout.put("txtMediaJasaID", txtMediaJasaID);
                                            resJsonFinalCheckout.put("txtMediaJasaPaymentID", txtMediaJasaPaymentID);
                                            resJsonFinalCheckout.put("txtCardNumber", txtCardNumber);
                                            resJsonFinalCheckout.put("txtBCATraceNo", txtBCATraceNo);
                                            resJsonFinalCheckout.put("AdminSetPaidFromBankName", txtMediaJasaName);
                                            resJsonFinalCheckout.put("AdminSetPaidToBankName", txtMediaJasaName);
                                            resJsonFinalCheckout.put("AdminSetPaidFromName", txtCustomerFinal);
                                            double dblAdminSetPaidValue = dbldecTotalPriceFinal - dbldecdecRoundedFinal;
                                            resJsonFinalCheckout.put("AdminSetPaidValue", dblAdminSetPaidValue);
                                            resJsonFinalCheckout.put("AdminSetReferenceTransferNo", txtBCATraceNo);
                                            resJsonFinalCheckout.put("PaidDescription", tvPaymentMethod.getText().toString());
                                            resJsonFinalCheckout.put("decAmount", dbldecTaxAmountFinal);
                                            resJsonFinalCheckout.put("decTaxBaseAmount", dblTaxBasedAmountFinal);
                                            resJsonFinalCheckout.put("decTotal", dbldecTotalFinal);
                                            resJsonFinalCheckout.put("decTotDiscount", dbldecTotDiscountFinal);
                                            resJsonFinalCheckout.put("decTotalPrice", dbldecTotalPriceFinal);
                                            resJsonFinalCheckout.put("txtmediajasapayment", txtmediajasapaymentFinal);
                                            resJsonFinalCheckout.put("decTotalBonusPoin", dbldecTotalBonusPoin);
                                            resJsonFinalCheckout.put("decTotalPoin", dbldecTotalPoin);
                                            resJsonFinalCheckout.put("txtSourceOrder", data.getTxtSumberDataID());

                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            String currentDateandTime = sdf.format(new Date());
                                            resJsonFinalCheckout.put("dtDate", currentDateandTime);
                                            String txtDeliverSche = "";
                                            if (draft.getDtDeliverySche() != null) {
                                                txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                                            }
                                            resJsonFinalCheckout.put("dtDelivery", txtDeliverSche);
                                            String target="\\";
                                            String replacement="\\\\";
                                            String txtIdUser = dataLogin.getIdUser();
                                            String txtReplaced = txtIdUser.replace(target,replacement);
                                            String[] txtGetName = txtReplaced.split("\\\\");
                                            int intLength = txtGetName.length;
                                            String agentName = txtGetName[intLength-1];
                                            resJsonFinalCheckout.put("txtAgentName", agentName);

                                            String walkin = "";
                                            if (draft.isBoolPassBy() || draft.isBoolWalkinCustomer()) {
                                                walkin = "1";
                                                resJsonFinalCheckout.put("txtAlamatKirim", txtDeliveryAddressFinal);
                                                resJsonFinalCheckout.put("txtCustPhone", dataLogin.getTxtPhoneNo());
                                                resJsonFinalCheckout.put("txtCustName", txtCustomerNameFinal);
                                                resJsonFinalCheckout.put("txtKontakID", txtCustomerFinal);
                                                resJsonFinalCheckout.put("txtPickUpLocation", dataLogin.getTxtNamaInstitusi());

                                                SimpleDateFormat sdfFinalCheckout = new SimpleDateFormat("yyyy-MM-dd");
                                                if (draft.getDtDeliverySche() != null) {
                                                    txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                                                }

                                                resJsonFinalCheckout.put("dtKirim", txtDeliverSche);
                                                resJsonFinalCheckout.put("txtKelurahanID", txtKelurahanIDFinal);
                                                resJsonFinalCheckout.put("txtNamaKelurahan", txtKelurahanNameFinal);
                                                resJsonFinalCheckout.put("txtPropinsiID", txtPropinsiIDFinal);
                                                resJsonFinalCheckout.put("txtNamaPropinsi", txtPropinsiNameFinal);
                                                resJsonFinalCheckout.put("txtKabKotaID", txtKabupatenKotaIDFinal);
                                                resJsonFinalCheckout.put("txtNamaKabKota", txtKabupatenKotaNameFinal);
                                                resJsonFinalCheckout.put("txtKecamatanID", txtKecamatanIDFinal);
                                                resJsonFinalCheckout.put("txtNamaKecamatan", txtKecamatanNameFinal);
                                                resJsonFinalCheckout.put("txtKodePos", txtKodePosFinal);
                                            } else {
                                                walkin = "0";
                                                resJsonFinalCheckout.put("txtCustName", draft.getTxtCustomerName());
                                                resJsonFinalCheckout.put("txtAlamatKirim", draft.getTxtAddress());
                                                resJsonFinalCheckout.put("txtCustPhone", draft.getTxtPhoneNumber());
                                                resJsonFinalCheckout.put("txtCustName", draft.getTxtCustomerName());
                                                resJsonFinalCheckout.put("txtKontakID", draft.getTxtContactID());
                                                resJsonFinalCheckout.put("txtPickUpLocation", draft.getTxtProvince());

//                                                    SimpleDateFormat sdfFinalCheckout = new SimpleDateFormat("yyyy-MM-dd");
                                                if (draft.getDtDeliverySche() != null) {
                                                    txtDeliverSche = sdf.format(draft.getDtDeliverySche());
                                                }

                                                resJsonFinalCheckout.put("dtKirim", txtDeliverSche);
                                                resJsonFinalCheckout.put("txtKelurahanID", draft.getTxtKelurahanID());
                                                resJsonFinalCheckout.put("txtNamaKelurahan", draft.getTxtKelurahan());
                                                resJsonFinalCheckout.put("txtPropinsiID", String.valueOf(draft.getTxtProvinceID()));
                                                resJsonFinalCheckout.put("txtNamaPropinsi", draft.getTxtProvince());
                                                resJsonFinalCheckout.put("txtKabKotaID", String.valueOf(draft.getTxtKabKotID()));
                                                resJsonFinalCheckout.put("txtNamaKabKota", draft.getTxtKabKot());
                                                resJsonFinalCheckout.put("txtKecamatanID", String.valueOf(draft.getTxtKecamatanID()));
                                                resJsonFinalCheckout.put("txtNamaKecamatan", draft.getTxtKecamatan());
                                                resJsonFinalCheckout.put("txtKodePos", draft.getTxtPostCode());
                                            }
                                            resJsonFinalCheckout.put("intWalkIn", walkin);

                                            String deliverBy = "";
                                            if (draft.isBoolAttachCustomer()) {
                                                deliverBy = "1";
                                            } else {
                                                deliverBy = "0";
                                            }

                                            resJsonFinalCheckout.put("txtUserID", dataLogin.getIdUser());
                                            resJsonFinalCheckout.put("txtTeleID", dataLogin.getTxtTeleID());
                                            resJsonFinalCheckout.put("txtUserID", dataLogin.getIdUser());
                                            resJsonFinalCheckout.put("txtCodeID", dataLogin.getTxtCodeId());
                                            resJsonFinalCheckout.put("txtCustomer", draft.getTxtCustomerName());

                                            resJsonFinalCheckout.put("txtRemarkSO", draft.getTxtRemarks());
                                            resJsonFinalCheckout.put("txtDeviceId", deviceInfo.getModel());
                                            resJsonFinalCheckout.put("txtUser", dataLogin.getNmUser());
                                            resJsonFinalCheckout.put("txtStatusDoc", "0");
                                            resJsonFinalCheckout.put("decNetPrice", dbldecNetPriceFinal);
                                            resJsonFinalCheckout.put("Detail", jsonProductCheckout);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        final String mRequestBodyFinalCheckout = resJsonFinalCheckout.toString();
                                        if (!tvPaymentMethod.getText().toString().equals("*Add payment method")) {
                                            boolPaymentFill = true;
                                        } else {
                                            boolPaymentFill = false;
                                        }
                                        if (boolPaymentFill) {
                                            new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPIFinalCheckout, mRequestBodyFinalCheckout, access_token, "Please Wait...", new VolleyResponseListener() {
                                                @Override
                                                public void onError(String response) {
                                                    ToastCustom.showToasty(context, response, 2);
                                                }

                                                @Override
                                                public void onResponse(String response, Boolean status, String strErrorMsg) {
                                                    if (response != null) {
                                                        getActivity().onUserInteraction();
                                                        JSONObject jsonObject = null;
                                                        try {
                                                            jsonObject = new JSONObject(response);
                                                            int result = jsonObject.getInt("intResult");
                                                            String warn = jsonObject.getString("txtMessage");
                                                            if (result == 1) {
                                                                if (!jsonObject.getString("ListData").equals("null")) {
                                                                    JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                                    for (int n = 0; n < jsn.length(); n++) {

                                                                    }
                                                                }
                                                                alertDPreview.dismiss();
                                                                new clsProductDraftRepo(context).clearAllData();
                                                                new clsDraftRepo(context).clearAllData();
                                                                ToastCustom.showToasty(context, "Checkout Completed", 1);
                                                                FragmentSalesOrder SOFragment = new FragmentSalesOrder();
                                                                FragmentTransaction fragmentTransactionSO = getActivity().getSupportFragmentManager().beginTransaction();
                                                                fragmentTransactionSO.replace(R.id.frame, SOFragment, "FragmentSalesOrder");
                                                                fragmentTransactionSO.commit();


                                                            } else {
                                                                ToastCustom.showToasty(context, warn, 2);
//                                                                ToastCustom.showToasty(context, "Barang tidak tersedia", 2);
                                                            }
                                                        } catch (JSONException ex) {
                                                            String x = ex.getMessage();
                                                        }
                                                        String a = "";
                                                    }
                                                }
                                            });
                                        } else {
                                            ToastCustom.showToasty(context, "Payment method empty !", 3);
                                        }
                                    }
                                });

                                btnCancelPrev.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDPreview.dismiss();
                                        getActivity().onUserInteraction();
                                    }
                                });
                                        /*btnCheckoutPrev.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });*/
                            }
                        }else{
                            ToastCustom.showToasty(context, warn, 2);
                        }
                    } catch (Exception ex) {
                        String exa = "sa";
                    }


                }
            }
        });
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

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

    public boolean addItem(VMItems item) {
        getActivity().onUserInteraction();
        String itemCode = etItemCode.getText().toString();
        final String itemName = etItemName.getText().toString();
        boolean valid = true;
        if (valid) {
            boolean boolMatch = false;
            int j = 0;
            for (VMItems i : contentLibs) {
                if (i.getItemCode().equals(item.getItemCode()) && i.getDesc().equals(item.getDesc())) {
                    int intQty = item.getQty();
                    i.setQty(intQty);
                    i.setPrice(item.getPrice());
                    i.setNetPrice(item.getNetPrice());
                    i.setTotalPrice(item.getTotalPrice());
                    i.setTotalBasePoint(item.getTotalBasePoint());
                    i.setBasePoint(item.getBasePoint());
                    i.setBonusPoint(item.getBonusPoint());
                    i.setDiscount(item.getDiscount());
                    i.setTaxAmount(item.getTaxAmount());
                    boolMatch = true;
                    contentLibs.set(j, i);
                }
                j++;
            }
            if (!boolMatch) {
                contentLibs.add(item);
                ToastCustom.showToasty(context, "Item added", 3);
            } else {
                ToastCustom.showToasty(context, "Item on list updated", 3);
            }
            lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, contentLibs, Color.WHITE));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            rvParent.setLayoutManager(mLayoutManager);

            rvParent.setItemAnimator(new DefaultItemAnimator());
            mAdapterItemHeader = new RVParentAdapter(context, contentLibs, Color.WHITE);
            mAdapterItemHeader.setClickListener(this);
            rvParent.setAdapter(mAdapterItemHeader);

            lvItemAdd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    final String productName = contentLibs.get(position).getItemName();
                    final String itemGroup = contentLibs.get(position).getItemBrand();
                    final String itemCode = contentLibs.get(position).getItemCode();
                    final String itemBrand = contentLibs.get(position).getItemBrand();
                    final String itemBarcode = contentLibs.get(position).getBarcode();
                    final String itemDesc = contentLibs.get(position).getDesc();
                    final Double itemPrice = contentLibs.get(position).getPrice();
                    final Double itemDiscount = contentLibs.get(position).getDiscount();
                    final Double itemNetPrice = contentLibs.get(position).getNetPrice();
                    final Double itemTotalPrice = contentLibs.get(position).getTotalPrice();
                    final Double itemTax = contentLibs.get(position).getTaxAmount();
                    final String itemBasedPoint = contentLibs.get(position).getBasePoint();
                    final String itemBonusPoint = contentLibs.get(position).getBonusPoint();
                    final int intQty = contentLibs.get(position).getQty();
                    final int intQtyPaket = contentLibs.get(position).getQtyPaket();
                    final int intPaket = contentLibs.get(position).getIntPaket();
                    final int intCampagn = contentLibs.get(position).getIntCampagn();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    alertDialogBuilder.setMessage("Action for " + itemBrand + "-" + itemDesc + " ?");
                    alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            contentLibs.remove(position);
                            lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, contentLibs, Color.WHITE));
//                            rvParent.setAdapter(new RVParentAdapter(context, contentLibs, Color.WHITE));
                        }
                    })
                            .setCancelable(true)
                            .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).setNeutralButton("View Details", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDProductSearch = alertDialogBuilder.create();
                    alertDProductSearch.show();
                    startSessionPopUp();
                    alertDProductSearch.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getActivity().onUserInteraction();
                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                            ListView lvItemDetail;
                            final View promptViewDetail = layoutInflater.inflate(R.layout.popup_detail_item, null);
                            lvItemDetail = promptViewDetail.findViewById(R.id.lvItemAddDetail);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setView(promptViewDetail);
                            VMItems item = new VMItems(promptViewDetail);
                            item.setItemName(itemBrand);
                            item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                            item.setItemGroup(itemGroup);
                            item.setBarcode(itemBarcode);
                            item.setItemCode(itemDesc);
                            item.setDesc(itemDesc);
                            item.setItemBrand(itemBrand);
                            item.setBasePoint(itemBasedPoint);
                            item.setBonusPoint(itemBonusPoint);
                            item.setQty(intQty);
                            item.setPrice(itemPrice);
                            item.setTxtItemPacketID(itemDesc);
                            item.setNetPrice(itemNetPrice);
                            item.setDiscount(itemDiscount);
                            item.setTotalPrice(itemTotalPrice);
                            item.setTaxAmount(itemTax);

                            List<VMItems> itemDetail = new ArrayList<>();
                            itemDetail.add(item);
                            lvItemDetail.setAdapter(new CardAppAdapter(context, itemDetail, Color.WHITE));
                            alertDialogBuilder
                                    .setCancelable(true)
                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            alertDProductSearch.dismiss();
                                        }
                                    });
                            alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    alertDProductSearch.dismiss();
                                }
                            });
                            final AlertDialog alertDi = alertDialogBuilder.create();
                            alertDi.show();
                        }
                    });
                    alertDProductSearch.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelTimer();
                            getActivity().onUserInteraction();
                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                            final View promptView = layoutInflater.inflate(R.layout.popup_product_edit, null);
                            lvSearchResult = (ListView) promptView.findViewById(R.id.lvItemSearchResult);
                            etQtySearch = (EditText) promptView.findViewById(R.id.etQty);
                            Helper.textWatcher(etQtySearch, getActivity());
                            etSearchOnPopUp = (EditText) promptView.findViewById(R.id.etItemName);
                            etDiscount = (EditText) promptView.findViewById(R.id.etDiscount);
                            etPrice = (EditText) promptView.findViewById(R.id.etPrice);
                            etBonusPoint = (EditText) promptView.findViewById(R.id.etBonusPoint);
                            etBasedPoint = (EditText) promptView.findViewById(R.id.etBasePoint);
                            etSearchOnPopUp.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etSearchOnPopUp) {
                                @Override
                                public boolean onDrawableClick() {
//                                        ToastCustom.showToasty(context,"Toast bro",1);
                                    refreshListItemSearch(promptView);
                                    return false;
                                }
                            });
                            etSearchOnPopUp.setOnKeyListener(new View.OnKeyListener() {
                                @Override
                                public boolean onKey(View v, int keyCode, KeyEvent event) {
                                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                        refreshListItemSearch(promptView);
                                        return true;
                                    }
                                    return false;
                                }
                            });
                            contentSearchResult = new ArrayList<VMItems>();
                            VMItems item = new VMItems(promptView);

                            if (intPaket == 1) {
                                item = new VMItems(promptView);
                                item.setItemName(itemBrand);
                                item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                                item.setItemGroup(itemGroup);
                                item.setBarcode(itemBarcode);
                                item.setItemCode(itemDesc);
                                item.setDesc(itemDesc);
                                item.setItemBrand(itemBrand);
                                etQtySearch.setText(String.valueOf(intQtyPaket));
                            } else {
                                item = new VMItems(promptView);
                                item.setItemName(productName);
                                item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                                item.setItemGroup(itemGroup);
                                item.setBarcode(itemBarcode);
                                item.setItemCode(itemCode);
                                item.setDesc(itemDesc);
                                item.setItemBrand(itemBrand);
                                etQtySearch.setText(String.valueOf(intQty));
                            }


                            contentSearchResult.add(item);
                            lvSearchResult.setAdapter(new CardAdapterSearchResult(context, contentSearchResult, Color.WHITE));

                            lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    view.setSelected(true);
                                    getDetailItem(contentSearchResult, position);
                                }
                            });

                            double dblDisc = itemDiscount;
                            int intDisc = (int) dblDisc;
                            etDiscount.setText(String.valueOf(intDisc));
                            double dblPrice = itemPrice;
                            int intPrice = (int) dblPrice;
                            etPrice.setText(String.valueOf(itemPrice));
                            double dblBp = Double.parseDouble(itemBasedPoint);
                            int intBp = (int) dblBp;
                            etBasedPoint.setText(String.valueOf(intBp));
                            double dblBonP = Double.parseDouble(itemBonusPoint);
                            int intBonP = (int) dblBonP;
                            etBonusPoint.setText(String.valueOf(intBonP));

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setView(promptView);
                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    }).setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            final AlertDialog alertDi = alertDialogBuilder.create();
                            alertDi.show();
                            alertDi.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getActivity().onUserInteraction();
                                    String stQtySearch = etQtySearch.getText().toString();
                                    if (!stQtySearch.equals("") && Integer.parseInt(stQtySearch) < 1) {
                                        ToastCustom.showToasty(context, "Invalid Qty", 2);
                                    } else {
                                        if (1 > -1) {
                                            if (etQtySearch.getText().toString().equals("")) {
                                                ToastCustom.showToasty(context, "Quantity Empty", 3);
                                                etQtySearch.setBackgroundResource(R.drawable.bg_edtext_error_border);
                                            } else {

//                                                int positionResult = lvSearchResult.getCheckedItemPosition();
                                                int positionResult = 0;
                                                Object checkedItem = lvSearchResult.getAdapter().getItem(positionResult);
                                                VMItems itemResult = new VMItems(promptView);
                                                String itemNameAdd = contentSearchResult.get(positionResult).getItemName();
                                                String itemCodeAdd = contentSearchResult.get(positionResult).getItemCode();
                                                String itemBrand = contentSearchResult.get(positionResult).getItemBrand();
                                                double itemTax = decTaxGlobal;
                                                int intCampagn = intCampagnGlobal;
                                                int intbitPaket = contentSearchResult.get(positionResult).getIntPaket();
                                                int intBitCampaignPaket = contentSearchResult.get(positionResult).getIntCampagn();
                                                String txtPrice = etPrice.getText().toString();
                                                double itemPriceSearch = 0;
                                                if (!txtPrice.equals("0")) {
                                                    itemPriceSearch = Double.parseDouble(txtPrice);
                                                }
                                                String txtDisc = etDiscount.getText().toString();
                                                double itemDiscount = 0;
                                                if (!txtDisc.equals("0")) {
                                                    itemDiscount = Double.parseDouble(txtDisc);
                                                }
                                                String itemQtyAdd = etQtySearch.getText().toString();

                                                int itemQty = Integer.parseInt(itemQtyAdd);

                                                Double itemTotalPrice = (itemPriceSearch * itemQty) - itemDiscount;

//                                                Double itemTaxAmount = itemTotalPrice * 0.1;
                                                Double itemTaxAmount = itemTotalPrice * (itemTax / 100);

                                                Double itemNetPrice = itemTotalPrice + itemTaxAmount;

                                                String itemBasePoint1 = etBasedPoint.getText().toString();
                                                double dblBasepoint = Double.parseDouble(itemBasePoint1);
                                                int intItemBasePoint = (int)dblBasepoint;
                                                String itemBonusPoint1 = etBonusPoint.getText().toString();
                                                double intBonusPoint = Double.parseDouble(itemBonusPoint1);

                                                int totalBasePoinProduct = itemQty * intItemBasePoint;
                                                double totalBonusPointProduct = itemQty * intBonusPoint;

                                                VMItems item = new VMItems(promptView);

                                                if (intPaketGlobal == 1) {
                                                    String intQty = String.valueOf(itemQtyAdd);
                                                    String txtKontakID = dataLogin.getTxtKontakID();
                                                    String txtNoLangganan = "";
                                                    String intPeriodeLangganan = "0";
                                                    final String strLinkAPIPaketItem = new clsHardCode().linkGetPrice;
                                                    final JSONObject resJsonPaketItem = new JSONObject();
                                                    try {
                                                        resJsonPaketItem.put("txtGroupUser", dataLogin.getGrpUser());
                                                        resJsonPaketItem.put("txtItemCode", itemCodeAdd);
                                                        resJsonPaketItem.put("intQty", intQty.toString());
                                                        resJsonPaketItem.put("txtKontakID", txtKontakID);
                                                        resJsonPaketItem.put("txtNoLangganan", txtNoLangganan);
                                                        resJsonPaketItem.put("intPeriodeLangganan", intPeriodeLangganan);
                                                        resJsonPaketItem.put("txtCabang", "KALCARE");
                                                        resJsonPaketItem.put("intQty", itemQty);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    final String mRequestBodyPaketItem = resJsonPaketItem.toString();
                                                    new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPIPaketItem, mRequestBodyPaketItem, access_token, "Please Wait...", new VolleyResponseListener() {
                                                        @Override
                                                        public void onError(String response) {
                                                            ToastCustom.showToasty(context, response, 2);
                                                        }

                                                        @Override
                                                        public void onResponse(String response, Boolean status, String strErrorMsg) {
                                                            if (response != null) {
                                                                getActivity().onUserInteraction();
                                                                JSONObject jsonObject = null;
                                                                try {
                                                                    jsonObject = new JSONObject(response);
                                                                    int result = jsonObject.getInt("intResult");
                                                                    String warn = jsonObject.getString("txtMessage");
                                                                    if (result == 1) {
                                                                        if (!jsonObject.getString("ListData").equals("null")) {
                                                                            JSONArray jsn = jsonObject.getJSONArray("ListData");
                                                                            for (int n = 0; n < jsn.length(); n++) {
//                                                                                            JSONObject object = jsn.getJSONObject(n);
//                                                                                            String txtDiscount = object.getString("decDiscount");
                                                                                JSONArray arrListOfPoint = jsn.getJSONObject(n).getJSONArray("ListOfPoint");
                                                                                JSONObject objHeader = jsn.getJSONObject(n).getJSONObject("dtclsProductKHDData");
                                                                                int intQtyPaket = objHeader.getInt("intQty");
                                                                                for (int m = 0; m < arrListOfPoint.length(); m++) {
                                                                                    JSONObject obj = arrListOfPoint.getJSONObject(m);
                                                                                    double decCalcTotalPrice = obj.getDouble("decCalcTotalPrice");
                                                                                    double decCalcTaxAmount = obj.getDouble("decCalcTaxAmount");
                                                                                    double decCalcNetPrice = obj.getDouble("decCalcNetPrice");
                                                                                    double decCalcTotalBasePoint = obj.getDouble("decCalcTotalBasePoint");
                                                                                    double decCalcTotal = obj.getDouble("decCalcTotal");
                                                                                    String txtNewId = obj.getString("txtNewId");
                                                                                    String txtNoSO = obj.getString("txtNoSO");
                                                                                    String txtProductCode = obj.getString("txtProductCode");
                                                                                    String txtProductName = obj.getString("txtProductName");
                                                                                    String txtBrand = obj.getString("txtBrand");
                                                                                    String txtGroupProduct = obj.getString("txtGroupProduct");
                                                                                    String txtProductBarcode = obj.getString("txtProductBarcode");
                                                                                    String txtGroupItem = obj.getString("txtGroupItem");
                                                                                    String txtItemPacketID = obj.getString("txtItemPacketID");
                                                                                    String txtProductCategory = obj.getString("txtProductCategory");
                                                                                    int intItemID = obj.getInt("intItemID");
                                                                                    int intQty = obj.getInt("intQty");
                                                                                    double decPrice = obj.getInt("decPrice");
                                                                                    double decDiscount = obj.getInt("decDiscount");
                                                                                    double decBasePoint = obj.getInt("decBasePoint");
                                                                                    String decTotalBasePoint = obj.getString("decTotalBasePoint");
                                                                                    double decBonusPoint = obj.getInt("decBonusPoint");
                                                                                    int intBitPaket = obj.getInt("intBitPaket");
                                                                                    int bitActive = obj.getInt("bitActive");
                                                                                    String txtKN = obj.getString("txtKN");
                                                                                    String txtGroupKN = obj.getString("txtGroupKN");
                                                                                    VMItems item = new VMItems(promptView);
                                                                                    item.setItemName(txtProductName);
                                                                                    item.setGuiid(new Helper().GenerateGuid());
                                                                                    item.setItemCode(txtProductCode);
                                                                                    item.setPrice(decPrice);
                                                                                    item.setTxtItemPacketID(txtItemPacketID);
                                                                                    item.setItemBrand(txtBrand);
                                                                                    item.setItemGroup(txtGroupItem);
                                                                                    item.setTxtGroupProduct(txtGroupProduct);
                                                                                    item.setProductCategory(txtProductCategory);
                                                                                    item.setBasePoint(String.valueOf(decBasePoint));
                                                                                    item.setQty(intQty);
                                                                                    item.setBarcode(txtProductBarcode);
                                                                                    item.setDiscount(decDiscount);
                                                                                    item.setTotalPrice(decCalcTotalPrice);
                                                                                    item.setDecCalcTotalPrice(decCalcTotalPrice);
                                                                                    item.setTaxAmount(decCalcTaxAmount);
                                                                                    item.setDecCalcTaxAmount(decCalcTaxAmount);
                                                                                    item.setNetPrice(decCalcNetPrice);
                                                                                    item.setDesc(txtItemPacketID);
                                                                                    item.setDecCalcNetPrice(decCalcNetPrice);
                                                                                    item.setBonusPoint(String.valueOf(decBonusPoint));
                                                                                    item.setIntPaket(intBitPaket);
                                                                                    item.setQtyPaket(intQtyPaket);
                                                                                    item.setDectax(decTaxGlobal);
                                                                                    item.setTxtGroupKN(txtGroupKN);
                                                                                    item.setTxtKN(txtKN);
//                                                                                                item.setIntCampagn(intCampagn);

                                                                                    boolean booladded = addItem(item);
                                                                                    if (booladded) {
                                                                                        alertDProductSearch.dismiss();
                                                                                    } else {
                                                                                        ToastCustom.showToasty(context, "error system", 2);
                                                                                    }
                                                                                }
                                                                            }
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
                                                    item.setItemName(itemNameAdd);
                                                    item.setGuiid(new Helper().GenerateGuid());
                                                    item.setItemCode(itemCodeAdd);
                                                    item.setPrice(itemPriceSearch);
                                                    item.setItemBrand(itemBrand);
                                                    item.setProductCategory(HMtxtProductCategory.get(itemCodeAdd));
                                                    item.setBasePoint(Integer.toString(totalBasePoinProduct));
                                                    item.setQty(itemQty);
                                                    item.setDesc(itemNameAdd);
                                                    item.setDiscount(itemDiscount);
                                                    item.setTotalPrice(itemTotalPrice);
                                                    item.setTaxAmount(itemTaxAmount);
                                                    item.setNetPrice(itemNetPrice);
                                                    item.setBonusPoint(String.valueOf(totalBonusPointProduct));
                                                    item.setDectax(decTaxGlobal);
                                                    item.setTxtGroupKN(txtGroupKNGlobal);
                                                    item.setTxtKN(txtKNGlobal);
                                                    if (intbitPaket == 1) {
                                                        item.setIntCampagn(intBitCampaignPaket);
                                                    } else {
                                                        item.setIntCampagn(intCampagn);
                                                    }
                                                    boolean boolMatch = addItem(item);
                                                }
                                                lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, contentLibs, Color.WHITE));
                                                alertDi.dismiss();
                                                alertDProductSearch.dismiss();
                                            }

                                        } else {
                                            ToastCustom.showToasty(context, "Pick the item first", 4);
                                        }
                                    }


                                }
                            });
                        }
                    });
                    alertDProductSearch.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cancelTimer();
                            getActivity().onUserInteraction();
                            AlertDialog.Builder alertDialogBuilderDelete = new AlertDialog.Builder(getActivity());
                            alertDialogBuilderDelete.setMessage("Delete item " + itemBrand + "-" + itemDesc + " from list?");
                            alertDialogBuilderDelete
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setCancelable(false)
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            final AlertDialog alertDelete = alertDialogBuilderDelete.create();
                            alertDelete.show();
                            alertDelete.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getActivity().onUserInteraction();
                                    int intPaket = contentLibs.get(position).getIntPaket();
                                    if (intPaket == 1) {
                                        String packetID = contentLibs.get(position).getTxtItemPacketID();
                                        int a = contentLibs.size();
                                        for (int i = 0; i < a; i++) {
                                            String packetIDContent = contentLibs.get(i).getTxtItemPacketID();
                                            if (packetIDContent.equals(packetID)) {
                                                contentLibs.remove(i);
                                                a--;
                                                i--;
                                            }
                                        }
                                    } else {
                                        contentLibs.remove(position);
                                    }
                                    lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, contentLibs, Color.WHITE));
                                    alertDelete.dismiss();
                                    alertDProductSearch.dismiss();
                                    /*contentLibs.remove(position);
                                    lvItemAdd.setAdapter(new CardAppAdapter(context, contentLibs, Color.WHITE));
                                    alertDelete.dismiss();
                                    alertD.dismiss();*/
                                }
                            });
                        }
                    });
//                    alertDialogBuilder.setView(promptView);

                    return false;
                }
            });

            ListViewCustom.setListViewHeightBasedOnItems(getActivity(), lvItemAdd);
            reset();
            return true;
        } else {
            return false;
        }

    }

    @OnClick(R.id.ivReset)
    public void onViewClicked() {
        reset();
    }

    @OnClick(R.id.btnSearchItem)
    public void onbtnSearchClicked() {
        getActivity().onUserInteraction();
        String itemName = etItemName.getText().toString();
        String itemCode = etItemCode.getText().toString();
        if (!itemName.equals("")) {
            itemCode = "";
        }
        if (!itemCode.equals("")) {
            itemName = "";
        }
        String strLinkAPI = new clsHardCode().linkSearch;
        final JSONObject resJson = new JSONObject();
        try {
            resJson.put("txtProductBarcode", "");
            resJson.put("txtProductCode", itemCode.toUpperCase());
            resJson.put("txtProductDesc", itemName);
//            resJson.put("txtRefreshToken", token);
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
                            if (!jsonObject.getString("ListData").equals("null")) {

                                LayoutInflater layoutInflater = LayoutInflater.from(context);
                                final View promptView = layoutInflater.inflate(R.layout.popup_product_search_2, null);
                                lvSearchResult = (ListView) promptView.findViewById(R.id.lvItemSearchResult);
                                etQtySearch = (EditText) promptView.findViewById(R.id.etQty);
                                etSearchOnPopUp = (EditText) promptView.findViewById(R.id.etItemName);
                                Helper.textWatcher(etSearchOnPopUp, getActivity());
                                etDiscount = (EditText) promptView.findViewById(R.id.etDiscount);
                                etPrice = (EditText) promptView.findViewById(R.id.etPrice);
                                etBonusPoint = (EditText) promptView.findViewById(R.id.etBonusPoint);
                                etBasedPoint = (EditText) promptView.findViewById(R.id.etBasePoint);

                                etSearchOnPopUp.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(etSearchOnPopUp) {
                                    @Override
                                    public boolean onDrawableClick() {
                                        getActivity().onUserInteraction();
//                                        ToastCustom.showToasty(context,"Toast bro",1);
                                        refreshListItemSearch(promptView);
                                        return false;
                                    }
                                });
                                onclickKeyEnter(etSearchOnPopUp, promptView);

                                contentSearchResult = new ArrayList<VMItems>();
                                VMItems item = new VMItems(promptView);

                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String txtProductCategory = object.getString("txtProductCategory");
                                    String txtBrand = object.getString("txtBrand");
                                    String txtGroupProduct = object.getString("txtGroupProduct");
                                    String txtProductBarcode = object.getString("txtProductBarcode");
                                    String txtProductCode = object.getString("txtProductCode");
                                    HMtxtProductCategory.put(txtProductCode, txtProductCategory);
                                    String txtProductDesc = object.getString("txtProductDesc");
//                                    int intCampagn = object.getInt("intCampaign");
//                                    double dbltax = object.getDouble("decTax");


                                    item = new VMItems(promptView);
                                    item.setItemName(txtProductDesc);
                                    item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                                    item.setItemGroup(txtGroupProduct);
                                    item.setBarcode(txtProductBarcode);
                                    item.setItemCode(txtProductCode);
                                    item.setDesc(txtProductDesc);
                                    item.setItemBrand(txtBrand);
//                                    item.setIntCampagn(intCampagn);


                                    contentSearchResult.add(item);

                                }
                                getActivity().onUserInteraction();

                                lvSearchResult.setAdapter(new CardAdapterSearchResult(context, contentSearchResult, Color.WHITE));

                                lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        view.setSelected(true);
                                        getDetailItem(contentSearchResult, position);
                                    }
                                });
                                MainMenu objMain = (MainMenu) getActivity();
                                AlertDialog.Builder alertDialogBuilderAttch = new AlertDialog.Builder(objMain);
                                alertDialogBuilderAttch.setView(promptView);
//        alertDialogBuilderAttch.setMessage("this is attachment of customer dialog");
                                alertDialogBuilderAttch
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .setCancelable(false)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
//                                                cbAttach.setChecked(false);
//                                                lvSearchResult.setSelected(false);
                                            }
                                        });
                                alertDSearchitem = alertDialogBuilderAttch.create();
                                if (jsn.length() == 0) {
                                    ToastCustom.showToasty(context, "Item Not Found", 3);
                                } else {
                                    alertDSearchitem.show();
                                    startSessionPopUp();
                                    alertDSearchitem.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            cancelTimer();
                                            getActivity().onUserInteraction();
                                            String stQtySearch = etQtySearch.getText().toString();
                                            String itemBasePoint = etBasedPoint.getText().toString();
                                            String itemBonusPoint = etBonusPoint.getText().toString();
                                            int qty = 0;
                                            if (!stQtySearch.equals("")) {
                                                qty = Integer.parseInt(stQtySearch);
                                            }
                                            if (qty < 1 || itemBasePoint.equals("") || itemBonusPoint.equals("")) {
                                                ToastCustom.showToasty(context, "Invalid Data", 2);
                                            } else {
                                                if (lvSearchResult.getCheckedItemPosition() > -1) {
                                                    if (etQtySearch.getText().toString().equals("")) {
                                                        ToastCustom.showToasty(context, "Quantity Empty", 3);
                                                        etQtySearch.setBackgroundResource(R.drawable.bg_edtext_error_border);
                                                    } else {
                                                        Object checkedItem = lvSearchResult.getAdapter().getItem(lvSearchResult.getCheckedItemPosition());
                                                        VMItems itemResult = new VMItems(promptView);
                                                        int position = lvSearchResult.getCheckedItemPosition();
                                                        String itemNameAdd = contentSearchResult.get(position).getItemName();
                                                        String itemCodeAdd = contentSearchResult.get(position).getItemCode();
                                                        String itemBrand = contentSearchResult.get(position).getItemBrand();
                                                        String itemDesc = contentSearchResult.get(position).getDesc();
                                                        int intCampagn = contentSearchResult.get(position).getIntCampagn();
                                                        String txtPrice = etPrice.getText().toString();
                                                        double itemPriceSearch = 0;
                                                        if (!txtPrice.equals("0")) {
                                                            itemPriceSearch = Double.parseDouble(txtPrice);
                                                        }
                                                        String txtDisc = etDiscount.getText().toString();
                                                        double itemDiscount = 0;
                                                        if (!txtDisc.equals("0")) {
                                                            itemDiscount = Double.parseDouble(txtDisc);
                                                        }
                                                        String itemQtyAdd = etQtySearch.getText().toString();

                                                        int itemQty = Integer.parseInt(itemQtyAdd);

                                                        Double itemTotalPrice = (itemPriceSearch * itemQty) - itemDiscount;

                                                        Double itemTaxAmount = itemTotalPrice * 0.1;
                                                        Double b = itemTotalPrice + itemTaxAmount;
                                                        //a nya buat pembulatan
                                                        DecimalFormat twoDForm = new DecimalFormat("#");
                                                        Double a = Double.valueOf(twoDForm.format(b));
                                                        Double itemNetPrice = b;
                                                        String itemBasePoint1 = etBasedPoint.getText().toString();
                                                        double intItemBasePoint = Double.parseDouble(itemBasePoint1);
                                                        double intTotalItemBasePoint = intItemBasePoint * itemQty;
                                                        String itemBonusPoint1 = etBonusPoint.getText().toString();
                                                        double intBonusPoint = Double.parseDouble(itemBonusPoint1);
                                                        double intTotalBonusPoint = intBonusPoint * itemQty;


                                                        if (intPaketGlobal == 1) {
                                                            String intQty = String.valueOf(itemQtyAdd);
                                                            String txtKontakID = dataLogin.getTxtKontakID();
                                                            String txtNoLangganan = "";
                                                            String intPeriodeLangganan = "0";
                                                            final String strLinkAPIPaketItem = new clsHardCode().linkGetPrice;
                                                            final JSONObject resJsonPaketItem = new JSONObject();
                                                            try {
                                                                resJsonPaketItem.put("txtGroupUser", dataLogin.getGrpUser());
                                                                resJsonPaketItem.put("txtItemCode", itemCodeAdd);
                                                                resJsonPaketItem.put("intQty", intQty.toString());
                                                                resJsonPaketItem.put("txtKontakID", txtKontakID);
                                                                resJsonPaketItem.put("txtNoLangganan", txtNoLangganan);
                                                                resJsonPaketItem.put("intPeriodeLangganan", intPeriodeLangganan);
                                                                resJsonPaketItem.put("txtCabang", "KALCARE");
                                                                resJsonPaketItem.put("intQty", itemQty);
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            final String mRequestBodyPaketItem = resJsonPaketItem.toString();
                                                            new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPIPaketItem, mRequestBodyPaketItem, access_token, "Please Wait...", new VolleyResponseListener() {
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
                                                                                    for (int n = 0; n < jsn.length(); n++) {
//                                                                                            JSONObject object = jsn.getJSONObject(n);
//                                                                                            String txtDiscount = object.getString("decDiscount");
                                                                                        JSONArray arrListOfPoint = jsn.getJSONObject(n).getJSONArray("ListOfPoint");
                                                                                        JSONObject objHeader = jsn.getJSONObject(n).getJSONObject("dtclsProductKHDData");
                                                                                        int intQtyPaket = objHeader.getInt("intQty");
                                                                                        for (int m = 0; m < arrListOfPoint.length(); m++) {
                                                                                            JSONObject obj = arrListOfPoint.getJSONObject(m);
                                                                                            double decCalcTotalPrice = obj.getDouble("decCalcTotalPrice");
                                                                                            double decCalcTaxAmount = obj.getDouble("decCalcTaxAmount");
                                                                                            double decCalcNetPrice = obj.getDouble("decCalcNetPrice");
                                                                                            double decCalcTotalBasePoint = obj.getDouble("decCalcTotalBasePoint");
                                                                                            double decCalcTotal = obj.getDouble("decCalcTotal");
                                                                                            String txtNewId = obj.getString("txtNewId");
                                                                                            String txtNoSO = obj.getString("txtNoSO");
                                                                                            String txtProductCode = obj.getString("txtProductCode");
                                                                                            String txtProductName = obj.getString("txtProductName");
                                                                                            String txtBrand = obj.getString("txtBrand");
                                                                                            String txtGroupProduct = obj.getString("txtGroupProduct");
                                                                                            String txtProductBarcode = obj.getString("txtProductBarcode");
                                                                                            String txtGroupItem = obj.getString("txtGroupItem");
                                                                                            String txtItemPacketID = obj.getString("txtItemPacketID");
                                                                                            String txtProductCategory = obj.getString("txtProductCategory");
                                                                                            double decHJPOriginal = obj.getDouble("decHJPOriginal");
                                                                                            int intItemID = obj.getInt("intItemID");
                                                                                            int intQty = obj.getInt("intQty");
                                                                                            double decPrice = obj.getInt("decPrice");
                                                                                            double decDiscount = obj.getInt("decDiscount");
                                                                                            double decBasePoint = obj.getInt("decBasePoint");
                                                                                            String decTotalBasePoint = obj.getString("decTotalBasePoint");
                                                                                            double decBonusPoint = obj.getInt("decBonusPoint");
                                                                                            int intBitPaket = obj.getInt("intBitPaket");
                                                                                            int bitActive = obj.getInt("bitActive");
                                                                                            String txtKN = obj.getString("txtKN");
                                                                                            String txtGroupKN = obj.getString("txtGroupKN");
                                                                                            VMItems item = new VMItems(promptView);
                                                                                            item.setItemName(txtProductName);
                                                                                            item.setGuiid(new Helper().GenerateGuid());
                                                                                            item.setItemCode(txtProductCode);
                                                                                            item.setPrice(decPrice);
                                                                                            item.setTxtItemPacketID(txtItemPacketID);
                                                                                            item.setItemBrand(txtBrand);
                                                                                            item.setItemGroup(txtGroupItem);
                                                                                            item.setTxtGroupProduct(txtGroupProduct);
                                                                                            item.setProductCategory(txtProductCategory);
                                                                                            item.setBasePoint(String.valueOf(decBasePoint));
                                                                                            item.setQty(intQty);
                                                                                            item.setBarcode(txtProductBarcode);
                                                                                            item.setDiscount(decDiscount);
                                                                                            item.setTotalPrice(decCalcTotalPrice);
                                                                                            item.setDecCalcTotalPrice(decCalcTotalPrice);
                                                                                            item.setTaxAmount(decCalcTaxAmount);
                                                                                            item.setDecCalcTaxAmount(decCalcTaxAmount);
                                                                                            item.setNetPrice(decCalcNetPrice);
                                                                                            item.setDesc(txtItemPacketID);
                                                                                            item.setDecCalcNetPrice(decCalcNetPrice);
                                                                                            item.setBonusPoint(String.valueOf(decBonusPoint));
                                                                                            item.setIntPaket(intBitPaket);
                                                                                            item.setDecHJPOriginal(decHJPOriginal);
                                                                                            item.setQtyPaket(intQtyPaket);
                                                                                            item.setDectax(decTaxGlobal);
                                                                                            item.setTxtKN(txtKN);
                                                                                            item.setTxtGroupKN(txtGroupKN);
//                                                                                                item.setIntCampagn(intCampagn);

                                                                                            boolean booladded = addItem(item);
                                                                                            if (booladded) {
                                                                                                alertDSearchitem.dismiss();
                                                                                            } else {
                                                                                                ToastCustom.showToasty(context, "error system", 2);
                                                                                            }
                                                                                        }
                                                                                    }
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
                                                            VMItems item = new VMItems(promptView);
                                                            item.setItemName(itemNameAdd);
                                                            item.setGuiid(new Helper().GenerateGuid());
                                                            item.setItemCode(itemCodeAdd);
                                                            item.setPrice(itemPriceSearch);
                                                            item.setItemBrand(itemBrand);
                                                            item.setProductCategory(HMtxtProductCategory.get(itemCodeAdd));
                                                            item.setBasePoint(String.valueOf(intTotalItemBasePoint));
                                                            item.setQty(itemQty);
                                                            item.setTxtItemPacketID(itemDesc);
                                                            item.setDiscount(itemDiscount);
                                                            item.setTotalPrice(itemTotalPrice);
                                                            item.setDesc(itemNameAdd);
                                                            item.setTaxAmount(itemTaxAmount);
                                                            item.setNetPrice(itemNetPrice);
                                                            item.setBonusPoint(String.valueOf(intTotalBonusPoint));
                                                            item.setIntCampagn(intCampagn);
                                                            item.setTxtGroupProduct(txtGroupProductGlobal);
                                                            item.setBarcode(txtBarcodeGlobal);
                                                            item.setItemGroup(txtGroupItemGlobal);
                                                            item.setDectax(decTaxGlobal);
                                                            item.setTxtKN(txtKNGlobal);
                                                            item.setTxtGroupKN(txtGroupKNGlobal);

                                                            boolean booladded = addItem(item);
                                                            if (booladded) {
                                                                alertDSearchitem.dismiss();
                                                            } else {
                                                                ToastCustom.showToasty(context, "error system", 2);
                                                            }
                                                        }
                                                    }

                                                } else {
                                                    ToastCustom.showToasty(context, "Pick the item first", 4);
                                                }
                                            }

                                        }
                                    });
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String a = "";
                }
            }
        });

    }

    @Override
    public void onResume() {
        getActivity().onUserInteraction();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (alertDSearchitem != null) {
            alertDSearchitem.dismiss();
        }
//        getActivity().onUserInteraction();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (alertDSearchitem != null) {
            alertDSearchitem.dismiss();
        }
    }

    private String getPeriodeActive() {
        final String strLinkAPI = new clsHardCode().linkGetPeriodeActive;
        final JSONObject resJson = new JSONObject();
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
                        DateFormat inputFormat = new SimpleDateFormat("yyyyMM");

                        if (result == 1) {
                            try {
                                Date date = inputFormat.parse(warn);
                                Date dtLogin = dataLogin.getDtLogin();
                                Calendar cal1 = Calendar.getInstance();
                                Calendar cal2 = Calendar.getInstance();
                                cal1.setTime(date);
                                cal2.setTime(dtLogin);
                                boolean valid = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
                                //testing
                                valid = true;
                                if (valid) {
                                    getNext();
                                } else {
                                    SimpleDateFormat inputFormat2 = new SimpleDateFormat("yyyy MM");
                                    String b = inputFormat2.format(date);
                                    String a = "Periode saat ini " + b + " tidak sama dengan SO Date. Pastikan proses Monthly Closing sudah dilakukan";
//                                    ToastCustom.showToasty(context,a,4);
                                    Toasty.warning(context, a, Toast.LENGTH_LONG, true).show();
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
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

        return "";
    }

    private void getNext() {
        getActivity().onUserInteraction();
        boolean boolValidNext = true;
        if (etDeliverySchedule.getText().toString().equals("")) {
            ToastCustom.showToasty(context, "Please set delivery schedule", 3);
        } else {
            lvItemAdd.setAdapter(new CardAdapterNewRevisi(context, new ArrayList<VMItems>(), Color.WHITE));
            rvParent.setAdapter(new RVParentAdapter(context, new ArrayList<VMItems>(), Color.WHITE));
            String noSO = etNoSo.getText().toString();
            String soStatus = etSoStatus.getText().toString();
            String soDate = etDate.getText().toString();
            String kontakID = tvContactIDHeader.getText().toString();
            String soSource = etSOSource.getText().toString();
            String deliverySche = etDeliverySchedule.getText().toString();
            String agentName = etAgentName.getText().toString();
            String orderLocation = etOrderLocation.getText().toString();
            boolean deliverByPass = cbPassBy.isChecked();
            boolean attchCust = cbAttach.isChecked();
            boolean walkinCustomer = cbWalkInCustomer.isChecked();
            String remarks = etRemarks.getText().toString();
            String guiid = new Helper().GenerateGuid();
            boolean draftDelivery = false;
            boolean boolDraftNull = true;
            try {
                draft = new clsDraftRepo(context).findByBitActive();
                if (draft != null) {
                    boolDraftNull = false;
                    boolean boolDraftWalkin = draft.isBoolWalkinCustomer();
                    boolean boolPassBy = draft.isBoolPassBy();
                    boolean boolDeliverySch = draft.isBoolAttachCustomer();


                    if (boolDraftWalkin) {
                        if (boolDraftWalkin == walkinCustomer) {
                            draftDelivery = true;
                        }
                    } else if (boolPassBy) {
                        if (boolPassBy == deliverByPass) {
                            draftDelivery = true;
                        }
                    } else if (boolDeliverySch) {
                        if (boolDeliverySch == attchCust) {
                            draftDelivery = true;
                        }
                    }
                    draft.setBoolAttachCustomer(attchCust);
                    draft.setTxtNoSO(noSO);
                    draft.setIntStatus(0);
                    if (!soDate.equals("")) {
                        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
//                    Calendar cal = Calendar.getInstance();
//                    String now = dateFormat.format(cal.getTime()).toString();
                        Date dtSoDate = null;
                        try {
                            dtSoDate = dateFormat.parse(soDate);
                            draft.setDtSODate(dtSoDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    draft.setTxtSoSource(soSource);
                    if (!deliverySche.equals("")) {
                        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        Date dtDeliverySche = null;
                        try {
                            dtDeliverySche = dateFormat.parse(soDate);
                            draft.setDtDeliverySche(dtDeliverySche);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    draft.setTxtAgentName(agentName);
                    draft.setTxtOrderLocation(orderLocation);
                    draft.setBoolPassBy(deliverByPass);
                    draft.setBoolWalkinCustomer(walkinCustomer);
                    if (!deliverByPass && attchCust) {
                        String contactId = tvContactIDHeader.getText().toString();
                        String memberNo = tvMemberNoHeader.getText().toString();
                        String customername = tvCustomerNameHeader.getText().toString();
                        String province = spnProvinceAddOrder.getSelectedItem().toString();
                        String provinceID = HMProvinceID.get(province);
                        String kabKot = spnKabKotAddOrder.getSelectedItem().toString();
                        String kabKotID = HMKabKotID.get(kabKot);
                        String Kec = spnKecamatanAddOrder.getSelectedItem().toString();
                        String kecID = HMKecID.get(Kec);
                        String kel = spnKelurahanAddOrder.getSelectedItem().toString();
                        String kelID = HMKel.get(kel);
                        String postCodeKel = spnPostCodeAddOrder.getSelectedItem().toString();
                        String postCode = HMKodePos.get(postCodeKel);
                        String phoneNumber = tvPhoneNumb.getText().toString();
                        String address = etAddress.getText().toString();
                        draft.setTxtContactID(contactId);
                        draft.setTxtMemberID(memberNo);
                        draft.setTxtPhoneNumber(phoneNumber);
                        draft.setTxtCustomerName(customername);
                        draft.setTxtProvinceID(provinceID);
                        draft.setTxtProvince(province);
                        draft.setTxtKabKot(kabKot);
                        draft.setTxtKabKotID(kabKotID);
                        draft.setTxtKontakID(kontakID);
                        draft.setTxtKecamatan(Kec);
                        draft.setTxtKecamatanID(kecID);
                        draft.setTxtKelurahan(kel);
                        draft.setTxtKelurahanID(kelID);
                        draft.setTxtPostCode(postCode);
                        draft.setTxtAddress(address);
                        draft.setBoolPassBy(false);
                        draft.setBoolAttachCustomer(true);

                        if (kelID.equals("0") || provinceID.equals("0") || kabKotID.equals("0") || postCode.equals("0") || kecID.equals("0")) {
                            boolValidNext = false;
                            ToastCustom.showToasty(context, "Invalid Customer Address", 3);
                        }
                    } else if (!deliverByPass && !attchCust && walkinCustomer) {
                        String contactId = tvContactIDHeader.getText().toString();
                        String memberNo = tvMemberNoHeader.getText().toString();
                        String customername = tvCustomerNameHeader.getText().toString();
                        String province = spnProvinceAddOrder.getSelectedItem().toString();
                        String provinceID = HMProvinceID.get(province);
                        String kabKot = spnKabKotAddOrder.getSelectedItem().toString();
                        String kabKotID = HMKabKotID.get(kabKot);
                        String Kec = spnKecamatanAddOrder.getSelectedItem().toString();
                        String kecID = HMKecID.get(Kec);
                        String kel = spnKelurahanAddOrder.getSelectedItem().toString();
                        String kelID = HMKel.get(kel);
                        String postCodeKel = spnPostCodeAddOrder.getSelectedItem().toString();
                        String postCode = HMKodePos.get(postCodeKel);
                        String phoneNumber = tvPhoneNumb.getText().toString();
                        String address = etAddress.getText().toString();
                        draft.setTxtContactID(contactId);
                        draft.setTxtMemberID(memberNo);
                        draft.setTxtPhoneNumber(phoneNumber);
                        draft.setTxtCustomerName(customername);
                        draft.setTxtProvinceID(provinceID);
                        draft.setTxtProvince(province);
                        draft.setTxtKabKot(kabKot);
                        draft.setTxtKabKotID(kabKotID);
                        draft.setTxtKontakID(kontakID);
                        draft.setTxtKecamatan(Kec);
                        draft.setTxtKecamatanID(kecID);
                        draft.setTxtKelurahan(kel);
                        draft.setTxtKelurahanID(kelID);
                        draft.setTxtPostCode(postCode);
                        draft.setTxtAddress(address);
                        draft.setBoolPassBy(false);
                        draft.setBoolAttachCustomer(false);
                        draft.setBoolWalkinCustomer(true);

                        if (kelID.equals("0") || provinceID.equals("0") || kabKotID.equals("0") || postCode.equals("0") || kecID.equals("0")) {
                            boolValidNext = false;
                            ToastCustom.showToasty(context, "Invalid Customer Address", 3);
                        }
                    } else {
                        draft.setTxtContactID("");
                        draft.setTxtMemberID("");
                        draft.setTxtCustomerName("");
                        draft.setTxtProvince("");
                        draft.setTxtKabKot("");
                        draft.setTxtKecamatan("");
                        draft.setTxtPostCode("");
                        draft.setTxtAddress("");
                        draft.setTxtKontakID("");
                    }
                    draft.setTxtRemarks(remarks);
                    draft.setIntStatus(0);
                    new clsDraftRepo(context).clearAllData();
                    new clsDraftRepo(context).createOrUpdate(draft);
                } else {
                    draft = new clsDraft();
                    draft.setGuiId(guiid);
                    draft.setTxtNoSO(noSO);
                    draft.setIntStatus(0);
                    if (!soDate.equals("")) {
                        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
//                    Calendar cal = Calendar.getInstance();
//                    String now = dateFormat.format(cal.getTime()).toString();
                        Date dtSoDate = null;
                        try {
                            dtSoDate = dateFormat.parse(soDate);
                            draft.setDtSODate(dtSoDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    draft.setTxtSoSource(soSource);
                    if (!deliverySche.equals("")) {
                        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                        Date dtDeliverySche = null;
                        try {
                            dtDeliverySche = dateFormat.parse(deliverySche);
                            draft.setDtDeliverySche(dtDeliverySche);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    draft.setTxtAgentName(agentName);
                    draft.setTxtOrderLocation(orderLocation);
                    draft.setBoolPassBy(deliverByPass);
                    draft.setBoolAttachCustomer(attchCust);
                    draft.setBoolWalkinCustomer(walkinCustomer);

                    if (attchCust) {
                        String contactId = tvContactIDHeader.getText().toString();
                        String memberNo = tvMemberNoHeader.getText().toString();
                        String customername = tvCustomerNameHeader.getText().toString();
                        String province = spnProvinceAddOrder.getSelectedItem().toString();
                        String provinceID = HMProvinceID.get(province);
                        String kabKot = spnKabKotAddOrder.getSelectedItem().toString();
                        String kabKotID = HMKabKotID.get(kabKot);
                        String Kec = spnKecamatanAddOrder.getSelectedItem().toString();
                        String kecID = HMKecID.get(Kec);
                        String kel = spnKelurahanAddOrder.getSelectedItem().toString();
                        String kelID = HMKel.get(kel);
                        String postCodeKel = spnPostCodeAddOrder.getSelectedItem().toString();
                        String postCode = HMKodePos.get(postCodeKel);
                        String address = etAddress.getText().toString();
                        String phoneNumber = tvPhoneNumb.getText().toString();
                        if (kelID.equals("0") || provinceID.equals("0") || kabKotID.equals("0") || postCode.equals("0") || kecID.equals("0")) {
                            boolValidNext = false;
                            ToastCustom.showToasty(context, "Invalid Customer Address", 3);
                        }

                        draft.setTxtContactID(contactId);
                        draft.setTxtMemberID(memberNo);
                        draft.setTxtCustomerName(customername);
                        draft.setTxtProvinceID(provinceID);
                        draft.setTxtProvince(province);
                        draft.setTxtKabKot(kabKot);
                        draft.setTxtKabKotID(kabKotID);
                        draft.setTxtKecamatan(Kec);
                        draft.setTxtKecamatanID(kecID);
                        draft.setTxtKelurahan(kel);
                        draft.setTxtKelurahanID(kelID);
                        draft.setTxtPostCode(postCode);
                        draft.setTxtAddress(address);
                        draft.setTxtPhoneNumber(phoneNumber);
                        draft.setIntStatus(0);
                    } else if (walkinCustomer) {
                        String contactId = tvContactIDHeader.getText().toString();
                        String memberNo = tvMemberNoHeader.getText().toString();
                        String customername = tvCustomerNameHeader.getText().toString();
                        String province = spnProvinceAddOrder.getSelectedItem().toString();
                        String provinceID = province;
                        String kabKot = spnKabKotAddOrder.getSelectedItem().toString();
                        String kabKotID = kabKot;
                        String Kec = spnKecamatanAddOrder.getSelectedItem().toString();
                        String kecID = HMKecID.get(Kec);
                        String kel = spnKelurahanAddOrder.getSelectedItem().toString();
                        String kelID = HMKel.get(kel);
                        String postCodeKel = spnPostCodeAddOrder.getSelectedItem().toString();
                        String postCode = HMKodePos.get(postCodeKel);
                        String address = postCodeKel;
                        String phoneNumber = tvPhoneNumb.getText().toString();
                        if (kelID.equals("0") || provinceID.equals("0") || kabKotID.equals("0") || postCode.equals("0") || kecID.equals("0")) {
                            boolValidNext = false;
                            ToastCustom.showToasty(context, "Invalid Customer Address", 3);
                        }

                        draft.setTxtContactID(contactId);
                        draft.setTxtMemberID(memberNo);
                        draft.setTxtCustomerName(customername);
                        draft.setTxtProvinceID(provinceID);
                        draft.setTxtProvince(province);
                        draft.setTxtKabKot(kabKot);
                        draft.setTxtKabKotID(kabKotID);
                        draft.setTxtKecamatan(Kec);
                        draft.setTxtKecamatanID(kecID);
                        draft.setTxtKelurahan(kel);
                        draft.setTxtKelurahanID(kelID);
                        draft.setTxtPostCode(postCode);
                        draft.setTxtAddress(address);
                        draft.setTxtPhoneNumber(phoneNumber);
                        draft.setIntStatus(0);
                    }
                    draft.setTxtRemarks(remarks);
                    new clsDraftRepo(context).clearAllData();
                    new clsDraftRepo(context).createOrUpdate(draft);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            draftData = new clsDraft();
            draftData.setGuiId(guiid);

            if (boolValidNext) {
                linearLayoutTop.setVisibility(View.GONE);
                linearLayoutBottom.setVisibility(View.VISIBLE);
            }
            try {
                if (draft != null) {
                    List<clsProductDraft> itemsDraft = new clsProductDraftRepo(context).findAll();
                    boolean addedSucces = false;
                    if (draftDelivery) {
                        contentLibs = new ArrayList<VMItems>();
                        for (clsProductDraft productDraft : itemsDraft) {
                            VMItems item = new VMItems(getView());
                            item.setItemName(productDraft.getTxtItemName());
                            item.setItemBrand(productDraft.getTxtItemBrand());
                            item.setItemCode(productDraft.getTxtItemCode());
                            item.setProductCategory(productDraft.getTxtProductCategory());
                            item.setGuiid(productDraft.getTxtProductDraftGUI());
                            item.setPrice(productDraft.getDblPrice());
                            item.setBasePoint(productDraft.getTxtBasedPoint());
                            item.setQty(productDraft.getIntQty());
                            item.setDiscount(productDraft.getDblItemDiscount());
                            item.setTotalPrice(productDraft.getDblTotalPrice());
                            item.setTaxAmount(productDraft.getDblItemTax());
                            item.setNetPrice(productDraft.getDblNetPrice());
                            item.setBonusPoint(productDraft.getTxtBonusPoint());
                            item.setTxtGroupProduct(productDraft.getTxtGroupProduct());
                            item.setItemGroup(productDraft.getTxtGroupItem());
                            item.setBarcode(productDraft.getTxtItemBarcode());
                            item.setIntCampagn(productDraft.getIntCampagn());
                            item.setTxtItemPacketID(productDraft.getTxtItemPacketId());
                            item.setIntItemId(productDraft.getIntItemId());
                            item.setIntPaket(productDraft.getIntBitPaket());
                            if (productDraft.getIntBitPaket() == 1) {
                                item.setDesc(productDraft.getTxtItemPacketId());
                            } else {
                                item.setDesc(productDraft.getTxtItemName());
                            }
                            item.setDectax(decTaxGlobal);
                            item.setTxtKN(productDraft.getTxtKN());
                            item.setTxtGroupKN(productDraft.getTxtGroupKN());
                            boolean booladded = addItem(item);
                            addedSucces = booladded;
                        }


                    } else {
                        if (!boolDraftNull) {
                            ToastCustom.showToasty(context, "Delivery by has changed, the product list has been deleted", 3);
                            new clsProductDraftRepo(context).clearAllData();
                            draft.setBoolWalkinCustomer(walkinCustomer);
                            draft.setBoolPassBy(deliverByPass);
                            draft.setBoolAttachCustomer(attchCust);
                            new clsDraftRepo(context).clearAllData();
                            new clsDraftRepo(context).createOrUpdate(draft);
                            contentLibs = new ArrayList<VMItems>();
                        }

                    }
                    if (addedSucces) {
                        ToastCustom.showToasty(context, "Draft items restored", 3);
                    } else {

                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getDetailItem(List<VMItems> contentSearchResult, int position) {
        VMItems itemSelected = null;
        itemSelected = contentSearchResult.get(position);
        String txtGroupUser = dataLogin.getGrpUser();
        final String txtItemCode = itemSelected.getItemCode();
        final String txtItemDesc = itemSelected.getDesc();
        final String txtItemGroup = itemSelected.getItemGroup();
        int Qty = itemSelected.getQty();
        String intQty = String.valueOf(Qty);
        String txtKontakID = "";
        try {
            draft = new clsDraftRepo(context).findByBitActive();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cbPassBy.isChecked()) {
            txtKontakID = dataLogin.getTxtKontakID();
        } else {
            txtKontakID = draft.getTxtContactID();
        }

        String txtNoLangganan = "";
        String intPeriodeLangganan = "0";
        final String strLinkAPI = new clsHardCode().linkGetPrice;
        final JSONObject resJson = new JSONObject();
        try {
            resJson.put("txtGroupUser", txtGroupUser);
            resJson.put("txtItemCode", txtItemCode);
            resJson.put("txtItemGroup", txtItemGroup);
            resJson.put("intQty", intQty);
            resJson.put("txtKontakID", txtKontakID);
            resJson.put("txtNoLangganan", txtNoLangganan);
            resJson.put("intPeriodeLangganan", intPeriodeLangganan);
            resJson.put("txtCabang", "KALCARE");
            resJson.put("intQty", "1");
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
                JSONObject jsonObjectPrice = null;
                if (response != null) {
                    getActivity().onUserInteraction();
                    try {
                        jsonObjectPrice = new JSONObject(response);
                        int result = jsonObjectPrice.getInt("intResult");
                        String warn = jsonObjectPrice.getString("txtMessage");
                        if (result == 1) {
                            if (!jsonObjectPrice.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObjectPrice.getJSONArray("ListData");
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n).getJSONObject("dtclsProductKHDData");
                                    String txtDiscount = object.getString("decDiscount");
                                    String txtPrice = object.getString("decPrice");
                                    double txtBasePoint = object.getDouble("decBasePoint");
                                    double txtDecBonus = object.getDouble("decBonus");
                                    String txtBrand = object.getString("txtBrand");
                                    String txtGroupProduct = object.getString("txtGroupProduct");

                                    String txtProductCode = object.getString("txtProductCode");
                                    String txtProductDesc = object.getString("txtProductDesc");
                                    String txtProductBarcode = object.getString("txtProductBarcode");
                                    String txtKN = object.getString("txtKN");
                                    String txtGroupKN = object.getString("txtGroupKN");
//                                    String txtUOM = object.getString("txtUOM");
//                                    int intCampagn = object.getInt("intCampaign");
                                    int intPaket = object.getInt("intPaket");
//                                    double decWeight = object.getDouble("decWeight");
                                    double decTax = 10;
                                    if (object.getString("decTax") == null || !object.getString("decTax").equals("null")) {
                                        decTax = object.getDouble("decTax");
                                    }
//                                    intCampagnGlobal = intCampagn;
                                    decTaxGlobal = decTax;
                                    txtBrandGlobal = txtBrand;
                                    txtGroupProductGlobal = txtGroupProduct;
                                    txtDescriptionGlobal = txtProductDesc;
                                    txtBarcodeGlobal = txtProductBarcode;
                                    txtKNGlobal = txtKN;
                                    txtGroupKNGlobal = txtGroupKN;
//                                    txtUOMGlobal = txtUOM ;
                                    intPaketGlobal = intPaket;
//                                    decWeightGlobal = decWeight;
                                    if (txtDiscount.equals("null")) {
                                        etDiscount.setText("0");
                                    } else {
                                        etDiscount.setText(txtDiscount);
                                    }
                                    int intBasedPoin = (int) txtBasePoint;
                                    int intBonusPoin = (int) txtDecBonus;
                                    etPrice.setText(txtPrice);
                                    etBonusPoint.setText(String.valueOf(intBonusPoin));
                                    etBasedPoint.setText(String.valueOf(intBasedPoin));
                                    JSONArray arrListOfPoint = jsn.getJSONObject(n).getJSONArray("ListOfPoint");
                                    for (int m = 0; m < arrListOfPoint.length(); m++) {
                                        JSONObject obj = arrListOfPoint.getJSONObject(n);
                                        int intBitCampaign = obj.getInt("intCampaignId");
                                        String txtGroupItem = obj.getString("txtGroupItem");
                                        intCampagnGlobal = intBitCampaign;
                                        txtGroupItemGlobal = txtGroupItem;

                                    }
                                    /*if(intPaket == 1){
                                        JSONArray arrListOfPoint = jsn.getJSONObject(n).getJSONArray("ListOfPoint");
                                        for (int m = 0; m < arrListOfPoint.length(); m++) {
                                            JSONObject obj = jsn.getJSONObject(n);
                                            double decCalcTotalPrice = obj.getDouble("decCalcTotalPrice");
                                            double decCalcTaxAmount = obj.getDouble("decCalcTaxAmount");
                                            double decCalcNetPrice = obj.getDouble("decCalcNetPrice");
                                            double decCalcTotalBasePoint = obj.getDouble("decCalcTotalBasePoint");
                                            double decCalcTotal = obj.getDouble("decCalcTotal");
                                            String txtNewId = obj.getString("txtNewId");
                                            String txtNoSO = obj.getString("txtNoSO");
                                            String txtProductName = obj.getString("txtProductName");
                                            int intQty = obj.getInt("intQty");
                                            double decPrice = obj.getInt("decPrice");
                                            double decDiscount = obj.getInt("decDiscount");
                                            double decTotalPrice = obj.getInt("decTotalPrice");
                                            double decTaxAmount = obj.getInt("decTaxAmount");
                                            double decNetPrice = obj.getInt("decNetPrice");
                                            double decBasePoint = obj.getInt("decBasePoint");
                                            double decTotalBasePoint = obj.getInt("decTotalBasePoint");
                                            double decBonusPoint = obj.getInt("decBonusPoint");
                                            String txtProductCategory = obj.getString("txtProductCategory");
                                            String txtProductBarcode = obj.getString("txtProductBarcode");
                                            int intBitPaket = obj.getInt("intBitPaket");
                                            int bitActive = obj.getInt("bitActive");
                                        }
                                    }*/
                                    for (VMItems item : contentLibs) {
                                        if (item.getItemCode().equals(txtItemCode) && item.getDesc().equals(txtItemDesc)) {
                                            etQtySearch.setText(String.valueOf(item.getQty()));
                                        }
                                    }
                                }
                                etQtySearch.requestFocus();
                                boolListPaketOrProduct = true;
                            }
                        } else {
                            ToastCustom.showToasty(context, warn, 2);
                            boolListPaketOrProduct = false;
                            etPrice.setText("");
                            etBonusPoint.setText("");
                            etBasedPoint.setText("");
                            etQtySearch.setText("");
                            etDiscount.setText("");
                        }
                    } catch (JSONException e) {
                        String a = e.getMessage();
                    }

                    String a = "";
                }
            }
        });
    }

    private void onclickKeyEnter(EditText et, View v) {
        etSearchOnPopUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    refreshListItemSearch(v);
                    return true;
                }
                return false;
            }
        });
    }

    private void refreshListItemSearch(final View promptView) {
        getActivity().onUserInteraction();
        String strLinkAPISearchOnPopUp = new clsHardCode().linkSearch;
        String keywordOnPopUpSearch = etSearchOnPopUp.getText().toString();
        final JSONObject resJson = new JSONObject();
        try {
            resJson.put("txtProductBarcode", "");
            resJson.put("txtProductCode", "");
            resJson.put("txtProductDesc", keywordOnPopUpSearch);
//            resJson.put("txtRefreshToken", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = resJson.toString();
        new VolleyUtils().makeJsonObjectRequestWithToken(getActivity(), strLinkAPISearchOnPopUp, mRequestBody, access_token, "Please Wait...", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                ToastCustom.showToasty(context, response, 2);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    getActivity().onUserInteraction();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        int result = jsonObject.getInt("intResult");
                        String warn = jsonObject.getString("txtMessage");
                        VMItems item = new VMItems(promptView);
                        contentSearchResult = new ArrayList<VMItems>();
                        if (result == 1) {
                            if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String txtProductCategory = object.getString("txtProductCategory");
                                    String txtBrand = object.getString("txtBrand");
                                    String txtGroupProduct = object.getString("txtGroupProduct");
                                    String txtProductBarcode = object.getString("txtProductBarcode");
                                    String txtProductCode = object.getString("txtProductCode");
                                    HMtxtProductCategory.put(txtProductCode, txtProductCategory);
                                    String txtProductDesc = object.getString("txtProductDesc");
//                                    String decTax = object.getString("decTax");
//                                    double dblTax = 0;
//                                    if(decTax.equals("")){
//                                        dblTax = Double.parseDouble(decTax);
//                                    }
                                    item = new VMItems(promptView);
                                    item.setItemName(txtProductDesc);
                                    item.setGuiid(new Helper().GenerateGuid());
//                                    item.setPrice(80000);
                                    item.setItemGroup(txtGroupProduct);
                                    item.setBarcode(txtProductBarcode);
                                    item.setItemCode(txtProductCode);
                                    item.setDesc(txtProductDesc);
                                    item.setItemBrand(txtBrand);
//                                    item.setTaxAmount(dblTax);
                                    contentSearchResult.add(item);
                                }
                                lvSearchResult.setAdapter(new CardAdapterSearchResult(context, contentSearchResult, Color.WHITE));
                                etDiscount.setText("");
                                etPrice.setText("");
                                etQtySearch.setText("");
                                etBasedPoint.setText("");
                                etBonusPoint.setText("");
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

    @OnClick(R.id.etDeliverySchedule)
    public void onDeliveryScheduleClicked() {
        final String strLinkAPI = new clsHardCode().linkgetNationalHoliday;
        final JSONObject resJson = new JSONObject();
        try {
            resJson.put("year", "");
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
                    getActivity().onUserInteraction();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        int result = jsonObject.getInt("intResult");
                        String warn = jsonObject.getString("txtMessage");
                        if (result == 1) {
                            if (!jsonObject.getString("ListData").equals("null")) {
                                JSONArray jsn = jsonObject.getJSONArray("ListData");
                                List<Calendar> datesCalendar = new ArrayList<>();
                                for (int n = 0; n < jsn.length(); n++) {
                                    JSONObject object = jsn.getJSONObject(n);
                                    String txtDay = object.getString("txtDay");
                                    String dateString = "03/26/2012 11:49:00 AM";
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                                    Date convertedDate = new Date();
                                    try {
                                        convertedDate = dateFormat.parse(txtDay);
                                        Calendar cal = new GregorianCalendar();
                                        cal.setTime(convertedDate);
                                        datesCalendar.add(cal);
                                    } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }


                                Calendar c = Calendar.getInstance();
                                final Bundle args = new Bundle();
                                args.putInt(CustomDatePicker.YEAR, c.get(Calendar.YEAR));
                                args.putInt(CustomDatePicker.MONTH, 0);
                                args.putInt(CustomDatePicker.DAY_OF_MONTH, 1);

                                int max = data.getIntSchedel();

                                c.add(Calendar.DATE, 0);
                                args.putLong(CustomDatePicker.DATE_MIN, c.getTimeInMillis());
//                args.putLong(CustomDatePicker.DATE_MAX, System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                                //4 di bawah bisa di ganti dengan max schedule dari api
                                c.add(Calendar.DATE, max);
                                args.putLong(CustomDatePicker.DATE_MAX, c.getTimeInMillis());
                                OnSelectDateListener ondate = new OnSelectDateListener() {
                                    @Override
                                    public void onSelect(List<Calendar> calendar) {
//            int a = 10;
                                        Calendar calSelected = calendar.get(0);
                                        year = calSelected.get(Calendar.YEAR);
                                        month = calSelected.get(Calendar.MONTH);
                                        day = calSelected.get(Calendar.DATE);
                                        displayDate(etDeliverySchedule, clsDatePicker.format.standard1);
                                        etDeliverySchedule.setEnabled(true);
                                    }

                                };

                                //set hint for date
//        CustomDatePicker.showHint(etDeliverySchedule, args, CustomDatePicker.format.standard0);
//        clsDatePicker.showDatePicker(getActivity(), context, etDeliverySchedule, "Delivery Schedule", args, clsDatePicker.format.standard1, android.R.style.Theme_Holo_Light_Dialog);
                                List<Calendar> dts = getSundaySaturday();
                                dts.addAll(datesCalendar);
                                DatePickerBuilder oneDayBuilder = new DatePickerBuilder(getActivity(), ondate)
                                        .pickerType(CalendarView.ONE_DAY_PICKER)
                                        .date(Calendar.getInstance())
                                        .headerColor(R.color.colorPrimaryDark)
                                        .headerLabelColor(R.color.currentMonthDayColor)
                                        .selectionColor(R.color.daysLabelColor)
//                .todayLabelColor(R.color.colorAccent)
                                        .dialogButtonsColor(android.R.color.holo_green_dark)
//                .disabledDaysLabelsColor(android.R.color.holo_red_light)
                                        .disabledDays(dts)
                                        .disabledDaysLabelsColor(android.R.color.holo_red_light);
//                .previousButtonSrc(R.drawable.ic_chevron_left_black_24dp)
//                .forwardButtonSrc(R.drawable.ic_chevron_right_black_24dp)


                                Calendar a = Calendar.getInstance();
                                Calendar b = Calendar.getInstance();
//                                a.add(Calendar.DATE,-1);
                                b.add(Calendar.DATE, max);
                                int intJumlahHoliday = new Helper().getDatesHolidayBetweenUsingJava7(a, b, datesCalendar);
                                b = Calendar.getInstance();
                                b.add(Calendar.DATE, max + intJumlahHoliday - 1);
                                a = Calendar.getInstance();
                                a.add(Calendar.DATE, -1);
                                oneDayBuilder.minimumDate(a);
                                oneDayBuilder.maximumDate(b);
                                String DATE_MAX = "date_max";
                                String DATE_MIN = "date_min";
                                long maxi = args.getLong(DATE_MAX);
                                long mini = args.getLong(DATE_MIN);
                                DatePicker oneDayPicker = oneDayBuilder.build();
                                oneDayPicker.show();
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


//        CustomDatePicker.showDatePicker(getActivity(), context, etDeliverySchedule, "Delivery Schedule", CustomDatePicker.format.standard0, args);
    }

    public List<Calendar> getSundaySaturday() {
        Calendar calendar = Calendar.getInstance();
        // Note that month is 0-based in calendar, bizarrely.
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        List<Calendar> cs = new ArrayList<>();

        for (int day = 1; day <= daysInMonth; day++) {
            calendar = Calendar.getInstance();
//            calendar.set(2018, 6, day);
            calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            //menghilangkan hari sabtu
            if (dayOfWeek == Calendar.SUNDAY) {
                cs.add(calendar);
                Date a = calendar.getTime();
                String b = formatSimpleDate(a, 2);
                Log.d("Day", b);
                // Or do whatever you need to with the result.
            }
        }
        return cs;
    }

    private static void displayDate(EditText editText, int format) {
        GregorianCalendar c = new GregorianCalendar(year, month, day);
        String date = formatSimpleDate(c.getTime(), format);
        editText.setText(date);
    }

    @OnClick(R.id.etOrderLocation)
    public void onOrderLocationClicked() {

    }

    @Override
    public void onItemClick(View view, int position) {
        String a = "adasd";
        ToastCustom.showToasty(context, "Tst", 2);
    }
}
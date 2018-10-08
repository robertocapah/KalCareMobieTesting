package com.kalbenutritionals.kalcaremobieTesting.Fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalbenutritionals.kalcaremobieTesting.Common.clsUserLogin;
import com.kalbenutritionals.kalcaremobieTesting.R;
import com.kalbenutritionals.kalcaremobieTesting.Repo.clsUserLoginRepo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Robert on 22/06/2017.
 */

public class FragmentInformation extends Fragment {
    View v;
    Context context;
    String gui = "";
    Unbinder unbinder;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.tvNIK)
    TextView tvNIK;
    @BindView(R.id.tvBranchOutlet)
    TextView tvBranchOutlet;
    @BindView(R.id.tvGrpUser)
    TextView tvEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_new, container, false);
        unbinder = ButterKnife.bind(this, v);
        context = getActivity().getApplicationContext();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final clsUserLogin data = new clsUserLoginRepo(context).getDataLogin(context);
        if (data != null){
            tvUsername.setText(data.getTxtTeleName());
            tvNIK.setText(data.getIdUser());
            tvBranchOutlet.setText(data.getTxtNamaInstitusi());
            tvEmail.setText(data.getTxtSumberDataID());
            tvEmail.setVisibility(View.GONE);
        }



        return v;
    }

    public static boolean isMockSettingsON(Context context) {
        // returns true if mock location enabled, false if not enabled.
        if (Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0"))
            return false;
        else
            return true;
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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}


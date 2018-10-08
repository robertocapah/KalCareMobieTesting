package com.kalbenutritionals.kalcaremobieTesting.Data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Base64;
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

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Rian Andrivani on 12/7/2017.
 */

public class VolleyUtils {
    public void makeJsonObjectRequestToken(final Activity activity, String strLinkAPI, final String username, final String password, final String clientId, String progressBarType, final VolleyResponseListener listener) {
        final ProgressDialog Dialog = new ProgressDialog(activity);
        Dialog.setMessage(progressBarType);
        Dialog.setCancelable(false);
        Dialog.show();

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
                String body, message;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {

                    /*
                    // HTTP Status Code: 401 Unauthorized
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                        JSONObject jsonObject = new JSONObject(body);
                        message = jsonObject.getString("Message");
                        Toast.makeText(activity.getApplicationContext(), "Error 401 " + message, Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finalDialog1.dismiss();
                    if (error.getMessage() != null) {
                        listener.onError(error.getMessage());
                    }*/
                } else if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_BAD_REQUEST) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(body);
                        message = jsonObject.getString("error_description");
                        Toast.makeText(activity.getApplicationContext(), "Error 400, " + message, Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finalDialog1.dismiss();
                } else if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Toast.makeText(activity.getApplicationContext(), "Error 500, Server Error", Toast.LENGTH_SHORT).show();
                    finalDialog1.dismiss();
                } else {
                    popup();
                    finalDialog1.dismiss();
                }
            }

            public void popup() {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);

                builder.setTitle("Request Time Out");
                builder.setMessage("You Have to request again");

                builder.setPositiveButton("REFRESH", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("grant_type", "password");
                params.put("username", username);
                params.put("password", password);
                params.put("client_id", clientId);
                return params;
            }
        };
        req.setRetryPolicy(new
                DefaultRetryPolicy(600000, 0,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(req);
    }

    public void requestTokenWithRefresh(final Activity activity, String strLinkAPI, final String refreshToken, final String clientId, final VolleyResponseListener listener) {
        StringRequest req = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body, message;
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(body);
                        message = jsonObject.getString("Message");
                        Toast.makeText(activity.getApplicationContext(), "Error 401, " + message, Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (error.getMessage() != null) {
                        listener.onError(error.getMessage());
                    }
                } else if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_BAD_REQUEST) {
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(body);
                        message = jsonObject.optString("error_description");
                        if (message.equals("")) {
                            message = jsonObject.optString("error");
                        }
                        Toast.makeText(activity.getApplicationContext(), "Error 401, " + message, Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Toast.makeText(activity.getApplicationContext(), "Error 500, Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity.getApplicationContext(), "Something Error, please request again", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("grant_type", "refresh_token");
                params.put("client_id", clientId);
                params.put("refresh_token", refreshToken);
                return params;
            }
        };
        req.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(req);
    }


    //dari kalbe family

    public void makeJsonObjectRequestWithToken(final Activity activity, String strLinkAPI, final String mRequestBody, final String tokenVolley, String progressBarType, final VolleyResponseListener listener) {
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
//                    if (error.getMessage() != null) {
                    listener.onError(error.getMessage());
//                    }
                    ToastCustom.showToasty(activity.getApplicationContext(), "Failed, Check your network", 3);
                } else {
//                    popup();
                    ToastCustom.showToasty(activity.getApplicationContext(), "Failed, Check your network", 3);
                    finalDialog.dismiss();
                    listener.onError(error.getMessage());
//                    activity.startActivity(new Intent(activity, LoginActivity.class));
                }
            }
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
        req.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        queue.add(req);
    }

    public void makeJsonObjectRequestWithTokenWithoutProgressD(final Activity activity, String strLinkAPI, final String mRequestBody, final String tokenVolley, String progressBarType, final VolleyResponseListener listener) {
        /*ProgressDialog Dialog = new ProgressDialog(activity);
        Dialog = ProgressDialog.show(activity, "",
                progressBarType, false);
        final ProgressDialog finalDialog = Dialog;
        final ProgressDialog finalDialog1 = Dialog;*/

        StringRequest req = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
//                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
//                    new clsActivity().showCustomToast(activity.getApplicationContext(), "401", false);
//                    finalDialog1.dismiss();
                    if (error.getMessage() != null) {
                        listener.onError(error.getMessage());
                    }
                } else {
//                    popup();
//                    finalDialog1.dismiss();
                    ToastCustom.showToasty(activity.getApplicationContext(), "Error Connection", 2);
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
        req.setShouldCache(false);
        queue.add(req);
    }

    public void makeJsonObjectRequestWithTokenWithoutProgressDService(Activity activity,String strLinkAPI, final String mRequestBody, final String tokenVolley, String progressBarType, final VolleyResponseListener listener) {

        StringRequest req = new StringRequest(Request.Method.POST, strLinkAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = false;
                String errorMessage = null;
                listener.onResponse(response, status, errorMessage);
//                finalDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    listener.onError(error.getMessage());
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("txtParam", mRequestBody);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

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
        req.setShouldCache(false);
        queue.add(req);
    }
}

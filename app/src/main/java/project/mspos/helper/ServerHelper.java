package project.mspos.helper;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import project.mspos.activity.MainActivity;
import project.mspos.app.AppController;
import project.mspos.entity.AddressEntity;
import project.mspos.entity.UserEntity;
import project.mspos.utils.Const;


public class ServerHelper {
    UserEntity userEntity;
    public static final String TAG = "User";
    String username = null;
    String password = null;
    String webUrl = null;
    String keyName = null;
    String subKeyName = null;
    int check = 0;
    public ServerHelper() {}

    public void getAccountToServer(final DatabaseHelper databaseHelper) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_SERVER, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        try {
                            // Parsing json object response
                            // response will be a json object
                            databaseHelper.deleteAllAccount();//Delete all data
                            Log.i("Account", response.length()+"");
                            for (int i = 1; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject("data");
                                webUrl = data.getString("demo_url");
                                username = data.getString("demo_user");
                                password = data.getString("demo_pass");
                                databaseHelper.addAccount(webUrl, username, password);
                                Const.REQUEST_URL = webUrl;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "getDemoData");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_ACCOUNT);
    }
    public void getUserToServer(final DatabaseHelper databaseHelper) {
        Log.i("Request url", "Xin chao");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    Const.REQUEST_URL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Parsing json object response
                                // response will be a json object
                                databaseHelper.deleteAllUser();//Delete all data

                                JSONObject data = response.getJSONObject("data");
                                String session = data.getString("session");
                                Const.SESSION = session;//Khoi tao session moi
                                JSONObject userInfo = data.getJSONObject("user_info");
                                int userId = Integer.parseInt(userInfo.getString("user_id"));
                                String username = userInfo.getString("username");
                                String passwordUser = userInfo.getString("password");
                                String displayName = userInfo.getString("display_name");
                                String email = userInfo.getString("email");
                                databaseHelper.addUser(userId, username, passwordUser, displayName, email, session);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.i(TAG, e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, error.toString());
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            }) {

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded";
                }

                @Override
                public byte[] getBody() {
                    // init parameters
                    Map<String, String> params = new HashMap<>();
                    params.put("method", "login");
                    params.put("username", "demo");
                    params.put("password", "demo1234");
                    // encode parameters (can use Uri.Builder as above)
                    String paramsEncoding = "UTF-8";
                    StringBuilder encodedParams = new StringBuilder();
                    try {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                            encodedParams.append('=');
                            encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                            encodedParams.append('&');
                        }
                        return encodedParams.toString().getBytes(paramsEncoding);
                    } catch (UnsupportedEncodingException uee) {
                        throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                    }
                }
            };
            jsonObjReq.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return Const.TIME_OUT;
                }

                @Override
                public int getCurrentRetryCount() {
                    return Const.TIME_OUT;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_USER);
    }
    public void getCategoryToServer(final DatabaseHelper databaseHelper) {
        Log.i("getCategoryToServer","getCategoryToServer");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        try {
                            // Parsing json object response
                            // response will be a json object
                            databaseHelper.deleteAllCategories();//Delete all data

                            JSONObject data = response.getJSONObject("data");
                            databaseHelper.addCategory(2,data.getString("name"),Integer.parseInt(data.getString("level")),1);
                            Iterator keys = data.keys();

                            while(keys.hasNext()) {
                                // loop to get the dynamic key
                                keyName = (String)keys.next();
                                if (!keyName.equals("name") && !keyName.equals("level") && !keyName.equals("root") ) {
                                    JSONObject category = data.getJSONObject(keyName);
                                    databaseHelper.addCategory(Integer.parseInt(keyName),category.getString("name"),2,2);
                                    Iterator keyCate = category.keys();
                                    while(keyCate.hasNext()) {
                                        subKeyName = (String)keyCate.next();
                                        if (!subKeyName.equals("name") && !subKeyName.equals("level") ) {
                                            JSONObject subCategory = category.getJSONObject(subKeyName);
                                            databaseHelper.addCategory(Integer.parseInt(subKeyName), subCategory.getString("name"), 3, Integer.parseInt(keyName));
                                        }
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "category.tree");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_CATEGORY);
    }
    public void getAllProductToServer(final DatabaseHelper databaseHelper) {
        Log.i("getCategoryToServer","getCategoryToServer");


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        try {
                            // Parsing json object response
                            // response will be a json object
                            float price = 0;
                            float finalPrice = 0;
                            databaseHelper.deleteAllProducts();//Delete all data
                            JSONObject dataProducts = response.getJSONObject("data");
                            Iterator keys = dataProducts.keys();
                            while(keys.hasNext()) {
                                // loop to get the dynamic key
                                String keyName = (String)keys.next();
                                if (!keyName.equals("total")) {
                                    JSONObject product = dataProducts.getJSONObject(keyName);
                                    JSONObject productDetail = product.getJSONObject("detail");
                                    if (productDetail.getString("price").equals("null")) {
                                        price = 0;
                                    }
                                    else
                                        price = Float.parseFloat(productDetail.getString("price"));
                                    finalPrice = Float.parseFloat(productDetail.getString("final_price"));
                                    databaseHelper.addProducts(
                                            Integer.parseInt(keyName),
                                            product.getString("name"),
                                            product.getString("sku"),
                                            product.getString("image"),
                                            Integer.parseInt(productDetail.getString("qty")),
                                            price,
                                            Boolean.parseBoolean(productDetail.getString("has_options")),
                                            Boolean.parseBoolean(productDetail.getString("is_salable")),
                                            Boolean.parseBoolean(productDetail.getString("is_available")),
                                            productDetail.getString("short_description"),
                                            finalPrice,
                                            product.getJSONArray("cat_ids").toString() ,
                                            productDetail.getJSONArray("additional").toString(),
                                            productDetail.getJSONArray("images").toString(),
                                            productDetail.getString("description")
                                    );
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("params", "[{},1,120]");

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "product.list");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_PRODUCTS);
    }
    public void getAllCustomersToServer(final DatabaseHelper databaseHelper) {
        Log.i("getCustomerToServer","getCustomerToServer");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            databaseHelper.deleteAllCustomers();//Delete all data

                            JSONObject data = response.getJSONObject("data");
                            Iterator keys = data.keys();
                            while(keys.hasNext()) {
                                keyName = (String)keys.next();
                                if (!keyName.equals("total") && !keyName.equals("customer_default_id")) {
                                    JSONObject customer = data.getJSONObject(keyName);

                                    databaseHelper.addCustomer(Integer.parseInt(keyName.toString()), customer.getString("name"), customer.getString("email"), customer.getString("telephone"), Integer.parseInt(customer.getString("group_id")));
                                    Thread background = new Thread(new Runnable() {
                                        public void run() {
                                            try {
                                                getAddress(databaseHelper, keyName.toString());
                                            } catch (Throwable t) {}
                                        }
                                    });
                                    background.start();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "customer.search");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_CUSTOMERS);
    }
    public void getAddress(final DatabaseHelper databaseHelper,final String customerId) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("customerid", customerId+"");
                            JSONObject data = response.getJSONObject("data");
                            //(int id, int active, String prefix,String firstName,String middleName,String lastName,
                           // String suffix, String company, String city, String countryId, String region, String postCode,
                            //        String fax, String vatId, String regionId, String street, int customerId)
                            int id = Integer.parseInt(data.getString("id"));
                            int active = Integer.parseInt(data.getString("is_active"));
                            String firstName = data.getString("firstname");
                            String lastName = data.getString("lastname");
                            String city = data.getString("city");
                            String countryId = data.getString("country_id");
                            String telephone = data.getString("telephone");
                            String street = data.getString("street");
                            int customerId = Integer.parseInt(data.getString("customer_id"));
                            databaseHelper.addAddress(id, active, firstName, lastName, city, countryId, telephone, street, customerId);
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "customer_address.address");
                params.put("session", Const.SESSION);
                params.put("params",customerId);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_CUSTOMERS);
    }
    public void addProductToCartOnServer(final DatabaseHelper databaseHelper,final int productId) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("ADD PRODUCT TO CART", response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ADD PRODUCT TO CART", error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "checkout_product.add");
                params.put("session", Const.SESSION);
                params.put("params","[{\"id\":\""+productId+"\"}]");
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_CUSTOMERS);
    }
    public void setAddress(final DatabaseHelper databaseHelper,final String address) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("params", address);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "checkout_customer.setAddress");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_SET_ADDRESS);
    }
    public void setPaymentMethod(final DatabaseHelper databaseHelper) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "checkout_payment.setMethod");
                params.put("session", Const.SESSION);
                params.put("params","[{\"method\":\"multipaymentforpos\"}]");
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_SET_PAYMENT);
    }
    public void setShippingMethod(final DatabaseHelper databaseHelper) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "checkout_shipping.setMethod");
                params.put("session", Const.SESSION);
                params.put("params", "[\"flatrate_flatrate\"]");
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_SET_SHIPPING);
    }
    public int createOrder(final DatabaseHelper databaseHelper) {
        check = 0;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.REQUEST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            check = Integer.parseInt(response.getString("success"));
                            if (check ==1)
                                MainActivity.orderId = Integer.parseInt(data.getString("order"));
                            else
                                MainActivity.orderId = -1;
                            Log.i("CREATE ORDER",response.toString());
                        } catch (JSONException e) {
                            MainActivity.orderId = -1;
                            e.printStackTrace();
                            Log.i(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("params", "shipped");

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                // init parameters
                Map<String, String> params = new HashMap<>();
                params.put("method", "checkout_cart.createOrder");
                params.put("session", Const.SESSION);
                // encode parameters (can use Uri.Builder as above)
                String paramsEncoding = "UTF-8";
                StringBuilder encodedParams = new StringBuilder();
                try {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                    return encodedParams.toString().getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException uee) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
                }
            }
        };
        jsonObjReq.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return Const.TIME_OUT;
            }

            @Override
            public int getCurrentRetryCount() {
                return Const.TIME_OUT;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, Const.REQUEST_CREATE_ORDER);

        return check;
    }
}

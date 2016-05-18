package project.mspos.utils;

import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

import project.mspos.adapter.GridViewProductAdapter;

/**
 * Created by CONGHAO on 4/24/2016.
 */
public class Const {

    public static String CURRENCY = "USD";
    //FF
    public static RecyclerView recyclerViewProduct;
    public static GridViewProductAdapter rcAdapter;
    //
    //Request server
    public static String REQUEST_SERVER = "http://www.magestore.com/posmanagement/api";
    public static String REQUEST_URL = "http://demo.magestore.com/retailerpos/webpos/api/";
    public static String SESSION = "f491cf08304aee5dd93b264b7e65f7bf";
    public static String USERNAME = null;
    public static String PASSWORD = null;
    public static final int TIME_OUT = 50000;

    public static final String REQUEST_USER = "getUser";
    public static final String REQUEST_ACCOUNT = "getAccount";
    public static final String REQUEST_CATEGORY = "getAccount";
    public static final String REQUEST_PRODUCTS = "getProducts";
    public static final String REQUEST_CUSTOMERS = "getCustomers";
    public static final String REQUEST_SET_SHIPPING = "setShippingMethod";
    public static final String REQUEST_CREATE_ORDER = "createOrder";
    public static final String REQUEST_SET_ADDRESS = "setAddress";
    public static final String REQUEST_SET_PAYMENT = "setPayment";

    public static final int GIRD_VIEW_PRODUCT = 1;
    public static final int LIST_PRODUCT_IN_CART = 2;

    public static final String SDCARD_DIR_NAME = "MsPos";

    //Get category
    public static HashMap<Integer, String> mapCategory = new HashMap<Integer, String>();
    //Create Data base

    // Logcat tag
    public static final String LOG = "DatabaseHelper";
    // Database Version
    public static int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "msPos";

    //Table Name
    public static final String TABLE_ACCOUNT = "account";
    public static final String TABLE_USER = "user";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_CUSTOMERS = "customers";
    public static final String TABLE_ADDRESS = "address";
    public static final String TABLE_ORDER = "order";
    public static final String TABLE_DAILY_REPORT = "daily_report";

    public static final String TABLE_PRODUCT_PRODUCT = "product";
    public static final String TABLE_PRODUCT_IMAGE = "product_image";
    public static final String TABLE_DISCOUNT = "discount";


    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";

    // Account Table - column names
    public static final String KEY_USER = "demo_user";
    public static final String KEY_PASS = "demo_pass";
    public static final String KEY_URL = "demo_url";

    //User table

    public static final String KEY_EMAIL = "email";
    public static final String KEY_DISPLAY_NAME = "display_name";
    public static final String KEY_SESSION = "session";

    //Category table
    public static final String KEY_NAME = "name";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_PARENT = "parent";

    //Products table
    public static final String KEY_QTY = "qty";
    public static final String KEY_SKU = "sku";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_HAS_OPTIONS = "has_options";
    public static final String KEY_IS_SALABLE = "is_salable";
    public static final String KEY_IS_AVAILABLE = "is_available";
    public static final String KEY_SHORT_DESCRIPTION = "short_description";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_FINAL_PRICE = "final_price";
    public static final String KEY_CATE_ID = "cate_id";
    public static final String KEY_IMAGES = "images";
    public static final String KEY_ADDITIONAL = "additional";


    //Address table
    public static final String KEY_INCREMENT_ID = "increment_id";
    public static final String KEY_IS_ACTIVE = "is_active";
    public static final String KEY_PRE_FIX = "prefix";
    public static final String KEY_FIRST_NAME = "firstname";
    public static final String KEY_MIDDLE_NAME = "middlename";
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_SUFFIX = "suffix";
    public static final String KEY_COMPANY = "company";
    public static final String KEY_CITY = "city";
    public static final String KEY_COUNTRY_ID = "country_id";
    public static final String KEY_REGION = "region";
    public static final String KEY_POST_CODE = "postcode";
    public static final String KEY_FAX = "fax";
    public static final String KEY_VAT_ID = "vat_id";
    public static final String KEY_REGION_ID = "region_id";
    public static final String KEY_STREET = "street";
    public static final String KEY_CUSTOMER_ID = "customer_id";

    //Address
    public static final String KEY_STATUS = "status";
    public static final String KEY_CREATE_AT = "created_at";
    public static final String KEY_CUSTOMER_NAME = "customer_name";
    public static final String KEY_GRAND_TOTAL = "grand_total";
    public static final String KEY_WEBPOS_USER = "webpos_user";
    public static final String KEY_WEBPOS_EMAIL = "webpos_email";
    public static final String KEY_PRODUCT_SKUS = "product_skus";
    public static final String KEY_CAN_CANCEL = "can_cancel";
    public static final String KEY_CAN_INVOICE = "can_invoice";
    public static final String KEY_CAN_SHIP = "can_ship";
    public static final String KEY_CAN_REFUND = "can_ship";

    //Daily Report
    public static final String KEY_CASH_DRAWER = "cash_drawer";
    public static final String KEY_CASHIER_NAME = "cashier_name";
    public static final String KEY_STORE_NAME = "store_name";
    public static final String KEY_BILLING_NAME = "billing_name";

    //Customer
    public static final String KEY_GROUP_ID = "group_id";
    public static final String KEY_TELEPHONE = "telephone";
    // Table Create Statements
    // Todo table create statement
    public static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER
            + " TEXT," + KEY_PASS + " TEXT," + KEY_URL + " TEXT"+")";

    //User
    public static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER," + KEY_USERNAME
            + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_EMAIL + " TEXT," + KEY_DISPLAY_NAME + " TEXT," + KEY_SESSION + " TEXT" +")";
    //Category
    public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY + "(" + KEY_ID + " INTEGER," + KEY_NAME
            + " TEXT," + KEY_LEVEL + " INTEGER," + KEY_PARENT + " INTEGER" +")";
    //Products
    public static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "
            + TABLE_PRODUCTS + "(" + KEY_ID + " INTEGER," + KEY_NAME+ " TEXT," + KEY_SKU + " TEXT," + KEY_QTY + " INTEGER,"
            + KEY_IMAGE+ " TEXT,"+ KEY_HAS_OPTIONS+ " BOOLEAN,"+ KEY_IS_SALABLE+ " BOOLEAN,"+ KEY_IS_AVAILABLE+ " BOOLEAN,"+ KEY_SHORT_DESCRIPTION+ " TEXT,"
            + KEY_PRICE+ " FLOAT,"+ KEY_FINAL_PRICE + " FLOAT,"+KEY_IMAGES + " TEXT,"+KEY_ADDITIONAL + " TEXT,"+KEY_DESCRIPTION + " TEXT,"+ KEY_CATE_ID+ " TEXT"+")";

    //Customer
    public static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE "
            + TABLE_CUSTOMERS + "(" +
            KEY_ID + " INTEGER," + KEY_NAME+ " TEXT," +
            KEY_EMAIL + " TEXT,"+ KEY_TELEPHONE + " TEXT," +
            KEY_GROUP_ID + " INTEGER" +
            ")";


    //Address
    public static final String CREATE_TABLE_ADDRESS =
            "CREATE TABLE "+ TABLE_ADDRESS + "(" +
            KEY_ID + " INTEGER," +
            KEY_IS_ACTIVE + " INTEGER," + KEY_PRE_FIX + " TEXT,"+
            KEY_FIRST_NAME + " TEXT," + KEY_MIDDLE_NAME + " TEXT,"+
            KEY_LAST_NAME + " TEXT,"+ KEY_SUFFIX + " TEXT,"+
            KEY_COMPANY + " TEXT,"+ KEY_CITY + " TEXT,"+
            KEY_COUNTRY_ID + " TEXT,"+ KEY_REGION + " TEXT,"+
            KEY_POST_CODE + " TEXT,"+ KEY_FAX + " TEXT,"+
            KEY_VAT_ID + " TEXT,"+ KEY_REGION_ID + " TEXT,"+
            KEY_STREET + " TEXT,"+ KEY_CUSTOMER_ID + " INTEGER"+
            ")";



    //Order
    public static final String CREATE_TABLE_ORDER =
            "CREATE TABLE "+TABLE_ORDER + "(" +
            KEY_INCREMENT_ID + " TEXT," + KEY_STATUS + " TEXT," +
            KEY_CREATE_AT + " TEXT,"+ KEY_CUSTOMER_ID + " TEXT," +
            KEY_CUSTOMER_NAME + " TEXT,"+ KEY_GRAND_TOTAL + " TEXT,"+
            KEY_WEBPOS_USER + " TEXT,"+ KEY_WEBPOS_EMAIL + " TEXT,"+
            KEY_PRODUCT_SKUS + " TEXT,"+ KEY_CAN_CANCEL + " TEXT,"+
            KEY_CAN_INVOICE + " TEXT,"+ KEY_CAN_SHIP + " TEXT,"+
            KEY_CAN_REFUND + " TEXT"+
            ")";

    //Daily Report
    public static final String CREATE_TABLE_DAILY_REPORT =
            "CREATE TABLE "+TABLE_DAILY_REPORT + "(" +
            KEY_INCREMENT_ID + " TEXT," +
            KEY_CASH_DRAWER + " TEXT," + KEY_CREATE_AT + " TEXT,"+
            KEY_CASHIER_NAME + " TEXT," + KEY_STORE_NAME + " TEXT,"+
            KEY_GRAND_TOTAL + " TEXT,"+ KEY_BILLING_NAME + " TEXT,"+
            KEY_STATUS + " TEXT"+
            ")";

}

package project.mspos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.mspos.entity.AccountEntity;
import project.mspos.entity.CategoryEntity;
import project.mspos.entity.CustomerEntity;
import project.mspos.entity.ProductEntity;
import project.mspos.entity.UserEntity;
import project.mspos.utils.Const;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "User";
    public DatabaseHelper(Context context) {
        super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Account table
        db.execSQL(Const.CREATE_TABLE_ACCOUNT);

        //User table
        db.execSQL(Const.CREATE_TABLE_USER);

        //Category table
        db.execSQL(Const.CREATE_TABLE_CATEGORY);

        //Products table
        db.execSQL(Const.CREATE_TABLE_PRODUCTS);

        //Customer table
        db.execSQL(Const.CREATE_TABLE_CUSTOMER);

        //Address table
        db.execSQL(Const.CREATE_TABLE_ADDRESS);

        //Order table
       // db.execSQL(Const.CREATE_TABLE_ORDER);

        //Daily report table
        db.execSQL(Const.CREATE_TABLE_DAILY_REPORT);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + Const.CREATE_TABLE_ACCOUNT);
        // Create tables again
        onCreate(db);
    }

    /**
     * Table Account
     * Luu link website
     * Tai khoan tren duoc cap cho trang web tren trang magestore
     *
     * */
    // Adding new contact
    void addAccount(String url, String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Const.KEY_URL, url);
        values.put(Const.KEY_USER, username);
        values.put(Const.KEY_PASS, password);


        // Inserting Row
        db.insert(Const.TABLE_ACCOUNT, null, values);
        db.close(); // Closing database connection
    }

    //Getting single contact from username
    public AccountEntity getAccount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        AccountEntity userEntity;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Const.CREATE_TABLE_ACCOUNT + " WHERE username = '" + username+"'",null);
        cursor.moveToFirst();
        Log.i("DatabaseHelper","getUser"+cursor.getCount()+"");
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        else
            return null;

        userEntity = new AccountEntity(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return userEntity;
    }

    public void deleteAllAccount() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_ACCOUNT, null,null);
    }
    //End Table Account

    /**
     * Table User
     * Luu tru thong tin tai khoan webPos va session
     *
     * */

    // Adding new contact
    void addUser(int id, String username, String password, String displayName, String email, String session) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.KEY_ID, id);
        values.put(Const.KEY_PASSWORD, password);
        values.put(Const.KEY_USERNAME, username);
        values.put(Const.KEY_EMAIL, email);
        values.put(Const.KEY_DISPLAY_NAME, displayName);
        values.put(Const.KEY_SESSION, session);
        // Inserting Row
        db.insert(Const.TABLE_USER, null, values);
        db.close(); // Closing database connection
    }
    // Getting single contact
    public UserEntity getUser(int id) {
        UserEntity userEntity;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Const.CREATE_TABLE_ACCOUNT, new String[] { Const.KEY_ID,
                        Const.KEY_USER, Const.KEY_PASS, Const.KEY_URL }, Const.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;

        userEntity = new UserEntity(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return user
        return userEntity;
    }
    public boolean checkUserToSqlite(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Const.TABLE_ACCOUNT + " WHERE demo_user = '" + username+"'" + "AND demo_pass = '" + password +"'",null);

        if (cursor.getCount() >0) {
            //cursor.moveToFirst();
            cursor.moveToPosition(0);
            cursor.toString();
            Const.USERNAME = cursor.getString(1);
            Const.PASSWORD = cursor.getString(2);
            Const.REQUEST_URL = cursor.getString(3);
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    //Getting single contact from username
    public UserEntity getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        UserEntity userEntity;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Const.CREATE_TABLE_ACCOUNT + " WHERE username = '" + username+"'" + "password = '" + password +"'",null);
        Log.i("DatabaseHelper","getUser"+cursor.getCount()+"");
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        else
            return null;

        userEntity = new UserEntity(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
        return userEntity;
    }
    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_USER, null,null);
    }
    //End Table User

    //Table Category
    // Adding new contact
    void addCategory(int id, String name, int level, int parent) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Const.KEY_ID, id);
        values.put(Const.KEY_NAME,  name);
        values.put(Const.KEY_LEVEL, level);
        values.put(Const.KEY_PARENT, parent);

        // Inserting Row
        db.insert(Const.TABLE_CATEGORY, null, values);
        db.close(); // Closing database connection
    }
    public void deleteAllCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_CATEGORY, null,null);
    }

    // Getting All Categories
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setId(Integer.parseInt(cursor.getString(0)));
                categoryEntity.setName(cursor.getString(1));
                categoryEntity.setLevel(Integer.parseInt(cursor.getString(2)));
                categoryEntity.setParent(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                categoryList.add(categoryEntity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return categoryList;
    }
    //End Category

    //Products table
    void addProducts(int id, String name, String sku, String image, int qty, float price, boolean hasOptions, boolean isSalable, boolean isAvailable, String shortDescription, float finalPrice, String cateId, String additional, String images, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Const.KEY_ID, id);
        values.put(Const.KEY_NAME,  name);
        values.put(Const.KEY_SKU, sku);
        values.put(Const.KEY_IMAGE, image);
        values.put(Const.KEY_QTY, qty);
        values.put(Const.KEY_PRICE, price);
        values.put(Const.KEY_FINAL_PRICE, finalPrice);
        values.put(Const.KEY_HAS_OPTIONS, hasOptions);
        values.put(Const.KEY_IS_AVAILABLE, isAvailable);
        values.put(Const.KEY_IS_SALABLE, isSalable);
        values.put(Const.KEY_SHORT_DESCRIPTION, shortDescription);
        values.put(Const.KEY_CATE_ID, cateId);
        values.put(Const.KEY_ADDITIONAL, additional);
        values.put(Const.KEY_IMAGES, images);
        values.put(Const.KEY_DESCRIPTION, description);
        // Inserting Row
        db.insert(Const.TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }
    public List<ProductEntity> getAllProducts(int categoryId) {
        List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Const.TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(Integer.parseInt(cursor.getString(0)));
                productEntity.setName(cursor.getString(1));
                productEntity.setSku(cursor.getString(2));
                productEntity.setQty(Integer.parseInt(cursor.getString(3)));
                productEntity.setImage(cursor.getString(4));
                productEntity.setHasOptions(Boolean.parseBoolean(cursor.getString(5)));
                productEntity.setSalable(Boolean.parseBoolean(cursor.getString(6)));
                productEntity.setAvailable(Boolean.parseBoolean(cursor.getString(7)));
                productEntity.setShortDescription(cursor.getString(8));
                if (cursor.getString(9).equals("null"))
                    productEntity.setPrice(0);
                else
                    productEntity.setPrice(Float.parseFloat(cursor.getString(9)));
                productEntity.setFinalPrice(Float.parseFloat(cursor.getString(10)));
                productEntity.setCateId(cursor.getString(11));
                // Adding contact to list
                productEntityList.add(productEntity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productEntityList;
    }
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Const.TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(Integer.parseInt(cursor.getString(0)));
                productEntity.setName(cursor.getString(1));
                productEntity.setSku(cursor.getString(2));
                productEntity.setQty(Integer.parseInt(cursor.getString(3)));
                productEntity.setImage(cursor.getString(4));
                productEntity.setHasOptions(Boolean.parseBoolean(cursor.getString(5)));
                productEntity.setSalable(Boolean.parseBoolean(cursor.getString(6)));
                productEntity.setAvailable(Boolean.parseBoolean(cursor.getString(7)));
                productEntity.setShortDescription(cursor.getString(8));
                if (cursor.getString(9).equals("null"))
                    productEntity.setPrice(0);
                else
                    productEntity.setPrice(Float.parseFloat(cursor.getString(9)));
                productEntity.setFinalPrice(Float.parseFloat(cursor.getString(10)));
                productEntity.setCateId(cursor.getString(11));
                // Adding contact to list
                productEntityList.add(productEntity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productEntityList;
    }
    public void deleteAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_PRODUCTS, null,null);
    }
    //End Product

    //Table Customers
    // Adding new Customer
    public void addCustomer(int id, String name, String email, String telephone, int groupId) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.KEY_ID, id);
        values.put(Const.KEY_NAME,  name);
        values.put(Const.KEY_EMAIL, email);
        values.put(Const.KEY_GROUP_ID, groupId);
        values.put(Const.KEY_TELEPHONE, telephone);
        // Inserting Row
        db.insert(Const.TABLE_CUSTOMERS, null, values);
        db.close(); // Closing database connection
    }
    // Getting All Customers
    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> customerEntities = new ArrayList<CustomerEntity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Const.TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(Integer.parseInt(cursor.getString(0)));
                customerEntity.setName(cursor.getString(1));
                customerEntity.setEmail(cursor.getString(2));
                customerEntity.setPhone(cursor.getString(3));
                customerEntity.setGroupId(Integer.parseInt(cursor.getString(4)));
                // Adding contact to list
                customerEntities.add(customerEntity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return customerEntities;
    }
    public void deleteAllCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_CUSTOMERS, null,null);
    }
    //End Customer

    //Table Address

    //Save address
    public void addAddress(int id, int active,String firstName,String lastName,
                           String city, String countryId,
                           String vatId, String street, int customerId) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Const.KEY_ID, id);
        values.put(Const.KEY_IS_ACTIVE,  active);
        values.put(Const.KEY_FIRST_NAME, firstName);
        values.put(Const.KEY_LAST_NAME, lastName);
        values.put(Const.KEY_LAST_NAME, lastName);
        values.put(Const.KEY_CITY, city);
        values.put(Const.KEY_COUNTRY_ID, countryId);
        values.put(Const.KEY_VAT_ID, vatId);
        values.put(Const.KEY_STREET, street);
        values.put(Const.KEY_CUSTOMER_ID, customerId);
        // Inserting Row
        db.insert(Const.TABLE_ADDRESS, null, values);
        db.close(); // Closing database connection
    }
    //End save

    //Create new address

    //End Create

    //Delete all address
    public void deleteAllAddress() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.TABLE_ADDRESS, null,null);
    }
    //End Delete
}
package project.mspos.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;

import project.mspos.R;
import project.mspos.adapter.GridViewProductAdapter;
import project.mspos.adapter.ListCategoryAdapter;
import project.mspos.adapter.ListProductBoughtAdapter;
import project.mspos.adapter.NavDrawerListAdapter;
import project.mspos.entity.AddressEntity;
import project.mspos.entity.CategoryEntity;
import project.mspos.entity.CustomSale;
import project.mspos.entity.CustomerEntity;
import project.mspos.entity.DiscountEntity;
import project.mspos.entity.ProductEntity;
import project.mspos.fragment.CustomDiscountDialogFragment;
import project.mspos.fragment.CustomSaleFragment;
import project.mspos.fragment.CustomerInfoDialogFragment;
import project.mspos.fragment.GridViewProductFragment;
import project.mspos.fragment.ListCategoryFragment;
import project.mspos.fragment.OrderCartFragment;
import project.mspos.fragment.OrderCartPlaceFragment;
import project.mspos.fragment.PlaceOrderBackFragment;
import project.mspos.fragment.PlaceOrderFragment;
import project.mspos.fragment.SplashFragment;
import project.mspos.helper.DatabaseHelper;
import project.mspos.helper.ServerHelper;
import project.mspos.model.NavDrawerItem;
import project.mspos.utils.Const;

public class MainActivity extends AppCompatActivity implements GridViewProductFragment.AddCustomSaleInterface,
        GridViewProductFragment.AddProductInCartInterface, OrderCartFragment.CheckoutCart,
        PlaceOrderFragment.PlaceOrder,CustomSaleFragment.AddCustomSaleToCartInterface,
        ListCategoryFragment.GetProductsCategoryInterface,GridViewProductFragment.GetProducts,
        ListProductBoughtAdapter.DeleteProductInCartInterface, OrderCartFragment.OpenDialogCustomer,
        GridViewProductFragment.BackNavigation, CustomerInfoDialogFragment.CreateNewCustomer,
        OrderCartPlaceFragment.BackStackOption, OrderCartPlaceFragment.AddCustomerToCart, PlaceOrderBackFragment.StartNewOrder,
OrderCartFragment.OpenDialogCustomDiscountInterface,CustomDiscountDialogFragment.ApplyDiscountInterface{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DatabaseHelper databaseHelper;
    private ServerHelper serverHelper;
    private List<CategoryEntity> categoryEntityList;
    private List<ListCategoryAdapter.CategoriesListItem> items;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence mTitle;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private Toolbar mToolbar;
    private TextView category;
    private SearchView searchView;
    private SearchManager searchManager;
    private ImageView btnHamburger, btnBackOption;
    private FragmentManager fragmentManager;
    public static List<ProductEntity> listProductInCart;
    public static float subTotal = 0;
    public static float discount = 0;
    public static float tax = 0;
    public static float grandTotal = 0;

    private OrderCartFragment orderCartFragment;
    private OrderCartPlaceFragment orderCartPlaceFragment;
    private PlaceOrderFragment placeOrderFragment;
    private PlaceOrderBackFragment placeOrderbackFragment;
    private FrameLayout layout_left, layout_right;
    private ImageView imgDeleteCart, imgCommentCart;
    private TextView tvProductInCart;
    private LinearLayout toolbarLeft, layoutToolbar;
    private View view;
    private GridViewProductFragment gridViewProductFragment;
    private List<ProductEntity> productCategoryList;
    private List<ProductEntity> allProducts;
    private List<ProductEntity> productCategorySearch;
    public static ArrayList<DiscountEntity> listDiscount;

    private AddressEntity addressEntity;
    public static final int NO_CUSTOMER=-1;
    public static List<CustomerEntity> listCustomer;
    public static String customerName = "Add Customer";
    public static int orderId = 0;
    public static CustomerEntity customerInCart;
    public static AddressEntity customerAddressInCart;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CustomSaleFragment customSaleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = View.inflate(this, R.layout.activity_main, null);
        fragmentManager = getFragmentManager();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        //Add tool bar
        setupView();
       // addSearchView();
        setupUI(view);
        //Add Slide menu
        addSliderMenu(savedInstanceState);
        //Add gird view product
        addListCategoryFragment();
        //Add list product in cart
        addOrderCartFragment();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //Get customer
        setCustomersData();
       // setDefaultData();
    }
    private void setupView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        listProductInCart = new ArrayList<ProductEntity>();
        allProducts = new ArrayList<ProductEntity>();
        listDiscount=new ArrayList<DiscountEntity>();
        layout_left = (FrameLayout) findViewById(R.id.frame_left);
        layout_right = (FrameLayout) findViewById(R.id.frame_right);
        listCustomer = new ArrayList<CustomerEntity>();
        customerInCart = new CustomerEntity();
        customerAddressInCart = new AddressEntity();
        serverHelper = new ServerHelper();
        addressEntity = new AddressEntity();
        setDefaultAddress();
    }
    public void setDefaultAddress() {
        customerAddressInCart.setIsActive(1);
        customerAddressInCart.setFirstName("Guest");
        customerAddressInCart.setLastName("POS");
        customerAddressInCart.setStreet("Street");
        customerAddressInCart.setCity("Guest City");
        customerAddressInCart.setCountry("United States");
        customerAddressInCart.setRegion("California");
        customerAddressInCart.setRegionId("12");
        customerAddressInCart.setPostcode("90034");
        customerAddressInCart.setTelephone("12345678");
        customerAddressInCart.setCountryId("US");
        customerAddressInCart.setCustomerId(137);
        customerAddressInCart.setId(93);
    }
    /**
     * Slide menu item click listener
     */
    private void setCustomersData() {
       listCustomer = databaseHelper.getAllCustomers();
    }

    @Override
    public void addCustomSaleToCart(CustomSale customSale) {
        ProductEntity productInCartItem=new ProductEntity(0,customSale.getName(),"","",customSale.getQuantity(),customSale.getPrice(),false,false,true,"","","");
        orderCartFragment.addProductInCart(productInCartItem);
        customSaleFragment.dismiss();

    }

    @Override
    public void openDialogCustomDiscount(DiscountEntity discountEntity) {
        FragmentManager fm = getFragmentManager();
        CustomDiscountDialogFragment customDiscountDialogFragment = CustomDiscountDialogFragment.newInstance(discountEntity);
        customDiscountDialogFragment.show(fm,"");

    }

    @Override
    public void applyDiscountDone(DiscountEntity discount) {
        orderCartFragment.addDiscount(discount);
    }

    @Override
    public void removeDiscount() {
        orderCartFragment.removeDiscount();

    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            Log.i("SlideMenuClickListener", position + "");
            displayView(position);
        }
    }
    private void addSliderMenu(Bundle savedInstanceState) {


       // setSupportActionBar(mToolbar);
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTitle = mDrawerTitle = getTitle();
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);
        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
             //   mToolbar,//Setup loi click khong hien
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerOpened(view);
                // calling onPrepareOptionsMenu() to show action bar icons
                //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                // calling onPrepareOptionsMenu() to hide action bar icons
                //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        if (savedInstanceState == null) {
            // on first time display view for first nav item
//            displayView(0);
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    public void backNavigation() {
        Log.i("xxxxx","YYYYYYYYYYY");
        mDrawerLayout.openDrawer(mDrawerList);
    }
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SplashFragment();
                break;
            case 1:
                fragment = new SplashFragment();
                break;
            case 2:
                fragment = new SplashFragment();
                break;
            case 3:
                fragment = new SplashFragment();
                break;
            case 4:
                fragment = new SplashFragment();
                break;
            case 5:
                fragment = new SplashFragment();
                break;

            default:
                break;
        }
        if (fragment != null) {

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    public void setupUI(View view) {
        if (!(view instanceof SearchView)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    searchView.onActionViewCollapsed();
                    searchView.setQuery("", false);
                    return false;
                }

            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }
    @Override
    public void openDialogCustomerInfo(int positionCustomer) {
        FragmentManager fm = getFragmentManager();
        CustomerInfoDialogFragment customerInfoDialogFragment = CustomerInfoDialogFragment.newInstance(positionCustomer);
        customerInfoDialogFragment.show(fm,"");
    }
    @Override
    public void addCustomerToCart(int positionCustomer) {
        customerInCart = MainActivity.listCustomer.get(positionCustomer);
        MainActivity.customerName = customerInCart.getName();
        orderCartFragment.setCustomerInCart();
    }
    @Override
    public void addProduct(ProductEntity productInCartItem) {
        Log.i("MainActivity", "addProduct");
        orderCartFragment.addProductInCart(productInCartItem);
    }
    @Override
    public List<ListCategoryAdapter.CategoriesListItem> getAllCategories() {
        categoryEntityList = databaseHelper.getAllCategories();
        items = new ArrayList<>();
        for (CategoryEntity category : categoryEntityList) {
            if(category.getLevel() ==1) {
                items.add(new ListCategoryAdapter.CategoriesListItem(category.getName()));
            }else if (category.getLevel() == 2) {
                items.add(new ListCategoryAdapter.CategoriesListItem(category.getName()));
                items.add(new ListCategoryAdapter.CategoriesListItem(category.getName(),""));
            }else if (category.getLevel() ==3) {
                items.add(new ListCategoryAdapter.CategoriesListItem(category.getName(),""));
            }
        }
        return items;
    }
    @Override
    public void getCategoryProducts(int categoryId) {
        productCategoryList = new ArrayList<ProductEntity>();
        if (categoryId > 1) {

            String categoryArr;
            for (ProductEntity product : allProducts) {
                categoryArr = product.getCateId();
                try {
                    JSONArray jsonArray = new JSONArray(categoryArr);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (categoryId == jsonArray.getInt(i))
                            productCategoryList.add(product);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }else
            productCategoryList = allProducts;
        Const.rcAdapter = new GridViewProductAdapter(MainActivity.this, productCategoryList);
        Const.recyclerViewProduct.setAdapter(Const.rcAdapter);
        Const.rcAdapter.notifyDataSetChanged();
    }
    @Override
    public List<ProductEntity> getAllProducts() {
        allProducts = databaseHelper.getAllProducts();
        productCategoryList = allProducts;
        return allProducts;
    }
    @Override
    public int getCategoryIdHeader(String headerNameCate) {
        for (CategoryEntity category : categoryEntityList) {
            if(category.getName().equals(headerNameCate) && (category.getLevel()==2))
                return category.getId();
        }
        return 0;
    }
    @Override
    public int getCurrentlyCategoryId(int parentId, String nameCate) {
        for (CategoryEntity category : categoryEntityList) {
            if (category.getName().equals(nameCate) && (category.getParent() == parentId))
                return category.getId();
        }
        return 0;
    }
    @Override
    public void addCustomSale() {
        FragmentManager fm = getFragmentManager();
         customSaleFragment = new CustomSaleFragment();
          customSaleFragment.show(fm,"");
    }
    private void addListCategoryFragment() {
        Log.i("MainActivity", "addListCategoryFragment");
        gridViewProductFragment = new GridViewProductFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.frame_left,
                gridViewProductFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void addListCategory() {
        FragmentManager fm = getFragmentManager();
        ListCategoryFragment listCategoryFragment = new ListCategoryFragment();
        listCategoryFragment.show(fm,"dialog");
    }
    private void addOrderCartFragment() {
        Log.i("MainActivity", "addOrderCartFragment");
        orderCartFragment = new OrderCartFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.frame_right,
                orderCartFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void backStackCheckoutCart() {
        changeWeightLayout(3,4);
        backAllProducts();
        backAllProductInCart();

    }
    @Override
    public void addCustomer() {
        orderCartFragment.addListCustomerDiaLog();
    }
    private void backAllProducts() {
        gridViewProductFragment = new GridViewProductFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_left,
                gridViewProductFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void backAllProductInCart() {
        orderCartFragment = new OrderCartFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_right,
                orderCartFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void checkoutToCart() {
        changeWeightLayout(4, 3);
        //updateOrderCartFragment();  //Lay lai man hinh ben checkout cart
        addPlaceOrderCartFragment();
        addPlaceOrderFragment();
    }
    public void changeWeightLayout(int left, int right) {
        Log.i("MainActivity", "changeWeightLayout");
        LinearLayout.LayoutParams paramsLeft = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsLeft.weight = left;
        layout_left.setLayoutParams(paramsLeft);

        LinearLayout.LayoutParams paramsRight = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsRight.weight = right;
        layout_right.setLayoutParams(paramsRight);
    }
    private void updateOrderCartFragment() {
        Log.i("MainActivity", "updateOrderCartFragment");
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.remove(orderCartFragment);
        Fragment newInstance = recreateFragment(orderCartFragment);
        fragmentTransaction.add(R.id.frame_left, newInstance);
        fragmentTransaction.commit();
    }
    private Fragment recreateFragment(Fragment f) {
        Log.i("MainActivity", "recreateFragment");
        try {
            Fragment.SavedState savedState = fragmentManager.saveFragmentInstanceState(f);

            Fragment newInstance = f.getClass().newInstance();
            newInstance.setInitialSavedState(savedState);

            return newInstance;
        } catch (Exception e) // InstantiationException, IllegalAccessException
        {
            throw new RuntimeException("Cannot reinstantiate fragment " + f.getClass().getName(), e);
        }
    }
    private void addPlaceOrderCartFragment() {
        orderCartPlaceFragment = new OrderCartPlaceFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_left,
                orderCartPlaceFragment);
        fragmentTransaction.commit();
    }
    private void addPlaceOrderFragment() {
        Log.i("MainActivity", "addPlaceOrderFragment");
        placeOrderFragment = new PlaceOrderFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_right,
                placeOrderFragment);
        fragmentTransaction.commit();
    }
    private void addPlaceOrderBackFragment() {
        Log.i("MainActivity", "addPlaceOrderBackFragment");
        placeOrderbackFragment = new PlaceOrderBackFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_right,
                placeOrderbackFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void deleteProductInCart(String productName) {
        orderCartFragment.deleteProductInCart(productName);

    }
    @Override
    public void addCustomer(int id, String name, String email, String phone, int groupid) {
       databaseHelper.addCustomer(id, name, email, phone, groupid);
    }

    @Override
    public void setAddress() {
        serverHelper.setAddress(databaseHelper, addressEntity.convertObjectToJson(customerAddressInCart));
    }
    @Override
    public void setPaymentMethod() {
        serverHelper.setPaymentMethod(databaseHelper);
    }
    @Override
    public void setShippingMethod() {
        serverHelper.setShippingMethod(databaseHelper);
    }
    @Override
    public void createOrder() {
        orderCartPlaceFragment.donePayment();
        addPlaceOrderBackFragment();
//        Log.i("before order id", MainActivity.orderId + "");
//        serverHelper.createOrder(databaseHelper);
//        Log.i("after order id", MainActivity.orderId+"");
//        placeOrderSuccess();
    }
    @Override
    public void addProductToCartOnServer() {
        for (int i=0; i<MainActivity.listProductInCart.size(); i++) {
            serverHelper.addProductToCartOnServer(databaseHelper,MainActivity.listProductInCart.get(i).getId());
        }
    }

    @Override
    public void placeOrderSuccess() {
       if (MainActivity.orderId > 0) {
           addPlaceOrderBackFragment();
       }
    }
    @Override
    public void startNewOrder() {

        MainActivity.grandTotal = 0;
        MainActivity.subTotal = 0;
        MainActivity.tax = 0;
        MainActivity.listProductInCart = new ArrayList<ProductEntity>();
        MainActivity.customerAddressInCart = new AddressEntity();
        MainActivity.customerInCart = new CustomerEntity();
        MainActivity.customerName = "Add Customer";
        Log.i("SSSSSSS","SSSSS");
        changeWeightLayout(3,4);
        backAllProducts();
        backAllProductInCart();
    }

}
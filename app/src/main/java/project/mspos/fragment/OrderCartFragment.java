package project.mspos.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import android.widget.Switch;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.adapter.ListCustomerAdapter;
import project.mspos.adapter.ListProductBoughtAdapter;
import project.mspos.entity.DiscountEntity;
import project.mspos.entity.DiscountType;
import project.mspos.entity.ProductEntity;
import project.mspos.entity.ProductImage;
import project.mspos.utils.Const;

public class OrderCartFragment extends Fragment implements View.OnClickListener {
    ImageView imgAddDiscount;
    TextView tvDiscountName;
    TextView tvAmountDiscount;
    ImageView imgDeleteAllProduct;
    ImageView imgDeleteProductItemt;
    ImageView imageCommentCart;
    TextView tvTotalPrice;
    ListView listViewProductInCart;
    RelativeLayout layoutCustomerCart;
    RelativeLayout layoutAddDiscount;
    RelativeLayout layoutTax;
    LinearLayout layoutCheckout;
    TextView tvAmountTax;
    PopupWindow popupWindowOne;
    PopupWindow popupWindowTwo;
    Button btnCheckoutCart;
    OpenDialogCustomer mCallback;
    OpenDialogCustomDiscountInterface callBackOpenDiscountDialog;
    //OpenDialogCustomDiscountInterface callBackOpenDiscountDialog;
    Boolean isDollar=false;
    Boolean isCustomPrice=false;
    TextView tvCustomPriceOrDiscount;
    TextView tvInfoUser;
    Switch switchCustomPriceDiscount;
    TextView tvDiscountAmountInput;
    Button btNumberOne;
    Button btNumberTwo;
    Button btNumberThree;
    Button btNumberFour;
    Button btNumberFive;
    Button btNumberSix;
    Button btNumberSeven;
    Button btNumberEight;
    Button btNumberNine;
    Button btNumberZero;
    Button btNumberDoubleZero;
    private Button btDoneChangeDiscountAmount;
    private ImageView btBackChangeDiscountAmount;
    private Button btBackDiscountOrCustomPrice;
    private TextView tvDiscountAmount;
    ImageButton btBackspace;
    EditText editNumberProduct;
    float totalPrice=0;
    float discount=0;
    float tax=0;
    float realPrice=0;
    float selectionProductPrice=0;
    float selectionProductDiscount=0;
    float selectionProductCustomPrice=0;
    int numberSelectionProduct=0;
    int positionSelectionProduct=0;
    boolean isFirstChangeAmountSelectionProduct=true;
    boolean popupWindowAlready=false;
    boolean popupCommentOrderAlready=false;
    boolean discountAlready=false;
    boolean productAlready=false;
    public static ListProductBoughtAdapter listProductBoughtAdapter;
    RecyclerView recyclerView;
    ArrayList<ProductEntity> listProductBought;
    ArrayList<ProductImage> listImageProduct;
    RecyclerView.ItemAnimator itemAnimator;
    int i =1;
    NumberFormat format;
    CheckoutCart listener;
    private DiscountEntity currentDiscount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_order_cart,container,false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("OrderCartFragment","onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        setupView();
        setAdapterForRecyclerView();
        registerForEvent();
        setData();
    }
    public void setData() {
        tvTotalPrice.setText("$" + MainActivity.subTotal);
        tvAmountTax.setText("$" + MainActivity.tax);
        tvAmountDiscount.setText("$" + MainActivity.discount);
        btnCheckoutCart.setText("$" + MainActivity.grandTotal);
        if (MainActivity.grandTotal > 0)
            btnCheckoutCart.setEnabled(true);
        if (MainActivity.tax > 0)
            layoutTax.setVisibility(View.VISIBLE);

    }
    @Override
    public void onAttach(Activity activity) {
        Log.i("OrderCartFragment", "onAttach");
        super.onAttach(activity);
        try {
            listener = (CheckoutCart) activity;
            mCallback = (OpenDialogCustomer) activity;
            callBackOpenDiscountDialog=(OpenDialogCustomDiscountInterface)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CheckLogin");
        }
    }
    private void setAdapterForRecyclerView() {
        Log.i("OrderCartFragment","setAdapterForRecyclerView");
        itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);

        listProductBoughtAdapter = new ListProductBoughtAdapter(getActivity(), MainActivity.listProductInCart);
        recyclerView.setAdapter(listProductBoughtAdapter);
        recyclerView.setItemAnimator(itemAnimator);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void setupView() {
        imgAddDiscount=(ImageView)getActivity().findViewById(R.id.btnAddCartDiscount);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvProductInCart);
        layoutCustomerCart = (RelativeLayout)getActivity().findViewById(R.id.layoutCustomerCart);
        tvInfoUser = (TextView)getActivity().findViewById(R.id.tvInfoUser);
        imgDeleteProductItemt = (ImageView)getActivity().findViewById(R.id.imgDeleteProductItem);
        imgDeleteAllProduct = (ImageView)getActivity().findViewById(R.id.imgClear);
        imageCommentCart = (ImageView)getActivity().findViewById(R.id.imgCommentCart);
        layoutAddDiscount=(RelativeLayout)getActivity().findViewById(R.id.layoutAddDiscount);
        tvDiscountName = (TextView)getActivity().findViewById(R.id.tvNameDiscountCart);
        tvAmountDiscount = (TextView)getActivity().findViewById(R.id.tvDiscountAmountCart);
        tvTotalPrice = (TextView)getActivity().findViewById(R.id.tvTotalPrice);
        layoutTax = (RelativeLayout)getActivity().findViewById(R.id.layoutTax);
        tvAmountTax = (TextView)getActivity().findViewById(R.id.tvAmountTax);
        btnCheckoutCart = (Button)getActivity().findViewById(R.id.btnCheckoutCart);
        layoutCheckout = (LinearLayout) getActivity().findViewById(R.id.layoutCheckout);
        format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));
    }
    private void registerForEvent() {
        Log.i("OrderCartFragment", "registerForEvent");
       layoutCustomerCart.setOnClickListener(this);
       imgDeleteAllProduct.setOnClickListener(this);
      //  imageCommentCart.setOnClickListener(this);
        layoutAddDiscount.setOnClickListener(this);
        btnCheckoutCart.setOnClickListener(this);

       /* listViewProductInCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(popupWindowTwo!=null){
                    popupWindowTwo.dismiss();
                }
                if(popupWindowOne!=null){
                    popupWindowOne.dismiss();
                }
               // addPopUpWindowCustomPriceDiscount(position);
            }
        });
        */
    }
    @Override
    public void onClick(View v) {
        Log.i("OrderCartFragment","onClick");
        if(popupWindowTwo!=null){
            popupWindowTwo.dismiss();
        }
        if(popupWindowOne!=null){
            popupWindowOne.dismiss();
        }
        switch (v.getId()){
            case R.id.layoutAddDiscount:
                if(MainActivity.listProductInCart.size()!=0) {
                    addOrEditDiscount(discountAlready);
                    updateRealPrice();
                }
                break;
            case R.id.btnCheckoutCart:
                checkoutToCart();
                break;
            case R.id.imgClear:
                deleteAllProductInCart();
                break;
            case R.id.layoutCustomerCart:
                addListCustomerDiaLog();
                break;
            case R.id.button_create_customer:
                mCallback.openDialogCustomerInfo(MainActivity.NO_CUSTOMER);
                break;
            default:
                break;
        }

    }

    private void addOrEditDiscount(boolean discountAlready) {
        if(!discountAlready){
            callBackOpenDiscountDialog.openDialogCustomDiscount(new DiscountEntity("", DiscountType.MONEY,0.0f,""));
        }else {
            callBackOpenDiscountDialog.openDialogCustomDiscount(currentDiscount);
        }
    }

    public void addListCustomerDiaLog() {
        popupWindowAlready=true;
        LayoutInflater layoutInflater
                = (LayoutInflater)getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.create_customer, null);
        popupWindowOne = new PopupWindow(
                popupView,
                800,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btCreateCustomer=(Button)popupView.findViewById(R.id.button_create_customer);
        EditText editSearchCustomer=(EditText)popupView.findViewById(R.id.edit_text_search_customer);
        ImageView imgBack=(ImageView)popupView.findViewById(R.id.img_list_customer_back);
        popupView.setFocusable(true);
        popupWindowOne.setFocusable(true);

        ListView listCustomerView=(ListView)popupView.findViewById(R.id.list_customer);
        ListCustomerAdapter listCustomerAdapter = new ListCustomerAdapter(getActivity(), R.layout.customer_item, MainActivity.listCustomer);
        listCustomerView.setAdapter(listCustomerAdapter);
        listCustomerAdapter.notifyDataSetChanged();

        popupWindowOne.showAsDropDown(layoutCustomerCart);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowOne.setFocusable(false);
                popupWindowOne.dismiss();
            }
        });
        listCustomerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               mCallback.addCustomerToCart(position);
                setCustomerInCart();

            }
        });
        btCreateCustomer.setOnClickListener(this);


    }
    public void setCustomerInCart() {
        tvInfoUser.setText(MainActivity.customerName);
        popupWindowOne.dismiss();
    }
    private void checkoutToCart() {
        Log.i("OrderCartFragment","checkoutToCart");
        layoutCheckout.setVisibility(View.GONE);
        listener.checkoutToCart();
    }
    public void addProductInCart(ProductEntity productInCartItem){
        Log.i("Price 123", productInCartItem.getPrice()+"");
        ProductEntity productEntity = null;
        productAlready=true;
        totalPrice += productInCartItem.getPrice();
        MainActivity.subTotal = totalPrice;
        tvTotalPrice.setText(format.format(MainActivity.subTotal));
        boolean isNewProduct=true;
        for(int i=0;  i< MainActivity.listProductInCart.size(); i++){
            productEntity = MainActivity.listProductInCart.get(i);
            if(productEntity.getId()== productInCartItem.getId()){
                productEntity.setNumberProduct(productEntity.getNumberProduct()+1);
                productEntity.setPrice(productEntity.getPrice()+productInCartItem.getPrice());
                isNewProduct=false;
            }
        }
        if(isNewProduct){
            productInCartItem.setNumberProduct(1);
            MainActivity.listProductInCart.add(productInCartItem);
        }       //Update UI
        listProductBoughtAdapter.notifyDataSetChanged();
        updateLayoutTax();
        updateRealPrice();

        btnCheckoutCart.setEnabled(true);//Enable check out cart
    }
    public void updateListProductInCart() {
        Log.i("OrderCartFragment","updateListProductInCart");
        listProductBoughtAdapter = new ListProductBoughtAdapter(getActivity(), MainActivity.listProductInCart);
        recyclerView.setAdapter(listProductBoughtAdapter);
        layoutCheckout.setVisibility(View.GONE);
        layoutAddDiscount.setVisibility(View.GONE);
        updateLayoutTax();
        updateRealPrice();
    }
    private void updateRealPrice() {
        Log.i("OrderCartFragment", "updateRealPrice");
        realPrice=totalPrice-discount+tax;
        MainActivity.discount=discount;
        MainActivity.grandTotal = realPrice;
        btnCheckoutCart.setText("Checkout " + format.format(MainActivity.grandTotal));
    }
    private void updateLayoutTax() {
        Log.i("OrderCartFragment", "updateLayoutTax");
        if(productAlready){
            layoutTax.setVisibility(View.VISIBLE);
            tax=(totalPrice-discount)*0.1f;
            MainActivity.tax = tax;
            tvAmountTax.setText(format.format(MainActivity.tax));
        }else {
            tax=0;
            MainActivity.tax = 0;
            layoutTax.setVisibility(View.GONE);
            tvAmountTax.setText("");
        }

    }
    public void deleteProductInCart(String productInCartName){
        totalPrice = MainActivity.subTotal;
        for(int i=0; i<MainActivity.listProductInCart.size(); i++){
            if(MainActivity.listProductInCart.get(i).getName().equals(productInCartName)){
                totalPrice -= MainActivity.listProductInCart.get(i).getPrice();
                MainActivity.subTotal = totalPrice;
                MainActivity.listProductInCart.remove(i);
            }
        }
        listProductBoughtAdapter.notifyDataSetChanged();
        if(MainActivity.listProductInCart.isEmpty()) {
            productAlready = false;
            btnCheckoutCart.setEnabled(false);
        }
        tvTotalPrice.setText( format.format(MainActivity.subTotal));
        updateLayoutTax();
        updateRealPrice();
    }
    public void deleteAllProductInCart() {
        MainActivity.listProductInCart.clear();
        listProductBoughtAdapter.notifyDataSetChanged();
        productAlready=false;
        totalPrice = 0;
        MainActivity.grandTotal = 0;
        MainActivity.subTotal = 0;
        tvTotalPrice.setText(format.format(MainActivity.subTotal));
        btnCheckoutCart.setEnabled(false);
        updateLayoutTax();
        updateRealPrice();
    }

    public void addDiscount(DiscountEntity discount) {
            discountAlready=true;
            imgAddDiscount.setVisibility(View.GONE);
            tvAmountDiscount.setVisibility(View.VISIBLE);
            tvDiscountName.setText(discount.getNameDiscount());
            if(discount.getDiscountType()==DiscountType.MONEY) {
                tvAmountDiscount.setText(discount.getAmount() + "$");
                this.discount=discount.getAmount();
            }
            else{
                float discountAmount=discount.getAmount()*totalPrice/100;
                tvAmountDiscount.setText(discountAmount+"%");
                this.discount=discountAmount;
            }
            currentDiscount=discount;
            updateRealPrice();
    }

    public void removeDiscount() {
        if(discountAlready){
            discountAlready=false;
            imgAddDiscount.setVisibility(View.VISIBLE);
            tvAmountDiscount.setVisibility(View.GONE);
            tvAmountDiscount.setText("");
            tvDiscountName.setText("Add Discount");
            currentDiscount=new DiscountEntity();
        }
    }

    public interface CheckoutCart {
        public void checkoutToCart();
    }
    public interface OpenDialogCustomer{
        public void openDialogCustomerInfo(int positionCustomer);
        public void addCustomerToCart(int positionCustomer);
    }
    public interface OpenDialogCustomDiscountInterface{
        public void openDialogCustomDiscount(DiscountEntity discountEntity);
    }
}

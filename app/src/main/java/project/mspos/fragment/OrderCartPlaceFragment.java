package project.mspos.fragment;

import android.app.Activity;
import android.app.Fragment;
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
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.adapter.ListProductBoughtAdapter;
import project.mspos.adapter.ListProductPlaceOrderAdapter;
import project.mspos.entity.ProductEntity;
import project.mspos.entity.ProductImage;
import project.mspos.utils.Const;

public class OrderCartPlaceFragment extends Fragment  implements View.OnClickListener {

    TextView tvDiscountName;
    TextView tvAmountDiscount;
    TextView tvTotalPrice;
    TextView tvGrandTotal;
    TextView tvAddCustomerToCart;
    RelativeLayout layoutCustomerCart;
    RelativeLayout layoutTax;
    TextView tvAmountTax;
    ListProductPlaceOrderAdapter listProductPlaceOrderAdapter;
    RecyclerView recyclerView;
    RecyclerView.ItemAnimator itemAnimator;
    ImageView btnBackOption,imgCommentCartPlace;
    BackStackOption mCallBack;
    AddCustomerToCart addCustomerToCart;
    private boolean boughtDone=false;
    int i =1;
    NumberFormat format;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_place_cart,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        setupView();
        setAdapterForRecyclerView();
        setData();
    }
    public void donePayment(){
        boughtDone=true;
    }


    public void setData() {
        tvTotalPrice.setText(format.format(MainActivity.subTotal));
        tvAmountTax.setText(format.format(MainActivity.tax));
        tvAmountDiscount.setText(format.format(MainActivity.discount));
        tvGrandTotal.setText(format.format(MainActivity.grandTotal));
    }


    private void setAdapterForRecyclerView() {

        itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        listProductPlaceOrderAdapter = new ListProductPlaceOrderAdapter(getActivity(), MainActivity.listProductInCart);
        recyclerView.setAdapter(listProductPlaceOrderAdapter);
        recyclerView.setItemAnimator(itemAnimator);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

    }



    private void setupView() {
        Log.i("OrderCartFragment","setupView");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvProductInCartPlace);
        layoutCustomerCart = (RelativeLayout)getActivity().findViewById(R.id.layoutCustomerCartPlace);
        tvAmountDiscount = (TextView)getActivity().findViewById(R.id.tvDiscountAmountCart);
        tvDiscountName = (TextView)getActivity().findViewById(R.id.tvNameDiscountCartPlace);
        tvTotalPrice = (TextView)getActivity().findViewById(R.id.tvTotalPricePlace);
        tvGrandTotal = (TextView)getActivity().findViewById(R.id.tvGrandTotalPlace);
        layoutTax = (RelativeLayout)getActivity().findViewById(R.id.layoutTaxPlace);
        tvAmountTax = (TextView)getActivity().findViewById(R.id.tvAmountTaxPlace);
        tvAddCustomerToCart = (TextView)getActivity().findViewById(R.id.tvAddCustomerToCart);
        btnBackOption = (ImageView) getActivity().findViewById(R.id.btnBackOption);
        imgCommentCartPlace = (ImageView) getActivity().findViewById(R.id.imgCommentCartPlace);

        btnBackOption.setOnClickListener(this);
        imgCommentCartPlace.setOnClickListener(this);
        tvAddCustomerToCart.setOnClickListener(this);

        format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));
    }
    @Override
    public void onClick(View v) {
        Log.i("OrderCartFragment","onClick");

        switch (v.getId()) {
            case R.id.btnBackOption:
                if(!boughtDone)
                    mCallBack.backStackCheckoutCart();
                break;
            case R.id.imgCommentCartPlace:

                break;
            case R.id.tvAddCustomerToCart:
                addCustomerToCart.addCustomer();
                break;
            default:
                break;
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (BackStackOption) activity;
        addCustomerToCart = (AddCustomerToCart) activity;
    }
    public interface BackStackOption{
        public void backStackCheckoutCart();
    }
    public interface AddCustomerToCart{
        public void addCustomer();
    }

}

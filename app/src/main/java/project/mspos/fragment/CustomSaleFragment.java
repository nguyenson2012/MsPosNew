package project.mspos.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import project.mspos.R;
import project.mspos.entity.CustomSale;

/**
 * Created by SON on 4/6/2016.
 */
public class CustomSaleFragment extends DialogFragment implements View.OnClickListener {
    private EditText editTextCustomsaleName;
    private Switch switchShippable;
    private TextView tvPrice;
    private Spinner spinnerTaxId;
    private EditText editTextQuantity;
    private Button btNumberOne;
    private Button btNumberTwo;
    private Button btNumberThree;
    private Button btNumberFour;
    private Button btNumberFive;
    private Button btNumberSix;
    private Button btNumberSeven;
    private Button btNumberEight;
    private Button btNumberNine;
    private Button btNumberZero;
    private Button btNumberDoubleZero;
    private ImageButton btBackSpace;
    private Button btAddToCart;
    private float priceReal;
    AddCustomSaleToCartInterface mCallBackAddCustomSaleToCart;
    CustomSale customSale;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.dialog_custom_sale,container,false);
        setUpView(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBackAddCustomSaleToCart=(AddCustomSaleToCartInterface)activity;
    }

    private void setUpView(View rootView) {
        editTextCustomsaleName=(EditText)rootView.findViewById(R.id.editTextNameCustomSale);
        switchShippable=(Switch)rootView.findViewById(R.id.switch_shippable);
        tvPrice=(TextView)rootView.findViewById(R.id.price_custom_sale);
        spinnerTaxId=(Spinner)rootView.findViewById(R.id.spinner_tax_id);
        editTextQuantity=(EditText)rootView.findViewById(R.id.editTextQuantityCustomSale);
        btNumberOne=(Button)rootView.findViewById(R.id.button_number_one);
        btNumberTwo=(Button)rootView.findViewById(R.id.button_number_two);
        btNumberThree=(Button)rootView.findViewById(R.id.button_number_three);
        btNumberFour=(Button)rootView.findViewById(R.id.button_number_four);
        btNumberFive=(Button)rootView.findViewById(R.id.button_number_five);
        btNumberSix=(Button)rootView.findViewById(R.id.button_number_six);
        btNumberSeven=(Button)rootView.findViewById(R.id.button_number_seven);
        btNumberEight=(Button)rootView.findViewById(R.id.button_number_eight);
        btNumberNine=(Button)rootView.findViewById(R.id.button_number_nine);
        btNumberZero=(Button)rootView.findViewById(R.id.button_number_zero);
        btNumberDoubleZero=(Button)rootView.findViewById(R.id.button_number_double_zero);
        btBackSpace=(ImageButton)rootView.findViewById(R.id.button_backspace);
        btAddToCart=(Button)rootView.findViewById(R.id.button_add_to_cart_custom_sale);
        customSale=new CustomSale();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerEvent();

    }

    private void registerEvent() {
        btNumberOne.setOnClickListener(this);
        btNumberTwo.setOnClickListener(this);
        btNumberThree.setOnClickListener(this);
        btNumberFour.setOnClickListener(this);
        btNumberFive.setOnClickListener(this);
        btNumberSix.setOnClickListener(this);
        btNumberSeven.setOnClickListener(this);
        btNumberEight.setOnClickListener(this);
        btNumberNine.setOnClickListener(this);
        btNumberZero.setOnClickListener(this);
        btNumberDoubleZero.setOnClickListener(this);
        btBackSpace.setOnClickListener(this);
        btAddToCart.setOnClickListener(this);
    }

    private void addPrice(int number){
        String priceDollar=(String)tvPrice.getText();
        String price=priceDollar.substring(0, priceDollar.length()-1);
        priceReal=Float.parseFloat(price);
        priceReal=(Float)priceReal*10+number;
        tvPrice.setText(priceReal + "$" + "");

    }
    private void backSpace(){
        float priceSubstract=priceReal%10;
        priceReal=(priceReal-priceSubstract)/10;
        tvPrice.setText(priceReal+"$"+"");
    }
    private void multiplePrice(int number){
        priceReal=priceReal*number;
        tvPrice.setText(priceReal+"$"+"");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_number_one:
                addPrice(1);
                break;
            case R.id.button_number_two:
                addPrice(2);
                break;
            case R.id.button_number_three:
                addPrice(3);
                break;
            case R.id.button_number_four:
                addPrice(4);
                break;
            case R.id.button_number_five:
                addPrice(5);
                break;
            case R.id.button_number_six:
                addPrice(6);
                break;
            case R.id.button_number_seven:
                addPrice(7);
                break;
            case R.id.button_number_eight:
                addPrice(8);
                break;
            case R.id.button_number_nine:
                addPrice(9);
                break;
            case R.id.button_number_zero:
                multiplePrice(10);
                break;
            case R.id.button_number_double_zero:
                multiplePrice(100);
                break;
            case R.id.button_backspace:
                backSpace();
                break;
            case R.id.button_add_to_cart_custom_sale:
                addCustomSaleToCart();
                break;
        }

    }

    private void addCustomSaleToCart() {
        customSale.setName(editTextCustomsaleName.getText()+"");
        customSale.setPrice(priceReal);
        customSale.setQuantity(Integer.parseInt(editTextQuantity.getText() + ""));
        customSale.setShippable(switchShippable.isChecked());
        customSale.setTaxClassId(1);
        mCallBackAddCustomSaleToCart.addCustomSaleToCart(customSale);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public interface AddCustomSaleToCartInterface{
        public void addCustomSaleToCart(CustomSale customSale);
    }
}

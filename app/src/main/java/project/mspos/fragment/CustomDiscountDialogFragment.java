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
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.entity.DiscountEntity;
import project.mspos.entity.DiscountType;

/**
 * Created by SON on 4/6/2016.
 */
public class CustomDiscountDialogFragment extends DialogFragment implements View.OnClickListener{
    public static final String DISCOUNT_NAME="DISCOUNT_NAME";
    public static final String DISCOUNT_CODE="DISCOUNT_CODE";
    public static final String DISCOUNT_TYPE="DISCOUNT_TYPE";
    public static final String DISCOUNT_AMOUNT="DISCOUNT_AMOUNT";
    TextView tvDiscountMax;
    Button btApplyDiscount;
    TextView tvAmountDiscount;
    TextView tvNameDiscount;
    TextView tvDiscountCode;
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
    private Button btRemoveDiscount;
    private Switch typeDiscount;
    private float amountReal;
    private ApplyDiscountInterface applyDiscountCallback;
    private DiscountEntity currentDiscount;
    private boolean discountAlready=false;

    public static CustomDiscountDialogFragment newInstance(DiscountEntity discountEntity){
        CustomDiscountDialogFragment customDiscountDialogFragment=new CustomDiscountDialogFragment();
        Bundle argument=new Bundle();
        argument.putString(DISCOUNT_NAME, discountEntity.getNameDiscount());
        if(discountEntity.getDiscountType()== DiscountType.MONEY)
            argument.putInt(DISCOUNT_TYPE,0);
        else
            argument.putInt(DISCOUNT_TYPE,1);
        argument.putFloat(DISCOUNT_AMOUNT,discountEntity.getAmount());
        argument.putString(DISCOUNT_CODE,discountEntity.getDiscountCode());
        customDiscountDialogFragment.setArguments(argument);
        return customDiscountDialogFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.dialog_discount,container,false);
        setUpView(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        applyDiscountCallback=(ApplyDiscountInterface)activity;
    }

    private void setUpView(View rootView) {
        tvDiscountMax=(TextView)rootView.findViewById(R.id.textview_discountMax);
        btRemoveDiscount=(Button)rootView.findViewById(R.id.button_remove_discount);
        btApplyDiscount=(Button)rootView.findViewById(R.id.button_apply_discount);
        tvAmountDiscount=(TextView)rootView.findViewById(R.id.textview_amount_discount);
        tvNameDiscount=(TextView)rootView.findViewById(R.id.custom_discount_name);
        tvDiscountCode=(TextView)rootView.findViewById(R.id.editTextCouponCode);
        btNumberOne=(Button)rootView.findViewById(R.id.button_number_one_custom_discount);
        btNumberTwo=(Button)rootView.findViewById(R.id.button_number_two_custom_discount);
        btNumberThree=(Button)rootView.findViewById(R.id.button_number_three_custom_discount);
        btNumberFour=(Button)rootView.findViewById(R.id.button_number_four_custom_discount);
        btNumberFive=(Button)rootView.findViewById(R.id.button_number_five_custom_discount);
        btNumberSix=(Button)rootView.findViewById(R.id.button_number_six_custom_discount);
        btNumberSeven=(Button)rootView.findViewById(R.id.button_number_seven_custom_discount);
        btNumberEight=(Button)rootView.findViewById(R.id.button_number_eight_custom_discount);
        btNumberNine=(Button)rootView.findViewById(R.id.button_number_nine_custom_discount);
        btBackSpace=(ImageButton)rootView.findViewById(R.id.button_backspace_custom_discount);
        btNumberZero=(Button)rootView.findViewById(R.id.button_number_zero_custom_discount);
        btNumberDoubleZero=(Button)rootView.findViewById(R.id.button_number_double_zero_custom_discount);
        typeDiscount=(Switch)rootView.findViewById(R.id.switch_type);
        btRemoveDiscount=(Button)rootView.findViewById(R.id.button_remove_discount);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDataFromArument();
        setDiscountInfo();
        registerEvent();
    }

    private void setDiscountInfo() {
        if(discountAlready){
            tvDiscountCode.setText(currentDiscount.getDiscountCode() + "");
            tvNameDiscount.setText(currentDiscount.getNameDiscount() + "");
            if(currentDiscount.getDiscountType()== DiscountType.MONEY)
                typeDiscount.setChecked(true);
            else
                typeDiscount.setChecked(false);
            tvAmountDiscount.setText(currentDiscount.getAmount()+"");
        }
    }

    private void getDataFromArument() {
        String discountName=getArguments().getString(DISCOUNT_NAME);
        String discountCode=getArguments().getString(DISCOUNT_CODE);
        int discountType=getArguments().getInt(DISCOUNT_TYPE);
        float discountAmount=getArguments().getFloat(DISCOUNT_AMOUNT);
        if(!discountName.equals("")){
            discountAlready=true;
            btRemoveDiscount.setVisibility(View.VISIBLE);
            tvDiscountMax.setVisibility(View.GONE);
            if(discountType==0){
                currentDiscount=new DiscountEntity(discountName, DiscountType.MONEY,discountAmount,discountCode);
            }else {
                currentDiscount=new DiscountEntity(discountName, DiscountType.PERCENT,discountAmount,discountCode);
            }
        }else {
            discountAlready=false;
            btRemoveDiscount.setVisibility(View.GONE);
            tvDiscountMax.setVisibility(View.VISIBLE);
        }
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
        btBackSpace.setOnClickListener(this);
        btNumberZero.setOnClickListener(this);
        btNumberDoubleZero.setOnClickListener(this);
        btApplyDiscount.setOnClickListener(this);
        btRemoveDiscount.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void addPrice(int number){
        String amountDiscount=(String)tvAmountDiscount.getText();
        amountReal=Float.parseFloat(amountDiscount);
        amountReal=(Float)amountReal*10+number;
        tvAmountDiscount.setText(amountReal + "");

    }
    private void backSpace(){
        float amountSubstract=amountReal%10;
        amountReal=(amountReal-amountSubstract)/10;
        tvAmountDiscount.setText(amountReal + "");
    }
    private void multiplePrice(int number){
        amountReal=amountReal*number;
        tvAmountDiscount.setText(amountReal + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_number_one_custom_discount:
                addPrice(1);
                break;
            case R.id.button_number_two_custom_discount:
                addPrice(2);
                break;
            case R.id.button_number_three_custom_discount:
                addPrice(3);
                break;
            case R.id.button_number_four_custom_discount:
                addPrice(4);
                break;
            case R.id.button_number_five_custom_discount:
                addPrice(5);
                break;
            case R.id.button_number_six_custom_discount:
                addPrice(6);
                break;
            case R.id.button_number_seven_custom_discount:
                addPrice(7);
                break;
            case R.id.button_number_eight_custom_discount:
                addPrice(8);
                break;
            case R.id.button_number_nine_custom_discount:
                addPrice(9);
                break;
            case R.id.button_number_zero_custom_discount:
                multiplePrice(10);
                break;
            case R.id.button_number_double_zero_custom_discount:
                multiplePrice(100);
                break;
            case R.id.button_backspace_custom_discount:
                backSpace();
                break;
            case R.id.button_apply_discount:
                applyDiscount();
                break;
            case R.id.button_remove_discount:
                removeDiscount();
                break;
        }

    }

    private void removeDiscount() {
        if(discountAlready){
            applyDiscountCallback.removeDiscount();
            this.dismiss();
        }

    }

    private void applyDiscount() {
        DiscountEntity discountEntity=new DiscountEntity();
        if(enoughInformationDiscount()){
            discountEntity.setNameDiscount(tvNameDiscount.getText() + "");
            discountEntity.setDiscountCode(tvDiscountCode.getText() + "");
            if(typeDiscount.isChecked()){
                discountEntity.setDiscountType(DiscountType.MONEY);
            }else {
                discountEntity.setDiscountType(DiscountType.PERCENT);
            }
            discountEntity.setAmount(Float.parseFloat(tvAmountDiscount.getText()+""));
            this.dismiss();
            MainActivity.listDiscount.add(discountEntity);
            applyDiscountCallback.applyDiscountDone(discountEntity);
        }

    }

    private boolean enoughInformationDiscount() {
        boolean enoughInfo=true;
        if(tvDiscountCode.getText().equals("")){
            Toast.makeText(getActivity(),"Please give the discount code",Toast.LENGTH_SHORT).show();
            enoughInfo=false;
        }
        if(tvNameDiscount.getText().equals("")){
            Toast.makeText(getActivity(),"Please give the discount name",Toast.LENGTH_SHORT).show();
            enoughInfo=false;
        }
        if(tvNameDiscount.getText().equals("")){
            Toast.makeText(getActivity(),"Please give the discount name",Toast.LENGTH_SHORT).show();
            enoughInfo=false;
        }
        return enoughInfo;
    }


    public interface ApplyDiscountInterface{
        public void applyDiscountDone(DiscountEntity discount);
        public void removeDiscount();
    }
}

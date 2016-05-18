package project.mspos.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.helper.ServerHelper;
import project.mspos.utils.Const;

public class PlaceOrderFragment extends Fragment implements View.OnClickListener {
    ServerHelper serverHelper;
    PlaceOrder listener;
    Button btnCashIn, btnPlaceOrder;
    TextView btnEditAddress;
    TextView tvRemain, tvAmountTendered;
    Button btnTotal, btnSmallTotal, btnLargeTotal;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDelete, btn00;
    NumberFormat format;
    float amountTendered = 0, remain = 0, priceAdd= 0,smallPrice,largePrice,totalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_place_order, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupView();
        registerForEvent();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (PlaceOrder) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement PlaceOrder");
        }
    }

    private void registerForEvent() {

        btnTotal.setOnClickListener(this);
        btnSmallTotal.setOnClickListener(this);
        btnLargeTotal.setOnClickListener(this);
        btnCashIn.setOnClickListener(this);
        //btnEditAddress.setOnClickListener(this);
        btnPlaceOrder.setOnClickListener(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn00.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void setupView() {
        serverHelper = new ServerHelper();

        btnCashIn = (Button) getActivity().findViewById(R.id.btnCashIn);
        btnEditAddress = (TextView) getActivity().findViewById(R.id.btnEditAddress);
        btnPlaceOrder = (Button) getActivity().findViewById(R.id.btnPlaceOrder);

        tvAmountTendered = (TextView) getActivity().findViewById(R.id.tvAmountTendered);
        tvRemain = (TextView) getActivity().findViewById(R.id.tvRemain);
        btnTotal = (Button) getActivity().findViewById(R.id.btnTotal);
        btnSmallTotal = (Button) getActivity().findViewById(R.id.btnSmallTotal);
        btnLargeTotal = (Button) getActivity().findViewById(R.id.btnLargeTotal);
        btn1 = (Button) getActivity().findViewById(R.id.btn1);
        btn2 = (Button) getActivity().findViewById(R.id.btn2);
        btn3 = (Button) getActivity().findViewById(R.id.btn3);
        btn4 = (Button) getActivity().findViewById(R.id.btn4);
        btn5 = (Button) getActivity().findViewById(R.id.btn5);
        btn6 = (Button) getActivity().findViewById(R.id.btn6);
        btn7 = (Button) getActivity().findViewById(R.id.btn7);
        btn8 = (Button) getActivity().findViewById(R.id.btn8);
        btn9 = (Button) getActivity().findViewById(R.id.btn9);
        btn0 = (Button) getActivity().findViewById(R.id.btnx10);
        btnDelete = (Button) getActivity().findViewById(R.id.btnDelete);
        btn00 = (Button) getActivity().findViewById(R.id.btnx100);
        setEnablePlaceOrder();



        format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));

        btnTotal.setText(format.format(MainActivity.grandTotal));
        setSmallLargeTotal();

        remain = MainActivity.grandTotal;
        totalPrice=MainActivity.grandTotal;
        tvRemain.setText(format.format(remain));
        tvAmountTendered.setText(format.format(amountTendered));
    }

    public void setSmallLargeTotal() {
        if (MainActivity.grandTotal < 100) {
            setTextTotal(10);
        } else if (MainActivity.grandTotal < 1000) {
            setTextTotal(100);
        } else if (MainActivity.grandTotal < 10000) {
            setTextTotal(1000);
        } else if (MainActivity.grandTotal < 100000) {
            setTextTotal(10000);
        } else if (MainActivity.grandTotal < 100000) {
            setTextTotal(100000);
        }
    }

    public void setTextTotal(int divide) {
        int total, balance;
        total = (int) MainActivity.grandTotal / divide;
        balance = (int) MainActivity.grandTotal % divide;
        if (balance < divide / 2) {
            btnSmallTotal.setText(format.format(total * divide));
            smallPrice=total*divide;
            btnLargeTotal.setText(format.format(total * divide + divide / 2));
            largePrice=total*divide+divide/2;
        } else {
            btnSmallTotal.setText(format.format(total * divide + divide / 2));
            smallPrice=total * divide + divide / 2;
            btnLargeTotal.setText(format.format((total + 1) * divide));
            largePrice=(total+1)*divide;
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTotal:
                cashInAll();
                break;
            case R.id.btnSmallTotal:
                smallPlaceOrder();
                break;
            case R.id.btnLargeTotal:
                largePlaceOrder();
                break;
            case R.id.btnCashIn:
                cashInPayment();
                break;
            case R.id.btnPlaceOrder:
                placeOrder();
                break;
            case R.id.btnx10:
                calculatorAmount(10);
                break;
            case R.id.btnx100:
                calculatorAmount(100);
                break;
            case R.id.btn1:
                calculatorAmount(1);
                break;
            case R.id.btn2:
                calculatorAmount(2);
                break;
            case R.id.btn3:
                calculatorAmount(3);
                break;
            case R.id.btn4:
                calculatorAmount(4);
                break;
            case R.id.btn5:
                calculatorAmount(5);
                break;
            case R.id.btn6:
                calculatorAmount(6);
                break;
            case R.id.btn7:
                calculatorAmount(7);
                break;
            case R.id.btn8:
                calculatorAmount(8);
                break;
            case R.id.btn9:
                calculatorAmount(9);
                break;
            case R.id.btnDelete:
                calculatorAmount(0);
                break;
            default:
                calculatorAmount(v.getId());
        }
    }

    public void cashInAll() {
            float change;
            tvRemain.setText(format.format(0.0));
            tvAmountTendered.setText(format.format(totalPrice));
            setEnablePlaceOrder();

    }
    public void smallPlaceOrder() {
        remain=smallPrice-totalPrice;
        amountTendered= smallPrice;
        tvRemain.setText(format.format(remain));
        tvAmountTendered.setText(format.format(amountTendered));
        setEnablePlaceOrder();

    }
    public void largePlaceOrder() {
        remain=largePrice-totalPrice;
        amountTendered= largePrice;
        tvRemain.setText(format.format(remain));
        tvAmountTendered.setText(format.format(amountTendered));
        setEnablePlaceOrder();

    }
    public void cashInPayment() {

    }
    public void calculatorAmount(int number) {
        if (number ==0) {
            priceAdd = priceAdd/10;
        }else if(number ==10) {
            priceAdd = priceAdd*10;
        }else if (number == 100) {
            priceAdd = priceAdd*100;
        }else {
            priceAdd = priceAdd*10 +number;
        }
        amountTendered=priceAdd;
        remain=priceAdd-totalPrice;
        tvAmountTendered.setText(format.format(priceAdd));
        tvRemain.setText(format.format(priceAdd-totalPrice));
        setEnablePlaceOrder();
    }
    public void placeOrder() {
        listener.createOrder();
        Thread background = new Thread(new Runnable() {
            public void run() {
                try {
                    listener.addProductToCartOnServer();
                    listener.setAddress();
                    listener.setShippingMethod();
                    listener.setPaymentMethod();
                    listener.createOrder();
                } catch (Throwable t) {}
            }
        });
        //background.start();
    }

    public void setEnablePlaceOrder() {
        if (remain >=0) {
            btnPlaceOrder.setEnabled(true);
        }else {
            btnPlaceOrder.setEnabled(false);
        }
    }

    public interface PlaceOrder {
        public void addProductToCartOnServer();
        public void createOrder();
        public void setShippingMethod();
        public void setAddress();
        public void setPaymentMethod();
        public void placeOrderSuccess();


    }
}

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

public class PlaceOrderBackFragment extends Fragment {
    NumberFormat format;
    StartNewOrder startNewOrder;
    TextView tvSuccess,tvOrderId,tvGrandTotal;
    Button btnStartNewOrder;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_place_order_back,container,false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupView();
    }
    private void setupView() {
        format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));

        tvSuccess = (TextView) getActivity().findViewById(R.id.tvSuccess);
        tvOrderId = (TextView) getActivity().findViewById(R.id.tvOrderId);
        tvGrandTotal=(TextView)getActivity().findViewById(R.id.tvGrandTotal);
        tvGrandTotal.setText(MainActivity.grandTotal+"$");
        btnStartNewOrder = (Button) getActivity().findViewById(R.id.btnStartNewOrder);

        btnStartNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewOrder.startNewOrder();
            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        startNewOrder = (StartNewOrder)activity;
    }
    public interface StartNewOrder{
        public void startNewOrder();
    }
}

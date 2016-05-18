package project.mspos.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import project.mspos.R;
import project.mspos.utils.Const;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    //GIRD_VIEW_PRODUCT
    public NetworkImageView imgProduct;
    public TextView tvProductId;
    public TextView tvProductPrice;
    public TextView tvProductName;

    public RecyclerViewHolders(View itemView) {
        super(itemView);

        imgProduct = (NetworkImageView) itemView.findViewById(R.id.imgProduct);
        tvProductId = (TextView) itemView.findViewById(R.id.tvProductId);
        tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductName);
        tvProductName = (TextView) itemView.findViewById(R.id.tvProductPrice);

    }
    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Product Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}

package project.mspos.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import project.mspos.R;
import project.mspos.utils.Const;

public class RecyclerListViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imgDeleteProductItem;
    public NetworkImageView imgItemProductCart;
    public TextView tvNumberProductInCart, tvNameProductInCart, tvPriceProductInCart;

    public RecyclerListViewHolders (View itemView) {
        super(itemView);

        imgItemProductCart = (NetworkImageView) itemView.findViewById(R.id.imgItemProductCart);
        imgDeleteProductItem = (ImageView) itemView.findViewById(R.id.imgDeleteProductItem);
        tvNumberProductInCart = (TextView) itemView.findViewById(R.id.tvNumberProductInCart);
        tvNameProductInCart = (TextView) itemView.findViewById(R.id.tvNameProductInCart);
        tvPriceProductInCart = (TextView) itemView.findViewById(R.id.tvPriceProductInCart);

    }
    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Product Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}

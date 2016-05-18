package project.mspos.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.toolbox.ImageLoader;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import project.mspos.R;
import project.mspos.app.AppController;
import project.mspos.entity.ProductEntity;
import project.mspos.utils.Const;
import project.mspos.views.RecyclerListViewHolders;

public class ListProductBoughtAdapter extends RecyclerView.Adapter<RecyclerListViewHolders> {

    private Activity activity;
    private List<ProductEntity> listProductBought;
    RecyclerListViewHolders rcv;
    DeleteProductInCartInterface mCallBack;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    ProductEntity productInCartItem;
    public ListProductBoughtAdapter(Activity context, List<ProductEntity> listProduct) {

        this.activity = context;
        this.listProductBought = listProduct;
        mCallBack=(DeleteProductInCartInterface)activity;
    }

    @Override
    public RecyclerListViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_in_cart_item, null);
        rcv = new RecyclerListViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerListViewHolders holder, final int position) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));
        productInCartItem = listProductBought.get(position);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        holder.imgItemProductCart.setImageUrl(productInCartItem.getImage(),imageLoader);
        holder.tvNameProductInCart.setText(productInCartItem.getName());
        holder.tvPriceProductInCart.setText(format.format(productInCartItem.getPrice()));
        holder.tvNumberProductInCart.setText(productInCartItem.getNumberProduct()+"");
        imageLoader.get(productInCartItem.getImage(), ImageLoader.getImageListener(holder.imgItemProductCart, R.drawable.thumbnail, R.drawable.thumbnail));

        holder.imgDeleteProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.deleteProductInCart(productInCartItem.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductBought.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface DeleteProductInCartInterface{
        public void deleteProductInCart(String productName);
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(activity, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}

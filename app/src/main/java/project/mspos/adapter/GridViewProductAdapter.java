package project.mspos.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import project.mspos.R;
import project.mspos.app.AppController;
import project.mspos.entity.ProductEntity;
import project.mspos.utils.Const;
import project.mspos.views.RecyclerViewHolders;

public class GridViewProductAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ProductEntity> itemList;
    private Activity context;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public GridViewProductAdapter(Activity context, List<ProductEntity> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_product_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        ProductEntity productEntity;
        productEntity = itemList.get(position);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
       // holder.imageProduct.setImageResource(productEntity.getList_image().get(0).getmProductImage());
        // Loading image with placeholder and error image
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Const.CURRENCY));
        holder.imgProduct.setImageUrl(productEntity.getImage(),imageLoader);
        holder.tvProductId.setText(productEntity.getId() + "");
        holder.tvProductPrice.setText(format.format(productEntity.getPrice()));
        holder.tvProductName.setText(productEntity.getName());
        holder.tvProductName.setSingleLine(true);
        holder.tvProductName.setEllipsize(TextUtils.TruncateAt.END);
        imageLoader.get(productEntity.getImage(), ImageLoader.getImageListener(holder.imgProduct, R.drawable.thumbnail, R.drawable.thumbnail));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
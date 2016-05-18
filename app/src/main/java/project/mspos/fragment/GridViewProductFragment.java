package project.mspos.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.adapter.GridViewProductAdapter;
import project.mspos.entity.ProductEntity;
import project.mspos.utils.Const;
import project.mspos.utils.RecyclerItemClickListener;

public class GridViewProductFragment extends Fragment implements View.OnClickListener{
    //private RecyclerView recyclerViewProduct;
    private Button btnCustomSale;
    private GridLayoutManager gridLayoutManager;
  //  GridViewProductAdapter rcAdapter;
    private List<ProductEntity> listProduct;
    AddProductInCartInterface mCallBack;
    AddCustomSaleInterface customSaleCallBack;
    BackNavigation backNavigation;
    GetProducts productEvent;
    private TextView tvCategory;
    private SearchView searchView;
    private ImageView btnHamburger;
    private SearchManager searchManager;
    private List<ProductEntity> productCategorySearch;
    private List<ProductEntity> productCategoryList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_list_category_product,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDefaultListProduct();
        setupView();
        setAdapterForRecyclerView();
        registerForEvent();
        addSearchView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (AddProductInCartInterface)activity;
        productEvent = (GetProducts)activity;
        backNavigation = (BackNavigation)activity;
        customSaleCallBack=(AddCustomSaleInterface)activity;
    }
    private void setupView() {
        gridLayoutManager=new GridLayoutManager(getActivity(),4);
        Const.recyclerViewProduct=(RecyclerView)getActivity().findViewById(R.id.listCategoryProduct);
        Const.recyclerViewProduct.setHasFixedSize(true);
        Const.recyclerViewProduct.setLayoutManager(gridLayoutManager);
        btnCustomSale=(Button)getActivity().findViewById(R.id.btnCustomSale);
        searchView = (SearchView) getActivity().findViewById(R.id.action_search);
        tvCategory = (TextView) getActivity().findViewById(R.id.tvCategory);
        btnHamburger = (ImageView) getActivity().findViewById(R.id.btnHamburger);

    }
    private void registerForEvent() {
        Const.recyclerViewProduct.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        ProductEntity productEntity = listProduct.get(position);
                        mCallBack.addProduct(productEntity);
                    }
                })
        );
        btnCustomSale.setOnClickListener(this);
        btnHamburger.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheckoutCart:
                break;
            case R.id.tvCategory:
                productEvent.addListCategory();
                break;
            case R.id.btnCustomSale:
                customSaleCallBack.addCustomSale();
                break;
            case R.id.btnHamburger:
                backNavigation.backNavigation();
                break;
            default:
                break;
        }
    }
    private void setAdapterForRecyclerView() {
        Const.rcAdapter = new GridViewProductAdapter(getActivity(), listProduct);
        Const.recyclerViewProduct.setAdapter(Const.rcAdapter);
    }



    private void setDefaultListProduct(){
        listProduct = productEvent.getAllProducts();
    }
    private void addSearchView() {
        searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getActivity().getComponentName()));
            searchView.setIconifiedByDefault(false);
            searchView.setSubmitButtonEnabled(true);
            //Hide keyboard when init
            searchView.setFocusable(false);
            searchView.setFocusableInTouchMode(false);
            searchView.onActionViewCollapsed();//Remove forcus
            searchView.setQuery("", false);//
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                productCategorySearch = new ArrayList<ProductEntity>();
                for (ProductEntity product: listProduct) {
                    if (product.getName().contains(newText) || product.getSku().contains(newText)) {
                        productCategorySearch.add(product);
                    }
                }
                // This is your adapter that will be filtered
                Const.rcAdapter = new GridViewProductAdapter(getActivity(), productCategorySearch);
                Const.recyclerViewProduct.setAdapter(Const.rcAdapter);
                Const.rcAdapter.notifyDataSetChanged();
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                productCategorySearch = new ArrayList<ProductEntity>();
                // **Here you can get the value "query" which is entered in the search box.**
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                for (ProductEntity product: listProduct) {
                    if (product.getName().contains(query) || product.getSku().contains(query)) {
                        productCategorySearch.add(product);
                    }
                }
                // This is your adapter that will be filtered
                Const.rcAdapter = new GridViewProductAdapter(getActivity(), productCategorySearch);
                Const.recyclerViewProduct.setAdapter(Const.rcAdapter);
                Const.rcAdapter.notifyDataSetChanged();
                searchView.onActionViewCollapsed();
                searchView.setQuery("", true);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
    }
    public interface AddProductInCartInterface{
        public void addProduct(ProductEntity productInCartItem);
    }
    public interface GetProducts{
        public List<ProductEntity> getAllProducts();
        public void addListCategory();
    }
    public interface AddCustomSaleInterface{
        public void addCustomSale();
    }
    public interface BackNavigation{
        public void backNavigation();
    }
}

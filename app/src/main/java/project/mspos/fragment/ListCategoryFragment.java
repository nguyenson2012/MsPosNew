package project.mspos.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.List;
import project.mspos.R;
import project.mspos.adapter.ExpandableRecyclerAdapter;
import project.mspos.adapter.ListCategoryAdapter;
import project.mspos.utils.Const;
import project.mspos.utils.RecyclerItemClickListener;

public class ListCategoryFragment extends DialogFragment {

    RecyclerView recycler;
    ListCategoryAdapter adapter;
    GetProductsCategoryInterface mCallBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_category,container,false);
        recycler = (RecyclerView) rootView.findViewById(R.id.lv_categories);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ListCategoryAdapter(getActivity());
        adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        adapter.setItems(mCallBack.getAllCategories());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        registerForEvent();
    }

    private void registerForEvent() {
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        HashMap<Integer, String> listCategory = Const.mapCategory;
                        //Get header
                        if (position==0) {
                            mCallBack.getCategoryProducts(1);
                            getDialog().dismiss();
                        }
                        String headerNameCate = "null";
                        for (String value : listCategory.values() ) {
                            headerNameCate = value;
                        }
                        int currentlyCategoryId = 0;
                        if (!headerNameCate.equals("null")) {
                            //Get category id header
                            int categoryIdHeader = mCallBack.getCategoryIdHeader(headerNameCate);
                            //End get
                            //Get sub Category id
                            currentlyCategoryId = mCallBack.getCurrentlyCategoryId(categoryIdHeader, listCategory.get(position));
                            //End get
                        }
                        //Get All Product
                        if(currentlyCategoryId > 0) {
                            mCallBack.getCategoryProducts(currentlyCategoryId);
                            getDialog().dismiss();
                        }

                    }
                })
        );    }
    public interface GetProductsCategoryInterface{
        public void getCategoryProducts(int categoryId);
        public List<ListCategoryAdapter.CategoriesListItem> getAllCategories();
        public int getCategoryIdHeader(String headerNameCate);
        public int getCurrentlyCategoryId(int parentId, String name);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (GetProductsCategoryInterface) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP | Gravity.LEFT;
        wlp.y = 100;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        return dialog;
    }
}

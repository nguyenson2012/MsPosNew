package project.mspos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import project.mspos.R;

public class NavigationDrawerFragment extends Fragment implements View.OnClickListener {

    View mFragmentContainerView;
    LinearLayout cate,bookmark, setting,subscribe,history;
    private DrawerLayout mDrawerLayout;
    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cate = (LinearLayout) getActivity().findViewById(R.id.cate);
        bookmark = (LinearLayout) getActivity().findViewById(R.id.bookmark);
        setting = (LinearLayout) getActivity().findViewById(R.id.setting);
        subscribe = (LinearLayout) getActivity().findViewById(R.id.subscribe);
        history = (LinearLayout) getActivity().findViewById(R.id.history);
        cate.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        history.setOnClickListener(this);
        subscribe.setOnClickListener(this);
        setting.setOnClickListener(this);

    }
    public  void closeDrawer(){
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
    }

    @Override
    public void onClick(View view) {
        int key = view.getId();
        switch (key)
        {
            case R.id.cate:

                closeDrawer();
                break;
            case R.id.history:

                closeDrawer();
                break;
            case R.id.bookmark:

                closeDrawer();
                break;
            case R.id.setting:

                closeDrawer();
                break;
            case R.id.subscribe:

                closeDrawer();
                break;

        }

    }
}
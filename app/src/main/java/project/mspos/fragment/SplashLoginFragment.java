package project.mspos.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.mspos.R;

/**
 * Created by CONGHAO on 4/12/2016.
 */
public class SplashLoginFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_splash_login,container,false);
        return rootView;
    }
}

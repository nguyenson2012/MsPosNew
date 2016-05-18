package project.mspos.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import project.mspos.R;
import project.mspos.fragment.LoginFragment;
import project.mspos.fragment.SplashFragment;
import project.mspos.helper.DatabaseHelper;
import project.mspos.helper.ServerHelper;
import project.mspos.utils.AlertDialogManager;
import project.mspos.utils.Const;
import project.mspos.utils.HttpsTrustManager;
import project.mspos.utils.SessionManager;

public class SplashScreenActivity extends AppCompatActivity implements LoginFragment.CheckLogin{

    FragmentManager fragmentManager;
    SessionManager sessionManager;
    DatabaseHelper databaseHelper;
    ServerHelper serverHelper;
    AlertDialogManager alertDialogManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_login);
        alertDialogManager = new AlertDialogManager();
        databaseHelper = new DatabaseHelper(SplashScreenActivity.this);
        serverHelper = new ServerHelper();
        HttpsTrustManager.allowAllSSL();
        fragmentManager = getFragmentManager();
        sessionManager = new SessionManager(getApplicationContext());
        addSplashFragment();
        //Khoi tao
        new GetAccountToServer().execute();
        //getDataToServer();
    }

    private void addSplashFragment() {
        SplashFragment splashFragment = new SplashFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_splash_and_login, splashFragment);
        fragmentTransaction.commit();
    }

    public void addMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    private void addLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_splash_and_login, loginFragment);
        fragmentTransaction.commit();
    }

    public void setLoggedIn(boolean checkLogin, String username, String password) {
        addMainActivity();
//        if (checkLogin) {
//            sessionManager.createLoginSession(username, password, Const.KEY_URL, Const.SESSION);
//            new GetUserToServer().execute();
//            addMainActivity();
//        }else {
//            alertDialogManager.showAlertDialog(SplashScreenActivity.this, "Login failed..", "Username/Password is incorrect", false);
//        }
    }

    public boolean checkUser(String username, String password) {
        if (Const.USERNAME != null){
            if(username.equals(Const.USERNAME) && password.equals(Const.PASSWORD)) {
                return true;
            }else
                return false;
        }else {//Check data base
            return databaseHelper.checkUserToSqlite(username, password);
        }
    }

    public class GetAccountToServer extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            serverHelper.getAccountToServer(databaseHelper);
            serverHelper.getUserToServer(databaseHelper);
            serverHelper.getCategoryToServer(databaseHelper);
            serverHelper.getAllProductToServer(databaseHelper);
            serverHelper.getAllCustomersToServer(databaseHelper);
            serverHelper.addProductToCartOnServer(databaseHelper,338);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(!sessionManager.isLoggedIn()) {
                addLoginFragment();
            }
            else {
                sessionManager.createLoginSession(Const.USERNAME, Const.PASSWORD, Const.KEY_URL, Const.SESSION);//Cap nhat lai session moi
                addMainActivity();
            }
        }
    }

    public class GetUserToServer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            serverHelper.getUserToServer(databaseHelper);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            sessionManager.createLoginSession(Const.USERNAME, Const.PASSWORD, Const.KEY_URL, Const.SESSION);//Cap nhat lai session moi
        }
    }

    public class GetCategoryToServer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            serverHelper.getCategoryToServer(databaseHelper);
            return null;
        }
    }

}

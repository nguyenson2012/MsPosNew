package project.mspos.utils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;
import project.mspos.activity.SplashScreenActivity;

/**
 * Created by CONGHAO on 4/12/2016.
 */
public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MsPOS";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String password, String username, String url, String session){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(Const.KEY_PASSWORD, password);
        editor.putString(Const.KEY_USERNAME, username);
        editor.putString(Const.KEY_URL, url);
        editor.putString(Const.SESSION, session);
        // commit changes
        editor.commit();
    }

    public void initialize() {
        Const.REQUEST_SERVER = "http://www.magestore.com/posmanagement/api";
        Const.USERNAME = pref.getString(Const.KEY_USERNAME, null);
        Const.PASSWORD = pref.getString(Const.KEY_PASSWORD, null);
        Const.REQUEST_URL = pref.getString(Const.KEY_URL, null);
        Const.SESSION = pref.getString(Const.KEY_SESSION, null);
    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(Const.KEY_PASSWORD, pref.getString(Const.KEY_PASSWORD, null));
        user.put(Const.USERNAME, pref.getString(Const.USERNAME, null));

        // return user
        return user;
    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Splash Screen Activity
        Intent i = new Intent(_context, SplashScreenActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


}

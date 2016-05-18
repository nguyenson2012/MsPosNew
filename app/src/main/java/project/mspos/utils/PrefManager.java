package project.mspos.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;

import project.mspos.app.AppController;

public class PrefManager {

    // Shared Preferences
    SharedPreferences pref;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MsPos";

    // No of grid columns
    private static final String KEY_NO_OF_COLUMNS = "no_of_columns";

    // Gallery directory name
    private static final String KEY_GALLERY_NAME = "gallery_name";

    // gallery albums key
    private static final String KEY_ALBUMS = "albums";
    SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    }

    /**
     * storing gallery name
     * */
    public void setGalleryName(String galleryName) {
        editor = pref.edit();

        editor.putString(KEY_GALLERY_NAME, galleryName);

        // commit changes
        editor.commit();
    }

    public String getGalleryName() {
        return pref.getString(KEY_GALLERY_NAME, Const.SDCARD_DIR_NAME);
    }


}
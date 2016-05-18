package project.mspos.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project.mspos.R;


public class LoginFragment extends Fragment {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public static final String DEFAULT_EMAIL="abc@gmail.com";
    public static final String DEFAULT_PASS="password";
    private CheckLogin listener;
    public LoginFragment() {}

    public interface CheckLogin {
        public void setLoggedIn(boolean check, String username, String password);
        public boolean checkUser (String userName, String password);
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextInputLayout usernameWrapper = (TextInputLayout) getActivity().findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) getActivity().findViewById(R.id.passwordWrapper);
        Button btnLogin = (Button) getActivity().findViewById(R.id.btnLogin);
        usernameWrapper.requestFocus();
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //An ban phim
                hideKeyboard();

                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();

                if (!validateUsername(username)) {
                    usernameWrapper.setError("Not a valid username!");
                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    //Login
                    listener.setLoggedIn(listener.checkUser(username, password), username, password);

                }
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            listener = (CheckLogin) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CheckLogin");
        }
    }
    /*
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
    public boolean validateUsername(String username) {
        return username.length() > 3;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}

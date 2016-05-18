package project.mspos.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.mspos.R;
import project.mspos.activity.MainActivity;
import project.mspos.entity.CustomerEntity;
import project.mspos.helper.DatabaseHelper;

public class CustomerInfoDialogFragment extends DialogFragment implements View.OnClickListener{
    public static final String POSITION_CUSTOMER="position_customer";
    Button btSaveCustomer;
    Button btCancel;
    TextInputLayout editFirstName,editLastName,editEmail,editPhone,editAddressLine1,editAddressLine2;
    TextInputLayout editCity,editZip,editCompany,editFax,editVatId;
    DatabaseHelper databaseHelper;
    Spinner spinCountry,spinProvice,spinTypeCustomer;
    List<CustomerEntity> listCustomer;
    CreateNewCustomer mCallBack;

    public static CustomerInfoDialogFragment newInstance(int positionCustomer){
        CustomerInfoDialogFragment customerInfoDialogFragment=new CustomerInfoDialogFragment();
        Bundle argument=new Bundle();
        argument.putInt(POSITION_CUSTOMER, positionCustomer);
        customerInfoDialogFragment.setArguments(argument);
        return customerInfoDialogFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.information_customer, container, false);
        setUpView(rootView);
        displayInformationCustomer();
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (CreateNewCustomer) activity;
    }
    private void displayInformationCustomer() {

        int positionCustomer = getArguments().getInt(POSITION_CUSTOMER,0);
        if(positionCustomer >= 0) {
            editFirstName.getEditText().setText(listCustomer.get(positionCustomer).getFirstName());
            editLastName.getEditText().setText(listCustomer.get(positionCustomer).getLastName());
            editEmail.getEditText().setText(listCustomer.get(positionCustomer).getEmail());
            editPhone.getEditText().setText(listCustomer.get(positionCustomer).getPhone());
            editAddressLine1.getEditText().setText(listCustomer.get(positionCustomer).getAddressLine1());
            editAddressLine2.getEditText().setText(listCustomer.get(positionCustomer).getAddressLine2());
            editCity.getEditText().setText(listCustomer.get(positionCustomer).getCity());
            editZip.getEditText().setText(listCustomer.get(positionCustomer).getZipId());
            editCompany.getEditText().setText(listCustomer.get(positionCustomer).getCompany());
            editFax.getEditText().setText(listCustomer.get(positionCustomer).getFax());
            editVatId.getEditText().setText(listCustomer.get(positionCustomer).getVATId());
        }

    }

    private void setUpView(View rootView) {

        btCancel=(Button)rootView.findViewById(R.id.btCancelInfoCustomer);
        btSaveCustomer=(Button)rootView.findViewById(R.id.btSaveInfoCustomer);
        editFirstName=(TextInputLayout)rootView.findViewById(R.id.editFirstname);
        editLastName=(TextInputLayout)rootView.findViewById(R.id.editLastName);
        editEmail=(TextInputLayout)rootView.findViewById(R.id.editEmail);
        editPhone=(TextInputLayout)rootView.findViewById(R.id.editPhone);
        editCity=(TextInputLayout)rootView.findViewById(R.id.editCity);
        editAddressLine1=(TextInputLayout)rootView.findViewById(R.id.editAddressLine1);
        editAddressLine2=(TextInputLayout)rootView.findViewById(R.id.editAddressLine2);
        editZip=(TextInputLayout)rootView.findViewById(R.id.editZip);
        editCompany=(TextInputLayout)rootView.findViewById(R.id.editCompany);
        editFax=(TextInputLayout)rootView.findViewById(R.id.editFax);
        editVatId=(TextInputLayout)rootView.findViewById(R.id.editVatID);
       // spinCountry=(Spinner)rootView.findViewById(R.id.spinnerCountry);
      //  spinProvice=(Spinner)rootView.findViewById(R.id.spinner_Province);
     //   spinTypeCustomer=(Spinner)rootView.findViewById(R.id.spinner_type_customer);

        editFirstName.requestFocus();
        editFirstName.setHint("First Name");
        editLastName.setHint("Last Name");
        editEmail.setHint("Email");
        editPhone.setHint("Telephone");
        editCity.setHint("City");
        editAddressLine1.setHint("Address Line 1");
        editAddressLine2.setHint("Address Line 2");
        editZip.setHint("Zip");
        editCompany.setHint("Company");
        editFax.setHint("Fax");
        editVatId.setHint("Vat Id");


        databaseHelper = new DatabaseHelper(getActivity());
        listCustomer = new ArrayList<CustomerEntity>();
        listCustomer = MainActivity.listCustomer;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerEvent();
    }

    private void registerEvent() {
        btSaveCustomer.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCancelInfoCustomer:
                dismiss();
                break;
            case R.id.btSaveInfoCustomer:
                createNewCustomer();
                break;
        }
    }
    public void createNewCustomer() {
        String firstName, lastName, name, email, phone;
        String city, address1, address2, zip, company, vatId;

        firstName = editFirstName.getEditText().getText().toString();
        lastName = editLastName.getEditText().getText().toString();
        name = firstName + lastName;
        email = editEmail.getEditText().getText().toString();
        phone = editPhone.getEditText().getText().toString();
        city = editCity.getEditText().getText().toString();
        address1 = editAddressLine1.getEditText().getText().toString();
        address2 = editAddressLine2.getEditText().getText().toString();
        zip = editZip.getEditText().getText().toString();
        company = editCompany.getEditText().getText().toString();
        vatId = editVatId.getEditText().getText().toString();
        MainActivity.customerName = name;
        mCallBack.addCustomer(0, name, email, phone, 1);
        dismiss();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public interface CreateNewCustomer{
        public void addCustomer(int id, String name, String email, String phone, int groupId);
    }
}

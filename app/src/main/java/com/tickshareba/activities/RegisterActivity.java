package com.tickshareba.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.Constants;
import com.tickshareba.R;

import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private String TAG = "RegisterActivity";

    private AlertDialog dialog;

    private EditText name, lastName, region, emailAddress, password, password_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTextFields();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        MainActivity.createPersistenceManager(this);

    }

    public void onNextClick(View view) {
        String userFirstName = name.getEditableText().toString().trim();
        String userLastName = lastName.getEditableText().toString().trim();
        String userRegion = region.getEditableText().toString().trim();
        String userEmail = emailAddress.getEditableText().toString().trim();
        String userPassword = password.getEditableText().toString().trim();
        String userPasswordConfirm = password_confirm.getEditableText().toString().trim();
        if (checkUserData(userFirstName, userLastName, userRegion, userEmail, userPassword, userPasswordConfirm)) {

            MainActivity.showSuccessAlert(getApplicationContext(), Constants.AccountSuccess.getValue());

            MainActivity.userManager.createUser(userFirstName, userLastName, userRegion, userEmail, userPassword);

            if(MainActivity.userManager.getUserFromEmail(userEmail)!= null) {
                MainActivity.persistenceManagerDBHelper.persistUser(MainActivity.userManager.getUserFromEmail(userEmail));
            }else {
                Log.e(TAG, "something went wrong getting the user from his email Address: "+emailAddress);
            }
            finish();
        } else {
            showErrorAlert(MainActivity.errorMap);
            MainActivity.errorMap.clear();
            return;
        }


    }

    private void initTextFields() {
        name = findViewById(R.id.inputTextCreateAccountName);
        lastName = findViewById(R.id.inputTextCreateAccountLastName);
        region = findViewById(R.id.inputTextCreateAccountRegion);
        emailAddress = findViewById(R.id.inputTextCreateAccountEmailAddress);
        password = findViewById(R.id.inputTextCreateAccountPassword);
        password_confirm = findViewById(R.id.inputTextCreateAccountConfirmPassword);
    }

    private boolean checkUserData(String firstName, String lastName, String region, String email, String password, String passwordConfirm) {
        boolean retVal = true;
        if (!password.equals(passwordConfirm)) {
            MainActivity.errorMap.put("Password: ", "The passwords you´ve entered dont match!");
            retVal = false;
        }
        if (password.length() < 8) {
            MainActivity.errorMap.put("Password: ", "The password you´ve entered is to short!");
            retVal = false;
        }
        if (!Pattern.matches(Constants.INPUT_REGEX.getValue(), firstName)) {
            MainActivity.errorMap.put("Name: ", "The name you´ve entered is not allowed !");
            retVal = false;
        }
        if (!Pattern.matches(Constants.INPUT_REGEX.getValue(), lastName)) {
            MainActivity.errorMap.put("Last Name: ", "The last name you´ve entered is not allowed !");
            retVal = false;
        }
        if (!Pattern.matches(Constants.INPUT_REGEX.getValue(), region)) {
            MainActivity.errorMap.put("Region: ", "The region you´ve entered is not allowed !");
            retVal = false;
        }
        if (!Pattern.matches(Constants.EMAIL_REGEX.getValue(), email)) {
            MainActivity.errorMap.put("Email Address: ", "The Email Address you´ve entered is not allowed !");
            retVal = false;
        }
        return retVal;
    }

    private void showErrorAlert(Map<String, String> errors) {
        dialog = new AlertDialog.Builder(this)
                .setTitle("Register")
                .setMessage("Could not create your account please check your entries: " + errors.toString())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public AlertDialog getAlertDialog() {
        return dialog;
    }


}

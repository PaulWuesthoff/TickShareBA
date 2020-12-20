package com.tickshareba.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.Constants;
import com.tickshareba.R;
import com.tickshareba.authentication.PasswordUtils;
import com.tickshareba.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginActivity extends AppCompatActivity {

    private static final Logger LOG = LogManager.getLogger(LoginActivity.class);

    private AlertDialog dialog;

    private EditText textFieldEmail, textFieldPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupTextFields();
        if (MainActivity.userPersistenceManagerDBHelper == null) {
            MainActivity.createPersistenceManager(this);
        }

    }


    public void onLogin(View view) {
        try {
            if (authenticateUser(getUserData(), textFieldPassword.getText().toString())) {
                MainActivity.userManager.getUserList().add(getUserData());
                System.out.println(getUserData().toString());
                MainActivity.showSuccessAlert(this, Constants.LOGIN_SUCCESS.getValue());
                MainActivity.setUserState(UserState.LOGGED_IN);
                finish();
            }else{
                showErrorAlert("Wrong Email or Password! Please try again. ");
            }
        }catch (Exception e){
            LOG.error("Something went wrong while logging in ! ", e);
            showErrorAlert("Wrong Email or Password! Please try again. ");
        }
    }

    private UserModel getUserData() {
        return MainActivity.userPersistenceManagerDBHelper.getUser(textFieldEmail.getText().toString().trim());

    }

    private void setupTextFields() {
        textFieldEmail = findViewById(R.id.inputTextEmailAddressLogin);
        textFieldPassword = findViewById(R.id.inputTextPasswordLogin);
    }

    private boolean authenticateUser(UserModel model, String password) {

        return PasswordUtils.verifyUserPassword(password, model.getPassword(), model.getSalt());
    }

    private void showErrorAlert(String error) {
        dialog = new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("Login: " + error)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}

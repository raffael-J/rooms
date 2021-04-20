package com.example.hotelmanagement.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.hotelmanagement.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private EditText mEmail_editText;
    private EditText mPassword_editText;

    private Button mSignIn_btm;
    private Button mRegister_btm;
    private Button mBack_btm;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        mEmail_editText = (EditText) findViewById(R.id.email_editText);
        mPassword_editText = (EditText) findViewById(R.id.password_editText);
        mSignIn_btm = (Button) findViewById(R.id.signIn_btm);
        mRegister_btm = (Button) findViewById(R.id.regsiter_button);
        mBack_btm = (Button) findViewById(R.id.back_btm);
        mProgress_bar = (ProgressBar) findViewById(R.id.loading_progressBar);
        mSignIn_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* If one of the edit texts is empty, stop the execution. else call inProgress method and pass it true
                value to discipline process bar and to disable all buttons
                */
                if (isEmpty()) return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(mEmail_editText.getText().toString(),
                        mPassword_editText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "User signned in", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignInActivity.this, MenuActivity.class );
                                // set flag on the intent object to clear all activities in the current back stack
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish(); return;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "Sign in failed!"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mRegister_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(mEmail_editText.getText().toString(),
                        mPassword_editText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "register successfully", Toast.LENGTH_LONG).show();
                                inProgress(false);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "registration in failed!"+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mBack_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); return;
            }
        });
    }

    //if the user click on sign in or register one we need to discipline progressbar
    // and disable all buttons. for this we need this helper method
    private void inProgress(boolean x) {
        if (x) {
            mProgress_bar.setVisibility(View.VISIBLE);
            mBack_btm.setEnabled(false);
            mSignIn_btm.setEnabled(false);
            mRegister_btm.setEnabled(false);
        } else {
            mProgress_bar.setVisibility(View.GONE);
            mBack_btm.setEnabled(true);
            mSignIn_btm.setEnabled(true);
            mRegister_btm.setEnabled(true);
        }
    }

    //check if the edit text is empty
    private boolean isEmpty() {
        if (TextUtils.isEmpty(mEmail_editText.getText().toString())) {
            mEmail_editText.setError("REQUIRED!");
            return true;
        }
        if (TextUtils.isEmpty(mPassword_editText.getText().toString())) {
            mPassword_editText.setError("REQUIRED!");
            return true;
        }
        return false;
    }
}
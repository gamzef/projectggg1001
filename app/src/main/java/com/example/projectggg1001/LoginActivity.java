package com.example.projectggg1001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button bLogin;
    EditText etEmailText, etPasswort;
    TextView tvRegisterLink;
    CheckBox mCbShowPwd;
    String nameofuser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailText = (EditText) findViewById(R.id.emailText);
        etPasswort = (EditText) findViewById(R.id.etPasswort);
        mCbShowPwd= (CheckBox) findViewById(R.id.cbShowPwd);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        //Kullanıcı ismini alma
        Intent intent = getIntent();
        nameofuser = intent.getStringExtra("name");

        //ŞİFRE HIDE/SHOW ÖZELLİĞİ
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    etPasswort.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else{
                    etPasswort.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        mAuth = FirebaseAuth.getInstance();
        if (etPasswort.length() > 0 && etEmailText.length() > 0) {
            mAuth.signInWithEmailAndPassword(etEmailText.getText().toString(), etPasswort.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(
                                        LoginActivity.this,
                                        "Authentication Failed",
                                        Toast.LENGTH_LONG).show();
                                mAuth.getInstance().signOut();
                            } else {
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name",nameofuser);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    });
        } else {
            Toast.makeText(
                    LoginActivity.this,
                    "Fill All Fields",
                    Toast.LENGTH_LONG).show();
        }
    }


}

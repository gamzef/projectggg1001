package com.example.projectggg1001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailText, passwordText2, nameText, alterText;
    CheckBox mCbShowPwd;
    Button bRegister;
    String name;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.emailText);
        passwordText2 = (EditText) findViewById(R.id.etPasswort2);
        mCbShowPwd= (CheckBox) findViewById(R.id.cbShowPwd);
        bRegister = (Button) findViewById(R.id.bRegister);

        passwordText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //ŞİFRE HIDE/SHOW ÖZELLİĞİ
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    passwordText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else{
                    passwordText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mAuth = FirebaseAuth.getInstance();
        if (passwordText2.length() > 0 && passwordText2.length() > 0 && emailText.length() > 0) {
            mAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText2.getText().toString())
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(
                                        RegisterActivity.this,
                                        "Authentication Failed",
                                        Toast.LENGTH_LONG).show();
                                mAuth.signOut();
                            } else {
                                finish();
                                speichernBenutzerInfo();
                            }
                        }
                    });
        } else{
            Toast.makeText(RegisterActivity.this,"Informationen werden falsch eingegeben.",Toast.LENGTH_LONG).show();
        }
    }


    public void speichernBenutzerInfo(){
        String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        nameText = (EditText) findViewById(R.id.etName);
        alterText = (EditText) findViewById(R.id.etAlter);
        name = nameText.getText().toString();
        String alt = alterText.getText().toString();

        HashMap<String, String> newPost = new HashMap<String, String>();
        newPost.put("name",name);
        newPost.put("age",alt);

        current_user_db.setValue(newPost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class PasswortActivity extends AppCompatActivity {
    Button bandern;
    Button bconfirm;
    EditText eskipassword, yenipassword;
    CheckBox mCbShowPwd;
    LinearLayout layoutpasswordconfirm;
    LinearLayout layoutpasswordupdate;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwort);

        //layoutpasswordconfirm.setVisibility(View.VISIBLE);
        //layoutpasswordupdate.setVisibility(View.GONE);

        eskipassword = (EditText) findViewById(R.id.eskipasswort);
        yenipassword = (EditText) findViewById(R.id.yenipasswort);
        mCbShowPwd= (CheckBox) findViewById(R.id.cbShowPwd);
        bandern = (Button) findViewById(R.id.bandern);
        bconfirm = (Button) findViewById(R.id.bconfirm);
        layoutpasswordconfirm = (LinearLayout) findViewById(R.id.layoutPasswordconfirm);
        layoutpasswordupdate = (LinearLayout) findViewById(R.id.layoutPasswordupdate);

        //ŞİFRE HIDE/SHOW ÖZELLİĞİ
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    yenipassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else{
                    yenipassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        bconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eskipass = eskipassword.getText().toString();
                if(eskipass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Geben Sie Ihres Passwort ein.",Toast.LENGTH_LONG);
                }
                AuthCredential credential = EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(),eskipass);
                mAuth.getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        layoutpasswordconfirm.setVisibility(View.GONE);
                        layoutpasswordupdate.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        bandern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yenipass = yenipassword.getText().toString();
                if(yenipass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Geben Sie Ihres neues Passwort ein.",Toast.LENGTH_LONG);
                }
                mAuth.getCurrentUser().updatePassword(yenipass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            mAuth.signOut();
                            finish();
                            Intent i = new Intent(PasswortActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }
}

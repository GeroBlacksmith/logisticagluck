package com.example.gero.gluck_logistica.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.example.gero.gluck_logistica.domain.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrarActivity extends AppCompatActivity implements TextWatcher{

    //Views
    EditText            etNombre,           etPassword,     etRepeatPassword,       etEMail;
    Button              btnSignUp;
    ProgressBar         progressBar;

    //Backend managers

    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase    firebaseDatabase;
    FirebaseAuth        auth;


    //Information
    String  nombre,  email,    password,    repeatpassword;

    //Helper flags
    boolean flagNombre, flagEmail, flagPassword, flagRepeatPassword, matchPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Opcion.Grafica.pantallaCompleta(this);

        setContentView(R.layout.activity_registrar);

        Opcion.Grafica.ocultarActionBar(getSupportActionBar());

        //guardar en una base de datos usuarios la informaicion de usuario.
        //guardar el usuario en auth de Firebase


        firebaseDatabase    =   FirebaseDatabase.getInstance();
        auth                =   FirebaseAuth.getInstance();

        etNombre            =   (EditText)findViewById(R.id.et_registro_nombre);
        etEMail             =   (EditText)findViewById(R.id.et_registro_mail);
        etPassword          =   (EditText)findViewById(R.id.et_registro_password);
        etRepeatPassword    =   (EditText)findViewById(R.id.et_registro_password_repetir);
        etNombre.addTextChangedListener(this);
        etEMail.addTextChangedListener(this);
        etRepeatPassword.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);

        btnSignUp           = (Button)findViewById(R.id.btn_registrar);

        progressBar         = (ProgressBar)findViewById(R.id.progressBar);

        nombre          = etNombre.getText().toString().trim();
        email           = etEMail.getText().toString().trim();
        password        = etPassword.getText().toString().trim();
        repeatpassword  = etRepeatPassword.getText().toString().trim();



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagEmail    &&  flagNombre  &&  flagPassword    &&  flagRepeatPassword  &&  matchPassword){
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(RegistrarActivity.this, email+" "+password, Toast.LENGTH_SHORT).show();
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegistrarActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegistrarActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                    } else {
                                        
                                        startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }
                            });

                }
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("FBSN:", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("FBSO:", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


        nombre          = etNombre.getText().toString().trim();
        email           = etEMail.getText().toString().trim();
        password        = etPassword.getText().toString().trim();
        repeatpassword  = etRepeatPassword.getText().toString().trim();
        if(nombre.isEmpty()){
            flagNombre=false;
            etNombre.setError("¡No puede estar vacio!");
        }else{
            flagNombre=true;
        }
        if(email.isEmpty()){
            flagEmail=false;
            etEMail.setError("¡No puede estar vacio!");
        }else{
            flagEmail=true;
        }
        if(password.isEmpty()){
            flagPassword=false;
            etPassword.setError("¡No puede estar vacio!");
        }else{
            flagPassword=true;
        }
        if(repeatpassword.isEmpty()){
            flagRepeatPassword=false;
            etRepeatPassword.setError("¡No puede estar vacio!");
        }else{
            flagRepeatPassword=true;
        }
        if(Objects.equals(password, repeatpassword)){
            matchPassword=true;
        }else{
            matchPassword=false;
            etPassword.setError("¡¡¡");
            etRepeatPassword.setError("No coinciden los passwords." +
                                        "\n!!!");
        }


    }
}

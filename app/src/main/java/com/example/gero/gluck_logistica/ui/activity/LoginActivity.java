package com.example.gero.gluck_logistica.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gero.gluck_logistica.R;
import com.example.gero.gluck_logistica.configuraciones.Opcion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    EditText inputName, inputPassword;
    Button btnLogin, btnSignUp, bteResetPassword;
    FirebaseAuth auth;

    private View.OnClickListener clickLogin;
    private View.OnClickListener clickSignUp;
    private View.OnClickListener clickResetPassword;

    String email, password;
    /***
     * Metodo onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Opcion.Grafica.pantallaCompleta(this);

        setContentView(R.layout.activity_login);

        Opcion.Grafica.ocultarActionBar(getSupportActionBar());

        auth = FirebaseAuth.getInstance();

        inputName=(EditText)findViewById(R.id.tv_login_usuario);
        inputName.addTextChangedListener(this);

        inputPassword=(EditText)findViewById(R.id.tv_login_password);
        inputPassword.addTextChangedListener(this);
        email=inputName.getText().toString().trim();
        password=inputPassword.getText().toString();
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnSignUp=(Button)findViewById(R.id.btn_sign_up);
        //btnResetPassword=(Button)findViewById(R.id.btn_reset_password);

        btnLogin.setEnabled(false);

        setClickLogin();
        btnLogin.setOnClickListener(clickLogin);

        setClickSignUp();

        btnSignUp.setOnClickListener(clickSignUp);

        DatabaseReference mydb = FirebaseDatabase.getInstance().getReference("nuevo");

        HashMap<String, String> nuevoCliente = new HashMap<String, String>();
        nuevoCliente.put("nombre", "Gerardo");


        mydb.push().setValue("gero");




    }

    public void setClickLogin(){


        clickLogin=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
                email=inputName.getText().toString().trim();
                password=inputPassword.getText().toString();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, user.getUid(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });

            }
        };
    }

    public void setClickSignUp(){
        clickSignUp=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, RegistrarActivity.class));
                finish();
            }
        };
    }

    public void setClickResetPassword(){
        clickLogin=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LoginActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        boolean flagName=false;
        boolean flagPassword=false;
        String auxName=inputName.getText().toString().trim();
        if(auxName.isEmpty()){
            inputName.setError("No puede estar vacio");
            flagName=false;
        }else{
            flagName=true;
        }


        String auxPassword=inputPassword.getText().toString();
        if(auxPassword.isEmpty()){
            inputPassword.setError("No puede estar vacio");
            flagPassword=false;
        }else{
            flagPassword=true;
        }

        btnLogin.setEnabled(flagName && flagPassword);
    }
}

package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText edEmail,edPassword;
    private Button btnLoguear;
    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edEmail= findViewById(R.id.etEmail);
        edPassword= findViewById(R.id.edPassword);
        btnLoguear = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);

    }

    private void loguearUsuario(){

        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe de ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Bienvenido: "+edEmail.getText(),Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplication(), RecicleView.class);
                            intent.putExtra(RecicleView.user, email);
                            startActivity(intent);

                        }else {
                            Toast.makeText(MainActivity.this,"Contraseña o usuario incorrecto: ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }


    public void Login(View view) {
        loguearUsuario();

    }
}
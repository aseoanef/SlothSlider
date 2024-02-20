package com.example.slothslider;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Intent intent;
    private Activity activity=this;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        Button buttonLogin = findViewById(R.id.IniciarSesion);

        buttonLogin.setOnClickListener(v -> iniciarSesion());

        Button buttonRegister = findViewById(R.id.CrearCuenta);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RegisterActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    private void iniciarSesion(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Validaciones de los campos...

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        //Iniciar Sesión de forma exitosa y navegar a la pantalla principal
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }else {
                        //Error al iniciar sesión

                        Toast.makeText(LoginActivity.this, "Error al iniciar sesión: "
                        + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

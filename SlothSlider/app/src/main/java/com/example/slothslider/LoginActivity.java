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

    // Declaración de variables de instancia
    private EditText editTextEmail, editTextPassword;
    private Intent intent;
    private Activity activity=this;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);

        // Inicialización de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Vinculación de variables con elementos de la interfaz de usuario
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        Button buttonLogin = findViewById(R.id.IniciarSesion);

        // Configuración del OnClickListener para el botón de inicio de sesión
        buttonLogin.setOnClickListener(v -> iniciarSesion());

        // Configuración del OnClickListener para el botón de registro
        Button buttonRegister = findViewById(R.id.CrearCuenta);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear una nueva instancia de Intent para pasar de LoginActivity a RegisterActivity
                Intent intent = new Intent(activity, RegisterActivity.class);
                // Iniciar la actividad de registro
                activity.startActivity(intent);
            }
        });

    }

    private void iniciarSesion(){
        // Obtención de datos de los EditText
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validaciones de los campos ingresados por el usuario

        // Iniciar sesión en Firebase Authentication

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        //Iniciar Sesión de forma exitosa y navegar a la pantalla principal
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }else {
                        //Error al iniciar sesión: Mostrar mensaje de error

                        Toast.makeText(LoginActivity.this, "Error al iniciar sesión: "
                        + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.slothslider;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
public class RegisterActivity extends AppCompatActivity {

    // Declaración de variables de instancia
    private EditText editTextNombre, editTextEmail, editTextPassword, editTextPassword2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialización de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Vinculación de variables con elementos de la interfaz de usuario
        editTextNombre = findViewById(R.id.newUser);
        editTextEmail = findViewById(R.id.correo);
        editTextPassword = findViewById(R.id.newPassword);
        editTextPassword2 = findViewById(R.id.confirmPassword);
        Button buttonRegister = findViewById(R.id.createUser);

        // Configuración del OnClickListener para el botón de registro
        buttonRegister.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        // Obtención de datos de los EditText
        String nombre = editTextNombre.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();

        // Validaciones de los datos ingresados por el usuario
        if(TextUtils.isEmpty(nombre)){
            editTextNombre.setError("Ingresa tu nombre de usuario");
            return;
        }
        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Ingresa tu correo electrónico");
            return;
        }
        if(TextUtils.isEmpty(password)|| password.length() <6 ){
            editTextPassword.setError("Ingresa una contraseña válida (mínimo 6 caracteres)");
            return;
        }
        if(TextUtils.isEmpty(password2) || !password2.equals(password)){
            editTextPassword2.setError("La contraseña debe ser igual a la anterior");
            return;
        }

        // Creación de usuario en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        // Registro exitoso: Obtener usuario actual y guardar datos adicionales en Realtime Database
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Crear un nuevo objeto Usuario con nombre y correo
                        Usuario nuevoUsuario = new Usuario(nombre,email);
                        // Guardar datos en la base de datos en tiempo real de Firebase
                        FirebaseDatabase.getInstance().getReference("usuarios")
                                .child(user.getUid())
                                .setValue(nuevoUsuario)
                                .addOnCompleteListener(taskDb -> {
                                    if(taskDb.isSuccessful()){
                                        // Éxito al guardar datos en la base de datos
                                        Toast.makeText(RegisterActivity.this, "Registro exitoso",
                                                Toast.LENGTH_SHORT).show();
                                        // Redirigir a la actividad de inicio de sesión
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();
                                    }else {
                                        // Error al guardar datos en la base de datos
                                        Toast.makeText(RegisterActivity.this, "Error al guardar datos: "
                                                + taskDb.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        // Registro fallido: Mostrar mensaje de error
                        Log.e("TagError", task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "Registro fallido: "+
                                task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Clase estática interna que representa un objeto Usuario
    static class Usuario{
        public String nombre, correo;
        public Usuario (String nombre, String correo){
            this.nombre = nombre;
            this.correo = correo;
        }
    }


}


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
    private EditText editTextNombre, editTextEmail, editTextPassword, editTextPassword2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editTextNombre = findViewById(R.id.newUser);
        editTextEmail = findViewById(R.id.correo);
        editTextPassword = findViewById(R.id.newPassword);
        editTextPassword2 = findViewById(R.id.confirmPassword);
        Button buttonRegister = findViewById(R.id.createUser);

        buttonRegister.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();

        //Validaciones...
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

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        //Guardar datos adicionales en Realtime Database
                        Usuario nuevoUsuario = new Usuario(nombre,email);
                        FirebaseDatabase.getInstance().getReference("usuarios")
                                .child(user.getUid())
                                .setValue(nuevoUsuario)
                                .addOnCompleteListener(taskDb -> {
                                    if(taskDb.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Registro exitoso",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Error al guardar datos: "
                                                + taskDb.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        Log.e("TagError", task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "Registro fallido: "+
                                task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    static class Usuario{
        public String nombre, correo;
        public Usuario (String nombre, String correo){
            this.nombre = nombre;
            this.correo = correo;
        }
    }


}

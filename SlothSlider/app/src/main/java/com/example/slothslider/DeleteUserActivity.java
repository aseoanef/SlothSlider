package com.example.slothslider;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class DeleteUserActivity extends AppCompatActivity {
    private EditText editTextPassword;
    private FirebaseAuth mAuth;
    private Activity activity=this;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        mAuth = FirebaseAuth.getInstance();
        editTextPassword = findViewById(R.id.password);
        Button buttonDeleteUser = findViewById(R.id.confirmar);

        buttonDeleteUser.setOnClickListener(v -> deleteUser());
    }

    private void deleteUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        String password = editTextPassword.getText().toString().trim();

        //Verificar contraseña antes de eliminar la cuenta

        if (user != null) {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), password);
            user.reauthenticate(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                     user.delete().addOnCompleteListener(taskDelete -> {
                        if (taskDelete.isSuccessful()) {
                            Toast.makeText(DeleteUserActivity.this, "Cuenta eliminada correctamente", Toast.LENGTH_SHORT).show();
                            //Una vez eliminada la cuenta, volveremos a la pantalla de registro
                            System.exit(0);

                        } else {
                            Toast.makeText(DeleteUserActivity.this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(DeleteUserActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}


package com.example.slothslider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.slothslider.R;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView usernameTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        profileImageView = findViewById(R.id.profileImageView);
        usernameTextView = findViewById(R.id.usernameTextView);

        String username = "Nombre de Usuario"; // Aquí deberías obtener el nombre de usuario real

        int profileImageResource = R.drawable.koala;

        usernameTextView.setText(username);
        profileImageView.setImageResource(profileImageResource);
    }
}
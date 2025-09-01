package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_contato);

        // Adiciona fragment da sidebar
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sidebar_container, new SidebarFragment())
                    .commit();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        EditText nameInput = findViewById(R.id.nameInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText messageInput = findViewById(R.id.messageInput);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String nome = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String mensagem = messageInput.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || mensagem.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mensagem enviada! Obrigado pelo contato.", Toast.LENGTH_LONG).show();
                nameInput.setText("");
                emailInput.setText("");
                messageInput.setText("");
            }
        });
    }
}
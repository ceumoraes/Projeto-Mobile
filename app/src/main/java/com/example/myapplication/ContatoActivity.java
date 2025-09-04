package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

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
                // Monta o Intent para enviar e-mail
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"celestemoraes1706@gmail.com"}); // Altere para o e-mail que irá receber
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
                intent.putExtra(Intent.EXTRA_TEXT, "Nome: " + nome + "\nEmail: " + email + "\nMensagem: " + mensagem);

                try {
                    startActivity(Intent.createChooser(intent, "Enviar e-mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Não há nenhum aplicativo de e-mail instalado.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
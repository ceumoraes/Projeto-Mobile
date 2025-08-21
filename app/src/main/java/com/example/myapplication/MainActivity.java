package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private ImageButton menuButton;
    private DrawerLayout drawerLayout;
    private TextView trabalhos, selos, contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa as views
        menuButton = findViewById(R.id.menuButton);
        drawerLayout = findViewById(R.id.drawerLayout);
        trabalhos = findViewById(R.id.textView3);
        selos = findViewById(R.id.textView4);
        contato = findViewById(R.id.textView5);

        // Clique no botão hambúrguer
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Cliques nos textos da sidebar
        trabalhos.setOnClickListener(v -> {
            // ação para Trabalhos
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        selos.setOnClickListener(v -> {
            // ação para Selos
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        contato.setOnClickListener(v -> {
            // ação para Contato
            drawerLayout.closeDrawer(GravityCompat.START);
        });
    }
}

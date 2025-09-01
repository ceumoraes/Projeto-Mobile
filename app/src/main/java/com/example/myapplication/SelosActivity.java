package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SelosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selos_layout);

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

        // Anima selo1 vindo da ESQUERDA
        View selo1 = findViewById(R.id.selo1);
        selo1.setTranslationX(-1000f);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(selo1, "translationX", 0f);
        anim1.setDuration(2000);
        anim1.start();

        // Anima selo2 vindo da DIREITA
        View selo2 = findViewById(R.id.selo2);
        selo2.setTranslationX(1000f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(selo2, "translationX", 0f);
        anim2.setDuration(2200);
        anim2.start();

        // Anima selo3 vindo da ESQUERDA
        View selo3 = findViewById(R.id.selo3);
        selo3.setTranslationX(-1000f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(selo3, "translationX", 0f);
        anim3.setDuration(2400);
        anim3.start();
    }
}
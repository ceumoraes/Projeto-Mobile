package com.example.myapplication;

import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private List<Projeto> projetosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        gridLayout = findViewById(R.id.gridLayout);

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("projetos");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                projetosList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Projeto projeto = snapshot.getValue(Projeto.class);
                    if (projeto != null) {
                        projetosList.add(projeto);
                    }
                }
                preencherGridComProjetos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Trate erros aqui
            }
        });
    }

    private void preencherGridComProjetos() {
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(2);

        for (Projeto projeto : projetosList) {
            CardView cardView = new CardView(this);
            cardView.setRadius(16);
            cardView.setCardElevation(8);
            cardView.setUseCompatPadding(true);

            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(8, 8, 8, 8);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            imageView.setLayoutParams(imageParams);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setBackgroundResource(R.drawable.dashed_border);
            imageView.setPadding(8, 8, 8, 8);

            Picasso.get()
                    .load(projeto.getImageUrl())
                    .into(imageView);

            TextView nomeView = new TextView(this);
            nomeView.setText(projeto.getNome());
            nomeView.setTextSize(16);
            nomeView.setTextAlignment(cardView.TEXT_ALIGNMENT_CENTER);
            nomeView.setGravity(Gravity.CENTER);

            nomeView.setTypeface(ResourcesCompat.getFont(this, R.font.montserrat_bold));

            itemLayout.addView(imageView);
            itemLayout.addView(nomeView);

            cardView.addView(itemLayout);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(params);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProjetoActivity.class);
                intent.putExtra("projeto", projeto);
                startActivity(intent);
            });

            gridLayout.addView(cardView);
        }
    }
}
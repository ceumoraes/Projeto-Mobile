package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.squareup.picasso.Picasso;
import java.util.List;
import android.util.TypedValue;

public class ProjetoActivity extends AppCompatActivity {

    private ImageView mainImage;
    private LinearLayout thumbContainer;
    private TextView nomeProjeto;
    private TextView descricaoProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projeto_layout);

        mainImage = findViewById(R.id.mainImage);
        thumbContainer = findViewById(R.id.thumbContainer);
        nomeProjeto = findViewById(R.id.nomeProjeto);
        descricaoProjeto = findViewById(R.id.descricaoProjeto);

        Projeto projeto = (Projeto) getIntent().getSerializableExtra("projeto");
        if (projeto == null) {
            Toast.makeText(this, "Projeto não recebido!", Toast.LENGTH_LONG).show();
            return;
        }

        nomeProjeto.setText(projeto.getNome());
        descricaoProjeto.setText(projeto.getDescricao());

        Picasso.get().load(projeto.getImageUrl()).into(mainImage);

        List<String> galeria = projeto.getGaleria();
        thumbContainer.removeAllViews();
        if (galeria != null && !galeria.isEmpty()) {
            int sizeInDp = 60;
            int sizeInPx = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, sizeInDp, getResources().getDisplayMetrics());

            for (int i = 0; i < galeria.size(); i++) {
                final String url = galeria.get(i);
                ImageView thumb = new ImageView(this);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                params.setMargins(12, 4, 12, 4);
                thumb.setLayoutParams(params);

                thumb.setPadding(6, 6, 6, 6);
                thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);

                // dashed_border com corner radius no drawable
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    thumb.setForeground(ContextCompat.getDrawable(this, R.drawable.dashed_border));
                } else {
                    thumb.setBackgroundResource(R.drawable.dashed_border);
                }

                // Converta 24dp para px (exemplo)
                int radiusInDp = 60;
                int radiusInPx = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, radiusInDp, getResources().getDisplayMetrics());

                Picasso.get()
                        .load(url)
                        .transform(new RoundedCornersTransformation(radiusInPx, 0))
                        .into(thumb);

                thumb.setOnClickListener(v -> {
                    Picasso.get().load(url).into(mainImage);
                });

                thumbContainer.addView(thumb);
            }
        }
    }
}
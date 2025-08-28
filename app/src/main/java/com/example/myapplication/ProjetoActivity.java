package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

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

        // Se adicionar os campos abaixo no XML, descomente:
        // nomeProjeto = findViewById(R.id.nomeProjeto);
        // descricaoProjeto = findViewById(R.id.descricaoProjeto);

        Projeto projeto = (Projeto) getIntent().getSerializableExtra("projeto");
        if (projeto != null) {
            Picasso.get().load(projeto.getImageUrl()).into(mainImage);

            // nomeProjeto.setText(projeto.getNome());
            // descricaoProjeto.setText(projeto.getDescricao());

            List<String> galeria = projeto.getGaleria();
            thumbContainer.removeAllViews();
            if (galeria != null && galeria.size() > 0) {
                for (String url : galeria) {
                    ImageView thumb = new ImageView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            0, LinearLayout.LayoutParams.MATCH_PARENT, 1f
                    );
                    params.setMargins(2, 2, 2, 2);
                    thumb.setLayoutParams(params);
                    thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Picasso.get().load(url).into(thumb);
                    thumbContainer.addView(thumb);
                }
            }
        }
    }
}
package com.example.myapplication;
// Define o pacote da classe. Todos os arquivos dentro do mesmo pacote
// podem se referir entre si sem precisar de imports.
// Organiza o código e evita conflitos de nomes com classes de outros pacotes.


// Importa classes do Android:
import android.os.Build;
import android.os.Bundle;
// - Build: permite verificar a versão do Android em tempo de execução.
// - Bundle: usado para salvar/restaurar estado da Activity.

// Importa classes de interface gráfica:
import android.widget.ImageView; // - ImageView: para exibir imagens.
import android.widget.LinearLayout;
// - LinearLayout: container que organiza elementos em linha ou coluna.

import android.widget.TextView; // - TextView: para exibir textos.
import android.widget.Toast; // - Toast: para mostrar mensagens rápidas ao usuário.

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
// Importa a classe que permite aplicar **cantos arredondados nas imagens carregadas com Picasso.

import androidx.appcompat.app.AppCompatActivity;
// Importa a classe base de Activity compatível com versões antigas do Android.

import androidx.core.content.ContextCompat;
// Permite acessar recursos do app de forma compatível com várias versões do Android.

import androidx.core.view.GravityCompat;
// Permite usar constantes para abrir o DrawerLayout em START ou END.

import androidx.drawerlayout.widget.DrawerLayout;
// Importa o layout que suporta um menu lateral deslizante (DrawerLayout).

import android.widget.ImageButton;
// Importa ImageButton, botão que usa imagem como conteúdo.

import com.squareup.picasso.Picasso;
// Biblioteca Picasso para carregar imagens de forma assíncrona de URLs ou arquivos.

import java.util.List;
// Importa List, para armazenar listas de objetos (aqui, URLs da galeria de imagens).

import android.util.TypedValue;
// Importa TypedValue para converter unidades dp em pixels de forma programática.

public class ProjetoActivity extends AppCompatActivity {
    // Declara a classe ProjetoActivity, que herda de AppCompatActivity.
// Essa classe controla a tela que mostra os detalhes de um projeto.

    private ImageView mainImage;
    // Variável para a ImageView principal, que mostrará a imagem do projeto.

    private LinearLayout thumbContainer;
    // LinearLayout horizontal que conterá as miniaturas da galeria.
    private TextView nomeProjeto;
    // TextView que exibirá o nome do projeto.
    private TextView descricaoProjeto;
    // TextView que exibirá a descrição do projeto.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projeto_layout);
        // metodo chamado quando a Activity é criada.
        // setContentView define o layout XML que a Activity vai usar.


        // Adiciona fragment da sidebar
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sidebar_container, new SidebarFragment())
                    .commit();
        }
        // Se a Activity está sendo criada pela primeira vez
        // (savedInstanceState == null),
        // adiciona o fragment do menu lateral (SidebarFragment) no container R.id.sidebar_container.

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        // Inicializa o DrawerLayout e o botão de menu.
        // Ao clicar no botão, o menu lateral (drawer) é aberto do lado START (esquerda).



        mainImage = findViewById(R.id.mainImage);
        thumbContainer = findViewById(R.id.thumbContainer);
        nomeProjeto = findViewById(R.id.nomeProjeto);
        descricaoProjeto = findViewById(R.id.descricaoProjeto);
        // Inicializa as variáveis para acessar os elementos do layout.



        Projeto projeto = (Projeto) getIntent().getSerializableExtra("projeto");
        if (projeto == null) {
            Toast.makeText(this, "Projeto não recebido!", Toast.LENGTH_LONG).show();
            return;
        }
        // Recebe o objeto Projeto enviado via Intent da Activity anterior.
        // Se não existir, mostra uma mensagem e encerra a execução do onCreate.

        nomeProjeto.setText(projeto.getNome());
        descricaoProjeto.setText(projeto.getDescricao());
        // Preenche as TextViews com o nome e a descrição do projeto.

        Picasso.get().load(projeto.getImageUrl()).into(mainImage);
        // Carrega a imagem principal do projeto usando Picasso.
        // A imagem é carregada de forma assíncrona, sem travar a UI.

        List<String> galeria = projeto.getGaleria();
        thumbContainer.removeAllViews();
        // Obtém a lista de imagens da galeria.
        // Remove qualquer View antiga do container de miniaturas.
        if (galeria != null && !galeria.isEmpty()) {
            int sizeInDp = 60;
            int sizeInPx = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, sizeInDp, getResources().getDisplayMetrics());
            // Define o tamanho das miniaturas em dp e converte
            // para pixels de acordo com a densidade da tela.


            for (int i = 0; i < galeria.size(); i++) {
                final String url = galeria.get(i);
                ImageView thumb = new ImageView(this);
                // Cria dinamicamente cada ImageView que será a miniatura da galeria.

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeInPx, sizeInPx);
                params.setMargins(12, 4, 12, 4);
                thumb.setLayoutParams(params);
                // Define largura, altura e margens das miniaturas.

                thumb.setPadding(6, 6, 6, 6);
                thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Ajusta o padding interno e define que a imagem
                // preencha o ImageView cortando partes excedentes.

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    thumb.setForeground(ContextCompat.getDrawable(this, R.drawable.dashed_border));
                } else {
                    thumb.setBackgroundResource(R.drawable.dashed_border);
                }
                // Aplica borda tracejada na miniatura:
                // - Android M+ usa Foreground
                // - versões antigas usam Background


                int radiusInDp = 60;
                int radiusInPx = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, radiusInDp, getResources().getDisplayMetrics());
                // Converte o raio dos cantos arredondados de dp para pixels.
                Picasso.get()
                        .load(url)
                        .transform(new RoundedCornersTransformation(radiusInPx, 0))
                        .into(thumb);
                // Carrega a miniatura usando Picasso e aplica cantos arredondados.

                thumb.setOnClickListener(v -> {
                    Picasso.get().load(url).into(mainImage);
                });
                // Quando a miniatura é clicada
                // a imagem principal é atualizada com a imagem correspondente.

                thumbContainer.addView(thumb);
                // Adiciona a miniatura ao container LinearLayout horizontal.
            }
        }
    }
}
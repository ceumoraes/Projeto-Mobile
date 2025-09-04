package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SidebarFragment extends Fragment {

    public SidebarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sidebar_layout, container, false);

        TextView trabalhos = view.findViewById(R.id.textView3);
        TextView selos = view.findViewById(R.id.textView4);
        TextView contato = view.findViewById(R.id.textView5);

        ImageView behanceLogo = view.findViewById(R.id.imageView3);
        ImageView InstagramLogo = view.findViewById(R.id.imageView5);
        ImageView YoutubeLogo = view.findViewById(R.id.imageView6);
        ImageView BlueskyLogo = view.findViewById(R.id.imageView4);

        trabalhos.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        selos.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), SelosActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        contato.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), ContatoActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        behanceLogo.setOnClickListener(v -> {
            abrirLink("https://www.behance.net/kadeajojo");
        });
        InstagramLogo.setOnClickListener(v -> {
            abrirLink("https://www.instagram.com/kadeajojo/");
        });
        YoutubeLogo.setOnClickListener(v -> {
            abrirLink("https://www.youtube.com/@jojoslullaby");
        });
        BlueskyLogo.setOnClickListener(v -> {
            abrirLink("https://bsky.app/profile/kadeajojo.bsky.social");
        });

        return view;
    }

    private void abrirLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
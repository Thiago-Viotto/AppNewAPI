package com.thiago_viotto.projetonewsapi.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thiago_viotto.projetonewsapi.R;

import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_AUTOR;
import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_DATA;
import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_DESCRICAO;
import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_IMG_URL;
import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_TITULO;
import static com.thiago_viotto.projetonewsapi.fragment.EsporteFragment.EXTRA_URL;


public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitydetalhe);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_IMG_URL);
        String nomeAutor = intent.getStringExtra(EXTRA_AUTOR);
        String data = intent.getStringExtra(EXTRA_DATA);
        String descricao = intent.getStringExtra(EXTRA_DESCRICAO);
        String titulo = intent.getStringExtra(EXTRA_TITULO);
        String url = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = findViewById(R.id.imgDetalhe);
        TextView txtTituloDetalhe = findViewById(R.id.txtTituloDetalhe);
        TextView txtNomeAutorDetalhe = findViewById(R.id.txtNomeAutorDetalhe);
        TextView txtDataDetalhe = findViewById(R.id.txtDataDetalhe);
        TextView txtDescricaoDetalhe = findViewById(R.id.txtDescricaoDetalhe);
        TextView txtURL = findViewById(R.id.txtURLDetalhe);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        txtNomeAutorDetalhe.setText(nomeAutor);
        txtDataDetalhe.setText(data);
        txtDescricaoDetalhe.setText(descricao);
        txtTituloDetalhe.setText(titulo);
        txtURL.setText(url);
    }
}

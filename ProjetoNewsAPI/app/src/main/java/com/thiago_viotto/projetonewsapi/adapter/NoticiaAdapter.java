package com.thiago_viotto.projetonewsapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thiago_viotto.projetonewsapi.R;
import com.thiago_viotto.projetonewsapi.model.Noticia;

import java.util.ArrayList;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaAdapterViewHolder> {
    private Context mContext;
    private ArrayList<Noticia> itemNoticias;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemclick(int position);
    }

    public NoticiaAdapter(Context context, ArrayList<Noticia> noticias){
        mContext = context;
        itemNoticias = noticias;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public NoticiaAdapterViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_noticias, parent, false);
        return new NoticiaAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticiaAdapterViewHolder holder, int position) {
        Noticia noticiaAtual = itemNoticias.get(position);

        String imagemURL = noticiaAtual.getImagemURL();
        String autor = noticiaAtual.getAutor();
        String titulo = noticiaAtual.getTitulo();
        String descricao = noticiaAtual.getDescricao();
        String data = noticiaAtual.getData();
        String url = noticiaAtual.getUrl();

        holder.autor.setText(autor);
        holder.titulo.setText(titulo);
        holder.descricao.setText(descricao);
        holder.data.setText(data);
        holder.url.setText(url);
        Picasso.with(mContext).load(imagemURL).fit().centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemNoticias.size();
    }


    public class NoticiaAdapterViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView autor;
        public TextView titulo;
        public TextView descricao;
        public TextView data;
        public TextView url;

        public NoticiaAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            autor = itemView.findViewById(R.id.txtAutor);
            titulo = itemView.findViewById(R.id.txtTitulo);
            descricao = itemView.findViewById(R.id.txtDescricao);
            data = itemView.findViewById(R.id.txtData);
            url = itemView.findViewById(R.id.txtURL);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemclick(position);
                        }
                    }

                }
            });
        }

    }
}

package com.thiago_viotto.projetonewsapi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thiago_viotto.projetonewsapi.adapter.NoticiaAdapter;
import com.thiago_viotto.projetonewsapi.model.DetalheActivity;
import com.thiago_viotto.projetonewsapi.Interface.JSON;
import com.thiago_viotto.projetonewsapi.R;
import com.thiago_viotto.projetonewsapi.model.Noticia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CienciaFragment extends Fragment implements JSON,NoticiaAdapter.OnItemClickListener {
    View view;
    private RecyclerView mRecyclerView;
    private ArrayList<Noticia> ciencias;
    private NoticiaAdapter noticiaAdapter;
    private RequestQueue requestQueue;
    public static final String EXTRA_IMG_URL = "imagemURL";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITULO = "Título";
    public static final String EXTRA_AUTOR = "Nome do Autor";
    public static final String EXTRA_DATA = "data";
    public static final String EXTRA_DESCRICAO = "descricao";

    public CienciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null)
            view = inflater.inflate(R.layout.fragment_ciencia, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_viewCiencia);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ciencias = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON();

        return  view;
    }

    @Override
    public void parseJSON() {
        String url = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/Science/1.json?api-key=f9c6c21d6eb44a46be9a1a3b577d95a3";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");  //procura array com nome "results"
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONArray res;
                        String res2 = "";
                        JSONObject search = jsonArray.getJSONObject(i); //percorre elementos
                        JSONArray imagemURL = search.getJSONArray("media");
                        for(int j = 0; j<imagemURL.length(); j++){
                            JSONObject search2 = imagemURL.getJSONObject(j); //percorre elementos
                            res = search2.getJSONArray("media-metadata");
                            JSONObject search3 = res.getJSONObject(2); //percorre elementos
                            res2 = search3.getString("url");
                        }
                        String titulo = search.getString("title");
                        String autor = (search.getString("byline"));
                        String descricao = search.getString("abstract");
                        String data = search.getString("published_date");
                        String url = search.getString("url");

                        ciencias.add(new Noticia(res2,autor,titulo,descricao,data,url)); //adiciona elementos na lista
                    }

                    noticiaAdapter = new NoticiaAdapter(getContext(),ciencias);
                    mRecyclerView.setAdapter(noticiaAdapter);
                    noticiaAdapter.setOnItemClickListener(CienciaFragment.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemclick(int position) {
        Intent detailIntent = new Intent(getActivity(),DetalheActivity.class);
        Noticia noticia = ciencias.get(position);

        detailIntent.putExtra(EXTRA_IMG_URL, noticia.getImagemURL());
        detailIntent.putExtra(EXTRA_TITULO, noticia.getTitulo());
        detailIntent.putExtra(EXTRA_AUTOR, noticia.getAutor());
        detailIntent.putExtra(EXTRA_DATA, noticia.getData());
        detailIntent.putExtra(EXTRA_URL, noticia.getUrl());
        detailIntent.putExtra(EXTRA_DESCRICAO, noticia.getDescricao());

        startActivity(detailIntent);
    }

}

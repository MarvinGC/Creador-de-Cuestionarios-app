package com.devmarvin.cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentListaPreguntas extends Fragment {
    private RecyclerView mPreguntaRecyclerView;
    private PreguntaAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mPreguntaRecyclerView =  v.findViewById(R.id.pregunta_recycler);

        /*scroling vertical*/
        mPreguntaRecyclerView.setLayoutManager(new
                LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pregunta_lista, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nueva_pregunta:
                Intent intent = new Intent(getActivity(), ActivityCrearPregunta.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI(){
        LabPreguntas prgLab = LabPreguntas.get(getActivity());
        List<Pregunta> prgs = prgLab.getPreguntas();

        mAdapter = new PreguntaAdapter(prgs);
        mPreguntaRecyclerView.setAdapter(mAdapter);
    }
    private class ContenedorPregunta extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitulo;
        private Pregunta mPreg;
        public ContenedorPregunta(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_lista, parent, false));
            itemView.setOnClickListener(this);
            mTitulo = (TextView) itemView.findViewById(R.id.pregunta_titulo);
        }
        @Override
        public void onClick(View view){
            Intent intent = ActivityPregunta.newIntent(getActivity(),mPreg.getId());
            startActivity(intent);
        }
        public void bind(Pregunta preg){
            mPreg = preg;
            mTitulo.setText(mPreg.getTextPregunta());
        }
    }
    private class PreguntaAdapter extends RecyclerView.Adapter<ContenedorPregunta>{
        private List<Pregunta> mPreguntas;

        public PreguntaAdapter(List<Pregunta> Preg){
            mPreguntas = Preg;
        }
        @Override
        public ContenedorPregunta onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layInf = LayoutInflater.from(getActivity());

            return new ContenedorPregunta(layInf, parent);
        }

        @Override
        public void onBindViewHolder(ContenedorPregunta holder, int position) {
            Pregunta pregunta = mPreguntas.get(position);
            holder.bind(pregunta);
        }

        @Override
        public int getItemCount() {
            return mPreguntas.size();
        }
    }

}

package com.devmarvin.cuestionarios;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

public class FragmentPregunta extends Fragment {

    private Button botonOpcion1;
    private Button botonOpcion2;
    private Button botonOpcion3;
    private Button botonOpcion4;
    private TextView texto;
    private Pregunta mPregunta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)  getActivity().getIntent()
                .getSerializableExtra(ActivityPregunta.EXTRA_PREG_ID);
        mPregunta = LabPreguntas.get(getActivity()).getPregunta(id);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        LabPreguntas.get(getActivity()).UpdatePregunta(mPregunta);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pregunta, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editar_pregunta:
                Intent intent = ActivityEditarPregunta.newIntent(getActivity(),mPregunta.getId());
                startActivity(intent);
                return true;
            case R.id.borrar_pregunta:
                LabPreguntas.get(getActivity()).removePregunta(mPregunta);
                Intent intent2 = new Intent(getActivity(), ActivityListaPreguntas.class);
                startActivity(intent2);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pregunta_fragment,container,false);

        botonOpcion1 = (Button) v.findViewById(R.id.opcion_1);
        botonOpcion2 = (Button) v.findViewById(R.id.opcion_2);
        botonOpcion3 = (Button) v.findViewById(R.id.opcion_3);
        botonOpcion4 = (Button) v.findViewById(R.id.opcion_4);
        texto = (TextView) v.findViewById(R.id.question_text);

        texto.setText(mPregunta.getTextPregunta());

        botonOpcion1.setText(mPregunta.getAnswers()[0]);
        botonOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(0,view);
            }
        });
        botonOpcion2.setText(mPregunta.getAnswers()[1]);
        botonOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(1,view);;
            }
        });
        botonOpcion3.setText(mPregunta.getAnswers()[2]);
        botonOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(2,view);
            }
        });
        botonOpcion4.setText(mPregunta.getAnswers()[3]);
        botonOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(3,view);
            }
        });
        return v;
    }

    public void respuesta(int r,View v){
        if(mPregunta.getAnswer() == r){

            Snackbar.make(v, R.string.correct_toast, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            Snackbar.make(v, R.string.incorrect_toast, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}

package com.devmarvin.cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

public class FragmentCrearPregunta extends Fragment {
    protected EditText preguntaEditText;
    protected EditText respuesta1;
    protected EditText respuesta2;
    protected EditText respuesta3;
    protected EditText respuesta4;
    protected RadioButton respuestaRadioButton1;
    protected RadioButton respuestaRadioButton2;
    protected RadioButton respuestaRadioButton3;
    protected RadioButton respuestaRadioButton4;
    protected Button mOkButton;
    protected Pregunta mPregunta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crear_pregunta_fragment, container,false);
        mPregunta = new Pregunta();

        preguntaEditText = v.findViewById(R.id.edit_text_pregunta);
        respuesta1 = v.findViewById(R.id.respuesta_1);
        respuesta2 = v.findViewById(R.id.respuesta_2);
        respuesta3 = v.findViewById(R.id.respuesta_3);
        respuesta4 = v.findViewById(R.id.respuesta_4);
        respuestaRadioButton1 = v.findViewById(R.id.boton_respuesta_uno);
        respuestaRadioButton2 = v.findViewById(R.id.boton_respuesta_dos);
        respuestaRadioButton3 = v.findViewById(R.id.boton_respuesta_tres);
        respuestaRadioButton4 = v.findViewById(R.id.boton_respuesta_cuatro);

        mOkButton = v.findViewById(R.id.Ok_Button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPregunta.setTextPregunta(preguntaEditText.getText().toString());
                mPregunta.setAnswer( respuesta1.getText().toString(), 0);
                mPregunta.setAnswer( respuesta2.getText().toString(), 1);
                mPregunta.setAnswer( respuesta3.getText().toString(), 2);
                mPregunta.setAnswer( respuesta4.getText().toString(), 3);
                mPregunta.setAnswer(revsarCheckBoxes(
                        respuestaRadioButton1,
                        respuestaRadioButton2,
                        respuestaRadioButton3,
                        respuestaRadioButton4
                ));
                LabPreguntas.get(getActivity()).addPregunta(mPregunta);
                Intent intent = new Intent(getActivity(), ActivityListaPreguntas.class);
                startActivity(intent);
                getActivity().finish();
            }

            private int revsarCheckBoxes(RadioButton rUno, RadioButton rDos, RadioButton rTres, RadioButton rCuatro) {
                if(rUno.isChecked())
                    return 0;
                if(rDos.isChecked())
                    return 1;
                if(rTres.isChecked())
                    return 2;
                if(rCuatro.isChecked())
                    return 3;
                else
                    return -1;
            }
        });
        return v;
    }
}

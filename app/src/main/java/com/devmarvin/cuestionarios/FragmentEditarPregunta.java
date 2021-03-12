package com.devmarvin.cuestionarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.UUID;

public class FragmentEditarPregunta extends FragmentCrearPregunta {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)  getActivity().getIntent()
                .getSerializableExtra(ActivityEditarPregunta.EXTRA_PREG_ID);
        mPregunta = LabPreguntas.get(getActivity()).getPregunta(id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.crear_pregunta_fragment, container,false);

        preguntaEditText = v.findViewById(R.id.edit_text_pregunta);
        respuesta1 = v.findViewById(R.id.respuesta_1);
        respuesta2 = v.findViewById(R.id.respuesta_2);
        respuesta3 = v.findViewById(R.id.respuesta_3);
        respuesta4 = v.findViewById(R.id.respuesta_4);
        respuestaRadioButton1 = v.findViewById(R.id.boton_respuesta_uno);
        respuestaRadioButton2 = v.findViewById(R.id.boton_respuesta_dos);
        respuestaRadioButton3 = v.findViewById(R.id.boton_respuesta_tres);
        respuestaRadioButton4 = v.findViewById(R.id.boton_respuesta_cuatro);

        preguntaEditText.setText(mPregunta.getTextPregunta());
        respuesta1.setText(mPregunta.getAnswers()[0]);
        respuesta2.setText(mPregunta.getAnswers()[1]);
        respuesta3.setText(mPregunta.getAnswers()[2]);
        respuesta4.setText(mPregunta.getAnswers()[3]);



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
                LabPreguntas.get(getActivity()).UpdatePregunta(mPregunta);
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

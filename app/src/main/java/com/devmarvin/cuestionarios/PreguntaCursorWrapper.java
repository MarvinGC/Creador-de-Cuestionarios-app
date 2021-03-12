package com.devmarvin.cuestionarios;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.UUID;

import database.PreguntaDbSchema;

public class PreguntaCursorWrapper  extends CursorWrapper {
    public PreguntaCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Pregunta getPregunta(){

        String uuidString = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.UUID));
        String pregunta = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.PREGUNTA));
        String respuesta1 = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA1));
        String respuesta2 = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA2));
        String respuesta3 = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA3));
        String respuesta4 = getString(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA4));
        int respuestindex = getInt(getColumnIndex(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTAINX));

        Pregunta p = new Pregunta(UUID.fromString(uuidString));
        p.setTextPregunta(pregunta);
        p.setAnswer(respuesta1,0);
        p.setAnswer(respuesta2,1);
        p.setAnswer(respuesta3,2);
        p.setAnswer(respuesta4,3);
        p.setAnswer(respuestindex);

        return p;
    }
}

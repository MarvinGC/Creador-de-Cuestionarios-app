package com.devmarvin.cuestionarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.PreguntaBaseHelper;
import database.PreguntaDbSchema;

public class LabPreguntas {
    private static LabPreguntas sLabPreguntas;
    private Context mContext;
    private SQLiteDatabase mPreguntas;

    public static LabPreguntas get(Context context){
        if(sLabPreguntas == null){
            sLabPreguntas = new LabPreguntas(context);
        }
        return sLabPreguntas;
    }
    private LabPreguntas(Context context){
        mContext = context.getApplicationContext();
        mPreguntas = new PreguntaBaseHelper(context)
                .getWritableDatabase();
    }
    public void addPregunta(Pregunta p){
        ContentValues values = getContentValues(p);
        mPreguntas.insert(PreguntaDbSchema.PreguntaTable.Name,null,values);
    }
    public void removePregunta(Pregunta p){
        String uuidString = p.getId().toString();
        mPreguntas.delete(PreguntaDbSchema.PreguntaTable.Name,
                PreguntaDbSchema.PreguntaTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private ContentValues getContentValues(Pregunta p) {
        ContentValues values = new ContentValues();
        values.put(PreguntaDbSchema.PreguntaTable.Cols.UUID, p.getId().toString());
        values.put(PreguntaDbSchema.PreguntaTable.Cols.PREGUNTA, p.getTextPregunta());
        values.put(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA1, p.getAnswers()[0]);
        values.put(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA2, p.getAnswers()[1]);
        values.put(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA3, p.getAnswers()[2]);
        values.put(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA4, p.getAnswers()[3]);
        values.put(PreguntaDbSchema.PreguntaTable.Cols.RESPUESTAINX, p.getAnswer());
        return values;
    }
    private PreguntaCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mPreguntas.query(
                PreguntaDbSchema.PreguntaTable.Name,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // grupBY
                null, // having
                null // orderBy
        );
        return new PreguntaCursorWrapper(cursor);
    }

    public void UpdatePregunta(Pregunta p){
        String uuidString = p.getId().toString();
        ContentValues values = getContentValues(p);
        mPreguntas.update(PreguntaDbSchema.PreguntaTable.Name,values,
                PreguntaDbSchema.PreguntaTable.Cols.UUID + " = ?",
                new String[] {uuidString}
        );
    }

    public List<Pregunta> getPreguntas(){
        List<Pregunta> preguntas = new ArrayList<>();
        PreguntaCursorWrapper cursor = queryCrimes(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                preguntas.add(cursor.getPregunta());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return preguntas;
    }

    public Pregunta getPregunta(UUID id) {
        PreguntaCursorWrapper cursor = queryCrimes(
                PreguntaDbSchema.PreguntaTable.Cols.UUID + "= ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPregunta();
        } finally {
            cursor.close();
        }
    }
}

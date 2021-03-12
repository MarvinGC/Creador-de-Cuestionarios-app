package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PreguntaBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "preguntasBase.db";

    public PreguntaBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PreguntaDbSchema.PreguntaTable.Name + "(" +
                "_id integer primary key autoincrement, " +
                PreguntaDbSchema.PreguntaTable.Cols.UUID + "," +
                PreguntaDbSchema.PreguntaTable.Cols.PREGUNTA + "," +
                PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA1 + "," +
                PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA2 + "," +
                PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA3 + "," +
                PreguntaDbSchema.PreguntaTable.Cols.RESPUESTA4 + "," +
                PreguntaDbSchema.PreguntaTable.Cols.RESPUESTAINX + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

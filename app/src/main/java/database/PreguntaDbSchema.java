package database;

public class PreguntaDbSchema {
    public static final class PreguntaTable{
        public static final String Name = "preguntas";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String PREGUNTA = "pregunta";
            public static final String RESPUESTA1 = "respuesta1";
            public static final String RESPUESTA2 = "respuesta2";
            public static final String RESPUESTA3 = "respuesta3";
            public static final String RESPUESTA4 = "respuesta4";
            public static final String RESPUESTAINX = "respuestaIndice";
        }
    }
}

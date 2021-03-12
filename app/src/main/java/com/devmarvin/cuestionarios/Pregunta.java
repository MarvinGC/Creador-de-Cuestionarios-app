package com.devmarvin.cuestionarios;


import java.util.Random;
import java.util.UUID;

public class Pregunta {

    private UUID mId;
    private String textPregunta;
    private String[] mAnswers;
    private int answer;

    public Pregunta(UUID id){
        mAnswers = new String[4];
        answer = new Random().nextInt(4) + 1;
        mId = id;
    }
    public Pregunta(){
        this(UUID.randomUUID());
    }

    public UUID getId() { return mId; }

    public void setId(UUID id) { mId = id; }

    public void setAnswer(String answer, int index){
        mAnswers[index] = answer;
    }

    public String[] getAnswers() { return mAnswers; }

    public void setAnswers(String[] answers) {
        mAnswers = answers;
    }

    public String getTextPregunta() {
        return textPregunta;
    }

    public void setTextPregunta(String textPregunta) {
        this.textPregunta = textPregunta;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}

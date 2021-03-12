package com.devmarvin.cuestionarios;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class ActivityEditarPregunta extends UnicaFragmentActivity {

    public static final String EXTRA_PREG_ID =
            "com.bignerdranch.android.myapplication.pregunta_editar__id";

    public static Intent newIntent(Context packageContext, UUID Id) {
        Intent intent = new Intent(packageContext, ActivityEditarPregunta.class);
        intent.putExtra(EXTRA_PREG_ID, Id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new FragmentEditarPregunta();
    }
}

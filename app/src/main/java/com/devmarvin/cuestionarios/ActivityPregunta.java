package com.devmarvin.cuestionarios;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class ActivityPregunta extends UnicaFragmentActivity {

    public static final String EXTRA_PREG_ID =
            "com.bignerdranch.android.myapplication.pregunta_id";

    public static Intent newIntent(Context packageContext, UUID Id) {
        Intent intent = new Intent(packageContext,ActivityPregunta.class);
        intent.putExtra(EXTRA_PREG_ID, Id);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new FragmentPregunta();
    }
}

package com.devmarvin.cuestionarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class ActivityCrearPregunta extends UnicaFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FragmentCrearPregunta();
    }
}

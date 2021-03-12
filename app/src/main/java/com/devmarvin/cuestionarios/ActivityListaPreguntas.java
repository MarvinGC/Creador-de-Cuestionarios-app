package com.devmarvin.cuestionarios;

import androidx.fragment.app.Fragment;

public class ActivityListaPreguntas extends UnicaFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new FragmentListaPreguntas();
    }
}

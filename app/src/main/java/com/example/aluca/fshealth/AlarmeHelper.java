package com.example.aluca.fshealth;

import android.widget.EditText;
import android.widget.TimePicker;

import com.example.aluca.fshealth.modelo.Remedio;

class AlarmeHelper {

    private final EditText campoNome;
    private final TimePicker campoHorario;

    AlarmeHelper(AlarmeActivity activity) {

        campoNome = activity.findViewById(R.id.remedio_nome);
        campoHorario = activity.findViewById(R.id.remedio_horario);
    }

    Remedio pegaRemedio() {
        Remedio remedio = new Remedio();
        remedio.setNome(campoNome.getText().toString());
        remedio.setHora(campoHorario.getHour());
        remedio.setMinuto(campoHorario.getMinute());

        return remedio;
    }
}

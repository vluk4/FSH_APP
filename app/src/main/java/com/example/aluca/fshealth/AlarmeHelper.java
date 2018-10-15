package com.example.aluca.fshealth;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.aluca.fshealth.modelo.Remedio;

 class AlarmeHelper {

    private final EditText campoNome;
    private final TimePicker campoHorario;
    private Remedio remedio;

    AlarmeHelper(AlarmeActivity activity) {
        campoNome = activity.findViewById(R.id.remedio_nome);
        campoHorario = activity.findViewById(R.id.remedio_horario);
        remedio = new Remedio();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    Remedio pegaRemedio() {
        remedio.setNome(campoNome.getText().toString());
        remedio.setHora(campoHorario.getHour());
        remedio.setMinuto(campoHorario.getMinute());

        return remedio;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void preencheAlarme(Remedio remedio) {
        campoNome.setText(remedio.getNome());
        campoHorario.setHour(remedio.getHora());
        campoHorario.setMinute(remedio.getMinuto());
        this.remedio = remedio;
    }
}

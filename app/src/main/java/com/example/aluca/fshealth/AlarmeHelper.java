package com.example.aluca.fshealth;

import android.widget.EditText;
import android.widget.TimePicker;

import com.example.aluca.fshealth.modelo.Remedio;

public class AlarmeHelper {

    private final EditText campoNome;
    private final TimePicker campoHorario;
    private Remedio remedio;

    public AlarmeHelper(AlarmeActivity activity) {
        this.remedio = new Remedio();
        campoNome = activity.findViewById(R.id.remedio_nome);
        campoHorario = activity.findViewById(R.id.remedio_horario);
    }

    public Remedio pegaRemedio() {
        remedio = new Remedio();
        remedio.setNome(campoNome.getText().toString());
        remedio.setHora(campoHorario.getHour());
        remedio.setMinuto(campoHorario.getMinute());

        return remedio;
    }

    public void preencheAlarme(Remedio remedio) {
        this.remedio = remedio;
        campoNome.setText(remedio.getNome());
        campoHorario.setHour(remedio.getHora());
        campoHorario.setMinute(remedio.getMinuto());
    }
}

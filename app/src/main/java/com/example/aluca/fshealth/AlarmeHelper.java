package com.example.aluca.fshealth;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.aluca.fshealth.modelo.Remedio;

class AlarmeHelper {

    private final EditText campoNome;
    private final EditText campoIntervalo;
    private final EditText campoPosicao;
    private final EditText campoQuantidade;
    private final TimePicker campoHorario;
    private Remedio remedio;

    AlarmeHelper(AlarmeActivity activity) {
        campoNome = activity.findViewById(R.id.remedio_nome);
        campoIntervalo = activity.findViewById(R.id.remedio_intervalo);
        campoPosicao = activity.findViewById(R.id.remedio_posicao);
        campoQuantidade = activity.findViewById(R.id.remedio_quantidade);
        campoHorario = activity.findViewById(R.id.remedio_horario);
        remedio = new Remedio();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    Remedio pegaRemedio() {
        remedio.setNome(campoNome.getText().toString());
        remedio.setIntervalo(campoIntervalo.getText().toString());
        remedio.setPosicao(campoPosicao.getText().toString());
        remedio.setQuantidade(campoQuantidade.getText().toString());
        remedio.setHora(campoHorario.getHour());
        remedio.setMinuto(campoHorario.getMinute());

        return remedio;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void preencheAlarme(Remedio remedio) {
        campoNome.setText(remedio.getNome());
        campoIntervalo.setText(remedio.getIntervalo());
        campoPosicao.setText(remedio.getPosicao());
        campoQuantidade.setText(remedio.getQuantidade());
        campoHorario.setHour(remedio.getHora());
        campoHorario.setMinute(remedio.getMinuto());
        this.remedio = remedio;
    }
}

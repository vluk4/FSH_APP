package com.example.aluca.fshealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListaMedicamentosActivity extends AppCompatActivity {

    private ListView listaMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamentos);

        listaMedicamentos = findViewById(R.id.lista_medicamentos);

        Button novoMedicamento = findViewById(R.id.novo_medicamento);
        novoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProAlarme = new Intent(ListaMedicamentosActivity.this, AlarmeActivity.class);
                startActivity(intentVaiProAlarme);
            }
        });
    }
}

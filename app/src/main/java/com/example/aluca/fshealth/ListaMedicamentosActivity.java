package com.example.aluca.fshealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.aluca.fshealth.DAO.RemedioDAO;
import com.example.aluca.fshealth.modelo.Remedio;

import java.util.List;

public class ListaMedicamentosActivity extends AppCompatActivity {

    private ListView listaMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamentos);

        Button novoMedicamento = findViewById(R.id.novo_medicamento);
        novoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProAlarme = new Intent(ListaMedicamentosActivity.this, AlarmeActivity.class);
                startActivity(intentVaiProAlarme);
            }
        });
    }

    private void carregaLista() {
        RemedioDAO dao = new RemedioDAO(this);
        List<Remedio> remedios = dao.buscaRemedios();
        dao.close();

        listaMedicamentos = findViewById(R.id.lista_medicamentos);
        ArrayAdapter<Remedio> adapter = new ArrayAdapter<Remedio>(this, android.R.layout.simple_list_item_1, remedios);
        listaMedicamentos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}

package com.example.aluca.fshealth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aluca.fshealth.DAO.RemedioDAO;
import com.example.aluca.fshealth.modelo.Remedio;

public class AlarmeActivity extends AppCompatActivity {

    private AlarmeHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        helper = new AlarmeHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alarme, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Remedio remedio = helper.pegaRemedio();
                RemedioDAO dao = new RemedioDAO(this);
                dao.insere(remedio);
                dao.close();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
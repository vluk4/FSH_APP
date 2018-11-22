package com.example.aluca.fshealth;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

import com.example.aluca.fshealth.DAO.RemedioDAO;
import com.example.aluca.fshealth.modelo.Remedio;

import java.util.Calendar;

public class AlarmeActivity extends AppCompatActivity {

    private AlarmeHelper helper;
    TimePicker timePicker;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        helper = new AlarmeHelper(this);
        Intent intent = getIntent();
        Remedio remedio = (Remedio) intent.getSerializableExtra("remedio");
        if (remedio != null) {
            helper.preencheAlarme(remedio);
        }
    }


    public void setAlarm(Calendar calendar){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AlarmeActivity.this,AlarmControl.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
    }
    /*
    public void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmControl.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent, 0);

        alarmManager.cancel(pendingIntent);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alarme, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_alarme_ok:
                //configurando um evento de alarme
                timePicker = findViewById(R.id.remedio_horario);
                Calendar calendar = Calendar.getInstance();
                if(Build.VERSION.SDK_INT>=23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getHour(), timePicker.getMinute(), 0);
                }
                else{
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
                setAlarm(calendar);


                Remedio remedio = helper.pegaRemedio();
                RemedioDAO dao = new RemedioDAO(this);
                if (remedio.getId() != null) {
                    dao.altera(remedio);
                } else {
                    dao.insere(remedio);
                }
                dao.close();

                finish();
                break;

            case R.id.menu_alarme_cancel:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
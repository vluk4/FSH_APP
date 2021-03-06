package com.example.aluca.fshealth.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.aluca.fshealth.modelo.Remedio;

import java.util.ArrayList;
import java.util.List;

public class RemedioDAO extends SQLiteOpenHelper {
    public RemedioDAO(Context context) {
        super(context, "Remedios", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Remedios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, horas INTEGER NOT NULL, minutos INTEGER NOT NULL, " +
                "intervalo INTEGER NOT NULL, posicao INTEGER NOT NULL, quantidade INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Remedios";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Remedio remedio) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosRemedio(remedio);

        db.insert("Remedios", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosRemedio(Remedio remedio) {
        ContentValues dados = new ContentValues();

        dados.put("nome", remedio.getNome());
        dados.put("horas", remedio.getHora());
        dados.put("intervalo", remedio.getIntervalo());
        dados.put("posicao", remedio.getPosicao());
        dados.put("quantidade", remedio.getQuantidade());
        dados.put("minutos", remedio.getMinuto());
        return dados;
    }

    public List<Remedio> buscaRemedios() {
        String sql = "SELECT * FROM Remedios;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Remedio> remedios = new ArrayList<>();
        while (c.moveToNext()) {
            Remedio remedio = new Remedio();
            remedio.setId(c.getLong(c.getColumnIndex("id")));
            remedio.setNome(c.getString(c.getColumnIndex("nome")));
            remedio.setIntervalo(c.getString(c.getColumnIndex("intervalo")));
            remedio.setPosicao(c.getString(c.getColumnIndex("posicao")));
            remedio.setQuantidade(c.getString(c.getColumnIndex("quantidade")));
            remedio.setHora(c.getInt(c.getColumnIndex("horas")));
            remedio.setMinuto(c.getInt(c.getColumnIndex("minutos")));

            remedios.add(remedio);
        }
        c.close();

        return remedios;
    }

    public void deleta(Remedio remedio) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {remedio.getId().toString()};
        db.delete("Remedios", "id = ?", params);
    }

    public void altera(Remedio remedio) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosRemedio(remedio);
        String[] params = {remedio.getId().toString()};
        db.update("Remedios", dados, "id = ?", params);
    }
}

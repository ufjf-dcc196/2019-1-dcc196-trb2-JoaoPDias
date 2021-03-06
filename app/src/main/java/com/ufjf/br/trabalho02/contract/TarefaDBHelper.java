package com.ufjf.br.trabalho02.contract;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefaDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "GerenciadorTarefa.db";

    public TarefaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefaContract.Tarefa.SQL_CREATE_TAREFA);
        db.execSQL(TarefaContract.Etiqueta.SQL_CREATE_ETIQUETA);
        db.execSQL(TarefaContract.EtiquetaTarefa.SQL_CREATE_ETIQUETA_TAREFA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TarefaContract.Tarefa.SQL_DROP_TAREFA);
        db.execSQL(TarefaContract.Etiqueta.SQL_DROP_ETIQUETA);
        db.execSQL(TarefaContract.EtiquetaTarefa.SQL_DROP_ETIQUTA_TAREFA);
        onCreate(db);
    }

}

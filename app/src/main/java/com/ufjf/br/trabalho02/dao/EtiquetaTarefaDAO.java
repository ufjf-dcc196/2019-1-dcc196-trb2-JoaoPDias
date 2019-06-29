package com.ufjf.br.trabalho02.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ufjf.br.trabalho02.contract.TarefaContract;
import com.ufjf.br.trabalho02.contract.TarefaDBHelper;
import com.ufjf.br.trabalho02.model.Etiqueta;
import com.ufjf.br.trabalho02.model.Tarefa;

import java.util.Locale;

public class EtiquetaTarefaDAO {
    private static final EtiquetaTarefaDAO etiquetaDAO = new EtiquetaTarefaDAO();

    private final String TarefasByEtiqueta = String.format(Locale.getDefault(), "SELECT t.* FROM %s t INNER JOIN %s te ON" +
                    " t.%s = te.%s WHERE %s = ? ORDER BY %s",
            TarefaContract.Tarefa.TABLE_NAME,
            TarefaContract.EtiquetaTarefa.TABLE_NAME,
            TarefaContract.Tarefa._ID,
            TarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFA,
            TarefaContract.EtiquetaTarefa.COLUMN_NAME_ETIQUETA,
            TarefaContract.Tarefa.COLUMN_NAME_ESTADO);

    private final String EtiquetasByTarefa = String.format(Locale.getDefault(), "SELECT t.* FROM %s t INNER JOIN %s te ON" +
                    " t.%s = te.%s WHERE %s = ? ORDER BY %s",
            TarefaContract.Etiqueta.TABLE_NAME,
            TarefaContract.EtiquetaTarefa.TABLE_NAME,
            TarefaContract.Etiqueta._ID,
            TarefaContract.EtiquetaTarefa.COLUMN_NAME_ETIQUETA,
            TarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFA,
            TarefaContract.Etiqueta.COLUMN_NAME_TITULO);

    public static EtiquetaTarefaDAO getInstance() {
        return etiquetaDAO;
    }

    private EtiquetaTarefaDAO() {
    }

    public Long save(Etiqueta etiqueta, Tarefa tarefa, Context context) {
        TarefaDBHelper tarefaDBHelper = new TarefaDBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        return db.insert(TarefaContract.EtiquetaTarefa.TABLE_NAME, null, toValues(etiqueta, tarefa));
    }

    public Integer delete(Tarefa tarefa, Context context) {
        TarefaDBHelper tarefaDBHelper = new TarefaDBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getWritableDatabase();
        return db.delete(TarefaContract.EtiquetaTarefa.TABLE_NAME, TarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFA + "=?", toArgs(tarefa));

    }

    private String[] toArgs(Tarefa tarefa) {
        return new String[]{tarefa.getId().toString()};
    }

    public Cursor getTarefasByEtiqueta(Context context, Etiqueta etiqueta) {
        TarefaDBHelper tarefaDBHelper = new TarefaDBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getReadableDatabase();
        return db.rawQuery(TarefasByEtiqueta, new String[]{String.valueOf(etiqueta.getIdEtiqueta())});
    }

    public Cursor getEtiquetasByTarefa(Context context, Tarefa tarefa) {
        TarefaDBHelper tarefaDBHelper = new TarefaDBHelper(context);
        SQLiteDatabase db = tarefaDBHelper.getReadableDatabase();
        return db.rawQuery(EtiquetasByTarefa, new String[]{String.valueOf(tarefa.getId())});
    }

    private ContentValues toValues(Etiqueta etiqueta, Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(TarefaContract.EtiquetaTarefa.COLUMN_NAME_ETIQUETA, etiqueta.getIdEtiqueta());
        values.put(TarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFA, tarefa.getId());
        return values;
    }
}

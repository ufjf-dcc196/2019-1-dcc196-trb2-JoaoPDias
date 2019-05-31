package com.ufjf.br.trabalho02.contract;

import android.provider.BaseColumns;


public class TarefaContract {

    public TarefaContract(){};
    public static final class Tarefa implements BaseColumns{

        public static final String TABLE_NAME = "tarefa";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_GRAUDIFICULDADE = "grau";
        public static final String COLUMN_NAME_ESTADO = "estado";
        public static final String COLUMN_NAME_DATAHORALIMITE = "datahoralimite";
        public static final String COLUMN_NAME_DATAHORAATU = "datahoratu";
        public static final String SQL_CREATE_TAREFA = String.format("CREATE TABLE %s (" +
                "%s INT PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT NOT NULL" +
                "%s TEXT NOT NULL" +
                "%s INT NOT NULL" +
                "%s INT NOT NULL" +
                "%s TIMESTAMP NOT NULL" +
                "%s TIMESTAMP NOT NULL" +
                ")", Tarefa.TABLE_NAME, Tarefa._ID, Tarefa.COLUMN_NAME_TITULO
                ,Tarefa.COLUMN_NAME_DESCRICAO
                ,Tarefa.COLUMN_NAME_GRAUDIFICULDADE
                ,Tarefa.COLUMN_NAME_ESTADO
        ,Tarefa.COLUMN_NAME_DATAHORALIMITE
        ,Tarefa.COLUMN_NAME_DATAHORAATU);
        public static final String SQL_DROP_TAREFA = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
    }
}

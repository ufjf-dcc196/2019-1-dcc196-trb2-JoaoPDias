package com.ufjf.br.trabalho02.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum Estado {
    FAZER(1, "A Fazer"), EXECUCAO(4, "Em Execução"), BlOQUEADA(2, "Bloqueada"), CONCLUIDA(3, "Concluída");
    private static final Map map = new HashMap<>();
    private String texto;
    private int valor;

    Estado(int i, String texto) {
        this.texto = texto;
        this.valor = i;
    }

    public String getTexto() {
        return texto;
    }


    public int getValor() {
        return valor;
    }

    static {
        for (Estado estado : Estado.values()) {
            map.put(estado.valor, estado);
        }
    }

    public static Estado valueOf(int estado) {
        return (Estado) map.get(estado);
    }

    @NotNull
    @Override
    public String toString(){
        return this.texto;
    }
}

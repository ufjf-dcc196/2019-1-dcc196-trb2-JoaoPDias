package com.ufjf.br.trabalho02.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum GrauDificuldade {
    MUITOFACIL(1,"Muito Fácil"),FACIL(2,"Fácil"),MEDIO(3,"Médio"),DIFICIL(4,"Difícil"),MUITODIFICIL(5,"Muito Difícil");
    private static final Map map = new HashMap<>();
    private int grau;
    private String descricao;
    GrauDificuldade(int grau, String descricao){
        this.grau = grau;
        this.descricao = descricao;
    }

    public int getGrau() {
        return grau;
    }

    public String getDescricao() {
        return descricao;
    }

    static {
        for (GrauDificuldade grau : GrauDificuldade.values()) {
            map.put(grau.grau, grau);
        }
    }

    public static GrauDificuldade valueOf(int grau) {
        return (GrauDificuldade) map.get(grau);
    }

    @NotNull
    @Override
    public String toString(){
        return this.descricao;
    }
}

package com.ufjf.br.trabalho02.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Etiqueta implements Serializable {
    private Long idEtiqueta;
    private String descricao;

    public Etiqueta() {
    }

    public Etiqueta(Long idEtiqueta, String descricao) {
        this.idEtiqueta = idEtiqueta;
        this.descricao = descricao;
    }

    public Etiqueta(String titulo) {
        this.descricao = titulo;
    }

    public Long getIdEtiqueta() {
        return idEtiqueta;
    }

    public Etiqueta setIdEtiqueta(Long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Etiqueta setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etiqueta etiqueta = (Etiqueta) o;
        return Objects.equals(idEtiqueta, etiqueta.idEtiqueta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtiqueta);
    }

    public String makeDescription(){
        return String.format(Locale.getDefault(),
                "Id: %d \n" +
                        "Título: %s \n" ,
                this.getIdEtiqueta(),
                this.getDescricao());


    }
}

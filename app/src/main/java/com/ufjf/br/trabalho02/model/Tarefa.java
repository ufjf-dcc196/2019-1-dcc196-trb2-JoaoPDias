package com.ufjf.br.trabalho02.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private GrauDificuldade grau;
    private Estado estado;
    private Date dataLimite;
    private Date dataAtu;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Tarefa setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
        return this;
    }

    public Tarefa setDataAtu(Date dataAtu) {
        this.dataAtu = dataAtu;
        return this;
    }

    public Tarefa(Long id, String titulo, String descricao, String dataatu, String datalime, int estado, int grau) throws ParseException {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataAtu = format.parse(dataatu);
        this.dataLimite = format.parse(datalime);
        this.estado = Estado.valueOf(estado);
        this.grau = GrauDificuldade.valueOf(grau);

    }

    public Tarefa(String titulo, String descricao, GrauDificuldade grau, Estado estado, String dataLimite) throws ParseException {
        this.titulo = titulo;
        this.descricao = descricao;
        this.grau = grau;
        this.estado = estado;
        this.dataLimite = format.parse(dataLimite);
    }

    public Tarefa(){};

    public String getTitulo() {
        return titulo;
    }

    public Tarefa setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public Estado getEstado() {
        return estado;
    }

    public Tarefa setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }
    public Long getId() {
        return id;
    }

    public Tarefa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Tarefa setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public GrauDificuldade getGrau() {
        return grau;
    }

    public Tarefa setGrau(GrauDificuldade grau) {
        this.grau = grau;
        return this;
    }

    public String getHorarioCriacao() {
        return format.format(dataLimite);
    }

    public Tarefa setHorarioCriacao(String dataLimite) throws ParseException {
        this.dataLimite =format.parse(dataLimite);
        return this;
    }

    public String getHorarioAtualizacao() {
        return format.format(dataAtu);
    }

    public Tarefa setHorarioAtualizacao(String dataAtu) throws ParseException {
        this.dataAtu = format.parse(dataAtu);
        return this;
    }

    public String makeDescription(){
        return String.format(Locale.getDefault(),
                "Id: %d \n" +
                        "Título: %s \n" +
                        "Data Limite: %s \n" +
                        "Data Atualização: %s \n" +
                        "Grau de Dificuldade: %s \n" +
                        "Estado: %s",
                        this.getId(),
                        this.getTitulo(),
                        this.getHorarioCriacao(),
                        this.getHorarioAtualizacao(),
                        this.getGrau().getDescricao(),
                        this.getEstado().getTexto());
    }
}

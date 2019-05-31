package com.ufjf.br.trabalho02.model;

public enum Estado {
    FAZER(1,"A Fazer"),EXECUCAO(2,"Em Execução"),BlOQUEADA(3,"Bloqueada"),CONCLUIDA(4,"Concluída");

    private String texto;
    private int valor;
    Estado(int i, String texto) {
        this.texto = texto;
        this.valor = i;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public String getTexto() {
        return texto;
    }


    public int getValor() {
        return valor;
    }
}

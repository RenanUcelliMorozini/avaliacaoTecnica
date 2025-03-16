package com.softdesign.avaliacao_tecnica.domain;

public enum DominioTipoCampo {
    TEXTO("idTexto", "Texto"),
    INPUT_TEXTO("idCampoTexto", "Campo de Texto"),
    INPUT_NUMERO("idCampoNumerico", "Campo Numérico"),
    INPUT_DATA("idCampoData" ,"Campo de Data");

    private final String descricao;
    private final String codigo;

    DominioTipoCampo(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCodigo() {
        return codigo;
    }
    
}

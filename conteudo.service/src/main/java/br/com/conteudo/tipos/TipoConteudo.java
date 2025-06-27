package br.com.conteudo.tipos;

public enum TipoConteudo {

    VIDEO("Vídeo"),
    PDF("PDF");

    private String tipo;


    TipoConteudo(String video) {this.tipo = tipo;}

    public static TipoConteudo fromString(String valor) {
        for (TipoConteudo tipo : TipoConteudo.values()) {
            if (tipo.tipo.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipod de conteúdo encontrado");
    }
}

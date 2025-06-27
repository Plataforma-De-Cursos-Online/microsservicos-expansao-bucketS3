package br.com.curso.service.tipos;

public enum TipoCurso {

    CIENCIAS_EXATAS_E_TI ("Ciências Exatas & TI"),
    CIENCIAS_HUMANAS("Ciências Humanas"),
    GESTAO_E_NEGOCIOS("Gestão e Negócios"),
    SAUDE_BEM_ESTAR_E_SEGURANCA("Saúde, Bem-estar e Segurança"),
    IDIOMAS_E_LITERATURA("Idiomas e Literatura"),
    COMUNICACA_E_DESIGN("Comunicação e Design"),
    PRODUCAO_CULTURA("Produção Cultural");

    private String tipo;

    TipoCurso(String tipo) {
        this.tipo = tipo;
    }

    public static TipoCurso fromString(String valor) {
        for (TipoCurso tipo : TipoCurso.values()) {
            if (tipo.tipo.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipod de curso encontrado");
    }
}
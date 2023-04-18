package poov.Modelo;

public enum Situacao {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String decricao;

    private Situacao(String decricao) {
        this.decricao = decricao;
    }

    public String getDecricao() {
        return decricao;
    }

}

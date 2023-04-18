package poov.Modelo;

public class Vacina {
    private Long codigo;
    private String nome;
    private String descricao;
    private Situacao situacao;

    public Vacina() {

    }

    public Vacina(Long codigo, String nome, String descricao, Situacao situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Long getCodigo() {
        return codigo;

    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
   public String toString() {
        return "Vacina [codigo=" + codigo + ", descricao=" + descricao + ", nome=" + nome + ", situacao=" + situacao + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vacina other = (Vacina) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (codigo ^ (codigo >>> 32));
        return result;
    }

}

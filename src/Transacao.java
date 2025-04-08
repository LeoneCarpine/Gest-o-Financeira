import java.util.Date;

public class Transacao {
    private double valor;
    private Categoria categoria;
    private Date data;
    private String descricao;

    public Transacao(double valor, Categoria categoria, Date data, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
}

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorTransacoes {
    private List<Transacao> transacoes;

    public GestorTransacoes() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(double valor, Categoria categoria, Date data, String descricao) {
        // Lógica de adicionar a transação
    }


    // Metodo para filtrar transações por categoria, tipo (despesa/receita) e intervalo de datas
    public List<Transacao> filtrarTransacoes(Categoria categoria, TipoCategoria tipo, Date dataInicio, Date dataFim) {
        List<Transacao> transacoesFiltradas = new ArrayList<>();

        // Filtrando as transações
        for (Transacao t : transacoes) {
            boolean dentroDoPeriodo = isWithinPeriod(t.getData(), dataInicio, dataFim);
            boolean mesmaCategoria = t.getCategoria().equals(categoria);
            boolean mesmoTipo = t.getCategoria().getTipo().equals(tipo); // Agora usando TipoCategoria

            // Adicionando as transações que atendem aos critérios
            if (dentroDoPeriodo && mesmaCategoria && mesmoTipo) {
                transacoesFiltradas.add(t);
            }
        }

        return transacoesFiltradas;
    }

    // Metodo para verificar se a transação está dentro do intervalo de datas
    private boolean isWithinPeriod(Date dataTransacao, Date dataInicio, Date dataFim) {
        return (dataTransacao.after(dataInicio) || dataTransacao.equals(dataInicio)) &&
                (dataTransacao.before(dataFim) || dataTransacao.equals(dataFim));
    }

    // Metodo para listar todas as transações
    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}

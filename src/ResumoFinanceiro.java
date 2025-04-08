import java.util.Date;
import java.util.List;

public class ResumoFinanceiro {
    private GestorTransacoes gestorTransacoes;

    // Construtor
    public ResumoFinanceiro(GestorTransacoes gestorTransacoes) {
        this.gestorTransacoes = gestorTransacoes;
    }

    // Método para calcular o saldo total (receitas - despesas)
    public double calcularSaldo(Date inicio, Date fim) {
        double receitas = calcularReceitas(inicio, fim);
        double despesas = calcularDespesas(inicio, fim);
        return receitas - despesas;
    }

    // Método para calcular a soma das receitas em um período
    public double calcularReceitas(Date inicio, Date fim) {
        double somaReceitas = 0.0;
        List<Transacao> transacoes = gestorTransacoes.getTransacoes();

        for (Transacao t : transacoes) {
            if (t.getCategoria().getTipo().equals(TipoCategoria.RECEITA) && isWithinPeriod(t.getData(), inicio, fim)) {
                somaReceitas += t.getValor();
            }
        }
        return somaReceitas;
    }

    // Método para calcular a soma das despesas em um período
    public double calcularDespesas(Date inicio, Date fim) {
        double somaDespesas = 0.0;
        List<Transacao> transacoes = gestorTransacoes.getTransacoes();

        for (Transacao t : transacoes) {
            if (t.getCategoria().getTipo().equals(TipoCategoria.DESPEZA) && isWithinPeriod(t.getData(), inicio, fim)) {
                somaDespesas += t.getValor();
            }
        }
        return somaDespesas;
    }

    // Método para verificar se uma data está dentro de um período
    private boolean isWithinPeriod(Date data, Date inicio, Date fim) {
        return (data.after(inicio) || data.equals(inicio)) && (data.before(fim) || data.equals(fim));
    }

    // Método para exibir o resumo financeiro
    public void exibirResumo(Date inicio, Date fim) {
        double saldo = calcularSaldo(inicio, fim);
        double receitas = calcularReceitas(inicio, fim);
        double despesas = calcularDespesas(inicio, fim);

        // Exibe o resumo financeiro no console (ou pode ser modificado para exibir na interface)
        System.out.println("Resumo Financeiro de " + inicio.toString() + " até " + fim.toString());
        System.out.println("Receitas: R$" + receitas);
        System.out.println("Despesas: R$" + despesas);
        System.out.println("Saldo: R$" + saldo);
    }
}

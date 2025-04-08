import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ResumoFinanceiroGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JSpinner spDataInicio, spDataFim;
    private JButton btnCalcular;
    private List<Transacao> transacoes;

    public ResumoFinanceiroGUI(List<Transacao> transacoes) {
        this.transacoes = transacoes;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Resumo Financeiro");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new FlowLayout());

        spDataInicio = new JSpinner(new SpinnerDateModel());
        spDataFim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deInicio = new JSpinner.DateEditor(spDataInicio, "dd/MM/yyyy");
        JSpinner.DateEditor deFim = new JSpinner.DateEditor(spDataFim, "dd/MM/yyyy");
        spDataInicio.setEditor(deInicio);
        spDataFim.setEditor(deFim);

        btnCalcular = new JButton("Calcular Resumo");
        btnCalcular.addActionListener(e -> calcularResumo());

        panelFiltros.add(new JLabel("Data Início:"));
        panelFiltros.add(spDataInicio);
        panelFiltros.add(new JLabel("Data Fim:"));
        panelFiltros.add(spDataFim);
        panelFiltros.add(btnCalcular);

        frame.getContentPane().add(panelFiltros, BorderLayout.NORTH);
    }

    private void calcularResumo() {
        Date dataInicio = (Date) spDataInicio.getValue();
        Date dataFim = (Date) spDataFim.getValue();

        double totalReceitas = calcularReceitas(dataInicio, dataFim);
        double totalDespesas = calcularDespesas(dataInicio, dataFim);
        double saldoTotal = totalReceitas - totalDespesas;

        textArea.setText("");
        textArea.append("Resumo Financeiro de " + dataInicio + " até " + dataFim + "\n\n");
        textArea.append("Receitas: R$" + totalReceitas + "\n");
        textArea.append("Despesas: R$" + totalDespesas + "\n");
        textArea.append("Saldo Total: R$" + saldoTotal + "\n");
    }

    private double calcularReceitas(Date inicio, Date fim) {
        double somaReceitas = 0.0;

        for (Transacao t : transacoes) {
            if (t.getCategoria().getTipo() == TipoCategoria.RECEITA && isWithinPeriod(t.getData(), inicio, fim)) {
                somaReceitas += t.getValor();
            }
        }

        return somaReceitas;
    }

    private double calcularDespesas(Date inicio, Date fim) {
        double somaDespesas = 0.0;

        for (Transacao t : transacoes) {
            if (t.getCategoria().getTipo() == TipoCategoria.DESPESA && isWithinPeriod(t.getData(), inicio, fim)) {
                somaDespesas += t.getValor();
            }
        }

        return somaDespesas;
    }

    private boolean isWithinPeriod(Date dataTransacao, Date dataInicio, Date dataFim) {
        return (dataTransacao.after(dataInicio) || dataTransacao.equals(dataInicio)) &&
                (dataTransacao.before(dataFim) || dataTransacao.equals(dataFim));
    }

    public void exibir() {
        frame.setVisible(true);
    }
}

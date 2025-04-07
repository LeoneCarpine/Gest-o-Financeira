import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ResumoFinanceiroGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JSpinner spDataInicio;
    private JSpinner spDataFim;
    private GestorTransacoes gestorTransacoes;

    public ResumoFinanceiroGUI(GestorTransacoes gestorTransacoes) {
        this.gestorTransacoes = gestorTransacoes;
        initialize(); 
    }

    private void initialize() {
        frame = new JFrame("Resumo Financeiro");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel lblDataInicio = new JLabel("Data Início:");
        spDataInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deInicio = new JSpinner.DateEditor(spDataInicio, "dd/MM/yyyy");
        spDataInicio.setEditor(deInicio);

        JLabel lblDataFim = new JLabel("Data Fim:");
        spDataFim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deFim = new JSpinner.DateEditor(spDataFim, "dd/MM/yyyy");
        spDataFim.setEditor(deFim);

        JButton btnExibir = new JButton("Exibir Resumo");

        panel.add(lblDataInicio);
        panel.add(spDataInicio);
        panel.add(lblDataFim);
        panel.add(spDataFim);
        panel.add(btnExibir);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dataInicio = (Date) spDataInicio.getValue();
                Date dataFim = (Date) spDataFim.getValue();

                ResumoFinanceiro resumoFinanceiro = new ResumoFinanceiro(gestorTransacoes);
                resumoFinanceiro.exibirResumo(dataInicio, dataFim);  

                textArea.setText("Resumo Financeiro de " + dataInicio.toString() + " até " + dataFim.toString() + "\n");
                textArea.append("Receitas: R$" + resumoFinanceiro.calcularReceitas(dataInicio, dataFim) + "\n");
                textArea.append("Despesas: R$" + resumoFinanceiro.calcularDespesas(dataInicio, dataFim) + "\n");
                textArea.append("Saldo: R$" + resumoFinanceiro.calcularSaldo(dataInicio, dataFim) + "\n");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

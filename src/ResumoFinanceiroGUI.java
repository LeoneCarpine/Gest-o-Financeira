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
        initialize();  // Chama o método de inicialização para criar os componentes
    }

    private void initialize() {
        frame = new JFrame("Resumo Financeiro");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Campo de data de início e fim
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

        // Área de texto para exibir o resumo
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Ação ao clicar no botão "Exibir Resumo"
        btnExibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dataInicio = (Date) spDataInicio.getValue();
                Date dataFim = (Date) spDataFim.getValue();

                // Criando o objeto de ResumoFinanceiro e exibindo o resumo
                ResumoFinanceiro resumoFinanceiro = new ResumoFinanceiro(gestorTransacoes);
                resumoFinanceiro.exibirResumo(dataInicio, dataFim);  // Exibe o resumo no console

                // Exibe o resumo na interface gráfica
                textArea.setText("Resumo Financeiro de " + dataInicio.toString() + " até " + dataFim.toString() + "\n");
                textArea.append("Receitas: R$" + resumoFinanceiro.calcularReceitas(dataInicio, dataFim) + "\n");
                textArea.append("Despesas: R$" + resumoFinanceiro.calcularDespesas(dataInicio, dataFim) + "\n");
                textArea.append("Saldo: R$" + resumoFinanceiro.calcularSaldo(dataInicio, dataFim) + "\n");
            }
        });

        frame.setLocationRelativeTo(null);  // Centraliza a janela
        frame.setVisible(true);  // Torna a janela visível
    }
}

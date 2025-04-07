import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResumoFinanceiroGUI {
    private JFrame frame;
    private GestorTransacoes gestorTransacoes;

    public ResumoFinanceiroGUI(GestorTransacoes gestorTransacoes) {
        this.gestorTransacoes = gestorTransacoes;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Resumo Financeiro");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();    
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        double totalReceitas = 0;      
        double totalDespesas = 0;
        List<Transacao> transacoes = gestorTransacoes.getTransacoes();
        for (Transacao transacao : transacoes) {
            if (transacao.getCategoria().getTipo().equals("Receita")) {
                totalReceitas += transacao.getValor();
            } else {
                totalDespesas += transacao.getValor();
            }
        }

        textArea.append("Total de Receitas: " + totalReceitas + "\n");
        textArea.append("Total de Despesas: " + totalDespesas + "\n");

        frame.setVisible(true);
    }

    public void exibir() {
        frame.setVisible(true);
    }
}

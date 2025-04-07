import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConsultaTransacoesGUI {
    private JFrame frame;
    private GestorTransacoes gestorTransacoes;
    private GestorCategoria gestorCategoria;

    public ConsultaTransacoesGUI(GestorTransacoes gestorTransacoes, GestorCategoria gestorCategoria) {
        this.gestorTransacoes = gestorTransacoes;
        this.gestorCategoria = gestorCategoria;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Consulta de Transações");     // config da tela
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();            // Exibe a lista (testar JTable para tabela)
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        List<Transacao> transacoes = gestorTransacoes.getTransacoes();          // Exibe as transacoes
        for (Transacao transacao : transacoes) {
            textArea.append("Valor: " + transacao.getValor() + ", Categoria: " + transacao.getCategoria().getNome() +
                    ", Data: " + transacao.getData() + ", Descrição: " + transacao.getDescricao() + "\n");
        }

        frame.setVisible(true);
    }
  
    public void exibir()
        frame.setVisible(true);
    }
}

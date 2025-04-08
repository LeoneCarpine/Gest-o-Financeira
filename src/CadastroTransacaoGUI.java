import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class CadastroTransacaoGUI {

    private JFrame frame;
    private JTextField tfValor, tfDescricao;
    private JComboBox<Categoria> cbCategoria;
    private JSpinner spData;
    private JButton btnAdicionar;
    private List<Categoria> categorias;
    private List<Transacao> transacoes;

    public CadastroTransacaoGUI(List<Categoria> categorias, List<Transacao> transacoes) {
        this.categorias = categorias;
        this.transacoes = transacoes;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cadastro de Transação");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Rótulos
        JLabel lblValor = new JLabel("Valor:");
        JLabel lblCategoria = new JLabel("Categoria:");
        JLabel lblDescricao = new JLabel("Descrição:");
        JLabel lblData = new JLabel("Data:");

        tfValor = new JTextField();
        tfDescricao = new JTextField();

        cbCategoria = new JComboBox<>();
        carregarCategorias();

        spData = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor de = new JSpinner.DateEditor(spData, "dd/MM/yyyy");
        spData.setEditor(de);

        btnAdicionar = new JButton("Adicionar Transação");
        btnAdicionar.addActionListener(e -> adicionarTransacao());

        frame.getContentPane().add(lblValor);
        frame.getContentPane().add(tfValor);
        frame.getContentPane().add(lblCategoria);
        frame.getContentPane().add(cbCategoria);
        frame.getContentPane().add(lblDescricao);
        frame.getContentPane().add(tfDescricao);
        frame.getContentPane().add(lblData);
        frame.getContentPane().add(spData);
        frame.getContentPane().add(new JLabel());  // Espaço vazio
        frame.getContentPane().add(btnAdicionar);
    }
    private void carregarCategorias() {
        for (Categoria categoria : categorias) {
            cbCategoria.addItem(categoria);
        }
    }
    private void adicionarTransacao() {
        try {
            double valor = Double.parseDouble(tfValor.getText());
            Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
            String descricao = tfDescricao.getText();
            Date data = (Date) spData.getValue();  // Pega o valor do JSpinner como Date

            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Descrição não pode ser vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            transacoes.add(new Transacao(valor, categoria, data, descricao));

            JOptionPane.showMessageDialog(frame, "Transação adicionada com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void exibir() {
        frame.setVisible(true);  // Torna a janela visível
    }
}

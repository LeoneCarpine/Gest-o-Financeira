import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroTransacaoGUI {
    private JFrame frame;
    private JTextField campoValor;
    private JTextField campoDescricao;
    private JComboBox<Categoria> comboCategoria;
    private GestorTransacoes gestorTransacoes;
    private GestorCategoria gestorCategoria;

    public CadastroTransacaoGUI(GestorTransacoes gestorTransacoes, GestorCategoria gestorCategoria) {
        this.gestorTransacoes = gestorTransacoes;
        this.gestorCategoria = gestorCategoria;
        initialize();  
    }

    private void initialize() {
        frame = new JFrame("Cadastro de Transação");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);        // painelzinho
        panel.setLayout(new GridLayout(4, 2));
        // Campos 
        JLabel lblValor = new JLabel("Valor:");
        panel.add(lblValor);
        campoValor = new JTextField();  
        panel.add(campoValor);

        JLabel lblDescricao = new JLabel("Descrição:");
        panel.add(lblDescricao);
        campoDescricao = new JTextField();
        panel.add(campoDescricao);

        JLabel lblCategoria = new JLabel("Categoria:");
        panel.add(lblCategoria);
        comboCategoria = new JComboBox<>(gestorCategoria.getCategorias().toArray(new Categoria[0]));
        panel.add(comboCategoria);

        
        JButton btnSalvar = new JButton("Salvar");          
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
                try {         
                    double valor = Double.parseDouble(campoValor.getText());
                    String descricao = campoDescricao.getText();
                    Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
                    gestorTransacoes.adicionarTransacao(valor, categoria, new java.util.Date(), descricao);
                    JOptionPane.showMessageDialog(frame, "Transação cadastrada com sucesso!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um valor válido.");
                }
            }
        });
        panel.add(btnSalvar);

        frame.setVisible(true);
    }

    public void exibir() {
        frame.setVisible(true); 
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCategoriaGUI {
    private JFrame frame;
    private JTextField txtNome;
    private JComboBox<TipoCategoria> comboTipoCategoria;
    private GestorCategoria gestorCategoria;

    public CadastroCategoriaGUI(GestorCategoria gestorCategoria) {
        this.gestorCategoria = gestorCategoria;
        initialize();
    }

    private void initialize() {
        // Inicializa o frame
        frame = new JFrame("Cadastro de Categoria");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(3, 2)); // Layout com 3 linhas e 2 colunas

        // Campo para nome da categoria
        JLabel lblNome = new JLabel("Nome da Categoria:");
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        // Campo para tipo da categoria (Receita ou Despesa)
        JLabel lblTipo = new JLabel("Tipo de Categoria:");
        frame.getContentPane().add(lblTipo);

        comboTipoCategoria = new JComboBox<>(TipoCategoria.values()); // Adiciona os tipos de categoria
        frame.getContentPane().add(comboTipoCategoria);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                TipoCategoria tipo = (TipoCategoria) comboTipoCategoria.getSelectedItem();

                // Adiciona a nova categoria usando o gestor
                gestorCategoria.adicionarCategoria(nome, tipo);

                // Fechar a tela de cadastro
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnSalvar);

        // Botão de cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a tela de cadastro sem salvar
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnCancelar);

        // Exibe o frame
        frame.setVisible(true);
    }

    // Metodo para exibir o frame
    public void exibir() {
        frame.setVisible(true);
    }
}

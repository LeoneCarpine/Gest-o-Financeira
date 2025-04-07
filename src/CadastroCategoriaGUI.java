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
        
        frame = new JFrame("Cadastro de Categoria");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(3, 2));     // Layout 3 linhas e 2 colunas

        JLabel lblNome = new JLabel("Nome da Categoria:");
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo de Categoria:");
        frame.getContentPane().add(lblTipo);

        comboTipoCategoria = new JComboBox<>(TipoCategoria.values());       // Adiciona os tipos de categoria
        frame.getContentPane().add(comboTipoCategoria);

        // Botão de salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                TipoCategoria tipo = (TipoCategoria) comboTipoCategoria.getSelectedItem();

                gestorCategoria.adicionarCategoria(nome, tipo);

                frame.dispose(); // fechar
            }
        });
        frame.getContentPane().add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnCancelar);

        frame.setVisible(true);
    }

    public void exibir() {
        frame.setVisible(true);
    }
}

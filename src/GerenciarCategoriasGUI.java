import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciarCategoriasGUI {

    private JFrame frame;
    private GestorCategoria gestorCategoria;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton editButton;
    private JButton removeButton;
    private JButton addButton;
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private List<Categoria> categorias;

    public GerenciarCategoriasGUI(GestorCategoria gestorCategoria) {
        this.gestorCategoria = gestorCategoria;
        categorias = gestorCategoria.getCategorias();  // Pega as categorias do gestor
        initialize();  // Chama o método de inicialização
    }

    private void initialize() {
        // Configurações da janela
        frame = new JFrame("Gerenciar Categorias");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Inicializando o modelo de lista
        listModel = new DefaultListModel<>();
        atualizarListaCategorias();  // Atualiza a lista com as categorias

        // Criando a JList para mostrar as categorias
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Painel para campos de edição de categoria
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        nameField = new JTextField(15);
        typeComboBox = new JComboBox<>(new String[]{"DESPEZA", "RECEITA"});

        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Tipo:"));
        panel.add(typeComboBox);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        removeButton = new JButton("Remover");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Ação do botão "Adicionar"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                TipoCategoria type = TipoCategoria.valueOf(typeComboBox.getSelectedItem().toString());

                // Adiciona nova categoria
                if (!name.isEmpty()) {
                    gestorCategoria.adicionarCategoria(name, type);
                    atualizarListaCategorias();  // Atualiza a lista de categorias
                    JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "O nome da categoria não pode ser vazio.");
                }
            }
        });

        // Ação do botão "Editar"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Pega a categoria selecionada
                    Categoria selectedCategory = categorias.get(selectedIndex);

                    // Preenche o campo de texto com o nome e tipo da categoria selecionada
                    nameField.setText(selectedCategory.getNome());
                    typeComboBox.setSelectedItem(selectedCategory.getTipo().toString());

                    // Ação ao confirmar o botão "Editar"
                    addButton.setText("Atualizar");
                    addButton.removeActionListener(addButton.getActionListeners()[0]);  // Remove o listener anterior
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String newName = nameField.getText();
                            TipoCategoria newType = TipoCategoria.valueOf(typeComboBox.getSelectedItem().toString());

                            // Atualiza a categoria
                            gestorCategoria.removerCategoria(selectedCategory.getNome());
                            gestorCategoria.adicionarCategoria(newName, newType);

                            atualizarListaCategorias();  // Atualiza a lista de categorias
                            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma categoria para editar.");
                }
            }
        });

        // Ação do botão "Remover"
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Pega a categoria selecionada
                    Categoria selectedCategory = categorias.get(selectedIndex);

                    // Remove a categoria
                    gestorCategoria.removerCategoria(selectedCategory.getNome());
                    atualizarListaCategorias();  // Atualiza a lista de categorias
                    JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma categoria para remover.");
                }
            }
        });

        frame.setLocationRelativeTo(null);  // Centraliza a janela
        frame.setVisible(true);  // Exibe a janela
    }

    // Atualiza a lista de categorias na interface gráfica
    private void atualizarListaCategorias() {
        categorias = gestorCategoria.getCategorias();  // Atualiza a lista de categorias do gestor
        listModel.clear();
        for (Categoria categoria : categorias) {
            listModel.addElement(categoria.getNome() + " - " + categoria.getTipo());
        }
    }
}

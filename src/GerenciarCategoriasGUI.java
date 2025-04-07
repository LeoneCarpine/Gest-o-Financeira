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
        categorias = gestorCategoria.getCategorias();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gerenciar Categorias");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        atualizarListaCategorias();

        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        nameField = new JTextField(15);
        typeComboBox = new JComboBox<>(new String[]{"DESPEZA", "RECEITA"});

        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Tipo:"));
        panel.add(typeComboBox);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        removeButton = new JButton("Remover");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                TipoCategoria type = TipoCategoria.valueOf(typeComboBox.getSelectedItem().toString());

                if (!name.isEmpty()) {
                    gestorCategoria.adicionarCategoria(name, type);
                    atualizarListaCategorias();
                    JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "O nome da categoria não pode ser vazio.");
                }
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Categoria selectedCategory = categorias.get(selectedIndex);

                    nameField.setText(selectedCategory.getNome());
                    typeComboBox.setSelectedItem(selectedCategory.getTipo().toString());

                    addButton.setText("Atualizar");
                    addButton.removeActionListener(addButton.getActionListeners()[0]);  // Remove o listener anterior
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String newName = nameField.getText();
                            TipoCategoria newType = TipoCategoria.valueOf(typeComboBox.getSelectedItem().toString());

                            gestorCategoria.removerCategoria(selectedCategory.getNome());
                            gestorCategoria.adicionarCategoria(newName, newType);

                            atualizarListaCategorias();
                            JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma categoria para editar.");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Categoria selectedCategory = categorias.get(selectedIndex);

                    gestorCategoria.removerCategoria(selectedCategory.getNome());
                    atualizarListaCategorias();
                    JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma categoria para remover.");
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void atualizarListaCategorias() {
        categorias = gestorCategoria.getCategorias();
        listModel.clear();
        for (Categoria categoria : categorias) {
            listModel.addElement(categoria.getNome() + " - " + categoria.getTipo());
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenciarCategoriasGUI extends JFrame {

    private List<Categoria> categorias;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JButton addButton, editButton, removeButton;
    private JTextField nameField;
    private JComboBox<String> typeComboBox;

    public GerenciarCategoriasGUI(List<Categoria> categorias) {
        this.categorias = categorias;
        initialize();
    }

    private void initialize() {
        setTitle("Gerenciar Categorias");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        atualizarListaCategorias();

        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        nameField = new JTextField(15);
        typeComboBox = new JComboBox<>(new String[]{"DESPESA", "RECEITA"});

        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Tipo:"));
        panel.add(typeComboBox);

        add(panel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        removeButton = new JButton("Remover");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> adicionarCategoria());
        editButton.addActionListener(e -> editarCategoria());
        removeButton.addActionListener(e -> removerCategoria());
    }

    private void adicionarCategoria() {
        String name = nameField.getText();
        String tipoString = (String) typeComboBox.getSelectedItem();
        TipoCategoria tipo = TipoCategoria.valueOf(tipoString);

        categorias.add(new Categoria(name, tipo));
        atualizarListaCategorias();
        JOptionPane.showMessageDialog(this, "Categoria adicionada com sucesso!");
    }

    private void editarCategoria() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            Categoria selectedCategory = categorias.get(selectedIndex);

            String newName = nameField.getText();
            String newTipoString = (String) typeComboBox.getSelectedItem();
            TipoCategoria newTipo = TipoCategoria.valueOf(newTipoString);  // Converte de String para TipoCategoria

            selectedCategory.setNome(newName);
            selectedCategory.setTipo(newTipo);

            atualizarListaCategorias();
            JOptionPane.showMessageDialog(this, "Categoria editada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar.");
        }
    }

    private void removerCategoria() {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {

            Categoria selectedCategory = categorias.get(selectedIndex);

            categorias.remove(selectedCategory);
            atualizarListaCategorias();
            JOptionPane.showMessageDialog(this, "Categoria removida com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria para remover.");
        }
    }
    private void atualizarListaCategorias() {
        listModel.clear();
        for (Categoria categoria : categorias) {
            listModel.addElement(categoria.getNome() + " - " + categoria.getTipo());
        }
    }
    public void exibir() {
        setVisible(true);
    }
}

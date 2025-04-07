import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class ConsultaTransacoesGUI {

    private JFrame frame;
    private GestorTransacoes gestorTransacoes;
    private GestorCategoria gestorCategoria;
    private JTextArea textArea;
    private JSpinner spDataInicio;
    private JSpinner spDataFim;
    private JComboBox<Categoria> cbCategoria;
    private JComboBox<String> cbTipo;

    public ConsultaTransacoesGUI(GestorTransacoes gestorTransacoes, GestorCategoria gestorCategoria) {
        this.gestorTransacoes = gestorTransacoes;
        this.gestorCategoria = gestorCategoria;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Consulta de Transações");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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

        JLabel lblCategoria = new JLabel("Categoria:");
        cbCategoria = new JComboBox<>();
        carregarCategorias();

        JLabel lblTipo = new JLabel("Tipo:");
        cbTipo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTransacoes();
            }
        });

        panel.add(lblDataInicio);
        panel.add(spDataInicio);
        panel.add(lblDataFim);
        panel.add(spDataFim);
        panel.add(lblCategoria);
        panel.add(cbCategoria);
        panel.add(lblTipo);
        panel.add(cbTipo);
        panel.add(btnBuscar);

        frame.getContentPane().add(panel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void carregarCategorias() {
        for (Categoria categoria : gestorCategoria.getCategorias()) {
            cbCategoria.addItem(categoria);
        }
    }

    private void buscarTransacoes() {
        Date dataInicio = (Date) spDataInicio.getValue();
        Date dataFim = (Date) spDataFim.getValue();
        Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
        String tipo = (String) cbTipo.getSelectedItem();

        List<Transacao> transacoesFiltradas = gestorTransacoes.filtrarTransacoes(categoria, TipoCategoria.valueOf(tipo), dataInicio, dataFim);

        textArea.setText("");
        for (Transacao transacao : transacoesFiltradas) {
            textArea.append("Valor: R$" + transacao.getValor() + ", Categoria: " + transacao.getCategoria().getNome() +
                    ", Tipo: " + transacao.getCategoria().getTipo() + ", Data: " + transacao.getData() + ", Descrição: " + transacao.getDescricao() + "\n");
        }
    }
}

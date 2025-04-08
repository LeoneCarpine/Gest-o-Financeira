import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaTransacoesGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JComboBox<Categoria> cbCategoria;
    private JComboBox<String> cbTipoTransacao;
    private JSpinner spDataInicio, spDataFim;
    private List<Transacao> transacoes;
    private List<Categoria> categorias;

    public ConsultaTransacoesGUI(List<Transacao> transacoes, List<Categoria> categorias) {
        this.transacoes = transacoes;
        this.categorias = categorias;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Consultar Transações");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        cbCategoria = new JComboBox<>();
        cbCategoria.addItem(null);
        for (Categoria categoria : categorias) {
            cbCategoria.addItem(categoria);
        }

        cbTipoTransacao = new JComboBox<>(new String[]{"Todas", "Receita", "Despesa"});

        spDataInicio = new JSpinner(new SpinnerDateModel());
        spDataFim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deInicio = new JSpinner.DateEditor(spDataInicio, "dd/MM/yyyy");
        JSpinner.DateEditor deFim = new JSpinner.DateEditor(spDataFim, "dd/MM/yyyy");
        spDataInicio.setEditor(deInicio);
        spDataFim.setEditor(deFim);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarTransacoes());

        panelFiltros.add(new JLabel("Categoria:"));
        panelFiltros.add(cbCategoria);
        panelFiltros.add(new JLabel("Tipo:"));
        panelFiltros.add(cbTipoTransacao);
        panelFiltros.add(new JLabel("Data Início:"));
        panelFiltros.add(spDataInicio);
        panelFiltros.add(new JLabel("Data Fim:"));
        panelFiltros.add(spDataFim);
        panelFiltros.add(btnBuscar);

        frame.getContentPane().add(panelFiltros, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private void buscarTransacoes() {
        Categoria categoriaFiltro = (Categoria) cbCategoria.getSelectedItem();
        String tipoFiltro = (String) cbTipoTransacao.getSelectedItem();
        Date dataInicio = (Date) spDataInicio.getValue();
        Date dataFim = (Date) spDataFim.getValue();

        List<Transacao> transacoesFiltradas = filtrarTransacoes(categoriaFiltro, tipoFiltro, dataInicio, dataFim);

        textArea.setText("");
        if (transacoesFiltradas.isEmpty()) {
            textArea.append("Nenhuma transação encontrada com os filtros aplicados.\n");
        } else {
            for (Transacao t : transacoesFiltradas) {
                textArea.append("Valor: R$" + t.getValor() + "\n");
                textArea.append("Categoria: " + t.getCategoria().getNome() + "\n");
                textArea.append("Descrição: " + t.getDescricao() + "\n");
                textArea.append("Data: " + t.getData() + "\n");
                textArea.append("-------------\n");
            }
        }
    }

    private List<Transacao> filtrarTransacoes(Categoria categoria, String tipo, Date dataInicio, Date dataFim) {
        List<Transacao> transacoesFiltradas = new ArrayList<>();

        for (Transacao t : transacoes) {
            boolean dentroDoPeriodo = (t.getData().after(dataInicio) || t.getData().equals(dataInicio)) &&
                    (t.getData().before(dataFim) || t.getData().equals(dataFim));

            boolean mesmaCategoria = (categoria == null || t.getCategoria().equals(categoria));

            boolean mesmoTipo = tipo.equals("Todas") ||
                    (tipo.equals("Receita") && t.getCategoria().getTipo() == TipoCategoria.RECEITA) ||
                    (tipo.equals("Despesa") && t.getCategoria().getTipo() == TipoCategoria.DESPESA);

            if (dentroDoPeriodo && mesmaCategoria && mesmoTipo) {
                transacoesFiltradas.add(t);
            }
        }

        return transacoesFiltradas;
    }
    public void exibir() {
        frame.setVisible(true);
    }
}

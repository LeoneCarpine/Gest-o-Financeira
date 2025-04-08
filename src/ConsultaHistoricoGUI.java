import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;

public class ConsultaHistoricoGUI {

    private JFrame frame;
    private JComboBox<Categoria> cbCategoria;
    private JComboBox<String> cbTipo;
    private JSpinner spDataInicio;
    private JSpinner spDataFim;
    private JTable tabelaTransacoes;
    private GestorTransacoes gestorTransacoes;
    private GestorCategoria gestorCategoria;

    public ConsultaHistoricoGUI(GestorTransacoes gestorTransacoes, GestorCategoria gestorCategoria) {
        this.gestorTransacoes = gestorTransacoes;
        this.gestorCategoria = gestorCategoria;
        initialize();
    }

    private void initialize() {
        // Inicializa a janela principal
        frame = new JFrame("Consulta de Histórico");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(6, 2, 10, 10));

        // Rótulos
        JLabel lblCategoria = new JLabel("Categoria:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblDataInicio = new JLabel("Data Início:");
        JLabel lblDataFim = new JLabel("Data Fim:");

        // Campos de filtro
        cbCategoria = new JComboBox<>();
        carregarCategorias();

        cbTipo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});

        spDataInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deInicio = new JSpinner.DateEditor(spDataInicio, "dd/MM/yyyy");
        spDataInicio.setEditor(deInicio);

        spDataFim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor deFim = new JSpinner.DateEditor(spDataFim, "dd/MM/yyyy");
        spDataFim.setEditor(deFim);

        // Botão de buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHistorico();
            }
        });

        // Tabela para mostrar o histórico de transações
        tabelaTransacoes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);

        // Adicionando os componentes à janela
        frame.getContentPane().add(lblCategoria);
        frame.getContentPane().add(cbCategoria);
        frame.getContentPane().add(lblTipo);
        frame.getContentPane().add(cbTipo);
        frame.getContentPane().add(lblDataInicio);
        frame.getContentPane().add(spDataInicio);
        frame.getContentPane().add(lblDataFim);
        frame.getContentPane().add(spDataFim);
        frame.getContentPane().add(btnBuscar);
        frame.getContentPane().add(scrollPane);

        // Exibir a janela
        frame.setVisible(true);
    }

    private void carregarCategorias() {
        // Carregar as categorias no combo box
        for (Categoria categoria : gestorCategoria.getCategorias()) {
            cbCategoria.addItem(categoria);
        }
    }

    private void buscarHistorico() {
        // Obter os filtros
        Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
        String tipo = (String) cbTipo.getSelectedItem();
        Date dataInicio = (Date) spDataInicio.getValue();
        Date dataFim = (Date) spDataFim.getValue();

        // Filtrar as transações
        // Caso esteja utilizando TipoCategoria
        List<Transacao> transacoesFiltradas = gestorTransacoes.filtrarTransacoes(categoria, TipoCategoria.valueOf(tipo), dataInicio, dataFim);


        // Exibir as transações na tabela
        String[] colunas = {"Data", "Categoria", "Valor", "Descrição"};
        Object[][] dados = new Object[transacoesFiltradas.size()][4];

        for (int i = 0; i < transacoesFiltradas.size(); i++) {
            Transacao transacao = transacoesFiltradas.get(i);
            dados[i][0] = transacao.getData();
            dados[i][1] = transacao.getCategoria().getNome();
            dados[i][2] = transacao.getValor();
            dados[i][3] = transacao.getDescricao();
        }

        tabelaTransacoes.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}

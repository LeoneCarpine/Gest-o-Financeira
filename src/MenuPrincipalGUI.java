import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuPrincipalGUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mnTransacao, mnResumoFinanceiro, mnCategoria;
    private JMenuItem miCadastrarTransacao, miConsultarTransacoes, miResumoFinanceiro, miGerenciarCategorias;
    private List<Transacao> transacoes;
    private List<Categoria> categorias;
    private Usuario usuarioLogado;

    public MenuPrincipalGUI(List<Categoria> categorias, List<Transacao> transacoes, Usuario usuarioLogado) {
        this.categorias = categorias;
        this.transacoes = transacoes;
        this.usuarioLogado = usuarioLogado;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Menu Principal");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnTransacao = new JMenu("Transações");
        miCadastrarTransacao = new JMenuItem("Cadastrar Transação");
        miCadastrarTransacao.addActionListener(e -> {
            new CadastroTransacaoGUI(categorias, transacoes).exibir();  // Exibe a tela de cadastro
        });
        miConsultarTransacoes = new JMenuItem("Consultar Transações");
        miConsultarTransacoes.addActionListener(e -> {
            new ConsultaTransacoesGUI(transacoes, categorias).exibir();  // Exibe a tela de consulta
        });

        mnTransacao.add(miCadastrarTransacao);
        mnTransacao.add(miConsultarTransacoes);

        mnResumoFinanceiro = new JMenu("Resumo Financeiro");
        miResumoFinanceiro = new JMenuItem("Resumo Financeiro");
        miResumoFinanceiro.addActionListener(e -> {
            new ResumoFinanceiroGUI(transacoes).exibir();  // Exibe a tela de resumo financeiro
        });
        mnResumoFinanceiro.add(miResumoFinanceiro);

        mnCategoria = new JMenu("Categorias");
        miGerenciarCategorias = new JMenuItem("Gerenciar Categorias");
        miGerenciarCategorias.addActionListener(e -> {
            new GerenciarCategoriasGUI(categorias).exibir();  // Exibe a tela de gerenciamento de categorias
        });
        mnCategoria.add(miGerenciarCategorias);

        menuBar.add(mnTransacao);
        menuBar.add(mnResumoFinanceiro);
        menuBar.add(mnCategoria);

        frame.setVisible(true);
    }
}

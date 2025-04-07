import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalGUI {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mnTransacao, mnResumoFinanceiro, mnCategoria;
    private JMenuItem miCadastrarTransacao, miConsultarTransacoes, miResumoFinanceiro, miGerenciarCategorias;
    private GestorTransacoes gestorTransacoes;
    private GestorCategoria gestorCategoria;

    public MenuPrincipalGUI(GestorTransacoes gestorTransacoes, GestorCategoria gestorCategoria) {
        this.gestorTransacoes = gestorTransacoes;
        this.gestorCategoria = gestorCategoria;
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
        miCadastrarTransacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroTransacaoGUI(gestorTransacoes, gestorCategoria);
            }
        });
        miConsultarTransacoes = new JMenuItem("Consultar Transações");
        miConsultarTransacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaTransacoesGUI(gestorTransacoes, gestorCategoria);
            }
        });
        
        mnTransacao.add(miCadastrarTransacao);
        mnTransacao.add(miConsultarTransacoes);
        mnResumoFinanceiro = new JMenu("Resumo Financeiro");
        miResumoFinanceiro = new JMenuItem("Resumo Financeiro");
        miResumoFinanceiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResumoFinanceiroGUI(gestorTransacoes);
            }
        });
        mnResumoFinanceiro.add(miResumoFinanceiro);
        mnCategoria = new JMenu("Categorias");
        miGerenciarCategorias = new JMenuItem("Gerenciar Categorias");
        miGerenciarCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerenciarCategoriasGUI(gestorCategoria);
            }
        });
        mnCategoria.add(miGerenciarCategorias);
        menuBar.add(mnTransacao);
        menuBar.add(mnResumoFinanceiro);
        menuBar.add(mnCategoria);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void exibir() {
        frame.setVisible(true);
    }
}

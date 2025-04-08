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
        // Configurações da janela principal
        frame = new JFrame("Menu Principal");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criação da barra de menu
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Menu de Transações
        mnTransacao = new JMenu("Transações");
        miCadastrarTransacao = new JMenuItem("Cadastrar Transação");
        miCadastrarTransacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de cadastro de transação
                new CadastroTransacaoGUI(gestorTransacoes, gestorCategoria);  // Já chama o construtor que exibe automaticamente
            }
        });
        miConsultarTransacoes = new JMenuItem("Consultar Transações");
        miConsultarTransacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de consulta de transações
                new ConsultaTransacoesGUI(gestorTransacoes, gestorCategoria);  // Já chama o construtor que exibe automaticamente
            }
        });

        mnTransacao.add(miCadastrarTransacao);
        mnTransacao.add(miConsultarTransacoes);

        // Menu de Resumo Financeiro
        mnResumoFinanceiro = new JMenu("Resumo Financeiro");
        miResumoFinanceiro = new JMenuItem("Resumo Financeiro");
        miResumoFinanceiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de resumo financeiro
                new ResumoFinanceiroGUI(gestorTransacoes);  // Já chama o construtor que exibe automaticamente
            }
        });
        mnResumoFinanceiro.add(miResumoFinanceiro);

        // Menu de Categorias
        mnCategoria = new JMenu("Categorias");
        miGerenciarCategorias = new JMenuItem("Gerenciar Categorias");
        miGerenciarCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ao clicar, abre a tela de gerenciamento de categorias
                new GerenciarCategoriasGUI(gestorCategoria);  // Já chama o construtor que exibe automaticamente
            }
        });
        mnCategoria.add(miGerenciarCategorias);

        // Adiciona os menus à barra de menus
        menuBar.add(mnTransacao);
        menuBar.add(mnResumoFinanceiro);
        menuBar.add(mnCategoria);

        // Exibe a janela principal
        frame.setLocationRelativeTo(null);  // Centraliza a janela
        frame.setVisible(true);  // Torna a janela visível
    }

    // Método para exibir a janela principal
    public void exibir() {
        frame.setVisible(true);  // Exibe a janela
    }
}

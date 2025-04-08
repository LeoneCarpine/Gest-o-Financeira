import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroLoginGUI {

    private JFrame frame;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private GestorUsuarios gestorUsuarios;

    public CadastroLoginGUI(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
        initialize();
    }

    private void initialize() {
        // Criando a janela com um tamanho maior
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout mais flexível para centralizar os componentes
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Personalizando o layout
        gbc.insets = new Insets(10, 10, 10, 10);  // Adicionando espaçamento entre os componentes
        gbc.anchor = GridBagConstraints.CENTER;

        // Criando componentes do formulário
        JLabel lblEmail = new JLabel("User:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));  // Fontes maiores para mais visibilidade
        txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));  // Fontes maiores para mais visibilidade
        txtSenha = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));  // Fonte maior no botão para destacar

        // Posicionando os componentes usando GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(lblEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(lblSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(btnLogin, gbc);

        // Definindo ação do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUsuario();
            }
        });

        frame.setLocationRelativeTo(null);  // Centraliza a janela na tela
    }

    private void loginUsuario() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        // Verificando se as credenciais são para o administrador
        if (email.equals("adm") && senha.equals("Admin4123")) {

            // Redireciona para o MenuPrincipalGUI após login bem-sucedido
            new MenuPrincipalGUI(new GestorTransacoes(), new GestorCategoria()).exibir();  // Exemplo de redirecionamento para o menu principal

            frame.dispose();  // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(frame, "Erro: Email ou senha inválidos.");
        }
    }

    // Metodo para exibir a janela de login
    public void exibir() {
        frame.setVisible(true);  // Torna a tela visível
    }
}

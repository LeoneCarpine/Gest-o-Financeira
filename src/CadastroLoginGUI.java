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
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblEmail = new JLabel("User:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        txtSenha = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));

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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUsuario();
            }
        });

        frame.setLocationRelativeTo(null);
    }

    private void loginUsuario() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        if (email.equals("adm") && senha.equals("Admin4123")) {
            JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");

            new MenuPrincipalGUI(new GestorTransacoes(), new GestorCategoria()).exibir();  // Exemplo de redirecionamento para o menu principal

            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Erro: Email ou senha inválidos.");
        }
    }

    public void exibir() {
        frame.setVisible(true);
    }
}

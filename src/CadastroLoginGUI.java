import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadastroLoginGUI {

    private JFrame frame;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private List<Usuario> usuarios;
    private List<Categoria> categorias;
    private List<Transacao> transacoes;

    public CadastroLoginGUI(List<Usuario> usuarios, List<Categoria> categorias, List<Transacao> transacoes) {
        this.usuarios = usuarios;
        this.categorias = categorias;
        this.transacoes = transacoes;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        txtEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        txtSenha.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUsuario();
            }
        });

        frame.getContentPane().add(lblEmail);
        frame.getContentPane().add(txtEmail);
        frame.getContentPane().add(lblSenha);
        frame.getContentPane().add(txtSenha);
        frame.getContentPane().add(btnLogin);

        frame.setVisible(true);
    }

    private void loginUsuario() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        Usuario usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                break;
            }
        }

        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");

            new MenuPrincipalGUI(categorias, transacoes, usuarioLogado);

            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Erro: Email ou senha inválidos.");
        }
    }
}

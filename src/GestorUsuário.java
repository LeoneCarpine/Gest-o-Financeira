import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;    // armazenar usuários cadastrados 

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    // Metodo para registrar um novo usuário
    public void registrarUsuario(String email, String senha, String nome) {
        if (validarEmail(email) && validarSenha(senha)) {
            Usuario novoUsuario = new Usuario(email, senha, nome);
            usuarios.add(novoUsuario);
            System.out.println("Usuário registrado com sucesso!");
        } else {
            System.out.println("Erro no cadastro: Email ou senha inválidos.");
        }
    }

    // Metodo para realizar o login de um usuário
    public Usuario logar(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido!");
                return usuario;
            }
        }
        System.out.println("Email ou senha inválidos.");
        return null; // Retorna null se não encontrar o usuário
    }

    // validar o email
    private boolean validarEmail(String email) {
        // A validação do email se tem "@"
        return email.contains("@");
    }

    // Metodo para validar a senha
    private boolean validarSenha(String senha) {
        return senha.length() >= 6;
        }
    }
}

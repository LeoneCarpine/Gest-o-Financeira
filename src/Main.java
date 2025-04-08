public class Main {
    public static void main(String[] args) {
        // Criação do gestor de usuários e transações
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorTransacoes gestorTransacoes = new GestorTransacoes();
        GestorCategoria gestorCategoria = new GestorCategoria();

        // Adicionar o usuário administrador (se não existir)
        gestorUsuarios.registrarUsuario("adm", "Admin4123", "Administrador");

        // Exibir a tela de login
        CadastroLoginGUI loginGUI = new CadastroLoginGUI(gestorUsuarios);
        loginGUI.exibir();  // Exibe a tela de login

        // Após o login bem-sucedido, o redirecionamento para o MenuPrincipalGUI será feito na classe CadastroLoginGUI
    }
}

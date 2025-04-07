public class Main {
    public static void main(String[] args) {

        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorTransacoes gestorTransacoes = new GestorTransacoes();
        GestorCategoria gestorCategoria = new GestorCategoria();

        // Add adm
        gestorUsuarios.registrarUsuario("adm", "Admin4123", "Administrador");

        CadastroLoginGUI loginGUI = new CadastroLoginGUI(gestorUsuarios);
        loginGUI.exibir();
    }
}

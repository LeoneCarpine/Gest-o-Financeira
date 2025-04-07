import java.util.Date;

public class Main {
    public static void main(String[] args) {
        GestorTransacoes gestorTransacoes = new GestorTransacoes();
        GestorCategoria gestorCategoria = new GestorCategoria();
        new MenuPrincipalGUI(gestorTransacoes, gestorCategoria);        // interface
    }
}

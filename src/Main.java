import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Usuario> usuarios = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<Transacao> transacoes = new ArrayList<>();

        usuarios.add(new Usuario("adm", "Admin4123", "Administrador"));

        new CadastroLoginGUI(usuarios, categorias, transacoes);
    }
}

import java.util.ArrayList;
import java.util.List;

public class GestorCategoria {
    private List<Categoria> categorias;

    public GestorCategoria() {
        this.categorias = new ArrayList<>();
    }

    // Metodo para adicionar uma nova categoria (agora usando TipoCategoria)
    public void adicionarCategoria(String nome, TipoCategoria tipo) {
        Categoria novaCategoria = new Categoria(nome, tipo);
        categorias.add(novaCategoria);
        System.out.println("Categoria adicionada: " + novaCategoria.getNome() + ", Tipo: " + novaCategoria.getTipo());
    }

    // Metodo para listar todas as categorias
    public List<Categoria> getCategorias() {
        return categorias;
    }

    // Metodo para remover uma categoria
    public void removerCategoria(String nome) {
        categorias.removeIf(categoria -> categoria.getNome().equals(nome));
    }

    // Metodo para editar uma categoria
    public void editarCategoria(String nomeAntigo, String novoNome, TipoCategoria novoTipo) {
        for (Categoria categoria : categorias) {
            if (categoria.getNome().equals(nomeAntigo)) {
                categoria.setNome(novoNome);
                categoria.setTipo(novoTipo);
                System.out.println("Categoria " + nomeAntigo + " editada para: " + novoNome + ", Tipo: " + novoTipo);
                return;
            }
        }
        System.out.println("Categoria não encontrada: " + nomeAntigo);
    }
}

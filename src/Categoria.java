public class Categoria {
    private String nome;
    private TipoCategoria tipo;

    // Construtor
    public Categoria(String nome, TipoCategoria tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    // Métodos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCategoria getTipo() {
        return tipo;
    }

    public void setTipo(TipoCategoria tipo) {
        this.tipo = tipo;
    }

    // Exibir o nome da categoria
    @Override
    public String toString() {
        return nome; // Exibe o nome da Categoria
    }
}

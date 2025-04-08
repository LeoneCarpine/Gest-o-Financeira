public class Categoria {
    private String nome;
    private TipoCategoria tipo;

    public Categoria(String nome, TipoCategoria tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
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
    @Override
    public String toString() {
        return nome;
    }
}

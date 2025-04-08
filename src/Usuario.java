public class Usuario {

    private String usuario;
    private String senha;
    private String nome;

    public Usuario(String usuario, String senha, String nome) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

    public String getEmail() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}

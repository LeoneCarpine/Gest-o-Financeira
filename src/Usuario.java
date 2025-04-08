public class Usuario {
    // Atributos
    private String email;
    private String senha;
    private String nome;

    // Construtores
    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario() {
        email = " ";
        senha = " ";
        nome = " ";
    }

    // Métodos Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Metodo de Visualização de Resumo (para exibir transações do usuário)
    public void visualizarResumo() {
        System.out.println("Resumo de Transações de " + nome + ":");
        // Aqui, poderíamos exibir o histórico de transações desse usuário.
    }
}

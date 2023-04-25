import java.time.LocalDate;
import java.util.*;  


public class utilizador {
    private String email;
    private String password;
    private String nome;
    private String genero;
    private double altura;
    private double peso;
    private LocalDate dataNascimento;
    private LocalDate dataRegisto;
    // falta desporto favorito
    private List<atividade> atividades;

    public utilizador(){
        this.email = "";
        this.password = "";
        this.nome = "";
        this.genero = "";
        this.altura = 0;
        this.peso = 0;
        this.dataNascimento = LocalDate.now();
        this.dataRegisto = LocalDate.now();
        this.atividades = new ArrayList<atividade>();
    }

    public utilizador(String email, String password, String nome, String genero, double altura, double peso, LocalDate dataNascimento, LocalDate dataRegisto, List<atividade> atividades){
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.dataRegisto = dataRegisto;
        this.atividades = new ArrayList<atividade>();
    }

    public utilizador(utilizador u){
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.nome = u.getNome();
        this.genero = u.getGenero();
        this.altura = u.getAltura();
        this.peso = u.getPeso();
        this.dataNascimento = u.getDataNascimento();
        this.dataRegisto = u.getDataRegisto();
        this.atividades = new ArrayList<atividade>();
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getNome(){
        return this.nome;
    }

    public String getGenero(){
        return this.genero;
    }

    public double getAltura(){
        return this.altura;
    }

    public double getPeso(){
        return this.peso;
    }

    public LocalDate getDataNascimento(){
        return this.dataNascimento;
    }

    public LocalDate getDataRegisto(){
        return this.dataRegisto;
    }

    public List<atividade> getAtividades(){
        List<atividade> res = new ArrayList<atividade>();
        for(atividade a : this.atividades){
            res.add(a.clone());
        }
        return res;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public void setAltura(double altura){
        this.altura = altura;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public void setDataRegisto(LocalDate dataRegisto){
        this.dataRegisto = dataRegisto;
    }

    public void setAtividades(List<atividade> atividades){
        this.atividades = new ArrayList<atividade>();
        for(atividade a : atividades){
            this.atividades.add(a.clone());
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Genero: ").append(this.genero).append("\n");
        sb.append("Altura: ").append(this.altura).append("\n");
        sb.append("Peso: ").append(this.peso).append("\n");
        sb.append("Data de nascimento: ").append(this.dataNascimento).append("\n");
        sb.append("Data de registo: ").append(this.dataRegisto).append("\n");
        sb.append("==========  Atividades  ==========\n").append(this.atividades).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        utilizador u = (utilizador) o;
        return (this.email.equals(u.getEmail()) && 
                this.password.equals(u.getPassword()) && 
                this.nome.equals(u.getNome()) && 
                this.genero == u.getGenero() && 
                this.altura == u.getAltura() && 
                this.peso == u.getPeso() && 
                this.dataNascimento.equals(u.getDataNascimento()) && 
                this.dataRegisto.equals(u.getDataRegisto())) &&
                this.atividades.equals(u.getAtividades());
    }

    public utilizador clone(){
        return new utilizador(this);
    }

    public double calcularIdade(){
        return LocalDate.now().getYear() - this.dataNascimento.getYear();
    }

    public void adicionaAtividade(atividade a){
        this.atividades.add(a.clone());
    }
}

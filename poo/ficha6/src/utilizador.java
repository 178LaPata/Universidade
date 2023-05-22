import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class utilizador {
    public enum Genero{
        M,
        F
    }
    private String email; //chave do utilizador 
    private String password;
    private String nome;
    private Genero genero;
    private double altura;
    private double peso;
    private LocalDate dataNascimento;
    private LocalDate dataRegisto;
    private String desportoFav;
    private List<atividade> atividades;

    public utilizador(){
        this.email = "";
        this.password = "";
        this.nome = "";
        this.genero = null;
        this.altura = 0;
        this.peso = 0;
        this.dataNascimento = LocalDate.now();
        this.dataRegisto = LocalDate.now();
        this.desportoFav = "";
        this.atividades = new ArrayList<atividade>();
    }

    public utilizador(String email, String password, String nome, Genero genero, double altura, double peso, LocalDate dataNascimento, String desportoFav){
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        //this.dataRegisto = dataRegisto;
        this.desportoFav = desportoFav;
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
        this.desportoFav = u.getDesportoFav();
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

    public Genero getGenero(){
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

    public String getDesportoFav(){
        return this.desportoFav;
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

    public void setGenero(Genero genero){
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

    public void setDesportoFav(String desportoFav){
        this.desportoFav = desportoFav;
    }
    public void setAtividades(List<atividade> atividades){
        this.atividades = new ArrayList<atividade>();
        for(atividade a : atividades){
            this.atividades.add(a.clone());
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==============  Utilizador  ==============");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Genero: ").append(this.genero).append("\n");
        sb.append("Altura: ").append(this.altura).append("\n");
        sb.append("Peso: ").append(this.peso).append("\n");
        sb.append("Data de nascimento: ").append(this.dataNascimento).append("\n");
        sb.append("Data de registo: ").append(this.dataRegisto).append("\n");
        sb.append("Desporto favorito: ").append(this.desportoFav).append("\n");
        sb.append("==============  Atividades  ==============\n").append(this.atividades).append("\n");
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
                this.desportoFav.equals(u.getDesportoFav()) &&
                this.atividades.equals(u.getAtividades());
    }

    public utilizador clone(){
        return new utilizador(this);
    }

    public void adicionaAtividade(atividade a){
        this.atividades.add(a.clone());
    }

    public int diferencaDias(LocalDate data1, LocalDate data2){
        return (int) ChronoUnit.DAYS.between(data1, data2);
    }

}

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class corrida extends atividade{
    private int distancia;
    private int altimetria;
    private String percurso;

    public corrida(){
        super();
        this.distancia = 0;
        this.altimetria = 0;
        this.percurso = "";
    }

    public corrida(String codigo, String descricao, LocalDate dataAtividade, int minutos, int distancia, int altimetria, String percurso){
        super(codigo, descricao, dataAtividade, minutos);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.percurso = percurso;
    }

    public corrida(corrida c){
        super(c);
        this.distancia = c.getDistancia();
        this.altimetria = c.getAltimetria();
        this.percurso = c.getPercurso();
    }

    public int getDistancia(){
        return this.distancia;
    }

    public int getAltimetria(){
        return this.altimetria;
    }

    public String getPercurso(){
        return this.percurso;
    }

    public void setDistancia(int distancia){
        this.distancia = distancia;
    }

    public void setAltimetria(int altimetria){
        this.altimetria = altimetria;
    }

    public void setPercurso(String percurso){
        this.percurso = percurso;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Distancia: ").append(this.distancia).append("\n");
        sb.append("Altimetria: ").append(this.altimetria).append("\n");
        sb.append("Percurso: ").append(this.percurso).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        corrida co = (corrida) o;
        return  super.equals(co) &&
                this.distancia == co.getDistancia() &&
                this.altimetria == co.getAltimetria() &&
                this.percurso.equals(co.getPercurso());
    }

    public corrida clone(){
        return new corrida(this);   
    }

    //calcula o valor calórico dispendido numa corrida
    public void valorCalorico(utilizador u){
        double idade = ChronoUnit.YEARS.between(LocalDate.now(),u.getDataNascimento());
        super.setCalorias(this.distancia * u.getPeso() * (double) this.getMinutos() * idade/50);
    }
}

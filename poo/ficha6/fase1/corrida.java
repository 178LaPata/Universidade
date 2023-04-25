import java.time.LocalDate;

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

    public corrida(String descricao, LocalDate dataAtividade, int minutos, int distancia, int altimetria, String percurso){
        super(descricao, dataAtividade, minutos);
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
        atividade a = (corrida) o;
        return  super.equals(a) &&
                this.distancia == ((corrida) o).getDistancia() &&
                this.altimetria == ((corrida) o).getAltimetria() &&
                this.percurso.equals(((corrida) o).getPercurso());
    }

    public corrida clone(){
        return new corrida(this);   
    }
    
    public double valorCalorico(utilizador u){
        double caloriasCorrida;
        double peso = u.getPeso();
        double idade = u.calcularIdade();
        double minutos = (double) this.getMinutos();
        caloriasCorrida = (double) this.distancia * peso * minutos * idade/50;
        return caloriasCorrida;
    }
}

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class canoagem extends atividade{
    private String embarcacao;
    private double velocidadeVento; // em km/h
    private String direcaoVento;
    private double distancia;
    private int nrVoltas;

    public canoagem(){
        super();
        this.embarcacao = "";
        this.velocidadeVento = 0;
        this.direcaoVento = "";
        this.distancia = 0;
        this.nrVoltas = 0;
    }

    public canoagem(String codigo, String descricao, LocalDate dataAtividade, int minutos, String embarcacao, double velocidadeVento, String direcaoVento, double distancia, int nrVoltas){
        super(codigo, descricao, dataAtividade, minutos);
        this.embarcacao = embarcacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
        this.distancia = distancia;
        this.nrVoltas = nrVoltas;
    }

    public canoagem(canoagem c){
        super(c);
        this.embarcacao = c.getEmbarcacao();
        this.velocidadeVento = c.getVelocidadeVento();
        this.direcaoVento = c.getDirecaoVento();
        this.distancia = c.getDistancia();
        this.nrVoltas = c.getNrVoltas();
    }

    public String getEmbarcacao(){
        return this.embarcacao;
    }

    public double getVelocidadeVento(){
        return this.velocidadeVento;
    }

    public String getDirecaoVento(){
        return this.direcaoVento;
    }

    public double getDistancia(){
        return this.distancia;
    }

    public int getNrVoltas(){
        return this.nrVoltas;
    }

    public void setEmbarcacao(String embarcacao){
        this.embarcacao = embarcacao;
    }

    public void setVelocidadeVento(double velocidadeVento){
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVento(String direcaoVento){
        this.direcaoVento = direcaoVento;
    }

    public void setDistancia(double distancia){
        this.distancia = distancia;
    }

    public void setNrVoltas(int nrVoltas){
        this.nrVoltas = nrVoltas;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Embarcação: ").append(this.embarcacao).append("\n");
        sb.append("Velocidade do vento: ").append(this.velocidadeVento).append("\n");
        sb.append("Direção do vento: ").append(this.direcaoVento).append("\n");
        sb.append("Distância: ").append(this.distancia).append("\n");
        sb.append("Número de voltas: ").append(this.nrVoltas).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        canoagem c = (canoagem) o;
        return (super.equals(c) &&
                this.embarcacao.equals(c.getEmbarcacao()) &&
                this.velocidadeVento == c.getVelocidadeVento() &&
                this.direcaoVento.equals(c.getDirecaoVento()) &&
                this.distancia == c.getDistancia() &&
                this.nrVoltas == c.getNrVoltas());
    }
        
    public canoagem clone(){
        return new canoagem(this);
    }

    //calcula o valor calórico dispendido numa canoagem
    public void valorCalorico(utilizador u){
        double idade = ChronoUnit.YEARS.between(LocalDate.now(),u.getDataNascimento());
        super.setCalorias(this.distancia * this.velocidadeVento * (double) this.getMinutos() * idade/4);
    }
}

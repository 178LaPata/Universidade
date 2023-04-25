import java.time.LocalDate;

public class canoagem extends atividade{
    private String embarcacao;
    private int velocidadeVento;
    private String direcaoVento;
    private int distancia;
    private int nrVoltas;

    public canoagem(){
        super();
        this.embarcacao = "";
        this.velocidadeVento = 0;
        this.direcaoVento = "";
        this.distancia = 0;
        this.nrVoltas = 0;
    }

    public canoagem(String descricao, LocalDate dataAtividade, int minutos, String embarcacao, int velocidadeVento, String direcaoVento, int distancia, int nrVoltas){
        super(descricao, dataAtividade, minutos);
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

    public int getVelocidadeVento(){
        return this.velocidadeVento;
    }

    public String getDirecaoVento(){
        return this.direcaoVento;
    }

    public int getDistancia(){
        return this.distancia;
    }

    public int getNrVoltas(){
        return this.nrVoltas;
    }

    public void setEmbarcacao(String embarcacao){
        this.embarcacao = embarcacao;
    }

    public void setVelocidadeVento(int velocidadeVento){
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVento(String direcaoVento){
        this.direcaoVento = direcaoVento;
    }

    public void setDistancia(int distancia){
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
        atividade a = (canoagem) o;
        return (super.equals(a) &&
                this.embarcacao.equals(((canoagem) o).getEmbarcacao()) &&
                this.velocidadeVento == ((canoagem) o).getVelocidadeVento() &&
                this.direcaoVento.equals(((canoagem) o).getDirecaoVento()) &&
                this.distancia == ((canoagem) o).getDistancia() &&
                this.nrVoltas == ((canoagem) o).getNrVoltas());
    }
        
    public canoagem clone(){
        return new canoagem(this);
    }

    public double valorCalorico(utilizador u){
        double caloriasCorrida;
        double vento = (double) this.velocidadeVento;
        double idade = u.calcularIdade();
        double minutos = (double) this.getMinutos();
        caloriasCorrida = (double) this.distancia * vento * minutos * idade/4;
        return caloriasCorrida;
    }
}

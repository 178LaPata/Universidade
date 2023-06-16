import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class abdominais extends atividade {
    private String tipoAbdominal;
    private int nrRepeticoes;

    public abdominais(){
        super();
        this.tipoAbdominal = "";
        this.nrRepeticoes = 0;
    }

    public abdominais(String codigo, String descricao, LocalDate dataAtividade, int minutos, String tipoAbdominal, int nrRepeticoes){
        super(codigo, descricao, dataAtividade, minutos);
        this.tipoAbdominal = tipoAbdominal;
        this.nrRepeticoes = nrRepeticoes;
    }

    public abdominais(abdominais a){
        super(a);
        this.tipoAbdominal = a.getTipoAbdominal();
        this.nrRepeticoes = a.getNrRepeticoes();
    }

    public String getTipoAbdominal(){
        return this.tipoAbdominal;
    }

    public int getNrRepeticoes(){
        return this.nrRepeticoes;
    }

    public void setTipoAbdominal(String tipoAbdominal){
        this.tipoAbdominal = tipoAbdominal;
    }

    public void setNrRepeticoes(int nrRepeticoes){
        this.nrRepeticoes = nrRepeticoes;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Tipo de abdominal: ").append(this.tipoAbdominal).append("\n");
        sb.append("Numero de repeticoes: ").append(this.nrRepeticoes).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        abdominais a = (abdominais) o;
        return (super.equals(a) &&
                this.tipoAbdominal.equals(a.getTipoAbdominal()) &&
                this.nrRepeticoes == a.getNrRepeticoes());
    }

    public abdominais clone(){
        return new abdominais(this);
    }

    //calcula o valor calórico dispendido nuns abdominais
    public void valorCalorico(utilizador u){
        super.setCalorias((double) (this.getMinutos() * this.nrRepeticoes * 3) /5);
    }
}



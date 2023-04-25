import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class encEficiente {
    private String nomeCliente;
    private int nif;
    private String morada;
    private int nrEncomenda;
    private LocalDateTime dataEncomenda;
    private List<linhaEncomenda> linhasEncomenda;

    public encEficiente() {
        this.nomeCliente = "";
        this.nif = 0;
        this.morada = "";
        this.nrEncomenda = 0;
        this.dataEncomenda = LocalDateTime.now();
        this.linhasEncomenda = new ArrayList<>();
    }

    public encEficiente(String nomeCliente, int nif, String morada, int nrEncomenda, LocalDateTime dataEncomenda, List<linhaEncomenda> le) {
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.morada = morada;
        this.nrEncomenda = nrEncomenda;
        this.dataEncomenda = dataEncomenda;
        this.linhasEncomenda = new ArrayList<>(le);
    }

    public encEficiente(encEficiente e) {
        this.nomeCliente = e.getNomeCliente();
        this.nif = e.getNif();
        this.morada = e.getMorada();
        this.nrEncomenda = e.getNrEncomenda();
        this.dataEncomenda = e.getDataEncomenda();
        this.linhasEncomenda = e.getLinhasEncomenda();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNif() {
        return nif;
    }

    public String getMorada() {
        return morada;
    }

    public int getNrEncomenda() {
        return nrEncomenda;
    }

    public LocalDateTime getDataEncomenda() {
        return dataEncomenda;
    }

    public List<linhaEncomenda> getLinhasEncomenda() {
        ArrayList<linhaEncomenda> res = new ArrayList<>();
        for(linhaEncomenda le: this.linhasEncomenda){
            res.add(le.clone());
        }
        return res;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNrEncomenda(int nrEncomenda) {
        this.nrEncomenda = nrEncomenda;
    }
    
    public void setDataEncomenda(LocalDateTime dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }
    
    public void setLinhasEncomenda(List<linhaEncomenda> linhasEncomenda) {
        this.linhasEncomenda = new ArrayList<>(linhasEncomenda.size());
        for(linhaEncomenda le: linhasEncomenda){
            this.linhasEncomenda.add(le.clone());
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        encEficiente e = (encEficiente) o;
        return this.nomeCliente.equals(e.getNomeCliente()) &&
               this.nif == e.getNif() &&
               this.morada.equals(e.getMorada()) &&
               this.nrEncomenda == e.getNrEncomenda() &&
               this.dataEncomenda.equals(e.getDataEncomenda()) &&
               this.linhasEncomenda.equals(e.getLinhasEncomenda());
            }
            
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do cliente: ").append(this.nomeCliente).append("\n");
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Morada: ").append(this.morada).append("\n");
        sb.append("Numero de encomenda: ").append(this.nrEncomenda).append("\n");
        sb.append("Data da encomenda: ").append(this.dataEncomenda).append("\n");
        sb.append("Linha de encomenda: ").append(this.linhasEncomenda).append("\n");
        return sb.toString();
    }

    public encEficiente clone() {
        return new encEficiente(this);
    }

    public double calculaValorTotal(){
        double total = 0;
        for(linhaEncomenda l : this.linhasEncomenda){
            total += l.calculaValorLinhaEnc();
        }
        return total;
    }

    public double calculaValorDesconto(){
        double total = 0;
        for(linhaEncomenda l : this.linhasEncomenda){
            total += l.calculaValorDesconto();
        }
        return total;
    }
    public int numeroTotalProdutos(){
        int total = 0;
        for(linhaEncomenda l : this.linhasEncomenda){
            total += l.getQuantidade();
        }
        return total;
    }

    public boolean existeProdutoEncomenda(String refProduto){
        for(linhaEncomenda l : this.linhasEncomenda){
            if(l.getReferencia().equals(refProduto))
                return true;
        }
        return false;
    }

    public void adicionaLinha(linhaEncomenda linha){
        this.linhasEncomenda.add(linha.clone());
    }

    public void removeProduto(String codProd){
        for(linhaEncomenda l : this.linhasEncomenda){
            if(l.getReferencia().equals(codProd))
                this.linhasEncomenda.remove(l);
        }
    }
}
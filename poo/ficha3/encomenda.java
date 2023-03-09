import java.time.LocalDate;
import java.util.Arrays;
public class encomenda {
    private String nomeCliente;
    private int nif;
    private String morada;
    private int nrEncomenda;
    private LocalDate dataEncomenda;
    private linhaEncomenda[] linhasEncomenda;

    public encomenda(){
        this.nomeCliente = "";
        this.nif = 0;
        this.morada = "";
        this.nrEncomenda = 0;
        this.dataEncomenda = LocalDate.now();
        this.linhasEncomenda = new linhaEncomenda[0];
    }

    public encomenda(String nomeCliente, int nif, String morada, int nrEncomenda, LocalDate dataEncomenda, linhaEncomenda[] linhasEncomenda){
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.morada = morada;
        this.nrEncomenda = nrEncomenda;
        this.dataEncomenda = dataEncomenda;
        this.linhasEncomenda = linhasEncomenda;
    }

    public encomenda(encomenda e){
        this.nomeCliente = e.getNomeCliente();
        this.nif = e.getNif();
        this.morada = e.getMorada();
        this.nrEncomenda = e.getNrEncomenda();
        this.dataEncomenda = e.getDataEncomenda();
        this.linhasEncomenda = e.getLinhasEncomenda();
    }

    public String getNomeCliente(){
        return this.nomeCliente;
    }

    public int getNif(){
        return this.nif;
    }

    public String getMorada(){
        return this.morada;
    }

    public int getNrEncomenda(){
        return this.nrEncomenda;
    }

    public LocalDate getDataEncomenda(){
        return this.dataEncomenda;
    }

    public linhaEncomenda[] getLinhasEncomenda(){
        return this.linhasEncomenda;
    }

    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }

    public void setNif(int nif){
        this.nif = nif;
    }

    public void setMorada(String morada){
        this.morada = morada;
    }

    public void setNrEncomenda(int nrEncomenda){
        this.nrEncomenda = nrEncomenda;
    }

    public void setDataEncomenda(LocalDate dataEncomenda){
        this.dataEncomenda = dataEncomenda;
    }

    public void setLinhasEncomenda(linhaEncomenda[] linhasEncomenda){
        this.linhasEncomenda = linhasEncomenda;
    }

    public boolean equals(Object o){
        if (this == o) 
            return true;
        if ((o == null) || (this.getClass() != o.getClass())) 
            return false;
        encomenda l = (encomenda) o;
        return (this.nomeCliente.equals(l.getNomeCliente()) &&
                this.nif == l.getNif() &&
                this.morada.equals(l.getMorada()) &&
                this.nrEncomenda == l.getNrEncomenda() &&
                this.dataEncomenda.equals(l.getDataEncomenda()) &&
                this.linhasEncomenda.equals(l.getLinhasEncomenda()));
    }

    public String toString(){
        return "Nome do cliente: " + this.nomeCliente + 
                "\nNIF: " + this.nif + 
                "\nMorada: " + this.morada + 
                "\nNúmero da encomenda: " + this.nrEncomenda + 
                "\nData da encomenda: " + this.dataEncomenda + 
                "\nLinhas da encomenda: " + Arrays.toString(this.linhasEncomenda);
    }

    public encomenda clone() {
        return new encomenda(this);
    }

    public double calculaValorTotal(){
        double total = 0;
        for (linhaEncomenda linha : this.linhasEncomenda) {
            total += linha.calculaValorLinhaEnc();
        }
        return total;
    }

    public double calculaValorDesconto2(){
        double desconto = 0;
        for(linhaEncomenda linha : this.linhasEncomenda) {
            desconto += linha.calculaValorDesconto();
        }
        return desconto;
    }

    public int numeroTotalProdutos(){
        int total = 0;
        for(linhaEncomenda linha : this.linhasEncomenda) {
            total += linha.getQuantidade();
        }
        return total;
    }

    public boolean existeProdutoEncomenda(String refProduto){
        boolean existe = false;
        for(linhaEncomenda linha : this.linhasEncomenda) {
            if(linha.getReferencia().equals(refProduto)) {
                existe = true;
            }
        }
        return existe;
    }

    public void adicionaLinha(linhaEncomenda linha){
        linhaEncomenda[] nova = new linhaEncomenda[this.linhasEncomenda.length + 1];
        for(int i = 0; i < this.linhasEncomenda.length; i++) {
            nova[i] = this.linhasEncomenda[i];
        }
        nova[nova.length - 1] = linha;
        this.linhasEncomenda = nova;
    }

    public void removeProduto(String codProd) {
        linhaEncomenda[] linhasAtualizadas = new linhaEncomenda[linhasEncomenda.length - 1];
        int i = 0;
        boolean encontrado = false;
        for (linhaEncomenda linha : linhasEncomenda) {
            if (linha.getReferencia().equals(codProd)) {
                encontrado = true;
            } else {
                linhasAtualizadas[i++] = linha.clone();
            }
        }
        if (encontrado) {
            this.linhasEncomenda = linhasAtualizadas;
        } else {
            System.out.println("Produto não encontrado na encomenda.");
        }
    }
    
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class gestaoEncomendas {
    Map<Integer, encomenda> encomendas;

    public gestaoEncomendas() {
        this.encomendas = new HashMap<>();
    }
    
    public Set<Integer> todosCodigosEnc(){
        return this.encomendas.keySet();
    }
    
    public void addEncomenda(encomenda enc){
        this.encomendas.put(enc.getNrEncomenda(), enc);
    }

    public encomenda getEncomenda(Integer codEnc){
        for(Map.Entry<Integer, encomenda> e : this.encomendas.entrySet()){
            if(e.getKey().equals(codEnc)) return e.getValue();
        }   
        return null;
    }
 
    public void removeEncomenda(Integer codEnc){
        this.encomendas.remove(codEnc);
    }

    public Integer encomendaComMaisProdutos(){
        int max = 0;
        int cod = 0;
        for(Map.Entry<Integer, encomenda> e : this.encomendas.entrySet()){
            if(e.getValue().numeroTotalProdutos() > max){
                max = e.getValue().numeroTotalProdutos();
                cod = e.getKey();
            }
        }
        return cod;
    }
    
    public Set<Integer> encomendasComProduto(String codProd){
        Set<Integer> res = new HashSet<>();
        for(Map.Entry<Integer, encomenda> e : this.encomendas.entrySet()){
            if(e.getValue().existeProdutoEncomenda(codProd)) 
                res.add(e.getKey());
        }
        return res;

    }


}

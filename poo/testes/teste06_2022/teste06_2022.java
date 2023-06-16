package teste06_2022;

import java.io.*;
import java.util.*;

abstract class Veiculo implements Comparable<Veiculo>, Serializable {
    private String matricula;
    private String marca;
    private String modelo;
    private double precokm; // preço base por km
    private List<String> viagens;

    public abstract float custoViagem(float distancia);
}


class VeiculoLuxo extends Veiculo implements Comparable<VeiculoLuxo> {
    private float taxaLuxo;

    public float custoViagem(float distancia) {
        return distancia * getPrecoKm() * (1.1 + this.taxaLuxo);
    }

}


class Autocarro extends Veiculo implements Comparable<Autocarro> {
    private int lotacao; //lotação máxima do autocarro
    
    public float custoViagem(float distancia) {
        if (this.lotacao > 10)
            return distancia * getPrecoKm() * 0.5/this.lotacao;
        else
            return distancia * 0.75 * this.lotacao;
    }
}


class Viagem implements Serializable {
    private String origem; // local de origem da viagem
    private String destino; // local de destino
    private float distância;
    private float custo; // valor do custo da viagem
    
    public Viagem(String origem, String destino, float distancia, float custo) {...}
}


class UberUM {
    private Map<String, Veiculo> veiculos;

    public UberUM(Set<Map.Entry<String,Veiculo>> info){
        this.veiculos = new HashMap<>();
        for(Map.Entry<String,Veiculo> v : info)
            this.veiculos.put(v.getKey(), v.getValue().clone());
    }

    public Viagem viagemMaisAntiga(String matricula) throws VeiculoInexistenteException, SemViagensException {
        Veiculo v = this.veiculos.get(matricula);
        if(v == null){
            throw new VeiculoInexistenteException();
        } 
        List<String> viagens = v.getViagens();
        if(viagens.size() == 0){
            throw new SemViagensException();
        }
        return viagens.get(0);
    }

    public void adicionaViagem(String matricula, String origem, String destino, float distancia) throws VeiculoInexistenteException {
        Veiculo v = this.veiculos.get(matricula);
        if(v == null){
            throw new VeiculoInexistenteException();
        }
        Viagem viagem = new Viagem(origem, destino, distancia, v.custoViagem(distancia));
        v.addViagem(viagem);
    }
}
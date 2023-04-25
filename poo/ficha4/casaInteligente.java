import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class casaInteligente {
    private Set<lampada> lampadas;

    public casaInteligente(){
        this.lampadas = new HashSet<>();
    }

    public casaInteligente(Set<lampada> l){
        setLampadas(l);
    }

    public casaInteligente(casaInteligente c){
        this.lampadas = c.getLampadas();
    }

    public Set<lampada> getLampadas(){
        Set<lampada> res = new HashSet<>();
        for(lampada lamp : this.lampadas){
            res.add(lamp.clone());
        }
        return res;
    }

    public void setLampadas(Set<lampada> l){
        this.lampadas = new HashSet<>();
        for(lampada lamp : l){
            this.lampadas.add(lamp.clone());
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(lampada lamp : this.lampadas){
            sb.append(lamp.toString());
        }
        return "==========  Casa  ==========" + sb + "============================";
    }

    public boolean equals(Object o){
        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        casaInteligente c = (casaInteligente) o;
        return (this.lampadas.equals(c.getLampadas()));
    }

    public casaInteligente clone(){
        return new casaInteligente(this);
    }

    public void addLampada(lampada l){
        this.lampadas.add(l.clone());
    }

    public void ligaLampadaNormal(int index){
        Iterator<lampada> it = this.lampadas.iterator();
        int i = 0;
        while(it.hasNext() && i < index){
            it.next();
            i++;
        }
        if(it.hasNext()){
            lampada l = it.next();
            l.lampON();
        }
    }

    public void ligaLampadaECO(int index){
        Iterator<lampada> it = this.lampadas.iterator();
        int i = 0;
        while(it.hasNext() && i < index){
            it.next();
            i++;
        }
        if(it.hasNext()){
            lampada l = it.next();
            l.lampECO();
        }
    }

    public int qtEmEco(){
        int res = 0;
        for(lampada lamp : this.lampadas){
            if(lamp.getModo() == lampada.Modo.ECO)
                res++;
        }
        return res;
    }

    public void removeLampada(int index){
        Iterator<lampada> it = this.lampadas.iterator();
        int i = 0;
        while(it.hasNext() && i < index){
            it.next();
            i++;
        }
        if(it.hasNext()){
            it.next();
            it.remove();
        }
    }

    public void ligaTodasEco(){
        for(lampada lamp : this.lampadas){
            lamp.lampECO();
        }
    }

    public double consumoTotal(){
        double res = 0;
        for(lampada lamp : this.lampadas){
            res += lamp.totalConsumo();
        }
        return res;
    }

    public lampada maisGastadora(){
        lampada res = new lampada();
        for(lampada lamp : this.lampadas){
            if(lamp.totalConsumo() > res.totalConsumo())
                res = lamp;
        }
        return res;
    }

    public Set<lampada> lampadasEmModoEco(){
        Set<lampada> res = new HashSet<>();
        for(lampada lamp : this.lampadas){
            if(lamp.getModo() == lampada.Modo.ECO)
                res.add(lamp.clone());
        }
        return res;
    }

    public void reset(){
        for(lampada lamp : this.lampadas){
            lamp.resetPeriodo();
        }
    }

    /* nao esta a funcionar
    public Set<lampada> podiumEconomia(){
        Set<lampada> res = new HashSet<>();
        for(lampada lamp : this.lampadas){
            res.add(lamp.clone());
        }
        Set<lampada> podium = new HashSet<>();
        for(int i = 0; i < 3; i++){
            lampada l = new lampada();
            for(lampada lamp : res){
                if(lamp.totalConsumo() < l.totalConsumo())
                    l = lamp;
            }
            podium.add(l.clone());
            res.remove(l);
        }
        return podium;
    }
    */
}

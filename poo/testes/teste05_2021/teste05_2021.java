package teste05_2021;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;


class SmartDevice {
    private String id;
    private boolean on;
    private double consumoPorHora;
    private LocalDateTime inicio;
    
    
    public SmartDevice( String id, double consumoPorHora) {
        this.id = id;
        this.on = false;
        this.consumoPorHora = consumoPorHora;
    }
    
    public double totalConsumo() {...}

    public void turnOn() {
        this.on = true;
        if (this.inicio == null)
            this.inicio = LocalDateTime.now();
    }
}


class SmartBulb extends SmartDevice {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    private int tone;
    
    public SmartBulb(String id, int tone, double consumoPorHora) {
        super(id, consumoPorHora);
        this.tone = tone;
    }
    
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }
}

class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume maximo da coluna
    private int volume;
    private String channel;

    public SmartSpeaker(String id, String channel, double consumoPorHora) {
        super(id, consumoPorHora);
        this.channel = channel;
        this.volume = 10;
    }
}


class SmartBulbDimmable extends SmartBulb{
    private int intensidade;
    private int ligada;

    public SmartBulbDimmable(int intensidade, int ligada, String id, int tone, double consumoPorHora){
        super(id,tone,consumoPorHora);
        this.intensidade = intensidade;
        this.ligada = ligada;
    }

    public void turnOn() {
        if(this.ligada == 0){
            this.intensidade = this.intensidade/2;
            super.setConsumoPorHora(super.getConsumoPorHora()/2);
        }
        super.turnOn();
        this.ligada++;
    }
}


class CasaInteligente {
    Collection<SmartDevice> devices;
    Map<String, Collection<SmartDevice>> divisoes;

    public CasaInteligente(Collection<SmartDevice> dispositivo){    
        Iterator<SmartDevice> it = dispositivo.iterator();
        while(it.hasNext()){
            SmartDevice d = it.next();
            this.devices.add(d.clone());
        }
    }

    public void remove(String id) throws DeviceNaoExisteException {
        if(this.devices.removeIf(d -> d.getId().equals(id))){
            this.divisoes.values().forEach(c -> c.removeIf(d -> d.getId().equals(id)));
        }
        else throw new DeviceNaoExisteException("O dispositivo não existe");
    }

    public Iterator<SmartDevice> devicesPorConsumoCrescente(){
        Comparator<SmartDevice> comp = (sd1, sd2) -> sd1.getConsumoPorHora().compareTo(sd2.getConsumoPorHora());
        return this.devices.stream().map(SmartDevice :: clone).sorted(comp).iterator();
    }

    public String divisaoMaisEconomica(){
        double min = Double.MAX_VALUE;
        String div = null;
        for(String divisao : this.divisoes.keySet()){
            double consumoDiv = 0.0;
            for(SmartDevice sd : divisoes.get(divisao)){
                consumoDiv += sd.getConsumoPorHora();
            }
            if(consumoDiv < min || (consumoDiv == min && divisao.compareTo(div) > 0)){
                min = consumoDiv; 
                div = divisao;
            }
        }
        return div;
    }

    public static Consumer<SmartBulbDimmable> alteraLuminosidade(){
        return (bd -> bd.setIntensidade(bd.getIntensidade()/4));
    }

    public void alteraInfo(Consumer<SmartBulbDimmable> bd){
        for(SmartDevice sd : this.devices){
            if(sd instanceof SmartBulbDimmable){
                bd.accept((SmartBulbDimmable) sd);
            }
        }
    }

    public boolean apenasNumaDivisao(){
        return this.devices.stream()
                           .filter(d -> this.divisoes
                           .values()
                           .stream()
                           .filter(c -> c.contains(d))
                           .count() > 1)
                           .count() == 0;
    }

    public boolean gravaEmFichObjectos(String fich) throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich))){
            oos.writeObject(this);
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
}


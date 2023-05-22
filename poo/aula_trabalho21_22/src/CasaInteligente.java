import java.util.List;
import java.util.ArrayList;

public class CasaInteligente {
    private String morada;
    private List<SmartDevice> ids;

    public CasaInteligente(){
        this.morada = "";
        this.ids = new ArrayList<>();
    }

    public CasaInteligente(String morada, List<SmartDevice> ids){
        this.morada = morada;
        setIds(ids);
    }

    public CasaInteligente(CasaInteligente ci){
        this.morada = ci.getMorada();
        this.ids = ci.getIds();
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public List<SmartDevice> getIds() {
        List<SmartDevice> res = new ArrayList<>();
        for(SmartDevice sd : this.ids){
            res.add(sd.clone());
        }
        return res;
    }

    public void setIds(List<SmartDevice> ids) {
        this.ids = new ArrayList<>();
        for(SmartDevice sd : ids){
            this.ids.add(sd.clone());
        }
    }


}



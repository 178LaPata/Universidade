import java.util.ArrayList;
import java.util.List;

public class stackStrings{
    private List<String> elem;

    public stackStrings(){
        this.elem = new ArrayList<String>(); 
    }

    public stackStrings(List<String> l){
        this.elem = new ArrayList<>(); // como em cima ja digo que e string aqui nao preciso de dizer
        for(String s : l){
            this.elem.add(s);
        }
    }

    public stackStrings(stackStrings s){
        this.elem = s.getElem();
    }

    public List<String> getElem(){
        ArrayList<String> res = new ArrayList<>();
        for(String s : this.elem){
            res.add(s);
        }
        return res;
    }

    public boolean equals(Object o){
        if(this == o) 
            return true;
        if((o == null) || (this.getClass() != o.getClass())) 
            return false;
        stackStrings ss = (stackStrings) o;
        return this.elem.equals(ss.getElem());
    }

    public stackStrings clone(){
        return new stackStrings(this);
    }

    public String top(){
        if(!this.elem.isEmpty()){
            return this.elem.get(this.elem.size()-1);
        } else {
            return null; // nao e uma boa solucao 
        }
    }

    public void push(String s){
        this.elem.add(s);
    }

    public String pop(){
        if(!this.elem.isEmpty()){
            String topo = top();
            this.elem.remove(this.elem.size()-1);
            return topo;
        }
        return null;
    }

    public boolean empty(){
        return this.elem.isEmpty();
    }

    public int length(){
        return this.elem.size();
    }
}
package teste06_2021;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public interface Playable {
    public void play();
}


class Episodio implements Playable {
    private String nome;
    private double duracao;
    private int classificacao; //dada pelos seus ouvintes (valor de 0..100)
    private List<String> conteudo; //corresponde ao texto que e’ dito
    //quando se reproduz o episodio
    private int numeroVezesTocada; //qts vezes e’ que o podcast foi ouvido
    private LocalDateTime ultimaVez; //regista quando foi reproduzido ultima vez

    public Episodio(String nome, double duracao, int classificacao, List<String> conteudo, int numeroVezesTocada, LocalDateTime ultimaVez){
        this.nome = nome;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.conteudo = conteudo;
        this.numeroVezesTocada = numeroVezesTocada;
        this.ultimaVez = ultimaVez;
    }

    public void play() {
        for(String s : this.conteudo) {
            System.media.print(s);
        }
    }
}


class EpisodioVideo extends Episodio {
    private List<Byte> video;

    public EpisodioVideo(String nome, double duracao, int classificacao, List<String> conteudo, int numeroVezesTocada, LocalDateTime ultimaVez, List<Byte> video){
        super(nome, duracao, classificacao, conteudo, numeroVezesTocada, ultimaVez);
        this.video = video;
    }

    public void play(){
        super.play();
        for(Byte b : this.video){
            System.media.print(b);
        }
    }
}


class Utilizador {
    private String nome;
    private String email;
    private Set<Podcast> podcastsSubs;    

    public void playEpisodio(String idPodCast, String nomeEpisodio) throws AlreadyPlayingException{
        for(Podcast p : this.podcastsSubs){
            if(p.getNome().equals(idPodCast)){
                for(Episodio e : p.getEpisodios()){
                    if(e.getNome().equals(nomeEpisodio)){
                        if(e.getNumeroVezesTocada() == 0){
                            e.setUltimaVez(LocalDateTime.now());
                            e.play();
                        }
                        // throw new AlreadyPlayingException() se o episodio jรก estiver a ser tocado
                        else if (e.getUltimaVez().equals(LocalDateTime.now() || e.getUlimaVez().plusMinutes(e.getDuracao()).isAfter(LocalDateTime.now()))){
                            throw new AlreadyPlayingException();
                        }
                        else {
                            e.setUltimaVez(LocalDateTime.now());
                            e.setNumeroVezesTocada(e.getNumeroVezesTocada() + 1);
                            e.play();
                        }
                    }
                }
            }
        }
    }
}


class UtilizadorPremium extends Utilizador{
    private List <Episodio> listaEspera;

    public void playEpisodio(String idPodCast, String nomeEpisodio) throws AlreadyPlayingException{
        try{
            super.playEpisodio(idPodCast, nomeEpisodio);
        }
        catch(AlreadyPlayingException e){
            for(Podcast p : super.getPodcastsSubs()){
                if(p.getNome().equals(idPodCast)){
                    for(Episodio ep : p.getEpisodios()){
                        if(ep.getNome().equals(nomeEpisodio)){
                            this.listaEspera.add(ep);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void gravaInfoEpisodiosParaTocarMaisTarde(String fich) throws IOException {
        try (FileWriter fw = new FileWriter(fich)) {
            fw.write(super.getNome() + "\n")
            for (Episodio e : this.listaEspera) {
                fw.write(e.getNome() + "-" + e.getDuracao() + "\n");
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }
}


class Podcast {
    private String nome;
    private List<Episodio> episodios;

    public List<Episodio> getEpisodios(){
        return this.episodios.stream().map(Episodio::clone).collect(Collectors.toList());
    }
}


class SpotifyPOO {
    private Map<String,Podcast> podcasts;
    private Map<String,Episodio> episodio;
    private Map<String,Utilizador> utilizadores;

    public List<Episodio> getEpisodios(String nomePodcast){
        Podcast p = podcasts.get(nomePodcast);
        return p.getEpisodios();
    }

    public void remove(String nomeP) throws PodcastNotRegistered, PodcastHasSubscription{
        Podcast p = this.podcasts.get(nomeP);
        if(p==null) {
            throw new PodcastNotRegistered();
        }
        for(Utilizador u : this.utilizadores.values()){
            for(Podcast p1 : u.getPodcastsSubs()){
                if(p1.equals(p)){
                    throw  new PodcastHasSubscription();
                }
            }
        }

        this.podcasts.remove(nomeP);
    }

    public Episodio getEpisodioMaisLongo(String u){
        double max = 0;
        Utilizador user = this.utilizadores.get(u);
        Episodio ep = new Episodio();
        for(Podcast p : user.getPodcastsSubs()){
            for(Episodio e : p.getEpisodios()){
                if(e.getDuracao() > max){
                    max = e.getDuracao();
                    ep = e;
                }
            }
        }
        if(max == 0){
            return null;
        }
        return ep;
    }

    public Map<Integer,List<Episodio>> episodiosPorClassf(){
        Map<Integer, List<Episodio>> lista = new HashMap<>();
        for(Episodio e : this.episodio.values()){
            if(lista.containsKey(e.getClassificacao())){
                lista.get(e.getClassificacao()).add(e.clone());
            }
            else {
                List<Episodio> ep = new ArrayList<>();
                ep.add(e.clone());
                lista.put(e.getClassificacao(),ep);
            }
        }
        return lista;
    }
}


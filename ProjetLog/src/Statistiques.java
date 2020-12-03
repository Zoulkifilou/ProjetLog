import java.util.HashMap;

public class Statistiques {
    private int nbIpDif;
    private HashMap<String, Integer> DixPremIpH;
    private HashMap<String, Integer> DixPremIpK;
    private HashMap<String, String> DixPremUrl;
    private HashMap<String, String> DixPremUrlSource;
   
    private int TtotalKo;
    private HashMap<String, Integer> os;
    private HashMap<String, Integer> navigateur;

    public int getNbIpDif() {
        return nbIpDif;
    }

    public void setNbIpDif(int nbIpDif) {
        this.nbIpDif = nbIpDif;
    }

    public HashMap<String, Integer> getDixPremIpH() {
        return DixPremIpH;
    }

    public void setDixPremIpH(HashMap<String, Integer> dixPremIpH) {
        DixPremIpH = dixPremIpH;
    }

    public HashMap<String, Integer> getDixPremIpK() {
        return DixPremIpK;
    }

    public void setDixPremIpK(HashMap<String, Integer> dixPremIpK) {
        DixPremIpK = dixPremIpK;
    }

    public HashMap<String, String> getDixPremUrl() {
        return DixPremUrl;
    }

    public void setDixPremUrl(HashMap<String, String> dixPremUrl) {
        DixPremUrl = dixPremUrl;
    }

    public int getTtotalKo() {
        return TtotalKo;
    }

    public void setTtotalKo(int ttotalKo) {
        TtotalKo = ttotalKo;
    }

    public HashMap<String, Integer> getOs() {
        return os;
    }

    public void setOs(HashMap<String, Integer> os) {
        this.os = os;
    }

    public HashMap<String, Integer> getNavigateur() {
        return navigateur;
    }

    public void setNavigateur(HashMap<String, Integer> navigateur) {
        this.navigateur = navigateur;
    }
   
   

    public HashMap<String, String> getDixPremUrlSource() {
return DixPremUrlSource;
}

public void setDixPremUrlSource(HashMap<String, String> dixPremUrlSource) {
DixPremUrlSource = dixPremUrlSource;
}

public Statistiques() {

    }

}
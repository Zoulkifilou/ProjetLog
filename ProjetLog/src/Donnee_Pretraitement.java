import java.util.ArrayList;

public class Donnee_Pretraitement {
    private String ip;
    private String url;
    private String urlOrigine;
    private String navigateur;
    private String os;
    private String ko;
    private String instant;
    private String code;
    private ArrayList<String> TabDatas;

    public Donnee_Pretraitement(String ip, String url, String urlOrigine, String navigateur, String os, String ko, String instant, String code) {
        this.ip = ip;
        this.url = url;
        this.urlOrigine = urlOrigine;
        this.navigateur = navigateur;
        this.os = os;
        this.ko = ko;
        this.instant = instant;
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOrigine() {
        return urlOrigine;
    }

    public void setUrlOrigine(String urlOrigine) {
        this.urlOrigine = urlOrigine;
    }

    public String getNavigateur() {
        return navigateur;
    }

    public void setNavigateur(String navigateur) {
        this.navigateur = navigateur;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}
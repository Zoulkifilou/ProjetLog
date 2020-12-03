import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Calcul_Statistiques 
{
    public int CompteurIP(ArrayList<Donnee_Pretraitement> filedata){
    	// Calculer le nombre dï¿½adresses IP diffï¿½rentes
    	// Initialiser le nombre d'adresse IP diffï¿½rentes ï¿½ la taille de la liste
    	int nbIp = filedata.size();
     
    	for(int i = 0; i < filedata.size(); i++) {
     
    		for(int j = i+1; j < filedata.size() -1; j++) {
     
    			//Dï¿½crï¿½menter le nombre d'adresse IP diffï¿½rentes ï¿½ chaque fois que 2 adresses IP sont ï¿½gales
    			if(filedata.get(i).getIp().equals(filedata.get(j).getIp())) {
    				nbIp--;	
    			}
    			
    		}
    	}
    	return nbIp;
    }
    public HashMap<String, Integer> CompteurHitIp(ArrayList<Donnee_Pretraitement> filedata) {
   
    	// Calculer les 10 premiï¿½res IP (en nombre de hits)
    	HashMap<String, Integer> ipsHits = new HashMap<String, Integer>();
   
    	// Calculer le nombre de hits pour chaque IP
    	for(int i = 0; i < filedata.size(); i++) {
	        int nbHittmp = 0;
	        for(int j = 0; j < filedata.size(); j++) {
	        	 if(filedata.get(i).getIp().equals(filedata.get(j).getIp()))
	        		 	nbHittmp++;
         
         //
	        	 		ipsHits.put(filedata.get(i).getIp(), nbHittmp);
         
         }
        }
   
    	List linkedlist = new LinkedList(ipsHits.entrySet());
   
    	//Trier les adresses ip selon leur nombre de hits dans l'ordre dï¿½croissant
        Collections.sort(linkedlist, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                   .compareTo(((Map.Entry) (o1)).getValue());
             }
        });
       
        //Crï¿½ation d'une nouvelle Hashmap pour conserver seulement 10 valeurs triï¿½es
        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
       
        int cpt = 0;
       
        //ajouter les 10 valeurs ï¿½ la nouvelle Hashmap ayant le plus de hits
        for (Iterator it = linkedlist.iterator(); it.hasNext() && cpt < 10;cpt++) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put((String)entry.getKey(), (Integer) entry.getValue());
        }
   
   
        //Affichage dans la console
        Set cles = sortedHashMap.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
        	String cle = (String) it.next();
        	int valeur = ipsHits.get(cle);
     
        	System.out.println(cle + " " + valeur);
     
        }
    // retourner la map de 10 valeurs triï¿½es
        return sortedHashMap;
    }
   
   
   
    public HashMap<String, Integer> CompteurKo(ArrayList<Donnee_Pretraitement> filedata) {
    	// Calculer les 10 premiï¿½res IP (en nombre de Ko)
    	HashMap<String, Integer> ipsKo = new HashMap<String, Integer>();
   
    	//Initialiser la hashMap avec le nombre de Ko associï¿½ ï¿½ chaque IP
    	for(int i = 0; i < filedata.size();i++) {
    		ipsKo.put(filedata.get(i).getIp(), Integer.parseInt(filedata.get(i).getKo()));
    	}
   
    	//Trier la hashmap selon son nombre de Ko
    	List linkedlist = new LinkedList(ipsKo.entrySet());
        		Collections.sort(linkedlist, new Comparator() {
        			public int compare(Object o1, Object o2) {
        				return ((Comparable) ((Map.Entry) (o2)).getValue())
        						.compareTo(((Map.Entry) (o1)).getValue());
             }
        });
        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
       
        int cpt = 0;
        //Conserver les 10 premiï¿½res IP ayant le plus grand nombre de Ko
        for (Iterator it = linkedlist.iterator(); it.hasNext() && cpt < 10;cpt++) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put((String)entry.getKey(), (Integer) entry.getValue());
        }
   
   
       
        Set cles = sortedHashMap.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
        	String cle = (String) it.next();
        	int valeur = ipsKo.get(cle);
     
        System.out.println(cle + " " + valeur);
     
    }

        return sortedHashMap;
    }
   
   
    public HashMap<String, String> CompteurUrl(ArrayList<Donnee_Pretraitement> filedata) {
     
    	HashMap<String, String> urlsMap = new HashMap<String, String>();
    	// Calculer les 10 premiï¿½res URLs
    	for(int i = 0; i < filedata.size(); i++) {
    		urlsMap.put(filedata.get(i).getUrl(), filedata.get(i).getInstant());
    	}
     
    	HashMap<String, String> sortedHashMap = new LinkedHashMap<String, String>();
    	int cpt = 0;
    	List linkedlist = new LinkedList(urlsMap.entrySet());
     
    	Collections.sort(linkedlist, new Comparator() {
          public int compare(Object o1, Object o2) {
             return ((Comparable) ((Map.Entry) (o2)).getValue())
                .compareTo(((Map.Entry) (o1)).getValue());
          }
    	});
      
    	for (Iterator it = linkedlist.iterator(); it.hasNext() && cpt < 10;cpt++) {
             Map.Entry entry = (Map.Entry) it.next();
             sortedHashMap.put((String)entry.getKey(), (String) entry.getValue());
    	}
 
 
     
      Set cles = sortedHashMap.keySet();
  Iterator it = cles.iterator();
  while (it.hasNext()) {
    String cle = (String) it.next();
    String valeur = urlsMap.get(cle);
   
    System.out.println(cle + " " + valeur);
   
  }
 
  return sortedHashMap;
    }
   
   
   
   
   
   
    public HashMap<String, String> CompteurUrlSource(ArrayList<Donnee_Pretraitement> filedata) {
      // Calculer les 10 premiï¿½res URLs sources (ï¿½URL prï¿½cï¿½dentes ï¿½)
   
    HashMap<String, String> urlsMap = new HashMap<String, String>();
        // Calculer les 10 premiï¿½res URLs
        for(int i = 0; i < filedata.size(); i++) {
       
       urlsMap.put(filedata.get(i).getUrlOrigine(), filedata.get(i).getInstant());
        }
       
        HashMap<String, String> sortedHashMap = new LinkedHashMap<String, String>();
       
        int cpt = 0;
        List linkedlist = new LinkedList(urlsMap.entrySet());
       
        Collections.sort(linkedlist, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o2)).getValue())
                  .compareTo(((Map.Entry) (o1)).getValue());
            }
       });
        for (Iterator it = linkedlist.iterator(); it.hasNext() && cpt < 10;cpt++) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put((String)entry.getKey(), (String) entry.getValue());
        }
   
   
       
        Set cles = sortedHashMap.keySet();
    Iterator it = cles.iterator();
    while (it.hasNext()) {
      String cle = (String) it.next();
      String valeur = urlsMap.get(cle);
     
      System.out.println(cle + " " + valeur);
     
    }
   
    return sortedHashMap;
    }
   
   
    public HashMap<String, Integer> CompteurIp_Os(ArrayList<Donnee_Pretraitement> filedata) {
      // Calculer le systï¿½me dï¿½exploitation
   
      HashMap<String, Integer> mapOs = new HashMap<String, Integer>();
   
    // Calculer le systï¿½me d'exploitation le plus utilisï¿½ sur le serveur web.
    for(int i = 0; i < filedata.size(); i++) {
         int nbOs = 0;
         for(int j = 0; j < filedata.size(); j++) {
         if(filedata.get(i).getOs().equals(filedata.get(j).getOs()))
        nbOs++;
         
           mapOs.put(filedata.get(i).getOs(), nbOs);
         
         }
        }
   
    List linkedlist = new LinkedList(mapOs.entrySet());
        Collections.sort(linkedlist, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                   .compareTo(((Map.Entry) (o1)).getValue());
             }
        });
        HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
       
       
       
        for (Iterator it = linkedlist.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put((String)entry.getKey(), (Integer) entry.getValue());
        }
   
   
       
        Set cles = sortedHashMap.keySet();
    Iterator it = cles.iterator();
    while (it.hasNext()){
      String cle = (String) it.next();
      int valeur = mapOs.get(cle);
     
      System.out.println(cle + " " + valeur);
     
    }
   
      return sortedHashMap;
     
    }

    public HashMap<String, Integer> CompteurIp_Nav(ArrayList<Donnee_Pretraitement> filedata) {
 // Calculer le navigateur
 
      HashMap<String, Integer> mapNav = new HashMap<String, Integer>();
     
      // Calculer le navigateur le plus utilisï¿½ sur le serveur web.
      for(int i = 0; i < filedata.size(); i++) {
         int nbNav = 0;
         for(int j = 0; j < filedata.size(); j++) {
         if(filedata.get(i).getNavigateur().equals(filedata.get(j).getNavigateur()))
        nbNav++;
         
           mapNav.put(filedata.get(i).getNavigateur(), nbNav);
         
         }
         }
     
      List linkedlist = new LinkedList(mapNav.entrySet());
         Collections.sort(linkedlist, new Comparator() {
              public int compare(Object o1, Object o2) {
                 return ((Comparable) ((Map.Entry) (o2)).getValue())
                    .compareTo(((Map.Entry) (o1)).getValue());
              }
         });
         HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
         
         
         
         for (Iterator it = linkedlist.iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                sortedHashMap.put((String)entry.getKey(), (Integer) entry.getValue());
         }
     
     
         
         Set cles = sortedHashMap.keySet();
      Iterator it = cles.iterator();
      while (it.hasNext()){
        String cle = (String) it.next();
        int valeur = mapNav.get(cle);
       
        System.out.println(cle + " " + valeur);
       
      }
     
       return sortedHashMap;
    }

   
   
   
   
    public String createStatisticsFile(Statistiques stats) {
   
    int nbIpDif = stats.getNbIpDif();
        HashMap<String, Integer> hashDixPremIpH = stats.getDixPremIpH();
        HashMap<String, Integer> hashDixPremIpKo = stats.getDixPremIpK();
        HashMap<String, String> hashDixPremUrl = stats.getDixPremUrl();
        HashMap<String, String> hashDixPremUrlSource = stats.getDixPremUrlSource();
       
       
        int totalKo = stats.getTtotalKo();
        HashMap<String, Integer> hashos = stats.getOs();
        HashMap<String, Integer> hashnavigateur = stats.getNavigateur();
       
        System.out.println("iph = " + hashDixPremIpH);
        System.out.println("nav = " + hashnavigateur);
        System.out.println("os = " + hashos);
       
       
        String contentFile = "Nombre d'adresse IP : " + nbIpDif + "\n";
        contentFile += "Le total en Ko : " + totalKo + "\n";
        contentFile += "Les 10 premières IP en nombre de hits : [";
       
        Set clesIpH = hashDixPremIpH.keySet();
        Iterator itipH = clesIpH.iterator();
       
        while (itipH.hasNext()) {
           String cle = (String) itipH.next();
           int valeur = hashDixPremIpH.get(cle);
           contentFile += cle + " (nbHits = " + valeur + "),\n ";
        }
        contentFile += "] \n";
       
contentFile += "Les 10 premières IP en nombre de Ko : [";
       
        Set clesIpKo = hashDixPremIpKo.keySet();
        Iterator itipKo = clesIpKo.iterator();
       
        while (itipKo.hasNext()) {
           String cle = (String) itipKo.next();
           int valeur = hashDixPremIpKo.get(cle);
           contentFile += cle + " (nbKo = " + valeur + "),\n ";
        }
        contentFile += "] \n";
       
        contentFile += "Les 10 premières URLs : [";
       
        Set clesUrl = hashDixPremUrl.keySet();
        Iterator itUrl = clesUrl.iterator();
       
        while (itUrl.hasNext()) {
           String cle = (String) itUrl.next();
           String valeur = hashDixPremUrl.get(cle);
           contentFile += cle + " (date = " + valeur + "),\n ";
        }
        contentFile += "] \n";
       
contentFile += "Les 10 premières URLs Sources : [";
       
        Set clesUrlSource = hashDixPremUrlSource.keySet();
        Iterator itUrlSource = clesUrlSource.iterator();
       
        while (itUrlSource.hasNext()) {
           String cle = (String) itUrlSource.next();
           String valeur = hashDixPremUrlSource.get(cle);
           contentFile += cle + " (date = " + valeur + "),\n ";
        }
        contentFile += "] \n";
       
       
       
        contentFile += "Statistiques sur le système d'exploitation : [";
       
        Set clesOs = hashos.keySet();
        Iterator itOs = clesOs.iterator();
       
        while (itOs.hasNext()) {
           String cle = (String) itOs.next();
           int valeur = hashos.get(cle);
           contentFile += cle + " (nbUtilisateur = " + valeur + "),\n ";
        }
       
       
        contentFile += "] \n";
       
        contentFile += "Statistiques sur le navigateur : [";
       
        Set clesNav = hashnavigateur.keySet();
        Iterator itNav = clesNav.iterator();
       
        while (itNav.hasNext()) {
           String cle = (String) itNav.next();
           int valeur = hashnavigateur.get(cle);
           contentFile += cle + " (nbUtilisateur = " + valeur + "),\n ";
        }
        contentFile += "] \n";
        System.out.println(contentFile);
        return contentFile;
    }
   



 
    public Calcul_Statistiques()
    {
    }
}
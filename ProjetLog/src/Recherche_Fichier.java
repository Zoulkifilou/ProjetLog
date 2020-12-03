import java.io.File;

import java.io.FilenameFilter;

public class Recherche_Fichier {
    
    
	

    public Recherche_Fichier() {
    	
      super();
    }

    public String[] Rechercher(String path,String demande) {
    	
    	// Rechercher un fichier de donn�es trait�es
    	File file = new File(path+"\\"+demande);
    	Ouverture_de_Fichiers opf = new Ouverture_de_Fichiers();
			if(file.exists()) {
    			System.out.println("Le fichier existe");
    			System.out.println(path+"\\"+demande);
    			return opf.Lecture_deFichier_Pretraitees(path+"\\"+demande);
    			
    		}
    		
    		else {
    			System.out.println("fichier non trouv�");
    			System.out.println(path+"\\"+demande);
    			return null;
    		}
			
		} 
    	
       
    
    
    public void afficher(){
    		
    }
    
    

}
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URLDecoder;

public class Main_Principal {

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {

        Gestion_de_Fichiers mf = new Gestion_de_Fichiers();
        mf.EcrireFichier("Hello les amies", "fichierTest", "/Users/IBRAHIM/Desktop");


        Configuration_de_Donnees cd = new Configuration_de_Donnees();

        //cd.create_config_file();

        //cd.save_sourceFolder();



        //cd.save_saveFolder();

        //System.out.println(cd.get_saveFolder_path());
        //System.out.println(cd.get_sourceFolder_path());

        //System.out.println(cd.display);


    	Ouverture_de_Fichiers of = new Ouverture_de_Fichiers();
         of.setLastFile("2019-12-12");
        System.out.println("last file = " + of.getLastFile());
        of.setLastFile("2020-01-25");
        System.out.println("last file = " + of.getLastFile());
        of.setLastFile("2020-01-27");
        System.out.println("last file = " + of.getLastFile());
    }
}
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Ouverture_de_Fichiers {
	
	final String path = new File("").getAbsolutePath();
    private ArrayList<Donnee_Pretraitement> TabDatas;

//    final String path = "src/config/config.xml";

    public static String display;


    public Ouverture_de_Fichiers() 
    {
        TabDatas = new ArrayList<Donnee_Pretraitement>();
    }


    public void OuvrirFichierPreTraite(String Path) {

        String line;
        try {
     
          FileReader reader = new FileReader(Path);
     
          BufferedReader br = new BufferedReader(reader);

          // read line by line      
          while ((line = br.readLine()) != null) {
            int endip = line.indexOf(";");
            String ip = line.substring(0, endip);
            int endinstant = line.indexOf(";", endip+2);
            String instant = line.substring(endip+1, endinstant);
            int endcode = line.indexOf(";", endinstant+2);
            String code = line.substring(endinstant+1, endcode);
           
            int endurlSource = line.indexOf(";", endcode+2);
            String urlSource = line.substring(endcode+1, endurlSource);  
            int endurl = line.indexOf(";", endurlSource+2);
            String url = line.substring(endurlSource+1, endurl);    
            int endos = line.indexOf(";", endurl+2);
            String os = line.substring(endurl+1, endos);
            int endnav = line.indexOf(";", endos+2);
            String nav = line.substring(endos+1,endnav);
            String ko = line.substring(endnav+1);
           
            Donnee_Pretraitement predata = new Donnee_Pretraitement(ip,url,urlSource,nav,os,ko,instant,code);
            TabDatas.add(predata);
           
            System.out.println(line);
            System.out.println("----------------------------------------------");
         
            System.out.println(ip);
            System.out.println(instant);
            System.out.println(code);
            System.out.println(urlSource);
            System.out.println(url);
            System.out.println(os);
            System.out.println(nav);
            System.out.println(ko);
            System.out.println("----------------------------------------------");
          }
        }
        catch (IOException e) {
                 System.err.format("IOException: %s%n", e);
        }
      }


    public String[] Lecture_deFichier_Pretraitees(String path) 
    {

        String line;
        try {

            FileReader reader = new FileReader(path);

            BufferedReader br = new BufferedReader(reader);
            int tailleFichier = 10000;
            String[] contenuFichier = new String[tailleFichier];
            int cpt = 0;
            // read line by line
            while ((line = br.readLine()) != null) {

                contenuFichier[cpt] = line;
                System.out.println(contenuFichier[cpt]);
                cpt++;


            }

            return contenuFichier;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        }
    }


    public void OuvrirFichier(String Path) throws IOException {
        //text file, should be opening in default text editor
        File file = new File("/Users/pankaj/source.txt");


        //first check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);

        //let's try to open PDF file
        file = new File("/Users/pankaj/java.pdf");
        if (file.exists()) desktop.open(file);
    }

    public void setLastFile(String lastFileName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Path l_path = Paths.get(path + "/config/config.xml");

        if (Files.exists(l_path)) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");


            // Get the root element
            Node data = doc.getFirstChild();

            Node lastTreatedFolder = doc.getElementsByTagName("lastFile").item(0);

            lastTreatedFolder.setTextContent(lastFileName);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path + "/config/config.xml"));
            transformer.transform(source, result);
            display = "modifications éffectuées";

        } else 
        {
        	Configuration_de_Donnees cd = new Configuration_de_Donnees();
            if (cd.create_configFile()) {
                setLastFile(lastFileName);
            } else {
                display = "erreur de sauvegarde des données";
                System.out.println("erreur de sauvegarde des données");
            }
        }
    }

    public String getLastFile() throws ParserConfigurationException, IOException, SAXException {
        String lastFileName = null;
        if (Files.exists(Paths.get(path + "/config/config.xml"))) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");
            // Get the root element
            Node data = doc.getFirstChild();

            Node lastFile = doc.getElementsByTagName("lastFile").item(0);

            lastFileName = lastFile.getTextContent();
        }
        return lastFileName;
    }
    
    public ArrayList<Donnee_Pretraitement> getTabDatas() {
        return TabDatas;
    }

    public void setTabDatas(ArrayList<Donnee_Pretraitement> tabDatas) {
        TabDatas = tabDatas;
    }


}
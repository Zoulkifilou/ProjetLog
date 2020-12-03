import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration_de_Donnees {

    final String path = new File("").getAbsolutePath();
    public static String display;
    // Constructeur
    public Configuration_de_Donnees() {}

    //ON CREER UNE FILE SELON UN SCHEMA
    public Boolean create_configFile() throws TransformerException, IOException, SAXException, ParserConfigurationException {
        boolean resultat = false;
        File f = new File(this.path + "/config");
        if (f.exists() && f.isDirectory()) {
            File fl = new File(this.path + "/config/config.xml");
            if (!fl.exists() || !fl.isFile() || !configFileValidator(this.path + "/config/config.xml")) {
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("configuration");
                    doc.appendChild(rootElement);
                    Element sourceFolder = doc.createElement("sourceFolder");
                    sourceFolder.appendChild(doc.createTextNode("---"));
                    rootElement.appendChild(sourceFolder);
                    Element saveFolder = doc.createElement("saveFolder");
                    saveFolder.appendChild(doc.createTextNode("---"));
                    rootElement.appendChild(saveFolder);
                    Element lastFolder = doc.createElement("lastFile");
                    lastFolder.appendChild(doc.createTextNode("---"));
                    rootElement.appendChild(lastFolder);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(this.path + "/config/config.xml"));
                    transformer.transform(source, result);
                    display = "Fichier généré";
                } catch (ParserConfigurationException | TransformerException pce) {
                    pce.printStackTrace();
                }
                resultat = true;
            }

        } else {
            boolean creerFolder = f.mkdir();
            create_configFile();
        }

        return resultat;
    }

    //SAUVEGARDE DU PATH DU DOSSIER SOURCE
    public void save_sourceFolder(String folder) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        Path l_path = Paths.get(path + "/config/config.xml");

        if (Files.exists(l_path)) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");

            // Get the root element
            Node data = doc.getFirstChild();

            Node sourceFolder = doc.getElementsByTagName("sourceFolder").item(0);

            sourceFolder.setTextContent(folder);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.path + "/config/config.xml"));
            transformer.transform(source, result);
            display = "modifications éffectuées";
        } else {
            if (create_configFile()) {
                save_sourceFolder(folder);
            } else {
                display = "erreur de sauvegarde des données";
                System.out.println("erreur de sauvegarde des données");
            }
        }

    }

    //SAUVEGARDE DU PATH DU DOSSIER DE SAUVEGARDE DES FICHIERS TRAITES
    public void save_saveFolder(String folder) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Path l_path = Paths.get(path + "/config/config.xml");

        if (Files.exists(l_path)) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");
            // Get the root element
            Node data = doc.getFirstChild();

            Node saveFolder = doc.getElementsByTagName("saveFolder").item(0);
            saveFolder.setTextContent(folder);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path + "/config/config.xml"));
            transformer.transform(source, result);
            display = "modifications éffectuées";

        } else {
            if (create_configFile()) {
                save_saveFolder(folder);
            } else {
                display = "erreur de sauvegarde des données";
                System.out.println("erreur de sauvegarde des données");
            }
        }
    }

    public void save_lastTreatedFolder(String folder) throws TransformerException, ParserConfigurationException, IOException, SAXException {

        Path l_path = Paths.get(path + "/config/config.xml");

        if (Files.exists(l_path)) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");


            // Get the root element
            Node data = doc.getFirstChild();

            Node lastTreatedFolder = doc.getElementsByTagName("lastFile").item(0);

            lastTreatedFolder.setTextContent(folder);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path + "/config/config.xml"));
            transformer.transform(source, result);
            display = "modifications éffectuées";

        } else {
            if (create_configFile()) {
                save_lastTreatedFolder(folder);
            } else {
                display = "erreur de sauvegarde des données";
                System.out.println("erreur de sauvegarde des données");
            }
        }
    }

    // RECUP DU PATH DE LA FOLDER OU SONT STOCKES LES LES FICHIERS PRE TRAITES ( contenus entre les tags )
    public String get_sourceFolder_path() throws IOException, SAXException, ParserConfigurationException {
        String sourceFolderPath = null;
        if (Files.exists(Paths.get(path + "/config/config.xml"))) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");

            // Get the root element
            Node data = doc.getFirstChild();

            Node saveFolder = doc.getElementsByTagName("sourceFolder").item(0);

            sourceFolderPath = saveFolder.getTextContent();

        }
        return sourceFolderPath;
    }

    // RECUP DU PATH DE LA FOLDER DE SAUVEGARDE DES FICHIERS TRAITES ( contenus entre les tags )
    public String get_saveFolder_path() throws IOException, SAXException, ParserConfigurationException {
        String saveFolderPath = null;
        if (Files.exists(Paths.get(path + "/config/config.xml"))) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");

            // Get the root element
            Node data = doc.getFirstChild();

            Node saveFolder = doc.getElementsByTagName("saveFolder").item(0);

            saveFolderPath = saveFolder.getTextContent();
        }
        return saveFolderPath;
    }

    // RECUP DU PATH DE LA DERNIERE FOLDER TRAITEE
    public String get_lastTreatedFolder_path() throws IOException, SAXException, ParserConfigurationException {
        String lastTreatedFolderPath = null;
        if (Files.exists(Paths.get(path + "/config/config.xml"))) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path + "/config/config.xml");

            // Get the root element
            Node data = doc.getFirstChild();

            Node lastTreatedFolder = doc.getElementsByTagName("lastFile").item(0);

            lastTreatedFolderPath = lastTreatedFolder.getTextContent();

        }
        return lastTreatedFolderPath;
    }

    //VERIFICATION DE LA VALIDITEE DU FICHIER XML ( selon le schéma de base )
    public boolean configFileValidator(String path) throws ParserConfigurationException, IOException, SAXException
{
        Boolean validator = false;
        if (Files.exists(Paths.get(path))) 
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);

            if (doc.getElementsByTagName("saveFolder") != null && doc.getElementsByTagName("saveFolder") != null && doc.getElementsByTagName("lastFile") != null) 
            {
                validator = true;
            } 
            else 
            {
                validator = false;
            }
        }
        return validator;
   }
}
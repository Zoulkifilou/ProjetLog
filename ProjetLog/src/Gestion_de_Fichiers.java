import java.io.*;

public class Gestion_de_Fichiers 
{
    public void EcrireFichier(String fileContent, String name ,String path) throws IOException
    {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + name + ".txt"));
        writer.write(fileContent);
        writer.close();
    }
}
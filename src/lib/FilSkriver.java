package lib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serialiserer alle registre på toppnivå.
 */
public class FilSkriver implements Serializable {

    private String fil;
    private FileOutputStream fos;
    private ObjectOutputStream out;

    public FilSkriver(String fil) {
        this.fil = fil;
    }

    /**
     * Serialiserer valgt objekt til filbane valgt i kontruktøren.
     * @param o Generelt objekt.
     */
    public void skrivTilFil(Object o) {
        try {
            fos = new FileOutputStream(fil);    
            out = new ObjectOutputStream(fos);
            out.writeObject(o);
            out.close();
        } catch(IOException e){
            System.out.println(e.fillInStackTrace());
        } 
    }
}

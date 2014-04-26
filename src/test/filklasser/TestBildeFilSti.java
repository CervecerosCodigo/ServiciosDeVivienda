package test.filklasser;

import java.io.File;
import lib.BildeFilSti;
import lib.Konstanter;
import lib.Melding;
import model.Bolig;

/**
 *
 * File: TestBildeFilSti.java Package: test.filklasser Project:
 * ServiciosDeVivienda Apr 22, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class TestBildeFilSti {

    Bolig testBolig;
    BildeFilSti bildefilsti;

    public TestBildeFilSti(Bolig testBolig) {
        this.testBolig = testBolig;
        bildefilsti = new BildeFilSti();

        String b = bildefilsti.getBoligGalleryPath(testBolig);
        Melding.visMelding(Konstanter.BILDEGALLERIER, b);
    }

    public TestBildeFilSti() {
    BildeFilSti filsti = new BildeFilSti();
    File nymappe = new File(Konstanter.BILDEGALLERIER+"/"+"1234");
    nymappe.mkdirs();
    
    }
    
    

}

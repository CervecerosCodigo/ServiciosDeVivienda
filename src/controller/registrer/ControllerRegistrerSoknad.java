package controller.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import model.Annonse;
import model.Person;
import model.Soknad;
import view.registrer.SoknadRegVindu;


/**
 * Kontroller for registrering av søknad til megler. 
 * Boligsøker blir forespurt om å bekrefte at han/hun godtar utleievilkårene.
 * Om de godtas kan boligsøker registrere personalia og søknad. 
 * Personobjektet og annonseobjektet blir så sendt med til ControllerRegPerson der
 * person og søknad blir registrert.
 * Leietakerobjektet blir ikke lagt i personregisteret før megler godkjenner søknaden.
 * @author espen
 */
public class ControllerRegistrerSoknad {

    private HashSet<Person> personliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadliste;
    
    private SoknadRegVindu vindu;
    private Annonse annonse;
    
    public ControllerRegistrerSoknad(HashSet<Person> personliste, HashSet<Annonse> annonseliste, HashSet<Soknad> soknadliste, Annonse annonse){
    
        this.annonse = annonse;
        this.annonseliste = annonseliste;
        this.soknadliste = soknadliste;
        this.personliste = personliste;
        
        vindu = new SoknadRegVindu(350, 250, "Aksepter vilkårene");
        vindu.addSoknadListener( new KnappeLytter());
        
        visEiersKrav();
        
    }
    
    private void visEiersKrav(){
        String krav = annonse.getEiersKrav();
        String replace = krav.replace("<br>", "\n");
            vindu.setEiersKrav(replace);
        
    }
    
    class KnappeLytter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(vindu.getAksepter())){
                new ControllerRegistrerPerson(personliste, annonseliste, soknadliste, annonse);
                vindu.dispose();
            }
            if(e.getSource().equals(vindu.getAvbryt())){
                vindu.dispose();
            }
        }
        
    }
}

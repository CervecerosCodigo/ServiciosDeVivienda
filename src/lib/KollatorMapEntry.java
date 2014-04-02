package lib;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * File: KollatorMapEntry.java Package: lib Project: ServiciosDeVivienda Apr 2, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class KollatorMapEntry implements Comparator<Map.Entry<String, Integer>>{

    private RuleBasedCollator kollatorNO;

    public KollatorMapEntry() {
        //FIXME: Etter at vi har lagd struktur for dette bør vi kaste exceptions høgre opp i strukturen. Catchen skal derfor ikke foretas her.
        try {
            kollatorNO = new RuleBasedCollator(Konstanter.KOLLATOR_REKKEFOLGE);
        } catch (ParseException ex) {
            Logger.getLogger(KollatorMapEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        String str1 = o1.getKey();
        String str2 = o2.getKey();
        int d = kollatorNO.compare(str1, str2);
        if(d!=0){
            return d;
        }
        return -1;
    }
}

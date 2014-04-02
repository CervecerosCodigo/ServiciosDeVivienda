package register;

import java.util.Map;
import java.util.TreeMap;

/**
 * Klassen holder oversikt over antall boliger tilgjengelige for utleie for et
 * spesifikt possted. Kun poststeder med eksisterende boliger finnes med. File:
 * Postregister.java Package: register Project: ServiciosDeVivienda Apr 1, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class Postregister {

    protected Map<String, Integer> poststeder_med_bolig;

    /**
     * Initialiserer ny tom postregister med boliger.
     */
    public Postregister() {
//        poststeder_med_bolig = new HashMap<>();
        poststeder_med_bolig = new TreeMap<>();
    }

    /**
     * Legger til en ny bolig til poststedet. Dersom boligen ikke er med fra
     * tidligere vil den legge til poststedet i mengden med gitt antall boliger
     * på postedet. Hvis det eksisterer boliger på poststedet fra tidligere vil
     * det bli oppdatert antall tilgjengelige boliger for utleie vil bli summert
     * med antall boliger gitt som parameter.
     *
     * @param poststed String med postnavn
     * @param antall int med antall boliger som finnes tilgjengelige på
     * poststedet.
     */
    public void leggTill(String poststed, int antall) {
        if (poststeder_med_bolig.containsKey(poststed)) {
            int antall_eksisterende = poststeder_med_bolig.get(poststed);
            poststeder_med_bolig.put(poststed, antall + antall_eksisterende);
        } else {
            poststeder_med_bolig.put(poststed, antall);
        }
    }

    /**
     * Returnerer antall boliger tilgjengelig på en postort.
     *
     * @param poststed String med navn for poststedet. Case sensitive.
     * @return int Antall boliger.
     */
    public int getAntallFraPoststed(String poststed) {
        if (poststeder_med_bolig.containsKey(poststed)) {
            return poststeder_med_bolig.get(poststed);
        } else {
            return 0;
        }
    }

    /**
     * Sletter angitt antall boliger fra merkedet på er spesifikt poststed.
     * <br></br>Returverdier:
     * <ul>
     * <li>Etter vellyket sletting returneres positivt tall med antall boliger
     * igjen på poststedet.</li>
     * <li>Returverdi på 0 medfører at det er ingen boliger igjenn etter
     * sletting og poststeded blir slettet også.</li>
     * <li>Negativt returverdi i form av -1 indikerer at ingen postestedet ikke
     * finnes eller at man har forsøkt å slette flere boliger på et sted enn det
     * er utlyst til utleie.</li>
     * </ul>
     *
     * @param poststed String
     * @param antall int
     * @return int
     * @throws NullPointerException
     */
    public int slettBoligerFraPoststed(String poststed, int antall) {
        if (!poststeder_med_bolig.containsKey(poststed)) {
            return -1;
        } else if (getAntallFraPoststed(poststed) > antall) {
            poststeder_med_bolig.put(poststed, antall);
            return getAntallFraPoststed(poststed);
        } else if (getAntallFraPoststed(poststed) == antall) {
            poststeder_med_bolig.remove(poststed);
            return 0;
        }
        return -1;
    }

    /**
     * Returnerer totalt antal postster med bolig til utleie.
     *
     * @return int
     */
    public int antallPoststeder() {
        return poststeder_med_bolig.size();
    }

    /**
     * Returnerer en streng med oversikt over alle poststeder og antall boliger
     * til utleie.
     *
     * @return String
     */
    public String getStatistikk() {
        StringBuilder retur = new StringBuilder();
        for (String poststed : poststeder_med_bolig.keySet()) {
            retur.append(poststed + "\t" + poststeder_med_bolig.get(poststed).toString() + "\n");
        }
        return retur.toString();
    }
}

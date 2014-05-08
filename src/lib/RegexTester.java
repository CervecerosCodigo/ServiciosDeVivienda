package lib;

import java.util.regex.PatternSyntaxException;

/**
 * Klassen tester for vanligste reguljøre uttryck tværs over programmet. File:
 * RegexTester.java Package: model Project: ServiciosDeVivienda Mar 31, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class RegexTester {

    public static final String NAVN_PATTERN = "^[A-ZÆØÅ]{1}[a-zæøå]{1,20}$";
    public static final String EPOST_PATTERN = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$";
    public static final String GATE_NAVN_PATTERN = "^[A-ZÆØÅ]{1}[a-zæøå]{1,20}[\\s]?[A-ZÆØÅ]?[a-zæøå]*[\\s]?[A-ZÆØÅ]?[a-zæøå]*$";
    public static final String GATE_NR_PATTERN = "^[1-9]{1}[0-9]{0,2}$";
    /**
     * Denne pattern tar for seg en gateadresse som begynner med stor bokstav og
     * kan bestå av opp til tre ord, deretter må det komme et nummer og
     * frivillig kan det komme en stor bokstav for som markerer
     * trappeoppgangen.<br>
     * <u>Dette er foretrukket måte for å kontrollere riktig gateadresse.</u>
     */
    public static final String GATE_ADRESSE = "^[A-ZÆØÅ]{1}[a-zæøå]{1,20}[\\s]?[A-ZÆØÅ]?[a-zæøå]*[\\s]?[A-ZÆØÅ]?[a-zæøå]*[\\s][1-9]{1}[0-9]{0,2}?[\\s]?[A-ZÆØÅ]{0,1}$";
    public static final String POST_NUMMER_PATTERN = "^[0-9]{4}$";
    public static final String POSTORT_NAVN = "^[A-ZÆØÅ]{1}[a-zæøå]{1,20}$";
    public static final String TEL_NUMMER_NORSK = "^[1-9]{1}[0-9]{7}$";
    public static final String PERSONNUMMER = "^[0-9]{11}$";
    public static final String YEAR = "^[1|2]{1}[0-9]{3}$";
    public static final String MONTH_NUMMER = "^([0]{1}[1-9]{1})|([1]{1}[0-2]{1})$";
    public static final String DAY_OF_MONTH_NUMMER = "^([0|1|2]{1}[0-9]{1})|([3]{1}[0|1]{1})$";
    public static final String KVM_BOLIG = "^[1-9]{1}[0-9]{0,3}$";
    public static final String KVM_TOMT = "^[1-9]{1}[0-9]{0,4}$";
    public static final String ETASJE = "^[1-9]{1}[0-9]{0,1}$";
    public static final String PRIS = "^[1-9]{1}[0-9]{0,5}$";
    /**
     * Dette er ID variabel som kan brukes for å teste på alle ID nummer som for
     * person, bolig mm.
     */
    public static final String ID = "^[1-4]{1}[0-9]{4}$";
    /**
     * Tester at bruker har skrevet inn kun bokstaver. Valgfritt antall
     * mellomrom er tillatt og blandning av store og små bokstaver.
     */
    public static final String KUN_BOKSTAVER = "^[a-zæøåA-ZÆØÅ\\s]+$";
    /**
     * Valgfri rekkefølge store eller små bokstaver, tall og mellomrom.
     */
    public static final String KUN_BOKSTAVER_ELLER_TALL = "^[a-zæøåA-ZÆØÅ0-9\\s]+$";
    /**
     * Samme som KUN_BOKSTAVER_ELLER_TALL men tar også med seg bindestrek slik
     * at vi kan skrive så coole ord som IT-konsulent eller Rock-stjerne.
     */
    public static final String KUN_BOKSTAVER_TALL_BINDESTREK = "^[a-zæøåA-ZÆØÅ0-9\\-\\s]+$";
    /**
     * Denne er til for at man skal ha mulighet til å skrive navn med bindestrek
     * så lenge første bokstanven er stor. Da kan man skrive feks Bert-Ola,
     * Daniel-Joakim, Jonas-Mohammed osv
     */
    public static final String KUN_BOKSTAVER_FSTORBOKSTAV = "^[A-ZÆØÅ]{1}[a-zæøåA-ZÆØÅ\\-\\s]+$";
    private static boolean erTestOK;

    public RegexTester() {
    }

    /**
     * Tester dersom et navn innholder kun alfanumeriske tegn. Brukes kun på
     * for- eller etternavn for seg ettersom denne atr ikke høyde for
     * whitespaces. Navnet må begynne med stor bokstav.
     *
     * @param navn String
     * @return boolean
     */
    public static boolean testNavn(String navn) {
        return patternMatchOK(navn, NAVN_PATTERN);
    }

    /**
     * Kontrollerer dersom utryket er formatert som epost.
     *
     * @param epost String
     * @return boolean
     */
    public static boolean testEpost(String epost) {
        return patternMatchOK(epost, EPOST_PATTERN);
    }

    /**
     * Tester norsk gatenavn. Et gatenavn kan bestå av 1 til 3 ord separert med
     * blank tegn. Alle ord må begynne med stor bokstav.
     *
     * @param gatenavn String
     * @return boolean
     */
    public static boolean testGateNavn(String gatenavn) {
        return patternMatchOK(gatenavn, GATE_NAVN_PATTERN);
    }

    /**
     * Tester dersom vi har et gatenummer. Et gatenummer får ikke starte på null
     * og kan bestå opp til tre tall.
     *
     * @param gatenr String
     * @return boolean
     */
    public static boolean testGateNr(String gatenr) {
        return patternMatchOK(gatenr, GATE_NR_PATTERN);
    }

    /**
     * Tester for at postnummer består av fire tall.
     *
     * @param postnr String
     * @return boolean
     */
    public static boolean testPostNummer(String postnr) {
        return patternMatchOK(postnr, POST_NUMMER_PATTERN);
    }

    /**
     * Tester på navn for en postort. Må begynne på stor bokstav.
     *
     * @param postortnavn String
     * @return boolean
     */
    public static boolean testPostOrtNavn(String postortnavn) {
        return patternMatchOK(postortnavn, POSTORT_NAVN);
    }

    /**
     * Tester for 8-sifferig telefonnummer som ikke får begynne med null,
     *
     * @param telnr String
     * @return boolean
     */
    public static boolean testTelNummerNorsk(String telnr) {
        return patternMatchOK(telnr, TEL_NUMMER_NORSK);
    }

    /**
     * Tester for er 11-sifferig personnummer uten bindestrekk.
     *
     * @param pnum String
     * @return boolean
     */
    public static boolean testPersonNummer(String pnum) {
        return patternMatchOK(pnum, PERSONNUMMER);
    }

    /**
     * Tester for et fire sifferig år som kan begynne med 1 eller 2. Utgår fra
     * at det ikke finnes boliger til utleie som er bygget før år 1000.
     *
     * @param year String
     * @return boolean
     */
    public static boolean testYearNummer(String year) {
        return patternMatchOK(year, YEAR);
    }

    /**
     * Tester dersom måned begynner på nummer fra 0 eller 1. Dersom denne
     * begynner på 1 kan etterfølgende tall være mellom 0 og 2.
     *
     * @param month String
     * @return boolean
     */
    public static boolean testMonthNummer(String month) {
        return patternMatchOK(month, MONTH_NUMMER);
    }

    /**
     * Tester dersom en dag begynner med 0,1,2 eller 3. Dersom måneden begynner
     * med 3 kan etterfølgende tall være mellom 0 og 1.
     *
     * @param day String
     * @return boolean
     */
    public static boolean testDayNummer(String day) {
        return patternMatchOK(day, DAY_OF_MONTH_NUMMER);
    }

    /**
     * Tester kvadratmeter for boligen, får ikke begynne på 0 og kan ikke
     * overstige 999 kvm.
     *
     * @param kvm String
     * @return boolean
     */
    public static boolean testKVMbolig(String kvm) {
        return patternMatchOK(kvm, KVM_BOLIG);
    }

    /**
     * Tester på antall kvadratmeter for tomten. Får ikke begynne på 0 og kan
     * ikke overgå 9999 kvm.
     *
     * @param kvm String
     * @return boolean
     */
    public static boolean testKVMtomt(String kvm) {
        return patternMatchOK(kvm, KVM_TOMT);
    }

    /**
     * Tester på etasjenummer. Et etasjenummer skal bestå av min et tall og
     * maksimum av to tall og får ikke begynne på null.
     *
     * @param etasje String
     * @return boolean
     */
    public static boolean testEtasje(String etasje) {
        return patternMatchOK(etasje, ETASJE);
    }

    /**
     * Tester på pris, får ikke begynne på null og kan være av et til seks tall.
     *
     * @param pris String
     * @return boolean
     */
    public static boolean testPris(String pris) {
        return patternMatchOK(pris, PRIS);
    }

    /**
     * Tester dersom skrevet inn ID er riktig. Kan brukes på alle ID nummere som
     * er brukt i programmet. Som for person, bolig annonse mm.
     *
     * @param IDinn
     * @return boolean
     */
    public static boolean testID(String IDinn) {
        return patternMatchOK(IDinn, ID);
    }

    /**
     * Tester for en gateadresse som begynner med stor bokstav og kan bestå av
     * opp til tre ord, deretter må det komme et nummer og frivillig kan det
     * komme en stor bokstav for som markerer trappeoppgangen.<br>
     * <u>Dette er foretrukket måte for å kontrollere riktig gateadresse.</u>
     *
     * @param gateAdresse String
     * @return boolean
     */
    public static boolean testGateadresse(String gateAdresse) {
        return patternMatchOK(gateAdresse, GATE_ADRESSE);
    }

    /**
     *
     * @param kunBokstaver
     * @return
     */
    public static boolean testKunBokstaver(String kunBokstaver) {
        return patternMatchOK(kunBokstaver, KUN_BOKSTAVER);
    }

    /**
     * Valgfri rekkefølge store eller små bokstaver, tall og mellomrom.
     *
     * @param kunBokstaverEllerTall
     * @return
     */
    public static boolean testKunBokstaverEllerTall(String kunBokstaverEllerTall) {
        return patternMatchOK(kunBokstaverEllerTall, KUN_BOKSTAVER_ELLER_TALL);
    }

    /**
     * Samme som KUN_BOKSTAVER_ELLER_TALL men tar også med seg bindestrek slik
     * at vi kan skrive så coole ord som IT-konsulent eller Rock-stjerne.
     *
     * @param kunBokstaverTallBindestrek
     * @return
     */
    public static boolean testKunBokstaverTallBindestrek(String kunBokstaverTallBindestrek) {
        return patternMatchOK(kunBokstaverTallBindestrek, KUN_BOKSTAVER_TALL_BINDESTREK);
    }
    
    /**
     * Denne er til for at man skal ha mulighet til å skrive navn med bindestrek
     * så lenge første bokstanven er stor. Da kan man skrive feks Bert-Ola,
     * Daniel-Joakim, Jonas-Mohammed osv
     * 
     * @param kunBokstaverTallBindestrekFStorbokstav
     * @return 
     */
    public static boolean testKunBokstaverBindestrekFStorbokstav(String kunBokstaverTallBindestrekFStorbokstav) {
        return patternMatchOK(kunBokstaverTallBindestrekFStorbokstav, KUN_BOKSTAVER_FSTORBOKSTAV);
    }

    /**
     * Tester en input string mot en fordefiniert regex.
     *
     * @param input String med input fra bruker
     * @param regex Reguljært uttryk gitt og fordefiniert fra en av metodene i
     * klassen.
     * @return boolean
     */
    private static boolean patternMatchOK(String input, String regex) {
        try {
            erTestOK = input.matches(regex);
        } catch (PatternSyntaxException e) {
            System.out.println("Regex xception: input = " + input + " regex = " + regex);
        }
        return erTestOK;
    }

}

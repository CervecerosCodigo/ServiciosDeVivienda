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

package model;

import java.util.*;

/** 
 *  En samleklasse for hele registret av generisk type. Her skal det samles alle hashset av underliggende klasser i pakken.
 * @param <E>
 */
public class Register<E> {

    private Collection<E> kunde;
    private Collection<E> bolig;
    private Collection<E> annonse;
    private Collection<E> kontrakt;
    private Collection<E> soknad;
    private Collection<E> post;

    public Register() {
        kunde = new HashSet<>();
        bolig = new HashSet<>();
        annonse = new HashSet<>();
        kontrakt = new HashSet<>();
        soknad = new HashSet<>();   //Bør sorteres i forhold til "siste først" LinkedHashSet?
        post = new HashSet<>(); //Bør sorteres alfabetisk på Poststed. TreeSet?

    }

    /**
     * Alle metodene nedenfor tar i mot et eller annet objekt av type E. 
     * Det er laget en hjelpemetode "finnklasseVedRefleksjon" som returnerer en
     * peker til rett datasett som objektet e skal slegges inn/slettes fra.
     * @param e
     * @return 
     */
    public boolean leggTilObjekt(E e) {
        Collection<E> set = finnKlasseVedRefleksjon(e);
        if (set != null)
            return set.add(e);
        return false;
    }

    public boolean finnesObjektet(E e) {

        Collection<E> set = finnKlasseVedRefleksjon(e);
        if (set != null)
            return set.contains(e);
        return false;
    }

    public boolean slettObjekt(E e) {

        Collection<E> set = finnKlasseVedRefleksjon(e);
        if (set != null)        
            return set.remove(e);
        return false;
    }
    
    public boolean erRegisterTomt(E e){
        Collection<E> set = finnKlasseVedRefleksjon(e);
        if(set != null)
            return set.isEmpty();
        return false;
    }

    public int strAvRegister(E e) {
        Collection<E> set = finnKlasseVedRefleksjon(e);
        if (set != null)
            return set.size();
        return -1;
    }

    /**
     * Privat hjelpemetode klassens metoder.
     * Tar i mot et eller annet objekt. Finner så ut klassenavnet og returnerer et
     * pekerobjekt til det settet det innkommende objektet hører til.
     */
    private Collection<E> finnKlasseVedRefleksjon(E e) {
        Collection<E> retur = new HashSet<>();
        switch (e.getClass().getSimpleName()) {
            case "Kunde":
                return retur = kunde;
            case "Bolig":
                return retur = bolig;
            case "Annonse":
                return retur = annonse;
            case "Kontrakt":
                return retur = kontrakt;
            case "Sak":
                return retur = soknad;
            case "Post":
                return retur = post;
        }
        return null;
    }

}
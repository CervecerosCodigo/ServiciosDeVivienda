//package model;
//
//import java.text.ParseException;
//import java.text.RuleBasedCollator;
//import java.util.*;
//import javax.swing.JOptionPane;
//
///**
// * En samleklasse for hele registret av generisk type. Her skal det samles alle
// * hashset av underliggende klasser i pakken.
// *
// * @param <E>
// */
//public class Register<E> {
//
//    private Collection<E> person;
//    private Collection<E> bolig;
//    private Collection<E> annonse;
//    private Collection<E> kontrakt;
//    private Collection<E> soknad;
//    private Collection<E> post;
//    private Comparator komp = new Postsammenligner();
//
//    public Register() {
//        person = new HashSet<>();
//        bolig = new HashSet<>();
//        annonse = new HashSet<>();
//        kontrakt = new HashSet<>();
//        soknad = new LinkedHashSet<>();
//        post = new TreeSet<>(komp);
//
//    }
//
//    /**
//     * Alle metodene nedenfor tar i mot et eller annet objekt av type E. Det er
//     * laget en hjelpemetode "finnklasseVedRefleksjon" som returnerer en peker
//     * til rett datasett som objektet e skal slegges inn/slettes fra.
//     *
//     * @param e
//     * @return
//     */
//    public boolean leggTilObjekt(E e) {
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        if (set != null) {
//            return set.add(e);
//        }
//        return false;
//    }
//
//    public boolean finnesObjektet(E e) {
//        
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        if (set != null) {
//            return set.contains(e);
//        }
//        return false;
//    }
//
//    public boolean slettObjekt(E e) {
//
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        if (set != null) {
//            return set.remove(e);
//        }
//        return false;
//    }
//
//    public boolean erRegisterTomt(E e) {
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        if (set != null) {
//            return set.isEmpty();
//        }
//        return false;
//    }
//
//    public int strAvRegister(E e) {
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        if (set != null) {
//            return set.size();
//        }
//        return -1;
//    }
//
//    public String visRegister(E e) {
//
//        String info = "";
//        Collection<E> set = finnKlasseVedRefleksjon(e);
//        Iterator<E> iter = set.iterator();
//        if (set != null) {
//            while (iter.hasNext()) {
//                info += iter.next().toString() +"\n";
//            }
//        }
//        return info;
//    }
//
//    /**
//     * Privat hjelpemetode klassens metoder. Tar i mot et eller annet objekt.
//     * Finner så ut klassenavnet og returnerer et pekerobjekt til det settet det
//     * innkommende objektet hører til.
//     */
//    private Collection<E> finnKlasseVedRefleksjon(E e) {
//        Collection<E> retur = new HashSet<>();
//        switch (e.getClass().getSimpleName()) {
//            case "Person":
//            case "Utleier":
//            case "Leietaker":
//            case "Megler":
//                return retur = person;
//            case "Bolig":
//            case "Leilighet":
//            case "Enebolig":
//                return retur = bolig;
//            case "Annonse":
//                return retur = annonse;
//            case "Kontrakt":
//                return retur = kontrakt;
//            case "Soknad":
//                return retur = soknad;
//            case "Post":
//                return retur = post;
//        }
//        return null;
//    }
//
//    class Postsammenligner implements Comparator<Post> {
//
//        private String rekkefølge = "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j"
//            + "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t<U,u<V,v<W,w<X,x<Y,y<Z,"
//            + "z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";
//
//        private RuleBasedCollator kollator;
//
//        public Postsammenligner() {
//            try {
//                kollator = new RuleBasedCollator(rekkefølge);
//            } catch (ParseException pe) {
//                JOptionPane.showMessageDialog(null, "Feil i rekkefølgestreng for kollator!");
//            }
//        }
//
//        @Override
//        public int compare(Post o1, Post o2) {
//            String post1 = o1.getPostSted();
//            String post2 = o2.getPostSted();
//            int d = kollator.compare(post1, post2);
//            if( d!= 0)
//                return d;
//            else
//                return kollator.compare(post1, post2);
//        }
//
//    }
//}

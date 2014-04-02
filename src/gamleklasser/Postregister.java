//package gamleklasser;
//
////Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.
//
//import model.Post;
//import java.text.*;
//import java.util.*;
//import javax.swing.*;
//
///**
// * Postregister som arver Register, dens metoder og datasett.
// * Kjører egen Comparator her, for å sortere etter Poststed.
// * @author espen
// */
//
//public class Postregister extends Register<Post>{
//
//    protected TreeSet<Post> postRegister;
//    private Comparator komp;
//    
//    public Postregister(){
//
//        komp  = new Postsammenligner();
//        postRegister = new TreeSet<>(komp);
//        super.collection = postRegister;
//        Post ny = new Post("Oslo", 1);
//    }
//    
//    
//    class Postsammenligner implements Comparator<Post>{
//
//        private String rekkefolge = "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j"
//            + "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t<U,u<V,v<W,w<X,x<Y,y<Z,"
//            + "z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";
//
//        private RuleBasedCollator kollator;
//
//        public Postsammenligner() {
//            try {
//                kollator = new RuleBasedCollator(rekkefolge);
//            } catch (ParseException pe) {
//                JOptionPane.showMessageDialog(null, "Feil i rekkefølgestreng for kollator!");
//            }
//        }
//
//        @Override
//        public int compare(Post o1, Post o2) {
//            
//            String post1 = o1.getPostSted();
//            String post2 = o2.getPostSted();
//            int d = kollator.compare(post1, post2);
//            if( d!= 0)
//                return d;
//            else
//                return kollator.compare(post1, post2);
//        }
//
//
//    }
//
//}

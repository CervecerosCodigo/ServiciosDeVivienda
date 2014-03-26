package model;

import java.util.Calendar;

public abstract class Bolig {

  private int boligID;

  /** 
   *  Detter er nummer referanse til ID for eieren av boligen.
   */
  private int personID;
  /* {deprecated=false}*/

  private int meglerID;

  private String boligtype;

  private String adresse;

  private String postnummer;

  private String poststed;

  private int boAreal;

  private int byggeAr;

  private String beskrivelse;

  private boolean erUtleid;

  private Calendar tilgjengeligForUtleie;

  /** 
   *  Det er tenkt at denne variabeln skal vise til mappen i hvilken bilder til annonsen er lagret.
   */
  private String pathBildemappe;

}
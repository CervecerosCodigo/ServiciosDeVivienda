package model;

/** 
 *  Kontrakt klassen er ganske spesiell ettersom denne skal bavare historikken over alle annonser, leietakere, utleier og magler som var med da kontraktet skrevs. I denne klassen skal vi derfor ta kopi av alle disse objekter for fremtidig lagring. Dette blir egentlig dobbellagring men det er nødvendig ettersom vi må se på dette som en "bevaring for fremtiden".
 */
public class Kontrakt {

  private Annonse annonse;

  private Megler megler;

  private int kontraktID;

}
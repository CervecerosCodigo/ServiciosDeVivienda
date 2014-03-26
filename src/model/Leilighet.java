package model;

public class Leilighet extends Bolig {

  private int etasjeNr;

  /** 
   *  hvis 0 == ingen balkong
   */
  private int balkongAreal;

  /** 
   *  hvis 0 == ingen bod
   */
  private int bodAreal;

  private boolean harHeis;

  private boolean harGarsje;

  private boolean harFellesvaskeri;

}
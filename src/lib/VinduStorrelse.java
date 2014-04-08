
package lib;

/**
 * En enum type innholder konstanter med de vindustørrelser som brukes i programmet.
 * File: VinduStorrelse.java
 * Package: lib
 * Project: ServiciosDeVivienda
 * Apr 8, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public enum VinduStorrelse {

    STOR (1200, 700),
    MIDDEL (800, 600), 
    LITEN (400,300);
    
    private final int WIDTH;
    private final int HEIGHT;

    private VinduStorrelse(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }
    
    /**
     * Returnerer bredden på gitt vindutype.
     * @return int
     */
    public int getWIDTH(){
        return WIDTH;
    }
    
    /**
     * Returnerer høyden på gitt vindutype.
     * @return int
     */
    public int getHEIGHT(){
        return HEIGHT;
    }
    
}

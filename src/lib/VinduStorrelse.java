
package lib;

/**
 * En enum type innholder konstanter med de vindustørrelser som brukes i programmet.
 */
public enum VinduStorrelse {

    STOR (730, 1200),
    MIDDEL (600, 800), 
    LITEN (300,400),
    TOPPANEL (150,0),
    BUNNPANEL (30,0),
    VENSTREPANEL (0,400),
    SENTERPANEL (0,0);
    
    private final int WIDTH;
    private final int HEIGHT;

    private VinduStorrelse(int HEIGHT, int WIDTH) {
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

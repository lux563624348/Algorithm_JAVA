package rushhour;   
/**  
 * Copyright Jari Kuusisto 2008  
 * Rushhour game solver v1.0  
 */   
/**  
 * User move  
 */   
public class Move {   
    private int v;   
    private char move;   
    private int length;   
       
    public Move(int v, char move) {   
        this.v = v;   
        this.move = move;   
        this.length = 1;   
    }   
       
    public Move(int v, char move, int length) {   
        this.v = v;   
        this.move = move;   
        this.length = length;   
    }   
       
    public int getVehicle() {   
        return this.v;   
    }   
       
    public char getDirection() {   
        return this.move;   
    }   
       
    public int getLength() {   
        return this.length;   
    }   
       
    public String printStringMove() {   
        return "Move vehicle "+(v==0?'R':((char)(64+this.v)))+" to "+this.move+""+this.length+".";   
    }   
    public String toString() {   
        return printStringMove();   
    }   
}   

 

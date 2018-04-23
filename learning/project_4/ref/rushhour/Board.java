package rushhour;   
/**  
 * Copyright Jari Kuusisto 2008  
 * Rushhour game solver v1.0  
 */   
import java.util.Vector;   
/**  
 * The game board  
 */   
public class Board implements Cloneable {   
     Vector<Vehicle> vehicles;   
    private boolean[][] occupied;   
    private int outX, outY;   
       
    public Board() {   
        this.vehicles = new Vector<Vehicle>();   
        this.outX = 2;   
        this.outY = 4;   
        this.occupied = new boolean[RushHour.BOARD_SIZE][RushHour.BOARD_SIZE];   
        for(int i=0; i<RushHour.BOARD_SIZE; i++) {   
            for(int j=0; j<RushHour.BOARD_SIZE; j++) {   
                this.occupied[i][j] = false;   
            }   
        }                     
    }   
   
    public void printBinaryBoard() {   
        int i,j;   
        System.out.print("\n");   
        for(i=0; i<RushHour.BOARD_SIZE; i++) {   
            for(j=0; j<RushHour.BOARD_SIZE; j++) {   
                if(this.occupied[i][j] == true)   
                    System.out.print("1");   
                else   
                    System.out.print("0");   
            }   
            System.out.print("\n");   
        }   
        System.out.print("\n");   
    }   
   
    public String toString() {   
    StringBuffer sb=new StringBuffer();   
        int i, j;   
        int cars = this.vehicles.size();   
        char[] id = new char[cars];   
        for(i=0; i<this.vehicles.size(); i++) {   
            if(this.vehicles.get(i).isRed())   
                id[i] = 'R';   
            else   
                id[i] = (char) (64+i);   
        }   
        char[][] game = new char[RushHour.BOARD_SIZE][RushHour.BOARD_SIZE];   
        for(i=0; i<RushHour.BOARD_SIZE; i++) {   
            for(j=0; j<RushHour.BOARD_SIZE; j++) {   
                game[i][j] = '*';   
            }   
        }   
        for(int k=0; k<cars; k++) {   
            Vehicle v = this.vehicles.get(k);   
            if(v.isRed()) {   
                for(i=0; i<v.getLength(); i++) {   
                    game[v.getX()][v.getY()+i] = 'R';   
                }   
            } else if(v.getDirection()) {   
                for(i=0; i<v.getLength(); i++) {   
                    game[v.getX()][v.getY()+i] = id[k];   
                }   
            } else if(!v.getDirection()) {   
                for(i=0; i<v.getLength(); i++) {   
                    game[v.getX()+i][v.getY()] = id[k];   
                }   
            }   
        }   
        sb.append("\n");   
        for(i=0; i<RushHour.BOARD_SIZE; i++) {   
            for(j=0; j<RushHour.BOARD_SIZE; j++) {   
                sb.append(""+game[i][j]);   
            }   
            sb.append("\n");   
        }   
        sb.append("\n");       
        return sb.toString();   
    }   
   
    public void printStringBoard() {   
        System.out.println(this);   
    }   
    public int getOutX() {   
        return this.outX;   
    }   
    public int getOutY() {   
        return this.outY;   
    }   
    public int getVehiclesCount() {   
        return this.vehicles.size();   
    }   
    public Vehicle getVehicleAt(int i) {   
        return this.vehicles.elementAt(i);   
    }   
    public Vehicle getRedCar() {   
        return this.vehicles.firstElement();   
    }   
    public void addVehicle(Vehicle v) {   
        this.vehicles.add(v);   
        this.reserve(v);   
    }   
    public void reserve(Vehicle v) {   
        int x = v.getX();   
        int y = v.getY();   
        int i = 0;   
        while(i < v.getLength()) {   
            this.occupied[x][y] = true;   
            if(v.getDirection())   
                y++;   
            if(!v.getDirection())   
                x++;       
            i++;   
        }   
    }   
    public void freeReserved(Vehicle v) {   
        int x = v.getX();   
        int y = v.getY();   
        int i = 0;   
        while(i < v.getLength()) {   
            this.occupied[x][y] = false;   
            if(v.getDirection())   
                y++;   
            if(!v.getDirection())   
                x++;       
            i++;   
        }   
    }   
    public void setReserved(Vehicle v, int to) {   
        freeReserved(v);   
        if(v.getDirection())   
            v.setNewPosition(v.getX(), v.getY()+to);   
        else   
            v.setNewPosition(v.getX()+to, v.getY());   
        reserve(v);   
    }   
    public boolean isGoal() {   
        Vehicle v = this.getRedCar();   
        if(v.isRed() && v.getY() == this.getOutY() && v.getX() == this.getOutX())   
            return true;   
        else   
            return false;   
    }   
    public int legalNMove(Vehicle v, char to) {   
        int result = 0;   
        int length = v.getLength();   
        int x = v.getX();   
        int y = v.getY();   
        boolean direction = v.getDirection();   
           
        while(legalMove(direction, x, y, length, to)) {   
            if(direction) {   
                if(to == 'L')   
                    y--;   
                else if(to == 'R')   
                    y++;   
            } else {   
                if(to == 'U')   
                    x--;   
                else if(to == 'D')   
                    x++;   
            }   
            result++;   
        }   
        return result;   
    }   
    public boolean legalMove(boolean direction, int x, int y, int length, char to) {   
        int mx, my, px, py;   
        /**  
         * Vehicle coordinates (getX,getY)  
         */   
        if(x >= RushHour.BOARD_SIZE || x < 0 || y >= RushHour.BOARD_SIZE || y < 0)   
            return false;   
        if(direction) {   
            mx = 0;   
            my = y-1;   
            px = 0;   
            py = y+length;   
        } else {   
            mx = x-1;   
            my = 0;   
            px = x+length;   
            py = 0;   
        }   
        switch(to) {   
        case 'U':   
            if(direction || mx < 0 || this.occupied[mx][y])   
                return false;   
            else   
                return true;   
        case 'D':   
            if(direction ||  px >= RushHour.BOARD_SIZE || this.occupied[px][y])   
                return false;   
            else   
                return true;   
        case 'L':   
            if(!direction || my < 0 || this.occupied[x][my])   
                return false;   
            else   
                return true;   
        case 'R':   
            if(!direction || py >= RushHour.BOARD_SIZE || this.occupied[x][py])   
                return false;   
            else   
                return true;   
        default:   
            return false;   
        }   
    }   
    public Board move(Move m) {   
        Board newBoard = (Board) clone();   
        int index = m.getVehicle();   
        Vehicle v = newBoard.getVehicleAt(index);   
        switch(m.getDirection()) {   
        case 'U':   
            newBoard.setReserved(v, -1*m.getLength());   
            break;   
        case 'D':   
            newBoard.setReserved(v, 1*m.getLength());   
            break;   
        case 'L':   
            newBoard.setReserved(v, -1*m.getLength());   
            break;   
        case 'R':   
            newBoard.setReserved(v, 1*m.getLength());   
            break;   
        default:   
            ;   
        }   
        return newBoard;   
    }   
    public int hashCode() {   
        return this.vehicles.hashCode();   
    }   
    public boolean equals(Object o) {   
        if (!(o instanceof Board))   
        return false;   
        return vehicles.equals(((Board)o).vehicles);   
    }   
   
    public Object clone() {   
        try {   
            Board b=(Board)super.clone();   
            b.vehicles = new Vector<Vehicle>();   
            for(Vehicle v: vehicles)   
                b.vehicles.add(v.clone());   
            b.occupied=new boolean[occupied.length][];   
            for(int i=0;i<b.occupied.length;i++)   
                b.occupied[i]=occupied[i].clone();   
            return b;   
        } catch (CloneNotSupportedException e) {   
            return "CloneNotSupportedException: "+e.getMessage();   
        }   
    }   
} 

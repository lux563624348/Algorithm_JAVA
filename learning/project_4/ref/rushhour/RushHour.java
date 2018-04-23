package rushhour;   
/**  
 * Copyright Jari Kuusisto 2008  
 * Rushhour game solver v1.0  
 */   
import java.util.List;   
import net.sourceforge.jsl.*;   
/**  
 * The main class for solver  
 * Including main  
 */   
public class RushHour {   
    public final static int BOARD_SIZE = 6;   
    public final static boolean DEBUG = false;   
    public Board initialBoard;   
    public BreadthFirstSearch bfs;   
       
    public RushHour() {   
        this.initialBoard = new Board();   
    }   
    public Board getBoard() {   
        return this.initialBoard;   
    }   
    public void addVehicle(String type, char direction, int x, int y) {   
        this.initialBoard.addVehicle(new Vehicle(type, direction, x, y));   
    }   
       
    public void solve() {   
        long startTime = System.currentTimeMillis();   
        try {   
            RushHourVertex start, result;   
            start = new RushHourVertex(this.initialBoard);   
            bfs = new BreadthFirstSearch();   
            bfs.setUseHashSet(true);   
            bfs.setSeed(start);   
            result = (RushHourVertex) bfs.search();   
            System.out.println("Searched: " + bfs.getClosedList().size() + " states.");   
            printSolution(result);   
        } catch (Throwable t) {   
            System.err.println("Exception: " + t.getMessage());   
            t.printStackTrace();   
        }   
        System.out.println("\nTotal execution time was " + (System.currentTimeMillis() - startTime) + " ms.");   
    }   
   
    public static void printSolution(RushHourVertex result) {   
        if(result != null) {   
            List path = result.getPath();   
            System.out.println("Solution is:");   
            AbstractSearchNode.dumpPath(path);   
        } else {   
            System.out.println("No solution");   
        }   
    }   
    public void initRushHour10(RushHour game) {   
        /* Put red car first */   
        game.addVehicle("Red", 'h', 2, 1);   
        /* Trucks */   
        game.addVehicle("Truck", 'h', 3, 1);   
        game.addVehicle("Truck", 'v', 2, 0);   
        game.addVehicle("Truck", 'v', 1, 5);   
        /* Cars */   
        game.addVehicle("Car", 'h', 0, 0);   
        game.addVehicle("Car", 'h', 1, 0);   
        game.addVehicle("Car", 'h', 5, 0);   
        game.addVehicle("Car", 'v', 0, 2);   
        game.addVehicle("Car", 'v', 4, 3);   
        game.addVehicle("Car", 'h', 0, 4);   
        game.addVehicle("Car", 'h', 4, 4);   
        game.addVehicle("Car", 'h', 5, 4);     
    }   
    public void initRushHour20(RushHour game) {   
        /* Put red car first */   
        game.addVehicle("Red", 'h', 2, 0);   
        /* Trucks */   
        game.addVehicle("Truck", 'h', 0, 3);   
        game.addVehicle("Truck", 'v', 2, 5);   
        game.addVehicle("Truck", 'h', 5, 3);   
        /* Cars */   
        game.addVehicle("Car", 'v', 0, 0);   
        game.addVehicle("Car", 'h', 1, 1);   
        game.addVehicle("Car", 'v', 2, 2);   
        game.addVehicle("Car", 'v', 4, 2);   
        game.addVehicle("Car", 'v', 1, 3);   
        game.addVehicle("Car", 'h', 4, 3);   
    }   
    public void initRushHour30(RushHour game) {   
        /* Put red car first */   
        game.addVehicle("Red", 'h', 2, 1);   
        /* Trucks */   
        game.addVehicle("Truck", 'v', 0, 0);   
        game.addVehicle("Truck", 'h', 0, 3);   
        game.addVehicle("Truck", 'v', 3, 5);   
        /* Cars */   
        game.addVehicle("Car", 'v', 0, 2);   
        game.addVehicle("Car", 'v', 1, 3);   
        game.addVehicle("Car", 'h', 3, 0);   
        game.addVehicle("Car", 'h', 3, 2);   
        game.addVehicle("Car", 'h', 5, 0);   
        game.addVehicle("Car", 'h', 5, 2);   
    }   
    public void initRushHour40(RushHour game) {   
        /* Put red car first */   
        game.addVehicle("Red", 'h', 2, 3);   
        /* Trucks */   
        game.addVehicle("Truck", 'v', 0, 0);   
        game.addVehicle("Truck", 'v', 1, 5);   
        game.addVehicle("Truck", 'h', 3, 0);   
        /* Cars */   
        game.addVehicle("Car", 'v', 1, 1);   
        game.addVehicle("Car", 'v', 1, 2);   
        game.addVehicle("Car", 'v', 0, 4);   
        game.addVehicle("Car", 'v', 3, 3);   
        game.addVehicle("Car", 'v', 4, 2);   
        game.addVehicle("Car", 'h', 0, 1);   
        game.addVehicle("Car", 'h', 4, 4);   
        game.addVehicle("Car", 'h', 5, 0);   
        game.addVehicle("Car", 'h', 5, 3);   
    }   
    public void initRushHourColletteEtAl(RushHour game) {   
        /* Put red car first */   
        game.addVehicle("Red", 'h', 2, 2);   
        /* Trucks */   
        game.addVehicle("Truck", 'h', 0, 0);   
        game.addVehicle("Truck", 'v', 0, 4);   
        game.addVehicle("Truck", 'v', 0, 5);   
        /* Cars */   
        game.addVehicle("Car", 'h', 1, 1);   
        game.addVehicle("Car", 'h', 3, 0);   
        game.addVehicle("Car", 'h', 4, 4);   
        game.addVehicle("Car", 'h', 5, 2);   
        game.addVehicle("Car", 'h', 5, 4);   
        game.addVehicle("Car", 'v', 0, 3);   
        game.addVehicle("Car", 'v', 1, 0);   
        game.addVehicle("Car", 'v', 3, 2);   
        game.addVehicle("Car", 'v', 4, 1);         
    }   
    public static void main(String[] args) throws CloneNotSupportedException {   
        RushHour game = new RushHour();   
        /*  
         * 1st: type  
         * 2nd: (h)orizontal or (v)ertical  
         * 3rd: x-coord.  
         * 4th: y-coord.  
         * Horizontal direction is +y-axis  
         * Vertical direction is +x-axis  
         *   
         * Note: You can now place wrong on the board!  
         */   
        game.initRushHourColletteEtAl(game);   
        /* Solve game */   
        game.solve();   
    }   
}

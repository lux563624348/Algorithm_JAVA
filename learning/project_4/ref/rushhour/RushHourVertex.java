package rushhour;   
/**  
 * Copyright Jari Kuusisto 2008  
 * Rushhour game solver v1.0  
 */   
import java.util.Collections;   
import java.util.Collection;   
import java.util.List;   
import java.util.HashSet;   
import java.util.ArrayList;   
import java.util.Iterator;   
import net.sourceforge.jsl.*;   
/**  
 * Game state as a vertex  
 */   
public class RushHourVertex extends AbstractSearchNode {   
    private int depth;   
    private int breadth;   
       
    public RushHourVertex(Board domainObject) {   
        this.breadth = 0;   
        this.depth = 0;   
        setDomainObject(domainObject);   
    }   
   
    public RushHourVertex(Board domainObject, int depth) {   
        this.depth = depth;   
        setDomainObject(domainObject);   
    }   
       
    public int getBreadth() {   
        return this.breadth;   
    }   
   
    public int getDepth() {   
        return this.depth;   
    }   
       
    public Collection expand() {   
        Collection<RushHourVertex> children = new HashSet<RushHourVertex>(0);   
        ArrayList<Move> possibleMoves = new ArrayList<Move>(0);   
        Board b = (Board) getDomainObject();   
        Vehicle v;    
        int count = b.getVehiclesCount();   
        int result = 0;   
        // Check all legal moves   
        for(int i=0; i<count; i++) {   
            v = (Vehicle) b.getVehicleAt(i);   
            result = b.legalNMove(v,'U');   
            if(result > 0) {   
                for(int j=0; j<result; j++)   
                    possibleMoves.add(new Move(i,'U',1+j));   
            }   
            result = b.legalNMove(v,'D');   
            if(result > 0) {   
                for(int j=0; j<result; j++)   
                    possibleMoves.add(new Move(i,'D',1+j));   
            }   
            result = b.legalNMove(v,'R');   
            if(result > 0) {   
                for(int j=0; j<result; j++)   
                    possibleMoves.add(new Move(i,'R',1+j));   
            }   
            result = b.legalNMove(v,'L');   
            if(result > 0) {   
                for(int j=0; j<result; j++)   
                    possibleMoves.add(new Move(i,'L',1+j));   
            }   
        }   
        // Move legal moves   
        Iterator<Move> iterator = possibleMoves.iterator();   
        while(iterator.hasNext()){   
            Move m = (Move) iterator.next();   
                if(RushHour.DEBUG)   
                    m.toString();   
                Board newBoard = b.move(m);   
                if(RushHour.DEBUG)   
                    newBoard.printStringBoard();   
                RushHourVertex newVertex = new RushHourVertex(newBoard, getDepth() + 1);   
                children.add(newVertex);   
                //linkForward(this, newVertex);   
                linkForward(m, newVertex);   
        }   
        return children;   
    }   
   
    public List getPath() {   
        if (isGoal()) {   
            List path = getFirstPathIn();   
            Collections.reverse(path);   
            return path;   
        } else {   
            return null;   
        }   
    }   
       
    public boolean isGoal() {   
        return ((Board) getDomainObject()).isGoal();   
    }   
       
} 

package rushhour;   
/**  
 * Copyright Jari Kuusisto 2008  
 * Rushhour game solver v1.0  
 */   
/**  
 * Cars that move on board in game  
 */   
public class Vehicle implements Cloneable {   
    private boolean type;   
    private boolean length;   
    private boolean red;   
    private boolean dir;   
    private int x,y;   
       
    public Vehicle(boolean type, boolean length, boolean red, boolean dir, int x, int y) {   
        this.type = type;   
        this.length = length;   
        this.red = red;   
        this.dir = dir;   
        this.x = x;   
        this.y = y;   
    }   
       
    public Vehicle(String type, char c, int x, int y) {   
        boolean dir;   
        if(c == 'v')   
            dir = false;   
        else   
            dir = true;   
        if(type == "Car") {   
            this.type = false;   
            this.length = false;   
            this.red = false;   
            this.dir = dir;   
            this.x = x;   
            this.y = y;   
        } else if (type == "Truck") {   
            this.type = true;   
            this.length = true;   
            this.red = false;   
            this.dir = dir;   
            this.x = x;   
            this.y = y;   
        } else if(type == "Red") {   
            this.type = false;   
            this.length = false;   
            this.red = true;   
            this.dir = dir;   
            this.x = x;   
            this.y = y;   
        }   
    }   
   
    public int hashCode() {   
        return (red?131072:0)+(length?65536:0)+(x<<8)+y;   
    }   
   
    public boolean equals(Object o) {   
        if (!(o instanceof Vehicle))           
            return false;   
        Vehicle v=(Vehicle)o;   
        return (type==v.type)&&(length==v.length)&&(red==v.red)&&(x==v.x)&&(y==v.y);   
        }   
       
    public boolean getType() {   
        return this.type;   
    }   
       
    public int getLength() {   
        if(this.length)   
            return 3;   
        else   
            return 2;   
    }   
       
    public boolean isRed() {   
        return this.red;   
    }   
       
    public boolean getDirection() {   
        return this.dir;   
    }   
       
    public void setNewPosition(int x, int y) {   
        this.x = x;   
        this.y = y;   
    }   
       
    public int getX() {   
        return this.x;   
    }   
       
    public int getY() {   
        return this.y;   
    }   
       
    public String typeToString() {   
        if(this.type)   
            return (new String("Truck"));   
        else   
            return (new String("Car"));   
    }   
   
    public Vehicle clone() {   
    try {   
        return (Vehicle)super.clone();   
    } catch(CloneNotSupportedException e) {   
        throw new Error("Vehicle should be Cloneable.", e);   
    }   
    }   
}

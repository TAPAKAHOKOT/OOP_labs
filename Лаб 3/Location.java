/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Location loc){
        if (this == loc)
            return true;
        else if (loc == null)
            return false;
        else{
            return xCoord == loc.xCoord && yCoord == loc.yCoord;
        }
    }

    public int hashCode(){
        final int prime = 31;
        return (prime + xCoord ) * prime + yCoord;
    }
}
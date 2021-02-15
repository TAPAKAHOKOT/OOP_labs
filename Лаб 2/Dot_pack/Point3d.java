package Dot_pack;

import Dot_pack.Point2d;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;


public class Point3d extends Point2d{
	private double Z;

	public Point3d(){
		this(0, 0, 0);	
	}

	public Point3d(double x, double y, double z){
		super(x, y);
		Z = z;
	}

	public double getZ(){return Z;}
	public void setZ(double z){Z = z;}

	public boolean isEqualDots(Point3d dot){
		return (dot.getX() == this.getX() && dot.getY() == this.getY() && dot.getZ() == Z);
	}

	public double distanceTo(Point3d dot){
		return round(
					sqrt( 
						pow( dot.getX() - this.getX(), 2 ) 
						+ pow( dot.getY() - this.getY(), 2 ) 
						+ pow( dot.getZ() - Z, 2 )
					)*100
				) / 100d;
	}
}
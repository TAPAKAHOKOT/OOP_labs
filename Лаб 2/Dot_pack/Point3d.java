package Dot_pack;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;


public class Point3d{
	private double X;
	private double Y;
	private double Z;

	public Point3d(){
		this(0, 0, 0);	
	}
	public Point3d(double x, double y, double z){
		X = x;
		Y = y;
		Z = z;
	}

	public double getX(){return X;}
	public double getY(){return Y;}
	public double getZ(){return Z;}

	public void setX(double x){X = x;}
	public void setY(double y){Y = y;}
	public void setZ(double z){Z = z;}

	public boolean isEqualDots(Point3d dot){
		return (dot.getX() == X && dot.getY() == Y && dot.getZ() == Z);
	}

	public double distanceTo(Point3d dot){
		return round(
					sqrt( 
						pow( dot.getX() - X, 2 ) 
						+ pow( dot.getY() - Y, 2 ) 
						+ pow( dot.getZ() - Z, 2 )
					)*100
				) / 100d;
	}
}
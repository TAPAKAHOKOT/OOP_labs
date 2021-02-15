package Dot_pack;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;


public class Point2d{
	private double X;
	private double Y;

	public Point2d(){
		this(0, 0);	
	}
	public Point2d(double x, double y){
		X = x;
		Y = y;
	}

	public double getX(){return X;}
	public double getY(){return Y;}

	public void setX(double x){X = x;}
	public void setY(double y){Y = y;}
}
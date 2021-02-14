import Dot_pack.Point3d;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;


public class Lab2{
	public static void main(String[] args){
		Point3d dot_1 = new Point3d();
		Point3d dot_2 = new Point3d(1, 2, 3);
		Point3d dot_3 = new Point3d(1, 1, 1);

		System.out.println(computeArea(dot_1, dot_2, dot_3));
	}

	static double computeArea(Point3d d1, Point3d d2, Point3d d3){
		if (d1.isEqualDots(d2) || d2.isEqualDots(d3) || d3.isEqualDots(d1)){
			System.out.println("ERROR: точки совпадают(((")
			return 0d;
		}


		double a = d1.distanceTo(d2);
		double b = d2.distanceTo(d3);
		double c = d3.distanceTo(d1);

		double p = (a + b + c) / 2;

		return round( sqrt( p*(p - a)*(p-b)*(p-c) )*100 )/100d;
	}
}
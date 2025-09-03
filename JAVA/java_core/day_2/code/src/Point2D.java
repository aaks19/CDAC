
import java.lang.Math;


class Point2D{
	private int x, y;
			
	Point2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	int getx(){
		return this.x;
	}
	
	int gety(){
		return this.y;
	}
	
	
	boolean isEqual(Point2D a){
		if(this.x == a.x && this.y == a.y){
			return true;
		}else{
			return false;
		}
	}
	
	
	double calculateDistance(Point2D a){
		double dist = Math.sqrt(Math.pow((this.x-a.x),2)+Math.pow((this.y-a.y),2));
		return dist;
	}
		
}

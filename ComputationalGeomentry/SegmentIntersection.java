import java.util.*;
public class SegmentIntersection{
	public boolean SegmentsIntersect(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4){
		int d1 = Direction(x3,y3,x4,y4,x1,y1);
		int d2 = Direction(x3,y3,x4,y4,x2,y2);
		int d3 = Direction(x1,y1,x2,y2,x3,y3);
		int d4 = Direction(x1,y1,x2,y2,x4,y4);
		if(((d1>0 && d2<0) || (d1<0 && d2>0)) && ((d3>0 && d4<0) || (d3<0 && d4>0))){
			return true;
		}else if (d1==0 && OnSegment(x3,y3,x4,y4,x1,y1)){
			return true;
		}else if (d2==0 && OnSegment(x3,y3,x4,y4,x2,y2)){
			return true;
		}else if (d3==0 && OnSegment(x1,y1,x2,y2,x3,y3)){
			return true;
		}else if (d4==0 && OnSegment(x1,y1,x2,y2,x4,y4)){
			return true;
		}else{
			return false;
		}
	}
	public int Direction(int x1,int y1,int x2,int y2,int x3,int y3){
		return (x2-x1)*(y3-y1)-(x3-x1)*(y2-y1);
	}

	public boolean OnSegment(int x1,int y1,int x2,int y2,int x3,int y3 ){
		if ((Math.min(x1,x2)<=x3 && x3<=Math.max(x1,x2)) && (Math.min(y1,y2)<=y3 && y3<=Math.max(y1,y2))){
			return true;
		}else{
			return false;
		}
	}
}

/**
 * The below function implements Disjoint Set datastructure.
 * The code is adapted from Introduction to Algorithms 2nd Adition.
 *
 * @author Ayush Anand<iamayushanand@gmail.com>
 * */

class Node{
	Node parent=null;
	int rank=0;
}
public class DisJointSet{
	public void MAKESET(Node x){
		x.parent=x;
	}

	public UNION(Node x,Node y){
		LINK(FINDSET(x),FINDSET(y));
	}

	public LINK(Node x,Node y){
		if(x.rank>y.rank){
			y.parent = x;
		}else{
			x.parent =y;
			if(x.rank==y.rank){y.rank++;}
		}
	}

	public FINDSET(Node x){
		if(x!=x.parent){x.parent=FINDSET(x.parent);}
		return x.parent;
	}

}

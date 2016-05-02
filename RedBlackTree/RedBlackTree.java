

import java.util.*;


/*
 *The following is an implementation of red-black tree
 * 
 *notes:-
 * rules:-
 *	1) Every node is either red or black
 *	2) the root is black
 *	3) Every external leaf is black
 *	4) if a node is red both its children are red.
 *	5) all simple paths to external leaves contain same number of black nodes.
 *
 *	
 *
 * */
class RedBlackTreeNode{
	int key=0;
	RedBlackTreeNode parent = null;
	RedBlackTreeNode left = null;
	RedBlackTreeNode right =null;

	boolean color = false; // false if color==black true if color==red

	public RedBlackTreeNode(boolean color,int key,RedBlackTreeNode l,RedBlackTreeNode r){
		this.key = key;
		this.left = l;
		this.right = r;
		this.color=color;
	}

}
public class RedBlackTree{
	
	RedBlackTreeNode nil = new RedBlackTreeNode(false,-100000000,null,null);
	RedBlackTreeNode root = this.nil;
		
	public RedBlackTree(int key){
		this.root = new RedBlackTreeNode(false,key,this.nil,this.nil);
		this.root.parent=this.nil;
	}


	public void Left_Rotate(RedBlackTreeNode pivot){
		RedBlackTreeNode y = pivot.right;
		pivot.right = y.left;
		if(y.left!=this.nil)
			y.left.parent = pivot;
		y.parent = pivot.parent;
		System.out.println("pivot key: "+pivot.key);
		System.out.println("pivot parent key: "+pivot.parent.key);
		if(pivot.parent==this.nil)
			this.root=y;
		else if(pivot==pivot.parent.left)
			pivot.parent.left = y;
		else    pivot.parent.right=y;
		y.left=pivot;
		pivot.parent=y;
	}

	public void Right_Rotate(RedBlackTreeNode pivot){
		RedBlackTreeNode y = pivot.left;
		pivot.left = y.right;
		if(y.right!=this.nil)
			y.right.parent = pivot;
		y.parent = pivot.parent;
		if(pivot.parent==this.nil)
			this.root=y;
		else if(pivot==pivot.parent.right)
			pivot.parent.right = y;
		else    pivot.parent.left=y;
		y.right=pivot;
		pivot.parent=y;
	
	}

	public void Insert_Node(RedBlackTreeNode insertThis){
		RedBlackTreeNode y=this.nil;
		RedBlackTreeNode x=this.root;
	
		while(x!=this.nil){
			y=x;
			//System.out.println(insertThis.key);
			//System.out.println(x.key);
			if (insertThis.key < x.key){
				x=x.left;
			}else{
				x=x.right;
			}
		}
			//System.out.println("Key"+y.key);
			insertThis.parent = y;
			if(y==this.nil)
				this.root=insertThis;
			else if(insertThis.key < y.key)
				y.left = insertThis;
			else    y.right = insertThis;

			insertThis.left = this.nil;
			insertThis.right = this.nil;
			insertThis.color = true;

			FixColorsInsertion(insertThis);
	}
	
	
	public void insert(int key){
		RedBlackTreeNode insertThis = new RedBlackTreeNode(true,key,this.nil,this.nil);
		Insert_Node(insertThis);
	
	}
	public void FixColorsInsertion(RedBlackTreeNode insertThis){
		while(insertThis.parent.color){
			if(insertThis.parent == insertThis.parent.parent.left){
				RedBlackTreeNode y = insertThis.parent.parent.right;
				if(y.color){
					insertThis.parent.color=false;
					y.color=false;
					insertThis.parent.parent.color=true;
					insertThis=insertThis.parent.parent;
				}else{ if(insertThis==insertThis.parent.right){
					insertThis=insertThis.parent;
					Left_Rotate(insertThis);
				}
				insertThis.parent.color = false;
				insertThis.parent.parent.color = true;
				Right_Rotate(insertThis.parent.parent);
				}
			}else{
				RedBlackTreeNode y = insertThis.parent.parent.left;
				if(y.color){
					insertThis.parent.color=false;
					y.color=false;
					insertThis.parent.parent.color=true;
					insertThis=insertThis.parent.parent;
				}else{ if(insertThis==insertThis.parent.left){
					insertThis=insertThis.parent;
					Right_Rotate(insertThis);
				}
				insertThis.parent.color = false;
				//System.out.println("insertThis parent key: "+insertThis.parent.key);
				//System.out.println("insertThis key: "+insertThis.key);
				insertThis.parent.parent.color = true;
				Left_Rotate(insertThis.parent.parent);
			}}
		
		}
		this.root.color=false;
	
	}

	public void Transplant(RedBlackTreeNode u,RedBlackTreeNode v){
		if(u.parent==this.nil){
			this.root = v;
		}else if(u==u.parent.left){
			u.parent.left=v;
		}else{
			u.parent.right = v;
		}
		v.parent=u.parent;
	}

	public RedBlackTreeNode Minimum(RedBlackTreeNode x){
		while(x.left != this.nil){
			x=x.left;
		}
		return x;
	}

	public void Delete(RedBlackTreeNode z){
		RedBlackTreeNode y = z;
		RedBlackTreeNode x=this.nil;
		boolean originalColor = y.color;
		if(z.left==this.nil){
			x = z.left;
			Transplant(z,z.right);
		}else{
			y=Minimum(z.right);
			originalColor = y.color;
			x=y.right;
			if(y.parent==z){
				x.parent=y;
			}else{
				Transplant(y,y.right);
				y.right = z.right;
				y.right.parent=y;

			}
			Transplant(z,y);
			y.left=z.left;
			y.left.parent=y;
		        y.color=z.color;
			
		}
		if(!originalColor){
			FixColorsDeletion(x);
		}
	}

	public void FixColorsDeletion(RedBlackTreeNode x){
		while(x!=this.root && x.color == false){
			if(x==x.parent.left){
				RedBlackTreeNode w = x.parent.right;
				if(w.color){
					w.color=false;
					x.parent.color=true;
					Left_Rotate(x.parent);
					w=x.parent.right;
				}
				if(!(w.left.color) && !(w.right.color)){
					w.color=true;
					x=x.parent;
				}
				else{ 
					if(!(w.right.color)){
						w.left.color=false;
						w.color=true;
						Right_Rotate(w);
						w=x.parent.right;
					}
					w.color=x.parent.color;
					x.parent.color = false;
					w.right.color = false;
					Left_Rotate(x.parent);
					x=this.root;
				}
			}else{
				RedBlackTreeNode w = x.parent.left;
				if(w.color){
					w.color=false;
					x.parent.color=true;
					Right_Rotate(x.parent);
					w=x.parent.left;
				}
				if(!(w.right.color) && !(w.left.color)){
					w.color=true;
					x=x.parent;
				}else{ 
					if(!(w.left.color)){
						w.right.color=false;
						w.color=true;
						Left_Rotate(w);
						w=x.parent.left;
					}
					w.color=x.parent.color;
					x.parent.color = false;
					w.left.color = false;
					Right_Rotate(x.parent);
					x=this.root;
				}
			
			}
			x.color=false;
		
		}
	
	}

	public static void main(String[] main){
		RedBlackTree tree = new RedBlackTree(4);
		
		tree.insert(59);
		//System.out.println(tree.root.key);
		//System.out.println(tree.root.left.key);
		//System.out.println(tree.root.right.key);
		tree.insert(78);
		tree.insert(879);
		tree.insert(88665);

	 	//System.out.println(tree.root.key);
		//System.out.println(tree.root.left.key);
		//System.out.println(tree.root.right.key);

	}
	


}

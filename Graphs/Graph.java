import java.util.*;
/**
 * Graph.java
 *
 * <p>
 * This java file implements a Graph. This implementation uses
 * adjacency matrix even though adjacency list would have been a little better
 * therefore this implementation assumes that we are dealing with "dense" graphs
 * .There are two algorithms for MST in the graph one of them (Kruskal's algorithm) require Disjoint sets.
 * I am planning to change priority queue to Fibonacci Heaps but as of now it is priority queue.
 * For the sake of simplicity I have written Disjoint sets inside this file but you can optionally 
 * put them in another file.I will soon publish some analysis on the following algorithms.
 *
 * If you find any mistakes or you want to contribute to this code feel free to add an issue on github.
 * 
 * I will update keep updating this file in the future.
 *
 * Note: If you are using this implementation please make sure to put this comment
 * on top of the source.
 * </p>
 *
 * @author Ayush Anand<iamayushanand@gmail.com>
 *
 * Copyright (C) 2016 Ayush Anand
 * */
public class Graph{
	int MAX_V;
	boolean[][] matrix=new boolean[MAX_V][MAX_V];
	boolean[][] transpose = new boolean[MAX_V][MAX_V];
	int[][] weight = new int[MAX_V][MAX_V];
	int[][] AllPairsShortestPath = new int[MAX_V][MAX_V];
	
	Edge[] edges = new Edge[MAX_V*MAX_V];

	int Vlength=0;
	int Elength=0;
	Vertex[] vertices = new Vertex[MAX_V];
	int time=0;

	public void addVertex(){
		vertices[Vlength]=new Vertex(++Vlength);
	}

	public void addEdge(int u,int v){
		if(u<=Vlength && v<=Vlength){
			matrix[u-1][v-1]=true;
			edges[Elength++]=new Edge();
			edges[Elength-1].u=vertices[u-1];
			edges[Elength-1].v=vertices[v-1];
		}
	}

	public void addEdge(int u,int v,int w){
		if(u<=Vlength && v<=Vlength){
			matrix[u-1][v-1]=true;
			weight[u-1][v-1]=w;
			edges[Elength++]=new Edge();
			edges[Elength-1].u=vertices[u-1];
			edges[Elength-1].v=vertices[v-1];
			edges[Elength-1].weight=w;
		}
	
	}
	
	public void bfs(int source){
		vertices[source-1].color=0;
		vertices[source-1].dist=0;
		ArrayList<Vertex> q = new ArrayList<Vertex>();
		q.add(vertices[source-1]);
		while(!q.isEmpty()){
			Vertex current = q.remove(0);
			for(int i=0;i<Vlength;i++){
				if(matrix[current.idx-1][i] && vertices[i].color==-1){
					vertices[i].color=0;
					vertices[i].dist=current.dist+1;
					vertices[i].pred=current;
					q.add(vertices[i]);
				}
			}
			current.color=1;
		}
	}

	public void DFS(boolean[][] Gr,boolean output){
		time =0;
		for(int i=0;i<Vlength;i++){
			if(vertices[i].color==-1){
				DFSVISIT(Gr,vertices[i],output);
				if(output){System.out.println();}
			}
		}
	}

	public void DFSVISIT(boolean[][] Gr,Vertex u,boolean output){
		time++;
		if(output){System.out.println(u.idx);}
		u.dist=time;
		u.color=0;
		for(int i=0;i<Vlength;i++){
			if(Gr[u.idx-1][i] && vertices[i].color==-1){
				vertices[i].pred=u;
				DFSVISIT(Gr,vertices[i],output);
			
			}
			u.color=1;
			time++;
			u.fin=time;
		}
		
	}

	public void generateTranspose(){
		for(int i=0;i<Vlength;i++){
			for(int j=0;j<Vlength;j++){
				transpose[j][i]=matrix[i][j];
				
			}
		}
	}

	public void StronglyConnectedComponents(){
		DFS(matrix,true);
		generateTranspose();
		Arrays.sort(vertices,0,Vlength,new Comparator<Vertex>() {
			@Override
			public int compare(Vertex a,Vertex b){
				if(a.fin<b.fin){
					return 1;
				}else if(a.fin>b.fin){
					return -1;
				}else{
					return 0;
				}
			}
		});

		DFS(transpose,true);
		
	}

	public void MSTPrim(Vertex r){
		r.key=0;
		Comparator<Vertex> comp = new Comparator<Vertex>() {
			@Override
			public int compare(Vertex a,Vertex b){
				if(a.key<b.key){
					return -1;
				}else if(a.key>b.key){
					return 1;
				}else{
					return 0;
				}
			}
		};
		PriorityQueue Q= new PriorityQueue(Vlength,comp);
		for(int i=0;i<Vlength;i++){
			Q.add(vertices[i]);
		}
		while(!Q.isEmpty()){
			Vertex u=(Vertex)Q.poll();
			for(int i=0;i<Vlength;i++){
				if(matrix[u.idx-1][i]==true && Q.contains(vertices[i]) && weight[u.idx-1][i]<vertices[i].key){
					Q.remove(vertices[i]);
					vertices[i].pred=u;
					vertices[i].key=weight[u.idx-1][i];
					Q.add(vertices[i]);
				}	
			}
		}

	}
	public ArrayList<Edge> MSTKruskals(){
		DisJointSet A=new DisJointSet();
		ArrayList<Edge> MST = new ArrayList<Edge>();
		for(int i=0;i<Vlength;i++){
			A.MAKESET(vertices[i]);
		}	
		Arrays.sort(edges,0,Elength,new Comparator<Edge> (){
			@Override
			public int compare(Edge a,Edge b){
				if(a.weight<b.weight){
					return -1;
				}else if(a.weight>b.weight){
					return 1;
				}
				return 0;
			}
		});

		for(int i=0;i<Elength;i++){
			if(A.FINDSET(edges[i].v)!=A.FINDSET(edges[i].u)){
				MST.add(edges[i]);
				A.UNION(edges[i].u,edges[i].v);
			}
		}


		return MST;	
	}
	
	public void Dijkstras(int source){
		Vertex s=vertices[source-1];
		s.SingleSourceShortestPath=0;
		PriorityQueue<Vertex> ToCover = new PriorityQueue<Vertex>(Vlength,new Comparator<Vertex>(){
			@Override
			public int compare(Vertex a,Vertex b){
				if(a.SingleSourceShortestPath<b.SingleSourceShortestPath){
					return -1;
				}else if(a.SingleSourceShortestPath>b.SingleSourceShortestPath){
					return 1;
				}
				return 0;
			}
		
		});
		for(int i=0;i<Vlength;i++){
			ToCover.add(vertices[i]);
		}
		while(!ToCover.isEmpty()){
			Vertex u=(Vertex)ToCover.poll();
			for(int j=0;j<Vlength;j++){
				if(matrix[u.idx-1][j]==true && ToCover.contains(vertices[j]) &&
						vertices[u.idx-1].SingleSourceShortestPath+weight[u.idx-1][j]<vertices[j].SingleSourceShortestPath){
							ToCover.remove(vertices[j]);
							vertices[j].SingleSourceShortestPath=vertices[u.idx-1].SingleSourceShortestPath+weight[u.idx-1][j];							  vertices[j].pred=u;
							ToCover.add(vertices[j]);
						}
			}
		}
	}
	
	public boolean BellmanFord(int s){
		Vertex source = vertices[s-1];
		source.SingleSourceShortestPath=0;
		for(int i=0;i<Vlength-1;i++){
			for(int j=0;j<Elength;j++){
				Edge cur= edges[j];
				if(cur.v.SingleSourceShortestPath>cur.u.SingleSourceShortestPath+weight[cur.u.idx-1][cur.v.idx-1]){
					
					cur.v.SingleSourceShortestPath=cur.u.SingleSourceShortestPath+weight[cur.u.idx-1][cur.v.idx-1];
					cur.v.pred=cur.u;
				}
			}
		}
		for(int i=0;i<Elength;i++){
			Edge cur = edges[i];
			if(cur.v.SingleSourceShortestPath>cur.u.SingleSourceShortestPath+weight[cur.u.idx-1][cur.v.idx-1]){
				return false;
			}
		}
		return true;
	}
	
	public void FloydWarshall(){
		for(int i=0;i<Vlength;i++){
			for(int j=0;j<Vlength;j++){
				if(i==j){
					AllPairsShortestPath[i][j]=0;
				}else if(matrix[i][j]){
					AllPairsShortestPath[i][j]=weight[i][j];
				}else{
					AllPairsShortestPath[i][j]=1000000000;
				}
			}
		}

		for(int k=0;k<Vlength;k++){
			for(int i=0;i<Vlength;i++){
				for(int j=0;j<Vlength;j++){
					AllPairsShortestPath[i][j]=Math.min(AllPairsShortestPath[i][j],AllPairsShortestPath[i][k]+AllPairsShortestPath[k][j]);
				}
			}
		}
	}
}


 class DisJointSet{
	public void MAKESET(Vertex x){
		x.parent=x;
	}

	public void UNION(Vertex x,Vertex y){
		LINK(FINDSET(x),FINDSET(y));
	}

	public void  LINK(Vertex x,Vertex y){
		if(x.rank>y.rank){
			y.parent = x;
		}else{
			x.parent =y;
			if(x.rank==y.rank){y.rank++;}
		}
	}

	public Vertex FINDSET(Vertex x){
		if(x!=x.parent){x.parent=FINDSET(x.parent);}
		return x.parent;
	}

}

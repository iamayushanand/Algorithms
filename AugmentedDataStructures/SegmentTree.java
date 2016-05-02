import java.util.ArrayList;

/**
 * Created by mYPC on 22/12/2015.
 */
class SegmentTreeNode{
    SegmentTreeNode L;
    SegmentTreeNode R;
    int start;
    int end;
    int value;


    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
public class SegmentTree {
    SegmentTreeNode RootNode =  new SegmentTreeNode();
    SegmentTreeNode curr = RootNode;
    int compare1=0;
    int compare2=0;
    boolean empComp1;
    boolean empComp2;
    public int[] SortThis;
    public void Construct(int start,int end,SegmentTreeNode n){
        if(start==end){
            n.start = start;
            n.end = end;
            n.value = this.SortThis[start];

        }else {
            SegmentTreeNode Left = new SegmentTreeNode();
            SegmentTreeNode Right = new SegmentTreeNode();
            n.L =Left;
            n.R =Right;
            n.start = start;
            n.end = end;
            Construct(start,(start+end)/2,n.L);
            Construct(((start+end)/2)+1,end,n.R);
            n.value = Math.min(n.L.value,n.R.value);
            //this.curr.L.value=RangeMin(start,(start+end)/2);
            //this.curr.R.value=RangeMin(((start+end)/2)+1,end);
            //this.curr = this.curr.L;

        }

    }

    public void Construct(){
        Construct(0,this.SortThis.length-1,this.RootNode);
    }


    public int RMQ(int start,int end,SegmentTreeNode n){
        if(n.start>=start && n.end<=end ){
            return n.value;
        }else if((n.start<start && n.end<end && n.end<start) || (n.start>start && n.end>end && n.start>end)){
            return Integer.MAX_VALUE;
        }else{
            return Math.min(RMQ(start,end,n.L),RMQ(start,end,n.R));
        }

    }

    private void update(SegmentTreeNode node,int pos ,int val){
        if(node.start == node.end){
            node.value=val;
        }else{
            if( pos>=node.L.start && pos<=node.L.end){
                update(node.L,pos,val);
            }else{
                update(node.R,pos,val);
            }

            node.value=Math.min(node.L.value,node.R.value);
        }
    }



    public void update(int pos,int val){
        update(RootNode,pos,val);
    }

    public int RangeMin(int start,int end){
        return RMQ(start,end,this.RootNode);

    }
    public SegmentTree(int[] nums){
        this.SortThis = nums;
    }

    public void PrintTree(){

        int ins = 40;
        ArrayList<SegmentTreeNode> nodes=new ArrayList<SegmentTreeNode>() ;
        int curNodes =1;
        int nxtNodes =0;
        nodes.add(RootNode);

        int level=1;
        System.out.print("level #"+level+": ");
        while(!nodes.isEmpty()){
            SegmentTreeNode curr = nodes.get(0);
            nodes.remove(0);
            curNodes--;

            if(curr!=null){

                for(int i=0;i<ins;i++){
                    System.out.print(" ");
                }
                System.out.print(curr.value+"["+curr.start+","+curr.end+"]");
                for(int i=0;i<ins;i++){
                    System.out.print(" ");
                }

                nodes.add(curr.L);
                nodes.add(curr.R);
                nxtNodes+=2;
            }

            if (curNodes == 0) {
                System.out.println();

                ins/=2;
                level++;
                System.out.print("level #"+level+": ");
                curNodes = nxtNodes;
                nxtNodes = 0;
            }
        }
}
}

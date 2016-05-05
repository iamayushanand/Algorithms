import java.io.*;
import java.util.*;
/** <p>
  * This program solves the maximum sum subarray problem.
  * The code was originally meant for a hackerrank problem
  * but it came into my mind to actually post this on github.
  * </p> 
  *
  * @author Ayush Anand<iamayushanand@gmail.com>
  */
public class main {

    public int[]  MaximumCrossingSubArray(int[] input,int mid,int low,int high){
    	int sum=0;
	int MaxLeftSum = 0;
	int l = 0;
	for(int i=mid;i>=low;i--){
	    sum+=input[i];
	    if(MaxLeftSum<sum){
	    	MaxLeftSum=sum;
	    	l=i;
	    }
	}

	sum=0;
	int MaxRightSum=0;
	int h=0;
	for(int i=mid+1;i<=high;i++){
		sum+=input[i];
		if(sum>MaxRightSum){
			MaxRightSum = sum;
			h=i;
		}
	}

    	return new int[]{MaxLeftSum+MaxRightSum,l,h};
    }


    public int[] MaxSubarray(int[] input, int start, int end){
	if(start == end){
		return new int[]{input[start],start,end};
	}else{
    		int mid  =  (start+end)/2;
		int[] left = MaxSubarray(input,start,mid);
		//DEBUG.DebugArray(left);
		int[] right =  MaxSubarray(input,mid+1,end);
		//DEBUG.DebugArray(right);
		int[] cross = MaximumCrossingSubArray(input,mid,start,end);
		//DEBUG.DebugArray(cross);
		if(cross[0]>=Math.max(left[0],right[0])){
			return cross; 
		}
		if(left[0]>=Math.max(cross[0],right[0])){
			return left;
		}
		else{
    			return right;
    		}
	}
    }

    public void solve(int[] input, PrintWriter out) {
	int[] diffrence = new int[input.length-1];
	for(int i = 0;i<input.length-1;i++){
		diffrence[i]=input[i+1]-input[i];
	}
	//DEBUG.DebugArray(diffrence);
	int[] ans = MaxSubarray(diffrence,0,input.length-2);
	out.println("The maximum profit you can gain in the next "+input.length+" days is "+ans[0]+" you should buy at day  "+ (ans[1]+1)+" and sell at day "+(ans[2]+2));
    }

    public static void main(String[] args) throws IOException {
	
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int[] T = IOUtils.readLinearIntArray(in);
        Date t1 = new Date();
        main solver = new main();
        
        solver.solve(T, out);
        out.flush();
        
        Date t2 = new Date();
        System.err.println(String.format("Your program took %f seconds", (t2.getTime() - t1.getTime()) / 1000.0));
        out.close();
	
		
	
    }

    static class IOUtils {

        public static int[] readIntArray(Scanner in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = in.nextInt();
            return array;
        }

        public static String[] readStringArray(Scanner in, int size) {
            String[] array = new String[size];
            for (int i = 0; i < size; i++)
                array[i] = in.nextLine();
            return array;
        }

        public static int[] readLinearIntArray(Scanner in, int size) {
            //in.nextLine();
            String[] arr = in.nextLine().split(" ");
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = Integer.parseInt(arr[i]);
            return array;
        }

        public static int[] readLinearIntArray(BufferedReader in) throws IOException {
            //in.nextLine();
            String[] arr = in.readLine().split(" ");
            int[] array = new int[arr.length];
            for (int i = 0; i < arr.length; i++)
                array[i] = Integer.parseInt(arr[i]);
            return array;
        }

        public static Double[] readLinearDoubleArray(BufferedReader in) throws IOException {
            //in.nextLine();
            String[] arr = in.readLine().split(" ");
            Double[] array = new Double[arr.length];
            for (int i = 0; i < arr.length; i++)
                array[i] = Double.parseDouble(arr[i]);
            return array;
        }

        public static String[] convert(int[] from) {
            String[] to = new String[from.length];
            for (int i = 0; i < to.length; i++) to[i] = String.valueOf(from[i]);
            return to;
        }

        public static int[] convert(String[] from) {
            int[] to = new int[from.length];
            for (int i = 0; i < to.length; i++) to[i] = Integer.parseInt(from[i]);
            return to;
        }

        public static int[][] read2DIntArray(BufferedReader in, int rows, int columns, String seprator) throws IOException {
            int[][] array = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                array[i] = convert(in.readLine().split(seprator));
            }
            return array;
        }

        public static String[][] read2DStringArray(BufferedReader in, int rows, int columns, String seprator) throws IOException {
            String[][] array = new String[rows][columns];
            for (int i = 0; i < rows; i++) {
                array[i] = in.readLine().split(seprator);
            }
            return array;
        }
    }


    static class DEBUG {
        public static void DebugInfo(String disp) {
            System.err.println("DEBUG Info: " + disp);
        }

        public static void DebugVariable(String variable, String value) {
            System.err.println("DEBUG Info: " + variable + " => " + value);
        }


        public static void DebugVariable(String variable, int value) {
            System.err.println("DEBUG Info: " + variable + " => " + value);
        }


        public static void DebugVariable(String variable, long value) {
            System.err.println("DEBUG Info: " + variable + " => " + value);
        }

        public static void DebugArray(int[] value) {
            for (int i = 0; i < value.length; i++) {

                System.err.println("DEBUG Info: " + i + " => " + value[i]);
            }
        }

    }

}

import java.io.*;
import java.util.*;
/**
 * <p>
 * this class implements the rodcuting problem.
 * This program takes input from standard inpout 
 * </p>
 * 
 * @author Ayush Anand<iamayushanand@gmail.com>
 * 
 */
public class RodCutting {
    
    
    public void solve(int n,int[] p, PrintWriter out) {
	int[] dp = new int[n+1];
	dp[0] = 0;
	
        
	for(int i=0;i<n;i++){
		int max=p[i];
		for(int j=0;j<i;j++){
			max=Math.max(max,dp[i-j+1]+dp[j]);
		}	
		out.println("max: "+max);
		dp[i+1] = max; 
	}
	out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
	
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
	int[] p = IOUtils.readLinearIntArray(in);
        Date t1 = new Date();
        RodCutting solver = new RodCutting();
        solver.solve(n,p, out);
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

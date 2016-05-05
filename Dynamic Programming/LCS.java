import java.io.*;
import java.util.*;
/**
  * <p>
  * This program finds the longest common subsequence length.though you can construct 	 the subsequence with some optimization.
  * </p>
  *
  * @author Ayush Anand<iamayushanand@gmail.com>
  */
public class LCS {
    public void solve(char[] a,char[] b, PrintWriter out) {
	int[][] dp = new int[a.length+1][b.length+1];
	String construct = "";
	dp[0][0] = a[0]==b[0] ? 1 : 0;
	for(int i=1;i<a.length;i++){

		for(int j=1;j<b.length;j++){
			if(a[i]==b[j]){
				dp[i][j]=dp[i-1][j-1] + 1;
				
			}else if(a[i]!=b[j]){
				dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
			}
		}
	}	
	out.println("The longest common subsequence: "+dp[a.length-1][b.length-1]);
	
    }

    public static void main(String[] args) throws IOException {
	
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String a = in.readLine();
	String b = in.readLine();
        Date t1 = new Date();
        LCS solver = new LCS();
        solver.solve(a.toCharArray(),b.toCharArray(), out);
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

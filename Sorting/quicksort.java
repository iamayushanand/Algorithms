import java.util.*;
import java.io.*;
/**
 *<p>
 *this code implements quicksort with the pivot at
 *the middle element.
 *</p>
 *@author Ayush Anand <iamayushanand@gmail.com>
 *
 */
public class quicksort{

	int[] input;

	/**
	 * <p>
	 * sort function sorts an int[]  array
	 * </p>
	 *
	 * @params int[] input
	 *
	 */
	public void sort(int start,int end){
		
		/*
		 * checks for wrong input 
		 */
		if(input==null || input.length ==0){
			return;
		}
		
		quicksortUtil(start,end);
	
	}

	public quicksort(int[] input){
		this.input=input;
	}

	private void quicksortUtil(int start,int end){

		/*
		 *takes the pivot as the middle number in the array
		 */
		int pivot = input[start+(end-start)/2];

		int low=start;
		int high=end;

		
		while(low<=high){
			
			while(input[low]<pivot){
				low++;
			}
			while(input[high]>pivot){
				high--;
			}
			
			if(low<=high){
				swap(low,high);
				low++;
				high--;
			}
		}
		if(start<low){
			quicksortUtil(start,high);
		}
		if(low<end){
			quicksortUtil(low,end);
		}
	}

	/**
	 * swaps two numbers in the array
	 * */
	private void swap(int i,int j){
		int temp=input[j];
		input[j]=input[i];
		input[i]=temp;
	}
		
	/**
	 *testing function
	 * 
	 * */
	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter("runTime.dat");
		int[] testdata =new int[5000];
		int len=0;
		for(int i=0;i<5000;i++){
			testdata[len++]=new Random().nextInt(100000000);
			quicksort quicksorter = new quicksort(testdata);
			long time1=System.nanoTime();
			quicksorter.sort(0,len-1);
			long time2=System.nanoTime();
			out.println(len+" "+(time2-time1));
			System.out.println(len+" "+(time2-time1));
		}
		out.flush();
		out.close();
	}
}

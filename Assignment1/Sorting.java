import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Sorting {
	final int MAX_SIZE = 10000000;

	// Set this to true if you wish the arrays to be printed.
	final static boolean OUTPUT_DATA = false;
	
	public static String sortAlg= null;
	public static  int size = 0;
	
	public static void main(String[] args) {
		readInput();
		int [] data = new int[size];
		GenerateSortedData(data, size);
		Sort(data, size,"Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size,"Nearly Sorted Data");
		
		GenerateReverselySortedData(data, size);
		Sort(data, size,"Reversely Sorted Data");
		
		GenerateRandomData(data, size);
		Sort(data, size,"Random Data");
			
		System.out.println("\nProgram Completed Successfully.");
		
	}
	
	@SuppressWarnings("resource")
	public static void readInput(){
		System.out.println("  I:\tInsertion Sort");
		System.out.println("  M:\tMergeSort");
		System.out.println("  Q:\tQuickSort");
		System.out.println("  S:\tSTLSort");
	    System.out.println("Enter sorting algorithm:");
	    Scanner reader = new Scanner(System.in);
	    sortAlg = reader.next();
	    System.out.println(sortAlg);
		String sortAlgName = "";
		
		if(sortAlg.equals("I"))
			sortAlgName = "Insertion Sort";
		else if(sortAlg.equals("M"))
			sortAlgName = "MergeSort";
		else if(sortAlg.equals("Q"))
			sortAlgName = "QuickSort";
		else if(sortAlg.equals("S"))
			sortAlgName = "STLSort";
		else {
			System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
			System.exit(1);
		}
		System.out.println("Enter input size: ");
	    size = reader.nextInt();
		System.out.println("\nSorting Algorithm: " + sortAlgName);
        System.out.println("\nInput Size = "  + size);
	}
	
	/******************************************************************************/

	public static void GenerateSortedData(int data[], int size)
	{
		int i;
		
		for(i=0; i<size; i++)
			data[i] = i * 3 + 5;
	}
	/*****************************************************************************/
	public static void GenerateNearlySortedData(int data[], int size)
	{
		int i;
		
		GenerateSortedData(data, size);
		
		for(i=0; i<size; i++)
			if(i % 10 == 0)
				if(i+1 < size)
					data[i] = data[i+1] + 7;
	}
	/*****************************************************************************/

	public static void GenerateReverselySortedData(int data[], int size)
	{
		int i;
		
		for(i = 0; i < size; i++)
			data[i] = (size-i) * 2 + 3;
	}
	/*****************************************************************************/

	public static void GenerateRandomData(int data[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			data[i] = new Random().nextInt(10000000);
	}
	/*****************************************************************************/

	
	public static void Sort(int[] data, int size,  String string)
	{

		System.out.print("\n"+string+":");
		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

		// Sorting is about to begin ... start the timer!
		long start_time = System.nanoTime();
			if (sortAlg.equals("I"))
			{
			InsertionSort(data, size);
			}
			else if (sortAlg.equals("M"))
			{
			MergeSort(data, 0, size-1);
			}
			else if (sortAlg.equals("Q"))
			{
			QuickSort(data, 0, size-1);
			}
			else if (sortAlg.equals("S"))
			{
			STLSort(data, size);
			}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		
		double elapsed = System.nanoTime()-start_time;
		elapsed=elapsed/1000000;


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
	
	/*****************************************************************************/

	public static boolean IsSorted(int data[], int size)
	{
		int i;
		
		for(i=0; i<(size-1); i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
	/*****************************************************************************/

	public static void InsertionSort(int data[], int size)
	{
		//Write your code here
		//System.out.println("InserionSort");
      for (int i=1; i<size; i++){
         int k = data[i];
         int j = i-1;
         
         while(j>=0 && data[j] > k){
            data[j+1] = data[j];
            j = j-1;
         }
         data[j+1] = k;
		}
	}
	/*****************************************************************************/

	public static void MergeSort(int data[], int lo, int hi)
	{
		//Write your code here
		//You may create other functions if needed 
		//System.out.println("MergeSort");
      if(lo<hi){
         int q = (lo + hi)/2;
         
         MergeSort(data, lo, q);
         MergeSort(data, q+1, hi);
         
         Merge(data, lo, q, hi);
      }
	}
   
   public static void Merge(int data[], int l, int m, int r)
   {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        for (int i=0; i<n1; ++i)
            L[i] = data[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = data[m + 1+ j];
 
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                data[k] = L[i];
                i++;
            }
            else
            {
                data[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            data[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            data[k] = R[j];
            j++;
            k++;
        }
   }
         
      
	/*****************************************************************************/
	public static void QuickSort(int data[], int lo, int hi){
	   if (lo < hi) {
              int p = rPartition(data,lo, hi);
              if(data.length > 40){
              QuickSort(data,lo ,p - 1);
              QuickSort(data,p + 1, hi);
              }
              //below is the code to call insertion sort for the last 40 elements
              /*
              else{
               InsertionSort(data, data.length);
               }*/
          }
      }
      public static int rPartition(int[] A, int lo, int hi) {
          int I = randomNumber(lo,hi);
          swap(hi,I, A);
          return partition(A,lo,hi);
      }

      public static int randomNumber(int lo, int hi) {
          return (int)Math.floor(Math.random() * (hi - lo + 1) + lo);
      }

      public static int partition(int[] A,int lo,int hi) {
          int V = A[hi];
          int I = lo;

          for (int j = lo; j < hi; j++) {
              if (A[j] <= V) {
                  swap(I,j, A);
                  I = I + 1;
              }
          }
          swap(I,hi, A);
          return I;
      }
      //median of three
       public static void quickSort(int[] A, int lo, int hi)
       {
          if (lo >= hi)
          return;
   
          int m = lo + (hi - lo) / 2;
          int x = A[m];
   
          while (lo <= hi) {
               while (A[lo] < x){
                    lo++;
               }
         while (A[hi] > x) {
                    hi--;
               }
   
               if (lo <= hi) {
                    int temp = A[lo];
                    A[lo] = A[hi];
                    A[hi] = temp;
                    lo++;
                    hi--;
                }
            }
   
            if (lo < hi)
               QuickSort(A, lo, hi);
   
            if (hi > lo)
                 QuickSort(A, lo, hi);
       }
	/*****************************************************************************/

	public static void STLSort(int data[], int size)
	{
		//Write your code here
		//Your code should simply call the STL sorting function  
		//System.out.println("STLSort");
      Arrays.sort(data);
		
	}
	/*****************************************************************************/
	
	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}
	
	/*****************************************************************************/
	
	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}

}

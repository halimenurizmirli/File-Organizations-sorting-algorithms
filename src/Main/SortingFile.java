package Main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class SortingFile {
	
	static File file;
	static FileWriter fileWriter;
	static BufferedWriter bufferedWriter;
	
	
	final static int numberOfData = 1000;
	

	public static void main(String[] args) {
		
		try {
			file = new File("Numbers.txt");
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			
			Random random = new Random();
			
			
			for(int i=0; i<numberOfData; i++) {
				bufferedWriter.write(random.nextInt()+"\n");
			}
			
			System.out.println("Dosyalar oluşturuldu.");
			
			bufferedWriter.close();
			
			int arrayOfData1[]=new int[numberOfData];
			int arrayOfData2[]=new int[numberOfData];
			int arrayOfData3[]=new int[numberOfData];
			int index=0;
			Scanner scan = new Scanner(file);
			int number;
			while(scan.hasNextInt()) {
				number=scan.nextInt();
				arrayOfData1[index] = number;
				arrayOfData2[index] = number;
				arrayOfData3[index] = number;
				index++;
			}
			scan.close();

		System.out.println("Selection ile siralama: ");
		long startTime = System.nanoTime();
		selectionSort(arrayOfData1);
		long endTime = System.nanoTime();
		System.out.println("Gecen süre: "+ ((double)(endTime-startTime)/1000000000));
		
		System.out.println("\nInsertion ile siralama: ");
		startTime = System.nanoTime();
		insertionSort(arrayOfData2);
		endTime = System.nanoTime();
		System.out.println("Gecen süre: "+ ((double)(endTime-startTime)/1000000000));
		
		System.out.println("\nMerge ile siralama: ");
		startTime = System.nanoTime();
		mergeSort(arrayOfData3, 0, numberOfData-1);
		endTime = System.nanoTime();
		System.out.println("Gecen süre: "+ ((double)(endTime-startTime)/1000000000));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	static public void selectionSort(int arrayOfData[]) throws IOException {
		int min;
		for(int i=0; i<numberOfData-1; i++) {
			min=i;
			for(int j=i+1; j<numberOfData; j++) {
				if(arrayOfData[j]<arrayOfData[min]) {
					min=j;
				}	
			}
			
			int temp = arrayOfData[i];
			arrayOfData[i]=arrayOfData[min];
			arrayOfData[min]=temp;
			
		}	
		
	}
	public static void insertionSort(int arrayOfData[]) throws IOException {
		int key;
		
		for(int i=1; i<numberOfData; i++) {
			key=arrayOfData[i];
			int j=i-1;
			
			while (j >= 0 && arrayOfData[j] > key) {
		        arrayOfData[j + 1] = arrayOfData[j];
		        j = j - 1;
		    }
			
		    arrayOfData[j + 1] = key;
		}
			
	}
	public static void merge(int arrayOfData[], int firstIndex, int middleIndex, int lastIndex) {
		int n1 = middleIndex-firstIndex+1;
		int n2 = lastIndex-middleIndex;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for (int i = 0; i < n1; ++i) {
            L[i] = arrayOfData[firstIndex + i];
		}
        for (int j = 0; j < n2; ++j) {
            R[j] = arrayOfData[middleIndex + 1 + j];
        }
        
        int i = 0, j = 0;
		
        int t=firstIndex;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arrayOfData[t] = L[i];
                i++;
            }
            else {
                arrayOfData[t] = R[j];
                j++;
            }
            t++;
        }
        
        while (i < n1) {
            arrayOfData[t] = L[i];
            i++;
            t++;
        }
        
        while (j < n2) {
            arrayOfData[t] = R[j];
            j++;
            t++;
        }
	}
	
	public static void mergeSort(int arrayOfData[], int firstIndex, int lastIndex) {
		if(firstIndex<lastIndex) {
			int middleIndex = (firstIndex+lastIndex)/2;
			mergeSort(arrayOfData, firstIndex, middleIndex); //Sol array
			mergeSort(arrayOfData, middleIndex+1, lastIndex); //Sağ array
			merge(arrayOfData, firstIndex, middleIndex, lastIndex);
		}
	}
	

}

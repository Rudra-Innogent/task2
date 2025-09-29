import java.util.*;
class Performance{
	public static long insertion(List<Integer> l, int size){
		long startTime=System.nanoTime();
		for(int i=0;i<size;){
			l.add(100);
			i++;
		}
		long endtime=System.nanoTime();
		return (endtime-startTime)/100000;
		
		
	}
	
	public static long deletion(List<Integer> l, int size){
		long startTime=System.nanoTime();
		for(int i=0;i<size;){
			l.remove(0);
			i++;
		}
		long endtime=System.nanoTime();
		return (endtime-startTime)/100000;
			
	}
public static void main(String rudra[]){
	
	List<Integer> l=new ArrayList<>();
	int tenK=10000;
	int hundredK=100000;
	int fiftyK=50000;
	System.out.print("Insertion Performance for 10k => ");
    System.out.println(insertion(l,tenK)+" miloseconds");
	
	System.out.print("Insertion Performance for 50k => ");
    System.out.println(insertion(l,fiftyK)+" miloseconds");
	
	System.out.print("Insertion Performance for 100k => ");
    System.out.println(insertion(l,hundredK)+" miloseconds");


    System.out.print("Deletion Performance for 10k => ");
    System.out.println(deletion(l,tenK)+" miloseconds");
	
	System.out.print("Deletion Performance for 50k => ");
    System.out.println(deletion(l,fiftyK)+" miloseconds");
	
	System.out.print("Deletion Performance for 100k => ");
    System.out.println(deletion(l,hundredK)+" miloseconds");

}
}
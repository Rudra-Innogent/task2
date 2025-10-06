// Even odd Task with sleep() method only
class PerformOperation{
	public synchronized void printEven(String even){
		Runnable r1=()->{
			for(int i=1;i<=100;i++){
				if(i%2==0)System.out.println(Thread.currentThread().getName()+" "+i);
				try{Thread.sleep(100);}catch(Exception e){e.printStackTrace();}
				
			
			}
		};
		Thread t1=new Thread(r1,even);
		t1.start();
	}
	
	public synchronized void printOdd(String odd){
		Runnable r2=()->{
			for(int i=1;i<=100;i++){
				if(i%2==1)System.out.println(Thread.currentThread().getName()+" "+i);
					try{Thread.sleep(100);}catch(Exception e){ e.printStackTrace(); }
			}
		};
		Thread t1=new Thread(r2,odd);
		t1.start();
	
	}
}
class complete{
	
	public static void main(String rudra[])throws InterruptedException{
		
		PerformOperation p=new PerformOperation();
		p.printEven("Even ");
		p.printOdd("Odd ");
	}
}
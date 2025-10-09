import java.util.concurrent.locks.*;
import java.io.*;

class SharedConfig {
    
    private final ReentrantReadWriteLock rwLock;
	SharedConfig(ReentrantReadWriteLock rwLock){this.rwLock=rwLock;}

    public void readindData(String readerName) {
        rwLock.readLock().lock();
	try (BufferedReader reader=new BufferedReader(new FileReader("abc.txt"))) {
		String line;
		System.out.println(Thread.currentThread().getName() + " is READING from file: ");
		while ((line=reader.readLine())!=null) {
        System.out.println(line);
    }
	} 
	catch(IOException e){e.printStackTrace(); } 
	finally { rwLock.readLock().unlock(); }
}
  
    public void writingData(String writerName, String newData) {
        rwLock.writeLock().lock();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("abc.txt",true))) {
		String data="\n Written by "+Thread.currentThread().getName()+"\n";
		writer.write(data);
		System.out.println(Thread.currentThread().getName()+"\n WROTE to file: \n"+data);
	} 
		catch(IOException e){ e.printStackTrace(); }
		finally{ rwLock.writeLock().unlock(); }

    }
}

public class ReadWriteExample {
    public static void main(String[] args) {

        SharedConfig sharedConfig=new SharedConfig(new ReentrantReadWriteLock());
       
        Runnable readerTask=()-> {
            String threadName = Thread.currentThread().getName();
            sharedConfig.readindData(threadName);
        };
    
        Runnable writerTask=()-> {
            String threadName=Thread.currentThread().getName();
            sharedConfig.writingData(threadName,"Updated Config by "+threadName);
        };
      
        Thread r1 = new Thread(readerTask, "Reader-1");
        Thread r2 = new Thread(readerTask, "Reader-2");
        Thread r3 = new Thread(readerTask, "Reader-3");

        Thread w1 = new Thread(writerTask, "Writer-1");
  
        w1.start();

        try { Thread.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}
		
        r1.start();
        r2.start();
        r3.start();
    }
}

import java.sql.*;
import java.io.*;
import java.util.concurrent.*;

class DatabaseConnection implements Callable<String>
{
	private final CountDownLatch latch;
	private  Connection con;
	
	DatabaseConnection(CountDownLatch latch){this.latch=latch;}
	
	public String call(){
		
		try{ 
		Thread.currentThread().setName("Database Thread");
		System.out.println(Thread.currentThread().getName()+" trying to stablish Database Connection");		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con=DriverManager.getConnection("jdbc:mysql:///innogent","root","root");
		Statement st=con.createStatement();
		String id="Rudra";
		String pwd="1234";
		int x=st.executeUpdate("insert into login values('"+id+"','"+pwd+"')");
		
		if(x!=0)System.out.println("login id and password inserted successfully");
		else System.out.println("insertion into login table failed");
		
		}
		catch(Exception e){e.printStackTrace();}
		
		finally{try{con.close();}catch(Exception e){}latch.countDown();}
		
	return "connection stablished Successfully";
	}
	}
class WriteFile implements Callable<String>
{
	private File f;
	private FileOutputStream fi;
	private final CountDownLatch latch;
	
	WriteFile(CountDownLatch latch){this.latch=latch;}
	
	public String call() throws Exception{
		try{
			
		Thread.currentThread().setName("Writing Thread");
		System.out.println(Thread.currentThread().getName()+" writing data on file");				
		f=new File("abc.txt");
		fi=new FileOutputStream(f,true);
		fi.write('A');
		System.out.println("Data written on file readed successfully ");
		
		}
		
		catch(IOException e){e.printStackTrace();}
		
		finally{
		fi.close();
		latch.countDown();}	
		return "Written data on file successfully";
	}
	
}
class ReadFile implements Callable<String>
{
	private  File f;
	private FileInputStream fi;
	private final CountDownLatch latch;
	
	ReadFile(CountDownLatch latch){this.latch=latch;}
	
	public String call() throws Exception{
		try{
		Thread.currentThread().setName("Reading Thread");		
		System.out.println(Thread.currentThread().getName()+" reading data from file");				
	    f=new File("abc.txt");
		fi=new FileInputStream(f);
		char c=(char)fi.read();
		System.out.println("Data "+c+" from file readed successfully ");
		}
		
		catch(IOException e){e.printStackTrace();}
		
		finally{
		fi.close();
		latch.countDown();}		
		return "readed data from file successfully";
	}	
}
class LatchExample{
	public static void main(String[] args)throws Exception {
		int num=3;
		
		ExecutorService executorService=Executors.newFixedThreadPool(num);
		CountDownLatch latch=new CountDownLatch(num);
		
		System.out.println("main thread waiting starts");
		executorService.submit(new DatabaseConnection(latch));
		executorService.submit(new WriteFile(latch));
		executorService.submit(new ReadFile(latch));
		
		try{latch.await();}catch(Exception e ){e.printStackTrace();}
		
		System.out.println("main thread waiting ends");
		executorService.shutdown();
	}
}
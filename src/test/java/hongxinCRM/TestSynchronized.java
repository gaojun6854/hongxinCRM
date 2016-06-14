package hongxinCRM;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSynchronized implements Runnable {
	/*private int threadNo;

	public TestSynchronized(int threadNo) {
		this.threadNo = threadNo;
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			new TestSynchronized(i).start();
			Thread.sleep(1);
		}
	}

	public static synchronized void abc(int threadNo){
		for (int i = 0; i < 1000; i++) {
			System.out.println("NO-" + threadNo + ":" + i);
		}
	}
	public void run() {
		abc(threadNo);
	}*/
	
	
	
	//////////////////////////////////////////////////////////
	/*private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args)  {
        final TestSynchronized test = new TestSynchronized();
         
        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }  
     
    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
                thread.sleep(400);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
    }
}*/
	
	
	/*public void m4t1() { 
        synchronized(this) { 
             int i = 5; 
             while( i-- > 0) { 
                  System.out.println(Thread.currentThread().getName() + " : " + i); 
                  try { 
                       Thread.sleep(500); 
                  } catch (InterruptedException ie) { 
                  } 
             } 
        } 
   } 
   public synchronized  void  m4t2() { 
	   int i = 5; 
        while( i-- > 0) { 
             System.out.println(Thread.currentThread().getName() + " : " + i); 
             try { 
                  Thread.sleep(500); 
             } catch (InterruptedException ie) { 
             } 
        } 
   } 
   public static void main(String[] args) { 
        final TestSynchronized myt2 = new TestSynchronized(); 
        Thread t1 = new Thread(  new Runnable() {  public void run() {  myt2.m4t1();  }  }, "t1"  ); 
        Thread t2 = new Thread(  new Runnable() {  public void run() { myt2.m4t2();   }  }, "t2"  ); 
        t1.start(); 
        t2.start(); 
   } 
	
	*/
	   private byte[] lock = new byte[0];  // 特殊的instance变量
	   public void method()
	   {
	      synchronized(lock) {
	    	  for (int i = 0; i < 500; i++) {
				System.out.println(Thread.currentThread().getName()+"--"+i);
			}
	      }
	   }

	   public void run() {
		   method();
	   }
	  
	 public static void main(String[] args) {
		 TestSynchronized test=new TestSynchronized();
	        Thread t1 = new Thread(test); 
	        Thread t2 = new Thread(test); 
	        t1.start(); 
	        t2.start(); 
		}
}

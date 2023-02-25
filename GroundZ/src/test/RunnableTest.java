package test;

import t4.CodeRunner;
import t5.ClientSystem;
import t5.ServerPacket;

public class RunnableTest {
	private CodeRunner jEngine;
	private volatile boolean running = true;
    private volatile boolean paused = false;
    private Object pauseLock;
    private ClientSystem cSys;
	public class Example implements Runnable {
	    
	    public Example() {
	    }
	    
	    @Override
	    public void run() {
	        while (running) {
	            synchronized (pauseLock) {
	            	System.out.println(cT.getName()+" == "+Thread.currentThread().getName());
	            	try {
	    				Thread.sleep(100);
	    			} catch (InterruptedException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	            	
	            	
	                if (!running) { // may have changed while waiting to
	                    break;
	                }
	                if (paused) {
	                    try {
	                        pauseLock.wait();
	                    } catch (InterruptedException ex) {
	                        break;
	                    }
	                    if (!running) { // running might have changed since we paused
	                        break;
	                    }
	                }
	            }
	            // Your code here
	        }
	    }

	    public void stop() {
	        running = false;
	        // you might also want to interrupt() the Thread that is 
	        // running this Runnable, too, or perhaps call:
	        resume();
	        // to unblock
	    }

	    public void pause() {
	        // you may want to throw an IllegalStateException if !running
	        paused = true;
	    }

	    public void resume() {
	        synchronized (pauseLock) {
	            paused = false;
	            pauseLock.notifyAll(); // Unblocks thread
	        }
	    }
	}
	
	public static void main(String[] args) {
		RunnableTest r = new RunnableTest();
		r.start();
	}
	
	public void start() {
    	pauseLock = new Object();
    	cSys = new ClientSystem();
    	jEngine = new CodeRunner(cSys, false);
    	
    	jEngine.handel("public class Main{\n"
    			+ "public static Thread codeThread = Thread.currentThread();"
    			+ "    public static void main() {\n"
    			+ "        //Output to client\n"
    			+ "        System.out.println(\"Hello\");\n"
    			+ "        while(true){\n"
    			+ "            try {\n"
    			+ "				Thread.sleep(50);\n"
    			+ "			} catch (InterruptedException e) {\n"
    			+ "				// TODO Auto-generated catch block\n"
    			+ "				e.printStackTrace();\n"
    			+ "			}\n"
    			+ "            System.out.println(\"To the sky\");\n"
    			+ "        }\n"
    			+ "    }\n"
    			+ "}", false);
    	pauseLock = jEngine.bindings.get("codeThread");
		
		
		Example ex = new Example();
		Thread t = new Thread(ex);
		t.start();
		
//		ex.pause();
		
		
		
		//ex.resume();
		Thread cT = (Thread) jEngine.bindings.get("codeThread");
//		//System.out.println(cT.getName()+" :: "+t.getName()+" == "+Thread.currentThread().getName());
//		try {
//			cT.wait(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		ex.stop();
		
		
		
//		while(true) {
//			ex.pause();
//			int i = (int) (Math.random() * 500);
//			System.out.println("=== wait ===  "+ i);
//			try {
//				t.sleep(i);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			ex.resume();
//			try {
//				t.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}

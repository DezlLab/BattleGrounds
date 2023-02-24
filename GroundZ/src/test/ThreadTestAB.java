package test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTestAB {
	private class A extends Thread {
	    private List<String> data = new ArrayList<>();
	    
	    int i = 0;
	    public void run() {
	        while (true) {
	        	data.add("Data Stuff"+i);
	        	i++;
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void setWait() {
	        try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public List<String> getData() {
	        return data;
	    }
	}

	private class B extends Thread {
	    private A a;

	    public B(A a) {
	        this.a = a;
	    }

	    public void run() {
	        while (true) {
	            // Sleep for a while to simulate waiting for A to finish processing
	        	System.out.println("B cycle");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            // Stop A's processing
	            a.setWait();

	            // Print A's processed data
	            List<String> data = a.getData();
	            for (String item : data) {
	                System.out.println(item);
	            }

	            // Start A's processing
	            a.notify();
	        }
	    }
	}

    public static void main(String[] args) {
        ThreadTestAB x = new ThreadTestAB();
        x.test();
    }
    
    public void test() {
        A a = new A();
        a.start();

        B b = new B(a);
        b.start();
    }
}

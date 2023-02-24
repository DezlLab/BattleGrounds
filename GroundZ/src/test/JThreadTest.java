package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JThreadTest {
	public class MyThread extends Thread {

	    Object lock;

	    @Override
	    public void run() {

	        JFrame fr = new JFrame("Anothing");
	        JButton btn = new JButton("Next");
	        btn.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                synchronized (lock) {
	                    lock.notify();
	                }

	            }
	        });
	        fr.setLayout(new FlowLayout());
	        fr.add(btn);
	        fr.setSize(400, 400);
	        fr.setVisible(true);
	    }
	}
	
	public void main() {

        final Object lock = new Object();


        MyThread t = new MyThread();
        t.lock = lock;
        t.run();

        while (true) {
            try {
                synchronized (lock) {
                    lock.wait();
                }
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	
    public static void main(String[] args) {
    	JThreadTest j = new JThreadTest();
    	j.main();
    }

}
package test;

public class ThreadTest {
	
	
	
	public static void main(String[] args) {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("sus");
		}
	}
	
	
}

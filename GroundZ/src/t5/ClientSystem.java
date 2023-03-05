package t5;

public class ClientSystem{

	private String output;
	
	public ClientSystem() {
		this.output = "";
	}
	
	public void print(String output) {
		System.out.println(output);
		this.output += output;
	}
	
	public void println(String output) {
		System.out.println(output);
		this.output += output + '\n';
	}
	
	public String stripString() {
		String result = output;
		this.output = "";
		return result;
	}
}

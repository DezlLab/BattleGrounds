package codeSupport;

import java.util.ArrayList;

public class CodeReader
{
	private String name;
	private ArrayList<ReadTask> readTasks;
	
	public CodeReader(String name, ArrayList<ReadTask> readTasks) {
		this.name = name;
		this.readTasks = readTasks;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<CodeBlock> readCode(String code) {
		ArrayList<CodeBlock> codeBlocks = new ArrayList<CodeBlock>();
		for(ReadTask readTask : readTasks) {
			codeBlocks.addAll(readTask.readCode(code));
		}
		return codeBlocks;
	}
}

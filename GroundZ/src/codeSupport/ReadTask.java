package codeSupport;

import java.util.ArrayList;

public class ReadTask
{
	private ArrayList<CodeBlockDescriptor> codeBlockDescriptors;
	
	public ReadTask(ArrayList<CodeBlockDescriptor> codeBlockDescriptors) {
		this.codeBlockDescriptors = codeBlockDescriptors;
	}
	
	public ArrayList<CodeBlock> readCode(String code) {
		ArrayList<CodeBlock> codeBlocks = new ArrayList<CodeBlock>();
		int readPos = 0;
		while(readPos < code.length()) {
			for(CodeBlockDescriptor descriptor : codeBlockDescriptors) {
				descriptor.read(code.charAt(readPos), readPos);
				if(descriptor.createNewBlock()) {
					codeBlocks.add(descriptor.newCodeBlock());
				}
			}
			readPos++;
		}
		return codeBlocks;
	}
}

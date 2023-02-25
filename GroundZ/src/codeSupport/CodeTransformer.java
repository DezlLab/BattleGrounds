package codeSupport;

import java.util.ArrayList;

import codeSupport.CodeBlockDescriptor.IgnoreTypes;

public class CodeTransformer
{
	private ArrayList<CodeReader> codeReaders;
	private String codeBeforTransform;
	
	public CodeTransformer() {
		ArrayList<ReadTask> readTasks = new ArrayList<ReadTask>() {
			{
				add(new ReadTask(new ArrayList<CodeBlockDescriptor>() {
					{
						add(new CodeBlockDescriptor(new String[] {"\"", "\""}, 0, "String", null, IgnoreTypes.ignoreAllInner));
						add(new CodeBlockDescriptor(new String[] {"//", "\n"}, 1, "LineComment", null, IgnoreTypes.ignoreAllInner));
						add(new CodeBlockDescriptor(new String[] {"/*", "*/"}, 1, "MultiLineComment", null, IgnoreTypes.ignoreAllInner));
					}
				}));
			}
		};
		CodeReader javaTest = new CodeReader("default-string-comments", readTasks);
	}
	
	public void addCodeReader(CodeReader codeReader) {
		codeReaders.add(codeReader);
	}
	
	public String transform(String code, String codeReaderName) {
		for(CodeReader reader : codeReaders) {
			if(reader.getName().equals(codeReaderName)) {
				ArrayList<CodeBlock> codeBlocks = reader.readCode(codeReaderName);
				System.out.println();
				codeBeforTransform = code;
				String newCode = "";
				for(CodeBlock codeBlock : codeBlocks) {
					code = code.substring(0, codeBlock.getStartPos()) + code.substring(codeBlock.getEndPos());
				}
			}
		}
		return code;
	}
	
	
	
//	public static void main(String[] args) {
//		ArrayList<ReadTask> readTasks = new ArrayList<ReadTask>() {
//			{
//				add(new ReadTask(new ArrayList<CodeBlockDescriptor>() {
//					{
//						add(new CodeBlockDescriptor(new String[] {"\"", "\""}, 0, "LineComment", " \n\t".toCharArray()));
//						add(new CodeBlockDescriptor(new String[] {"class", "{", "}"}, 1, "class", " \n\t".toCharArray()));
//					}
//				}));
//			}
//		};
//		CodeReader javaTest = new CodeReader("java", readTasks);
//		System.out.println(javaTest.readCode("  class{\t\n}   \"  \"  "));
//		
//		System.out.println("done");
//	}
	
//	public void replaceBlock(String str) {
//	code = code.substring(0, startPos) + str + code.substring(endPos);
//}
//
//public void addBevorBlock(String str) {
//	code = code.substring(0, startPos) + str + code.substring(startPos);
//}
//
//public void addInBlock(String str) {
//	code = code.substring(0, innerPos) + str + code.substring(endPos);
//}
//
}

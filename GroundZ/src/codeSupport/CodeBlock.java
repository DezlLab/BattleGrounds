package codeSupport;

public class CodeBlock {
	private int startPos;
	private int innerPos;
	private int endPos;
	private String type;

	public CodeBlock(int startPos, int innerPos, int endPos, String type) {
		this.startPos = startPos;
		this.innerPos = innerPos;
		this.endPos = endPos;
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getStartPos() {
		return this.startPos;
	}
	
	public int getInnerPos() {
		return this.innerPos;
	}
	
	public int getEndPos() {
		return this.endPos;
	}

	@Override
	public String toString() {
		return "CodeBlock [startPos=" + startPos + ", innerPos=" + innerPos + ", endPos=" + endPos + ", type=" + type
				+ "]";
	}
}

package codeSupport;

import java.awt.Color;

public class CodeBlockDescriptor {
	
	public enum IgnoreTypes{
		ignoreAllInner,
		ignoreChars,
		ignoreAll
	}
	
	private String[] keys;
	private int startKeysLength;
	private String type;
	private char[] ignoredChars;
	private IgnoreTypes ignoreType;
	private Color color;
	private String info;
	
	private boolean createNewBlock;
	private int keysIdx;
	private int stringIdx; 
	
	private int startPos;
	private int innerPos;
	private int endPos;
	
	public CodeBlockDescriptor(String[] keys, int startKeysLength, String type, char[] ignoredChars, IgnoreTypes ignoreType) {
		this(keys, startKeysLength, type, ignoredChars, ignoreType, new Color(200, 200, 255), "no info");
	}
	
	public CodeBlockDescriptor(String[] keys, int startKeysLength, String type, char[] ignoredChars, IgnoreTypes ignoreType,
			Color color, String info) {
		super();
		this.keys = keys;
		this.startKeysLength = startKeysLength;
		this.type = type;
		this.ignoredChars = ignoredChars;
		this.ignoreType = ignoreType;
		this.color = color;
		this.info = info;
		resetRead();
	}

	private void resetRead() {
		this.createNewBlock = false;
		this.keysIdx = 0;
		this.stringIdx = 0;
		this.startPos = -1;//If -1 next read is first read
		this.innerPos = -1;
		this.endPos = -1;
	}
	
	public CodeBlock newCodeBlock() {
		CodeBlock res = new CodeBlock(startPos, innerPos, endPos, type);
		resetRead();
		return res;
	}

	public boolean createNewBlock() {
		return this.createNewBlock;
	}

	public void read(char charInCode, int readPos) {
		boolean read = true;
		for(char ignore : ignoredChars) {
			if(ignore == charInCode) {
				read = false;
			}
		}
		if(read) {
			System.out.println(readPos+">>"+keysIdx+"::"+stringIdx);
			if(keys[keysIdx].charAt(stringIdx) == charInCode) {
				if(startPos == -1) {
					System.out.println("set start");
					this.startPos = readPos;
				}
				stringIdx++;
				if(stringIdx >= keys[keysIdx].length()) {
					stringIdx = 0;
					System.out.println("end of "+keys[keysIdx]+" here : "+keysIdx);
					if(keysIdx == startKeysLength) {
						System.out.println(startKeysLength+":: set inner : "+readPos);
						this.innerPos = readPos;
					}
					keysIdx++;
					System.out.println("nextIdx : "+keysIdx);
				}
			}
			else {
				if(ignoreType != IgnoreTypes.ignoreAll && ignoreType != IgnoreTypes.ignoreAllInner) {
					resetRead();
					System.out.println("reset");
				}
			}
			if(keysIdx >= keys.length) {
				this.endPos = readPos;
				createNewBlock = true;
				System.out.println("create");
				return;
			}
		}
	}
}

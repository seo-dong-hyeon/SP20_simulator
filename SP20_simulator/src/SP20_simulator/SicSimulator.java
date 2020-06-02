package SP20_simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import SP20_simulator.InstTable;
import SP20_simulator.ResourceManager;
import SP20_simulator.SicLoader;

/**
 * �ùķ����ͷμ��� �۾��� ����Ѵ�. VisualSimulator���� ������� ��û�� ������ �̿� ����
 * ResourceManager�� �����Ͽ� �۾��� �����Ѵ�.  
 * 
 * �ۼ����� ���ǻ��� : <br>
 *  1) ���ο� Ŭ����, ���ο� ����, ���ο� �Լ� ������ �󸶵��� ����. ��, ������ ������ �Լ����� �����ϰų� ������ ��ü�ϴ� ���� ������ ��.<br>
 *  2) �ʿ信 ���� ����ó��, �������̽� �Ǵ� ��� ��� ���� ����.<br>
 *  3) ��� void Ÿ���� ���ϰ��� ������ �ʿ信 ���� �ٸ� ���� Ÿ������ ���� ����.<br>
 *  4) ����, �Ǵ� �ܼ�â�� �ѱ��� ��½�Ű�� �� ��. (ä������ ����. �ּ��� ���Ե� �ѱ��� ��� ����)<br>
 * 
 * <br><br>
 *  + �����ϴ� ���α׷� ������ ��������� �����ϰ� ���� �е��� ������ ��� �޺κп� ÷�� �ٶ��ϴ�. ���뿡 ���� �������� ���� �� �ֽ��ϴ�.
 */
public class SicSimulator {
	ResourceManager rMgr;
	SicLoader sLd;
	InstTable instTable;
	int memoryIdx = 9;
	
	ArrayList<String> log;

	public SicSimulator(ResourceManager resourceManager, SicLoader sicloader) {
		// �ʿ��ϴٸ� �ʱ�ȭ ���� �߰�
		this.rMgr = resourceManager;
		this.sLd = sicloader;
		this.instTable = new InstTable("inst.data");
		
		log = new ArrayList<String>();
	}

	/**
	 * ��������, �޸� �ʱ�ȭ �� ���α׷� load�� ���õ� �۾� ����.
	 * ��, object code�� �޸� ���� �� �ؼ��� SicLoader���� �����ϵ��� �Ѵ�. 
	 */
	public void load(File program) {
		/* �޸� �ʱ�ȭ, �������� �ʱ�ȭ ��*/
		sLd.load(program);
		
		
	}

	/**
	 * 1���� instruction�� ����� ����� ���δ�. 
	 */
	public void oneStep() {
		//rMgr.operatorCnt++;
		
		/*String objectCode = new String(rMgr.getMemory(memoryIdx, 6));
		System.out.println(objectCode);
		rMgr.objectCodeList.add(objectCode);
		memoryIdx += 6;*/
		
		//String code = new String(rMgr.getMemory(memoryIdx, 2));
		rMgr.operatorCnt++;
		char[] codes = rMgr.getMemory(memoryIdx, 3);
		int code0 = hexToDec(codes[0]);
		int code1 = hexToDec(codes[1]);
		int code2 = hexToDec(codes[2]);
		
		String code0Binary = Integer.toBinaryString(code0);
		String code1Binary = Integer.toBinaryString(code1);
		String code2Binary = Integer.toBinaryString(code2);
		String opeartor = "";
		String opcode = "";

		if(code1Binary.charAt(code1Binary.length()-1) == '1') {
			code1 -= 1;
		}
		if(code1Binary.length() >=2 && code1Binary.charAt(code1Binary.length()-2) == '1') {
			code1 -= 2;
		}
		
		opcode += Integer.toHexString(code0).toUpperCase();
		opcode += Integer.toHexString(code1).toUpperCase();
		opeartor += instTable.getName(opcode);
		addLog(opeartor);
		System.out.println(opeartor +" "+opcode);
		if(instTable.getFormat(opeartor) == 2) {
			rMgr.objectCodeList.add(new String(rMgr.getMemory(memoryIdx, 4)));
			memoryIdx += 4;
		}
		else if(code2 % 2 != 0) {
			rMgr.objectCodeList.add(new String(rMgr.getMemory(memoryIdx, 8)));
			memoryIdx += 8;
		}
		else {
			rMgr.objectCodeList.add(new String(rMgr.getMemory(memoryIdx, 6)));
			memoryIdx += 6;
		}
		
		
	}
	
	/**
	 * ���� ��� instruction�� ����� ����� ���δ�.
	 */
	public void allStep() {
	}
	
	/**
	 * �� �ܰ踦 ������ �� ���� ���õ� ����� ���⵵�� �Ѵ�.
	 */
	public void addLog(String log) {
		rMgr.log.add(log);
	}	
	
	public int hexToDec(char hex) {
		if(hex >= 'A') {
			return hex - 'A' + 10;
		}
		else {
			return hex - '0';
		}
	}
	
	private String getHexToDec(String hex) {
		long v = Long.parseLong(hex, 16);  
		return String.valueOf(v);
	}
}

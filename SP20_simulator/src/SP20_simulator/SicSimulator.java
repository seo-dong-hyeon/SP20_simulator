package SP20_simulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import SP20_simulator.InstTable;
import SP20_simulator.ResourceManager;
import SP20_simulator.SicLoader;

/**
 * 시뮬레이터로서의 작업을 담당한다. VisualSimulator에서 사용자의 요청을 받으면 이에 따라
 * ResourceManager에 접근하여 작업을 수행한다.  
 * 
 * 작성중의 유의사항 : <br>
 *  1) 새로운 클래스, 새로운 변수, 새로운 함수 선언은 얼마든지 허용됨. 단, 기존의 변수와 함수들을 삭제하거나 완전히 대체하는 것은 지양할 것.<br>
 *  2) 필요에 따라 예외처리, 인터페이스 또는 상속 사용 또한 허용됨.<br>
 *  3) 모든 void 타입의 리턴값은 유저의 필요에 따라 다른 리턴 타입으로 변경 가능.<br>
 *  4) 파일, 또는 콘솔창에 한글을 출력시키지 말 것. (채점상의 이유. 주석에 포함된 한글은 상관 없음)<br>
 * 
 * <br><br>
 *  + 제공하는 프로그램 구조의 개선방법을 제안하고 싶은 분들은 보고서의 결론 뒷부분에 첨부 바랍니다. 내용에 따라 가산점이 있을 수 있습니다.
 */
public class SicSimulator {
	ResourceManager rMgr;
	SicLoader sLd;
	InstTable instTable;
	int memoryIdx = 9;
	
	ArrayList<String> log;

	public SicSimulator(ResourceManager resourceManager, SicLoader sicloader) {
		// 필요하다면 초기화 과정 추가
		this.rMgr = resourceManager;
		this.sLd = sicloader;
		this.instTable = new InstTable("inst.data");
		
		log = new ArrayList<String>();
	}

	/**
	 * 레지스터, 메모리 초기화 등 프로그램 load와 관련된 작업 수행.
	 * 단, object code의 메모리 적재 및 해석은 SicLoader에서 수행하도록 한다. 
	 */
	public void load(File program) {
		/* 메모리 초기화, 레지스터 초기화 등*/
		sLd.load(program);
		
		
	}

	/**
	 * 1개의 instruction이 수행된 모습을 보인다. 
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
	 * 남은 모든 instruction이 수행된 모습을 보인다.
	 */
	public void allStep() {
	}
	
	/**
	 * 각 단계를 수행할 때 마다 관련된 기록을 남기도록 한다.
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

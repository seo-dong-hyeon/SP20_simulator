package SP20_simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * SicLoader는 프로그램을 해석해서 메모리에 올리는 역할을 수행한다. 이 과정에서 linker의 역할 또한 수행한다. 
 * <br><br>
 * SicLoader가 수행하는 일을 예를 들면 다음과 같다.<br>
 * - program code를 메모리에 적재시키기<br>
 * - 주어진 공간만큼 메모리에 빈 공간 할당하기<br>
 * - 과정에서 발생하는 symbol, 프로그램 시작주소, control section 등 실행을 위한 정보 생성 및 관리
 */
public class SicLoader {
	ResourceManager rMgr;
	int currentSection = 0;
	int startADDR = 0;
	int memoryIdx = 0;
	
	public SicLoader(ResourceManager resourceManager) {
		// 필요하다면 초기화
		resourceManager.initializeResource();
		setResourceManager(resourceManager);
	}

	/**
	 * Loader와 프로그램을 적재할 메모리를 연결시킨다.
	 * @param rMgr
	 */
	public void setResourceManager(ResourceManager resourceManager) {
		this.rMgr=resourceManager;
	}
	
	/**
	 * object code를 읽어서 load과정을 수행한다. load한 데이터는 resourceManager가 관리하는 메모리에 올라가도록 한다.
	 * load과정에서 만들어진 symbol table 등 자료구조 역시 resourceManager에 전달한다.
	 * @param objectCode 읽어들인 파일
	 */
	public void load(File objectCode){
		try{
            //입력 스트림 생성
            FileReader filereader = new FileReader(objectCode);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
            	if(!line.equals("")) {
            		analyze(line);
        		}
            }

            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
		
		/*for(int i=0; i<memoryIdx +10; i++) {
			System.out.print(rMgr.memory[i]);
			if(i % 10 == 0) {
				System.out.println();
			}
		}*/
	};
	
	public void analyze(String line) {
		switch(line.charAt(0)) {
		case 'H' :
			System.out.println(line);
			//H와 관련된 정보를 Resource manager를 통해서 저장
			//필요한 변수와 함수는 자유롭게 선언 가능		
			rMgr.setProgname(line.substring(1, 7));
			rMgr.setProgLength(line.substring(13,19));
			rMgr.setStartADDR(memoryIdx);

			// SYMTAB 등록
			rMgr.symtabList.get(currentSection).
			putSymbol(line.substring(1,7),Integer.parseInt(rMgr.getStartADDR(currentSection), 16));
			break;
			
		case 'T' :	
            rMgr.setMemory(memoryIdx, line.toCharArray(), line.length());
            memoryIdx += line.length() - 9;              
            break;

		case 'M' :
		}

	}
	
	
	
	

}

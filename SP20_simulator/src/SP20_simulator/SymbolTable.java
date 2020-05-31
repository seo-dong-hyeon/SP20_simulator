package SP20_simulator;
import java.util.ArrayList;

/**
 * symbol과 관련된 데이터와 연산을 소유한다.
 * section 별로 하나씩 인스턴스를 할당한다.
 */
public class SymbolTable {
	ArrayList<String> symbolList;
	ArrayList<Integer> locationList;
	// 기타 literal, external 선언 및 처리방법을 구현한다.
	ArrayList<String> extdefList;
	ArrayList<String> extrefList;
	
	/**
	 * 새로운 Symbol을 table에 추가한다.
	 * @param symbol : 새로 추가되는 symbol의 label
	 * @param location : 해당 symbol이 가지는 주소값
	 * 주의 : 만약 중복된 symbol이 putSymbol을 통해서 입력된다면 이는 프로그램 코드에 문제가 있음을 나타낸다. 
	 * 매칭되는 주소값의 변경은 modifySymbol()을 통해서 이루어져야 한다.
	 */
	
	/** 생성자 추가 **/
	/** symbolList를 비롯한 모든 필드 생성 **/
	public SymbolTable() {
		symbolList = new ArrayList<String>();
		locationList = new ArrayList<Integer>();
		extdefList = new ArrayList<String>();
		extrefList = new ArrayList<String>();
	}
	
	/** 매개변수로 온 인덱스의 extdef 변수를 리턴 **/
	public String getExtdef(int index) {
		return extdefList.get(index);
	}
	
	/** 매개변수로 온 인덱스의 extref 변수를 리턴 **/
	public String getExtref(int index) {
		return extrefList.get(index);
	}
	
	public void putSymbol(String symbol, int location) {
		/** 매개변수로 온 symbol과 location을 list에 추가함 **/
		symbolList.add(symbol);
		locationList.add(location);
	}
	
	/**
	 * 기존에 존재하는 symbol 값에 대해서 가리키는 주소값을 변경한다.
	 * @param symbol : 변경을 원하는 symbol의 label
	 * @param newLocation : 새로 바꾸고자 하는 주소값
	 */
	public void modifySymbol(String symbol, int newLocation) {		
		/** symbolList를 순회하다 **/
		/** 변경을 원하는 symbol을 발견하면 **/
		for(int i = 0; i < symbolList.size(); i++) {
			if(symbolList.get(i).equals(symbol)) {
				/** 변경 **/
				locationList.set(i, newLocation);
				break;
			}
		}
	}
	
	/**
	 * 인자로 전달된 symbol이 어떤 주소를 지칭하는지 알려준다. 
	 * @param symbol : 검색을 원하는 symbol의 label
	 * @return symbol이 가지고 있는 주소값. 해당 symbol이 없을 경우 -1 리턴
	 */
	public int search(String symbol) {
		int address = -1;
		//...
		
		/** 순수한 symbol만을 얻기 위해 '@','#'와 같은 문자 제거 **/
		if(symbol.contains("#")) {
			symbol = symbol.replace("#", "");
		}
		if(symbol.contains("@")) {
			symbol = symbol.replace("@", "");
		}
		
		/** symbolList를 순회하다 **/
		/** 찾으려는 symbol을 발견하면 **/
		for(int i = 0; i < symbolList.size(); i++) {
			if(symbolList.get(i).contains(symbol)) {
				/** 해당 순번의 location값을 리턴 **/
				address = locationList.get(i);
				break;
			}
		}
		
		return address;
	}
	
	
	
}

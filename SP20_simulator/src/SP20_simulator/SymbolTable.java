package SP20_simulator;
import java.util.ArrayList;

/**
 * symbol�� ���õ� �����Ϳ� ������ �����Ѵ�.
 * section ���� �ϳ��� �ν��Ͻ��� �Ҵ��Ѵ�.
 */
public class SymbolTable {
	ArrayList<String> symbolList;
	ArrayList<Integer> locationList;
	// ��Ÿ literal, external ���� �� ó������� �����Ѵ�.
	ArrayList<String> extdefList;
	ArrayList<String> extrefList;
	
	/**
	 * ���ο� Symbol�� table�� �߰��Ѵ�.
	 * @param symbol : ���� �߰��Ǵ� symbol�� label
	 * @param location : �ش� symbol�� ������ �ּҰ�
	 * ���� : ���� �ߺ��� symbol�� putSymbol�� ���ؼ� �Էµȴٸ� �̴� ���α׷� �ڵ忡 ������ ������ ��Ÿ����. 
	 * ��Ī�Ǵ� �ּҰ��� ������ modifySymbol()�� ���ؼ� �̷������ �Ѵ�.
	 */
	
	/** ������ �߰� **/
	/** symbolList�� ����� ��� �ʵ� ���� **/
	public SymbolTable() {
		symbolList = new ArrayList<String>();
		locationList = new ArrayList<Integer>();
		extdefList = new ArrayList<String>();
		extrefList = new ArrayList<String>();
	}
	
	/** �Ű������� �� �ε����� extdef ������ ���� **/
	public String getExtdef(int index) {
		return extdefList.get(index);
	}
	
	/** �Ű������� �� �ε����� extref ������ ���� **/
	public String getExtref(int index) {
		return extrefList.get(index);
	}
	
	public void putSymbol(String symbol, int location) {
		/** �Ű������� �� symbol�� location�� list�� �߰��� **/
		symbolList.add(symbol);
		locationList.add(location);
	}
	
	/**
	 * ������ �����ϴ� symbol ���� ���ؼ� ����Ű�� �ּҰ��� �����Ѵ�.
	 * @param symbol : ������ ���ϴ� symbol�� label
	 * @param newLocation : ���� �ٲٰ��� �ϴ� �ּҰ�
	 */
	public void modifySymbol(String symbol, int newLocation) {		
		/** symbolList�� ��ȸ�ϴ� **/
		/** ������ ���ϴ� symbol�� �߰��ϸ� **/
		for(int i = 0; i < symbolList.size(); i++) {
			if(symbolList.get(i).equals(symbol)) {
				/** ���� **/
				locationList.set(i, newLocation);
				break;
			}
		}
	}
	
	/**
	 * ���ڷ� ���޵� symbol�� � �ּҸ� ��Ī�ϴ��� �˷��ش�. 
	 * @param symbol : �˻��� ���ϴ� symbol�� label
	 * @return symbol�� ������ �ִ� �ּҰ�. �ش� symbol�� ���� ��� -1 ����
	 */
	public int search(String symbol) {
		int address = -1;
		//...
		
		/** ������ symbol���� ��� ���� '@','#'�� ���� ���� ���� **/
		if(symbol.contains("#")) {
			symbol = symbol.replace("#", "");
		}
		if(symbol.contains("@")) {
			symbol = symbol.replace("@", "");
		}
		
		/** symbolList�� ��ȸ�ϴ� **/
		/** ã������ symbol�� �߰��ϸ� **/
		for(int i = 0; i < symbolList.size(); i++) {
			if(symbolList.get(i).contains(symbol)) {
				/** �ش� ������ location���� ���� **/
				address = locationList.get(i);
				break;
			}
		}
		
		return address;
	}
	
	
	
}

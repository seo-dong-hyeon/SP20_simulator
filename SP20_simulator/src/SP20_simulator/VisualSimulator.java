package SP20_simulator;

import java.awt.EventQueue;
import java.io.File;

import SP20_simulator.Gui;

/**
 * VisualSimulator�� ����ڿ��� ��ȣ�ۿ��� ����Ѵ�.<br>
 * ��, ��ư Ŭ������ �̺�Ʈ�� �����ϰ� �׿� ���� ������� ȭ�鿡 ������Ʈ �ϴ� ������ �����Ѵ�.<br>
 * �������� �۾��� SicSimulator���� �����ϵ��� �����Ѵ�.
 */
public class VisualSimulator {
	ResourceManager resourceManager = new ResourceManager();
	SicLoader sicLoader = new SicLoader(resourceManager);
	SicSimulator sicSimulator = new SicSimulator(resourceManager, sicLoader);
	Gui gui;
	
	/**
	 * ���α׷� �ε� ����� �����Ѵ�.
	 */
	public void load(File program){
		//...
		sicLoader.load(program);
		//sicSimulator.load(program);
	};

	/**
	 * �ϳ��� ��ɾ ������ ���� SicSimulator�� ��û�Ѵ�.
	 */
	public void oneStep(){
		
	};

	/**
	 * �����ִ� ��� ��ɾ ������ ���� SicSimulator�� ��û�Ѵ�.
	 */
	public void allStep(){
		
	};
	
	/**
	 * ȭ���� �ֽŰ����� �����ϴ� ������ �����Ѵ�.
	 */
	public void update(){
		this.gui.eventAction();
	};
	

	public static void main(String[] args) {
		VisualSimulator vs = new VisualSimulator();
		vs.gui = new Gui(vs);
		vs.update();
		
	}
}

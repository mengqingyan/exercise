package www._21cn.com;

import java.util.ArrayList;
import java.util.List;

public class EventListener {
	public List<Action> listenerList = new ArrayList<Action>();
	public void addListener(Action action){
		listenerList.add(action);
	}
	public void addUser() {
		System.out.println("������һ���û�����");
		notifiyListener();
	}
	public void notifiyListener(){
		for (Action action : listenerList) {
			action.listen();
		} 
	}
	public void deleteUser() {
		System.out.println("ɾ����һ���û�����");
		notifiyListener();		
	}
}

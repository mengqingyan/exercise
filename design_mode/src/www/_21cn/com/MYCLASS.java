package www._21cn.com;

public class MYCLASS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventListener eventListener=new EventListener();//Ö÷Ìâ
		eventListener.addListener(new auditListener());
		eventListener.addUser();
		eventListener.addListener(new DeleteListen());
		eventListener.deleteUser();
	}

}

package www._21cn.com;

public class auditListener implements Action {

	@Override
	public void listen() {
		System.out.println("监听到已增加用户！！！");		
	}

}

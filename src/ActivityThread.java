
public class ActivityThread {
	
	final H mH = new H();

	public void attach(boolean b) {
		MainActivity activity = new MainActivity();
		activity.onCreate();
		
		//通过handler执行activity的生命周期
		Message message = new Message();
		message.obj = activity;
		mH.sendMessage(message);
	}
	
	private class H extends Handler{
		public void handleMessage(Message msg){
			Activity activity = (Activity) msg.obj;
			activity.onResume();
		}

	}

}


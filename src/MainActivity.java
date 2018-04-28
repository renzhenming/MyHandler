
public class MainActivity extends Activity{
	
	private Handler mHander = new Handler(){
		public void handleMessage(Message msg) {
			mTextView.setText((String) msg.obj);
		};
	};

	private TextView mTextView;

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate");
		mTextView = findViewById(R.id.text_view);
		
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				//mTextView.setText("哈哈哈哈");
				Message message = new Message();
				message.obj = "哈哈哈";
				mHander.sendMessage(message);
			};
			
		}.start();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("onResume");
	}
}

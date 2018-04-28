
public class MainActivity extends Activity{
	
	private Handler mHander = new Handler(){
		public void handleMessage(Message msg) {
			System.out.println("线程："+Thread.currentThread());
			mTextView.setText((String) msg.obj);
			System.out.println("更新UI成功");
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
					Thread.sleep(1000);
				} catch (Exception e) {
				}
//				Looper.prepare();
//				Handler handler = new Handler();
				//mTextView.setText("哈哈哈哈");
				System.out.println("线程："+Thread.currentThread());
				Message message = new Message();
				message.obj = "后台数据";
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

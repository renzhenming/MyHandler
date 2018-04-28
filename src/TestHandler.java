
public class TestHandler {

	public static void main(String[] args) {
		Looper.prepare();

        ActivityThread thread = new ActivityThread();
        thread.attach(false);

        Looper.loop();
	}

}

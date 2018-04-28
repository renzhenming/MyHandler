
public class Handler {

	private MessageQueue mQueue;

	public void sendMessage(Message message) {
		sendMessageDelayed(message, 0);
	}
	
	private final boolean sendMessageDelayed(Message message, int delayMillis) {
		if (delayMillis < 0) {
	        delayMillis = 0;
	    }
	    return sendMessageAtTime(message, System.currentTimeMillis());
	}

	private boolean sendMessageAtTime(Message message, long uptimeMillis) {
		MessageQueue queue = mQueue;
        
        return enqueueMessage(queue, message, uptimeMillis);
	}
	private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, uptimeMillis);
    }
	

	public void handleMessage(Message msg){
	}
	
	
}

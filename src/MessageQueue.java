
public class MessageQueue {

	private Message mMessages;

	public boolean enqueueMessage(Message msg, long when) {
		synchronized (this) {
            
            msg.when = when;
            Message p = mMessages; 
            if (p == null || when == 0 || when < p.when) {
                // New head, wake up the event queue if blocked.
                msg.next = p;
                mMessages = msg;
     
            } else {
                Message prev;
                for (;;) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p; // invariant: p == prev.next
                prev.next = msg;
            }
        }
        return true;
	}

}

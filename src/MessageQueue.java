
public class MessageQueue {

	private Message mMessages;
	public Message next;

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

	public Message next() {
		
        int pendingIdleHandlerCount = -1;
        for (;;) {
          
            synchronized (this) {
                final long now = System.currentTimeMillis();
                Message prevMsg = null;
                Message msg = mMessages;
                if (msg != null && msg.target == null) {
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null);
                }
                if (msg != null) {
                    if (now < msg.when) {
                    } else {
                        if (prevMsg != null) {
                            prevMsg.next = msg.next;
                        } else {
                            mMessages = msg.next;
                        }
                        msg.next = null;
                        return msg;
                    }
                }
                if (pendingIdleHandlerCount <= 0) {
                    continue;
                }
            }

        }
	}

}


public class CircularArrayQueue<T> implements IQueue<T> {
	private final static int defaultCapacity = 25;
	private final int maxCapacity=1000;
	private int frontIndex=-1;
	private int backIndex=-1;
	private T[] queue;
	private boolean initialized = false; 
	
	public CircularArrayQueue() throws Exception
	{
		this(defaultCapacity);
	}
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int initialCapacity) throws Exception
	{
		checkCapacity(initialCapacity);
		queue = (T[])new Object[initialCapacity+1];
		initialized=true;
	}
	private void checkCapacity(int initialCapacity)  {
		if(initialCapacity>maxCapacity-1)
		{
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void enqueue(T newEntry) {
		
		checkInitialization();
		ensureCapacity();
		backIndex= (backIndex+1)%queue.length;
		queue[backIndex]= newEntry;
		
		
		
		
		
		
	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		if(this.isFull()) {
			int newLength=(queue.length)*2;
			checkCapacity(newLength);
			T[] tempqueue= (T[]) new Object[newLength];
			backIndex=queue.length-1;
			for (int i = 0 ; i<queue.length;i++)
			{
				tempqueue[i]=queue[(frontIndex+i)%queue.length];
				queue=tempqueue;
				
				
		}
		frontIndex=0;
		}
	}
	public T dequeue() {
		checkInitialization();
		if(!this.isEmpty())
		{
			frontIndex=(frontIndex+1)%queue.length;
			T temp=queue[frontIndex];
			queue[frontIndex]=null;
			return temp;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T getFront() {
		checkInitialization();
		if(!this.isEmpty())
		{
			T temp=queue[frontIndex];
			return (T) temp.toString();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		checkInitialization();
		return (frontIndex==(backIndex+1)% queue.length);
	}

	@Override
	public void clear() {
		checkInitialization();
		for (int i = 0 ; i<queue.length;i++)
		{
			queue[i]=null;
		}
		
	}
	private boolean isFull() {
		
		checkInitialization();
		return (frontIndex==(backIndex+2)%queue.length);
	}
	private void checkInitialization() {
		if(!initialized)
			try {
				throw new Exception() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

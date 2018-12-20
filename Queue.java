
//Mohamed Ameen Omar
//u16055323

class Node<T>
{
			public T data;
			public Node<T> next;

			Node(T data)
			{
						this.data = data;
						next = null;
			}
}


public class Queue<T>
{
    private Node<T> head;
    private int size;

    public Queue()
		{
			head = null;
      size = 0;
    }


     public T dequeue()
     {
        if(head == null)
				{

					return null;
				}
				else
				{
					Node<T> temp = head;

					head = temp.next;

          T foo;
          foo = temp.data;
          size--;

					if(size + 1 == 1)
					{
						head = null;
						size = 0;
					}
					return foo;


				}
    }

     public  void enqueue(T node)
	   {


				 Node<T> focus = new Node<T>(node);


				 if(head == null)
				 {
					 head = focus;
					 head.next = null;

	        this.size = 1;
				}

				else
				{
					Node<T> temp = head;

					while(temp.next != null)
	        {
						temp = temp.next;
					}
					temp.next = focus;
					focus.next = null;
	        size++;
				}
    }

    public boolean isEmpty()
    {
        if(head == null || this.size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getSize()
		{

	  	return this.size;
    }



 }

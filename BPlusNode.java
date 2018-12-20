/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: u16055323
*/

/*
You must design and implement this class to be used as a node in your B+ tree. You may
choose to implement this class to double up for both internal and leaf nodes or you
could create a separate class to represent the internal nodes.  In choosing the latter
strategy of having separate leaf and internal node classes, it would be a good idea
to create a super class for your two node types.

You may add any additional members that you need but you may not remove or modify
the members already contained in the class.
*/


public class BPlusNode
{
	public boolean isLeaf;
	public int order;
	public Integer keys[];
	public BPlusNode references[];
	int keyTally;

	//parent in all levels
	public BPlusNode parent;

	//previous in leaf
	public BPlusNode previous;

	/*
	This is the "next" node in the leaf level.
	*/
	protected BPlusNode next;

	BPlusNode(boolean leaf, int order)
	{

		this(leaf,null,order);
	}

	BPlusNode(boolean leaf, BPlusNode p, int order)
	{

		parent = p;
		this.isLeaf = leaf;
		this.order = order;
		keyTally = 0;

		if(this.isLeaf == false)
		{
			keys = new Integer[order - 1];


			for(int x = 0; x < order - 1; x++)
			{
				keys[x] = null;
			}

			references = new BPlusNode[order];

			for(int i = 0; i < order; i++)
			{
				references[i] = null;
			}

			next = null;
			this.previous = null;
		}

		else
		{
			references = null;

			keys = new Integer[order-1];

			for(int x = 0; x < order - 1; x++)
			{
				keys[x] = null;
			}

			this.next = null;
			this.previous = null;
		}
	}

	BPlusNode(boolean leaf, int order, Integer key)
	{

		parent = null;
		isLeaf = leaf;
		this.order = order;
		keyTally = 1;

		if(this.isLeaf == false)
		{
			keys = new Integer[order - 1];
			keys[0] = (Integer)key;

			for(int x = 1; x < order - 1; x++)
			{
				keys[x] = null;
			}

			references = new BPlusNode[order];

			for(int i = 0; i < order; i++)
			{
				references[i] = null;
			}

			next = null;
			previous = null;
		}

		else
		{
			references = null;

			keys = new Integer[order-1];
			keys[0] = key;

			for(int x = 1; x < order - 1; x++)
			{
				keys[x] = null;
			}

			previous = next = null;
		}
	}

	public void setNext(BPlusNode next)
	{
			if(isLeaf == true)
			{
				this.next = next;
				next.previous = this;
			}
	}

	public String toString()
	{
			if(keyTally == 0)
			{
				return "";
			}

			String output = "";

			for(int x = 0; x <keyTally; x++)
			{
				if(keys[x] != null)
				{
					output  += "[" + keys[x] + "]";
				}

			}

			return output;
	}



	public Boolean isFull()
	{
		if(this.keyTally == this.order-1)
		{
			return true;
		}

		else
		{
			return false;
		}
	}


	//swap the elements
	public void swapArrayElements(int index1, int index2)
	{
		if(index1 >= order-1 || index2 >= order-1)
		{
			return;
		}

		else
		{
			Integer temp = this.keys[index1];
			this.keys[index1] = this.keys[index2];
			this.keys[index2] = temp;
		}
	}

	//sort key array in ascending
	public void sortAscending()
	{
		if(this.keyTally == 0)
		{
			return;
		}


		else
		{
			Boolean swapped = false;

			while(true)
			{
				swapped = false;

				for(int x = 0; x < this.keyTally -1; x++)
				{
					if(this.keys[x] == null || this.keys[x+1] == null)
					{
						break;
					}
					else if(this.keys[x] > this.keys[x+1] || this.keys[x] == null && this.keys[x+1] != null)
					{
						swapped = true;
						swapArrayElements(x,x+1);
					}
				}

				if(swapped == false)
				{
					break;
				}
			}//end while
		}//end else
	}



	//insert the value passed in when the array is not full
	public void insert(Integer temp)
	{
		if(this.keyTally >= this.order -1)
		{
			return;
		}

		else
		{

			this.keys[this.keyTally] = temp;
			this.keyTally++;
			this.sortAscending();

		}
	}

	//if leaf
	public boolean isLeaf()
	{
		return this.isLeaf;
	}

	//delete value passed in
	public void delete(Integer element)
	{
		for(int count = 0; count < this.keyTally; count++)
		{
			if(keys[count] == element)
			{
				keys[count] = null;
				--this.keyTally;
				sortAfterDelete(count);

			}

		}

	}

	//to move the null node to the right
	public void sortAfterDelete(int index)
	{
		if(this.keyTally == 0)
		{
			return;
		}


		for(int x = index; x < this.order-2; x++)
		{
			swapArrayElements(x,x+1);
		}
	}

	//if empty
	public boolean isEmpty()
	{
		if(keyTally == 0)
		{
			return true;
		}

		else
		{
			return false;
		}
	}


	//get next level child from references
	public BPlusNode nextChild(Integer element)
	{
		int x = 0;
		for(x = 0; x<keyTally && keys[x] <= element; x++)
		{

		}

		return references[x];

	}

	public boolean willUnderFlow()
	{
		if(keyTally == 1)
		{
			return true;
		}

		return false;
	}

	//given a node get the index for which the reference for parent is this node
	public int getReferenceIndex()
	{
		BPlusNode parentTemp = this.parent;
		int count = 0;
		for(count = 0; count < this.order-1;count++)
		{
			if(parentTemp.references[count] == this)
			{
				break;
			}
		}

		//count is the index

		return count;
	}

	public void clearArray()
	{
		for(int x = 0; x<order-1;x++)
		{
			keys[x] = null;
		}

		keyTally = 0;
	}







}

/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: u16055323
*/

/*You must complete this class to create a fully functional B+-Tree containing
Integer objects.  Additional instructions are provided in comments throughout the class*/
public class BPlusTree
{
	/*
	1. You may not change the signatures of any of the given methods.  You may
	however add any additional methods and/or field which you may require to aid
	you in the completion of this assignment.

	2. You will have to design and implement a your own Node class.  The BPlusNode
	should house Integer objects. You are given a partial BPlusNode class.

	3. You will notice that there are some overloaded methods, some of which work
	for Integer objects and some with primitive type int.  You have to find a way
	to implement the methods to work with both types.
	*/
	BPlusNode root;
	int order;

	public BPlusTree(int m)
	{
		/*
		The constructor.  You must create a BPlusTree object of order m,
		where m is passed as a parameter to the constructor.
		You may assume that m will always be valid.
		*/

		order = m;
		root = null;
	}

	public String insertElement(int element)
	{
		/*
		The int element passed as a parameter should be inserted in
		your B+-Tree.  The method should return a string representation
		of the path followed through the tree to find a node
		to insert into.  If the tree was empty, return the string
		representatio of the new root.
		*/

		Integer temp = (Integer)element;
		return insertElement(temp);
	}

	public String insertElement(Integer element)
	{
		/*
		The int element passed as a parameter should be inserted in
		your B+-Tree.  The method should return a string representation
		of the path followed through the tree to find a node
		to insert into.  If the tree was empty, return the string
		representatio of the new root.
		*/

		//if empty
		if(root == null)
		{
			root = new BPlusNode(true, this.order, element);
			return root.toString();
		}


		String output = "";
		//inserting into the root
		if(root.isLeaf == true)
		{
			output += root.toString();

			if(root.isFull() == false)
			{
				root.insert(element);
			}

			else
			{
				//we need to split the root
				splitLeafRoot(element);
			}

			return output;
		}//end if

		//traverse the tree

		BPlusNode temp = root;

		while(temp.isLeaf == false)
		{
			output += temp.toString() + ",";
			temp = temp.nextChild(element);
		}

		output += temp.toString();

		Integer elementTemp = element;

		//while(55 < 60)
		//{
			if(temp.isFull() != true)
			{

					temp.insert(element);
					return output;
			}


			//temp is place to insert
			//need to split
			//temp right now is a leaf

			boolean left = leftHasSpace(temp);
			boolean right = rightHasSpace(temp);

			//send to left
			if(left == true)
			{
				//parents are the same
				if(temp.parent == temp.previous.parent)
				{

					shareLeftSameParent(temp,elementTemp);
				}


				//if parents arent the same
				else
				{

				}

				return output;
			}

			//send to right
			else if(right == true)
			{
				//if they have the same parent
				if(temp.parent == temp.next.parent)
				{
					shareRightSameParent(temp,elementTemp);
				}

				else
				{
					//have different parents
				}

				return output;
			}

			//split
			else
			{
				if(temp.isLeaf == true)
				{
					elementTemp = leafSplit(temp, elementTemp);

					if(elementTemp == -1)
					{
						return output;
					}
				}

				else
				{
					//elementTemp = indexSplit(temp, elementTemp);
				}

				if(temp == root)
				{
					//split the root and fix chid refernece
					return output;
				}
			else
			{
					temp = temp.parent;
			}


			}



	//	}

	return output;

}


	public String deleteElement(int element)
	{
		/*
		The int element passed as a parameter should be deleted from
		your B+-Tree.  The method should return a string representation
		of the path followed through the tree to find the node to delete
		from. If the tree was empty, return the string representation
		of the NULL node.
		*/
		if(root == null)
		{
			return "*NULL*";
		}

		else
		{
			Integer temp = (Integer)element;
			return deleteElement(temp);
		}
	}

	public String deleteElement(Integer element)
	{
		/*
		The int element passed as a parameter should be deleted from
		your B+-Tree.  The method should return a string representation
		of the path followed through the tree to find the node to delete
		from. If the tree was empty, return the string representation
		of the NULL node.
		*/

		if(root == null)
		{
			return "*NULL*";
		}

		else if(root.keyTally == 0)
		{
			return "*NULL*";
		}

		else
		{
				String output = "";
				//only root exists
				if(root.isLeaf() == true)
				{
					output = root.toString();

					root.delete(element);

					if(root.isEmpty() == true)
					{
						root = null;
					}

				}

				else
				{
					//traverse

					BPlusNode temp = root;

					while(temp.isLeaf == false)
					{
						output += temp.toString() + ",";
						temp = temp.nextChild(element);
					}

					output += temp.toString();

					if(temp.willUnderFlow() != true)
					{
							temp.delete(element);
					}

					else
					{
						//we need to merge
					}
				}

				return output;
		}


	}

	public String search(int element)
	{
		/*
		A String should be returned representing the search path
		for the element sent in as a parameter.  Refer to the
		specification for more details.
		*/
		if(root == null)
		{
			return "*NULL*";
		}

		else
		{
			Integer temp = (Integer)element;
			return search(temp);
		}

	}

	public String search(Integer element)
	{
		/*
		A String should be returned representing the search path
		for the element sent in as a parameter.  Refer to the
		specification for more details.
		*/
		if(root == null)
		{
			return "*NULL*";
		}

		else
		{
			//traverse
			String output = "";
			BPlusNode temp = root;

			while(temp.isLeaf == false)
			{
				output += temp.toString() + ",";
				temp = temp.nextChild(element);
			}

			output += temp.toString();

			boolean found = false;

			for(int x = 0; x< temp.keyTally; x++)
			{
				if(temp.keys[x] == element)
				{
					found = true;
					break;
				}
			}

			if(found == true)
			{
				return output;
			}

			output += ",*NULL*";

			return output;
		}

	}


	//done
	public int height()
	{
		/*
		This method should return the height of the tree.
		*/
		if(root == null)
		{
			return 0;
		}

		else
		{
			BPlusNode focus = root;

			int height = 1;
			while(focus.isLeaf == false)
			{
				focus = focus.references[0];
				height++;

				if(focus == null)
				{
					return height;
				}
			}

			return height;
		}
	}

	//done
	public int countInternalNodes()
	{
		/*
		This method should count and return the number of internal nodes.
		*/

		if(root == null || root.isLeaf == true)
		{
			return 0;
		}

		else
		{
			Queue<BPlusNode> queue = new Queue<BPlusNode>();

			queue.enqueue(root);

			int count = 0;

			while(queue.isEmpty() == false)
			{
				BPlusNode focus = queue.dequeue();

				if(focus == null || focus.isLeaf == true)
				{
					continue;
				}

				if(focus.isLeaf == false)
				{
					count++;
				}

				for(int x = 0; x < focus.order; x++)
				{
					if(focus.references[x] != null)
					{
						queue.enqueue(focus.references[x]);
					}
				}
			}

			return count;
		}

	}


	//done
	public int countLeafNodes()
	{
		/*
		This method should count and return the number of leaf nodes.
		*/
		if(root == null)
		{
			return 0;
		}

		else if(root.isLeaf == true)
		{
			return 1;
		}

		else
		{
			BPlusNode focus = getFirstLeaf();

			int count = 0;

			while(true)
			{
				if(focus == null)
				{
					break;
				}

				count++;
				focus = focus.next;
			}

			return count;
		}


	}

	//done
	public int fullness()
	{
		/*
		This method should return as a percentage the fullness of the tree.
		The percentage is out of 100 and if, for example, 70 is returned,
		it means that the tree is 70% full. A tree containing no keys is 0%
		full.  Round your answer up to the nearest integer.
		*/

		if(root == null || root.keyTally == 0)
		{
			return 0;
		}

		else
		{
			BPlusNode temp = root;
			Queue<BPlusNode> queue = new Queue<BPlusNode>();
			double sum = 0, totalKeys = 0;

			if( temp != null )
			{
				queue.enqueue(temp);

				while(queue.isEmpty()  == false)
				{
					temp = queue.dequeue();
					sum++;

					for( int x = 0; x < temp.order; x++ )
					{
						if(temp.isLeaf == false && temp.references[x] != null)
						{
							queue.enqueue(temp.references[x]);
						}

					}

					for( int x = 0; x < temp.keyTally; x++ )
					{
						if( temp.keys[ x ] != null )
						{
							totalKeys++;
						}
					}

				}
			}
			if(sum == 0 || totalKeys == 0)
			{
				return 0;
			}

			Double output = new Double( ( totalKeys / ( sum * ( this.order - 1 ) ) ) * 100.00 );
			return output.intValue();
		}
	}

	//done
	public String breadthFirst()
	{
		/*
		This method returns a String containing the nodes in breath-first
		order.  You should not include null nodes in the string.  The string
		for an empty tree is simply an empty string.
		*/

		if(root == null)
		{
			return "";
		}

		else if(root.keys[0] == null)
		{
			return null;
		}

		else
		{
			BPlusNode temp = root;
			Queue<BPlusNode> queue = new Queue<BPlusNode>();
			String output = "";
			queue.enqueue(temp);

			int count = 0;

			while (queue.isEmpty() == false )
			{

			      temp = queue.dequeue();
						if(temp == null)
						{

							continue;
						}

						for (int x = 0; x < temp.order; x++)
					 {

						 if (temp.isLeaf == false && temp.references[x] != null)
						 {
							 queue.enqueue(temp.references[x]);
						 }
						 count++;
					 }

			      output += temp.toString();


			      if ( queue.isEmpty() == false)
						{
							output += ",";

						}

						else if(count == 0 && queue.isEmpty() == false)
						{
							output += ",";

						}
			}

			return output;
		}
	}

	//done
	public BPlusNode getFirstLeaf()
	{
		/*
		This method should return the left-most leaf node in the tree.
		If the tree is empty, return null.
		*/

		if(root == null)
		{
			return null;
		}

		BPlusNode focus = root;
		while(focus.isLeaf == false)
		{
			focus = focus.references[0];
		}
		return focus;
	}



	//WORKS!!!!
	//splits the root if it is a leaf
	public void splitLeafRoot(Integer element)
	{
		if(root.isLeaf() == true && root.isFull() == true)
		{
			Integer[] temp = new Integer[order];

			for(int count = 0; count < order-1; count++)
			{
				temp[count] = root.keys[count];
			}
			temp[order-1] = element;

			sortArrayAscending(temp,order);

			//temp is our full thing

			int maxKeys = order;

			Double d = Math.ceil(maxKeys/2);

			int index = d.intValue();

			BPlusNode node = new BPlusNode(false, order,temp[index]);

			node.parent = null;

			BPlusNode nodeLeft = new BPlusNode(true,order);
			nodeLeft.parent = node;
			BPlusNode nodeRight = new BPlusNode(true,order);
			nodeRight.parent = node;
			nodeLeft.setNext(nodeRight);
			node.references[0] = nodeLeft;
			node.references[1] = nodeRight;

			nodeLeft.previous = null;

			for(int count = 0; count < index; count++)
			{
				nodeLeft.insert(temp[count]);
			}

			for(int count = index; count < maxKeys; count++)
			{
				nodeRight.insert(temp[count]);
			}

			this.root = node;

			root.isLeaf = false;

			root.next = null;

		}


	}


	//WORKS!!!!
	//sorts an array in ascending order
	public void sortArrayAscending(Integer array[], int maxKeys)
	{
		Boolean swapped = false;

		while(true)
		{
			swapped = false;

			for(int x = 0; x < maxKeys-1; x++)
			{
				if(array[x] == null || array[x+1] == null)
				{
					break;
				}
				else if(array[x] >= array[x+1])
				{
					swapped = true;
					Integer foo = array[x];
					array[x] = array[x+1];
					array[x+1] = foo;
				}
			}

			if(swapped == false)
			{
				break;
			}
		}//end while
	}

	//WORKS!!!!
	//check if left sibling has space
 public boolean leftHasSpace(BPlusNode temp)
 {
	 if(temp.previous == null || getFirstLeaf() == temp)
	 {
		 return false;
	 }

	 return !temp.previous.isFull();
 }


 //WORKS!!!!
//Check if right sibling has space
 public boolean rightHasSpace(BPlusNode temp)
 {
	 if(temp.next == null)
	 {
		 return false;
	 }
	 return !temp.next.isFull();
 }


	//split operation for the leafs
 public Integer leafSplit(BPlusNode temp, Integer element)
 {

	 //first key right needs to be sent to the parent return this

	 if(temp.parent.isFull() == false)
	 {
		 Integer[] foo = new Integer[temp.order];
		 int count;
		 for( count = 0; count < order-1; count++)
		 {
			 foo[count] = temp.keys[count];
		 }

		 foo[count] = element;

		 sortArrayAscending(foo,temp.order);

		 Double d = (Math.ceil(temp.order/2));
		 int index = d.intValue();

		 temp.clearArray();
		 int x;
		 for( x = 0; x < index; x++)
		 {
			 System.out.println(foo[x] + "index == " + index);
			 temp.insert(foo[x]);
		 }

		 Integer send = foo[x];



		 BPlusNode right = new BPlusNode(true, order, foo[x]);

		 x++;

		 right.next = temp.next;
		 temp.setNext(right);
		 right.previous = temp;
		 right.parent = temp.parent;






		 for(; x<order;x++)
		 {
			 right.insert(foo[x]);
		 }

		 temp.parent.insert(send);


		 int parentReference = temp.getReferenceIndex();

		 int pIndex = temp.getReferenceIndex();
		 pIndex++;
		 temp.parent.references[pIndex] = right;

		 return -1;


	 }

	 else
	 {
		 return 0;
	 }
	 // do references of children


 }



	/*//split operation for the index set
 public Integer indexSplit(BPlusNode temp, Integer element, BPlusNode newChild)
 {

	 	 // do BTree split


 }*/


 //WORKS!!!!
	//for insert, share left with the same parent
 public void shareLeftSameParent(BPlusNode node, Integer element)
 {

	 Integer[] foo = new Integer[node.order];
	 int count;
	 for( count = 0; count < order-1; count++)
	 {
		 foo[count] = node.keys[count];
	 }

	 foo[count] = element;

	 sortArrayAscending(foo,node.order);

	 //foo has our big array with the element


	 //insert last element
	 node.previous.insert(foo[0]);

	 int index = node.getReferenceIndex();

	 node.parent.keys[index] = foo[1];

	 for(int x = 1; x < order; x++ )
	 {
		 node.keys[x-1] = foo[x];
	 }

 }

//WORKS!!!!
 public void shareRightSameParent(BPlusNode node, Integer element)
 {

	 Integer[] foo = new Integer[node.order];
	 int count;
	 for( count = 0; count < order-1; count++)
	 {
		 foo[count] = node.keys[count];
	 }

	 foo[count] = element;

	 sortArrayAscending(foo,node.order);

	 //foo has our big array with the element


	 //insert last element
	 node.next.insert(foo[node.order-1]);

	 int index = node.getReferenceIndex();

	 node.parent.keys[index] = foo[node.order-1];

	 int x;
	 for( x = 0; x < order-1; x++ )
	 {
		 node.keys[x] = foo[x];
	 }

 }














}

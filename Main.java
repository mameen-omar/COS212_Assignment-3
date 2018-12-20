/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: u16055323
*/


public class Main
{
	/*Place your main and your test code here*/
	public static void main ( String [] args ) {

		BPlusTree tree = new BPlusTree( 5 );
		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );
		System.out.println(tree.insertElement( 10 ));


		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );

		System.out.println("Leaf: " + tree.getFirstLeaf().toString());

		System.out.println("Number of leafs: " + tree.countLeafNodes());

		System.out.println(tree.insertElement( 15));
		System.out.println("fullness : " + tree.fullness());
	System.out.println(	tree.insertElement( 50));
	System.out.println("fullness : " + tree.fullness());
		System.out.println(tree.insertElement( 20 ));
		System.out.println("fullness : " + tree.fullness());
		System.out.println(tree.insertElement( 30 ));
		System.out.println( "Root Split");
		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );
		System.out.println("fullness : " + tree.fullness());
		System.out.println(tree.insertElement( 2 ));
		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );
		System.out.println("fullness : " + tree.fullness());
		System.out.println("Number of leafs: " + tree.countLeafNodes());

		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );
		System.out.println("fullness : " + tree.fullness());
		System.out.println("Number of leafs: " + tree.countLeafNodes());

		System.out.println( "BFS : " + tree.breadthFirst() );
		System.out.println( "Height : " + tree.height() );
		System.out.println("fullness : " + tree.fullness());
		System.out.println("Number of leafs: " + tree.countLeafNodes());


			System.out.println("Leaf: " + tree.getFirstLeaf().toString());

			System.out.println( "BFS : " + tree.breadthFirst() );



			System.out.println( "BFS : " + tree.breadthFirst() );
			System.out.println( "Height : " + tree.height() );
			System.out.println("fullness : " + tree.fullness());
			System.out.println("Number of leafs: " + tree.countLeafNodes());


				System.out.println("Leaf: " + tree.getFirstLeaf().toString());

				System.out.println( "BFS : " + tree.breadthFirst() );

				System.out.println(tree.insertElement( 16 ));

				System.out.println( "BFS : " + tree.breadthFirst() );

				System.out.println(tree.insertElement( 6 ));

				System.out.println( "BFS : " + tree.breadthFirst() );

				System.out.println( "Search 16 : " + tree.search(55) );

				System.out.println(tree.insertElement( 60 ));

				System.out.println( "BFS : " + tree.breadthFirst() );

				System.out.println("Leaf: " + tree.getFirstLeaf().toString());

				System.out.println( "Height : " + tree.height() );
				System.out.println("fullness : " + tree.fullness());
				System.out.println("Number of leafs: " + tree.countLeafNodes());













	}

}

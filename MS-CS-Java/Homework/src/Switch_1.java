/**
 *  Test of the switch statement.
 *
 * @version    $Id$
 *
 * @author     hpb
 *
 * Revisions:
 *     $Log$
 */


class Switch_1 {
    private String itWasA(char c) {
        switch( c )	{
            case 'a':
                System.out.println("A");	// break?
            case 'b':	return("B");	// break?
            case 'c':	return("C");	// break?
            case 100:	return("D");	// break?
            case 101:	return("E");	// break?
            default:	return("no clue, but not an [a-e]");
            // What happens if
            // we delete this line?
        }
    }


    public static void main( String args[] ) {
        char theChar;

        theChar = 'a';
        System.out.println("You typed in an '" +
                new Switch_1().itWasA(theChar) + "'");

        System.exit(0);	// beware of ...
    }
}
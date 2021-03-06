package com.beliefrevision;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
    	menu();
    }

    public static void menu() {

        BeliefEngine be = new BeliefEngine();
        Util util = new Util();
        
    	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	for(;;) {
	    	System.out.println("1. Add to belief base");
	    	System.out.println("2. Check formula for consistency");
	    	System.out.println("3. Show belief base");
	    	System.out.println("4. Test resolution");
	    	System.out.println("5. Quit");

	    	//Read input for choice
	    	int choice = in.nextInt();
	    	in.nextLine();

	    	switch (choice) {
			case 1:
				System.out.println("Input sentence in CNF from:");
				String input = in.nextLine();
				CNFSentence cnf = new CNFSentence(input);
				if(be.beliefBase.isEmpty()) {
					System.out.println("Added \"" + input + "\" to the Belief base");
					be.addToBeliefBase(input);
				} else if(be.plResolution(be.beliefBase,cnf)) {
					System.out.println("Added " + input + " to the Belief base");
					be.addToBeliefBase(input);
				} else {
					System.out.println("Could not add sentence to belief base");
				}
				break;
				
			case 2:
				Clause tmp1 = new Clause("s");
				Clause tmp2 = new Clause("!s");
				be.plResolve(tmp1, tmp2);
				break;
				
			case 3:
				be.printBeliefBase();
				break;
	
			case 4:
				be.testPL();
				break;
				
			case 5:
				System.out.println("Belief engine shutdown");
				System.exit(0);
				break;

			case 6:
				be.addToBeliefBase("a");
				be.testPL2();
				break;
			default:
				break;
			}
    	}
    }
}

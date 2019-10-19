import java.io.*;
import java.util.Scanner;

public class Matchmaker extends DogHandler {
	private Dog dog;
	private UserAccount user;
	
	public Matchmaker(UserAccount user) {
		super();
		this.user = user;
		menu();
	}
	
	public String getUser() {
		return user.getUser();
	}
	
	private void menu() {
		Scanner in = new Scanner(System.in);
		int choice;
		
		System.out.println("\nMatchmaker Menu");
		System.out.println("1. Account settings");
		System.out.println("2. Look at pets");
		System.out.println("3. Messages");
		System.out.println("4. Log out");
		
		System.out.print("\nEnter an option: ");
		choice = in.nextInt();
		while (choice < 1 || choice > 4) {
			System.out.print("Please enter a number between 1 and 4: ");
			choice = in.nextInt();
		}
		if (choice == 1)
			System.out.println("Not implemented.");
		else if (choice == 2)
			match();
		else if (choice == 3)
			System.out.println("Not implemented.");
		else {
			user.logMeOut();
		}
	}
	
	private void match() {
		Scanner in = new Scanner(System.in);
		char ch;
		Dog dog;
		while (true) {
			dog = nextDog();
			if (dog == null)
				break;
			System.out.println();
			dog.display();
			System.out.print("Do you like this dog? (y/n/q)");
			ch = in.nextLine().charAt(0);
			if (ch == 'y')
				dog.addLike(getFile(), getUser());
			else if (ch == 'q') {
				System.out.println();
				break;
			}
		}
		menu();
	}
	
}

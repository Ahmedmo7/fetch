import java.io.File;
import java.util.Scanner;

public class BreederApp extends DogHandler {
	private UserAccount user;
	private Dog currentDog;
	
	public BreederApp(UserAccount user) {
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
		
		System.out.println("Breeder App Menu");
		System.out.println("1. Account settings");
		System.out.println("2. My Pets");
		System.out.println("3. Messages");
		System.out.println("4. Log out");
		
		System.out.print("\nEnter an option: ");
		choice = in.nextInt();
		while (choice < 1 || choice > 4) {
			System.out.print("\nPlease enter a number between 1 and 4: ");
			choice = in.nextInt();
		}
		if (choice == 1)
			System.out.println("Not implemented.");
		else if (choice == 2) {
			File f = new File(user.getUser() + "/dogs.fch");
			if (!f.exists()) {
				System.out.print("You have no pets, add one? (y/n)");
				in.nextLine();
				char ch = in.nextLine().charAt(0);
				if (ch == 'y')
					newDog(user);
			}
			petsMenu();
		} else if (choice == 3)
			System.out.println("Not implemented.");
		else
			user.logMeOut();
	}
	
	private void petsMenu() {
		Scanner in = new Scanner(System.in);
		Dog[] dogs = getDogs(user);
		int choice;
		
		for (int i = 0; i < dogs.length; i++) {
			System.out.println((i+1) + ". " + dogs[i].getName());
		}
		System.out.println((dogs.length+1) + ". Add a dog");
		System.out.println((dogs.length+2) + ". Go back");
		System.out.print("\nPlease select a dog (1-" + (dogs.length+2) + "): ");
		choice = in.nextInt();
		while (choice < 1 || choice > dogs.length + 2) {
			System.out.print("\nPlease select a valid option: ");
			choice = in.nextInt();
		}
		if (choice == dogs.length + 1) {
			newDog(user);
			petsMenu();
		} else if (choice == dogs.length + 2) {
			menu();
		} else {
			currentDog = dogs[choice - 1];
			System.out.println("\n" + currentDog.getName() + " selected!\n");
			currentDog.display();
			System.out.println();
			dogOptions();
		}
	}
	
	private void dogOptions() {
		Scanner in = new Scanner(System.in);
		int choice;
		
		System.out.println("Options for " + currentDog.getName());
		System.out.println("1. View Likes");
		System.out.println("2. Edit");
		System.out.println("3. Delete");
		System.out.println("4. Messages");
		System.out.println("5. Go Back");
		
		System.out.print("\nEnter an option: ");
		choice = in.nextInt();
		while (choice < 1 || choice > 5) {
			System.out.print("\nPlease select a number between 1 and 5: ");
			choice = in.nextInt();
		}
		
		if (choice == 1) {
			System.out.print("\nList of users who have liked " + currentDog.getName() + ":");
			currentDog.getLikes();
			System.out.println();
			dogOptions();
		} else if (choice == 2)
			System.out.println("Not implemented.");
		else if (choice == 3) {
			removeDog(user, currentDog.getID());
			petsMenu();
		} else if (choice == 4)
			System.out.println("Not implemented.");
		else
			petsMenu();
	}
}

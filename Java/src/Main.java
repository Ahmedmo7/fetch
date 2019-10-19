import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		UserAccount user;
		Matchmaker matcher;
		Main m = new Main();
		
		user = m.login();
		
		if (user.isLoggedIn()) {
			if (user.getType().equals("Breeder")) {
				matcher = new Matchmaker(user);
				m.match(matcher);
			}
		}
	}
	
	public void match(Matchmaker m) {
		Scanner in = new Scanner(System.in);
		char ch;
		Dog dog;
		while (true) {
			dog = m.nextDog();
			if (dog == null)
				break;
			dog.display();
			System.out.print("Do you like this dog? (y/n)");
			ch = in.nextLine().charAt(0);
			if (ch == 'y')
				dog.addLike(m.getFile(), m.getUser());
		}
	}
	
	public UserAccount login() {
		Scanner in = new Scanner(System.in);
		String email;
		char ch1, ch2;
		UserAccount acc;
		
		System.out.print("Press 'c' to create account or 'l' to login: ");
		ch1 = in.nextLine().charAt(0);
		System.out.print("Please enter 'b' for breeder account or 'c' for customer account: ");
		ch2 = in.nextLine().charAt(0);
		if (ch1 == 'c') {
			System.out.print("Email: ");
			email = in.nextLine();
			if (ch2 == 'b')
				acc = new BreederAccount(email);
			else
				acc = new UserAccount(email, 0);
		} else {
			if (ch2 == 'b')
				acc = new BreederAccount();
			else
				acc = new UserAccount(0);
		}
		acc.logMeIn();
		return acc;
	}
}
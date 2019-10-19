import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		UserAccount user;
		Matchmaker match;
		BreederApp breed;
		Main m = new Main();
		
		while (true) {
			user = m.login();
			System.out.println();
			if (user.isLoggedIn()) {
				if (user.getType().equals("User")) {
					match = new Matchmaker(user);
				} else {
					breed = new BreederApp(user);
				}
			}
		}
	}
	
	public UserAccount login() {
		Scanner in = new Scanner(System.in);
		String email;
		char ch;
		UserAccount acc = null;
		
		System.out.print("Press 'c' to create account or 'l' to login: ");
		ch = in.nextLine().charAt(0);
		if (ch == 'c') {
			System.out.print("Please enter 'b' for breeder account or 'c' for customer account: ");
			ch = in.nextLine().charAt(0);
			System.out.print("Email: ");
			email = in.nextLine();
			if (ch== 'b')
				acc = new UserAccount(email, 1);
			else
				acc = new UserAccount(email, 0);
		} else if (ch == 'l' ){
			acc = new UserAccount();
		} else
			System.exit(0);
		acc.logMeIn();
		return acc;
	}
}
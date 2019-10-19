import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		UserAccount user; //current user account
		Matchmaker match; //matchmaker app
		BreederApp breed; //breeder app
		Main m = new Main();
		
		while (true) {
			user = m.login(); //login
			System.out.println();
			if (user.isLoggedIn()) { //is there a user logged in?
				if (user.getType().equals("User")) { //is the account a user or breeder?
					match = new Matchmaker(user); //open matchmaker
				} else {
					breed = new BreederApp(user); //open breeder app
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
				acc = new UserAccount(email, 1); //new breeder account
			else
				acc = new UserAccount(email, 0); //new user account
		} else if (ch == 'l' ){
			acc = new UserAccount(); //basic user account object
		} else
			System.exit(0);
		acc.logMeIn(); //this actually does the "login" part and performs checks
		return acc; //return the logged in account
	}
}
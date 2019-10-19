import java.io.*;
import java.util.Scanner;

public class UserAccount {
	private String email;
	private String password;
	private String username;
	private int type; //0 is user, 1 is breeder
	private boolean loggedIn;
	
	public void logMeIn() {
		Scanner in = new Scanner(System.in);
		char ch;
		loggedIn = true;
		
		if (!userExists()) { //if the user entered doesn't exist
			System.out.println("User doesn't exist, create an account? (y/n)");
			ch = in.nextLine().charAt(0);
			if (ch == 'y') {
				System.out.print("Email: ");
				email = in.nextLine();
				createAccount(); //create new account with given info and email
			} else
				loggedIn = false; //did not create account, tries again
		} else {
			while (!checkPass()) { //is the password wrong?
				System.out.println("Wrong password, try again...");
				System.out.print("Password: ");
				password = in.nextLine();
			}
			type = checkType(); //gets stored account type
		}
		if (loggedIn) //if logged
			System.out.println("Logged into account with username: " + username);
	}
	
	public void logMeOut() {
		loggedIn = false;
	}
	
	public void newDog(int dogNum) {
		File file = new File(username + "/dogs.fch");
		BufferedWriter out;
		
		try {
			if(!file.exists())
				out = new BufferedWriter(new FileWriter(file));
			else
				out = new BufferedWriter(new FileWriter(file, true));
			out.write("d" + String.format("%04d", dogNum));
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Error with IO in UserAccount.newDog");
		}
	}
	
	public UserAccount(String email, int type) {
		this();
		this.email = email;
		this.type = type;
		createAccount();
	}
	
	public UserAccount() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Username: ");
		username = in.nextLine();
		System.out.print("Password: ");
		password = in.nextLine();
	}
	
	private boolean userExists() {
		File[] directories = new File(System.getProperty("user.dir")).listFiles(File::isDirectory);
		
		for (int i = 0; i < directories.length; i++) {
			if (directories[i].getName().toLowerCase().equals(username.toLowerCase()))
				return true;
		}
		return false;
	}

	private boolean checkPass() {
		boolean correctPass = false;
		BufferedReader in;

		try {
			in = new BufferedReader(new FileReader(username + "/account.fch"));
			if (in.readLine().equals(password))
				correctPass = true;
			in.close();
		} catch (IOException e) {
			System.out.println("Error with IO in UserAccount.checkPass");
		}
		return correctPass;
	}
	
	private int checkType() {
		BufferedReader in;
		int temp = 0;
		
		try {
			in = new BufferedReader(new FileReader(username + "/account.fch"));
			in.readLine();
			in.readLine();
			temp = Integer.parseInt(in.readLine());
			in.close();
		} catch (IOException e) {
			System.out.println("Error with IO in UserAccount.checkType");
		}
		return temp;
	}
	
	private void createAccount() {
		BufferedWriter out;
		
		try {
			new File(username).mkdir();
			out = new BufferedWriter(new FileWriter(username + "/account.fch"));
			out.write(password);
			out.newLine();
			out.write(email);
			out.newLine();
			out.write(String.valueOf(type));
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Error with IO in UserAccount.createAccount");
		}
	}
	
	public String getUser() {
		return username;
	}
	
	public void setUser(String user) {
		username = user;
	}
	
	public String getPass() {
		return password;
	}
	
	public void setPass(String pass) {
		password = pass;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String mail) {
		email = mail;
	}
	
	public String getType() {
		if (type == 1)
			return "Breeder";
		return "User";
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}

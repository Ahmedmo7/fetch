import java.io.*;
import java.util.Scanner;

public class DogHandler {
	private int counter = 0;
	private int totalDogs = 0;
	private final String PATH = "dogs/d";
	private File file;
	
	public DogHandler() {
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader("dogs/genInfo.fch"));
			totalDogs = Integer.parseInt(in.readLine());
			in.close();
		} catch (IOException e) {
			System.out.println("Error with IO in DogHandler constructor");
		}
	}
	
	public void removeDog(UserAccount user, String removeID) {
		Dog[] dogs = getDogs(user);
		BufferedWriter out;
		File file = new File(user.getUser() + "/dogs.fch");
		
		try {
			out = new BufferedWriter(new FileWriter(file));
			for (Dog i : dogs) {
				if (!i.getID().equals(removeID)) {
					out.write(i.getID());
					out.newLine();
				}
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Error with IO in DogHandler.removeDog");
		}
	}
	
	public Dog[] getDogs(UserAccount user) {
		BufferedReader in;
		Dog[] dogs = new Dog[totalDogs];
		String line = "";
		int newMax = 0;
		
		try {
			in = new BufferedReader(new FileReader(user.getUser() + "/dogs.fch"));
			for (int i = 0; i < totalDogs; i++) {
				line = in.readLine();
				if (line == null) {
					newMax = i;
					break;
				}
				dogs[i] = new Dog(new File("dogs/" + line + "/info.fch"));
			}
		} catch (IOException e) {
			System.out.println("Error with IO in DogHandler.getDogs");
		}
		
		Dog[] truncated = new Dog[newMax];
		for (int i = 0; i < newMax; i++) {
			truncated[i] = dogs[i];
		}
		return truncated;
	}
	
	public Dog nextDog() {
		counter++;
		file = new File(PATH + String.format("%04d", counter) + "/info.fch");
		if (counter <= totalDogs) 
			return new Dog(file);
		return null;
	}
	
	public void newDog(UserAccount user) {
		totalDogs++;
		new File(PATH + String.format("%04d", totalDogs)).mkdir();
		Scanner in = new Scanner(System.in);
		BufferedWriter out;
		System.out.println("Enter the new dog's info...");
		
		try {
			out = new BufferedWriter(new FileWriter(PATH + String.format("%04d", totalDogs) + "/info.fch"));
			System.out.print("Name: ");
			out.write(in.nextLine());
			out.newLine();
			System.out.print("Breed: ");
			out.write(in.nextLine());
			out.newLine();
			System.out.print("Age: ");
			out.write(in.nextLine());
			out.newLine();
			System.out.print("Gender: ");
			out.write(in.nextLine());
			out.newLine();
			System.out.print("Info: ");
			out.write(in.nextLine());
			out.newLine();
			out.close();
			out = new BufferedWriter(new FileWriter("dogs/genInfo.fch"));
			out.write(String.valueOf(totalDogs));
			out.close();
		} catch (IOException e) {
			System.out.println("Error with IO in DogHandler.newDog");
		}
		user.newDog(totalDogs);
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getTotalDogs() {
		return totalDogs;
	}
	public void setTotalDogs(int totalDogs) {
		this.totalDogs = totalDogs;
	}
	public File getFile() {
		return file;
	}
}
import java.io.File;

public class Matchmaker {
	private Dog dog;
	private int counter = 0;
	private UserAccount user;
	private File file;
	
	public Matchmaker(UserAccount user) {
		this.user = user;
	}
	
	public Dog nextDog() {
		counter++;
		file = new File("dogs/d" + String.format("%04d", counter) + ".fch");
		if (file.exists()) {
			return new Dog(file);
		}
		return null;
	}
	
	public String getUser() {
		return user.getUser();
	}

	public File getFile() {
		return file;
	}
	
}

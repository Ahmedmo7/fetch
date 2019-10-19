import java.io.*;

public class Dog {
	private String name;
	private String breed;
	private String age;
	private String gender;
	private String info;
	
	public Dog(File filename) {
		BufferedReader in;
		
		try {
			in = new BufferedReader(new FileReader(filename));
			name = in.readLine();
			breed = in.readLine();
			age = in.readLine();
			gender = in.readLine();
			info = in.readLine();
			in.close();
		} catch (IOException e) {
			System.out.println("Error with IO");
		}
	}
	
	public void addLike(File filename, String user) {
		BufferedWriter out;
		String fName = filename.toString();
		fName = fName.substring(0, fName.length() - 4);
		filename = new File(fName + ".lks");
		
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write(user);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Error with IO");
		}
	}
	
	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Breed: " + breed);
		System.out.println("Age: " + age);
		System.out.println("Gender: " + gender);
		System.out.println("Info: " + info);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}

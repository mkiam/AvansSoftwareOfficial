
public class Reference {

	private String author = "";
	private String name = "";
	private int year;
	private String reference;
	
	public Reference(){
		
	}
	
	public Reference(String reference){
		this.reference = reference;
	}
	
	public Reference(String author, String name, int year){
		this.author = author;
		this.name = name;
		this.year = year;
	}

	public void refDescription(){
		
	}
	
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Reference [author=" + author + ", name=" + name + ", year="
				+ year + "]";
	}
	
	
}

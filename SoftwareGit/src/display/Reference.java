package display;

public class Reference {

	private String author = "";
	private String name = "";
	private String year="";
	
	public Reference(){
		
	}
	
	
	public Reference(String author, String name, String year){
		this.author = author;
		this.name = name;
		this.year = year;
	}

	public void extractAttributes(String reference){
		author = "";
		name = "";
		String token="";
		boolean separator = false;
		int i=0, l=reference.length();
		//System.out.println(l);
		char c = ' ';
		if (reference.length()<4){
			return;
		} else {
			while (c!='“' && i<l){
				c = reference.charAt(i);
				author = author  +c;
				++i;
			}
			author = author.substring(3, author.length()-2);
			int idX = i;
			reference = reference.substring(i-1, reference.length());
			i=0;
			while (c!='”' && i<l){
				c = reference.charAt(i);
				//System.out.print(c);
				name = name + c;
				++i;
			}
			//System.out.println();
			reference = reference.substring(i);
			i=0;
			while (c!=',' && i<l && !Character.isDigit(c)){
				c = reference.charAt(i);
				//System.out.print(c);
				name = name + c;
				++i;
			}
			//System.out.println();
			reference = reference.substring(i-1);
			i=0;
			/*while (!isNumeric(reference) || i<l){
				reference = reference.substring(i);
				System.out.println(reference);
				i;
			}*/
			reference = reference.substring(0, reference.lastIndexOf('.'));
			year = reference;
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Reference [author=" + author + ", name=" + name  +", year="
				 +year+  "]";
	}
	
	
}
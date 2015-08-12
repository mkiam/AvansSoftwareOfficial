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
		year = "";
		String token="";
		boolean parenthese = false;
		int adjustment=0;
		boolean separator = false;
		int i=0, l=reference.length();
		//System.out.println(l);
		char c = ' ';
				
		for (int x=0; x<reference.length(); x++){
			if ( reference.charAt(x) == '('){
				parenthese = true;
				adjustment = x;
			}
		}
		
		
		/*if ((reference.substring(adjustment, reference.length()).length() - reference.substring(0, adjustment).length())< 0 ){
			parenthese = false;
			System.out.println("comparaison: "+(reference.substring(0, adjustment).length() - reference.substring(adjustment, reference.length()).length())+" & "+reference.substring(adjustment, reference.length()).length()*2);
		}*/
				
		if (reference.length()<4){
			return;
		} else if (!parenthese ){
			System.out.println("non parenthese");
			
			/*for (int x=0; x<reference.length(); x++){
				if ( reference.charAt(x) == '('){
					parenthese = true;
				}
			}
			if (parenthese){
				System.out.println("parenthese dans non parenthese");
				int entrant=0;
				int fermant = 0;
				for (int x=0; x<reference.length(); x++){
					if (reference.charAt(x)=='('){
						entrant = x;
						while (reference.charAt(x)!=')'){
							year += reference.charAt(x);
							x++;
						}
						year += reference.charAt(x);
						x ++;
						fermant = x;
					}
				}
				System.out.println("entrant: "+entrant+" fermant: "+fermant+" length()-1: "+(reference.length()-1));
				author += reference.substring(0, entrant);
				name += reference.substring(fermant, reference.length()-1);*/
			//} else  {
			
				token = reference;
				while ((c!='"' || c!='.') && i<l){
					c = token.charAt(i);
					author = author + c;
					++i;
				}
				author = author.substring(3, author.length()-2);
				int idX = i;
				token = reference.substring(i-1, reference.length());
				i=0;
				while ((c!='"' || c!='.') && i<token.length()){
					c = token.charAt(i);
					//System.out.print(c);
					name = name + c;
					++i;
				}
				//System.out.println();
				token = token.substring(i);
				i=0;
				while (c!=',' && i<token.length() && !Character.isDigit(c)){
					c =token.charAt(i);
					//System.out.print(c);
					name = name + c;
					++i;
				}
				//System.out.println();
				token = token.substring(i);
				i=0;
				/*while (!isNumeric(reference) || i<l){
					reference = reference.substring(i);
					System.out.println(reference);
					++i;
				}*/
				token = token.substring(0, token.length());
				year = token;
			//}
		} else if (parenthese){
			System.out.println("parenthese");
			int entrant=0;
			int fermant = 0;
			for (int x=0; x<reference.length(); x++){
				if (reference.charAt(x)=='('){
					entrant = x;
					while (reference.charAt(x)!=')'){
						year += reference.charAt(x);
						x++;
					}
					year += reference.charAt(x);
					x ++;
					fermant = x;
				}
			}
			author += reference.substring(0, entrant);
			name += reference.substring(fermant, reference.length()-1);
			//System.out.println(year);
			/*while (c!='('  && i<l){
				c = reference.charAt(i);
				author = author + c;
				++i;
			}*/
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
		return "Reference [author=" + author + ", name=" + name + ", year="
				+ year + "]";
	}
	
	
}

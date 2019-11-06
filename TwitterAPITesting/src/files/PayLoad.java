package files;

public class PayLoad {

	public static String getPostData() {
		String addPlace = "{\r\n" + 
				"	\"location\": {\r\n" + 
				"		\"lat\" : -38.383494,\r\n" + 
				"		\"lng\" : 33.427362\r\n" + 
				"	},\r\n" + 
				"	\r\n" + 
				"	\"accuracy\" : 50,\r\n" + 
				"	\"name\" : \"Frontline House\",\r\n" + 
				"	\"phone_number\" : \"(+1) 757-846-6162\",\r\n" + 
				"	\"address\" : \"1234 Main Street, Hampton, VA, 23664\",\r\n" + 
				"	\"types\" : [\"Shoe Park\", \"Shop\"],\r\n" + 
				"	\"website\" : \"http://google.com\",\r\n" + 
				"	\"language\" : \"French-IN\"\r\n" + 
				"}";
		
		return addPlace;
	}
	
	public static String AddBook(String book, String isbn, String aisle, String author) {
		
		String payLoad = "{\r\n" + 
				"	\"name\" : \"" + book + "\",\r\n" + 
				"	\"isbn\" : \"" + isbn + "\",\r\n" + 
				"	\"aisle\" : \"" + aisle + "\",\r\n" + 
				"	\"author\" : \"" + author + "\"\r\n" + 
				"}";
		
		return payLoad;
	}
	
	public static String DeleteBook(String isbn, String aisle) {
		String id_number = "{\r\n" + 
				"	\"ID\" : \"" + isbn + aisle + "\"\r\n" + 
				"}";
		
		return id_number;
	}
	
}

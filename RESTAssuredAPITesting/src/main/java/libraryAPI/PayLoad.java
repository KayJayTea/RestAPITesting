package libraryAPI;

public class PayLoad {

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

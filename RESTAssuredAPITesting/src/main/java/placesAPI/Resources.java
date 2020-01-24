package placesAPI;

public class Resources {
	
	public static String placePostData_JSON() {
		String placePost = "/maps/api/place/add/json";
		
		return placePost;
	}
	
	public static String placePostData_XML() {
		String placePost = "/maps/api/place/add/xml";
		
		return placePost;
	}
	
	public static String deletePostData_JSON() {
		String deletePost = "/maps/api/place/delete/json";
		
		return deletePost;
	}
	
	public static String deletePostData_XML() {
		String deletePost = "/maps/api/place/delete/xml";
		
		return deletePost;
	}
}

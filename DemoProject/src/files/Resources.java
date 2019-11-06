package files;

public class Resources {
	
	public static String searchNearby() {
		String search = "/maps/api/place/nearbysearch/json";
		return search;
	}

	public static String placePostData() {
		String placePost = "/maps/api/place/add/json";
		
		return placePost;
	}
	
	public static String deletePostData() {
		String deletePost = "/maps/api/place/delete/json";
		
		return deletePost;
	}
}

package files;

public class Resources {
	
	public static String PostTweet() {
		String post = "/update.json";
		return post;
	}
	
	public static String SearchUserTweets() {
		String search = "/user_timeline.json";
		return search;
	}
	
	public static String DeleteTweetByID(String id) {
		String delete = "destroy/" + id + ".json";
		return delete;
	}
	
}

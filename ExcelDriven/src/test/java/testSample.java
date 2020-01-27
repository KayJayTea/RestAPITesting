import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
		
		data_driven d  = new data_driven();
		
		@SuppressWarnings("rawtypes")
		ArrayList data = d.getData("Purchase");
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		System.out.println(data.get(4));

	}

}

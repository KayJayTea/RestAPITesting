package alpha;

import org.apache.logging.log4j.*;

public class Demo {
	
	private static Logger log = LogManager.getLogger(Demo.class.getName());
	
	public static void main(String[] args) {
		log.trace("TRACE");
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
		log.fatal("FATAL");
		
		System.out.println("Test complete");
	}

}

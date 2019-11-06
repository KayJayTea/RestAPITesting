package beta;

import org.apache.logging.log4j.*;

public class Demo2 {
	
	private static Logger log = LogManager.getLogger(Demo2.class.getName());
	
	public static void main(String[] args) {
		log.trace("TRACE");
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
		log.fatal("FATAL\n");
	}

}

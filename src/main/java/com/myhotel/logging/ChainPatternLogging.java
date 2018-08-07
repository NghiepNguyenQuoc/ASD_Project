package com.myhotel.logging;

import com.myhotel.patterns.COR.AbstractLogger;
import com.myhotel.patterns.COR.ConsoleLogger;
import com.myhotel.patterns.COR.ErrorLogger;
import com.myhotel.patterns.COR.FileLogger;

public class ChainPatternLogging {
	public static AbstractLogger getChainOfLoggers(){

	      AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
	      AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
	      AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

	      errorLogger.setNextLogger(fileLogger);
	      fileLogger.setNextLogger(consoleLogger);

	      return errorLogger;	
	   }
}

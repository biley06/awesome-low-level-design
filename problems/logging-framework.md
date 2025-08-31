# Designing a Logging Framework

## Requirements
1. The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
2. It should allow logging messages with a timestamp, log level, and message content.
3. The framework should support multiple output destinations, such as console, file, and database.
4. It should provide a configuration mechanism to set the log level and output destination.
5. The logging framework should be thread-safe to handle concurrent logging from multiple threads.
6. It should be extensible to accommodate new log levels and output destinations in the future.

## Notes from Biley
1. Another yoututbe video to refer: https://youtu.be/RljSBrZeJ3w?si=HkyoBVq1EBFYtOBs  and its code at: https://github.com/TheTechGranth/thegranths/tree/master/src/main/java/SystemDesign/LoggingFramework
2. You do not need to do async works.
3. LogManager --> singleton, and manages the loggers
4. Logger --> Each Logger behaves like a singleton per name (multiton), ie, there is only one logger per class name. Thus, when we call LogManager.getLogger(class name), we either create a logger or get the one.
5. This logger has configurations like list of appenders. This is observer design pattern. Once we decide that we need to log some message based on the log level, then we notify all appenders about this message and they display it.
6. Each appender is configured with a formatter, ie, how the message should be formatted for it. All these configurations are done when the logger is created first time for a class by the manager.
7. We can also add chain of responsibility pattern here, ie, the message flows through the chain till the final level is reached, and if it gets printed somewehre then we break. Else if it is not picked by any logger till that level, then its rejected. In that case, the Abstract logger will have the list of appenders and the Logger will have this abstract logger.

## UML Class Diagram

![](../class-diagrams/loggingframework-class-diagram.png)

## Implementations
#### [Java Implementation](../solutions/java/src/loggingframework/) 
#### [Python Implementation](../solutions/python/loggingframework/)
#### [C++ Implementation](../solutions/cpp/loggingframework/)
#### [C# Implementation](../solutions/csharp/loggingframework/)
#### [Go Implementation](../solutions/golang/loggingframework/)
#### [TypeScript Implementation](../solutions/typescript/src/LoggingFramework/)

## Classes, Interfaces and Enumerations
1. The **LogLevel** enum defines the different log levels supported by the logging framework.
2. The **LogMessage** class represents a log message with a timestamp, log level, and message content.
3. The **LogAppender** interface defines the contract for appending log messages to different output destinations.
4. The **ConsoleAppender**, **FileAppender**, and **DatabaseAppender** classes are concrete implementations of the LogAppender interface, supporting logging to the console, file, and database, respectively.
5. The **LoggerConfig** class holds the configuration settings for the logger, including the log level and the selected log appender.
6. The **Logger** class is a singleton that provides the main logging functionality. It allows setting the configuration, logging messages at different levels, and provides convenience methods for each log level.
7. The **LoggingExample** class demonstrates the usage of the logging framework, showcasing different log levels, changing the configuration, and logging from multiple threads.

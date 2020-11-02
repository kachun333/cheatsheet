import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public Logger getLogger() {
        if (isFileLoggingEnabled()) return new FileLogger();
        return new ConsoleLogger();
    }

    public boolean isFileLoggingEnabled() {
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResourceAsStream("logger.properties"));
            String fileLoggingValue = p.getProperty("FileLogging");
            return fileLoggingValue.equalsIgnoreCase("ON");
        } catch (IOException e) {
            return false;
        }
    }
}

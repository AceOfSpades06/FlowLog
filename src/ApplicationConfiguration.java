import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfiguration {
    private String flowLogReadPath;
    private String lookupTablePath;
    private String protocols;
    private String tagOutput;
    private String portProtocolOutput;

    public ApplicationConfiguration() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            this.flowLogReadPath = properties.getProperty("flowLogReadPath");
            this.lookupTablePath = properties.getProperty("lookupTablePath");
            this.protocols = properties.getProperty("protocols");
            this.tagOutput = properties.getProperty("tagOutput");
            this.portProtocolOutput = properties.getProperty("portProtocolOutput");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for the properties
    public String getFlowLogReadPath() {
        return flowLogReadPath;
    }

    public String getLookupTablePath() {
        return lookupTablePath;
    }

    public String getProtocols() {
        return protocols;
    }

    public String getTagOutput() {
        return tagOutput;
    }

    public String getPortProtocolOutput() {
        return portProtocolOutput;
    }
}
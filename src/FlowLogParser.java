import controller.FileFlowLogEmitter;
import controller.FileLookupTable;
import controller.FlowLogEmitter;
import controller.LookupTable;
import model.FlowLog;
import model.Key;

import java.util.HashMap;
import java.util.Map;

public class FlowLogParser {

    private ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
    private FlowLogEmitter flowLogEmitter;
    private Map lookupTable;
    private ProtocolMap protocolMap;
    private ParsedOutput parsedOutput;

    public FlowLogParser() {
        flowLogEmitter = new FileFlowLogEmitter(applicationConfiguration.getFlowLogReadPath());
        LookupTable Lookup = new FileLookupTable(applicationConfiguration.getLookupTablePath());
        lookupTable = Lookup.getLookupTable();
        protocolMap = new ProtocolMap(applicationConfiguration.getProtocols());
        parsedOutput = new ParsedOutput(applicationConfiguration.getTagOutput(), applicationConfiguration.getPortProtocolOutput());
    }

    public void parse() {
        FlowLog flowLog = flowLogEmitter.emit();
        Map<String, Integer> tagCount = new HashMap<>();
        Map<Key, Integer> portProtocolCount = new HashMap<>();
        while(flowLog != null){
            String protocolName = protocolMap.getProtocolName(flowLog.protocol());
            Key key = new Key(flowLog.dstport(), protocolName);
            String tag = (String) lookupTable.get(key);
            if(tag != null){
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }
            else{
                tagCount.put("Untagged", tagCount.getOrDefault("Untagged", 0) + 1);
            }
            portProtocolCount.put(key, portProtocolCount.getOrDefault(key, 0) + 1);
            flowLog = flowLogEmitter.emit();
        }
        parsedOutput.writeTagOutput(tagCount);
        parsedOutput.writePortProtocolOutput(portProtocolCount);
    }


}

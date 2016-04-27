package org.hibersap.examples.datamodel;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;

import static org.hibersap.annotations.ParameterType.STRUCTURE;

@Bapi("RFC_GET_SYSTEM_INFO")
public class RfcGetSystemInfo {

    @Import
    @Parameter("DESTINATION")
    private String destination;

    @Import
    @Parameter(value = "RFCSI_EXPORT", type = STRUCTURE)
    private RfcsiExport rfcsiExport;

    @Import
    @Parameter("DEST_COMMUNICATION_MESSAGE")
    private String communicationMsg;

    @Import
    @Parameter("DEST_SYSTEM_MESSAGE")
    private String systemMsg;

    public RfcGetSystemInfo(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public RfcsiExport getRfcsiExport() {
        return rfcsiExport;
    }

    public String getCommunicationMsg() {
        return communicationMsg;
    }

    public String getSystemMsg() {
        return systemMsg;
    }

    @BapiStructure
    public static class RfcsiExport {
        @Parameter("RFCHOST")
        private String host;

        @Parameter("RFCOPSYS")
        private String operationSystem;

        @Parameter("RFCIPADDR")
        private String ipAddress;

        public String getHost() {
            return host;
        }

        public String getOperationSystem() {
            return operationSystem;
        }

        public String getIpAddress() {
            return ipAddress;
        }
    }
}

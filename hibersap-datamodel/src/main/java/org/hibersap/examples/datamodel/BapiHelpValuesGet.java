package org.hibersap.examples.datamodel;

import org.hibersap.annotations.*;

import java.util.List;

@Bapi("BAPI_HELPVALUES_GET")
public class BapiHelpValuesGet {

    @Import
    @Parameter("OBJTYPE")
    private String objectType;

    @Import
    @Parameter("OBJNAME")
    private String objectName;

    @Import
    @Parameter("METHOD")
    private String method;

    @Import
    @Parameter("PARAMETER")
    private String parameter;

    @Import
    @Parameter("FIELD")
    private String field;

    @Table
    @Parameter("HELPVALUES")
    private List<HelpValue> helpValues;

    public BapiHelpValuesGet(String objectType, String objectName, String method, String parameter, String field) {
        this.objectType = objectType;
        this.objectName = objectName;
        this.method = method;
        this.parameter = parameter;
        this.field = field;
    }

    public List<HelpValue> getHelpValues() {
        return helpValues;
    }

    @BapiStructure
    public static class HelpValue {

        @Parameter("HELPVALUES")
        private String value;

        public String getValue() {
            return value;
        }
    }

}

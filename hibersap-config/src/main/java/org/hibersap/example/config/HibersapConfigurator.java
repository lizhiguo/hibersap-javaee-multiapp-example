package org.hibersap.example.config;

import com.sap.conn.jco.ext.DestinationDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.examples.datamodel.BapiHelpValuesGet;
import org.hibersap.examples.datamodel.RfcGetSystemInfo;
import org.hibersap.execution.jco.JCoContext;
import org.hibersap.session.SessionManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static com.sap.conn.jco.ext.DestinationDataProvider.*;
import static org.hibersap.configuration.xml.ValidationMode.NONE;

@Singleton
@Startup
public class HibersapConfigurator {

    private static final String SESSION_MANAGER_JNDI_NAME = "java:global/hibersap/SessionManagerNSP";

    private static final Log LOG = LogFactory.getLog(HibersapConfigurator.class);

    @PostConstruct
    public void createSessionManagerAndBindToJndi() {

/*
        System.getProperties().stringPropertyNames().forEach(name -> System.out.println("###   " + name + "=" + System.getProperty(name)));


        System.setProperty("java.library.path", "/Users/carsten/devel/appserver/wildfly-10.0.0.Final-Hibersap/modules/org/hibersap/main/lib");
*/
        LOG.info("# java.library.path=" + System.getProperty("java.library.path"));

        try {
            LOG.info("## " + Class.forName(AnnotationConfiguration.class.getName()));
            LOG.info("## " + Class.forName(JCoContext.class.getName()));
            LOG.info("## " + Class.forName(DestinationDataProvider.class.getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SessionManagerConfig sessionManagerConfig = new SessionManagerConfig("NSP")
                .setProperty(JCO_CLIENT, "001")
                .setProperty(JCO_USER, "sapuser")
                .setProperty(JCO_PASSWD, "password")
                .setProperty(JCO_LANG, "EN")
                .setProperty(JCO_ASHOST, "192.168.101.130")
                .setProperty(JCO_SYSNR, "00")
                .setProperty(JCO_POOL_CAPACITY, "1")
                .setProperty(JCO_PEAK_LIMIT, "1")
                .setValidationMode(NONE);
        AnnotationConfiguration config = new AnnotationConfiguration(sessionManagerConfig);
        config.addBapiClasses(RfcGetSystemInfo.class, BapiHelpValuesGet.class);
        SessionManager sessionManager = config.buildSessionManager();

        try {
            InitialContext initialContext = new InitialContext();
            initialContext.bind(SESSION_MANAGER_JNDI_NAME, sessionManager);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void unbindSessionManagerFromJndi() {
        try {
            InitialContext initialContext = new InitialContext();
            initialContext.unbind(SESSION_MANAGER_JNDI_NAME);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}

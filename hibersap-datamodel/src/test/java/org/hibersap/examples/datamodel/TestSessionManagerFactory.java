package org.hibersap.examples.datamodel;

import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.session.SessionManager;

import static com.sap.conn.jco.ext.DestinationDataProvider.*;

public class TestSessionManagerFactory {

    public static SessionManager buildHibersapSessionManager(Class<?>... bapiClasses) {
        SessionManagerConfig sessionManagerConfig = new SessionManagerConfig("NSP")
                .setProperty(JCO_CLIENT, "001")
                .setProperty(JCO_USER, "sapuser")
                .setProperty(JCO_PASSWD, "password")
                .setProperty(JCO_LANG, "EN")
                .setProperty(JCO_ASHOST, "192.168.101.130")
                .setProperty(JCO_SYSNR, "00")
                .setProperty(JCO_POOL_CAPACITY, "1")
                .setProperty(JCO_PEAK_LIMIT, "1");

        AnnotationConfiguration config = new AnnotationConfiguration(sessionManagerConfig);
        config.addBapiClasses(bapiClasses);
        return config.buildSessionManager();
    }
}

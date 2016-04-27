package org.hibersap.examples.datamodel;

import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibersap.examples.datamodel.TestSessionManagerFactory.buildHibersapSessionManager;

public class BapiHelpValuesGetTest {

    private SessionManager sessionManager = buildHibersapSessionManager(BapiHelpValuesGet.class);
    private Session session;

    @Before
    public void setUp() throws Exception {
        session = sessionManager.openSession();
    }

    @After
    public void tearDown() throws Exception {
        session.close();
        sessionManager.close();
    }

    @Test
    public void getsHelpValuesForCustomerCountry() throws Exception {
        BapiHelpValuesGet bapi = new BapiHelpValuesGet("SCUSTOMER", "FLIGHTCUSTOMER", "GETLIST", "CUSTOMERLIST", "COUNTR");

        session.execute(bapi);

        assertThat(bapi.getHelpValues()).isNotEmpty();
        assertThat(bapi.getHelpValues()).extracting(BapiHelpValuesGet.HelpValue::getValue).contains("AN  Dutch Antilles");
    }
}
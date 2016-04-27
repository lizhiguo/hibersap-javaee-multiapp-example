package org.hibersap.examples.datamodel;

import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibersap.examples.datamodel.TestSessionManagerFactory.buildHibersapSessionManager;

public class RfcGetSystemInfoTest {

    private SessionManager sessionManager = buildHibersapSessionManager(RfcGetSystemInfo.class);
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
    public void getsSystemInfoForSapSystemNsp() throws Exception {
        RfcGetSystemInfo rfcGetSystemInfo = new RfcGetSystemInfo("NSP");

        session.execute(rfcGetSystemInfo);

        assertThat(rfcGetSystemInfo.getCommunicationMsg()).isEmpty();
        assertThat(rfcGetSystemInfo.getSystemMsg()).isEmpty();
        assertThat(rfcGetSystemInfo.getRfcsiExport().getHost()).isEqualTo("WinXPPro");
        assertThat(rfcGetSystemInfo.getRfcsiExport().getIpAddress()).isEqualTo("192.168.101.130");
        assertThat(rfcGetSystemInfo.getRfcsiExport().getOperationSystem()).isEqualTo("Windows NT");
    }
}
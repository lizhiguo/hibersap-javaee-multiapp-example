package org.hibersap.example.app1;

import org.hibersap.examples.datamodel.RfcGetSystemInfo;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class SapResource {

    @Resource(lookup = "java:global/hibersap/SessionManagerNSP")
    private SessionManager sessionManager;

    @GET
    @Path("/info")
    @Produces("application/json")
    public RfcGetSystemInfo getSystemInfo() {
        RfcGetSystemInfo bapi = new RfcGetSystemInfo("NSP");
        Session session = sessionManager.openSession();
        try {
            session.execute(bapi);
            return bapi;
        } finally {
            session.close();
        }
    }

    @GET
    @Path("/")
    @Produces("text/plain")
    public String sayHello() {
        return "Hello!";
    }
}

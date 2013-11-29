/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.ssase.hb.service.client;

import junit.framework.TestCase;

/**
 *
 * @author cly-vs
 */
public class TestAlienClient 
    extends TestCase
{

    public TestAlienClient() {
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void testAlienClient() {
        AlienClient alienClient = new AlienClient();
        alienClient.synchronizeWithDatabase();
    }
}

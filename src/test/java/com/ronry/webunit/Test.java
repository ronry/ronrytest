package com.ronry.webunit;

import junit.framework.TestCase;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class Test extends TestCase {

    public void testAaaa() throws Exception {
        WebConversation wc = new WebConversation();
        WebResponse wr = wc.getResponse("http://www.meterware.com");
        System.out.println(wr.getText());
    }
}

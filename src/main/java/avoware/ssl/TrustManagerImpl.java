/**
 *     
 *    All intellectual property rights in the Software are protected by
 *    international copyright laws.
 *
 *
 *    Please make sure that third-party modules and libraries are used
 *    according to their respective licenses.
 * 
 *    Any modifications to this package must retain all copyright notices 
 *    of the original copyright holder(s) for the original code used.
 *    
 *    After any such modifications, the original code will still remain
 *    copyrighted by the copyright holder(s) or original author(s).
 *
 *
 *     Copyright (C) 2010 - 2012 Andrew Orlov
 *     mail:	                 andrew.v.orlov@gmail.com
 * 
 *
 *     This program is free software; you can redistribute it and/or modify it
 *     under the terms of the GNU General Public License as published by the Free
 *     Software Foundation; either version 2 of the License, or (at your option)
 *     any later version.
 * 
 *     This program is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *     or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 * 
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc., 59
 *     Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 *
 */

package avoware.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;

public class TrustManagerImpl implements X509TrustManager {

    /**
     * The default X509TrustManager returned by SunX509.  We'll delegate
     * decisions to it, and fall back to the logic in this class if the
     * default X509TrustManager doesn't trust it.
     */
    private X509TrustManager defaultX509TrustManager = null;

    /**
     * Our custom X509TrustManager returned by SunX509. It's based
     * on custom keystore.
     */
    private X509TrustManager customX509TrustManager = null;

    /**
     * Our custom keystore file:
     */
    private File _keyStoreFile = null;
    private char[] _keyStorePwd = null;
    private KeyStore ks = null;

    public TrustManagerImpl(File keyStoreFile, 
            char[] keyStorePwd) throws Exception {

        _keyStoreFile = keyStoreFile;
        _keyStorePwd = keyStorePwd;

        // create a "default" JSSE X509TrustManager.
        defaultX509TrustManager = initializeTrustManager(null);

        ks = KeyStore.getInstance("JKS");
        try {
            ks.load(new FileInputStream(keyStoreFile), keyStorePwd);
        } catch (FileNotFoundException ex) {
            ks.load(null, keyStorePwd);
        }

        // create a "custom" JSSE X509TrustManager.
        customX509TrustManager = initializeTrustManager(ks);
    }

    /**
     * Throws an exception if both the TrustManagers failed to trust client.
     * @param cert
     * @param authType
     * @throws CertificateException
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], String)
     **/
    //@Override
    public void checkClientTrusted(X509Certificate[] cert, String authType)
            throws CertificateException {
        try {
            defaultX509TrustManager.checkClientTrusted(cert, authType);
        } catch (CertificateException ex) {
            if (customX509TrustManager != null)
                customX509TrustManager.checkClientTrusted(cert, authType);
            else throw ex;
        }
    }

    /**
     * If only defaultX509TrustManager is defined then the behavior is
     * the same as "standard" - i.e. we trust to system trusted certs only.
     * If customX509TrustManager is defined too (i.e. alternative keystore
     * is used) then we trust to custom trusted certs, or (if cert chain is not
     * found in keystore) ask user if he trusts to untrusted cert.
     * @param cert
     * @param authType
     * @throws CertificateException
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], String)
     **/
    //@Override
    public void checkServerTrusted(X509Certificate[] cert, String authType) 
            throws CertificateException  {
        try {
            defaultX509TrustManager.checkServerTrusted(cert, authType);
        } catch (CertificateException ex) {
            if (customX509TrustManager != null) {
                try {
                    customX509TrustManager.checkClientTrusted(cert, authType);
                } catch (CertificateException ex1) {
                    X509Certificate ca = getCA(cert);
                    String message = 
                            "<html><table cellspacing=8 cellpadding=0 border=0 width=400>"
                            + "<tr><td colspan=2 color=red><strong>Certificate of the resource requested is not trusted!</strong></td></tr>"
                            + "<tr><td valign=top><strong>Subject:</strong></td><td valign=top>"
                            + ca.getSubjectX500Principal().toString() + "</td></tr>"
                            + "<tr><td valign=top><nobr><strong>Valid&nbsp;from:</strong></nobr></td><td valign=top>"
                            + ca.getNotBefore().toString() + "</td></tr>"
                            + "<tr><td valign=top><strong>Valid&nbsp;to:</strong></td><td valign=top>"
                            + ca.getNotAfter().toString() + "</td></tr>"
                            + "<tr><td colspan=2>Do you want to trust it?</td></tr></table></html>";

                    Object[] options = {
                        "Yes, always",
                        "Yes, once",
                        "No"
                    };
                    switch (JOptionPane.showOptionDialog(null, message, "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[2])) {
                        case 0:
                            try {
                                ks.setCertificateEntry(UUID.randomUUID().toString(), ca);
                                ks.store(new FileOutputStream(_keyStoreFile), _keyStorePwd);
                                customX509TrustManager = initializeTrustManager(ks);
                            } catch (Exception ex2) {
                                throw new CertificateException(ex2);
                            }
                            break;
                        case 1:
                            /**
                             * Just return - nothing to do if trust only once.
                             */
                            break;
                        default:
                            throw ex1;
                    }
                }
            } else throw ex;
        }
    }

    /**
     * @return
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     **/
    //@Override
    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] issuers1 = defaultX509TrustManager.getAcceptedIssuers();
        X509Certificate[] issuers2;
        if (customX509TrustManager != null)
            issuers2 = customX509TrustManager.getAcceptedIssuers();
        else issuers2 = new X509Certificate[0];
        X509Certificate[] issuers3 = new X509Certificate[issuers1.length + issuers2.length];
        System.arraycopy(issuers1, 0, issuers3, 0, issuers1.length);
        System.arraycopy(issuers2, 0, issuers3, issuers1.length, issuers2.length);
        return issuers3;
    }


    private X509Certificate getCA(X509Certificate[] chain) {
        X509Certificate ca = chain[chain.length - 1];
        if (ca.getSubjectX500Principal().equals(ca.getIssuerX500Principal()))
            // self-signed root certificate.
            return ca;
        else
            // means that certificate is signed by unknown issuer... TOO DANGEROUS!!!
            // return just server certificate and ask if we trust to this server only.
            return chain[0];
    }

    private X509TrustManager initializeTrustManager(KeyStore ks) throws Exception {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        tmf.init(ks);
        TrustManager[] tms = tmf.getTrustManagers();
        tms = tmf.getTrustManagers();
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
                return (X509TrustManager) tms[i];
            }
        }
        throw new Exception("Couldn't initialize TrustManager");
    }

}

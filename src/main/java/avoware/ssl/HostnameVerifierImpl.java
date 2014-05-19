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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew Orlov
 */
public class HostnameVerifierImpl implements HostnameVerifier {

    private static final Preferences trustedList =
            Preferences.userNodeForPackage(HostnameVerifierImpl.class).node("trustedhosts");

    private static final Object[] options = {
        "Yes, always",
        "Yes, once",
        "No"
    };

    private static final String PREF_HOSTNAME = "hostname";
    private static final String PREF_SSLHOSTNAME = "sslhostname";

    private HashMap<UUID, SavedTrust> savedTrusts =
            new HashMap<UUID, SavedTrust>();

    //@Override
    public boolean verify(String hostname, SSLSession session) {
        String sslHostname;
        try {
            sslHostname = extractCN(session.getPeerPrincipal().toString());
        } catch (SSLPeerUnverifiedException ex) {
            ex.printStackTrace();
            sslHostname = null;
        }
        if (compare(hostname, sslHostname)) return true;
        else {
            if (checkTrusts(hostname, sslHostname)) return true;
            else {
                sync();
                if (checkTrusts(hostname, sslHostname)) return true;
                else {
                    String message =
                            "<html><table cellspacing=8 cellpadding=0 border=0 width=400>"
                            + "<tr><td colspan=2 color=red><strong>Certificate of the resource requested is not trusted!</strong></td></tr>"
                            + "<tr><td valign=top><strong>Certificate hostname:</strong></td><td valign=top>"
                            + sslHostname + "</td></tr>"
                            + "<tr><td valign=top><nobr><strong>Hostname is being connected:</strong></nobr></td><td valign=top>"
                            + hostname + "</td></tr>"
                            + "<tr><td colspan=2>Do you want to trust it?</td></tr></table></html>";

                    switch (JOptionPane.showOptionDialog(null, message, "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[2])) {
                        case 0:
                            // new preference node is created
                            UUID uuid = UUID.randomUUID();
                            Preferences pref = trustedList.node(uuid.toString());
                            pref.put(PREF_HOSTNAME, hostname);
                            pref.put(PREF_SSLHOSTNAME, sslHostname);
                            try {
                                pref.flush();
                            } catch (BackingStoreException ex) {
                                ex.printStackTrace();
                            }
                            savedTrusts.put(uuid, new SavedTrust(hostname, sslHostname));
                            return true;
                        case 1:
                            return true;
                        default:
                            return false;
                    }
                }
            }
        }
    }

    private void sync() {
        try {
            trustedList.sync();
            String[] trusts = trustedList.childrenNames();
            for (int i=0; i<trusts.length; i++) {
                try {
                    // Check if our node is saved trust one (which must be of type UUID.toString())
                    UUID uuid = UUID.fromString(trusts[i]);
                    if (!savedTrusts.containsKey(uuid)) {
                        Preferences pref = trustedList.node(trusts[i]);
                        savedTrusts.put(uuid, 
                                new SavedTrust(pref.get(PREF_HOSTNAME, null), pref.get(PREF_SSLHOSTNAME, null)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkTrusts(String hostname, String sslHostname) {
        Iterator<Map.Entry<UUID, SavedTrust>> iter =
                savedTrusts.entrySet().iterator();
        while(iter.hasNext()) {
            SavedTrust st = iter.next().getValue();
            if (st.check(hostname, sslHostname)) return true;
        }
        return false;
    }

    private String extractCN(String principalName) {
        if (principalName != null && principalName.length() > 0) {
            String lowered = principalName.toLowerCase();
            int start = lowered.indexOf("cn=");
            if (start > -1) {
                int end = lowered.indexOf(",", start);
                return lowered.substring(start + "cn=".length(), end);
            }
        }
        return null;
    }

    private static boolean compare(String str1, String str2) {
        if ((str1 == null && str2 == null)
                || (str1 != null && str1.equalsIgnoreCase(str2)))
            return true;
        else
            return false;
    }

    private class SavedTrust {
        String _hostname, _sslHostname;

        SavedTrust(String hostname, String sslHostname) {
            _hostname = hostname;
            _sslHostname = sslHostname;
        }

        boolean check(String hostname, String sslHostname) {
            if (compare(hostname, _hostname)
                    && compare(sslHostname, _sslHostname))
                return true;
            else
                return false;
        }
    }
}

package provil.be.functions;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Connection {

    //<editor-fold desc="Connection info">
    /*/
    http://developercenter.robotstudio.com/BlobProxy/manuals/RobotStudioOpManual/doc76.html

    Connection possibilities:

    UDP port 5514 (unicast)

    TCP port 5515

    Passive FTP
     */
    //</editor-fold>

    /**
     * Methode om via een FTP verbinding bestanden te verzenden naar de robotarm.
     * @param client FTPClient object
     * @param hostname Hostname van de robotcontroller
     * @param username gebruikersnaam van de ftp gebruiker
     * @param password passwoord van de ftp gebruiker
     * @param file bestand om door te sturen naar de host.
     */

    public static void sendFile(FTPClient client, String hostname, String username, String password, File file) {

        FileInputStream fis = null;

        try

    {
        client.connect(hostname);
        client.login(username, password);
        client.enterLocalPassiveMode();


        //
        // Create an InputStream of the propertiesConfig to be uploaded
        //
        client.setFileType(FTPClient.BINARY_FILE_TYPE);
        for(File f : file.listFiles()) {
            fis = new FileInputStream(f);
            client.storeFile("//hd0a//Motoro//" + f.getName(), fis);
        }
        //
        // Store propertiesConfig to server
        //

        client.logout();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    } finally

    {
        try {
            if (fis != null) {
                fis.close();
            }
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}

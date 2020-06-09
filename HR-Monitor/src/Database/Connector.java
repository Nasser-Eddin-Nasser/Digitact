package Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Connector {
    public static void sendGetHttp(Method method) throws IOException {
        URL bes_url = new URL(Configuration.BES_URI + method.toString());
        switch (method) {
            case getApplicants:
                BufferedReader in = null;
                try {
                    URLConnection uc = bes_url.openConnection();
                    in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    if ((inputLine = in.readLine()) != null) {
                        Util.JSONTools.convertJSONToApplicant(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    in.close();
                }
                break;
            case getAllEducationInfo:
                BufferedReader im = null;
                try {
                    URLConnection uc = bes_url.openConnection();
                    im = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    if ((inputLine = im.readLine()) != null) {
                        Util.JSONTools.convertJSONToEduInfo(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    im.close();
                }
            default:
                break;
        }
    }
}

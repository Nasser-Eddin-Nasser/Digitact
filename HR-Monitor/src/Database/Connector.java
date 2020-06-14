package Database;

import Main.Configuration;
import Storage.DBStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Connector {
    public static void sendGetHttp(Method method, String... params) {
        switch (method) {
            case getApplicants:
                BufferedReader in = null;
                try {
                    URL bes_url = new URL(Configuration.BES_URI + method.toString());
                    URLConnection uc = bes_url.openConnection();
                    in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    if ((inputLine = in.readLine()) != null) {
                        Util.JSONTools.convertJSONToApplicant(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case getImageById:
                BufferedReader in_1 = null;
                try {
                    URL bes_url =
                            new URL(Configuration.BES_URI + method.toString() + "=" + params[1]);
                    URLConnection uc = bes_url.openConnection();
                    in_1 = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    if ((inputLine = in_1.readLine()) != null) {
                        DBStorage.getApplicantByID(Integer.parseInt(params[0]))
                                .getAppImage()
                                .stream()
                                .filter(x -> x.getId().equals(params[1]))
                                .collect(Collectors.toList())
                                .get(0)
                                .setContent(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in_1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            default:
                break;
        }
    }
}

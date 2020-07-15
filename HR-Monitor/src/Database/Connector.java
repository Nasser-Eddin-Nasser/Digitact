package Database;

import Controller.CreateAccountController;
import Main.Configuration;
import Model.Status;
import Model.User.Admin;
import Storage.DBStorage;
import Storage.Token;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Connector {
    public static void sendGetHttp(Method method, String... params) {
        URL bes_url = null;
        switch (method) {
            case gutenMorgen:
                try {
                    Long besNumber =
                            handelPingReq(new URL(Configuration.BES_URI + method.toString()));
                    if (besNumber != null) {
                        DBStorage.setToken(new Token(besNumber));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case getApplicants:
                try {
                    handleGetApplicants(new URL(Configuration.BES_URI + method.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case getImageById:
                try {
                    handleGetImageById(
                            new URL(Configuration.BES_URI + method.toString() + "=" + params[1]),
                            params);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case getAdminByUserName:
                try {
                    handleGetAdminByUserName(
                            new URL(Configuration.BES_URI + method.toString() + "=" + params[0]),
                            params);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case getAdminUserNames:
                try {
                    handleGetAdminUserNames(new URL(Configuration.BES_URI + method.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private static Long handelPingReq(URL url) {
        BufferedReader in = null;
        try {
            URLConnection uc = url.openConnection();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                return Long.parseLong(inputLine);
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void handleGetAdminUserNames(URL url) {
        BufferedReader in = null;
        try {
            URLConnection uc = url.openConnection();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                Util.JSONTools.convertJSONToUserNames(inputLine);
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
    }

    private static void handleGetAdminByUserName(URL bes_url, String[] params) {
        BufferedReader in_1 = null;
        try {
            URLConnection uc = bes_url.openConnection();
            in_1 = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            if ((inputLine = in_1.readLine()) != null) {
                Util.JSONTools.convertJSONToAdmin(inputLine);
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
    }

    private static void handleGetImageById(URL bes_url, String[] params) {
        BufferedReader in_1 = null;
        try {
            URLConnection uc = bes_url.openConnection();
            uc.setRequestProperty("AuthToken", DBStorage.getToken().toString());
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
    }

    private static void handleGetApplicants(URL bes_url) {
        BufferedReader in = null;
        try {
            URLConnection uc = bes_url.openConnection();
            uc.setRequestProperty("AuthToken", DBStorage.getToken().toString());
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
    }

    public static String changeStatus(long appID, Status status) {
    	String message = "";
        try {
            message = handleChangeStatus(new URL(Configuration.BES_URI + Method.changeStatus), appID, status);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return message;
    }

    private static String handleChangeStatus(URL url, long appID, Status status) {
    	StringBuilder response = new StringBuilder();
        try {
            URLConnection uc = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; utf-8");
            http.setRequestProperty("Accept", "application/json");
            String message = "{ \"appID\":" + appID + ",\"status\":\"" + status.toString() + "\"}";

            try (OutputStream os = http.getOutputStream()) {
                byte[] input = message.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br =
                    new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {                
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public static String postHRComment(long appID, String comment) {
    	String message = "";
        try {
        	message = handlePostHRComment(
                    new URL(Configuration.BES_URI + Method.postHRComment), appID, comment);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void sendPutType(Method method, Token token) {
        switch (method) {
            case putToken:
                try {
                    handlePutTokenToAdmin(
                            new URL(Configuration.BES_URI + method.toString()), token);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private static String handlePostHRComment(URL url, long appID, String comment) {
    	StringBuilder response = new StringBuilder();
        try {
            URLConnection uc = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; utf-8");
            http.setRequestProperty("Accept", "application/json");
            String message = "{ \"appID\":" + appID + ",\"comment\":\"" + comment + "\"}";

            try (OutputStream os = http.getOutputStream()) {

                byte[] input = message.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br =
                    new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private static void handlePutTokenToAdmin(URL url, Token token) {
        BufferedReader in = null;
        try {
            URLConnection uc = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; utf-8");
            http.setRequestProperty("Accept", "application/json");
            try (OutputStream os = http.getOutputStream()) {
                String reqBody = token.toString();
                byte[] input = reqBody.getBytes("utf-8");
                os.write(input, 0, input.length);
                try (BufferedReader br =
                        new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPutType(Method method, Admin admin) {
        switch (method) {
            case createAdminAccount:
                try {
                    handleCreateAdmin(new URL(Configuration.BES_URI + method.toString()), admin);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

    private static void handleCreateAdmin(URL bes_url, Admin admin) {
        BufferedReader in = null;
        try {
            URLConnection uc = bes_url.openConnection();
            HttpURLConnection http = (HttpURLConnection) uc;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json; utf-8");
            http.setRequestProperty("Accept", "application/json");
            if (!CreateAccountController.isFirstAccount) {
                http.setRequestProperty("AuthToken", DBStorage.getToken().toString());
            }
            try (OutputStream os = http.getOutputStream()) {
                //   convertAdminToJSON
                byte[] input = Util.JSONTools.convertAdminToJSON(admin).getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // read the Response
            try (BufferedReader br =
                    new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

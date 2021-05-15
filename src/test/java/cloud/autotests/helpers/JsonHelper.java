package cloud.autotests.helpers;

import org.json.simple.JSONObject;

public class JsonHelper {
    public static JSONObject createJSON (String email, String name, String password) {
        JSONObject resultJson = new JSONObject();

        resultJson.put("email",email);
        resultJson.put("name",name);
        resultJson.put("password",password);

        return (resultJson);
    }
}

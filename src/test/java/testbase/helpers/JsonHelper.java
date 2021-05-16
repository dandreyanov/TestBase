package testbase.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class JsonHelper {
    public static JSONObject createJSONdoRegister(String email, String name, String password) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("email", email);
        resultJson.put("name", name);
        resultJson.put("password", password);

        return (resultJson);
    }

    public static JSONObject createJSONcreateCompany(String company_name, String company_type, String firstCompanyUsers, String secondCompanyUsers, String emailOwner) throws IOException {
        JSONObject resultJson = new JSONObject();
        JSONArray companiesArr = new JSONArray();
        companiesArr.add(firstCompanyUsers);
        companiesArr.add(secondCompanyUsers);
        resultJson.put("company_name",company_name);
        resultJson.put("company_type",company_type);
        resultJson.put("email_owner", emailOwner);
        resultJson.put("company_users", companiesArr);

        return (resultJson);
    }

}



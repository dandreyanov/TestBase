package testbase.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class JsonHelper {
    public static JSONObject createJSONdoRegister(String[][] request) {
        JSONObject resultJson = new JSONObject();
        for (int i = 0; i < request.length; i++) {
            for (int j = 0; j < request[i].length-1; j++) {
                resultJson.put(request[i][j], request[i][j + 1]);
            }
        }
        return resultJson;
    }

    public static JSONObject createJSONcreateCompany(String company_name, String company_type, String firstCompanyUsers, String secondCompanyUsers, String emailOwner) throws IOException {
        JSONObject resultJson = new JSONObject();
        JSONArray companiesArr = new JSONArray();
        companiesArr.add(firstCompanyUsers);
        companiesArr.add(secondCompanyUsers);
        resultJson.put("company_name", company_name);
        resultJson.put("company_type", company_type);
        resultJson.put("email_owner", emailOwner);
        resultJson.put("company_users", companiesArr);

        System.out.println(resultJson);
        return (resultJson);
    }


}



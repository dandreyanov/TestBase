package testbase.helpers;

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

    public static String createJSONcreateCompany(String company_name, String company_type, String firstCompanyUsers, String secondCompanyUsers, String emailOwner) throws IOException {
//        resultJson.put("company_name",company_name);
//        resultJson.put("company_type",company_type);
//        resultJson.put("email_owner", emailOwner);
/*
{
  "company_name": "Алкоголики и тунеядцы",
  "company_type": "ООО",
  "company_users": ["test_anna@gmail.com", "mrak20@list.ru"],
  "email_owner": "aa+1@mail.com"
}

 */
        String resultJson = "{\n" +
                "  \"company_name\": \"" + company_name + "\",\n" +
                "  \"company_type\": \"" + company_type + "\",\n" +
                "  \"company_users\": [\"" + firstCompanyUsers + "\", \"" + secondCompanyUsers + "\"],\n" +
                "  \"email_owner\": \"" + emailOwner + "\"\n" +
                "}";

        System.out.println(resultJson);
        return (resultJson);
    }

//    public static JSONObject createJSON (String...vars) {
//        JSONObject resultJson = new JSONObject();
//
//        for (String var: vars) {
//            resultJson.put(var, var);
//        }
//
//        System.out.println(resultJson);
//        return (resultJson);


}



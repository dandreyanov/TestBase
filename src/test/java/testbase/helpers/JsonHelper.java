package testbase.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JsonHelper {
    public static JSONObject createJSONdoRegister(String[][] request) {
        JSONObject resultJson = new JSONObject();
        for (int i = 0; i < request.length; i++) {
            for (int j = 0; j < request[i].length - 1; j++) {
                resultJson.put(request[i][j], request[i][j + 1]);
            }
        }
        return resultJson;
    }

    public static JSONObject createJSONcreateCompany(String company_name, String company_type, String firstCompanyUsers, String secondCompanyUsers, String emailOwner) {
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

    public static JSONObject createJSONcreateUser(String email,
                                                  String name,
                                                  Integer tasks[],
                                                  Integer companies[],
                                                  String hobby,
                                                  String adres,
                                                  String name1,
                                                  String surname1,
                                                  String fathername1,
                                                  String cat,
                                                  String dog,
                                                  String parrot,
                                                  String cavy,
                                                  String hamster,
                                                  String squirrel,
                                                  String phone,
                                                  String inn,
                                                  String gender,
                                                  String birthday,
                                                  String date_start) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("email", email);
        resultJson.put("name", name);
        resultJson.put("hobby", hobby);
        resultJson.put("adres", adres);
        resultJson.put("name1", name1);
        resultJson.put("surname1", surname1);
        resultJson.put("fathername1", fathername1);
        resultJson.put("cat", cat);
        resultJson.put("dog", dog);
        resultJson.put("parrot", parrot);
        resultJson.put("cavy", cavy);
        resultJson.put("hamster", hamster);
        resultJson.put("squirrel", squirrel);
        resultJson.put("phone", phone);
        resultJson.put("inn", inn);
        resultJson.put("gender", gender);
        resultJson.put("birthday", birthday);
        resultJson.put("date_start", date_start);

        JSONArray tasksArr = new JSONArray();
        for (int i = 0; i < tasks.length; i++) {
            tasksArr.add(tasks[i]);
        }
        JSONArray companiesArr = new JSONArray();
        for (int i = 0; i < companies.length; i++) {
            companiesArr.add(companies[i]);
        }
        resultJson.put("tasks", tasksArr);
        resultJson.put("companies", companiesArr);

        System.out.println(resultJson);


        return (resultJson);
    }


}



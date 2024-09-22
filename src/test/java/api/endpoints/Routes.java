package api.endpoints;

public class Routes {

    /* ONLY URL's */

    public static String base_url = "https://petstore.swagger.io/v2";

    //User
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";

    //store
    public static String postStore_url = base_url + "/store/inventory";
    public static String getStore_url = base_url + "/store/order";
    public static String updateStore_url = base_url + "/store/order/{orderId}";
    public static String deleteStore_url = base_url + "/store/order/{orderId}";

    //pet - need to update URL
    // Pet
    public static String postPet_url = base_url + "/pet";
    public static String getPet_url = base_url + "/pet/{petId}";
    public static String updatePet_url = base_url + "/pet";
    public static String deletePet_url = base_url + "/pet/{petId}";

}

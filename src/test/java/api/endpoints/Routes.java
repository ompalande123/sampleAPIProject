package api.endpoints;

public class Routes {
	
	// this class will contains base url and path urls
	
	//base
	public static String base_url="https://reqres.in";
	
	//paths
	public static String post_url=base_url+"/api/users";
	public static String get_url=base_url+"/api/users/{id}";
	public static String get_all_users_from_page_number=base_url+"/api/users?page={id}";
	
	public static String patch_exisitngUser=base_url+"/api/users/2";
	
	// login paths
	public static String post_login_url=base_url+"/api/login";
	
	
	
	

}

package demo.session.framework.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getJsonDataToMap {
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap () throws IOException {
	
	@SuppressWarnings("deprecation")
	//convert json to string
	String jsonContent = FileUtils.readFileToString(new File (System.getProperty("user.dir") + "//src//test//java//demo//session//framework//TestData//PurchaseOrderData.json"),StandardCharsets.UTF_8);
	
	//String to Hashmap using jakson data bind dependency
	
	ObjectMapper mapper = new ObjectMapper ();
	
	List<HashMap<String,String>>data = 	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		
	});
	
	return data;
	
	
	
	}
	

}

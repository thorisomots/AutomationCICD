package onlinesystems.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJSONFile() throws IOException {

		String jsonFileCOntent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
				+ "//home//thoriso//eclipse-workspace//EcommerceFrameWork//src//test//java//onlinesystems//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		ObjectMapper mapData = new ObjectMapper();

		List<HashMap<String, String>> dataBind = mapData.readValue(jsonFileCOntent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return dataBind;
	}
}

package com.alameendev.librarymanagement.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {

	private static JsonSerializer jsonSerializer;
	private ObjectMapper mapper;

	private JsonSerializer() {
		mapper = new ObjectMapper();
	}

	public static JsonSerializer serialize() {
		if (jsonSerializer == null) {
			jsonSerializer = new JsonSerializer();
		}
		return jsonSerializer;
	}

	// A generic method converts the specific type object to string and save it to
	// file.
	public <T> void saveToJson(T object, String fileName) {
		try {
			String json = mapper.writeValueAsString(object);
			String path = System.getProperty("user.dir");
			File fileDir = new File(path + "/Data/");
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File file = new File(path + "/Data/" + fileName);
			file.createNewFile();
			FileOutputStream fileOutPutStream = new FileOutputStream(file);
			fileOutPutStream.write(json.getBytes());
			fileOutPutStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// A generic method read json string and converts it into object.
	public <T> T retrieveFromJson(String fileName, Class<T> classFile) {
		try {
			String path = System.getProperty("user.dir");
			File file = new File(path + "/Data/" + fileName);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				byte[] bytes = new byte[fileInputStream.available()];
				fileInputStream.read(bytes);
				fileInputStream.close();
				String json = new String(bytes);
				fileInputStream.close();
				return (T) mapper.readValue(json, classFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public <T> T retrieveFromJson(String fileName, TypeReference<T> typeReference) {
		try {
			String path = System.getProperty("user.dir");
			File file = new File(path + "/Data/" + fileName);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				byte[] bytes = new byte[fileInputStream.available()];
				fileInputStream.read(bytes);
				fileInputStream.close();
				String json = new String(bytes);
				fileInputStream.close();
				return mapper.readValue(json, typeReference);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

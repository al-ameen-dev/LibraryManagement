package com.alameendev.librarymanagement.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ObjectSerializer {
	private static ObjectSerializer objectSerializer;

	public static ObjectSerializer serialize() {
		if (objectSerializer == null) {
			objectSerializer = new ObjectSerializer();
		}
		return objectSerializer;
	}

	// Save the list to the disk.
	public <T> void saveBookListToDisk(T object, String fileName) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		try {
			objectOutputStream.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectOutputStream.close();
		fileOutputStream.close();
	}

	// Get books from the disk if available.
	public <T> T getBooksFromTheDisk(String fileName) throws IOException {
		File file = new File(fileName);
		if (file.exists()) {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			try {
				return (T) objectInputStream.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			objectInputStream.close();
			fileInputStream.close();
		}
		return null;
	}
}

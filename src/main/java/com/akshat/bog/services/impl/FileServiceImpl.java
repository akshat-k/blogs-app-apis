package com.akshat.bog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.akshat.bog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String name = file.getOriginalFilename();

		String randomID = UUID.randomUUID().toString();
		String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

		String filePath = path + File.separator + fileName;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullpath = path + File.separator + filename;
		InputStream is = new FileInputStream(fullpath);

		return is;
	}

}

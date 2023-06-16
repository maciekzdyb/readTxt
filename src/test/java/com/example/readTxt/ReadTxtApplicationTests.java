package com.example.readTxt;

import com.example.readTxt.controller.FileController;
import com.example.readTxt.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReadTxtApplicationTests {
	@Autowired
	private FileService fileService;
	@Autowired
	private FileController fileController;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void fileServiceShouldReturnMap() throws IOException {

		String fileBody = "Hello, Hello Word. This is is a test. Hello";

		Map<String, Long> expected = new LinkedHashMap<>();
		expected.put("Hello",3L);
		expected.put("is", 2L);
		expected.put("a", 1L);
		expected.put("Word", 1L);
		expected.put("test", 1L);
		expected.put("This",1L);

		MultipartFile multipartFile = new MockMultipartFile("test.txt", fileBody.getBytes());

		Map<String, Long> actual = fileService.getFrequencyList(multipartFile);

		assertEquals(expected,actual);
	}

	@Test
	void fileServiceShouldReturnEmptyMap() throws IOException {

		String fileBody = "";

		Map<String, Long> expected = new LinkedHashMap<>();

		MultipartFile multipartFile = new MockMultipartFile("test.txt", fileBody.getBytes());

		Map<String, Long> actual = fileService.getFrequencyList(multipartFile);

		assertEquals(expected,actual);
	}

	@Test
	public void whenFileUploaded_thenVerifyStatus()
			throws Exception {
		MockMultipartFile file
				= new MockMultipartFile(
				"file",
				"hello.txt",
				MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes()
		);

		MockMvc mockMvc
				= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart("/read").file(file))
				.andExpect(status().isOk());
	}
}

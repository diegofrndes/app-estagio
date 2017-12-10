package br.com.taldi.armazenamento;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArquivoUploadController {

	private ArmazenamentoService armazenamentoService;

	@Autowired
	public ArquivoUploadController(ArmazenamentoService armazenamentoService) {
		this.armazenamentoService = armazenamentoService;
	}

	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {
		model.addAttribute("files", armazenamentoService.loadAll()
				.map(path -> MvcUriComponentsBuilder
						.fromMethodName(ArquivoUploadController.class, "serveFile", path.getFileName().toString())
						.build().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = armazenamentoService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		armazenamentoService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/upload";
	}

	@ExceptionHandler(ArmazenamentoArquivoNaoEncontradoException.class)
	public ResponseEntity<?> handleStorageFileNotFound(ArmazenamentoArquivoNaoEncontradoException exc) {
		return ResponseEntity.notFound().build();
	}
}

package br.com.taldi.armazenamento;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArmazenamentoSistemaArquivosService implements ArmazenamentoService {

	private final Path rootLocation;
	
	private Logger logger = LogManager.getLogger(ArmazenamentoSistemaArquivosService.class);

	@Autowired
	public ArmazenamentoSistemaArquivosService(ArmazenamentoProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new ArmazenamentoException("Falha ao tentar armazenar arquivo vazio " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new ArmazenamentoException(
						"Não é possível cadastrar arquivo com caminho fora do diretório atual" + filename);
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new ArmazenamentoException("Falha ao salvar aquivo " + filename, e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new ArmazenamentoException("Falha ao ler arquivos armazenados", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ArmazenamentoArquivoNaoEncontradoException("Não foi possível ler o arquivo: " + filename);

			}
		} catch (MalformedURLException e) {
			logger.info("Não foi possível ler o arquivo: " + filename, e.getMessage());
			throw new ArmazenamentoArquivoNaoEncontradoException("Não foi possível ler o arquivo: " + filename, e);
		}
		catch (Exception e) {
			logger.info("Não foi possível ler o arquivo: " + filename, e.getMessage());
			throw new ArmazenamentoArquivoNaoEncontradoException("Não foi possível ler o arquivo: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new ArmazenamentoException("Não foi possível iniciar o armazenamento", e);
		}
	}

	public Path getRootLocation() {
		return rootLocation;
	}

}

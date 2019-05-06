package com.el.edp.sfs.api;

import com.el.core.storage.AbstractStorageManager;
import com.el.core.storage.StorageFileEntry;
import com.el.core.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * @author Simon.Hu
 * @since 17/11/20
 */
@Profile("edp")
@Slf4j
@RestController
@RequestMapping("/edp/fm")
public class EdpStorageApi extends AbstractStorageManager {

    public EdpStorageApi(StorageService storageService) {
        super(storageService);
    }

    @GetMapping("/repos/{repo}/owned")
    @Override
    public ResponseEntity<Integer> owned(@PathVariable("repo") String repoName) {
        return super.owned(repoName);
    }

    @GetMapping("/repos/{repo}/files")
    @Override
    public ResponseEntity<List<StorageFileEntry>> files(@PathVariable("repo") String repoName) {
        return super.files(repoName);
    }

    @PostMapping("/repos/{repo}/files/{file}/erase")
    @ResponseBody
    @Override
    public void erase(@PathVariable("repo") String repoName, @PathVariable("file") String fileName) {
        super.erase(repoName, fileName);
    }

    @GetMapping("/repos/{repo}/files/{file:.+}")
    @Override
    public ResponseEntity<Resource> file(
        @PathVariable("repo") String repoName, @PathVariable("file") String fileName) {
        return super.file(repoName, fileName);
    }

    @GetMapping("/repos/{repo}/bytes/{file:.+}")
    @Override
    public ResponseEntity<byte[]> fileBytes(
        @PathVariable("repo") String repoName, @PathVariable("file") String fileName) throws IOException {
        return ResponseEntity.ok(Base64.getEncoder().encode(super.fileBytes(repoName, fileName).getBody()));
    }

    @GetMapping("/repos/{repo}/files/{file:.+}/preview")
    @Override
    public ResponseEntity<Resource> preview(
        @PathVariable("repo") String repoName, @PathVariable("file") String fileName) {
        return super.preview(repoName, fileName);
    }

    @PostMapping("/repos/{repo}/store")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void store(@PathVariable("repo") String repoName, MultipartFile file) {
        super.store(repoName, file);
    }

    @PostMapping("/repos/{repo}/nstore")
    @ResponseStatus(HttpStatus.OK)
    public void nstore(@PathVariable("repo") String repoName, List<MultipartFile> files) {
        files.forEach(file -> super.store(repoName, file));
    }
}

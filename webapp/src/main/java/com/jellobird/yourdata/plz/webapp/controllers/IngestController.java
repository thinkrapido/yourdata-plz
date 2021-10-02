package com.jellobird.yourdata.plz.webapp.controllers;

import com.jellobird.yourdata.plz.webapp.services.PlzSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ingest")
@RolesAllowed({"ROLE_INGEST"})
public class IngestController {

    @Autowired
    PlzSearchService searchService;

    @PostMapping("")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        searchService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    private class StorageFileNotFoundException extends Throwable {
    }
}

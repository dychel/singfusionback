package com.singfusion.singfusion.controller;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.services.drive.model.Permission;
import com.singfusion.singfusion.entity.Users;
import com.singfusion.singfusion.exception.ApiRequestException;
import com.singfusion.singfusion.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/singfusion/rapportetonnement")
public class DriveUploadController {

    private Drive driveService;

    @Autowired
    private UserService userService;

//    @PostConstruct
//    public void init() {
//        try {
//            this.driveService = getDriveService();
//        } catch (IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Erreur d'initialisation de DriveService", e);
//        }
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        File tempFile = null;
        try {
            String username = getAuthenticatedUsername();

            String folderId = getOrCreateFolder(username);

            // Création fichier temporaire
            tempFile = File.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(tempFile);

            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setName(file.getOriginalFilename());
            fileMetadata.setParents(Collections.singletonList(folderId));

            FileContent mediaContent = new FileContent(file.getContentType(), tempFile);

            com.google.api.services.drive.model.File uploadedFile =
                    driveService.files().create(fileMetadata, mediaContent)
                            .setFields("id, webViewLink")
                            .execute();

            makeFilePublic(uploadedFile.getId());

            return ResponseEntity.ok(Map.of(
                    "message", "Fichier téléversé avec succès.",
                    "driveUrl", uploadedFile.getWebViewLink()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur : " + e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete(); // Nettoyage du fichier temporaire
            }
        }
    }

    private Drive getDriveService() throws IOException, GeneralSecurityException {
        // Chargement du fichier credentials.json depuis resources
        try (InputStream credentialsStream = getClass().getResourceAsStream("/credentials.json")) {
            if (credentialsStream == null) {
                throw new IOException("credentials.json introuvable dans resources");
            }
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(credentialsStream)
                    .createScoped(List.of(DriveScopes.DRIVE));

            return new Drive.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials)
            )
                    .setApplicationName("SingFusion")
                    .build();
        }
    }

    private String getOrCreateFolder(String folderName) throws IOException {
        String query = String.format("mimeType='application/vnd.google-apps.folder' and name='%s' and trashed=false", folderName);
        FileList result = driveService.files().list()
                .setQ(query)
                .setFields("files(id, name)")
                .execute();

        if (!result.getFiles().isEmpty()) {
            return result.getFiles().get(0).getId();
        }

        com.google.api.services.drive.model.File folderMetadata = new com.google.api.services.drive.model.File();
        folderMetadata.setName(folderName);
        folderMetadata.setMimeType("application/vnd.google-apps.folder");

        com.google.api.services.drive.model.File folder = driveService.files().create(folderMetadata)
                .setFields("id")
                .execute();

        return folder.getId();
    }

    private void makeFilePublic(String fileId) throws IOException {
        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        driveService.permissions().create(fileId, permission).execute();
    }

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ApiRequestException("Utilisateur non authentifié");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new ApiRequestException("Utilisateur non trouvé");
        }
        UserDetails userDetails = (UserDetails) principal;
        Users users = userService.getUserByUsername(userDetails.getUsername());
        return users.getFirstName();
    }
}

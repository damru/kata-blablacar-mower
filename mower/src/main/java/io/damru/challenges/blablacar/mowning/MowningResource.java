package io.damru.challenges.blablacar.mowning;

import io.damru.challenges.blablacar.mowning.model.LawnConfiguration;
import io.damru.challenges.blablacar.mowning.model.Mower;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@CrossOrigin
public class MowningResource {

    private final MowningService mowningService;
    private final LawnConfigurationService lawnConfigurationService;

    public MowningResource(MowningService mowningService,
                           LawnConfigurationService lawnConfigurationService) {
        this.mowningService = mowningService;
        this.lawnConfigurationService = lawnConfigurationService;
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Object> mow(@RequestParam("file") MultipartFile config) {
        try {
            LawnConfiguration lawnConfiguration = lawnConfigurationService.load(config.getInputStream());
            Set<Mower> mowers = mowningService.mow(lawnConfiguration.getLawn(), lawnConfiguration.getMowersCourses());
            return ResponseEntity.ok(mowers);
        } catch (IOException e) {
            return ResponseEntity.unprocessableEntity().body(e.getLocalizedMessage());
        }
    }

}

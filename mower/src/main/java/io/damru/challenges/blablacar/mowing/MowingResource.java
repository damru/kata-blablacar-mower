package io.damru.challenges.blablacar.mowing;

import io.damru.challenges.blablacar.mowing.model.LawnConfiguration;
import io.damru.challenges.blablacar.mowing.model.Mower;
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
public class MowingResource {

    private final MowingService mowingService;
    private final LawnConfigurationService lawnConfigurationService;

    public MowingResource(MowingService mowingService,
                          LawnConfigurationService lawnConfigurationService) {
        this.mowingService = mowingService;
        this.lawnConfigurationService = lawnConfigurationService;
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Object> mow(@RequestParam("file") MultipartFile config) {
        try {
            LawnConfiguration lawnConfiguration = lawnConfigurationService.load(config.getInputStream());
            Set<Mower> mowers = mowingService.mow(lawnConfiguration.getLawn(), lawnConfiguration.getMowersCourses());
            return ResponseEntity.ok(mowers);
        } catch (IOException e) {
            return ResponseEntity.unprocessableEntity().body(e.getLocalizedMessage());
        }
    }

}

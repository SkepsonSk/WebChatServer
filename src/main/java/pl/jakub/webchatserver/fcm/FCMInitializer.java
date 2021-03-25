package pl.jakub.webchatserver.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Initializes the Firebase Cloud Messaging
 * responsible for pushing messages to users.
 *
 * @author Jakub Zelmanowicz
 */
@Service
public class FCMInitializer {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    /**
     * Constructs the Firebase Messaging client.
     *
     * @return instance of the Firebase Messaging client.
     * @throws IOException if provided credentials were wrong or did
     * not exist.
     */
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());

        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);
        return FirebaseMessaging.getInstance(app);

    }

}

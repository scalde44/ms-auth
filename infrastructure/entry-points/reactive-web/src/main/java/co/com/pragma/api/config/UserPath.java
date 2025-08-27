package co.com.pragma.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "routes.user.paths")
public class UserPath {
    private String base;
    private String documentNumber;

    public String getDocumentNumberVar() {
        return this.documentNumber.replaceAll("[{}/]", "");
    }
}

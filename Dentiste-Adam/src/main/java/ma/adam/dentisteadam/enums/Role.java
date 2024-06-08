package ma.adam.dentisteadam.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    PATIENT,
    DOCTEUR;

    @Override
    public String getAuthority() {
        return name();
    }
}

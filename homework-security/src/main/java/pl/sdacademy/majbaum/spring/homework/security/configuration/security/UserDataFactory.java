package pl.sdacademy.majbaum.spring.homework.security.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.sdacademy.majbaum.spring.homework.security.model.User;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserAuthorities.*;

public class UserDataFactory {
    private UserDataFactory() {}

    public static final UserData ANONYMOUS = new UserData(null,null, null, Collections.emptySet());

    public static UserData from(User user) {
        final Set<String> grants;
        switch(user.getUserType()) {
            case ADMIN: {
                grants = Set.of(CAN_DELETE_ANY_ARTICLE, CAN_DELETE_COMMENT, CAN_ADD_USER);
                break;
            }
            case EDITOR: {
                grants = Set.of(CAN_ADD_ARTICLE, CAN_EDIT_OWNED_ARTICLE, CAN_DELETE_OWNED_ARTICLE);
                break;
            }
            case READER: {
                grants = Collections.emptySet();
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown user type: " + user.getUserType());
            }
        }

        final Set<GrantedAuthority> authorities = grants.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toUnmodifiableSet());

        return new UserData(
            user.getId(),
            user.getUserName(),
            user.getPassword(),
            authorities
        );
    }
}

package pl.sdacademy.majbaum.spring.homework.security.configuration.security;

public interface UserAuthorities {
    String CAN_ADD_ARTICLE = "CAN_ADD_ARTICLE";
    String CAN_EDIT_OWNED_ARTICLE = "CAN_EDIT_OWNED_ARTICLE";
    String CAN_DELETE_ANY_ARTICLE = "CAN_DELETE_ANY_ARTICLE";
    String CAN_DELETE_OWNED_ARTICLE = "CAN_DELETE_OWNED_ARTICLE";

    String CAN_DELETE_COMMENT = "CAN_DELETE_COMMENT";

    String CAN_ADD_USER = "CAN_ADD_USER";
}

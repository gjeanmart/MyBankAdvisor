package com.gjeanmart.mybankadvisor.security.config;

import com.gjeanmart.mybankadvisor.core.exception.business.NotAuthorizedException;
import com.gjeanmart.mybankadvisor.security.model.RoleEnum;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

/**
 * Created by Grégoire JEANMART on 2016-05-15.
 */
public class Authorization {

    public static void isAuthorized(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, RoleEnum roleEnum) throws NotAuthorizedException {
        AccessToken token = principal.getKeycloakSecurityContext().getToken();
        if(!token.getRealmAccess().getRoles().contains(roleEnum.getCode())) {
            throw new NotAuthorizedException(token.getName());
        }

    }

}

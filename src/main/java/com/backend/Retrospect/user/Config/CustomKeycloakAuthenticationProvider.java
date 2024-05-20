package com.backend.Retrospect.user.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

;

public class CustomKeycloakAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomKeycloakAuthenticationProvider.class);

    public CustomKeycloakAuthenticationProvider(ApplicationContext appCtx,
                                                Jwt2AuthenticationConverter jwt2AuthenticationConverter, JwtDecoder jwtDecoder) {
        this.appCtx = appCtx;
        this.jwt2AuthenticationConverter = jwt2AuthenticationConverter;
        this.jwtDecoder = jwtDecoder;
    }

    private ApplicationContext appCtx;
    private Jwt2AuthenticationConverter jwt2AuthenticationConverter;
    private JwtDecoder jwtDecoder;

    /**
     *
     * authenticate - Customer Authentication
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;
        Jwt jwt = getJwt(bearer);
        JwtAuthenticationToken token = this.jwt2AuthenticationConverter.convert(jwt);
        UserServiceImpl userServiceImpl = appCtx.getBean(UserServiceImpl.class);
        SecurityContextHolder.getContext().setAuthentication(token);
        String email = Utils.getSource();
        UserEntity userDetails = null;
//        AMQUtils amqUtils = appCtx.getBean(AMQUtils.class);
        if (userServiceImpl.getUserDetails(email) == null) {
            try {
                Map<String, String> userInfoMapper = new HashMap<>();
                String accessToken = (String)authentication.getPrincipal();
                userInfoMapper.put("email", email);
                userInfoMapper.put("token", accessToken);
                String req = Utils.convertObjectToJson(userInfoMapper);
                LOGGER.debug("The request passed {}", req);
//                amqUtils.sendAndReceive("common_authentication_preauth", req);
            } catch (Exception e) {
                LOGGER.error("Error in saving the roles", e);
            }
        }
        userDetails = userServiceImpl.getUserDetails(email);
        Collection<? extends GrantedAuthority> grantedAuthorities = addUserSpecificAuthorities(
                token != null ? token.getAuthorities() : authentication.getAuthorities(), userDetails);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt, grantedAuthorities);
        jwtAuthenticationToken.setDetails(bearer.getDetails());
        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }

    private Jwt getJwt(BearerTokenAuthenticationToken bearer) {
        try {
            return this.jwtDecoder.decode(bearer.getToken());
        } catch (BadJwtException failed) {
            throw new InvalidBearerTokenException(failed.getMessage(), failed);
        } catch (JwtException failed) {
            throw new AuthenticationServiceException(failed.getMessage(), failed);
        }
    }

    /**
     * mapAuthorityMapper - Mapping the prefix
     *
     * @return
     */
    protected SimpleAuthorityMapper mapAuthorityMapper() {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_");
        grantedAuthorityMapper.setConvertToUpperCase(true);
        return grantedAuthorityMapper;
    }

//    /**
//     * Adding custom Roles either from Redis addUserSpecificAuthorities
//     *
//     * @param authentication
//     * @param authorities
//     * @return
//     */
    protected Collection<? extends GrantedAuthority> addUserSpecificAuthorities(
            Collection<? extends GrantedAuthority> authorities, UserEntity userDetails) {
        List<GrantedAuthority> result = new ArrayList<>();
        result.addAll(authorities);
        result.add(new SimpleGrantedAuthority("Admin"));
//        if (userDetails != null) {
//            for (String role : userDetails.getRole()) {
//                LOGGER.info("Role Received {}", role);
//                if (role != null)
//                    result.add(new SimpleGrantedAuthority(role));
//            }
//        } else {
//            result.add(new SimpleGrantedAuthority("Admin"));
//        }
        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BearerTokenAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public String getToken(Object userPrincipal) {
        String accessToken = null;
        if (null != userPrincipal) {
            if (userPrincipal instanceof Jwt) {
                LOGGER.debug("Inside Keycloak Security Contect pricipal");
                Jwt customUserData = (Jwt) userPrincipal;
                if (customUserData != null)
                    accessToken = customUserData.getTokenValue();
            }
        }
        return accessToken;
    }

}

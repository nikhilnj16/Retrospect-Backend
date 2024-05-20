package com.backend.Retrospect.keycloak.CommonConfig;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface Jwt2AuthenticationConverter extends Converter<Jwt, JwtAuthenticationToken> {
}
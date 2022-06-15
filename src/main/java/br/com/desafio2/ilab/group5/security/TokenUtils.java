package br.com.desafio2.ilab.group5.security;

import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import br.com.desafio2.ilab.group5.model.Administrator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class TokenUtils {

	private static final int SECONDS = 1000;
	private static final int MINUTES = 60 * SECONDS;

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final long EXPIRATION = MINUTES * 360 * 24;
	private static final String SECRET_KEY = "$3cr37k3y3q03D3s4f103n7r3g4d0r3S";
	private static final String EMISSOR = "FoodLovers";

	public static String createToken(Administrator administrador) {

		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		String token = Jwts.builder()
				.setSubject(administrador.getName() + ',' + administrador.getId() + ',' + administrador.getEmail())
				.setIssuer(EMISSOR).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();

		return PREFIX + token;

	}

	private static boolean isExpirationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));
	}

	private static boolean isEmissorValid(String emissor) {
		return emissor.equals(EMISSOR);
	}

	private static boolean isSubjectValid(String username) {
		return username != null && username.length() > 0;
	}

	public static Authentication validate(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String token = request.getHeader(HEADER);
			token = token.replace(PREFIX, "");

			Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build()
					.parseClaimsJws(token);

			String username = jwsClaims.getBody().getSubject();
			String issuer = jwsClaims.getBody().getIssuer();
			Date expira = jwsClaims.getBody().getExpiration();

			if (isSubjectValid(username) && isEmissorValid(issuer) && isExpirationValid(expira)) {
				return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
			}
		} catch ( SignatureException| ExpiredJwtException | MalformedJwtException | PrematureJwtException
				| UnsupportedJwtException | IllegalArgumentException e) {
			
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			
			
		}
		return null;
		
	}
}

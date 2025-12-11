package br.com.d13solutions;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@Path("secure")
@RequestScoped
public class SecureResource {

    @Claim(standard = Claims.preferred_username)
    private String username;

    @GET
    @Path("claim")
    @RolesAllowed("Subscriber")
    public String getClaim(){
        return username;
    }

    /* para testes em prompt
    token=$(curl https://raw.githubusercontent.com/eldermoraes/unipds/main/jwt-token/quarkus.jwt.token -s)
    curl -v -w '\n' -H "Authorization: Bearer $token" localhost:8080/api/secure/claim

    //criar próprio token

    public static void main(String[] args){
        String token = Jwt.issuer("https://example.com/issuer")
            .upn("jdoe@quarkus.io")
            .groups(new HashSet<>(Arrays.asList("User", "Admin")))
            .claim(Claims.birthdate.name(), "2001-07-13")
            .sign();

        System.out.println(token);
        System.exit(0);
    }
    */
}

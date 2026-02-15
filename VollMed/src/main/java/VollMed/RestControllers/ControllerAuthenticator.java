package VollMed.RestControllers;

import VollMed.Domain.user.AuthenticationDetails;
import VollMed.Domain.user.User;
import VollMed.Infra.Security.TokenJWTDTO;
import VollMed.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/login"})
public class ControllerAuthenticator {
    @Autowired
    public TokenService tokenService;
    @Autowired
    public AuthenticationManager manager;

    @PostMapping
    public ResponseEntity Login(@RequestBody @Valid AuthenticationDetails data) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.user(), data.password());
        Authentication authentication = this.manager.authenticate(token);
        String tokenJWT = this.tokenService.CreateToken((User)authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}

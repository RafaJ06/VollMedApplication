package VollMed.Infra.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ExceptionsValidator {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity manage404Error() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity manageNullPointerException500() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity managedArgumentNotValidException500(MethodArgumentNotValidException ex) {
        List<ManagedException500> message = ex.getFieldErrors().stream().map(ManagedException500::new).toList();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity manageSQLIntegrityConstraintViolationException500(SQLIntegrityConstraintViolationException Sql) {
        String message = Sql.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity managedError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity managedErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity managedErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Error");
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity managedErrorDeniedAccess() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity ManagedError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    public static record ManagedException500(String field, String message) {
        public ManagedException500(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }
    }
}


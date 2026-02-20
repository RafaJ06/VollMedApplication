package VollMed.Domain.Consult;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelConsult(@NotNull Long id, @NotBlank CancellationMotive motive) {
}

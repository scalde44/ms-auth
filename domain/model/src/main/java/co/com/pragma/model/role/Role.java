package co.com.pragma.model.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Role {
    private Long id;
    private RoleName name;
    private String description;

    public Role(Long id) {
        this.id = id;
    }
}

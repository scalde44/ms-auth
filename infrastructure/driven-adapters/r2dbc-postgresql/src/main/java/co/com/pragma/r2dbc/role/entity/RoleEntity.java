package co.com.pragma.r2dbc.role.entity;

import co.com.pragma.model.role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rol")
public class RoleEntity {
    @Id
    @Column("id_rol")
    private Long id;

    @Column("nombre")
    private RoleName name;

    @Column("descripcion")
    private String description;
}

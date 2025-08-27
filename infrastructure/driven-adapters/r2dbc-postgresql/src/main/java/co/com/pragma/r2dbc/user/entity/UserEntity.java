package co.com.pragma.r2dbc.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UserEntity {
    @Id
    @Column("id_usuario")
    private Long id;
    @Column("nombre")
    private String name;
    @Column("apellido")
    private String lastName;
    @Column("documento_identidad")
    private String documentNumber;
    @Column("telefono")
    private String phoneNumber;
    @Column
    private String email;
    @Column("salario_base")
    private BigDecimal baseSalary;
    @Column("id_rol")
    private Long roleId;

}

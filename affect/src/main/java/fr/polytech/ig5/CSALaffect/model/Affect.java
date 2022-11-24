package fr.polytech.ig5.CSALaffect.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class Affect {

    @Id
    @Column("offer_id")
    private int offer_id;


    @Column("user_id")
    private  int user_id;

    @Column("is_accepted")
    private boolean isAccepeted;

}

package com.util.commons.entity.title;

import com.util.commons.abstraction.AbstractEntity;
import com.util.commons.annotation.ExcludedCoverage;
import com.util.commons.entity.cardMachine.CardMachine;
import com.util.commons.entity.typeTitle.TypeTitle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = false)
@ExcludedCoverage
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Title extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type_title_id")
    private TypeTitle typeTitle;

    @ManyToOne
    @JoinColumn(name="card_machine_id")
    private CardMachine cardMachine;


}

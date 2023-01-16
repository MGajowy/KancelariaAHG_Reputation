package pl.gajewski.reputation.db.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gajewski.reputation.db.model.constants.ModelConstants;

import javax.persistence.*;

@Entity
@Data
@Table(name = ModelConstants.TABLE_REPUTATION)
@NoArgsConstructor
@AllArgsConstructor
public class ReputationOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private long id;

    @NotNull
    private String userName;

    @NotNull
    @Column(name = ModelConstants.COLUMN_DESCRIPTION)
    private String description;

    @Column(name = ModelConstants.COLUMN_LIKE)
    private Long likeRep;

    @Column(name = ModelConstants.COLUMN_NOT_LIKE)
    private Long notLikeRep;

    private Boolean visible;
}

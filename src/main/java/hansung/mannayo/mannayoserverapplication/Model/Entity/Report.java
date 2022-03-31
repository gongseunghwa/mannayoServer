package hansung.mannayo.mannayoserverapplication.Model.Entity;


import hansung.mannayo.mannayoserverapplication.Model.Type.FollowUpActionType;
import hansung.mannayo.mannayoserverapplication.Model.Type.ReportType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity @Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id @GeneratedValue
    private Integer idReport;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @NotNull
    private String ReportReason;

    @NotNull
    private LocalTime Reporttime;


    private FollowUpActionType followUpAction;

}

package google.solution.dto;

import google.solution.domain.SuccessMission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetMissionHistoryRes {
    private List<SuccessMission> successMissions;
}

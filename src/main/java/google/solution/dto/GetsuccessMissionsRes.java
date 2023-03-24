package google.solution.dto;

import google.solution.domain.SuccessMission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GetsuccessMissionsRes {
    private List<SuccessMission> successMissions;
}

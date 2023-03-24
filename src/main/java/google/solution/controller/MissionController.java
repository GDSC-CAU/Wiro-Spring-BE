package google.solution.controller;


import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.GetsuccessMissionsRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;
import google.solution.service.MissionService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/getMissionInfo/{code}")
    public BaseResponse<GetMissionInfoRes> getMissionHistory(@PathVariable String code) {
        try {
            GetMissionInfoRes getChatRoomsRes = missionService.getMissionInfo(code);
            return new BaseResponse<>(getChatRoomsRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @PostMapping("/missionComplete")
    public BaseResponse<MissionCompleteRes> missionComplete(@RequestBody MissionCompleteReq missionCompleteReq, Authentication authentication) {
        try {
            String userId = authentication.getName();
            MissionCompleteRes missionCompleteRes = missionService.missionComplete(missionCompleteReq, userId);
            return new BaseResponse<>(missionCompleteRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @GetMapping("/getSuccessMissions")
    public BaseResponse<GetsuccessMissionsRes> getSuccessMissions(Authentication authentication) {
        try {
            String userId = authentication.getName();
            GetsuccessMissionsRes getsuccessMissionsRes = missionService.getMissionHistory(userId);
            return new BaseResponse<>(getsuccessMissionsRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

}

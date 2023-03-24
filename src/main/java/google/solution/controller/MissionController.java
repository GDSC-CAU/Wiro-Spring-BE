package google.solution.controller;


import google.solution.dto.*;
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

    @GetMapping("/getMissionHistory")
    public BaseResponse<GetMissionHistoryRes> getMissionHistory(Authentication authentication) {
        try {
            String userId = authentication.getName();
            GetMissionHistoryRes getMissionHistoryRes = missionService.getMissionHistory(userId);
            return new BaseResponse<>(getMissionHistoryRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @GetMapping("/getCheckListHistory")
    public BaseResponse<GetCheckListHistoryRes> getCheckListHistory(Authentication authentication) {
        try {
            String userId = authentication.getName();
            GetCheckListHistoryRes getCheckListHistoryRes = missionService.getCheckListHistory(userId);
            return new BaseResponse<>(getCheckListHistoryRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

}

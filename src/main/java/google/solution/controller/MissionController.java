package google.solution.controller;


import google.solution.dto.GetChatRoomRes;
import google.solution.dto.GetMissionInfoReq;
import google.solution.dto.GetMissionInfoRes;
import google.solution.service.MissionService;
import google.util.BaseResponse;
import google.util.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/getMissionInfo")
    public BaseResponse<GetMissionInfoRes> getMissionInfo(GetMissionInfoReq getMissionInfoReq) {
        try {
            GetMissionInfoRes getChatRoomsRes = missionService.getMissionInfo(getMissionInfoReq.getCode());
            return new BaseResponse<>(getChatRoomsRes);
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }
}

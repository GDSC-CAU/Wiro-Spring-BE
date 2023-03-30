package google.solution.service;

import google.solution.domain.Mission;
import google.solution.domain.SuccessMission;
import google.solution.dto.*;
import google.solution.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    @Transactional(readOnly = true)
    public GetMissionInfoRes getMissionInfo(String id, String code) throws Exception {
        Mission mission = missionRepository.getMissionInfo(id, code);
        GetMissionInfoRes getMissionInfoRes = GetMissionInfoRes.missionToGetMissionInfoRes(mission);
        return getMissionInfoRes;
    }

    @Override
    public MissionCompleteRes missionComplete(MissionCompleteReq missionCompleteReq, String userId) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String nowDate = simpleDateFormat.format(new Date());
        missionCompleteReq.setUpdateTime(nowDate);
        missionRepository.saveOneMission(missionCompleteReq);
        List<SuccessMission> missions = missionRepository.getSuccessMissions(missionCompleteReq, userId);
        String code = missionCompleteReq.getCode();
        if (missions.size() >= 5) {
            double averageScore  = calculateWeightedAverage(missions);
            missionRepository.saveScore(code, averageScore, userId);
            return new MissionCompleteRes();
        } else {
            return new MissionCompleteRes();
        }
    }

    private double calculateWeightedAverage(List<SuccessMission> missions) {
        double averageScore = 0.0;
        for (int i = 0; i < missions.size(); i++) {
            averageScore = averageScore + (i+1) * missions.get(i).getScore();
        }
        averageScore = averageScore / 15;
        averageScore = Double.parseDouble(String.format("%.3f",averageScore));

        return averageScore;
    }

    @Override
    @Transactional(readOnly = true)
    public GetMissionHistoryRes getMissionHistory(String userId) throws Exception {
        GetMissionHistoryRes getMissionHistoryRes = missionRepository.getMissionHistory(userId);
        return getMissionHistoryRes;
    }

    @Override
    @Transactional(readOnly = true)
    public GetCheckListHistoryRes getCheckListHistory(String userId) throws Exception {
        GetCheckListHistoryRes getCheckListHistoryRes = missionRepository.getCheckListHistory(userId);
        return getCheckListHistoryRes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetRecommendMissionRes> getRecommendMission(String userId) throws Exception {
        List<GetRecommendMissionRes> recommendMissions = missionRepository.getRecommendMission(userId);
        return recommendMissions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetRecommendChecklistRes> getRecommendChecklist(String userId) throws Exception {
        List<GetRecommendChecklistRes> recommendChecklists = missionRepository.getRecommendChecklist(userId);
        return recommendChecklists;
    }

}

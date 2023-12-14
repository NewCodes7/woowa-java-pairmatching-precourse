package pairmatching.controller;

import java.util.List;
import pairmatching.model.PairMatch;
import pairmatching.model.PairResult;
import pairmatching.util.Util;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MainController {
    public void execute() {
        String selectedFeature;
        do {
            selectedFeature = selectFeature();
        } while (!selectedFeature.equals("Q"));
    }

    private String selectFeature() {
        String selectedFeature = InputView.readFeature();
        if(selectedFeature.equals("1")) {
            PairMatch pairMatch = new PairMatch();
            OutputView.printCourseMission();
            matchController(pairMatch);
        }
        return selectedFeature;
    }

    private void matchController(PairMatch pairMatch) {
        List<String> courseLevelMission = Util.splitByComma(InputView.readCourseLevelMission());
        boolean hasPairMatchResult = PairResult.checkPairMatchResult(courseLevelMission);
        if(!hasPairMatchResult) {
            pairMatch.setPair(courseLevelMission);
        }
        if(hasPairMatchResult) {
            boolean retryMatching = hasExistingMatch(courseLevelMission);
            if(retryMatching) {
                pairMatch.setPair(courseLevelMission);
            }
            if(!retryMatching) {
                reMatchPair(pairMatch);
            }
        }
    }

    private void reMatchPair(PairMatch pairMatch) {
        List<String> courseLevelMission = Util.splitByComma(InputView.readCourseLevelMission());
        boolean hasPairMatchResult = PairResult.checkPairMatchResult(courseLevelMission);
        if(!hasPairMatchResult) {
            pairMatch.setPair(courseLevelMission);
        }
        if(hasPairMatchResult) {
            boolean retryMatching = hasExistingMatch(courseLevelMission);
            if(retryMatching) {
                pairMatch.setPair(courseLevelMission);
            }
            if(!retryMatching) {
                reMatchPair(pairMatch);
            }
        }
    }

    private boolean hasExistingMatch(List<String> courseLevelMission) {
        String yesOrNo = InputView.readReMatching();
        if(!(yesOrNo.equals("아니오") || yesOrNo.equals("네"))) {
            throw new IllegalArgumentException();
        }
        return yesOrNo.equals("네");
    }
}

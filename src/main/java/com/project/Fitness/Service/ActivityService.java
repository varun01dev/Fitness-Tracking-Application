package com.project.Fitness.Service;

import com.project.Fitness.DTO.ActivityRequest;
import com.project.Fitness.DTO.ActivityResponse;
import com.project.Fitness.Repository.ActivityRepository;
import com.project.Fitness.Repository.UserRepository;
import com.project.Fitness.model.Activity;
import com.project.Fitness.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse trackActivity(ActivityRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: "+ request.getUserId()));
        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUser().getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }

    public  List<ActivityResponse> getUserActivities(String userId)
    {
        List<Activity> activityList = activityRepository.findByUserId(userId);
        // 1.Activity --> ActivityResponse 2. --> Collect in list and return
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}

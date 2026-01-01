package com.project.Fitness.Controller;

import com.project.Fitness.DTO.ActivityRequest;
import com.project.Fitness.DTO.ActivityResponse;
import com.project.Fitness.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request)
    {
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivity(
           @RequestHeader(value = "X-User-ID") String userId
    )
    {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

}

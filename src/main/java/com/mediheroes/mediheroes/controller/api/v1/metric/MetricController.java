package com.mediheroes.mediheroes.controller.api.v1.metric;

import com.mediheroes.mediheroes.dto.SummaryMetricResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.MetricService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricController {

    private final UserService userService;
    private final MetricService metricService;

    public MetricController(
        UserService userService,
        MetricService metricService
    ) {
        this.userService = userService;
        this.metricService = metricService;
    }

    @GetMapping("/summary")
    public ResponseEntity<SummaryMetricResponse> getBundledMetrics(){

        var user = this.userService.getCurrentUser();
        var metrics = this.metricService.getActualSummaryMetric(user.orElseThrow(EntityNotFoundException::new));

        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

}

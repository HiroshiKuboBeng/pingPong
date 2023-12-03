package com.mcframe.tools.pingPong;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
public class PingController {

    @GetMapping("ping")
    public String requestParam(
            @RequestParam(required = false) Long delay_seconds
            , @RequestParam(required = false) Integer response_status_code
            , HttpServletResponse response
    ) throws InterruptedException {
        return process(delay_seconds, response_status_code, response);
    }

    @RequestMapping(path = "ping", method = {
            RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE
    })
    public String requestBody(
            @RequestBody(required = false) PingRequestBody requestBody
            , HttpServletResponse response
    ) throws InterruptedException {
        return process(requestBody.getDelay_seconds(), requestBody.getResponse_status_code(), response);
    }

    @Data
    public static class PingRequestBody {
        private final Long delay_seconds;
        private final Integer response_status_code;
    }


    private String process(
            Long delaySeconds
            , Integer responseStatusConde
            , HttpServletResponse response
    ) throws InterruptedException {
        if (delaySeconds != null) {
            if (delaySeconds > 0) {
                Thread.sleep(delaySeconds * 1000);
            } else {
                throw new InvalidParameterException("delay_seconds must be positive");
            }
        }
        if (responseStatusConde != null) {
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(responseStatusConde);
            response.setStatus(httpStatusCode.value());
        }
        return "pong";

    }
}

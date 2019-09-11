package com.example.webSocketDemo.controller;

import com.example.webSocketDemo.component.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MainController {
    @RequestMapping(value = "/pushVideoListToWeb", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> pushVideoListToWeb(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:" + param.get("sltAccountId"));
            result.put("operationResult", true);
        } catch (IOException e) {
            result.put("operationResult", false);
        }
        return result;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
}

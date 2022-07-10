package ro.ieti.licentaBE.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    @GetMapping("/")
    public String helloWord(Model model) {
        model.addAttribute("testName","this is the value of model");
        return "Hello all!";
    }

    @GetMapping("/restricted")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }
}

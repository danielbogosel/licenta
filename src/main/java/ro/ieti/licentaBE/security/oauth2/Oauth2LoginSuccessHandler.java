package ro.ieti.licentaBE.security.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ro.ieti.licentaBE.entity.AuthEnum;
import ro.ieti.licentaBE.entity.CustomerUser;
import ro.ieti.licentaBE.service.CustomUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private CustomUserService customUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOauth2User oauth2User = (CustomOauth2User) authentication.getPrincipal();
        String email = oauth2User.getEmail();
        String name = oauth2User.getName();
        CustomerUser customer = customUserService.getCustomerByEmail(email);

        if (customer == null) {
            customUserService.createNewCustomerAfterLoginWithGoogle(email, name, AuthEnum.GOOGLE);

        } else {
            customUserService.updateCustomerAfterLoginWithGoogle(customer,email,AuthEnum.GOOGLE);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

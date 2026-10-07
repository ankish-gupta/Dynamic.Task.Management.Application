package com.todoApplication.springboot.controller;

//import com.todoApplication.springboot.authentication.LoginAuthentication;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
//
//	@Autowired
//	private LoginAuthentication loginAuthentication;
//
//	// http://localhost:8080/login
//	@GetMapping("login")
//	public String sayHello() {
//		return "login";
//	}
//
//	@PostMapping( "login")
//	public String welcomePage(@RequestParam String name, @RequestParam String password,
//							  ModelMap modelMap) {
//		if(loginAuthentication.userLoginAuthentication(name, password)) {
//			modelMap.put("name", name);
//			modelMap.put("password", password);
//			return "welcome";
//		}
//		else {
//			modelMap.put("errorMessage", "Invalid Credentials. Please Check again!!!");
//		}
//		return "login";
//	}

    @GetMapping("/")
    public String welcomePage(ModelMap modelMap){
        modelMap.put("name",getLoggedInUsername());
        System.out.println("welcome page");
        return "welcome";
    }

    private String getLoggedInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    //CSRF disable to enable the h2 console
    //Frames

    //Security filter chain defines a filter chain to filter request and if user is unauthorized login form is shown
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Inside the securityFilterChain");
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )                                          //Intercepts every HTTP request before running controller & runs it through Security Filter Chain.
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .invalidateHttpSession(true)       // destroy session
                        .deleteCookies("JSESSIONID")       // remove session cookie
                        .logoutSuccessUrl("/login?logout") // redirect to login page after logout
                        .permitAll()                       // allow anyone to access logout
                )
                .csrf(csrf -> csrf.disable())           // disable CSRF for testing
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())   // allow H2 console
                        .cacheControl(withDefaults -> {})         // prevent browser cache
                );

        return httpSecurity.build();
    }

}
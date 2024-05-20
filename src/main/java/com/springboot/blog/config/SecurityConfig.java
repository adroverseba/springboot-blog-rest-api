package com.springboot.blog.config;


import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAutheticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {

    // uso userDetailsService para configurar authenticationManager
    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAutheticationFilter authenticationFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAutheticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //spring-boot security automatically provides userDetailsServices and passwordEncoded to authenticationManager,
    //we don`t need to explicitly specify it
    //this authenticationManager will do this database authentication using userDetailsService and passwordEncoded
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // Deshabilita la protección CSRF
                .csrf(csrf -> csrf.disable()) // Cross-Site Request Forgery

                // Configura la autorización de las solicitudes HTTP
                .authorizeHttpRequests(authorize ->
//                        authorize.anyRequest().authenticated()
                                authorize
                                        // Permite todas las solicitudes GET a URLs que coincidan con "/api/**"
                                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                        .requestMatchers("/api/auth/**").permitAll()
                                        .requestMatchers("/swagger-ui/**").permitAll()
                                        .requestMatchers("/v3/api-docs/**").permitAll()
                                        // Exige autenticación para cualquier otra solicitud
                                        .anyRequest().authenticated()
                )

                // Configura el manejo de excepciones relacionadas con la autenticación
                .exceptionHandling(exception ->  exception.authenticationEntryPoint(authenticationEntryPoint))

                // Configura la gestión de sesiones (en este caso, sin sesiones)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configura la autenticación básica
                .httpBasic(Customizer.withDefaults());

        // Agrega un filtro personalizado (authenticationFilter) antes de UsernamePasswordAuthenticationFilter
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Devuelve la configuración de seguridad construida
        return httpSecurity.build();


        /**
         * Lo de abajo es necesario para el caso de autenticacion basica en memoria
         * */
//        httpSecurity
//
//                // Deshabilita la protección CSRF
//                .csrf(csrf -> csrf.disable()) // Cross-Site Request Forgery
//
//                // Configura la autorización de las solicitudes HTTP
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
//                                // Exige autenticación para cualquier otra solicitud
//                                .anyRequest().authenticated()
//                )
//                // Configura la autenticación básica
//                .httpBasic(Customizer.withDefaults());
//
//        // Devuelve la configuración de seguridad construida
//        return httpSecurity.build();


    }

//     **** in-memory authentication ****
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails seba = User.builder()
//                .username("sebastian")
//                .password(passwordEncoder().encode("sebastian"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(seba,admin);
//    }

}
//http.csrf().disable() -> DEPRECATED
//http.csrf((csrf) -> csrf.disable())


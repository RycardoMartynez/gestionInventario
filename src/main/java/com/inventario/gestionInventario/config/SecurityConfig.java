package com.inventario.gestionInventario.config;

// --- INICIO DE IMPORTS MANUALES DE SECURITY ---
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// --- FIN DE IMPORTS MANUALES DE SECURITY ---

@Configuration // Indica que es una clase de configuración de Spring
@EnableWebSecurity // Habilita la seguridad web
public class SecurityConfig {

    // 1. Configura las reglas de autorización (quién puede acceder a qué rutas)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF (necesario para APIs REST)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/productos/**").authenticated() // SOLO usuarios autenticados pueden acceder a /api/productos
                        .anyRequest().permitAll() // Permite acceso libre a cualquier otra ruta (aunque no hay)
                )
                .httpBasic(httpBasic -> {}); // Habilita la autenticación HTTP Basic

        return http.build();
    }

    // 2. Define usuarios en memoria (para pruebas rápidas)
    @Bean
    public UserDetailsService userDetailsService() {
        // Creamos un usuario de prueba: "user" con contraseña "password" y rol "USER"
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234") // ¡Nunca usar esto en producción!
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
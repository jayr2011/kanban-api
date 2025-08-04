package br.com.products.kanban.config;

                import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
                import org.springframework.context.annotation.Configuration;
                import org.springframework.lang.NonNull;
                import org.springframework.web.servlet.config.annotation.CorsRegistry;
                import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

                /**
                 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) in the application.
                 */
                @Configuration
                public class CorsConfig {
                    @Value("${cors.origin}")
                    private String corsOrigin;
                    /**
                     * Bean that configures CORS mappings for the application.
                     * Allows requests from * with specified HTTP methods and headers.
                     *
                     * @return WebMvcConfigurer instance with custom CORS settings.
                     */
                    @Bean
                    public WebMvcConfigurer corsConfigurer() {
                        return new WebMvcConfigurer() {
                            /**
                             * Adds CORS mappings to allow cross-origin requests.
                             *
                             * @param registry CorsRegistry to configure allowed origins, methods, headers, and credentials.
                             */
                            @Override
                            public void addCorsMappings(@NonNull CorsRegistry registry) {
                                registry.addMapping("/**")
                                        .allowedOrigins(corsOrigin)
                                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                                        .allowedHeaders("*")
                                        .allowCredentials(true);
                            }
                        };
                    }
                }
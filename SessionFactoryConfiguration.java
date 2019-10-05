@Configuration
public class SessionFactoryConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
         return entityManagerFactory.unwrap(SessionFactory.class);
    }
}

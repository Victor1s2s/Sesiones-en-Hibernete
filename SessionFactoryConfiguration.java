@Configuration
public class SessionFactoryConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
         return entityManagerFactory.unwrap(SessionFactory.class);
    }
    
    public void accederBBDDsesion(){
	    SessionFactory sessionFactory = getSessionFactory();
        //Abrimos la sesion
        Session session = sessionFactory.openSession();

        Bill b = (Bill) session.get(Bill.class, 1);
        System.out.println("Leida la factuta");
        System.out.println( b.toString());
        //Cerramos la sesion
        session.close();
        
    }
}

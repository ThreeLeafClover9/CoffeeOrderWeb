package test.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaIdMappingConfig {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Bean
    public CommandLineRunner testJpaIdMappingRunner(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = entityManager.getTransaction();

        return args -> {
//            testGeneratedValue();
        };
    }

    private void testGeneratedValue() {
        entityTransaction.begin();
        Member member = new Member();
        System.out.println("getMemberId = " + member.getMemberId());
        entityManager.persist(member);
        System.out.println("getMemberId = " + member.getMemberId());
        entityTransaction.commit();

        Member foundMember = entityManager.find(Member.class, 1L);
        System.out.println("getMemberId = " + foundMember.getMemberId());
    }
}

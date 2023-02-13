package test.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaColumnMappingConfig {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Bean
    public CommandLineRunner testJpaColumnMappingRunner(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = entityManager.getTransaction();

        return args -> {
//            testUnique();
        };
    }

    private void testNullable() {
        entityTransaction.begin();
        Member member = new Member();
        entityManager.persist(member);
        entityTransaction.commit();
    }

    private void testUpdatable() {
        entityTransaction.begin();
        Member member = new Member("hgd@gmail.com");
        entityManager.persist(member);

        Member foundMember = entityManager.find(Member.class, 1L);
        foundMember.setEmail("hgd@yahoo.co.kr");
        entityTransaction.commit();
    }

    private void testUnique() {
        entityTransaction.begin();
        Member member1 = new Member("hgd@gmail.com");
        Member member2 = new Member("hgd@gmail.com");
        entityManager.persist(member1);
        entityManager.persist(member2);
        entityTransaction.commit();
    }
}

package test.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = entityManager.getTransaction();

        return args -> {
//            testDelete();
        };
    }

    private void testPersistenceContext() {
        Member member = new Member("hgd@gmail.com");
        entityManager.persist(member);

        Member foundMember = entityManager.find(Member.class, 1L);
        System.out.println("getMemberId = " + foundMember.getMemberId());
        System.out.println("getEmail = " + foundMember.getEmail());
    }

    private void testPersistenceContextAndTable() {
        entityTransaction.begin();
        Member member = new Member("hgd@gmail.com");
        entityManager.persist(member);
        entityTransaction.commit();

        Member foundMember1 = entityManager.find(Member.class, 1L);
        System.out.println("getMemberId = " + foundMember1.getMemberId());
        System.out.println("getEmail = " + foundMember1.getEmail());

        Member foundMember2 = entityManager.find(Member.class, 2L);
        System.out.println(foundMember2);
    }

    private void testWriteBehind() {
        entityTransaction.begin();
        Member member1 = new Member("hgd1@gmail.com");
        Member member2 = new Member("hgd2@gmail.com");
        entityManager.persist(member1);
        entityManager.persist(member2);
        entityTransaction.commit();
    }

    private void testUpdate() {
        entityTransaction.begin();
        Member member = new Member("hgd@gmail.com");
        entityManager.persist(member);
        entityTransaction.commit();

        entityTransaction.begin();
        Member foundMember = entityManager.find(Member.class, 1L);
        foundMember.setEmail("hgd@yahoo.co.kr");
        entityTransaction.commit();
    }

    private void testDelete() {
        entityTransaction.begin();
        Member member = new Member("hgd@gmail.com");
        entityManager.persist(member);
        entityTransaction.commit();

        entityTransaction.begin();
        Member foundMember = entityManager.find(Member.class, 1L);
        entityManager.remove(foundMember);
        entityTransaction.commit();
    }
}

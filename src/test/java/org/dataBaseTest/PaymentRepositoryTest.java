package org.dataBaseTest;

import org.dataBaseTest.model.PaymentEntity;
import org.dataBaseTest.model.UsersEntity;
import org.dataBaseTest.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void testCreateUpdateDeletePayment() {
        // CREATE
        PaymentEntity payment = new PaymentEntity();
        payment.setBonus(100.0f);
        payment.setDateOfPayment(new Date());
        payment.setPay(1000.0f);

        UsersEntity user = new UsersEntity();
        user.setFirstname("John");
        user.setLastname("Doe");


        payment.setUser(user);

        payment = paymentRepository.save(payment);
        assertNotNull(payment.getIdPayment());

        // SELECT
        Optional<PaymentEntity> selectedPaymentOpt = paymentRepository.findById(payment.getIdPayment());
        assertTrue(selectedPaymentOpt.isPresent());
        PaymentEntity selectedPayment = selectedPaymentOpt.get();
        assertEquals(100.0f, selectedPayment.getBonus());
        // Sprawdzenie pozostałych pól...

        // UPDATE
        float newBonus = 200.0f;
        selectedPayment.setBonus(newBonus);
        paymentRepository.save(selectedPayment);

        PaymentEntity updatedPayment = paymentRepository.findById(selectedPayment.getIdPayment()).orElse(null);
        assertNotNull(updatedPayment);
        assertEquals(newBonus, updatedPayment.getBonus());

        // DELETE
        paymentRepository.delete(updatedPayment);
        assertFalse(paymentRepository.existsById(updatedPayment.getIdPayment()));
    }
}

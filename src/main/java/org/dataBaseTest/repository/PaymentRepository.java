package org.dataBaseTest.repository;
import org.dataBaseTest.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository, implementauje operacji bazodanowe takich jak save, findById, findAll, delete itp.
//1 element określa formę tabeli 2 forme klucza głównego(ID) w tabeli
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

}

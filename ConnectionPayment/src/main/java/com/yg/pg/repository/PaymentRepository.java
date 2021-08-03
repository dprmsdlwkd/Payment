package com.yg.pg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yg.pg.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

	Payment findByOrderNo(String orderNo);

}

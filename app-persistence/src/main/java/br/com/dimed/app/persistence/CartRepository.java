package br.com.dimed.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimed.app.business.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}

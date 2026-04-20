package com.command.toyvillage_server.domain.popup.domain.repository;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopUpRepository extends JpaRepository<PopUp,Long> {
    Page<PopUp> findAllBy(Pageable pageable);
}

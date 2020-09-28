package com.retrobot.initializer.dbinitializer.db.repository;

import com.retrobot.initializer.dbinitializer.db.model.PosMapIdEntity;
import org.springframework.data.repository.CrudRepository;

public interface PosMapIdRepository extends CrudRepository<PosMapIdEntity, String> {
}

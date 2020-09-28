package com.retrobot.initializer.dbinitializer.db.repository;

import com.retrobot.initializer.dbinitializer.db.model.MapEntity;
import org.springframework.data.repository.CrudRepository;

public interface MapRepository extends CrudRepository<MapEntity, Integer> {
}

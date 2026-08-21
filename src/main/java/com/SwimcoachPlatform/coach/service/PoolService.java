package com.SwimcoachPlatform.coach.service;

import com.SwimcoachPlatform.coach.entity.Pool;
import com.SwimcoachPlatform.coach.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoolService {

    private final PoolRepository poolRepository;

    @Autowired
    public PoolService(PoolRepository poolRepository) {
        this.poolRepository = poolRepository;

    }

    public List<Pool> getAllPools() {
        return poolRepository.findAll();
    }
    public Pool getPoolById(Long id) {
        return poolRepository.findById(id).orElse(null);
    }
    public Pool addPool(Pool pool) {
        return poolRepository.save(pool);
    }
    public Pool updatePool(Long id, Pool pool) {
        return poolRepository.save(pool);
    }
    public void deletePool(Long id) {
        poolRepository.deleteById(id);
    }


}

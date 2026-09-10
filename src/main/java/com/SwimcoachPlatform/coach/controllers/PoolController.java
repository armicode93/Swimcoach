package com.SwimcoachPlatform.coach.controllers;

import com.SwimcoachPlatform.coach.entity.Pool;
import com.SwimcoachPlatform.coach.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pools")
public class PoolController {

    private final PoolService poolService;

    @Autowired
    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    // GET: recupera tutte le piscine
    @GetMapping
    public List<Pool> getAllPools() {
        return poolService.getAllPools();
    }

    // GET: recupera una piscina tramite ID
    @GetMapping("/{id}")
    public Pool getPoolById(@PathVariable Long id) {
        return poolService.getPoolById(id);
    }

    // POST: crea una nuova piscina
    @PostMapping
    public Pool addPool(@RequestBody Pool pool) {
        return poolService.addPool(pool);
    }

    // PUT: modifica una piscina
    @PutMapping("/{id}")
    public Pool updatePool(@PathVariable Long id, @RequestBody Pool pool) {
        pool.setId(id);
        return poolService.updatePool(id, pool);
    }

    // DELETE: elimina una piscina
    @DeleteMapping("/{id}")
    public void deletePool(@PathVariable Long id) {
        poolService.deletePool(id);
    }
}
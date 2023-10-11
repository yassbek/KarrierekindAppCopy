package com.karrierekind.karrierekindapp.controller;

import com.karrierekind.karrierekindapp.entity.Talent;
import com.karrierekind.karrierekindapp.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/talents")
public class TalentController {

        private final TalentService talentService;

        @Autowired
        public TalentController(TalentService talentService) {
            this.talentService = talentService;
        }

        @PostMapping
        public ResponseEntity<Talent> createTalent(@RequestBody Talent talent) {
            return ResponseEntity.ok(talentService.saveTalent(talent));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Talent> getTalentById(@PathVariable Long id) {
            return talentService.getTalentById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping
        public ResponseEntity<List<Talent>> getAllTalents() {
            return ResponseEntity.ok(talentService.getAllTalents());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTalent(@PathVariable Long id) {
            talentService.deleteTalent(id);
            return ResponseEntity.noContent().build();
        }

        // If needed, add PUT endpoint for updating existing Talent and other CRUD operations.
    }


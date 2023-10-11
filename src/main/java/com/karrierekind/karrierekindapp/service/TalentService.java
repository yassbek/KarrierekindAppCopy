package com.karrierekind.karrierekindapp.service;

import com.karrierekind.karrierekindapp.entity.Talent;
import com.karrierekind.karrierekindapp.repository.TalentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentService {

    private final TalentRepository talentRepository;

    @Autowired
    public TalentService(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public Talent saveTalent(Talent talent) {
        return talentRepository.save(talent);
    }
    @Transactional
    public Optional<Talent> getTalentById(Long id) {
        return talentRepository.findById(id);
    }

    public List<Talent> getAllTalents() {
        return talentRepository.findAll();
    }

    public void deleteTalent(Long id) {
        talentRepository.deleteById(id);
    }
    public void deleteAllTalents() {talentRepository.deleteAll();}

    public void setHardSkillsForTalent(Long talentId, List<String> hardSkills) {
        Optional<Talent> optionalTalent = talentRepository.findById(talentId);

        if (optionalTalent.isEmpty()) {
            throw new RuntimeException("Talent with ID " + talentId + " not found.");
        }

        Talent talent = optionalTalent.get();
        talent.setHardSkills(hardSkills);

        talentRepository.save(talent);
    }

    public void setMetaSkillsScoresForTalent(Long talentId, Integer teamorientierung, Integer kundenorientierung, Integer flexibilitaet, Integer regelOrientierung, Integer initiative) {
        Optional<Talent> optionalTalent = talentRepository.findById(talentId);

        if (optionalTalent.isEmpty()) {
            throw new RuntimeException("Talent with ID " + talentId + " not found.");
        }

        Talent talent = optionalTalent.get();

        talent.setTeamorientierung(teamorientierung);
        talent.setKundenorientierung(kundenorientierung);
        talent.setFlexibilitaet(flexibilitaet);
        talent.setRegelOrientierung(regelOrientierung);
        talent.setInitiative(initiative);

        talentRepository.save(talent);
    }


    // You can add other business methods here as per your requirements.
}
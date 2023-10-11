package com.karrierekind.karrierekindapp.service;

import com.google.cloud.talent.v4.CustomAttribute;
import com.google.cloud.talent.v4.Job;
import com.karrierekind.karrierekindapp.entity.Talent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class TalentToJobService {

    public Job convertTalentToJob(Talent talent, String companyName) {
        // Prepare custom attributes
        Map<String, CustomAttribute> customAttributes = new HashMap<>();

        // Add hard skills
        for (String skill : talent.getHardSkills()) {
            String sanitizedSkill = skill.replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
            customAttributes.put(
                    "hardSkill_" + sanitizedSkill,
                    CustomAttribute.newBuilder().addStringValues(skill).setFilterable(true).build()
            );
        }

        // Add meta skills scores
        customAttributes.put("teamorientierung",
                CustomAttribute.newBuilder().addLongValues(Long.valueOf(talent.getTeamorientierung())).setFilterable(true).build());
        customAttributes.put("kundenorientierung",
                CustomAttribute.newBuilder().addLongValues(Long.valueOf(talent.getKundenorientierung())).setFilterable(true).build());
        customAttributes.put("flexibilitaet",
                CustomAttribute.newBuilder().addLongValues(Long.valueOf(talent.getFlexibilitaet())).setFilterable(true).build());
        customAttributes.put("regelOrientierung",
                CustomAttribute.newBuilder().addLongValues(Long.valueOf(talent.getRegelOrientierung())).setFilterable(true).build());
        customAttributes.put("initiative",
                CustomAttribute.newBuilder().addLongValues(Long.valueOf(talent.getInitiative())).setFilterable(true).build());
        // ... add the rest of the meta skills here ...

        return Job.newBuilder()
                .setCompany(companyName)
                .setRequisitionId("ReqID_" + talent.getId()) // Sample Requisition ID using Talent's ID
                .setTitle(talent.getUser().getEmail())
                .setDescription(talent.toString()) // Sample description using Talent's toString method.
                .putAllCustomAttributes(customAttributes)
                .build();
    }
}

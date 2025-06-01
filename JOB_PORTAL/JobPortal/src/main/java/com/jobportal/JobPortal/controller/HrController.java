package com.jobportal.JobPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.service.HrService;

@RestController
@RequestMapping("/api/hr")
public class HrController {

    @Autowired
    private HrService hrService;

    // ------------------------- Register HR ---------------------------------------------
    /*
     * AIM     : To register a new HR having user role as HR
     * PATH    : /api/hr/register
     * METHOD  : POST
     * INPUT   : Hr (request body)
     * RESPONSE: Hr (saved HR object)
     */
    @PostMapping("/register")
    public ResponseEntity<Hr> registerHr(@RequestBody Hr hr) {
        Hr savedHr = hrService.registerHr(hr);
        return ResponseEntity.ok(savedHr);
    }

    // ------------------------- Get HR by ID ---------------------------------------------
    /*
     * AIM     : To get HR details by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : GET
     * INPUT   : hrId (path variable)
     * RESPONSE: Hr (HR object)
     */
    @GetMapping("/{hrId}")
    public ResponseEntity<?> getHrById(@PathVariable Long hrId) {
        Hr hr = hrService.getHrById(hrId);
        return ResponseEntity.ok(hr);
    }

    // ------------------------- Update HR ---------------------------------------------
    /*
     * AIM     : To update HR details
     * PATH    : /api/hr/{hrId}
     * METHOD  : PUT
     * INPUT   : hrId (path variable), Hr (request body)
     * RESPONSE: String (confirmation message)
     */
    @PutMapping("/{hrId}")
    public ResponseEntity<?> updateHr(@PathVariable Long hrId, @RequestBody Hr updatedHr) {
        Hr updated = hrService.updateHr(hrId, updatedHr);
        return ResponseEntity.ok("HR updated successfully");
    }

    // ------------------------- Delete HR ---------------------------------------------
    /*
     * AIM     : To delete an HR by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : DELETE
     * INPUT   : hrId (path variable)
     * RESPONSE: String (confirmation message)
     */
    @DeleteMapping("/{hrId}")
    public ResponseEntity<?> deleteHr(@PathVariable Long hrId) {
        hrService.deleteHr(hrId);
        return ResponseEntity.ok("HR with ID " + hrId + " has been deleted successfully.");
    }

    // ------------------------- Get All HRs ---------------------------------------------
    /*
     * AIM     : To get a list of all HRs
     * PATH    : /api/hr
     * METHOD  : GET
     * RESPONSE: List<Hr> (all HR records)
     */
    @GetMapping
    public ResponseEntity<?> getAllHrs() {
        List<Hr> list = hrService.getAllHrs();
        return ResponseEntity.ok(list);
    }
}

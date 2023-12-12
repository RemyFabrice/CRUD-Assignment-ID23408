package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Service.StudentService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/unit" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class UnitController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UnitService unitService;
    //creating
    @PostMapping(value = "/saveUnit")
    public ResponseEntity<?> createUnit(@RequestBody AcademicUnit unit){
        if(unit != null ){

            String message = unitService.saveUnit(unit);
            if(message != null){
                return new ResponseEntity<>("Unit Saved Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Unit Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listUnits")
    public ResponseEntity<List<AcademicUnit>> listUnits() {
        List<AcademicUnit> units = unitService.listUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateUnit/{code}")
    public ResponseEntity<String> updateUnit(@PathVariable String code, @RequestBody AcademicUnit unit) {
        if (unit != null) {
            String message = unitService.updateUnit(code, unit);
            if (message != null) {
                return new ResponseEntity<>("Unit Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unit Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteUnit/{code}")
    public ResponseEntity<String> deleteUnit(@PathVariable String code) {
        if (code != null) {
            String message = unitService.deleteUnit(code);
            if (message != null) {
                return new ResponseEntity<>("Unit Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unit Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}

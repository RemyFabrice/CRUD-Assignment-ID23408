package com.auca.StudentRegistration.Model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;

@Entity
public class AcademicUnit {
    @Id
    private String code;
    private String name;

    @Enumerated(EnumType.STRING)
    private EAcademicUnit unitType;

    @ManyToOne
    @JoinColumn(name = "parent_unit_code")
    private AcademicUnit parentUnit;

    public AcademicUnit() {
    }

    public AcademicUnit(String code, String name, EAcademicUnit unitType, AcademicUnit parentUnit) {
        this.code = code;
        this.name = name;
        this.unitType = unitType;
        this.parentUnit = parentUnit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EAcademicUnit getUnitType() {
        return unitType;
    }

    public void setUnitType(EAcademicUnit unitType) {
        this.unitType = unitType;
    }

    public AcademicUnit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(AcademicUnit parentUnit) {
        this.parentUnit = parentUnit;
    }
}

//package com.academy.certificate.repository;
//
//import com.academy.certificate.domain.ToBeResidentList;
//import com.academy.certificate.entity.QHouseholdCompositionResident;
//import com.academy.certificate.entity.QResident;
//import com.academy.certificate.entity.Resident;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import java.util.List;
//
//public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
//
//    public ResidentRepositoryImpl() {
//        super(Resident.class);
//    }
//
//    QResident resident = QResident.resident;
//    QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
//
//    //    @Query("SELECT r.resident_serial_number, r.name, r.gender_code, " +
////            "r.birth_date, r.birth_place_code, r.registration_base_address " +
////            "from Resident as r " +
////            "INNER JOIN household_composition_resident hc on hc.resident_serial_number = r.resident_serial_number \n" +
////            "WHERE hc.household_serial_number= ?1")
//
////    @Override
////    public List<ToBeResidentList> getCompositionResidentList(Long residentSerialNumber) {
////        return from(resident)
////                .leftJoin()
////    }
//}

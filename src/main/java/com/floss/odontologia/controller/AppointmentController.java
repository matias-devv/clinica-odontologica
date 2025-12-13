package com.floss.odontologia.controller;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @Operation( summary = "Create appointment")
    @PostMapping("/create")
    public String createAppointment( @RequestBody Appointment appointment){
        return iAppointmentService.createAppo(appointment);
    }

    @Operation(summary = "Get appointment by ID")
    @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Appointment found"),
                    @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getAppointmentById(    @Parameter(description = "Appointment ID", example = "1")
                                                    @PathVariable Long id){

        AppointmentDTO dto = iAppointmentService.getAppointmentById(id);
        if(dto != null){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.status(404).body("Appointment not found");
    }

    @Operation(summary = "Get all appointments")
    @GetMapping("/find-all")
    public ResponseEntity<?> getAllAppointments(){

        List<AppointmentDTO> listDto = iAppointmentService.getAllAppointments();
        if( listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of appointments is empty");
    }

    @Operation(summary = "Get today's appointment number for a dentist")
    @GetMapping("/today/{id}")
    public int getAppointmentNumberToday(@PathVariable Long id){
        return iAppointmentService.getAppointmentNumberToday(id);
    }


    @Operation(
            summary = "Get available time slots for a dentist",
            description = "Returns 30-minute time blocks available for booking on a specific date"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Available time slots",
                    content = @Content(examples = @ExampleObject(value = "[\"09:30\", \"10:00\", \"10:30\"]"))
            ),
            @ApiResponse(responseCode = "404", description = "No available slots")
    })
    @GetMapping("/hours/{date}/{id_dentist}/{selectedDay}")
        public ResponseEntity<?> getHoursOfDentist(
                                @Parameter(description = "Date (yyyy-MM-dd)", example = "2025-12-15") @PathVariable LocalDate date,
                                @Parameter(description = "Dentist ID", example = "102") @PathVariable Long id_dentist,
                                @Parameter(description = "Day of week", example = "MONDAY") @PathVariable String selectedDay
                                                                                                                            ){

        List<LocalTime> hours = iAppointmentService.getHoursOfDentist(date, id_dentist, selectedDay);
        if( hours == null || hours.isEmpty() ){
            return ResponseEntity.status(404).body("The dentist has no free time");
        }
        return ResponseEntity.status(200).body(hours);
    }


    @Operation(summary = "Update an existing appointment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Appointment updated"),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
    })
    @PutMapping("/edit")
    public String editAppointment(  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                            description = "Appointment data to update",
                                            required = true,
                                            content = @Content(
                                                    examples = @ExampleObject(
                                                            value = """
                                                    {
                                                      "id_appointment": 2,
                                                      "date": "2025-12-15",
                                                      "startTime": "11:00",
                                                      "dentist": { "id_dentist": 102 },
                                                      "patient": { "id_patient": 2 }
                                                    }
                                                    """
                                                    )
                                            )
                                    )
                                    @RequestBody Appointment appointment){
        return iAppointmentService.editAppo(appointment);
    }

    @Operation(summary = "Delete appointment by ID")
    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id){
        return iAppointmentService.deleteAppo(id);
    }
}

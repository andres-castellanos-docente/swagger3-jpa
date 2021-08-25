package com.prueba.basedatos.controllers;

import com.prueba.basedatos.dtos.CiudadDto;
import com.prueba.basedatos.requests.CiudadRequests;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.basedatos.responses.CiudadResponse;
import com.prueba.basedatos.services.CiudadService;


@RestController
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;
    @ApiOperation(value = "Lista todas las ciudades",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/ciudad")
    public ResponseEntity<CiudadResponse> getCiudad() {
        return ciudadService.getCiudad();
    }

    @GetMapping("/ciudadxnombre/{nombre}")
    public ResponseEntity<CiudadResponse> getCiudadXNombre(@PathVariable(value = "nombre") String nombciud) {
        return ciudadService.getCiudadFinNombre(nombciud);
    }

    @GetMapping("/ciudadxnombrenative/{nombre}")
    public ResponseEntity<CiudadResponse> getCiudadXNombreNative(@PathVariable(value = "nombre") String nombciud) {
        return ciudadService.getCiudadFinNombreNative(nombciud);
    }

    @PostMapping("/ciudad")
    public ResponseEntity<CiudadResponse> addCiudad(@RequestBody CiudadDto ciudadDto) {
        return ciudadService.createEditCiudad(ciudadDto);
    }

    @ApiOperation(value = "Cambia ciudad de Estado",response = CiudadResponse.class)
    @PostMapping("/ciudadcambestado")
    public ResponseEntity<CiudadResponse> editStatusCiudad(  @ApiParam(
            name =  "ciudadRequest",
            type = "json",
            value = "Json activo, idPais",
            required = true) @RequestBody CiudadRequests ciudadRequests) {

        return ciudadService.inactivarCiudadporPais(ciudadRequests);
    }



    @PutMapping("/ciudad")
    public ResponseEntity<CiudadResponse> editCiudad(@RequestBody CiudadDto ciudadDto) {
        return ciudadService.createEditCiudad(ciudadDto);
    }

    @DeleteMapping("/ciudad/{id}")
    public ResponseEntity<CiudadResponse> delCiudad(@PathVariable(value = "id") Integer idCiudad) {
        return ciudadService.removeCiudad(idCiudad);
    }

}

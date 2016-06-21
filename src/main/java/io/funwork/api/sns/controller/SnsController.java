package io.funwork.api.sns.controller;

import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.service.SnsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sns")
public class SnsController {

  @Autowired
  SnsService snsService;

  @RequestMapping("/list")
  public ResponseEntity list() {
    List<Sns> snsList = snsService.getSnsList();
    if( snsList != null) {
      return new ResponseEntity<>(snsList, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping("/add")
  public ResponseEntity add(SnsCommand snsCommand) {
    Sns snsAdd = snsService.saveSns(snsCommand);
    if( snsAdd != null) {
      return new ResponseEntity<>(snsAdd, HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

}

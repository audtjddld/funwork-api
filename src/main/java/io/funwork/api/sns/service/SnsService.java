package io.funwork.api.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.repository.SnsRepository;

@Service
public class SnsService {

  @Autowired
  private SnsRepository snsRepository;

  public List<Sns> getSnsList(){
    return snsRepository.findAll();
  }

}

package io.funwork.api.sns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.organization.domain.Person;
import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.CommentSnsCommand;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.repository.CommentSnsRepository;
import io.funwork.api.sns.repository.FileSnsRepository;
import io.funwork.api.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SnsService {

  @Autowired
  private SnsRepository snsRepository;

  @Autowired
  private FileSnsRepository fileSnsRepository;

  @Autowired
  private CommentSnsRepository commentSnsRepository;

  public List<Sns> getSnsList(){
    return snsRepository.findAll();
  }

  public Sns saveSns(SnsCommand snsCommand){

    Sns sns = Sns.createSns(snsCommand);
    sns = snsRepository.save(sns);

    //첨부파일등록
    if (snsCommand.getFileSns() != null) {
      FileSns fileSns = saveFileSns(sns);
      sns.addFileSns(fileSns);
    }

    return sns;
  }

  private FileSns saveFileSns(Sns sns) {
    FileSns fileSns = new FileSns(sns);
    return fileSnsRepository.save(fileSns);
  }

  public List<CommentSns> saveCommentSns(CommentSnsCommand commentSnsCommand){

    CommentSns commentSns = CommentSns.createCommentSns(commentSnsCommand);

    log.info("등록 전 :" + commentSns);
    commentSns = commentSnsRepository.save(commentSns);
    log.info("등록 후 :" + commentSns);

    Long snsId = commentSns.getSns().getId();

    return getCommentSnsList(snsId);
  }

  public List<CommentSns> getCommentSnsList(Long snsId){
    return commentSnsRepository.findBySnsId(snsId);
  }

}

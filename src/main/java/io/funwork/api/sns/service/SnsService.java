package io.funwork.api.sns.service;

import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.LikeSns;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.CommentSnsCommand;
import io.funwork.api.sns.domain.support.command.LikeSnsCommand;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.repository.CommentSnsRepository;
import io.funwork.api.sns.repository.FileSnsRepository;
import io.funwork.api.sns.repository.LikeSnsRepository;
import io.funwork.api.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SnsService {

    @Autowired
    private SnsRepository snsRepository;

    @Autowired
    private FileSnsRepository fileSnsRepository;

    @Autowired
    private CommentSnsRepository commentSnsRepository;

    @Autowired
    private LikeSnsRepository likeSnsRepository;

    public List<Sns> getSnsList() {
        return snsRepository.findAll();
    }

    public Sns saveSns(SnsCommand snsCommand) {
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

    public CommentSns saveCommentSns(CommentSnsCommand commentSnsCommand) {
        CommentSns commentSns = CommentSns.createCommentSns(commentSnsCommand);
        commentSns = commentSnsRepository.save(commentSns);

        return commentSns;
    }

    public LikeSns saveLikeSns(LikeSnsCommand likeSnsCommand) {
        LikeSns likeSns = LikeSns.createLikeSns(likeSnsCommand);
        likeSns = likeSnsRepository.save(likeSns);

        return likeSns;
    }

}

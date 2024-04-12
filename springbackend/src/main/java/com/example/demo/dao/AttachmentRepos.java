package com.example.demo.dao;

import com.example.demo.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AttachmentRepos extends JpaRepository<Attachment, String> {
}

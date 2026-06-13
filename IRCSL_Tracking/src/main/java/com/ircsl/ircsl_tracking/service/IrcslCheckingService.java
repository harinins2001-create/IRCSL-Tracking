package com.ircsl.ircsl_tracking.service;



import com.ircsl.ircsl_tracking.model.IrcslChecking;
import com.ircsl.ircsl_tracking.repository.IrcslCheckingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IrcslCheckingService {

    private final IrcslCheckingRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String myEmail;

    public IrcslCheckingService(IrcslCheckingRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }

    public List<IrcslChecking> getAllCheckings() {
        return repository.findAll();
    }

    public Optional<IrcslChecking> getCheckingById(String id) {
        return repository.findById(id);
    }

//    public IrcslChecking saveChecking(IrcslChecking checking) {
//        return repository.save(checking);
//    }

    public IrcslChecking updateChecking(String id, IrcslChecking updatedChecking) {
        return repository.findById(id).map(checking -> {
            checking.setIrcslDescription(updatedChecking.getIrcslDescription());
            checking.setResponsiblePerson(updatedChecking.getResponsiblePerson());
            checking.setSentDateToResponsiblePerson(updatedChecking.getSentDateToResponsiblePerson());
            checking.setDueDate(updatedChecking.getDueDate());
            checking.setSentDateToIrcsl(updatedChecking.getSentDateToIrcsl());
            return repository.save(checking);
        }).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public void deleteChecking(String id) {
        repository.deleteById(id);
    }

    public void sendInstantDueAlert(IrcslChecking record) {
        LocalDate today = LocalDate.now();

        // චෙක් කරනවා මේ දැන් සේව් කරපු රෙකෝඩ් එකේ Due Date එක අද දවසද කියලා
        if (record.getDueDate() != null && record.getDueDate().isEqual(today)) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo("harini.s@arpicoinsurance.com");
                message.setSubject("⚠️ IRCSL Due Date Alert (Today)!");

                String body = "The following IRCSL record is due today:\n\n" +
                        "- Description: " + record.getIrcslDescription() + "\n" +
                        "- Responsible Person: " + record.getResponsiblePerson() + "\n" +
                        "- Due Date: " + record.getDueDate() + "\n\n" +
                        "This is an automated instant alert.";

                message.setText(body);
                mailSender.send(message);
                System.out.println("Instant due date email alert sent successfully for Record ID: " + record.getId());
            } catch (Exception e) {
                System.err.println("Failed to send email alert: " + e.getMessage());
            }
        }
    }

    public IrcslChecking saveChecking(IrcslChecking checking) {

        IrcslChecking savedRecord = repository.save(checking);
        sendInstantDueAlert(savedRecord);
        return savedRecord;
    }
}

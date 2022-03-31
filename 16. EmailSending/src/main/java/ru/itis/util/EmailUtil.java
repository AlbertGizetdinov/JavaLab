package ru.itis.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {

        Configuration configuration;
        configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setDefaultEncoding("UTF-8");
        StringWriter writer = new StringWriter();
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new ClassPathResource("mails").getFile()));
            Template template = configuration.getTemplate(templateName + ".ftlh");
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        final String mailText = writer.getBuffer().toString();

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailText, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
        };

        mailSender.send(preparator);
    }
}

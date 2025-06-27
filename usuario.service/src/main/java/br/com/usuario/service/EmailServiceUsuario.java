package br.com.usuario.service;

import br.com.usuario.dto.EmailCorpoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceUsuario {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarConfirmacaoUsuarioCriado(EmailCorpoDto dados) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dados.getLogin());
        message.setSubject("Usuário criado com Sucesso");

        String corpo = """
Olá, %s!

Seu usuário foi criado com sucesso em nosso sistema. Abaixo estão as suas informações de acesso:

🧑 Nome: %s  
🔐 Login: %s  
👤 Tipo de usuário: %s

Agora você já pode acessar o sistema e começar a utilizar todos os recursos disponíveis para o seu perfil.

Caso tenha qualquer dúvida, nossa equipe está à disposição para ajudar.

Bem-vindo(a)!

Atenciosamente,  
Equipe Mony courses
""".formatted(dados.getNome(), dados.getNome(), dados.getLogin(), dados.getTipoUsuario().name());


        message.setText(corpo);
        mailSender.send(message);
        System.out.println("E-mail enviado com sucesso para " + dados.getLogin());
    }
}

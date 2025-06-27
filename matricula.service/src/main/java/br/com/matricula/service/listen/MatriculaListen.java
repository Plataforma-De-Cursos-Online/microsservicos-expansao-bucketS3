package br.com.matricula.service.listen;

import br.com.matricula.service.dto.EmailUsuarioCursoDto;
import br.com.matricula.service.dto.UsuarioDto;
import br.com.matricula.service.service.EmailServiceMatricula;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MatriculaListen {

    @Autowired
    EmailServiceMatricula emailServiceMatricula;

    @RabbitListener(queues = "matricula.confirmada")
    public void ouvirMatricula(EmailUsuarioCursoDto emailCorpo) {

        emailServiceMatricula.enviarConfirmacaoTransacao(emailCorpo);
        System.out.println("Login: " + emailCorpo.getLogin() + " Nome: " + emailCorpo.getNome());
    }

}
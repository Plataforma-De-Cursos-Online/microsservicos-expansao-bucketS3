package br.com.usuario.listen;

import br.com.usuario.config.RabbitMQConfig;
import br.com.usuario.dto.EmailCorpoDto;
import br.com.usuario.service.EmailServiceUsuario;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioListen {

    @Autowired
    EmailServiceUsuario emailServiceMatricula;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void ouvirUsuario(EmailCorpoDto emailCorpo) {

        emailServiceMatricula.enviarConfirmacaoUsuarioCriado(emailCorpo);
        System.out.println("Login: " + emailCorpo.getLogin() + " Nome: " + emailCorpo.getNome());
    }

}

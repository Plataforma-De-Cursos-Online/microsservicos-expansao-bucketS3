# Microsserviços - Acabou Mony
Este repositório contém a implementação de microsserviços do projeto Acabou Mony.

🗃️ Banco de Dados
O banco de dados utilizado é o PostgreSQL.
Para iniciar o projeto, siga os passos abaixo:

✅ Link para instalar o PostgreSQL
https://sbp.enterprisedb.com/getfile.jsp?fileid=125956

Crie o banco de dados:
No PostgreSQL, execute o seguinte comando para criar o banco:

CREATE DATABASE acabou_mony;

📦 RabbitMQ
Utilizamos o RabbitMQ como sistema de mensageria entre os microsserviços.

🔗 Instalação
Baixe o RabbitMQ:

Baixar o RabbitMQ Server - https://github.com/rabbitmq/rabbitmq-server/releases/download/v4.1.0/rabbitmq-server-4.1.0.exe
 
Baixar o Erlang - https://github.com/erlang/otp/releases/download/OTP-28.0/otp_win64_28.0.exe        

No terminal como administrador
Instalar Pluggins para Projeto: rabbitmq-plugins enable rabbitmq_management
Iniciar servidor: rabbitmq-service start

🚀 Iniciando os Microsserviços

Com o banco de dados PostgreSQL, inicie cada um dos microsserviços do projeto. Lembre-se de muda a senha no application.properties de cada microsserviços 

📌 Observações
Antes de iniciar os microsserviços, verifique se o banco de dados e o Kafka estão devidamente configurados e em execução.

Em caso de problemas, consulte a documentação oficial do PostgreSQL e do Kafka para obter detalhes sobre configuração e troubleshooting.

📞 Contato:
Em caso de dúvidas ou sugestões, fique à vontade para enviar um email para:
turmadamonycasquad99@gmail.com



## 🧪 Testando a API com Insomnia/Postman

Siga os passos abaixo para testar o sistema utilizando o **Insomnia/Postman**. 

> ⚠️ **Importante:** Todas as requisições após o login exigem o token de autenticação (Bearer Token) no **Header**.










A FAZER ABAIXO ************* $$$$$$$$$$$$$$$$$ %%%%%%%%%%%%%%%%%%%%% A FAZER ABAIXO 







---

### 👤 Criação de Usuário

**URL:** `POST http://localhost:8084/usuario`  
**Body (JSON):**  

```json
{  
  "nome": "tiago",  
  "login": "tiago.elastic@gmail.com",  
  "password": "senhaSegura123",  
  "cpf": "221.536.578-33",  
  "telefone": "(11) 91234-5655",  
  "dtNasc": "1990-05-20T00:00:00.000+00:00",  
  "role": "admin"  
}  
``` 

### 🔐 Login do Usuário

**URL:** `POST http://localhost:8084/usuario/login`  
**Body (JSON):**
``` json
{
  "login": "tiago.elastic@gmail.com",
  "password": "senhaSegura123"
}
``` 

### 🏦 Criação de Conta

**URL:** `POST http://localhost:8080/api/conta`  
**Body (JSON):**
``` json
{
"dataVencimento": "2025-10-12", 
"limite": 1000.00,
"agencia": 100,
"numero": 123421,
"banco": 1,
"idUsuario": "2db7e9bb-171b-4147-9df8-ebb739267099"
}
``` 

### 📦 Criação de Produto

**URL:** `POST http://localhost:8080/api/produto/criar`  
**Body (JSON):**
``` json
{
"nome": "Fone de Ouvido Bluetooth",
"preco": 199.99,
"descricao": "Fone de ouvido com cancelamento de ruído e conexão Bluetooth 5.0",
"disponivel": 1,
"quantidade": 10
}
``` 

### 🛒 Criação de Pedido

**URL:** `POST http://localhost:8080/api/pedido/criar`  
**Body (JSON):**
``` json
{
  "usuario": "2db7e9bb-171b-4147-9df8-ebb739267099",
	"tipo": "CREDITO",
  "produtos": [
    "0f12645f-1907-43d0-acc8-2283cb50bf0e"
  ]
}
```

### ✅ Confirmação do Pedido

**URL:** `PATCH http://localhost:8080/api/pedido/concluir-transacao`  
**Body (JSON):**
``` json
{
  "idUsuario": "2db7e9bb-171b-4147-9df8-ebb739267099",
  "idPedido": "d5966c0f-8b53-49fa-a63f-cfedad0356d0"
}
``` 

### 💸 Criação Manual da Transação

**URL:** `POST http://localhost:8080/api/transacao`  
**Body (JSON):**
``` json
{
  "tipo": "CREDITO",
  "cartao": "280e40c1-f775-4c0b-8047-8e46a5c6d525",
  "destinatario": "9ffce95b-d383-405e-9432-577534af3825",
  "pedido": "d5966c0f-8b53-49fa-a63f-cfedad0356d0"
}
``` 
OBS: O destinatário não precisa alterar.


### 🛠️ Observações Técnicas
📬 Envio de E-mails: Durante a primeira execução, o sistema pode demorar mais de 1 segundo, pois envolve a inicialização do produtor Kafka e conexões SMTP para envio de e-mails.

🚀 Nas execuções subsequentes, o tempo de resposta será menor, já que os componentes já estarão carregados em memória.

### 🤝 Contribuidores
Este projeto foi desenvolvido com a colaboração de um time dedicado e comprometido. Agradecimentos especiais aos integrantes do squad:

* Eduardo Kendi De Sousa Miyasaki:
	- Microsserviço de conta
 	- teste de carga conta
	- Colaboração no microsserviço de transação
* João Lázaro Neto:
  	- Microsserviço de usuario
  	- teste de carga usuario
  	- Arquitetura dos microsservico
  	- Swagger
* Mônica Jiuliani Leamari:
  	- Parte de produto no microsservico de pedido
  	- teste de carga de transação
  	- Colaboração no microsserviço de transação
* Maikon Douglas Da Silva Gomes:
  	- Parte de pedido no microsservico de pedido
  	- Colaboração no microsserviço de transação
  	- teste de carga pedido

Desenvolvido em conjunto:
- JWT
- API Gateway
- Prints de comprovação
- DER
- Envio de email
- Mensageria Kafka

Cada um contribuiu ativamente para o desenvolvimento, testes, arquitetura e melhorias dessa aplicação. O trabalho em equipe foi essencial para transformar a ideia em um projeto funcional e robusto. 💪🚀

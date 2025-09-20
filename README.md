# Hackaton Orbitall 2025 - API de Gestão de Clientes e Transações

API REST desenvolvida como solução для o desafio final do Hackaton Orbitall 2025, focada no gerenciamento de clientes e suas transações financeiras em um ecossistema Open Finance simulado.

## 🚀 Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA & Spring Web
- Spring Validation para DTOs
- H2 Database (banco de dados em memória)
- Lombok
- Maven

## ✨ Diferenciais Implementados
- **Tratamento Global de Erros:** Utilização de `@ControllerAdvice` para fornecer respostas de erro padronizadas e claras para cenários de 400 (Bad Request), 404 (Not Found) e 500 (Internal Server Error).
- **API Versionada:** Adoção de versionamento na URL (`/v1`) para garantir a manutenibilidade futura da API.
- **Design com DTOs:** Uso do padrão Request/Response DTO para garantir um contrato de API seguro e desacoplado da lógica de domínio.

## ▶️ Como Rodar a Aplicação

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/1GabrielBastos/HacktonOrbitall-2025.git](https://github.com/1GabrielBastos/HacktonOrbitall-2025.git)
   cd HacktonOrbitall-2025
   ```
2. **Execute a aplicação com Maven:**
   ```bash
   mvn spring-boot:run
   ```
A API estará disponível em `http://localhost:8081`.

## 🗄️ Acessando o Banco de Dados (H2 Console)
Com a aplicação em execução, acesse o console do banco de dados em memória para inspecionar os dados.

- **URL:** [**http://localhost:8081/h2-console**](http://localhost:8081/h2-console)
- **JDBC URL:** `jdbc:h2:mem:hackatondb`
- **User Name:** `sa`
- **Password:** (deixe em branco)

## 📡 Endpoints Principais
A seguir estão exemplos de requisições para os endpoints da API.

### Clientes (`/v1/api/customers`)

**Criar um novo cliente**
```bash
curl -X POST http://localhost:8081/v1/api/customers \
-H "Content-Type: application/json" \
-d '{
    "fullName": "Luis Forcinnetti",
    "email": "luis.forcinnetti@example.com",
    "phone": "51912341234"
}'
```

**Buscar cliente por ID**
```bash
curl http://localhost:8081/v1/api/customers/{customerId}
```

**Listar todos os clientes ativos**
```bash
curl http://localhost:8081/v1/api/customers
```

### Transações (`/v1/api/transactions`)

**Criar uma nova transação**
```bash
curl -X POST http://localhost:8081/v1/api/transactions \
-H "Content-Type: application/json" \
-d '{
    "customerId": "ID_DO_CLIENTE_CRIADO_ANTERIORMENTE",
    "amount": 75.25,
    "cardType": "CREDIT"
}'
```

**Listar transações de um cliente**
```bash
curl http://localhost:8081/v1/api/transactions?customerId={customerId}
```
---
